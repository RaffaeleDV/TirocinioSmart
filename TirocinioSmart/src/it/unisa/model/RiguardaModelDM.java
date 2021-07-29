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
import it.unisa.sql.RiguardaSQL;

public class RiguardaModelDM implements BeansModel {

  public static final RiguardaModelDM INSTANCE = new RiguardaModelDM();

  private RiguardaModelDM() {

  }

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
        Logger.getGlobal().log(Level.SEVERE, "Oggetto RiguardaBean Non Memorizzato.");
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
  
  public boolean doDelete(int codeProgettoFormativoID, int codeSettoreOperativoID) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    boolean deleted = false;
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(RiguardaSQL.DO_DELETE);
      
      ps.setInt(1, codeProgettoFormativoID);
      ps.setInt(2, codeSettoreOperativoID);
      
      if(!(ps.executeUpdate() > 0)) {
        Logger.getGlobal().log(Level.SEVERE, "Oggetto RiguardaBean Non Rimosso.");
      } else {
    	  deleted = true;
      }
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
  
  public boolean doUpdate(AbstractBean abstractBean, int codeProgettoFormativo, int codeSettoreOperativo) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    RiguardaBean riguardaBean = (RiguardaBean) abstractBean;
    boolean updated = false;
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(RiguardaSQL.DO_UPDATE);
      
      ps.setInt(1, riguardaBean.getProgettoFormativoID());
      ps.setInt(2, riguardaBean.getSettoreOperativoID());
      ps.setInt(3, riguardaBean.getPriorita());
      ps.setInt(4, codeProgettoFormativo);
      ps.setInt(5, codeSettoreOperativo);
      
      if (ps.executeUpdate() > 0) {
        updated = true;
      } else {
        Logger.getGlobal().log(Level.SEVERE, "Oggetto RiguardaBean Non Aggiornato.");
      }
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
  
  public AbstractBean doRetrieveByKey(int codeProgettoFormativo, int codeSettoreOperativo) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    RiguardaBean riguardaBean = null;
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(RiguardaSQL.DO_RETRIEVE_BY_KEY);
      
      ps.setInt(1, codeProgettoFormativo);
      ps.setInt(2, codeSettoreOperativo);
      
      rs = ps.executeQuery();
      
      if (rs.next()) {
        int progettoFormativoID = rs.getInt("progettoFormativoID");
        int settoreOperativoID = rs.getInt("settoreOperativoID");
        int priorita = rs.getInt("priorita");
        riguardaBean = new RiguardaBean(progettoFormativoID, settoreOperativoID, priorita);
      } else {
        Logger.getGlobal().log(Level.SEVERE, "Oggetto RiguardaBean Non Trovato.");
      }
    } finally {
      try {
        if (ps != null)
          ps.close();
      } finally {
        DriverManagerConnectionPool.releaseConnection(connection);
      }
    }
    return riguardaBean;
  }
  
  public Collection<AbstractBean> doRetrieveByProgettoFormativo(int code) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    Collection<AbstractBean> riguarda = null;
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(RiguardaSQL.DO_RETRIEVE_BY_PROGETTO_FORMATIVO);
      
      ps.setInt(1, code);
      
      rs = ps.executeQuery();
      
      riguarda = new ArrayList<AbstractBean>();
      
      while (rs.next()) {
        int settoreOperativoID = rs.getInt("settoreOperativoID");
        int priorita = rs.getInt("priorita");
        
        riguarda.add(new RiguardaBean(code, settoreOperativoID, priorita));
      }
      
    } finally {
      try {
        if (ps != null)
          ps.close();
      } finally {
        DriverManagerConnectionPool.releaseConnection(connection);
      }
    }
    return riguarda;
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
  
  public Collection<AbstractBean> doRetrieveByPriorita(int starting, int ending) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    Collection<AbstractBean> riguarda = null;
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(RiguardaSQL.DO_RETRIEVE_BY_PRIORITA);
      
      ps.setInt(1, starting);
      ps.setInt(2, ending);
      
      rs = ps.executeQuery();
      
      riguarda = new ArrayList<AbstractBean>();
      
      while (rs.next()) {
        int settoreOperativoID = rs.getInt("settoreOperativoID");
        int progettoFormativoID = rs.getInt("progettoFormativoID");
        int priorita = rs.getInt("priorita");
        
        riguarda.add(new RiguardaBean(progettoFormativoID, settoreOperativoID, priorita));
      }
      
    } finally {
      try {
        if (ps != null)
          ps.close();
      } finally {
        DriverManagerConnectionPool.releaseConnection(connection);
      }
    }
    return riguarda;
  }
}
