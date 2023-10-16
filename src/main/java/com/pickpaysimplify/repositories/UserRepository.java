package com.pickpaysimplify.repositories;

import com.pickpaysimplify.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Users, Long> {

    Optional<Users> findByDocument(String document);
}
