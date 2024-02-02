package com.example.foodsubscription.repo;

import com.example.foodsubscription.domain.entity.User;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Сервис обертка над интерфейсом репозитория пользователей.
 * Необходим для более гибкой настройки логирования и кеширования.
 */
@Slf4j
@Service
@AllArgsConstructor
public class UserRepositoryService {
    private UserRepository dao;

    @Cacheable(value = "users", key = "#login")
    public User findByLogin(String login) {
        log.debug("Finding user with login {}", login);
        return dao.findByLogin(login).orElseThrow(() -> new UsernameNotFoundException("User with login = " + login + " not exist!"));
    }

    public void save(User user) {
        log.debug("Saving user with login {}", user.getLogin());
        dao.save(user);
    }
}
