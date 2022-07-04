package com.example.demo.service;

import com.example.demo.model.Transaction;

import java.util.List;

public interface DataReaderService {

    List<Transaction> readData(String src);
}
