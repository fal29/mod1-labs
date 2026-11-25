package lab1b2;

import java.util.Scanner;

public class Airport {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean keepGoing = true;

        while (keepGoing) {
            System.out.print("Enter amount in GBP: ");
            double gbp = scanner.nextDouble();

            System.out.print("Choose currency (1 = USD, 2 = EUR, 3 = JPY): ");
            int currencyChoice = scanner.nextInt();

            System.out.print("Enter customer type (R = Regular, V = VIP, S = Staff): ");
            String customerType = scanner.next().toUpperCase();

            // 1. Service fee: < 100 -> 5% fee, >= 100 -> 2.5% fee
            double afterFee;
            if (gbp < 100) {
                afterFee = gbp * 0.95;
            } else {
                afterFee = gbp * 0.975;
            }

            // 2. Airport tax: R -> 10%, V -> 5%, S -> 0%
            double taxRate;
            if (customerType.equals("R")) {
                taxRate = 0.10;
            } else if (customerType.equals("V")) {
                taxRate = 0.05;
            } else if (customerType.equals("S")) {
                taxRate = 0.0;
            } else {
                System.out.println("Unknown customer type. Defaulting to Regular (10% tax).");
                taxRate = 0.10;
            }

            double afterTax = afterFee * (1.0 - taxRate);

            // 3. Convert currency with switch
            double rate = 0.0;
            String currencyCode = "";

            switch (currencyChoice) {
                case 1:
                    rate = 1.25;
                    currencyCode = "USD";
                    break;
                case 2:
                    rate = 1.15;
                    currencyCode = "EUR";
                    break;
                case 3:
                    rate = 180.50;
                    currencyCode = "JPY";
                    break;
                default:
                    System.out.println("Invalid currency choice. Defaulting to USD.");
                    rate = 1.25;
                    currencyCode = "USD";
                    break;
            }

            double foreignExact = afterTax * rate;
            int wholeUnits = (int) foreignExact;

            System.out.printf("Amount after service fee: £%.2f%n", afterFee);
            System.out.printf("Amount after tax: £%.2f%n", afterTax);
            System.out.printf("Final amount (exact): %.2f %s%n", foreignExact, currencyCode);
            System.out.println("Final amount (whole units): " + wholeUnits + " " + currencyCode);

            System.out.print("Perform another exchange? (y/n): ");
            String again = scanner.next().toLowerCase();
            keepGoing = again.equals("y");
        }

        scanner.close();
        System.out.println("Goodbye.");
    }
}
