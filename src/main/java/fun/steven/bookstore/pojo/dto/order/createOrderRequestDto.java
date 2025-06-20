package fun.steven.bookstore.pojo.dto.order;

import java.util.List;

import lombok.Data;

@Data
public class createOrderRequestDto {
    private String receiver;
    private String tel;
    private String address;

    private List<Long> bookIds;
}
