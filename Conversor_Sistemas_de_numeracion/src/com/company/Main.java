/*
Author:Santiago Hernandez Molina
Date:22/feb/2022
Description:This software convert numbers from different bases to others
 */

package com.company;

import java.util.*;


public class Main {

    public HashMap<Character, Integer> letterNumbers;

    public static void main(String[] args) {
        var main = new Main();
        main.fillLetterNumbers();
        Scanner keyboard = new Scanner(System.in);
        int base;
        String number;
        int baseToConvert;
        do {
            System.out.println("--------------Welcome to SoftConverter----------");
            System.out.println("------------------------------------------------");
            System.out.println("please input the base of your number(between 1-25)");
            base = keyboard.nextInt();
            System.out.println("please input the number()");
            number = keyboard.next();
            System.out.println("please input the base you want to convert");
            baseToConvert = keyboard.nextInt();
        } while (base > 25);
        double decimalNumber = main.convertToDecimal(base, number);
        Stack<String> resultNumber = main.convertDecimalToOtherBase(decimalNumber, baseToConvert);
        System.out.println("your number is: ");
        while (!resultNumber.isEmpty()) {
            System.out.print(resultNumber.pop());
        }


    }

    public void fillLetterNumbers() {
        letterNumbers = new HashMap<>();
        int number = 10;
        for (char i = 'A'; i <= 'O'; i++) {
            letterNumbers.put(i, number);
            number++;
        }
        System.out.println(letterNumbers);
    }

    public double convertToDecimal(int base, String number) {
        try {
            List<String> numberList = convertCharactersToNumbers(number);
            double numberInt = 0;
            int power = 0;
            for (int i = number.length() - 1; i >= 0; i--) {
                String digit = numberList.get(i);
                numberInt += Integer.parseInt(digit) * Math.pow(base, power);
                power++;
            }
            return numberInt;
        } catch (NumberFormatException e) {
            System.err.println("The number contains a character not recognized by the system");
            return 0;
        }
    }

    public List<String> convertCharactersToNumbers(String number) {
        List<String> newNumber = new ArrayList<>();
        for (int i = 0; i < number.length(); i++) {
            char specificCharacter = Character.toUpperCase(number.charAt(i));
            if (letterNumbers.containsKey(specificCharacter)) {
                newNumber.add(String.valueOf(letterNumbers.get(specificCharacter)));
            } else {
                newNumber.add(String.valueOf(specificCharacter));
            }
        }
        return newNumber;
    }

    public Stack<String> convertDecimalToOtherBase(double number, int base) {
        int division = (int) number;
        Stack<String> resultNumber = new Stack<>();
        do {
            int rest = division % base;
            division = division / base;
            System.out.println(rest + division);
            if (rest > 9) {
                resultNumber.push(String.valueOf(getSingleKeyFromValue(letterNumbers, rest)));
            } else {
                resultNumber.push(String.valueOf(rest));
            }
        } while (division >= base);
        if (division > 9) {
            resultNumber.push(String.valueOf(getSingleKeyFromValue(letterNumbers, division)));
        } else {
            resultNumber.push(String.valueOf(division));
        }
        return resultNumber;
    }

    public static <K, V> K getSingleKeyFromValue(Map<K, V> map, V value) {
        for (Map.Entry<K, V> entry : map.entrySet()) {
            if (Objects.equals(value, entry.getValue())) {
                return entry.getKey();
            }
        }
        return null;
    }
}
