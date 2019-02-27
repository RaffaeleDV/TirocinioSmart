package it.unisa.model;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import it.unisa.database.DriverManagerConnectionPool;
import it.unisa.sql.ConvenienzaSQL;

public class ConvenienzaModelDM implements BeansModel {

  @Override
  public AbstractBean doRetrieveByKey(int code) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    ConvenienzaBean convenienzaBean = null;
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(ConvenienzaSQL.DO_RETRIEVE_BY_KEY);
      
      ps.setInt(1, code);
      
      rs = ps.executeQuery();
      
      if (rs.next()) {
        int convenzioneID = rs.getInt("convenzioneID");
        String descrizione = rs.getString("descrizione");
        
        convenienzaBean = new ConvenienzaBean(code, convenzioneID, descrizione);
      } else {
        Logger.getGlobal().log(Level.INFO, "Convenienza con l' id specificato non trovato");
      }
      
    } finally {
      try {
        if (ps != null)
          ps.close();
      } finally {
        DriverManagerConnectionPool.releaseConnection(connection);
      }
    }
    
    return convenienzaBean;
  }

  @Override
  public Collection<AbstractBean> doRetrieveAll(String order) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    Collection<AbstractBean> convenienze = null;
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(ConvenienzaSQL.DO_RETRIEVE_ALL);
      
      rs = ps.executeQuery();
      
      convenienze = new ArrayList<AbstractBean>();
      
      while (rs.next()) {
        int id = rs.getInt("id");
        int convenzioneID = rs.getInt("convenzioneID");
        String descrizione = rs.getString("descrizione");
        
        convenienze.add(new ConvenienzaBean(id, convenzioneID, descrizione));
      }
      
    } finally {
      try {
        if (ps != null)
          ps.close();
      } finally {
        DriverManagerConnectionPool.releaseConnection(connection);
      }
    }
    
    return convenienze;
  }

  @Override
  public void doSave(AbstractBean product) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    ConvenienzaBean convenienzaBean = (ConvenienzaBean) product;
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(ConvenienzaSQL.DO_SAVE);
      
      ps.setInt(1, convenienzaBean.getID());
      ps.setInt(2, convenienzaBean.getConvenzioneID());
      ps.setString(3, convenienzaBean.getDescrizione());
      
      if (!(ps.executeUpdate() > 0)) {
        Logger.getGlobal().log(Level.INFO, "L' oggetto ConvenienzaBean non è stato memorizzato");
      }
      
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

  @Override
  public boolean doDelete(int code) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    boolean deleted = false;
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(ConvenienzaSQL.DO_DELETE);
      
      ps.setInt(1, code);
      
      if (ps.executeUpdate() > 0) {
        deleted = true;
      } else { 
        Logger.getGlobal().log(Level.INFO, "Oggeto ConvenienzaBean non rimosso");
      }
      
      connection.commit();
      
    } finally {
      try {
        if (ps != null)
          ps.close();
      } finally {
        DriverManagerConnectionPool.releaseConnection(connection);
      }
    }
    
    return deleted;
  }
  
  public boolean doUpdate(AbstractBean product) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    ConvenienzaBean convenienzaBean = (ConvenienzaBean) product;
    boolean updated = false;
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(ConvenienzaSQL.DO_UPDATE);
      
      ps.setInt(1, convenienzaBean.getConvenzioneID());
      ps.setString(2, convenienzaBean.getDescrizione());
      ps.setInt(3, convenienzaBean.getID());
      
      if (ps.executeUpdate() > 0) {
        updated = true;
      } else {
        Logger.getGlobal().log(Level.INFO, "Oggetto ConvenienzaBean non aggiornato");
      }
      
      connection.commit();
      
    } finally {
      try {
        if (ps != null)
          ps.close();
      } finally {
        DriverManagerConnectionPool.releaseConnection(connection);
      }
    }
    
    return updated;
  }
  
  public Collection<AbstractBean> doRetrieveByConvenzione(int code) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    Collection<AbstractBean> convenienze = null;
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(ConvenienzaSQL.DO_RETRIEVE_BY_CONVENZIONE);
      
      ps.setInt(1, code);
      
      rs = ps.executeQuery();
      
      convenienze = new ArrayList<AbstractBean>();
      
      while (rs.next()) {
        int id = rs.getInt("id");
        String descrizione = rs.getString("descrizione");
        
        convenienze.add(new ConvenienzaBean(id, code, descrizione));
      }
      
    } finally {
      try {
        if (ps != null)
          ps.close();
      } finally {
        DriverManagerConnectionPool.releaseConnection(connection);
      }
    }
    
    return convenienze;
  }
  
  /*
   * può essere utile quando si vuole sapere quali convenienze fanno riferimento ad una certa norma o articolo
   */
  public  Collection<AbstractBean> doRetrieveByDescrizione(String text) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    Collection<AbstractBean> convenzioni = null;
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(ConvenienzaSQL.DO_RETRIEVE_BY_DESCRIZIONE);
      
      ps.setString(1, text);
      
      rs = ps.executeQuery();
      
      convenzioni = new ArrayList<AbstractBean>();
      
      while (rs.next()) {
        int id = rs.getInt("id");
        int convenzioneID = rs.getInt("convenzioneID");
        String descrizione = rs.getString("descrizione");
        
        convenzioni.add(new ConvenienzaBean(id, convenzioneID, descrizione));
      }
      
    } finally {
      try {
        if (ps != null)
          ps.close();
      } finally {
        DriverManagerConnectionPool.releaseConnection(connection);
      }
    }
    
    return convenzioni;
  }
}
