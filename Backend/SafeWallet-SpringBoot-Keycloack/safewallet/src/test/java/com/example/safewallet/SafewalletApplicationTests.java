package com.example.safewallet;

import com.example.safewallet.functions.generateCBU.CbuGeneratorClass;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SafewalletApplicationTests {

	@Test
	void cbuTest() {
		CbuGeneratorClass cbuGeneratorClass = new CbuGeneratorClass();
		String cbu = cbuGeneratorClass.cbuGenerator();
		System.out.println("El CBU es: " + cbu);

	}
}
