package it.unisa.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

import it.unisa.database.DriverManagerConnectionPool;
import it.unisa.sql.SettoreOperativoSQL;

public class SettoreOperativoModelDM implements BeansModel {

  public static final SettoreOperativoModelDM INSTANCE = new SettoreOperativoModelDM();

  private SettoreOperativoModelDM() {

  }

  @Override
  public AbstractBean doRetrieveByKey(int code) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    SettoreOperativoBean settoreOperativoBean = null;
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(SettoreOperativoSQL.DO_RETRIEVE_BY_KEY);
      
      ps.setInt(1, code);
      
      rs = ps.executeQuery();
      
      if (rs.next()) {
        String descrizione = rs.getString("descrizione");
        
        settoreOperativoBean = new SettoreOperativoBean(code, descrizione);
      } else {
        Logger.getGlobal().log(Level.SEVERE, "Oggetto SettoreOperativoBean Non Trovato.");
      }
      
    } finally {
      try {
        if (ps != null)
          ps.close();
      } finally {
        DriverManagerConnectionPool.releaseConnection(connection);
      }
    }
    return settoreOperativoBean;
  }

  @Override
  public Collection<AbstractBean> doRetrieveAll(String order) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    Collection<AbstractBean> settoriOperativi = null;
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(SettoreOperativoSQL.DO_RETRIEVE_ALL);
      
      rs = ps.executeQuery();
      
      settoriOperativi = new ArrayList<AbstractBean>();
      
      while (rs.next()) {
        int id = rs.getInt("id");
        String descrizione = rs.getString("descrizione");
        
        settoriOperativi.add(new SettoreOperativoBean(id, descrizione));
      }
      
    } finally {
      try {
        if (ps != null)
          ps.close();
      } finally {
        DriverManagerConnectionPool.releaseConnection(connection);
      }
    }
    
    return settoriOperativi;
  }

  @Override
  public void doSave(AbstractBean product) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    SettoreOperativoBean settoreOperativoBean = null;
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(SettoreOperativoSQL.DO_SAVE);
      
      settoreOperativoBean = (SettoreOperativoBean) product;
      
      ps.setInt(1, settoreOperativoBean.getID());
      ps.setString(2, settoreOperativoBean.getDescrizione());
      
      if (!(ps.executeUpdate() > 0)) {
        Logger.getGlobal().log(Level.SEVERE, "Oggetto SettoreOperativoBean Non Memorizzato.");
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
      ps = connection.prepareStatement(SettoreOperativoSQL.DO_DELETE);
      
      ps.setInt(1, code);
      
      if (ps.executeUpdate() > 0) {
        deleted = true;
      } else {
        Logger.getGlobal().log(Level.SEVERE, "Oggetto SettoreOperativoBean Non Rimosso.");
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

  public boolean doUpdate(AbstractBean product, int codeSettoreOperativo) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    SettoreOperativoBean settoreOperativoBean = (SettoreOperativoBean) product;
    boolean updated = false;
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(SettoreOperativoSQL.DO_UPDATE);
      
      ps.setInt(1, settoreOperativoBean.getID());
      ps.setString(2, settoreOperativoBean.getDescrizione());
      ps.setInt(3, codeSettoreOperativo);
      
      if (ps.executeUpdate() > 0) {
        updated = true;
      } else {
        Logger.getGlobal().log(Level.SEVERE, "Oggetto SettoreOperativoBean Non Aggiornato.");
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
  
  public Collection<AbstractBean> doRetrieveByDescrizione(String text) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    Collection<AbstractBean> settoriOperativi = null;
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(SettoreOperativoSQL.DO_RETRIEVE_BY_DESCRIZIONE);
      
      ps.setString(1, text);
      
      rs = ps.executeQuery();
      
      settoriOperativi = new ArrayList<AbstractBean>();
      
      while (rs.next()) {
        int id = rs.getInt("id");
        String descrizione = rs.getString("descrizione");
        
        settoriOperativi.add(new SettoreOperativoBean(id, descrizione));
      }
      
    } finally {
      try {
        if (ps != null)
          ps.close();
      } finally {
        DriverManagerConnectionPool.releaseConnection(connection);
      }
    }
    return settoriOperativi;
  }
}
