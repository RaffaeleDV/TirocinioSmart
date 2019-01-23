package it.unisa.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.sql.Connection;
import it.unisa.database.DriverManagerConnectionPool;
import it.unisa.sql.QuestionSQL;
import it.unisa.sql.QuestionarioSQL;

public class QuestionarioModelDM implements BeansModel {

  @Override
  public AbstractBean doRetrieveByKey(int code) throws SQLException {
    int quests = -1;
    int nutenti = -1;
    String nome = null;
    String description = null;
    String tematica = null;
    QuestionarioBean questionario = new QuestionarioBean();
    Connection connection = null;
    PreparedStatement ps = null;
    
    try {
      
      connection = (Connection) DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(QuestionarioSQL.doRetrieveByKey);
      ps.setInt(1, code);
      ResultSet rs = ps.executeQuery();
      
      if (rs.next()) {
        questionario.setId(code);
        questionario.setQuests(rs.getInt("quests"));
        questionario.setNutenti(rs.getInt("nutenti"));
        questionario.setNome(rs.getString("nome"));
        questionario.setDescription(rs.getString("description"));
        questionario.setTematica(rs.getString("tematica"));
      }
      
    } finally {
      try {
        if (ps != null)
          ps.close();
      } finally {
        DriverManagerConnectionPool.releaseConnection(connection);
      }
    }
    
    return questionario; 
  }

  @Override
  public Collection<AbstractBean> doRetrieveAll(String order) throws SQLException {
    Collection<AbstractBean> allQuestionari = new ArrayList<AbstractBean>();
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    
    try {
      
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(QuestionarioSQL.doRetrieveAll);
      rs = ps.executeQuery();
      while (rs.next()) {
        int id = rs.getInt("id");
        int nutenti = rs.getInt("nutenti");
        int quests = rs.getInt("quests");
        String nome = rs.getString("nome");
        String description = rs.getString("description");
        String tematica = rs.getString("tematica");
        QuestionarioBean questionario = new QuestionarioBean(id, quests, nutenti, description, nome, tematica);
        allQuestionari.add(questionario);
      }
      
    } finally {
      try {
        if (ps != null)
          ps.close();
      } finally {
        DriverManagerConnectionPool.releaseConnection(connection);
      }
    }
    
    return allQuestionari;
  }

  @Override
  public void doSave(AbstractBean product) throws SQLException {
    QuestionarioBean questionario = (QuestionarioBean) product;
    Connection connection = null;
    PreparedStatement ps = null;
    
    try {
      
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(QuestionarioSQL.doSave);
      
      ps.setInt(1, questionario.getId());
      ps.setInt(2, questionario.getNutenti());
      ps.setInt(3, questionario.getQuests());
      ps.setString(4, questionario.getNome());
      ps.setString(5, questionario.getDescription());
      ps.setString(6, questionario.getTematica());
      
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
      ps = connection.prepareStatement(QuestionarioSQL.doDelete);
      
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

  public QuestionarioBean loadQuestionarioByNome(String questionarioNome) throws SQLException {
    QuestionarioBean questionario = null;
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
        
    try {
      
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(QuestionarioSQL.loadQuestionarioByNome);
      ps.setString(1, questionarioNome);
      rs = ps.executeQuery();
      
      if (rs.next()) {
        int id = rs.getInt("id");
        int quests = rs.getInt("quests");
        int nutenti = rs.getInt("nutenti");
        String nome = rs.getString("nome");
        String description = rs.getString("description");
        String tematica = rs.getString("tematica");
        questionario = new QuestionarioBean(id, quests, nutenti, description, nome, tematica);
      }
      
    } finally {
      try {
        if (ps != null)
          ps.close();
      } finally {
        DriverManagerConnectionPool.releaseConnection(connection);
      }
    }
    
    return questionario;
  }
}
