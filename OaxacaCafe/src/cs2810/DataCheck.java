package cs2810;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataCheck {

  private final static String user = "vnxcaquwfcdcmx";
  private final static String password =
      "57a2d6d4bc061d9a386aaa5352bac1ac7cfc1744b1b7e46318519a73c7dfa547";



  public static void main(String[] args) throws URISyntaxException, SQLException {
    Connection Connect = getConnection();
  }

  private static Connection getConnection() throws URISyntaxException, SQLException {
    URI dbUri = new URI(System.getenv(
        "jdbc:postgresq1://vnxcaquwfcdcmx:57a2d6d4bc061d9a386aaa5352bac1ac7cfc1744b1b7e46318519a73c7dfa547@ec2-52-211-161-21.eu-west-1.compute.amazonaws.com:5432/dakl9haghtbqac"));

    String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();

    return DriverManager.getConnection(dbUrl, user, password);
  }
}