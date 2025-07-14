
import exceptions.*;
import domain.*;
import java.time.LocalDate;
import java.time.Period;
import javax.swing.SwingUtilities;
import ui.LoginFrame;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
/**
 *
 * @author pc
 */
public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginFrame().setVisible(true));
    }

    private static void handleException(Exception e) {
        if (e instanceof ICustomException customException) {
            System.out.printf("❌ Domain Error: %s%n", customException.getMessage());
        } else {
            System.out.printf("❌ Unexpected Error: %s%n", e.getMessage());
        }
    }

    private static void printAccountDetails(IAccount account) {
        System.out.printf("-> Account Title: %s | ID: %s | Type: %s | Balance: $%.2f%n",
                account.getAccountTitle(),
                account.getAccountId(),
                account.getAccountTypeDisplayName(),
                account.getBalance());
    }

    private static void tests() {
        try {
            // === Account Setup ===
            IAccount savings = new SavingsAccount("SAV001", "MySavings", 2000.00, 500.00, 1000.00);
            IAccount cheque = new ChequeAccount("CHQ001", "DailyCheque", 200.00);
            IAccount netSaver = new NetSaverAccount("NET001", "GrowthNetSaver", 1000.00, LocalDate.now().minusDays(31));
            IAccount fixed = new FixedAccount("FIX001", "LockedFixed", 1500.00, LocalDate.now().minusMonths(7), Period.ofMonths(6), false);

            System.out.println("\n=== 🧾 Initial Account Details ===");
            printAccountDetails(savings);
            printAccountDetails(cheque);
            printAccountDetails(netSaver);
            printAccountDetails(fixed);
            System.out.println();

            // === Deposit Test ===
            System.out.println("=== 💰 Deposit Test ===");
            try {
                savings.deposit(5000);
                System.out.println("✅ Deposited $5000 into Savings.");
                cheque.deposit(300);
                System.out.println("✅ Deposited $300 into Cheque.");
            } catch (Exception e) {
                handleException(e);
            }
            printAccountDetails(savings);
            printAccountDetails(cheque);
            System.out.println();

            // === Valid Withdrawals ===
            System.out.println("=== 💳 Valid Withdrawals ===");
            try {
                savings.withdraw(100);
                System.out.println("✅ Withdrew $100 from Savings.");
                cheque.withdraw(100);
                System.out.println("✅ Withdrew $100 from Cheque.");
            } catch (Exception e) {
                handleException(e);
            }
            printAccountDetails(savings);
            printAccountDetails(cheque);
            System.out.println();

            // === Interest Calculation ===
            System.out.println("=== 📈 Interest Calculation ===");
            ((IInterestBearing) savings).addInterest();
            ((IInterestBearing) netSaver).addInterest();
            ((IInterestBearing) fixed).addInterest();
            printAccountDetails(savings);
            printAccountDetails(netSaver);
            printAccountDetails(fixed);
            System.out.println();

            // === NetSaver Early Interest Attempt (Invalid) ===
            System.out.println("=== ⛔ NetSaver Early Interest Attempt ===");
            netSaver = new NetSaverAccount("NET002", "TooNewNetSaver", 1000.00, LocalDate.now().minusDays(7));
            try {
                ((IInterestBearing) netSaver).addInterest();
            } catch (Exception e) {
                handleException(e);
            }
            printAccountDetails(netSaver);
            System.out.println();

            // === Invalid Denomination Test ===
            System.out.println("=== ⚠️ Invalid Denomination (Withdraw 30) ===");
            try {
                cheque.withdraw(30);
            } catch (Exception e) {
                handleException(e);
            }
            printAccountDetails(cheque);
            System.out.println();

            // === Insufficient Funds Test ===
            System.out.println("=== 🚫 Insufficient Funds (Withdraw 5000) ===");
            try {
                cheque.withdraw(5000);
            } catch (Exception e) {
                handleException(e);
            }
            printAccountDetails(cheque);
            System.out.println();

            // === Daily Limit Tests ===
            System.out.println("=== ⛔ Daily Limit Test on Savings (Withdraw 1200) ===");
            try {
                savings.withdraw(1200);
            } catch (Exception e) {
                handleException(e);
            }

            System.out.println("=== ✅ Partial Withdrawals to Exhaust Daily Limit (Savings) ===");
            try {
                savings.withdraw(400);
                savings.withdraw(300);
                savings.withdraw(300); // might exceed remaining limit
            } catch (Exception e) {
                handleException(e);
            }
            printAccountDetails(savings);
            System.out.println();

            // === Unexpected Null Exception Simulation ===
            System.out.println("=== 💥 Unexpected Error Test ===");
            try {
                IAccount broken = null;
                broken.deposit(100);
            } catch (Exception e) {
                handleException(e);
            }
            System.out.println();

        } catch (Exception e) {
            handleException(e);
        }
    }
}
