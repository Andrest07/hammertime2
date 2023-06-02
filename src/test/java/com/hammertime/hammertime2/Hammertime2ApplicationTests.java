package com.hammertime.hammertime2;

import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;

@SpringBootTest
@ComponentScan({"com"})
@EntityScan({"com"})
class Hammertime2ApplicationTests {

	@Test
	void contextLoads() {
	}

}
