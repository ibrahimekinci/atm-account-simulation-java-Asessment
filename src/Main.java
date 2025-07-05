
import exception.*;
import domain.*;
import java.time.LocalDate;
import java.time.Period;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
/**
 *
 * @author pc
 */
/**
 * Main class to simulate ATM operations and test account behaviors.
 */
public class Main {

    /**
     * Handles exceptions and distinguishes between domain and system errors.
     */
    private static void handleException(Exception e) {
        if (e instanceof ICustomException customException) {
            System.out.printf("Domain Error: %s%n", customException.getMessage());
        } else {
            System.out.printf("Unexpected Error: %s%n", e.getMessage());
        }
    }

    public static void main(String[] args) {
        try {
            // === ACCOUNT INITIALIZATION ===
            System.out.println("=== ACCOUNT INITIALIZATION ===");
            IAccount savings = new SavingsAccount(500.00, 1000.00);
            IAccount cheque = new ChequeAccount(200.00);
            IAccount netSaver = new NetSaverAccount(1000.00, LocalDate.now().minusDays(31));
            IAccount fixed = new FixedAccount(1500.00, LocalDate.now().minusMonths(7), Period.ofMonths(6), false);

            System.out.printf("Savings Account: $%.2f (Daily Limit: $1000.00)%n", savings.getBalance());
            System.out.printf("Cheque Account: $%.2f%n", cheque.getBalance());
            System.out.printf("NetSaver Account: $%.2f (Opened 31 days ago)%n", netSaver.getBalance());
            System.out.printf("Fixed Account: $%.2f (Opened 7 months ago, Term: 6 months)%n%n", fixed.getBalance());

            // === DEPOSITS ===
            System.out.println("=== DEPOSIT TESTS ===");
            try {
                System.out.println("Depositing $5000 to Savings...");
                savings.deposit(5000);
                System.out.printf("New Savings Balance: $%.2f%n", savings.getBalance());

                System.out.println("Depositing $300 to Cheque...");
                cheque.deposit(300);
                System.out.printf("New Cheque Balance: $%.2f%n%n", cheque.getBalance());
            } catch (Exception e) {
                handleException(e);
            }

            // === VALID WITHDRAWALS ===
            System.out.println("=== VALID WITHDRAWALS ===");
            try {
                System.out.printf("Savings Balance Before Withdrawal: $%.2f%n", savings.getBalance());
                savings.withdraw(100);
                System.out.printf("Withdrew $100. New Savings Balance: $%.2f%n", savings.getBalance());

                System.out.printf("Cheque Balance Before Withdrawal: $%.2f%n", cheque.getBalance());
                cheque.withdraw(100);
                System.out.printf("Withdrew $100. New Cheque Balance: $%.2f%n%n", cheque.getBalance());
            } catch (Exception e) {
                handleException(e);
            }

            // === ADD INTEREST (VALID) ===
            System.out.println("=== INTEREST APPLICATION (VALID ACCOUNTS) ===");
            ((IInterestBearing) savings).addInterest();
            ((IInterestBearing) netSaver).addInterest();
            ((IInterestBearing) fixed).addInterest();
            System.out.printf("Savings with interest: $%.2f%n", savings.getBalance());
            System.out.printf("NetSaver with interest: $%.2f%n", netSaver.getBalance());
            System.out.printf("Fixed with interest: $%.2f%n%n", fixed.getBalance());

            // === REINIT FOR EARLY TEST ===
            System.out.println("=== REINITIALIZE FOR EARLY INTEREST TESTS ===");
            netSaver = new NetSaverAccount(1100.00, LocalDate.now().minusDays(7));
            fixed = new FixedAccount(300.00, LocalDate.now().minusMonths(4), Period.ofMonths(6), false);

            System.out.printf("NetSaver Reinit: $%.2f (Opened 7 days ago)%n", netSaver.getBalance());
            System.out.printf("Fixed Reinit: $%.2f (Opened 4 months ago, 6-month term)%n%n", fixed.getBalance());

            // === INTEREST DENIED DUE TO EARLY PERIOD ===
            System.out.println("=== EARLY INTEREST TESTS ===");
            ((IInterestBearing) netSaver).addInterest();
            ((IInterestBearing) fixed).addInterest();
            System.out.printf("NetSaver after denied interest (too early): $%.2f%n", netSaver.getBalance());
            System.out.printf("Fixed after denied interest (term not over): $%.2f%n%n", fixed.getBalance());

            // === INVALID DENOMINATION ===
            System.out.println("=== INVALID DENOMINATION (30 AUD) ===");
            try {
                cheque.withdraw(30);
            } catch (Exception e) {
                handleException(e);
            }
            System.out.printf("Cheque balance remains: $%.2f%n%n", cheque.getBalance());

            // === INSUFFICIENT FUNDS ===
            System.out.println("=== INSUFFICIENT FUNDS (5000 AUD) ===");
            try {
                cheque.withdraw(5000);
            } catch (Exception e) {
                handleException(e);
            }
            System.out.printf("Cheque balance remains: $%.2f%n%n", cheque.getBalance());

            // === NULL POINTER EXCEPTION TEST ===
            System.out.println("=== UNEXPECTED ERROR (NULL ACCOUNT) ===");
            try {
                IAccount broken = null;
                broken.deposit(100);
            } catch (Exception e) {
                handleException(e);
            }
            System.out.println();

            // === DAILY LIMIT TESTS: SAVINGS ===
            System.out.println("=== DAILY LIMIT TEST: SAVINGS ACCOUNT ===");
            System.out.printf("Current Balance: $%.2f | Daily Limit: $1000.00%n", savings.getBalance());

            System.out.println("Attempting to withdraw $1200 (above daily limit)...");
            try {
                savings.withdraw(1200);
            } catch (Exception e) {
                handleException(e);
            }

            System.out.println("Attempting 3 withdrawals: $400 + $300 + $300...");
            try {
                savings.withdraw(400);
                System.out.printf("After $400 -> Balance: $%.2f%n", savings.getBalance());
                savings.withdraw(300);
                System.out.printf("After $300 -> Balance: $%.2f%n", savings.getBalance());
                savings.withdraw(300); // should fail
            } catch (Exception e) {
                handleException(e);
            }
            System.out.printf("Final Savings Balance: $%.2f%n%n", savings.getBalance());

            // === DAILY LIMIT TESTS: NETSAVER ===
            System.out.println("=== DAILY LIMIT TEST: NETSAVER ACCOUNT ===");
            System.out.printf("Current Balance: $%.2f | Daily Limit: $1000.00%n", netSaver.getBalance());

            System.out.println("Attempting to withdraw $1200 (above limit)...");
            try {
                netSaver.withdraw(1200);
            } catch (Exception e) {
                handleException(e);
            }

            System.out.println("Attempting 3 withdrawals: $400 + $300 + $300...");
            try {
                netSaver.withdraw(400);
                System.out.printf("After $400 -> Balance: $%.2f%n", netSaver.getBalance());
                netSaver.withdraw(300);
                System.out.printf("After $300 -> Balance: $%.2f%n", netSaver.getBalance());
                System.out.printf("just trying for $350 (should fail) -> Balance: $%.2f%n", netSaver.getBalance());
                netSaver.withdraw(350); // should fail

            } catch (Exception e) {
                handleException(e);
            }
            System.out.printf("Final NetSaver Balance: $%.2f%n", netSaver.getBalance());

        } catch (Exception e) {
            handleException(e);
        }
    }
}
