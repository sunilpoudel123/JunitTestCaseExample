package com.junitExamples.isbntools;

public class ValidatorISBN {

    public static final int LONG_ISBN_LENGTH = 13;
    public static final int SHORT_ISBN_LENGTH = 10;
    public static final int SHORT_ISBN_MULTIPLIER = 11;
    public static final int LONG_ISBN_MULTIPLIER = 10;

    public boolean checkISBN(String ISBN) {

        if(ISBN.length()== LONG_ISBN_LENGTH){
            return isThisAValid13DigitISBN(ISBN);
        }
        else {
            if (ISBN.length() != SHORT_ISBN_LENGTH){
                throw new NumberFormatException("ISBN number should be 10 digit long");
            }
            return isThisAValid10DigitISBN(ISBN);
        }
    }

    private boolean isThisAValid10DigitISBN(String ISBN) {
        int total = 0;
        for (int i = 0; i < SHORT_ISBN_LENGTH; i++) {
            if(!Character.isDigit(ISBN.charAt(i))) {
                if(i==9 && ISBN.charAt(i)=='X'){
                    total +=10;
                }
                else {
                    throw new NumberFormatException("ISBN can only contains Number");
                }
            }
            else {
                total += Character.getNumericValue(ISBN.charAt(i)) * (SHORT_ISBN_LENGTH - i);
            }
        }

        if (total % SHORT_ISBN_MULTIPLIER == 0) {
            return true;
        } else
            return false;
    }

    private boolean isThisAValid13DigitISBN(String ISBN) {
        int total=0;
        for(int i=0; i<LONG_ISBN_LENGTH; i++){
            if(i%2==0){
                total += Character.getNumericValue(ISBN.charAt(i));
            }
            else{
                total += Character.getNumericValue(ISBN.charAt(i)) * 3;
            }
        }
        if (total % LONG_ISBN_MULTIPLIER == 0) {
            return true;
        } else
            return false;
    }
}
