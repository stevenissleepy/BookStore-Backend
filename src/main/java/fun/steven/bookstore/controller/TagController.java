package fun.steven.bookstore.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fun.steven.bookstore.pojo.ResponseMessage;
import fun.steven.bookstore.pojo.dto.tag.AddTagRequest;
import fun.steven.bookstore.pojo.dto.tag.HierarchyRequest;
import fun.steven.bookstore.service.ITagService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/tag")
public class TagController {
    @Autowired
    private ITagService tagService;

    @PostMapping
    public ResponseMessage<?> addTags(@RequestBody AddTagRequest request) {
        tagService.addTags(request);
        return ResponseMessage.success("add tags success", null);
    }

    @PostMapping("/hierarchy")
    public ResponseMessage<?> createHierarchy(@RequestBody HierarchyRequest request) {
        tagService.createHierarchy(request);
        return ResponseMessage.success("create tag hierarchy success", null);
    }
}
