package it.unisa.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;
import it.unisa.database.DriverManagerConnectionPool;
import it.unisa.sql.RispondeSQL;

public class RispondeModelDM implements BeansModel {

  @Override
  public AbstractBean doRetrieveByKey(int code) throws SQLException {
    return null;
  }

  @Override
  public Collection<AbstractBean> doRetrieveAll(String order) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    Collection<AbstractBean> risponde = null;
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(RispondeSQL.DO_RETRIEVE_ALL);
      
      rs = ps.executeQuery();
      
      risponde = new ArrayList<AbstractBean>();
      
      while (rs.next()) {
        int questionID = rs.getInt("questionID");
        int chooseID = rs.getInt("chooseID");
        
        risponde.add(new RispondeBean(questionID, chooseID));
      }
      
    } finally {
      try {
        if (ps != null)
          ps.close();
      } finally {
        DriverManagerConnectionPool.releaseConnection(connection);
      }
    }
    
    return risponde;
  }

  @Override
  public void doSave(AbstractBean product) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    RispondeBean risponde = (RispondeBean) product;
    
    try {
      
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(RispondeSQL.DO_SAVE);
      
      ps.setInt(1, risponde.getQuestionID());
      ps.setInt(2, risponde.getChooseID());
      
      if (!(ps.executeUpdate() > 0)) {
        Logger.getGlobal().log(Level.INFO, "Oggetto RispondeBean non memorizzato");
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

  public Collection<AbstractBean> doRetrieveByQuestion(int code) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    Collection<AbstractBean> risposte = null;
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(RispondeSQL.DO_RETRIEVE_BY_QUESTION);
      
      ps.setInt(1, code);
      
      rs = ps.executeQuery();
      
      risposte = new ArrayList<AbstractBean>();
      
      while (rs.next()) {
        int chooseID = rs.getInt("chooseID");
        
        risposte.add(new RispondeBean(code, chooseID));
      }
      
    } finally {
      try {
        if (ps != null)
          ps.close();
      } finally {
        DriverManagerConnectionPool.releaseConnection(connection);
      }
    }
    
    return risposte;
  }
  
  public Collection<AbstractBean> doRetrieveByChoose(int code) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    Collection<AbstractBean> risposte = null;
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(RispondeSQL.DO_RETRIEVE_BY_CHOOSE);
      
      ps.setInt(1, code);
      
      rs = ps.executeQuery();
      
      risposte = new ArrayList<AbstractBean>();
      
      while (rs.next()) {
        int questionID = rs.getInt("questionID");
        
        risposte.add(new RispondeBean(questionID, code));
      }
      
    } finally {
      try {
        if (ps != null)
          ps.close();
      } finally {
        DriverManagerConnectionPool.releaseConnection(connection);
      }
    }
    
    return risposte;
  }
}
