import java.util.Scanner;

class Payment {
    String modeOfPayment;
    double cardBalance;
    static final int MINIMUM_CARD_BALANCE = 50;

    private static final double DISCOUNT_RATE = 0.20;
    private static final double CASHBACK_RATE = 0.05;

    void setPaymentDetails() {
        Scanner sc = new Scanner(System.in);

        try {
            System.out.println("Enter Mode Of Payment: Travel Card/Cash");
            this.modeOfPayment = sc.nextLine();

            if (!this.modeOfPayment.equals("Travel Card") && !this.modeOfPayment.equals("Cash")) {
                throw new IllegalArgumentException("Invalid Mode of Payment");
            }

            if (this.modeOfPayment.equals("Travel Card")) {
                System.out.println("What is your travel card balance?");
                this.cardBalance = sc.nextDouble();
                if (this.cardBalance < MINIMUM_CARD_BALANCE) {
                    throw new IllegalArgumentException("Card balance is less than the minimum required amount.");
                }
            }
        } catch (Exception e) {
            System.out.println("Invalid input: " + e.getMessage());
            System.exit(1); // Exit the program on invalid input
        }
    }

    void displayPaymentDetails() {
        System.out.println("Mode of Payment : " + this.modeOfPayment);
    }

    void calculateFinalAmount(double travelFare) {
        if (this.modeOfPayment.equals("Cash")) {
            printCashReceipt(travelFare);
        } else if (this.modeOfPayment.equals("Travel Card")) {
            double afterDiscount = calculateDiscount(travelFare);
            if (this.cardBalance < afterDiscount) {
                System.out.println("Insufficient Balance");
            } else {
                this.cardBalance -= afterDiscount;
                printCardReceipt(afterDiscount);
            }
        } else {
            System.out.println("Invalid Mode of Payment");
        }
    }

    void calculateCashback(int timeDifference, double travelFare) {
        if (timeDifference < 360) {
            double cashback = ((travelFare * 2) * CASHBACK_RATE);
            this.cardBalance += cashback;
            printCashBackReceipt(cashback);
        }
    }

    private double calculateDiscount(double travelFare) {
        double afterDiscount = travelFare - (travelFare * (DISCOUNT_RATE));
        return afterDiscount;
    }

    private void printCashReceipt(double amount) {
        System.out.println("Amount Deducted : " + amount);
    }

    private void printCardReceipt(double amount) {
        System.out.println("Amount Deducted : " + amount);
        System.out.println("Remaining Balance : " + this.cardBalance);
    }

    private void printCashBackReceipt(double amount) {
        System.out.println("To-Fro Travel : ");
        System.out.println("5% Cashback : " + amount);
        System.out.println("New Balance : " + this.cardBalance);
    }
}
