# Metro Ticket Booking Application

## Introduction

The Metro Ticket Booking Application is a Java program that allows users to book travel tickets and make payments using either cash or a travel card. This program is designed to manage the details of a user's travel, calculate fares, apply discounts, and even offer cashback for return trips on card payment mode.

## Classes

### 1. Travel

The `Travel` class is responsible for managing the travel details, including source and destination stations, in-time, out-time (if applicable), and return status. It also calculates the distance traveled and initiates payments.

#### Methods

- `setTravelDetails()`: Takes user input for source, destination, and in-time. It also initializes the payment details and checks for insufficient balance if a travel card is used.
- `displayTravelDetails()`: Displays the travel details, including source, destination, in-time, out-time (if available), return status, and payment details.
- `calculateTravelDistance()`: Calculates the distance traveled based on source and destination stations.
- `calculateTravelFare()`: Calculates the travel fare based on the distance traveled.
- `initiatePayment()`: Initiates the payment process, deducting the fare, and calculating cashback (if applicable).
- `returnTravel()`: Asks the user if they want to avail a return ticket, sets the return status, and records out-time (if applicable).
- `timeDifference()`: Calculates the time difference between in-time and out-time (if applicable).
- `isValidTimeFormat()`: Validates the input time format (HH:MM).

### 2. Payment

The `Payment` class handles payment-related functionalities, including selecting the mode of payment (cash or travel card), checking card balance, calculating final amounts, applying discounts, and providing cashback.

#### Methods

- `setPaymentDetails()`: Takes user input for the mode of payment (cash or travel card) and, if applicable, the card balance.
- `displayPaymentDetails()`: Displays the selected mode of payment.
- `calculateFinalAmount()`: Calculates the final payment amount, applying discounts for travel card payments and checking for insufficient balance.
- `calculateCashback()`: Calculates cashback for travel card payments on return trips.
- `calculateDiscount()`: Applies a discount to the travel fare for travel card payments.
- `printCashReceipt()`: Prints a receipt for cash payments.
- `printCardReceipt()`: Prints a receipt for travel card payments, including the remaining balance.
- `printCashBackReceipt()`: Prints a cashback receipt for travel card payments.

### 3. MetroTicketBookingApp Class

- Contains the `main` method for running the application.
- Creates a `Travel` object, allows passenger to travel and make payment.

## Usage

1. Go to the directory containing Java files:

   ```bash
   cd src
   ```

2. Compile the Java files:

   ```bash
   javac Travel.java Payment.java MetroTicketBookingApp.java
   ```

3. Run the application:
   ```bash
   java MetroTicketBookingApp
   ```

## Important Notes

- Ensure that you have the Java Development Kit (JDK) (atleast version 8 and above) installed on your system to compile and run the program.
