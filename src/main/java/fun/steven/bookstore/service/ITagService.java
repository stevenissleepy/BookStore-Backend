package fun.steven.bookstore.service;

import org.springframework.stereotype.Service;

import fun.steven.bookstore.pojo.dto.tag.AddTagRequest;
import fun.steven.bookstore.pojo.dto.tag.HierarchyRequest;

@Service
public interface ITagService {
    public void addTags(AddTagRequest request);

    public void createHierarchy(HierarchyRequest request);
}
