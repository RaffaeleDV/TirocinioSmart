package it.unisa.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import it.unisa.database.DriverManagerConnectionPool;
import it.unisa.sql.IncludeSQL;
import java.util.ArrayList;

public class IncludeModelDM implements BeansModel {

  @Override
  public AbstractBean doRetrieveByKey(int code) throws SQLException {
    return null;
  }

  @Override
  public Collection<AbstractBean> doRetrieveAll(String order) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    Collection<AbstractBean> include = null;
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(IncludeSQL.DO_RETRIEVE_ALL);
      
      rs = ps.executeQuery();
      
      include = new ArrayList<AbstractBean>();
      
      while (rs.next()) {
        int questionarioID = rs.getInt("questionarioID");
        int questionID = rs.getInt("questionID");
        
        include.add(new IncludeBean(questionarioID, questionID));
      }
      
    } finally {
      try {
        if (ps != null)
          ps.close();
      } finally {
        DriverManagerConnectionPool.releaseConnection(connection);
      }
    }
    
    return include;
  }

  @Override
  public void doSave(AbstractBean product) throws SQLException {
     Connection connection = null;
     PreparedStatement ps = null;
     IncludeBean include = (IncludeBean) product;
     
     try {
       connection = DriverManagerConnectionPool.getConnection();
       ps = connection.prepareStatement(IncludeSQL.DO_SAVE);
       
       ps.setInt(1, include.getQuestionarioID());
       ps.setInt(2, include.getQuestionID());
       
       if (!(ps.executeUpdate() > 0)) {
         Logger.getGlobal().log(Level.INFO, "Oggetto IncludeBean non memorizzato");
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
  
  public Collection<AbstractBean> doRetrieveByQuestionario(int code) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    Collection<AbstractBean> includes = null;
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(IncludeSQL.DO_RETRIEVE_BY_QUESTIONARIO);
      
      ps.setInt(1, code);
      
      rs = ps.executeQuery();
      
      includes = new ArrayList<AbstractBean>();
      
      while (rs.next()) {
        int questionID = rs.getInt("questionID");
        
        includes.add(new IncludeBean(code, questionID));
      }
      
    } finally {
      try {
        if (ps != null)
          ps.close();
      } finally {
        DriverManagerConnectionPool.releaseConnection(connection);
      }
    }
    
    return includes;
  }

  public Collection<AbstractBean> doRetrieveByQuestion(int code) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    Collection<AbstractBean> includes = null;
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(IncludeSQL.DO_RETRIEVE_BY_QUESTION);
      
      ps.setInt(1, code);
      
      rs = ps.executeQuery();
      
      includes = new ArrayList<AbstractBean>();
      
      while (rs.next()) {
        int questionarioID = rs.getInt("questionarioID");
        
        includes.add(new IncludeBean(questionarioID, code));
      }
      
    } finally {
      try {
        if (ps != null)
          ps.close();
      } finally {
        DriverManagerConnectionPool.releaseConnection(connection);
      }
    }
    
    return includes;
  }
  
  public boolean doDeleteByQuestionario(int code) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    boolean deleted = false;
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(IncludeSQL.DO_DELETE_BY_QUESTIONARIO);
      
      ps.setInt(1, code);
      
      if (ps.executeUpdate() > 0) {
        deleted = true;
      } else {
        Logger.getGlobal().log(Level.INFO, "Oggetto IncludeBean non rimosso con il questionario specificato");
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
  
  public boolean doDeleteByQuestion(int code) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    boolean deleted = false;
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(IncludeSQL.DO_DELETE_BY_QUESTION);
      
      ps.setInt(1, code);
      
      if (ps.executeUpdate() > 0) {
        deleted = true;
      } else {
        Logger.getGlobal().log(Level.INFO, "Oggetto IncludeBean non rimosso con il question specificato");
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
}
