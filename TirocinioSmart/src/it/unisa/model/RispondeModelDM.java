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
import it.unisa.sql.RispondeSQL;

public class RispondeModelDM implements BeansModel {

  public static final RispondeModelDM INSTANCE = new RispondeModelDM();

  private RispondeModelDM() {
  
  }

  @Override
  public AbstractBean doRetrieveByKey(int code) throws SQLException {
    return null;
  }

  public AbstractBean doRetrieveByKey(int codeQuestion, int codeChoose) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    RispondeBean rispondeBean = null;
    
    try {
      connection = (Connection) DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(RispondeSQL.DO_RETRIEVE_BY_KEY);
      
      ps.setInt(1, codeQuestion);
      ps.setInt(2, codeChoose);
      
      rs = ps.executeQuery();
      if (rs.next()) {
        rispondeBean = new RispondeBean(codeQuestion, codeChoose);
      } else {
        Logger.getGlobal().log(Level.SEVERE, "Oggetto RispondeBean Non Trovato.");
      }
    } finally {
      try {
        if (ps != null)
          ps.close();
      } finally {
        DriverManagerConnectionPool.releaseConnection(connection);
      }
    }
    return rispondeBean;
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
        Logger.getGlobal().log(Level.SEVERE, "Oggetto RispondeBean Non Memorizzato.");
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
  
  public boolean doDelete(int codeQuestion, int codeChoose) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    boolean deleted = false;
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(RispondeSQL.DO_DELETE);
      
      ps.setInt(1, codeQuestion);
      ps.setInt(2, codeChoose);
      
      if (!(ps.executeUpdate() > 0)) {
        Logger.getGlobal().log(Level.SEVERE, "Oggetto RispondeBean Non Rimosso.");
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
  
  public boolean doUpdate(AbstractBean abstractBean, int codeQuestion, int codeChoose) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    RispondeBean rispondeBean = (RispondeBean) abstractBean;
    boolean deleted = false;
    
    try {
      connection = (Connection) DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(RispondeSQL.DO_UPDATE);
      
      ps.setInt(1, rispondeBean.getQuestionID());
      ps.setInt(2, rispondeBean.getChooseID());
      ps.setInt(3, codeQuestion);
      ps.setInt(4, codeChoose);
    
      if (ps.executeUpdate() > 0) {
        deleted = true;
      } else {
        Logger.getGlobal().log(Level.SEVERE, "Oggetto RispondeBean Non Rimosso.");
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
