package it.unisa.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import it.unisa.database.DriverManagerConnectionPool;

public class ProgettoFormativoModelDM {

  private static final String TABLE_NAME = "prog_form";
  
  public static void loadInfo(ProgettoFormativoBean prog) throws SQLException{

    Connection connection = null;
    PreparedStatement ps = null;
    
    String selectSQL = "SELECT * FROM " + TABLE_NAME + " WHERE id = ?";
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(selectSQL);
      
      ps.setInt(1, prog.getId());
      
      ResultSet rs = ps.executeQuery();
      
      while(rs.next()) {
        prog.setApprovazione(rs.getBoolean("approvazione"));
        prog.setInfo(rs.getString("info"));
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
  
  
  public static ProgettoFormativoBean loadProgettoFormativo(int id) throws SQLException{
    
    Connection connection = null;
    PreparedStatement ps = null;
    
    ProgettoFormativoBean prog = new ProgettoFormativoBean();
    
    prog.setId(id);
    
    String selectSQL = "SELECT * FROM " + TABLE_NAME + " WHERE id = ?";
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(selectSQL);
      
      ps.setInt(1, id);
      
      ResultSet rs = ps.executeQuery();
      
      while(rs.next()) {
        prog.setApprovazione(rs.getBoolean("approvazione"));
        prog.setInfo(rs.getString("info"));
      }
      
    } finally {
      try {
        if (ps != null)
          ps.close();
        
      } finally {
        DriverManagerConnectionPool.releaseConnection(connection);
        
      }
      return prog;
      
    }
    
  }
  
  public static void saveProgettoFormativo(ProgettoFormativoBean prog) throws SQLException{
    
    int id;
    boolean approvazione;
    String info;
    
    id = prog.getId();
    approvazione = prog.getApprovazione();
    info = prog.getInfo();
    
    Connection connection = null;
    PreparedStatement ps = null;

    String selectSQL = "INSERT INTO " + TABLE_NAME + " VALUES (?,?,?)";

    try {

      connection = DriverManagerConnectionPool.getConnection();

      ps = connection.prepareStatement(selectSQL);
      
      ps.setInt(1, id);
      ps.setString(2, info);
      ps.setBoolean(3, approvazione);
      
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
