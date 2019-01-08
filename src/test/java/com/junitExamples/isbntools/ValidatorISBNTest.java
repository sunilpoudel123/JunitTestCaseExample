package com.junitExamples.isbntools;

import org.junit.*;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class ValidatorISBNTest {

    @Test
    public void checkAValid10DigitISBN(){

        ValidatorISBN validatorISBN= new ValidatorISBN();
        boolean result= validatorISBN.checkISBN("1783985801");
        assertTrue("first test", result);
        result= validatorISBN.checkISBN("1789131898");
        assertTrue("Second test", result);

    }

    @Test
    public void tenDigitISBNNumberEndingWithXAreValid(){
        ValidatorISBN validatorISBN= new ValidatorISBN();
        boolean result= validatorISBN.checkISBN("012000030X");
    }

    @Test
    public void ISBNNumberWith13DigitAreValid(){
        ValidatorISBN validatorISBN= new ValidatorISBN();
        boolean result= validatorISBN.checkISBN("9781438008653");
        assertTrue("first test", result);
        result= validatorISBN.checkISBN("9781642810059");
        assertTrue("second test",result );

    }

    @Test
    public void checkAInValid10DigitISBN(){

        ValidatorISBN validatorISBN= new ValidatorISBN();
        boolean result= validatorISBN.checkISBN("0134670941");
        assertFalse(result);

    }
    @Test
    public void checkAValid13DigitISBN(){

        ValidatorISBN validatorISBN= new ValidatorISBN();
        boolean result= validatorISBN.checkISBN("9781642810059");
        assertTrue(result);
    }


    @Test(expected = NumberFormatException.class)
    public void nineDigitIsbnNotAllowed(){
        ValidatorISBN validatorISBN= new ValidatorISBN();
        validatorISBN.checkISBN("123456789");

    }

    @Test(expected = NumberFormatException.class)
    public void StringAreNotAllowed(){
        ValidatorISBN validatorISBN= new ValidatorISBN();
        validatorISBN.checkISBN("helloworld");
    }


}
