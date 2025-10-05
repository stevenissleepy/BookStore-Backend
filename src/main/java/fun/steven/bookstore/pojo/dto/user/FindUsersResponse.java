package fun.steven.bookstore.pojo.dto.user;

import java.util.List;

import lombok.Data;

@Data
public class FindUsersResponse {
    private Integer quantity;
    private List<FindUserReponse> users;

    public FindUsersResponse(List<FindUserReponse> users) {
        this.quantity = users.size();
        this.users = users;
    }
}
