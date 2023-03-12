package com.example.einzelbeispiel.calculation;


public class CalculateCheckSum {

    public CalculateCheckSum(){
    }

    public String alternatingChecksums(int n) {
        int sumEven = 0, sumOdd = 0;
        int i = 1;
        while (n != 0) {
            int digit = n % 10;
            if (i % 2 == 0) {
                sumEven += digit;
            } else {
                sumOdd += digit;
            }
            n /= 10;
            i++;
        }
        return " Even checksum: " + sumEven + " Odd checksum: " + sumOdd;


    }




}
