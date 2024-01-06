package com.example.foodsubscription.repo;

import com.example.foodsubscription.domain.entity.TokenUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenUserRepository extends JpaRepository<TokenUser, Long> {
    TokenUser findByLogin(String login);

    TokenUser save(TokenUser user);
}
