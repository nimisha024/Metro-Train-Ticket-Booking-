import java.util.Scanner;

class Travel {
    private String source;
    private String destination;
    private String inTime;
    private String outTime;
    private Boolean returnStatus;
    private int travelledDistance;
    private int differenceInMinute;
    private Payment payment;

    void setTravelDetails() {
        Scanner sc = new Scanner(System.in);

        try {
            System.out.println("Enter Source Station: (A-Y)");
            this.source = sc.nextLine();
            if (this.source.length() != 1 || !Character.isLetter(this.source.charAt(0)) || this.source.charAt(0) < 'A'
                    || this.source.charAt(0) > 'Y') {
                throw new IllegalArgumentException("Invalid source station input.");
            }

            System.out.println("Enter Destination Station: (A-Y)");
            this.destination = sc.nextLine();
            if (this.destination.length() != 1 || !Character.isLetter(this.destination.charAt(0))
                    || this.destination.charAt(0) < 'A' || this.destination.charAt(0) > 'Y') {
                throw new IllegalArgumentException("Invalid destination station input.");
            }

            System.out.println("Enter In-Time: (HH:MM)");
            this.inTime = sc.nextLine();
            if (this.inTime.length() != 5 || !isValidTimeFormat(this.inTime)) {
                throw new IllegalArgumentException("Invalid in-time format (HH:MM).");
            }

            this.payment = new Payment();
            this.payment.setPaymentDetails();
            calculateTravelDistance();

            if (this.payment.modeOfPayment.equals("Travel Card")
                    && this.payment.cardBalance < Payment.MINIMUM_CARD_BALANCE) {
                System.out.println("Insufficient Balance");
            } else {
                initiatePayment();
            }
        } catch (Exception e) {
            System.out.println("Invalid input: " + e.getMessage());
            System.exit(1);
        }
    }

    void displayTravelDetails() {
        System.out.println("-------------------------------------------------------------");
        System.out.println(" TRAVEL DETAILS ");
        System.out.println("Source: " + this.source);
        System.out.println("Destination: " + this.destination);
        System.out.println("In-Time: " + this.inTime);

        if (this.returnStatus) {
            System.out.println("Return Status: Yes ");
            System.out.println("Out-Time: " + this.outTime);
        } else {
            System.out.println("Return Status: No ");
        }

        this.payment.displayPaymentDetails();
    }

    private void calculateTravelDistance() {
        this.travelledDistance = Math.abs(this.destination.charAt(0) - this.source.charAt(0)) + 1;
    }

    private double calculateTravelFare() {
        double totalFare;
        if (this.travelledDistance < 3) {
            totalFare = 10;
        } else {
            totalFare = 10 + (this.travelledDistance - 3) * 10;
        }
        return Math.min(totalFare, 50);
    }

    private void initiatePayment() {
        double travelFare = calculateTravelFare();
        this.payment.calculateFinalAmount(travelFare);
        returnTravel();
        if (returnStatus) {
            this.payment.calculateFinalAmount(travelFare);
            if (this.payment.modeOfPayment.equals("Travel Card")) {
                this.payment.calculateCashback(this.differenceInMinute, travelFare);
            }
        }
    }

    private void returnTravel() {
        Scanner sc = new Scanner(System.in);

        try {
            System.out.println("Do you want to avail a return ticket: Yes/No");
            String returnTemp = sc.nextLine();
            if (!returnTemp.equalsIgnoreCase("Yes") && !returnTemp.equalsIgnoreCase("No")) {
                throw new IllegalArgumentException("Invalid input for return ticket (Yes/No).");
            }

            this.returnStatus = returnTemp.equalsIgnoreCase("Yes");

            if (this.returnStatus) {
                System.out.println("Enter Out-Time: (HH:MM)");
                this.outTime = sc.nextLine();
                if (!isValidTimeFormat(this.outTime)) {
                    throw new IllegalArgumentException("Invalid out-time format (HH:MM).");
                }
                timeDifference(this.inTime, this.outTime);
            }
        } catch (Exception e) {
            System.out.println("Invalid input: " + e.getMessage());
            System.exit(1);
        }
    }

    private void timeDifference(String inTime, String outTime) {

        int startTime = Integer.parseInt(this.inTime.substring(0, 2)) * 60
                + Integer.parseInt(this.inTime.substring(3, 5));
        int endTime = Integer.parseInt(this.outTime.substring(0, 2)) * 60
                + Integer.parseInt(this.outTime.substring(3, 5));

        this.differenceInMinute = Math.abs(startTime - endTime);
    }

    private boolean isValidTimeFormat(String time) {
        try {
            int hours = Integer.parseInt(time.substring(0, 2));
            int minutes = Integer.parseInt(time.substring(3, 5));
            return hours >= 0 && hours <= 23 && minutes >= 0 && minutes <= 59;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
