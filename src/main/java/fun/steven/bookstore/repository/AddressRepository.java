package fun.steven.bookstore.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fun.steven.bookstore.pojo.entity.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
    Optional<List<Address>> findByUserId(Long userId);
}
