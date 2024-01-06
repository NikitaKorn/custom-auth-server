package com.example.foodsubscription;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.validation.annotation.Validated;

import java.util.Set;

@SpringBootTest
class FoodSubscriptionApplicationTests {

	@Autowired
	Validator validator;

	@Test
	void contextLoads() {
		TestClass testClass = new TestClass();
		Set<ConstraintViolation<TestClass>> validate = validator.validate(testClass);
		System.out.println(validate.isEmpty());
		System.out.println();
	}

	@Validated
	@NoArgsConstructor
	@AllArgsConstructor
	class TestClass{
		@NotNull
		String field;
	}
}
