package com.example.HealthCareMini.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.ClassOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.example.HealthCareMini.Entity.Specialization;
import com.example.HealthCareMini.repo.SpecializationRepository;

@DataJpaTest(showSql = true)
//one to use current database configuration
@AutoConfigureTestDatabase(replace = Replace.NONE)
//one to use after test done rollback / remove the newly created data
@Rollback(false)
//@TestMethodOrder(OrderAnnotation.class)
public class SpecializationTest {
	@Autowired
	private SpecializationRepository repo;
	
	@Test
	@Order(1)
	public  void testData() {
		Specialization spes = new Specialization(null,"pavan", "CARDIO","CARDIO EXPERT");
		spes = repo.save(spes);
		assertNotNull(spes, "spes record is not created");
		
	}
	
	

}
