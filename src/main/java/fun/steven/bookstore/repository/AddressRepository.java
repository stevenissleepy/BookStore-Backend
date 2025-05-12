package fun.steven.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fun.steven.bookstore.entity.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
}
