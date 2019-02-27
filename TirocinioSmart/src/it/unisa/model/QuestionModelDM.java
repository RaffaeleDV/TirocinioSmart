package it.unisa.model;

import java.util.ArrayList;
import java.util.logging.Level;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.logging.Logger;
import it.unisa.database.DriverManagerConnectionPool;
import it.unisa.sql.QuestionSQL;

public class QuestionModelDM implements BeansModel {

  @Override
  public AbstractBean doRetrieveByKey(int code) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    QuestionBean questionBean = null;
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(QuestionSQL.DO_RETRIEVE_BY_KEY);
      
      ps.setInt(1, code);
      
      rs = ps.executeQuery();
      
      if (rs.next()) {
        int maxChooses = rs.getInt("maxChooses");
        int maxAnswers = rs.getInt("maxAnswers");
        String question = rs.getString("question");
        String description = rs.getString("description");
        
        questionBean = new QuestionBean(code, maxChooses, maxAnswers, question, description);
      }
      
    } finally {
      try {
        if (ps != null)
          ps.close();
      } finally {
        DriverManagerConnectionPool.releaseConnection(connection);
      }
    }
    
    return questionBean;
  }

  @Override
  public Collection<AbstractBean> doRetrieveAll(String order) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    Collection<AbstractBean> questions = null;
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(QuestionSQL.DO_RETRIEVE_ALL);
      
      rs = ps.executeQuery();
      
      questions = new ArrayList<AbstractBean>();
      
      while (rs.next()) {
        int id = rs.getInt("id");
        int maxChooses = rs.getInt("maxChooses");
        int maxAnswers = rs.getInt("maxAnswers");
        String question = rs.getString("question");
        String description = rs.getString("description");
        
        questions.add(new QuestionBean(id, maxChooses, maxAnswers, question, description));
      }
      
    } finally {
      try {
        if (ps != null)
          ps.close();
      } finally {
        DriverManagerConnectionPool.releaseConnection(connection);
      }
    }
    
    return questions;
  }

  @Override
  public void doSave(AbstractBean product) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    QuestionBean questionBean = (QuestionBean) product;
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(QuestionSQL.DO_SAVE);
      
      ps.setInt(1, questionBean.getID());
      ps.setInt(2, questionBean.getMaxChooses());
      ps.setInt(3, questionBean.getMaxAnswers());
      ps.setString(4, questionBean.getQuestion());
      ps.setString(5, questionBean.getDescription());
      
      if (!(ps.executeUpdate() > 0)) {
        Logger.getGlobal().log(Level.INFO, "Oggetto QuestionBean non memorizzato");
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
      ps = connection.prepareStatement(QuestionSQL.DO_DELETE);
     
      ps.setInt(1, code);
      
      if (ps.executeUpdate() > 0) {
        deleted = true;
      } else {
        Logger.getGlobal().log(Level.INFO, "Oggetto QuestionBean non rimosso");
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
    QuestionBean questionBean = (QuestionBean) product;
    boolean updated = false;
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(QuestionSQL.DO_UPDATE);

      ps.setInt(1, questionBean.getMaxChooses());
      ps.setInt(2, questionBean.getMaxAnswers());
      ps.setString(3, questionBean.getQuestion());
      ps.setString(4, questionBean.getDescription());
      ps.setInt(5, questionBean.getID());
      
      if (ps.executeUpdate() > 0) {
        updated = true;
      } else {
        Logger.getGlobal().log(Level.INFO, "Oggetto QuestionBean non aggiornato");
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
    Collection<AbstractBean> questions = null;
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(QuestionSQL.DO_RETRIEVE_BY_DESCRIPTION);
      
      ps.setString(1, text);
      
      rs = ps.executeQuery();
      
      questions = new ArrayList<AbstractBean>();
      
      while (rs.next()) {
        int id = rs.getInt("id");
        int maxChooses = rs.getInt("maxChooses");
        int maxAnswers = rs.getInt("maxAnswers");
        String question = rs.getString("question");
        String description = rs.getString("description");
        
        questions.add(new QuestionBean(id, maxChooses, maxAnswers, question, description));
      }
      
    } finally {
      try {
        if (ps != null)
          ps.close();
      } finally {
        DriverManagerConnectionPool.releaseConnection(connection);
      }
    }
    
    return questions;
  }
  
  public Collection<AbstractBean> doRetrieveByQuestionario(int code) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    Collection<AbstractBean> questions = null;
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(QuestionSQL.DO_RETRIEVE_BY_QUESTIONARIO);
      
      ps.setInt(1, code);
      
      rs = ps.executeQuery();
      
      questions = new ArrayList<AbstractBean>();
      
      while (rs.next()) {
        int id = rs.getInt("id");
        int maxChooses = rs.getInt("maxChooses");
        int maxAnswers = rs.getInt("maxAnswers");
        String question = rs.getString("question");
        String description = rs.getString("description");
        
        questions.add(new QuestionBean(id, maxChooses, maxAnswers, question, description));
      }
      
    } finally {
      try {
        if (ps != null)
          ps.close();
      } finally {
        DriverManagerConnectionPool.releaseConnection(connection);
      }
    }
    
    return questions;
  }
}
