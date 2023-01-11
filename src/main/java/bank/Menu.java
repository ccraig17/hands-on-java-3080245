package bank;

import java.util.Scanner;

import javax.security.auth.login.LoginException;

public class Menu {

  private Scanner scanner;
  public static void main(String[] args) {
    System.out.println("Welcome to Globe Bank International!");

    Menu menu = new Menu();
    menu.scanner = new Scanner(System.in);






    menu.scanner.close();
  }
  //create a method to authenicate the User so they have access to the Menu
  private Customer authenicateUser(){
    System.out.println("Please enter your username");
    String username = scanner.next();

    System.out.println("Please enter you password");
    String userpassword = scanner.next();

    Customer customer = null;
    try{
      customer = Authenticator.login(username, userpassword);
    }catch(LoginException e){
      e.printStackTrace();
      System.out.println("There was an Error" + e.getMessage());
    }
    
    return customer;

  }
  
}
