package it.unisa.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.util.ArrayList;
import it.unisa.database.DriverManagerConnectionPool;
import it.unisa.sql.PercorsoFormativoSQL;

public class PercorsoFormativoModelDM implements BeansModel {

  @Override
  public AbstractBean doRetrieveByKey(int code) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    PercorsoFormativoBean percorsoFormativoBean = null;
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(PercorsoFormativoSQL.DO_RETRIEVE_BY_KEY);
      
      ps.setInt(1, code);
      
      rs = ps.executeQuery();
      
      if (rs.next()) {
        int progettoFormativoID = rs.getInt("progettoFormativoID");
        String percorso = rs.getString("percorso");
        
        percorsoFormativoBean = new PercorsoFormativoBean(code, progettoFormativoID, percorso);
      } else {
        Logger.getGlobal().log(Level.INFO, "Oggetto PercorsoFormativoBean con l' id specificato non trovato");
      }
      
    } finally {
      try {
        if (ps != null)
          ps.close();
      } finally {
        DriverManagerConnectionPool.releaseConnection(connection);
      }
    }
    
    return percorsoFormativoBean;
  }

  @Override
  public Collection<AbstractBean> doRetrieveAll(String order) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    Collection<AbstractBean> percorsi = null;
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(PercorsoFormativoSQL.DO_RETRIEVE_ALL);
      
      rs = ps.executeQuery();
      
      percorsi = new ArrayList<AbstractBean>();
      
      while (rs.next()) {
        int id = rs.getInt("id");
        int progettoFormativoID = rs.getInt("progettoFormativoID");
        String percorso = rs.getString("percorso");
        
        percorsi.add(new PercorsoFormativoBean(id, progettoFormativoID, percorso));
      }
      
    } finally {
      try {
        if (ps != null)
          ps.close();
      } finally {
        DriverManagerConnectionPool.releaseConnection(connection);
      }
    }
    
    return percorsi;
  }

  @Override
  public void doSave(AbstractBean product) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    PercorsoFormativoBean percorsoFormativoBean = (PercorsoFormativoBean) product;
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(PercorsoFormativoSQL.DO_SAVE);
      
      ps.setInt(1, percorsoFormativoBean.getID());
      ps.setInt(2, percorsoFormativoBean.getProgettoFormativoID());
      ps.setString(3, percorsoFormativoBean.getPercorso());
      
      if (!(ps.executeUpdate() > 0)) {
        Logger.getGlobal().log(Level.INFO, "Oggetto PercorsoFormativoBean non memorizzato");
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
      ps = connection.prepareStatement(PercorsoFormativoSQL.DO_DELETE);
      
      ps.setInt(1, code);
      
      if (ps.executeUpdate() > 0) {
        deleted = true;
      } else {
        Logger.getGlobal().log(Level.INFO, "Oggetto PercorsoFormativoBean non rimosso");
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
    PercorsoFormativoBean percorsoFormativoBean = (PercorsoFormativoBean) product;
    boolean updated = false;
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(PercorsoFormativoSQL.DO_UPDATE);
      
      ps.setInt(1, percorsoFormativoBean.getProgettoFormativoID());
      ps.setString(2, percorsoFormativoBean.getPercorso());
      ps.setInt(3, percorsoFormativoBean.getID());
      
      if (ps.executeUpdate() > 0) {
        updated = true;
      } else {
        Logger.getGlobal().log(Level.INFO, "Oggetto PercorsoFormativoBean non aggiornato");
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

  public Collection<AbstractBean> doRetrieveByProgettoFormativo(int code) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    Collection<AbstractBean> percorsi = null;
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(PercorsoFormativoSQL.DO_RETRIEVE_BY_PROGETTO_FORMATIVO);
      
      ps.setInt(1, code);
      
      rs = ps.executeQuery();
      
      percorsi = new ArrayList<AbstractBean>();
      
      while (rs.next()) {
        int id = rs.getInt("id");
        String percorso = rs.getString("percorso");
        
        percorsi.add(new PercorsoFormativoBean(id, code, percorso));
      }
    } finally {
      try {
        if (ps != null)
          ps.close();
      } finally {
        DriverManagerConnectionPool.releaseConnection(connection);
      }
    }
    
    return percorsi;
  }
  
  public Collection<AbstractBean> doRetrieveByPercorso(String text) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    Collection<AbstractBean> percorsi = null;
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(PercorsoFormativoSQL.DO_RETRIEVE_BY_PERCORSO);
      
      ps.setString(1, text);
      
      rs = ps.executeQuery();
      
      percorsi = new ArrayList<AbstractBean>();
      
      while (rs.next()) {
        int id = rs.getInt("id");
        int progettoFormativoID = rs.getInt("progettoFormativoID");
        String percorso = rs.getString("percorso");
        
        percorsi.add(new PercorsoFormativoBean(id, progettoFormativoID, percorso));
      }
      
    } finally {
      try {
        if (ps != null)
          ps.close();
      } finally {
        DriverManagerConnectionPool.releaseConnection(connection);
      }
    }
    
    return percorsi;
  }
}