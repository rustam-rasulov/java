package com.miirrr;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Calc {
    public static void main(String[] args) {
        int arabicNumberOne = 0;
        int arabicNumberTwo = 0;
        System.out.println("Input:");
        Scanner in = new Scanner(System.in);
        String ariphOperation = in.nextLine();
        ariphOperation = ariphOperation.replaceAll("\\s+","");
        ariphOperation = ariphOperation.toUpperCase();

        int indexOperPlace = indexOperPlaceProc(ariphOperation);
        String operationSymbol = ariphOperation.substring(indexOperPlace,indexOperPlace+1);
        String numberOne = ariphOperation.substring(0,indexOperPlace);
        String numberTwo = ariphOperation.substring(indexOperPlace+1);
        boolean numComparationStatus = numComparation(numberOne, numberTwo);
        if ( numComparationStatus == true) {
            if (Short.parseShort(numberOne) > 10 || Short.parseShort(numberTwo) > 10) errorMessage();
            else {
                arabicNumberOne = Short.parseShort(numberOne);
                arabicNumberTwo = Short.parseShort(numberTwo);
            }
        }
            else {
            arabicNumberOne = numTransform(numberOne);
            arabicNumberTwo = numTransform(numberTwo);
        }
        int operationAnswer = mathematicOperations(arabicNumberOne, arabicNumberTwo, operationSymbol);
        if ( numComparationStatus == true) System.out.println("Output:\n" + operationAnswer);
        else arabic2rome(operationAnswer);

    }

    static int mathematicOperations(int num1, int num2, String oper) {
        int answer = 0;
        switch (oper) {
            case "+":
                answer = num1 + num2;
                break;
            case "-":
                answer = num1 - num2;
                break;
            case "*":
                answer = num1 * num2;
                break;
            case "/":
                if (num2 <= 0) errorMessage();
                else answer = num1 / num2;
                break;
        }
        return answer;
    }

    static Boolean numComparation(String number1, String number2) {
        boolean status = false;
        Boolean numberType1 = numArabicCheckout(number1);
        Boolean numberType2 = numArabicCheckout(number2);
        if (numberType1 != numberType2) errorMessage();
        else if (numberType1 == true & numberType2 == true)
             status = true;
        else {
             status = false;
        }
        return status;
    }

    static Boolean numArabicCheckout(String number) {
        try {
            Short d = Short.parseShort(number);
        }
        catch(NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    static int numTransform(String number) {
        String[] romeNumbers = {"X","IX","VIII","VII","VI","V","IV","III","II","I"};
        int[] arabicNumbers = new int[10];
        int n = 11;
        for (int i = 0, j = n - 1; i < 10; i++, j--) {
            arabicNumbers[i] = j;
        }
        for (int i = 0; i < romeNumbers.length; i++) {
            if (number.equals(romeNumbers[i]))
                return (arabicNumbers[i]);
        }
        errorMessage();
        return (0);
    }

    static int indexOperPlaceProc(String iOPString){
        String[] arrOperations = {"+","-","*","/"};
        for (int i = 0; i < arrOperations.length; i++) {
            int ixOperPlace = iOPString.indexOf(arrOperations[i]);
            if (ixOperPlace != -1) {
                String subiOPString = iOPString.substring(ixOperPlace+1);
                for (int i2 = 0; i2 < arrOperations.length; i2++) {
                    int ixOperPlace2 = subiOPString.indexOf(arrOperations[i2]);
                    if (ixOperPlace2 != -1) {
                     errorMessage();
                    }
                }
                return ixOperPlace;
            }
           }
        errorMessage();
        return (0);
    }

    static void errorMessage() {
        System.out.println("Некорректная операция. До свидания.");
        System.exit(1);
    }

    //Честно скомунизденный конвертер.
    static void arabic2rome(int Int) {
        LinkedHashMap<String, Integer> roman_numerals = new LinkedHashMap<String, Integer>();
        roman_numerals.put("M", 1000);
        roman_numerals.put("CM", 900);
        roman_numerals.put("D", 500);
        roman_numerals.put("CD", 400);
        roman_numerals.put("C", 100);
        roman_numerals.put("XC", 90);
        roman_numerals.put("L", 50);
        roman_numerals.put("XL", 40);
        roman_numerals.put("X", 10);
        roman_numerals.put("IX", 9);
        roman_numerals.put("V", 5);
        roman_numerals.put("IV", 4);
        roman_numerals.put("I", 1);
        String res = "";
        for(Map.Entry<String, Integer> entry : roman_numerals.entrySet()){
            int matches = Int/entry.getValue();
            res += arabicToRomeRepeat(entry.getKey(), matches);
            Int = Int % entry.getValue();
        }
        System.out.println("Output:\n" + res);
    }

    static String arabicToRomeRepeat(String s, int n) {
        if(s == null) {
            return null;
        }
        final StringBuilder sb = new StringBuilder();
        for(int i = 0; i < n; i++) {
            sb.append(s);
        }
        return sb.toString();
    }

}