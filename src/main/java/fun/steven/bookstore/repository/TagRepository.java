package fun.steven.bookstore.repository;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import org.springframework.data.neo4j.core.Neo4jClient;

@Repository
public class TagRepository {
    private static final String RELATION_TYPE = "TAG_REL";

    private final Neo4jClient neo4jClient;

    public TagRepository(Neo4jClient neo4jClient) {
        this.neo4jClient = neo4jClient;
    }

    public void addTags(Collection<String> tags) {
        if (CollectionUtils.isEmpty(tags)) {
            return;
        }
        tags.stream()
                .filter(StringUtils::hasText)
                .map(String::trim)
                .map(String::toLowerCase)
                .forEach(tag -> neo4jClient.query("MERGE (:Tag {name: $name})")
                        .bind(tag)
                        .to("name")
                        .run());
    }

    public void createHierarchy(String parentTag, String childTag) {
        if (!StringUtils.hasText(parentTag) || !StringUtils.hasText(childTag)) {
            return;
        }
        String parent = parentTag.trim().toLowerCase();
        String child = childTag.trim().toLowerCase();
        if (Objects.equals(parent, child)) {
            return;
        }
        String relationQuery = String.join("\n",
                "MERGE (parent:Tag {name: $parent})",
                "MERGE (child:Tag {name: $child})",
                "MERGE (parent)-[:" + RELATION_TYPE + "]->(child)",
                "MERGE (child)-[:" + RELATION_TYPE + "]->(parent)");
        neo4jClient.query(relationQuery)
                .bind(parent)
                .to("parent")
                .bind(child)
                .to("child")
                .run();
    }

    public Set<String> fetchRelatedTags(String anchorTag) {
        if (!StringUtils.hasText(anchorTag)) {
            return Collections.emptySet();
        }
        String normalizedTag = anchorTag.trim().toLowerCase();
        try {
            String findQuery = String.join("\n",
                    "MATCH (selected:Tag {name: $tag})",
                    "OPTIONAL MATCH (selected)-[:" + RELATION_TYPE + "*1..2]-(related:Tag)",
                    "RETURN selected.name AS selectedName, collect(DISTINCT related.name) AS relatedNames");
            var rows = neo4jClient.query(findQuery)
                    .bind(normalizedTag)
                    .to("tag")
                    .fetch()
                    .all();
            if (rows.isEmpty()) {
                Set<String> fallback = new HashSet<>();
                fallback.add(normalizedTag);
                return fallback;
            }
            Map<String, Object> row = rows.iterator().next();
            Set<String> tags = new HashSet<>();
            Object selected = row.get("selectedName");
            if (selected instanceof String name && StringUtils.hasText(name)) {
                tags.add(name.trim());
            }
            Object related = row.get("relatedNames");
            if (related instanceof Collection<?> collection) {
                collection.stream()
                        .filter(String.class::isInstance)
                        .map(String.class::cast)
                        .filter(StringUtils::hasText)
                        .map(String::trim)
                        .forEach(tags::add);
            }
            return tags.isEmpty() ? Set.of(normalizedTag) : tags;
        } catch (DataAccessException ex) {
            Set<String> fallback = new HashSet<>();
            fallback.add(normalizedTag);
            return fallback;
        }
    }
}
