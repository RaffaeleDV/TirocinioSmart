package it.unisa.model;

import java.util.ArrayList;
import java.util.logging.Level;
import java.sql.SQLException;
import java.util.Collection;
import java.util.logging.Logger;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import it.unisa.database.DriverManagerConnectionPool;
import it.unisa.sql.AnswerSQL;

public class AnswerModelDM implements BeansModel {

  @Override
  public AbstractBean doRetrieveByKey(int code) throws SQLException {
    return null;
  }

  @Override
  public Collection<AbstractBean> doRetrieveAll(String order) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    Collection<AbstractBean> answers = null;
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(AnswerSQL.DO_RETRIEVE_ALL);
      
      rs = ps.executeQuery();
      
      answers = new ArrayList<AbstractBean>();
      
      while (rs.next()) {
        int questionID = rs.getInt("questionID");
        int studenteID = rs.getInt("studenteID");
        int chooseID = rs.getInt("chooseID");
        Date answerDate = rs.getDate("answerDate");
        
        answers.add(new AnswerBean(questionID, studenteID, chooseID, answerDate));
      }
      
    } finally {
      try {
        if (ps != null)
          ps.close();
      } finally {
        DriverManagerConnectionPool.releaseConnection(connection);
      }
    }
    
    return answers;
  }

  @Override
  public void doSave(AbstractBean product) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    AnswerBean answer = (AnswerBean) product;
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(AnswerSQL.DO_SAVE);
      
      ps.setInt(1, answer.getQuestionID());
      ps.setInt(2, answer.getStudenteID());
      ps.setInt(3, answer.getChooseID());
      ps.setDate(4, answer.getAnswerDate());
      
      if (!(ps.executeUpdate() > 0)) {
        Logger.getGlobal().log(Level.INFO, "Oggetto AnswerBean non e stato memorizzato");
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
  
  public boolean doUpdate(AbstractBean product) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    AnswerBean answerBean = (AnswerBean) product;
    boolean updated = false;
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(AnswerSQL.DO_UPDATE);

      ps.setInt(1, answerBean.getQuestionID());
      ps.setInt(2, answerBean.getStudenteID());
      ps.setInt(3, answerBean.getChooseID());
      ps.setDate(4, answerBean.getAnswerDate());
      
      if (ps.executeUpdate() > 0) {
        updated = true;
      } else {
        Logger.getGlobal().log(Level.INFO, "Oggetto AnswerBean non aggiornato");
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
  
  public Collection<AbstractBean> doRetrieveByQuestion(int code) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    Collection<AbstractBean> answers = null;
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(AnswerSQL.DO_RETRIEVE_BY_QUESTION);
      
      ps.setInt(1, code);
      
      rs = ps.executeQuery();
      
      answers = new ArrayList<AbstractBean>();
      
      while (rs.next()) {
        int questionID = rs.getInt("questionID");
        int studenteID = rs.getInt("studenteID");
        int chooseID = rs.getInt("chooseID");
        Date answerDate = rs.getDate("answerDate");
        
        answers.add(new AnswerBean(questionID, studenteID, chooseID, answerDate));
      }
      
    } finally {
      try {
        if (ps != null)
          ps.close();
      } finally {
        DriverManagerConnectionPool.releaseConnection(connection);
      }
    }
    
    return answers;
  }
  
  public Collection<AbstractBean> doRetrieveByStudente(int code) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    Collection<AbstractBean> answers = null;
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(AnswerSQL.DO_RETRIEVE_BY_STUDENTE);
      
      ps.setInt(1, code);
      
      rs = ps.executeQuery();
      
      answers = new ArrayList<AbstractBean>();
      
      while (rs.next()) {
        int questionID = rs.getInt("questionID");
        int chooseID = rs.getInt("chooseID");
        Date answerDate = rs.getDate("answerDate");
        
        answers.add(new AnswerBean(questionID, code, chooseID, answerDate));
      }
      
    } finally {
      try {
        if (ps != null)
          ps.close();
      } finally {
        DriverManagerConnectionPool.releaseConnection(connection);
      }
    }
    
    return answers;
  }
  
  public Collection<AbstractBean> doRetrieveByChoose(int code) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    Collection<AbstractBean> answers = null;
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(AnswerSQL.DO_RETRIEVE_BY_CHOOSE);
      
      ps.setInt(1, code);
      
      rs = ps.executeQuery();
      
      answers = new ArrayList<AbstractBean>();
      
      while (rs.next()) {
        int questionID = rs.getInt("questionID");
        int studenteID = rs.getInt("studenteID");
        Date answerDate = rs.getDate("answerDate");
        
        answers.add(new AnswerBean(questionID, studenteID, code, answerDate));
      }
      
    } finally {
      try {
        if (ps != null)
          ps.close();
      } finally {
        DriverManagerConnectionPool.releaseConnection(connection);
      }
    }
    
    return answers;
  }
  
  public Collection<AbstractBean> doRetrieveByAnswerDateBetween(Date startDate, Date endDate) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    Collection<AbstractBean> answers = null;
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(AnswerSQL.DO_RETRIEVE_BY_ANSWER_DATE_BETWEEN);
      
      ps.setDate(1, startDate);
      ps.setDate(2, endDate);
      
      rs = ps.executeQuery();
      
      answers = new ArrayList<AbstractBean>();
      
      while (rs.next()) {
        int questionID = rs.getInt("questionID");
        int studenteID = rs.getInt("studenteID");
        int chooseID = rs.getInt("chooseID");
        Date answerDate = rs.getDate("answerDate");
        
        answers.add(new AnswerBean(questionID, studenteID, chooseID, answerDate));
      }
      
    } finally {
      try {
        if (ps != null)
          ps.close();
      } finally {
        DriverManagerConnectionPool.getConnection();
      }
    }
    
    return answers;
  }
}
