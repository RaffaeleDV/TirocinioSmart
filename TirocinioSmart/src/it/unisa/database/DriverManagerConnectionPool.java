package it.unisa.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class DriverManagerConnectionPool {
  private static List<Connection> freeDbConnections;


  static {
    freeDbConnections = new LinkedList<Connection>();
    try {
      Class.forName("com.mysql.jdbc.Driver");
    } catch (ClassNotFoundException e) {
      System.out.println("DB driver not found:" + e.getMessage());
      e.printStackTrace();
    }
  }


  private static synchronized Connection createDBConnection() throws SQLException {

    Connection newConnection = null;
    String ip = "localhost";
    String port = "3306";
    String db = "dbtirocinio";
    String username = "root";
    String password = "raffaele";

    newConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbtirocinio",
        username, password);
    newConnection.setAutoCommit(false);
    return newConnection;
  }


  public static synchronized Connection getConnection() throws SQLException {
    Connection connection;

    if (!freeDbConnections.isEmpty()) {
      connection = (Connection) freeDbConnections.get(0);
      freeDbConnections.remove(0);

      try {
        if (connection.isClosed())
          connection = getConnection();
      } catch (SQLException e) {
        connection.close();
        connection = getConnection();
      }
    } else {
      connection = createDBConnection();
    }
    return connection;
  }

  public static synchronized void releaseConnection(Connection connection) throws SQLException {
    if (connection != null)
      freeDbConnections.add(connection);
  }
}
