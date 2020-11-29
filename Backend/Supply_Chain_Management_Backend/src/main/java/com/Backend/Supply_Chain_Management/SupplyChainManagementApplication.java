package com.Backend.Supply_Chain_Management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.Backend.Supply_Chain_Management"})
public class SupplyChainManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(SupplyChainManagementApplication.class, args);
	}

}
