package fun.steven.bookstore.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fun.steven.bookstore.pojo.dto.tag.AddTagRequest;
import fun.steven.bookstore.pojo.dto.tag.HierarchyRequest;
import fun.steven.bookstore.repository.TagRepository;
import fun.steven.bookstore.service.ITagService;

@Service
public class TagService implements ITagService {
    @Autowired
    private TagRepository tagRepository;

    public void addTags(AddTagRequest request) {
        tagRepository.addTags(request.getTags());
    }

    public void createHierarchy(HierarchyRequest request) {
        tagRepository.createHierarchy(request.getParentTag(), request.getChildTag());
    }
}
