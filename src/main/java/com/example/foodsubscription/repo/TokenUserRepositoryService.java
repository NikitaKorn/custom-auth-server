package com.example.foodsubscription.repo;

import com.example.foodsubscription.domain.entity.TokenUser;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * Сервис обертка над интерфейсом репозитория пользователей.
 * Необходим для более гибкой настройки логирования и кеширования.
 */
@Slf4j
@Service
@AllArgsConstructor
public class TokenUserRepositoryService {
    private TokenUserRepository dao;

    @Cacheable(value = "users", key = "#login")
    public TokenUser findByLogin(String login) {
        log.debug("Finding user with login {}", login);
        return dao.findByLogin(login);
    }

    public void save(TokenUser user) {
        log.debug("Saving user with login {}", user.getLogin());
        dao.save(user);
    }
}
