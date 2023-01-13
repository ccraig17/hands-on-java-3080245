package bank;

import java.util.Scanner;

import javax.security.auth.login.LoginException;

import bank.exceptions.AmountException;

public class Menu {

  private Scanner scanner;

  public static void main(String[] args) {
    System.out.println("Welcome to Globe Bank International!");

    Menu menu = new Menu(); // a menu object is initialize in order to create a Scanner Obj for the Menu
                            // Class
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
    int selection = 0; // keeps track of user selection; initially set to zero.

    // this loop run once the selection is not 4(to exit) and the
    // user(customer)isAuthenicated
    while (selection != 4 && customer.isAuthenicated()) {
      System.out.println("============================================");
      System.out.println("Please select one of the following options: "
          + "\n 1: Deposit"
          + "\n 2: Withdraw"
          + "\n 3: Check Balance"
          + "\n 4: Exit");
      System.out.println("============================================");

      selection = scanner.nextInt();
      double amount;

      switch (selection) {
        case 1:
          System.out.println("How much would you like to deposit?");
          amount = scanner.nextDouble();
          try {
            account.deposit(amount);
            // method to update the database
          } catch (AmountException e) {
            System.out.println(e.getMessage());
            System.out.println("Please try again");
          }
          break;

        case 2:
          System.out.println("How much would you like to withdraw?");
          amount = scanner.nextDouble();
          try {
            account.withdraw(amount);
            // method to update the database
          } catch (AmountException e) {
            System.out.println(e.getMessage());
            System.out.println("Please try again.");
          }
          break;

        case 3:
          System.out.println("Current balance is: " + account.getBalance());
          break;

        case 4:
          Authenticator.logout(customer);
          System.out.println("Thanks for banking at Globe Bank International");
          break;

        default:
          System.out.println("Invalid option. Please try again");
          break;
      }
    }
  }

}
