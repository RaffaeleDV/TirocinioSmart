package it.unisa.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import it.unisa.database.DriverManagerConnectionPool;

public class ConvenzioneModelDM {

  public static final String TABLE_NAME = "convenzione";

  public static void loadInfo(ConvenzioneBean conv) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;

    String selectSQL = "SELECT * FROM " + TABLE_NAME + " WHERE id = ?";

    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(selectSQL);

      ps.setInt(1, conv.getId());

      ResultSet rs = ps.executeQuery();

      while (rs.next()) {
        conv.setInfo(rs.getString("info"));
        conv.setAzienda(rs.getString("azienda"));
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


  public static ConvenzioneBean loadConvenzione(int id) throws SQLException {

    Connection connection = null;
    PreparedStatement ps = null;
    ConvenzioneBean conv = new ConvenzioneBean(id, null, null);
    conv.setId(id);
    String selectSQL = "SELECT * FROM " + TABLE_NAME + " WHERE id = ?";
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(selectSQL);
      ps.setInt(1, id);
      ResultSet rs = ps.executeQuery();
      while (rs.next()) {
        conv.setInfo(rs.getString("info"));
        conv.setAzienda(rs.getString("azienda"));
      }
    } finally {
      try {
        if (ps != null)
          ps.close();
      } finally {
        DriverManagerConnectionPool.releaseConnection(connection);
      }
    }
    return conv;

  }


  public static void saveConvenzione(ConvenzioneBean conv) throws SQLException {

    int id;
    String info;
    String azienda;
    id = conv.getId();
    info = conv.getInfo();
    azienda = conv.getAzienda();
    Connection connection = null;
    PreparedStatement ps = null;

    String selectSQL = "INSERT INTO " + TABLE_NAME + " VALUES (?,?,?)";

    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(selectSQL);

      ps.setInt(1, id);
      ps.setString(2, info);
      ps.setString(3, azienda);

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
