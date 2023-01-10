package bank;

import javax.security.auth.login.LoginException;

public class Authenticator {
  public static Customer login(String username, String password) throws LoginException{
    Customer customer = Datasource.getCustomer(username);
    if(customer == null){
      //if the customer is null; we throw login exception to stop the process
      throw new LoginException("The username NOT FOUND!");
    }
    if(password.equals(customer.getPassword())){
     customer.setAuthenicated(true);
      return customer;
    }
    else{
      throw new LoginException("Incorrect password");
    }
          
  }

  public static void logout(Customer customer){
    customer.setAuthenicated(false);
  }
  
}
