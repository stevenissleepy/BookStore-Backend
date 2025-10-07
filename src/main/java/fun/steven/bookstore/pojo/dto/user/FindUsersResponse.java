package fun.steven.bookstore.pojo.dto.user;

import java.util.List;

import lombok.Data;

@Data
public class FindUsersResponse {
    private Integer quantity;
    private List<FindUserResponse> users;

    public FindUsersResponse(List<FindUserResponse> users) {
        this.quantity = users.size();
        this.users = users;
    }
}
