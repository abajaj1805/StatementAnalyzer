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
import java.util.List;


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
    @DisplayName("Check Total Income")
    void totalIncomeTest_withDataRead()
    {   List<Transaction> trxList = readerService.readData("src/main/resources/static/statement.csv");
        Assertions.assertEquals(new BigDecimal("21200"), reportingService.calculateIncome(trxList));
    }




}
