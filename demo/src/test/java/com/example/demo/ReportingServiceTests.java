package com.example.demo;

import com.example.demo.model.Transaction;
import com.example.demo.service.DataReaderService;
import com.example.demo.service.ReportingService;
import com.example.demo.service.impl.DataReaderServiceImpl;
import com.example.demo.service.impl.ReportingServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestComponent;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;


public class ReportingServiceTests {

    ReportingService reportingService = new ReportingServiceImpl();
    DataReaderService readerService = new DataReaderServiceImpl();
    @Test
    @DisplayName("Total Income with Empty List")
    void totalIncomeTest_withEmptyList()
    {
        Assertions.assertEquals(BigDecimal.ZERO , reportingService.calculateIncome(new ArrayList<Transaction>()));
    }

    @Test
    @DisplayName("Check Total Income From File")
    void totalIncomeTest_withDataRead()
    {   List<Transaction> trxList = readerService.readData("src/main/resources/static/statement.csv");
        Assertions.assertEquals(new BigDecimal("21200"), reportingService.calculateIncome(trxList));
    }

    @Test
    @DisplayName("Check Incorrect Total Income From File")
    void totalIncomeTest_withIncorrectDataRead()
    {   List<Transaction> trxList = readerService.readData("src/main/resources/static/statement.csv");
        Assertions.assertFalse(reportingService.calculateIncome(trxList).equals(new BigDecimal("21201")));
    }

    @Test
    @DisplayName("Check Total Expense From File")
    void totalExpenseTest_withDataRead()
    {   List<Transaction> trxList = readerService.readData("src/main/resources/static/statement.csv");
        Assertions.assertEquals(new BigDecimal("16042.99"), reportingService.calculateTotalExpenses(trxList));
    }

    @Test
    @DisplayName("Total Expense with Empty List")
    void totalExpenseTest_withEmptyList()
    {
        Assertions.assertEquals(BigDecimal.ZERO , reportingService.calculateTotalExpenses(new ArrayList<Transaction>()));
    }

    @Test
    @DisplayName("Total Savings with Empty List")
    void totalSavings_withEmptyList()
    {
        Assertions.assertEquals(BigDecimal.ZERO , reportingService.calculateTotalSavings(new ArrayList<Transaction>()));
    }


    @Test
    @DisplayName("Check Total Savings From File ")
    void totalSavingsTest_withDataRead()
    {   List<Transaction> trxList = readerService.readData("src/main/resources/static/statement.csv");
        Assertions.assertEquals(new BigDecimal("5157.01"), reportingService.calculateTotalSavings(trxList));
    }
    @Test
    @DisplayName("Total Savings when expense is more than income")
    void totalSavingsTest_withExpenseMoreThanIncome()
    {
        List<Transaction> trxList = new ArrayList<>();
        trxList.add(new Transaction(new Date() , new BigDecimal("-5000") , "Grocery"));
        trxList.add(new Transaction(new Date() , new BigDecimal("1000") , "Salary"));
        Assertions.assertEquals(BigDecimal.ZERO , reportingService.calculateTotalSavings(trxList));
    }
    @Test
    @DisplayName("Check Top Expense Category from File")
    void topExpenseCategoryTest_withDataRead()
    {   List<Transaction> trxList = readerService.readData("src/main/resources/static/statement.csv");
        Assertions.assertEquals("Grocery", reportingService.calculateMaxSpendCategory(trxList).getKey());
    }

    @Test
    @DisplayName("Check Top Expense Category Value")
    void topExpenseCategoryAmount_withDataRead()
    {   List<Transaction> trxList = readerService.readData("src/main/resources/static/statement.csv");
        Assertions.assertEquals(new BigDecimal("6901.89"), reportingService.calculateMaxSpendCategory(trxList).getValue().abs());
    }

    @Test
    @DisplayName("Check Top Expense with Empty List")
    void topExpenseCategory_withEmptyList()
    {   List<Transaction> trxList = readerService.readData("src/main/resources/static/statement.csv");

        Assertions.assertNull( reportingService.calculateMaxSpendCategory(new ArrayList<>()));
    }






}
