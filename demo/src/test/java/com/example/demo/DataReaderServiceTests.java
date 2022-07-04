package com.example.demo;

import com.example.demo.service.DataReaderService;
import com.example.demo.service.impl.DataReaderServiceImpl;
import  static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.io.FileNotFoundException;

public class DataReaderServiceTests {
    DataReaderService dataReaderService = new DataReaderServiceImpl();
    @Test
    void readDataTest_withIncorrectFile()
    {
        assertThrows(FileNotFoundException.class , (Executable) dataReaderService.readData("abc"));

    }

    @Test
    @DisplayName("Verify List count")
    void readDataTest_verifyListCount()
    {
        assertEquals(9 , dataReaderService.readData("src/main/resources/static/statement.csv").size());

    }
}
