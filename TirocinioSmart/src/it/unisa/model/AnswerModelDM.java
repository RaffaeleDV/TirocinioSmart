package it.unisa.model;

import java.util.ArrayList;
import java.sql.SQLException;
import java.util.Collection;
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
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void doSave(AbstractBean product) throws SQLException {
    AnswerBean answer = (AnswerBean) product;
    Connection connection = null;
    PreparedStatement ps = null;
    
    try {
      
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(AnswerSQL.doSave);
      ps.setInt(2, answer.getChoose());
      ps.setInt(3, answer.getQuest());
      ps.setInt(4, answer.getUtente());
      ps.setDate(5, answer.getA_date());
      
      ps.executeQuery();
      
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
  
  public Collection<AnswerBean> retreiveByQuestion(AnswerBean answer) throws SQLException {
    Collection<AnswerBean> answers = new ArrayList<AnswerBean>();
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    
    try {
      
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(AnswerSQL.retreiveByQuestion);
      ps.setInt(1, answer.getQuest());
      rs = ps.executeQuery();
      
      while (rs.next()) {
        int utente = rs.getInt("utente");
        int choose = rs.getInt("choose");
        Date a_date = rs.getDate("a_date");
        AnswerBean a = new AnswerBean(answer.getQuest(), utente, choose, a_date);
        answers.add(a);
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
  
  public Collection<AnswerBean> retreiveByUtente(AnswerBean answer) throws SQLException {
    Collection<AnswerBean> answers = new ArrayList<AnswerBean>();
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    
    try {
      
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(AnswerSQL.retreiveByQuestion);
      ps.setInt(1, answer.getUtente());
      rs = ps.executeQuery();
      
      while (rs.next()) {
        int quest = rs.getInt("quest");
        int choose = rs.getInt("choose");
        Date a_date = rs.getDate("a_date");
        AnswerBean a = new AnswerBean(quest, answer.getUtente(), choose, a_date);
        answers.add(a);
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
  
  public Collection<AnswerBean> retreiveByChoose(AnswerBean answer) throws SQLException {
    Collection<AnswerBean> answers = new ArrayList<AnswerBean>();
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    
    try {
      
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(AnswerSQL.retreiveByQuestion);
      ps.setInt(1, answer.getUtente());
      rs = ps.executeQuery();
      
      while (rs.next()) {
        int quest = rs.getInt("quest");
        int utente = rs.getInt("utente");
        Date a_date = rs.getDate("a_date");
        AnswerBean a = new AnswerBean(quest, utente, answer.getChoose(), a_date);
        answers.add(a);
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
  
  
}
