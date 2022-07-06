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
    @DisplayName("Verify Read Data with Invalid File Path")
    void readDataTest_withInvalidFilePath()
    {
        assertThrows(RuntimeException.class , () ->dataReaderService.readData("abc"));

    }

    @Test
    @DisplayName("Verify List count")
    void readDataTest_verifyListCount()
    {
        assertEquals(9 , dataReaderService.readData("src/main/resources/static/statement.csv").size());

    }

    @Test
    @DisplayName("Verify Data read for Empty File")
    void readDataTest_withEmptyFile()
    {
        assertEquals(0 , dataReaderService.readData("src/main/resources/static/emptyFile.csv").size());

    }

    @Test
    @DisplayName("Verify Incorrect List count")
    void readDataTest_verifyIncorrectListCount()
    {
        assertFalse(dataReaderService.readData("src/main/resources/static/statement.csv").size() == 10);

    }


}
