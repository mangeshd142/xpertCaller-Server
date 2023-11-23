package com.company.consultant.db.repositories;

import com.company.consultant.db.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
        public Optional<User> findByUsername(String userName);
        public User findByMobileNumber(String mobileNumber);
}
