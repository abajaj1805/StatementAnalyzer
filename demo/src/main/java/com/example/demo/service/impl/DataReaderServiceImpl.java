package com.example.demo.service.impl;

import com.example.demo.model.Transaction;
import com.example.demo.service.DataReaderService;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
@Service
public class DataReaderServiceImpl implements DataReaderService {
    @Override
    public List<Transaction> readData(String fileName) {
        List<Transaction> trxList = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line = br.readLine();
            while(line!=null)
            {
                String[] trxData = line.split(",");
                trxList.add(new Transaction(new SimpleDateFormat("dd/mm/yyyy").parse(trxData[0]),  new BigDecimal(trxData[1]),trxData[2]));
                line = br.readLine();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        return trxList;
    }
}
