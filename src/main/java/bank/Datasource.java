package bank;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



public class Datasource {
  public static Connection connect(){
    String db_file = "jdbc:sqlite:resources/bank.db"; 
    Connection connection = null;
    try{
        connection = DriverManager.getConnection(db_file);
        System.out.println("We're Connected");
    }catch(SQLException e){
      e.printStackTrace();
    }
    return connection;
  }
  public static Customer getCustomer(String username){
    String sql = "Select * From Customers Where username =? ";
    Customer customer = null;
    try(Connection connection = connect();
        PreparedStatement statement = connection.prepareStatement(sql)){

           statement.setString(1, username);
           try(ResultSet resultSet = statement.executeQuery()){
              customer = new Customer(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("username"),
                        resultSet.getString("password"),
                        resultSet.getInt("account_id")
                                      );
           }
        }catch(SQLException e){
          e.printStackTrace();
        }
        return customer;
  }
  public static Account getAccount(int accountId){
    String sql = "Select * From Accounts Where id = ?";
    Account account = null;
    try(Connection connection = connect(); 
        PreparedStatement statement = connection.prepareStatement(sql)){
          statement.setInt(1, accountId);
          
          try(ResultSet resultSet = statement.executeQuery()){
                 account = new Account(
                            resultSet.getInt("id"),
                            resultSet.getString("type"),
                            resultSet.getDouble("balance")
                           );
          }

        }catch(SQLException e){
          e.printStackTrace();
        }
        return account;
  }

  public static void main(String[] args) {
  
    Customer customer = getCustomer("twest8o@friendfeed.com");
    Account account = getAccount(customer.getAccount_id());
    System.out.println(account.getBalance());
  }
  
}
