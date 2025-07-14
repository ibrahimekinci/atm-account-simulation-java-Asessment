package infrastructure;

import domain.*;
import exceptions.*;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;

public class MockBankingService {

    private static List<Customer> users;

    public MockBankingService() throws CustomException {

        if (users != null && !users.isEmpty()) {
            return;
        }

        users = new ArrayList<>();
        // Create fake accounts
        List<IAccount> johnsAccounts = new ArrayList<>();
        johnsAccounts.add(new SavingsAccount("1001", "John's Savings", 2000.0, 1000.0, 1000.0));
        johnsAccounts.add(new ChequeAccount("1002", "John's Cheque", 500.0));
        johnsAccounts.add(new NetSaverAccount("1003", "Net Saver", 1500.0, LocalDate.now().minusDays(40)));
        johnsAccounts.add(new FixedAccount("1004", "Term Deposit", 2000.0, LocalDate.now().minusMonths(3), Period.ofMonths(6), false));

        Customer user = new Customer("C001", "U001", "testuser", "test123", "John Doe", johnsAccounts);
        users.add(user);
    }

    public Customer authenticate(String username, String password) {
        for (Customer user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

    public String getDefaultUsername() {
        return "testuser";
    }

    public String getDefaultPassword() {
        return "test123";
    }
}
