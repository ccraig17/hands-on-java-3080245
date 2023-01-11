package bank;

import java.util.Scanner;

import javax.security.auth.login.LoginException;

public class Menu {

  private Scanner scanner;

  public static void main(String[] args) {
    System.out.println("Welcome to Globe Bank International!");

    Menu menu = new Menu();
    menu.scanner = new Scanner(System.in);
    Customer customer = menu.authenicateUser(); // uses the authenicated Method to verfiy customer
    if (customer != null) { // if the customer is not equal to null, we can get use the Datasource
                            // Class/method getAccounts() allowing customer to access the Menu options.
      Account account = Datasource.getAccount(customer.getAccount_id());
      menu.showMenu(customer, account);
    }

    menu.scanner.close();
  }

  // create a method to authenicate the User so they have access to the Menu
  private Customer authenicateUser() {
    System.out.println("Please enter your username");
    String username = scanner.next();
    System.out.println("Please enter you password");
    String userpassword = scanner.next();

    Customer customer = null;
    try {
      customer = Authenticator.login(username, userpassword);
    } catch (LoginException e) {
      e.printStackTrace();
      System.out.println("There was an Error" + e.getMessage());
    }
    return customer;
  }

  private void showMenu(Customer customer, Account account) {
    int selection = 0;

    while (selection != 4 && customer.isAuthenicated()) {
      System.out.println("============================================");
      System.out.println("Please select one of the following options: "
          + "\n 1: Deposit "
          + "\n 2: Withdraw"
          + "\n 3: Check Balance"
          + "\n 4: Exit");
      System.out.println("============================================");
    }

  }

}
