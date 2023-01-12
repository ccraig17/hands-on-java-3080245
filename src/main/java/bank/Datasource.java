package bank;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Datasource {
  public static Connection connect() {
    String db_file = "jdbc:sqlite:resources/bank.db";
    Connection connection = null;
    try {
      connection = DriverManager.getConnection(db_file);
      System.out.println("We're Connected");
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return connection;
  }

  public static Customer getCustomer(String username) {
    String sql = "Select * From Customers Where username =? ";
    Customer customer = null; //the Customer obj is initialize outside the Try-Catch to null;
    try (Connection connection = connect();
        PreparedStatement statement = connection.prepareStatement(sql)) {

      statement.setString(1, username);
      //ResultSet Class returns the results from the db for the obj;
      // ResultSet is a closeable class so we use/place it in a Try w/ Resources.
      try (ResultSet resultSet = statement.executeQuery()) { //to get results from the db "executeQuery" is used
        customer = new Customer(
            resultSet.getInt("id"),
            resultSet.getString("name"),
            resultSet.getString("username"),
            resultSet.getString("password"),
            resultSet.getInt("account_id"));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return customer;
  }

  public static Account getAccount(int accountId) {
    String sql = "Select * From Accounts Where id = ?";
    Account account = null;
    try (Connection connection = connect();
        PreparedStatement statement = connection.prepareStatement(sql)) {
      statement.setInt(1, accountId);

      try (ResultSet resultSet = statement.executeQuery()) {
        account = new Account(
            resultSet.getInt("id"),
            resultSet.getString("type"),
            resultSet.getDouble("balance"));
      }

    } catch (SQLException e) {
      e.printStackTrace();
    }
    return account;
  }

  public static void updateAccountBalance(int accountId, double balance) {
    String sql = "UPDATE accounts set balance ? WHERE accountId = ?";
    Account account = null;

    try (Connection connection = connect();
        PreparedStatement statement = connection.prepareStatement(sql)) {
      statement.setDouble(1, balance);
      statement.setInt(2, accountId);
      statement.executeUpdate(); //for INSERT, UPDATE or DELETE statements use "executeUpdate"
    } catch (SQLException e) {
      e.getStackTrace();
    }

  }
}
