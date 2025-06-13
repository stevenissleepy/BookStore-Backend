package fun.steven.bookstore.pojo.dto.user;

import java.util.List;

import lombok.Data;

@Data
public class GetUsersDto {
    private Integer quantity;
    private List<GetUserDto> users;

    public GetUsersDto(List<GetUserDto> users) {
        this.quantity = users.size();
        this.users = users;
    }
}
