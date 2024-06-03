package com.ramramv.springbootserver;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.test.web.servlet.MockMvc;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
@RequiredArgsConstructor
@AutoConfigureMockMvc
class SpringBootServerApplicationTests {
	// private final MockMvc mockMvc;

	@Test
	void contextLoads() {
		System.out.println("Hello TEST");
		log.info("Hello TESTSETSET");
	}

	@Test
	void test1() {
		System.out.println("Hello TEST1");
		log.info("Hello TEST1");
	}

}
