package it.unisa.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import it.unisa.database.DriverManagerConnectionPool;

public class ConvenzioneModelDM {

  private static final String TABLE_NAME= "convenzione";

  public static void loadInfo(ConvenzioneBean conv) throws SQLException{
    Connection connection = null;
    PreparedStatement ps = null;

    String selectSQL = "SELECT * FROM " + TABLE_NAME + " WHERE id = ?";

    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(selectSQL);

      ps.setInt(1, conv.getId());

      ResultSet rs = ps.executeQuery();

      while(rs.next()) {
        conv.setInfo(rs.getString("info"));
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
    ConvenzioneBean conv = new ConvenzioneBean(id, null);
    conv.setId(id);
    String selectSQL = "SELECT * FROM " + TABLE_NAME + " WHERE id = ?";
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(selectSQL);
      ps.setInt(1, id);
      ResultSet rs = ps.executeQuery();
      while(rs.next()) {
        conv.setId(rs.getString("info"));
      }
    }finally {
      try {
        if (ps != null)
          ps.close();
      } finally {
        DriverManagerConnectionPool.releaseConnection(connection);
      }
	}
	return conv;
	
  }
	

  public static void saveConvenzione(ConvenzioneBean conv) throws SQLException{

    int id;
    String info;
    
    id = conv.getId();
    info = conv.getInfo();
    
    Connection connection = null;
    PreparedStatement ps = null;
    
    String selectSQL = "INSERT INTO " + TABLE_NAME + " VALUES (?,?)";
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStament(selectSQL);
      
      ps.setInt(1, id);
      ps.setString(2, info);
      
      System.out.println(ps.toString());
      
      ps.executeUpdate();
      connection.commit();

    } finally {
      try {
        if (ps != null)
          ps.close();
      }finally {
        DriverManagerConnectionPool.realeaseConnection(connection);
      }
	}
  }
}
