package it.unisa.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import it.unisa.database.DriverManagerConnectionPool;

public class UfficioModelDM {

  public static final String TABLE_NAME = "ufficio";

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
        ufficio.setEmail(rs.getString("email"));
        ufficio.setNome(rs.getString("nome"));
        ufficio.setPass(rs.getString("pass"));
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
        uff.setEmail(rs.getString("email"));
        uff.setNome(rs.getString("nome"));
        uff.setPass(rs.getString("pass"));
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
    String nome, pass, email;

    id = ufficio.getId();
    nome = ufficio.getNome();
    pass = ufficio.getPass();
    email = ufficio.getEmail();
    
    Connection connection = null;
    PreparedStatement ps = null;

    String selectSQL = "INSERT INTO " + TABLE_NAME + " VALUES (?,?,?,?)";

    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(selectSQL);

      ps.setInt(1, id);
      ps.setString(2, email);
      ps.setString(3, nome);
      ps.setString(4, pass);

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
