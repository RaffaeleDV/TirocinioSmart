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
import it.unisa.sql.RiguardaSQL;

public class RiguardaModelDM implements BeansModel {

  @Override
  public AbstractBean doRetrieveByKey(int code) throws SQLException {
    return null;
  }

  @Override
  public Collection<AbstractBean> doRetrieveAll(String order) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    Collection<AbstractBean> segue = null;
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(RiguardaSQL.DO_RETRIEVE_ALL);
      
      rs = ps.executeQuery();
      
      segue = new ArrayList<AbstractBean>();
      
      while (rs.next()) {
        int progettoFormativoID = rs.getInt("progettoFormativoID");
        int settoreOperativoID = rs.getInt("settoreOperativoID");
        int priorita = rs.getInt("priorita");
        
        segue.add(new RiguardaBean(progettoFormativoID, settoreOperativoID, priorita));
      }
      
    } finally {
      try {
        if (ps != null)
          ps.close();
      } finally {
        DriverManagerConnectionPool.releaseConnection(connection);
      }
    }
    
    return segue;
  }

  @Override
  public void doSave(AbstractBean product) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    RiguardaBean segueBean = (RiguardaBean) product;
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(RiguardaSQL.DO_SAVE);
      
      ps.setInt(1, segueBean.getProgettoFormativoID());
      ps.setInt(2, segueBean.getSettoreOperativoID());
      ps.setInt(3, segueBean.getPriorita());
      
      if (!(ps.executeUpdate() > 0)) {
        Logger.getGlobal().log(Level.INFO, "Oggetto SegueBean non memorizzato");
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
    return false;
  }
  
  public boolean doDeleteByProgettoFormativo(int code) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    boolean deleted = false;
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(RiguardaSQL.DO_DELETE_BY_PROGETTO_FORMATIVO);
      
      ps.setInt(1, code);
      
      if (ps.executeUpdate() > 0) {
        deleted = true;
      } else {
        Logger.getGlobal().log(Level.INFO, "Oggetto SegueBean non rimosso");
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
  
  public boolean doDeleteBySettoreOperativo(int code) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    boolean deleted = false;
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(RiguardaSQL.DO_DELETE_BY_SETTORE_OPERATIVO);
      
      ps.setInt(1, code);
      
      if (ps.executeUpdate() > 0) {
        deleted = true;
      } else {
        Logger.getGlobal().log(Level.INFO, "Oggetto SegueBean non rimosso");
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
  
  public Collection<AbstractBean> doRetrieveByProgettoFormativo(int code) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    Collection<AbstractBean> segue = null;
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(RiguardaSQL.DO_RETRIEVE_BY_PROGETTO_FORMATIVO);
      
      ps.setInt(1, code);
      
      rs = ps.executeQuery();
      
      segue = new ArrayList<AbstractBean>();
      
      while (rs.next()) {
        int settoreOperativoID = rs.getInt("settoreOperativoID");
        int priorita = rs.getInt("priorita");
        
        segue.add(new RiguardaBean(code, settoreOperativoID, priorita));
      }
      
    } finally {
      try {
        if (ps != null)
          ps.close();
      } finally {
        DriverManagerConnectionPool.releaseConnection(connection);
      }
    }
    
    return segue;
  }
  
  public Collection<AbstractBean> doRetrieveBySettoreOperativo(int code) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    Collection<AbstractBean> segue = null;
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(RiguardaSQL.DO_RETRIEVE_BY_SETTORE_OPERATIVO);
      
      ps.setInt(1, code);
      
      rs = ps.executeQuery();
      
      segue = new ArrayList<AbstractBean>();
      
      while (rs.next()) {
        int progettoFormativoID = rs.getInt("progettoFormativoID");
        int priorita = rs.getInt("priorita");
        
        segue.add(new RiguardaBean(progettoFormativoID, code, priorita));
      }
      
    } finally {
      try {
        if (ps != null)
          ps.close();
      } finally {
        DriverManagerConnectionPool.releaseConnection(connection);
      }
    }
    
    return segue;
  }
  
  public Collection<AbstractBean> doRetrieveByPriorita(int code) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    Collection<AbstractBean> segue = null;
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(RiguardaSQL.DO_RETRIEVE_BY_PRIORITA);
      
      ps.setInt(1, code);
      
      rs = ps.executeQuery();
      
      segue = new ArrayList<AbstractBean>();
      
      while (rs.next()) {
        int settoreOperativoID = rs.getInt("settoreOperativoID");
        int progettoFormativoID = rs.getInt("progettoFormativoID");
        
        segue.add(new RiguardaBean(progettoFormativoID, settoreOperativoID, code));
      }
      
    } finally {
      try {
        if (ps != null)
          ps.close();
      } finally {
        DriverManagerConnectionPool.releaseConnection(connection);
      }
    }
    
    return segue;
  }
}
