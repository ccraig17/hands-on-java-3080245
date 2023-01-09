package bank;

public class Customer {
  private int id;
  private String name;
  private String username;
  private String password;
  private int account_id;

  public Customer(int id, String name, String username, String password, int account_id) {
    setId(id);
    setName(username);;
    setUsername(username);;
    setPassword(password);;
    setAccount_id(account_id);
  }

  public int getId() {
    return this.id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getUsername() {
    return this.username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return this.password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public int getAccount_id() {
    return this.account_id;
  }

  public void setAccount_id(int account_id) {
    this.account_id = account_id;
  }

}
