package com.example.demo.service;

import com.example.demo.model.Transaction;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface ReportingService {

    BigDecimal calculateIncome(List<Transaction> trxList);

    BigDecimal calculateTotalExpenses(List<Transaction> trxList);

    BigDecimal calculateTotalSavings(List<Transaction> trxList);

    Map.Entry<String, BigDecimal> calculateMaxSpendCategory(List<Transaction> trxList);
}
