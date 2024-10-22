package Lab_1;

import java.util.*;
class BankValidation {
    // Data member
    long cardNumber;

    // Parameterized constructor
    public BankValidation(long cardNumber) {
        this.cardNumber = cardNumber;
        System.out.println("So Your Card Number is: " + this.cardNumber);
        int count = countCardNumber(this.cardNumber);  // Count using long
       // System.out.println("The count of the digit is " + count);
        boolean validationB = validation(count); // Validate using int count

        if (validationB) {
           // System.out.println("The card is valid.");
            if (calculationStuff(this.cardNumber, count)) {
                System.out.println("--Card is Valid.--");
            } else {
                System.out.println("---CARD IS INVALID---");
            }
        } else {
            System.out.println("The card is invalid, Because count is extend!!!");
        }
    }

    // Method to count the number of digits in a long card number
    public int countCardNumber(long cardNumber) {
        int count = 0;
        while (cardNumber > 0) {
            cardNumber = cardNumber / 10;
            count++;
        }
        return count;
    }

    // Overloaded method to count digits using a String
    public int countCardNumber(String cardNumberStr) {
        return cardNumberStr.length(); // Simply return the length of the string
    }

    // Overloaded method to count digits using an integer
    public int countCardNumber(int cardNumberInt) {
        return String.valueOf(cardNumberInt).length(); // Count digits by converting to String
    }

    // Card Validation based on int count
    public boolean validation(int count) {
        return count >= 8 && count <= 9;
    }

    // Overloaded validation method to accept a String
    public boolean validation(String cardNumberStr) {
        int count = cardNumberStr.length();
        return count >= 8 && count <= 9;
    }

    // Summation function
    private long summation(long number) {
        long sum = 0;
        while (number > 0) {
            sum += number % 10;
            number /= 10;
        }
        return sum;
    }

    // Reverse the digit function
    private long reverseDigit(long remDigit) {
        long rev = 0;
        while (remDigit > 0) {
            rev = rev * 10 + remDigit % 10;
            remDigit = remDigit / 10;
        }
        return rev;
    }

    // Double its even number places
    private long digitDouble(long numRev, int count) {
        long resultDouble = 0; // Initialize the result
        long multiplier = 1; // To keep track of the place value

        while (numRev > 0) {
            long digit = numRev % 10; // Extract the last digit

            if (count % 2 != 0) {
                // Double the digit
                digit *= 2;

                // If doubled digit > 9, do the summation
                if (digit > 9) {
                    digit = summation(digit);
                }
            }

            // Add the (possibly modified) digit to the result
            resultDouble += digit * multiplier;

            multiplier *= 10;

            // Remove the last digit from num
            numRev /= 10;

            // Decrement count
            count--;
        }

        return resultDouble;
    }

    private boolean calculationStuff(long cardNumber, int count) {
        // Step a: Remove the last digit of the `ccNumber`.
        int lastDigit = (int) (cardNumber % 10);
        long remDigit = cardNumber / 10;
        // Step b: Reverse the remaining digits.
        long numRev = reverseDigit(remDigit);
        // Step c: Double at odd number, followed by sum if digit > 9
        long doubleNum = digitDouble(numRev, count - 1);
        // Step d: Add up all the digits.
        int finalSum = (int) summation(doubleNum);
        // Step e: Subtract the last digit obtained in step a from 10.
        int subtractAns = 10 - (finalSum % 10);
        // Step f: Compare the result of step e with the last digit obtained in step a.
        // If they match, the card number is valid; otherwise, it is invalid.
        return subtractAns == lastDigit;
    }
}

public class Lab_1 {
    public static void main(String[] args) {
        // Using the long constructor
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Your Credit Card Number: ");
        long cardNumberStr = sc.nextLong();
        BankValidation obj1 = new BankValidation(cardNumberStr);
       // System.out.println("Count from long: " + obj1.countCardNumber(cardNumberStr));
       // System.out.println("Count from String: " + obj1.countCardNumber("12345678"));
    }
}
