package com.junitExamples.isbntools;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class StockManagementTests {

    ExternalISBNDataService testWebService;
    ExternalISBNDataService testDatabaseService;
    StockManager  stockManager;


    @Before
    public void setup(){
        testWebService=mock(ExternalISBNDataService.class);
        testDatabaseService= mock(ExternalISBNDataService.class);
        stockManager= new StockManager();
        stockManager.setDatabaseService(testDatabaseService);
        stockManager.setWebService(testWebService);
    }
    @Test
    public void testCanGetACorrectLocatorCode() {
        when(testWebService.lookup(anyString())).thenReturn(
                new Book(
                "1789131898",
                "Java Projects Fundamentals of Java Second Edition",
                "Peter Verhas"));
        when(testDatabaseService.lookup(anyString())).thenReturn(null);
        String ISBN = "1789131898";
        String locatorCode= stockManager.getLocatorCode(ISBN);
        assertEquals("1898P7",locatorCode);
    }
    @Test
    public void databaseIsUsedIfDataIsPresent(){
        when(testDatabaseService.lookup("1789131898")).thenReturn(
                new Book("1789131898",
                        "abc",
                        "abc"));
        String ISBN = "1789131898";
        stockManager.getLocatorCode(ISBN);
        verify(testDatabaseService).lookup("1789131898");
        verify(testWebService, never()).lookup(anyString());
    }
    @Test
    public void webserviceisUsedIfDataisnotPresentInDatabase(){
        when(testDatabaseService.lookup("1789131898")).thenReturn(null);
        when(testWebService.lookup("1789131898")).thenReturn(
                new Book("1789131898",
                        "abc",
                        "abc"));
        String ISBN = "1789131898";
        stockManager.getLocatorCode(ISBN);
        verify(testDatabaseService).lookup("1789131898");
        verify(testWebService).lookup("1789131898");
    }

}
