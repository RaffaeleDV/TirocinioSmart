package it.unisa.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import it.unisa.database.DriverManagerConnectionPool;

public class UfficioModelDM {

  private static final String TABLE_NAME = "ufficio";

  public static void loadInfo(UfficioBean ufficio) throws SQLException {

    Connection connection = null;
    PreparedStatement ps = null;

    String selectSQL = "SELECT * FROM " + TABLE_NAME + " WHERE id = ?";

    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(selectSQL);

      ps.setInt(1, ufficio.getId());

      ResultSet rs = ps.executeQuery();

      while (rs.next()) {
        ufficio.setNome(rs.getString("nome"));
        ufficio.setPassword(rs.getString("password"));
      }

    } finally {
      try {
        if (ps != null)
          ps.close();
      } finally {
        DriverManagerConnectionPool.releaseConnection(connection);
      }
    }
  }

  public static UfficioBean loadUfficio(int id) throws SQLException {

    Connection connection = null;
    PreparedStatement ps = null;

    UfficioBean uff = new UfficioBean();

    uff.setId(id);

    String selectSQL = "SELECT * FROM " + TABLE_NAME + " WHERE id = ?";

    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(selectSQL);

      ps.setInt(1, id);

      ResultSet rs = ps.executeQuery();

      while (rs.next()) {
        uff.setNome(rs.getString("nome"));
        uff.setPassword(rs.getString("password"));
      }
    } finally {
      try {
        if (ps != null)
          ps.close();
      } finally {
        DriverManagerConnectionPool.releaseConnection(connection);
      }
    }
    return uff;
  }

  public static void saveUfficio(UfficioBean ufficio) throws SQLException {
    int id;
    String nome, password;

    id = ufficio.getId();
    nome = ufficio.getNome();
    password = ufficio.getPassword();

    Connection connection = null;
    PreparedStatement ps = null;

    String selectSQL = "INSERT INTO " + TABLE_NAME + " VALUES (?,?,?)";

    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(selectSQL);

      ps.setInt(1, id);
      ps.setString(2, nome);
      ps.setString(3, password);

      System.out.println(ps.toString());

      ps.executeUpdate();
      connection.commit();
    } finally {
      try {
        if (ps != null)
          ps.close();
      } finally {
        DriverManagerConnectionPool.releaseConnection(connection);
      }
    }
  }
}
