package com.smartWorkers.gestionBudgets;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.smartWorkers.gestionBudgets.dao.TransactionsRepository;
import com.smartWorkers.gestionBudgets.entities.Transactions;

@SpringBootTest
class GestionBudgetsApplicationTests {

	@Autowired
	private TransactionsRepository transactionRepository; 
	
	/*@Test
	public void testCreateProduit() { 
	 Transactions prod = new Transactions("bonne et rigide",120.5,"RESTO", new Date(), new Date(),"Resto"); 
	 transactionRepository.save(prod); 
    } */

}
