package com.example.demo;

import com.example.demo.model.Transaction;
import com.example.demo.service.DataReaderService;
import com.example.demo.service.ReportingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {
	@Autowired
	DataReaderService dataReaderService;

	@Autowired
	ReportingService reportingService;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		List<Transaction> trxList = dataReaderService.readData("src/main/resources/static/statement.csv");
		trxList.stream().forEach(trx -> System.out.println(trx.toString()));

		System.out.println("Total Income : " + reportingService.calculateIncome(trxList) );
		System.out.println("Total Expenses : " + reportingService.calculateTotalExpenses(trxList) );
		System.out.println("Total Savings : " + reportingService.calculateTotalSavings(trxList) );
		Map.Entry<String, BigDecimal> entry = reportingService.calculateMaxSpendCategory(trxList);
		if(entry != null)
		System.out.println("Top Expense Category : "+ entry.getKey() + " , Amount :"  + entry.getValue().abs() );
		else
		System.out.println("No Elements found");
	}
}

