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
import it.unisa.sql.IncludeSQL;

public class IncludeModelDM implements BeansModel {

  public static final IncludeModelDM INSTANCE = new IncludeModelDM();

  private IncludeModelDM() {

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
         Logger.getGlobal().log(Level.SEVERE, "Oggetto IncludeBean Non Memorizzato.");
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
  
  public AbstractBean doRetrieveByKey(int codeQuestionario, int codeQuestion) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    IncludeBean includeBean = null;
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(IncludeSQL.DO_RETRIEVE_BY_KEY);
      ps.setInt(1, codeQuestionario);
      ps.setInt(2, codeQuestion);
      
      rs = ps.executeQuery();
      
      if (rs.next()) {
        int questionarioID = rs.getInt("questionarioID");
        int questionID = rs.getInt("questionID");
        includeBean = new IncludeBean(questionarioID, questionID);
      }
    } finally {
      try {
        if (ps != null)
          ps.close();
      } finally {
        DriverManagerConnectionPool.releaseConnection(connection);
      }
    }
    return includeBean;
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
  
  public boolean doUpdate(IncludeBean includeBean,int codeQuestionario, int codeQuestion) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    boolean updated = false;
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(IncludeSQL.DO_UPDATE);
      
      ps.setInt(1, includeBean.getQuestionarioID());
      ps.setInt(2, includeBean.getQuestionID());
      ps.setInt(3, codeQuestionario);
      ps.setInt(4, codeQuestion);
      
      if (ps.executeUpdate() > 0) {
        updated = true;
      } else {
        Logger.getGlobal().log(Level.SEVERE, "Oggetto IncludeBean Non Aggiornato.");
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
  
  public boolean doDelete(int codeQuestionario, int codeQuestion) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    boolean deleted = false;
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(IncludeSQL.DO_DELETE);
      
      ps.setInt(1, codeQuestionario);
      ps.setInt(2, codeQuestion);
      
      if (ps.executeUpdate() > 0) {
        deleted = true;
      } else {
        Logger.getGlobal().log(Level.SEVERE, "Oggetto IncludeBean Non Rimosso.");
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
