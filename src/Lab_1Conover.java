class BankValidate{
    // Data member
    long cardNumber;

    // Parameterized constructor
    public BankValidate(long cardNumber) {
        this.cardNumber = cardNumber;
        System.out.println("Card Number: " + this.cardNumber);
        int count = countCardNumber(this.cardNumber);
        System.out.println("The count of the digit is " + count);
        boolean validationB = validation(count);

        if (validationB) {
            System.out.println("The card is valid.");
            if (calculationStuff(this.cardNumber, count)) {
                System.out.println("Card is Valid.");
            } else {
                System.out.println("---CARD IS INVALID---");
            }
        } else {
            System.out.println("The card is invalid, Bro!!!");
        }
    }

    // Overloaded constructor that accepts String type for card number
    public BankValidate(String cardNumberStr) {
        this.cardNumber = Long.parseLong(cardNumberStr); // Convert to long
        System.out.println("Card Number: " + this.cardNumber);
        int count = countCardNumber(this.cardNumber);
        System.out.println("The count of the digit is " + count);
        boolean validationB = validation(count);

        if (validationB) {
            System.out.println("The card is valid.");
            if (calculationStuff(this.cardNumber, count)) {
                System.out.println("Card is Valid.");
            } else {
                System.out.println("---CARD IS INVALID---");
            }
        } else {
            System.out.println("The card is invalid, Bro!!!");
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

    // Card Validation
    public boolean validation(int count) {
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

 /*  private boolean calculationStuff(long cardNumber, int count) {
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
    }*/
 private boolean calculationStuff(long cardNumber, int count) {
     int lastDigit = (int) (cardNumber % 10);
     long remDigit = cardNumber / 10;


     long numRev = 0;
     long doubleNum = 0;
     int finalSum = 0;
     int subtractAns = 0;

     // Perform operations based on step
     for (int step = 1; step <= 6; step++) {
         switch (step) {
             case 1: // Step a
                 // Already handled: lastDigit and remDigit
                 break;

             case 2: // Step b: Reverse the remaining digits.
                 numRev = reverseDigit(remDigit);
                 break;

             case 3: // Step c: Double at odd number, followed by sum if digit > 9
                 doubleNum = digitDouble(numRev, count - 1);
                 break;

             case 4: // Step d: Add up all the digits.
                 finalSum = (int) summation(doubleNum);
                 break;

             case 5: // Step e: Subtract the last digit obtained in step a from 10.
                 subtractAns = 10 - (finalSum % 10);
                 break;

             case 6: // Step f: Compare the result of step e with the last digit obtained in step a.
                 return subtractAns == lastDigit;
         }
     }

     return false; // Fallback return if not valid
 }

}

public class Lab_1Conover {
    public static void main(String[] args) {
        // Using the long constructor
        BankValidate obj1 = new BankValidate(121320052L);
        System.out.println("Count from long: " + obj1.countCardNumber(121320052L));

        // Using the String constructor
        BankValidate obj2 = new BankValidate("132145679");
        System.out.println("Count from String: " + obj2.countCardNumber("121320052"));
    }
}