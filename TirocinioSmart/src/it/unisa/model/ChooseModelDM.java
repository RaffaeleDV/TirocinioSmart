package it.unisa.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.logging.Logger;
import java.util.logging.Level;
import it.unisa.database.DriverManagerConnectionPool;
import it.unisa.sql.ChooseSQL;
import java.util.ArrayList;

public class ChooseModelDM implements BeansModel {

  public static final ChooseModelDM INSTANCE = new ChooseModelDM();

  private ChooseModelDM() {

  }

  @Override
  public AbstractBean doRetrieveByKey(int code) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    ChooseBean chooseBean = null;
    
    try {
      connection = (Connection) DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(ChooseSQL.DO_RETRIEVE_BY_KEY);
      
      ps.setInt(1, code);
      
      rs = ps.executeQuery();
      
      if (rs.next()) {
        int id = rs.getInt("id");
        String description = rs.getString("description");
        String tipo = rs.getString("tipo");
        
        chooseBean = new ChooseBean(id, description, tipo);
      } else {
        Logger.getGlobal().log(Level.SEVERE, "Oggetto ChooseBean Non Trovato.");
      }
      
    } finally {
      try {
        if (ps != null)
          ps.close();
      } finally {
        DriverManagerConnectionPool.releaseConnection(connection);
      }
    }
    
    return chooseBean;
  }

  @Override
  public Collection<AbstractBean> doRetrieveAll(String order) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    Collection<AbstractBean> chooses = null;
    
    try {
      
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(ChooseSQL.DO_RETRIEVE_ALL);
      
      rs = ps.executeQuery();
      
      chooses = new ArrayList<AbstractBean>();
      
      while(rs.next()) {
        int id = rs.getInt("id");
        String description = rs.getString("description");
        String tipo = rs.getString("tipo");
        
       chooses.add(new ChooseBean(id, description, tipo));
      }
      
    } finally {
      try {
        if (ps != null)
          ps.close();
      } finally {
        DriverManagerConnectionPool.releaseConnection(connection);
      }
    }
    
    return chooses;
  }

  @Override
  public void doSave(AbstractBean product) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    ChooseBean choose = (ChooseBean) product;
    
    try {
      
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(ChooseSQL.DO_SAVE);
    
      ps.setInt(1, choose.getID());
      ps.setString(2, choose.getDescription());
      ps.setString(3, choose.getTipo());
    
      if (!(ps.executeUpdate() > 0)) {
        Logger.getGlobal().log(Level.SEVERE, "Oggetto ChooseBean Non Memorizzato.");
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
      ps = connection.prepareStatement(ChooseSQL.DO_DELETE);
    
      ps.setInt(1, code);
    
      if (ps.executeUpdate() > 0) {
        deleted = true;
      } else {
        Logger.getGlobal().log(Level.SEVERE, "Oggetto ChooseBean Non Rimosso.");
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
  
  public boolean doUpdate(AbstractBean product, int oldID) throws SQLException{
    Connection connection = null;
    PreparedStatement ps = null;
    ChooseBean chooseBean = (ChooseBean) product;
    boolean updated = false;
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(ChooseSQL.DO_UPDATE);
      
      ps.setInt(1, chooseBean.getID());
      ps.setString(2, chooseBean.getDescription());
      ps.setString(3, chooseBean.getTipo());
      ps.setInt(4, oldID);
      
      if (ps.executeUpdate() > 0) {
        updated = true;
      } else {
        Logger.getGlobal().log(Level.SEVERE, "Oggetto ChooseBean Non Aggiornato.");
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
  
  public Collection<AbstractBean> doRetrieveByDescription(String text) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    Collection<AbstractBean> chooses = null;
    
    try {
      connection = (Connection) DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(ChooseSQL.DO_RETRIEVE_BY_DESCRIPTION);
      
      ps.setString(1, text);
      
      rs = ps.executeQuery();
      
      chooses = new ArrayList<AbstractBean>();
      
      while (rs.next()) {
        int id = rs.getInt("id");
        String tipo = rs.getString("tipo");
        
        chooses.add(new ChooseBean(id, text, tipo));
      }
      
    } finally {
      try {
        if (ps != null)
          ps.close();
      } finally {
        DriverManagerConnectionPool.releaseConnection(connection);
      }
    }
    
    return chooses;
  }
  
  public Collection<AbstractBean> doRetrieveByTipo(String text) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    Collection<AbstractBean> chooses = null;
    
    try {
      connection = (Connection) DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(ChooseSQL.DO_RETRIEVE_BY_TIPO);
      
      ps.setString(1, text);
      
      rs = ps.executeQuery();
      
      chooses = new ArrayList<AbstractBean>();
      
      while (rs.next()) {
        int id = rs.getInt("id");
        String description = rs.getString("description");
        String tipo = rs.getString("tipo");
        
        chooses.add(new ChooseBean(id, description, tipo));
      }
      
    } finally {
      try {
        if (ps != null)
          ps.close();
      } finally {
        DriverManagerConnectionPool.releaseConnection(connection);
      }
    }
    
    return chooses;
  }
  
  public Collection<AbstractBean> doRetrieveByQuestion(int code) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    Collection<AbstractBean> chooses = null;
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(ChooseSQL.DO_RETRIEVE_BY_QUESTION);
      
      ps.setInt(1, code);
      
      rs = ps.executeQuery();
      
      chooses = new ArrayList<AbstractBean>();
      
      while (rs.next()) {
        int id = rs.getInt("id");
        String description = rs.getString("description");
        String tipo = rs.getString("tipo");
        
        chooses.add(new ChooseBean(id, description, tipo));
      }
      
    } finally {
      try {
        if (ps != null)
          ps.close();
      } finally {
        DriverManagerConnectionPool.releaseConnection(connection);
      }
    }
    
    return chooses;
  }
}
