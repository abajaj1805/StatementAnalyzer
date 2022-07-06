package com.example.demo.service.impl;

import com.example.demo.model.Transaction;
import com.example.demo.service.ReportingService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReportingServiceImpl implements ReportingService {
    @Override
    public BigDecimal calculateIncome(List<Transaction> trxList) {
       return  trxList.stream().filter(trx -> trx.getTrxAmount().compareTo(BigDecimal.ZERO) == 1)
        .map( trx -> trx.getTrxAmount()).reduce(BigDecimal.ZERO , BigDecimal::add);
    }

    @Override
    public BigDecimal calculateTotalExpenses(List<Transaction> trxList) {
        return  trxList.stream().filter(trx -> trx.getTrxAmount().compareTo(BigDecimal.ZERO) == -1)
                .map( trx -> trx.getTrxAmount()).reduce(BigDecimal.ZERO , BigDecimal::add).abs();

    }

    @Override
    public BigDecimal calculateTotalSavings(List<Transaction> trxList) {
        BigDecimal savings =  trxList.stream()
                              .map( trx -> trx.getTrxAmount()).reduce(BigDecimal.ZERO , BigDecimal::add);

        if(savings.compareTo(BigDecimal.ZERO) == 1 )
             return savings;
        else
            return BigDecimal.ZERO;

    }

    @Override
    public Map.Entry<String, BigDecimal> calculateMaxSpendCategory(List<Transaction> trxList) {

        Optional<Map.Entry<String, BigDecimal> > entry =   trxList.stream().filter(trx -> trx.getTrxAmount().compareTo(BigDecimal.ZERO) == -1)
                .collect(Collectors.groupingBy(Transaction::getTrxCategory,Collectors.reducing(BigDecimal.ZERO , Transaction::getTrxAmount, BigDecimal::add)))
             .entrySet().stream().min(Comparator.comparing(Map.Entry::getValue));

        if(entry.isPresent())
            return entry.get();

        return null;

}

}
