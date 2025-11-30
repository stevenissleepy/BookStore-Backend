package fun.steven.bookstore.pojo.dto.tag;

import java.util.List;

import lombok.Data;

@Data
public class AddTagRequest {
    private List<String> tags;
}
