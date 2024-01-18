package com.example.foodsubscription.repo;

import com.example.foodsubscription.domain.entity.TokenUser;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Делаем репозиторий доступным только внутри пакета.
 * Для доступа к репозиторию необходимо использовать соответсвующий сервис, агрегирующий данный репозиторий.
 */
@Repository
interface TokenUserRepository extends JpaRepository<TokenUser, Long> {
    TokenUser findByLogin(String login);

    TokenUser save(TokenUser user);
}
