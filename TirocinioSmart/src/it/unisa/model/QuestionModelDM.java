package it.unisa.model;

import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import it.unisa.database.DriverManagerConnectionPool;
import it.unisa.sql.QuestionSQL;

public class QuestionModelDM implements BeansModel {

  @Override
  public AbstractBean doRetrieveByKey(int code) throws SQLException {
    QuestionBean question = new QuestionBean();
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    
    try {
      
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(QuestionSQL.doRetrieveByKey);
      ps.setInt(1, code);
      rs = ps.executeQuery();
      
      if (rs.next()) {
        question.setId(code);
        question.setDescription(rs.getString("description"));
        question.setMax_answers(rs.getShort("max_answers"));
        question.setMax_chooses(rs.getShort("max_chooses"));
        question.setQuestion(rs.getString("question"));
      }
      
    } finally {
      try {
        if (ps != null)
          ps.close();
      } finally {
        DriverManagerConnectionPool.releaseConnection(connection);
      }
    }
    
    return question;
  }

  @Override
  public Collection<AbstractBean> doRetrieveAll(String order) throws SQLException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void doSave(AbstractBean product) throws SQLException {
    QuestionBean question = (QuestionBean) product;
    Connection connection = null;
    PreparedStatement ps = null;
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(QuestionSQL.doSave);
      ps.setInt(1, question.getId());
      ps.setInt(2, question.getMax_answers());
      ps.setInt(3, question.getMax_chooses());
      ps.setString(4, question.getQuestion());
      ps.setString(5, question.getDescription());
      
      ps.executeUpdate();
      
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
    int rowCount = -1;
    
    try {
      
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(QuestionSQL.doDelete);
      ps.setInt(1, code);
      
      rowCount = ps.executeUpdate();
      
    } finally {
      try {
        if (ps != null)
          ps.close();
      } finally {
        DriverManagerConnectionPool.releaseConnection(connection);
      }
    }
    
    if (rowCount >= 1)
      return true;
    return false;
  }
  
  public Collection<QuestionBean> retreiveQuestionsByQuestionario(QuestionarioBean questionario) throws SQLException {
    Collection<QuestionBean> questions = new ArrayList<QuestionBean>();
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(QuestionSQL.retreiveQuestionsByQuestionario);
      ps.setInt(1, questionario.getId());
      rs = ps.executeQuery();
      
      while (rs.next()) {
        int id = rs.getInt("id");
        int max_chooses = rs.getInt("max_chooses");
        int max_answers = rs.getInt("max_answers");
        String question = rs.getString("question");
        String description = rs.getString("description");
        QuestionBean quest = new QuestionBean(id, question, description, max_chooses, max_answers);
        questions.add(quest);
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
