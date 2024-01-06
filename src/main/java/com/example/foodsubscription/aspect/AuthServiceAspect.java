package com.example.foodsubscription.aspect;

import com.example.foodsubscription.exception.CustomValidationException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Врубаем валидацию входящих параметров всех методов в сервис классе через аспект.
 * Тем самым избегаем дублирование и будущие изменения в сервис классе.
 */
@Aspect
@Component
public class AuthServiceAspect {
    @Autowired
    private Validator validator;

    @Pointcut("execution(public * com.example.foodsubscription.service.AuthService.*(..))")
    public void callAllClassMethods(){}

    @Before("callAllClassMethods()")
    public void beforeCallMethod(JoinPoint jp){
        Optional<Set<ConstraintViolation<Object>>> violations = Arrays.stream(jp.getArgs())
                .map(el -> validator.validate(el))
                .filter(el -> !el.isEmpty())
                .findFirst();

        if(violations.isPresent()){
            Set<ConstraintViolation<Object>> constraintViolations = violations.get();
            String resp = constraintViolations.stream().map(el -> el.getPropertyPath() + " " + el.getMessage()).collect(Collectors.joining("; "));
            throw new CustomValidationException(resp);
        }
    }
}
