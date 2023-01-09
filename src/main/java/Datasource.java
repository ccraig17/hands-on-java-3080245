import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Datasource {
  public static Connection connect(){
    String db_file = "jdbc:sqlite:resources/bank.db";
      Connection connect = null;
      try{
        DriverManager.getConnection(db_file);
        System.out.println("We are connected");
      }catch(SQLException e){
        e.printStackTrace();
      }
     return connect;   
  }
  public static void main(String[] args) {
    connect();
  }
}
