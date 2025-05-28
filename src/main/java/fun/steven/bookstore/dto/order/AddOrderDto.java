package fun.steven.bookstore.dto.order;

import java.util.List;

import lombok.Data;

@Data
public class AddOrderDto {
    private String receiver;
    private String tel;
    private String address;

    private List<Long> bookIds;
}
