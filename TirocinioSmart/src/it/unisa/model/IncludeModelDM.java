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
    return null;
  }

  @Override
  public void doSave(AbstractBean product) throws SQLException {
     IncludeBean include = (IncludeBean) product;
     Connection connection = null;
     PreparedStatement ps = null;
     
     try {
       
       connection = DriverManagerConnectionPool.getConnection();
       ps = connection.prepareStatement(IncludeSQL.doSave);
       ps.setInt(1, include.getQuestionario());
       ps.setInt(2, include.getQuestion());
       int rowCount = ps.executeUpdate();
       
       if (!(rowCount >= 1)) {
         Logger.getGlobal().log(Level.SEVERE, "IncludeBean not saved");
       }
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
  
  public Collection<IncludeBean> retreiveByQuestionario(IncludeBean include) throws SQLException {
    Collection<IncludeBean> includes = new ArrayList<IncludeBean>();
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    
    try {
      
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(IncludeSQL.retreiveByQuestionario);
      ps.setInt(1, include.getQuestionario());
      rs = ps.executeQuery();
      
      while (rs.next()) {
        int questionario = rs.getInt("questionario");
        int question = rs.getInt("question");
        IncludeBean i = new IncludeBean(questionario, question);
        includes.add(i);
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

  public Collection<IncludeBean> retreiveByQuestion(IncludeBean include) throws SQLException {
    Collection<IncludeBean> includes = new ArrayList<IncludeBean>();
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    
    try {
      
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(IncludeSQL.retreiveByQuestion);
      ps.setInt(1, include.getQuestion());
      rs = ps.executeQuery();
      
      while (rs.next()) {
        int questionario = rs.getInt("questionario");
        int question = rs.getInt("question");
        IncludeBean i = new IncludeBean(questionario, question);
        includes.add(i);
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
  
  public void deleteByQuestionario(IncludeBean include) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    
    try {
      
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(IncludeSQL.deleteByQuestionario);
      ps.setInt(1, include.getQuestionario());
      int rowCount = ps.executeUpdate();
      
      if (!(rowCount >= 1)) {
        Logger.getGlobal().log(Level.SEVERE, "IncludeBean not deleted");
      }
    } finally {
      try {
        if (ps != null)
          ps.close();
      } finally {
        DriverManagerConnectionPool.releaseConnection(connection);
      }
    }
  }
  
  public void deleteByQuestion(IncludeBean include) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    
    try {
      
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(IncludeSQL.deleteByQuestion);
      ps.setInt(1, include.getQuestion());
      int rowCount = ps.executeUpdate();
      
      if (!(rowCount >= 1)) {
        Logger.getGlobal().log(Level.SEVERE, "IncludeBean not deleted");
      }
    } finally {
      try {
        if (ps != null)
          ps.close();
      } finally {
        DriverManagerConnectionPool.releaseConnection(connection);
      }
    }
  }
}
