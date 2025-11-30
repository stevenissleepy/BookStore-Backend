package fun.steven.bookstore.pojo.dto.tag;

import lombok.Data;

@Data
public class HierarchyRequest {
    private String parentTag;
    private String childTag;
}
