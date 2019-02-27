package it.unisa.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Connection;
import it.unisa.database.DriverManagerConnectionPool;
import it.unisa.sql.QuestionSQL;
import it.unisa.sql.QuestionarioSQL;

public class QuestionarioModelDM implements BeansModel {

  @Override
  public AbstractBean doRetrieveByKey(int code) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    QuestionarioBean questionarioBean = null;
    
    try {
      connection = (Connection) DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(QuestionarioSQL.DO_RETRIEVE_BY_KEY);
      
      ps.setInt(1, code);
      
      rs = ps.executeQuery();
      
      if (rs.next()) {
        int id = rs.getInt("id");
        int questions = rs.getInt("questions");
        int nstudenti = rs.getInt("nstudenti");
        String nome = rs.getString("nome");
        String description = rs.getString("description");
        String tematica = rs.getString("tematica");
        
        questionarioBean = new QuestionarioBean(id, questions, nstudenti, nome, description, tematica);
      } else {
        Logger.getGlobal().log(Level.INFO, "Oggetto QuestionarioBean non trovato con l' id specificato");
      }
      
    } finally {
      try {
        if (ps != null)
          ps.close();
      } finally {
        DriverManagerConnectionPool.releaseConnection(connection);
      }
    }
    
    return questionarioBean; 
  }

  @Override
  public Collection<AbstractBean> doRetrieveAll(String order) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    Collection<AbstractBean> questionaries = null;
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(QuestionarioSQL.DO_RETRIEVE_ALL);
      
      rs = ps.executeQuery();
      
      questionaries = new ArrayList<AbstractBean>();
      
      while (rs.next()) {
        int id = rs.getInt("id");
        int questions = rs.getInt("questions");
        int nstudenti = rs.getInt("nstudenti");
        String nome = rs.getString("nome");
        String description = rs.getString("description");
        String tematica = rs.getString("tematica");
        
        questionaries.add(new QuestionarioBean(id, questions, nstudenti, nome, description, tematica));
      }
      
    } finally {
      try {
        if (ps != null)
          ps.close();
      } finally {
        DriverManagerConnectionPool.releaseConnection(connection);
      }
    }
    
    return questionaries;
  }

  @Override
  public void doSave(AbstractBean product) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    QuestionarioBean questionario = (QuestionarioBean) product;
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(QuestionarioSQL.DO_SAVE);
      
      ps.setInt(1, questionario.getID());
      ps.setInt(2, questionario.getQuestions());
      ps.setInt(3, questionario.getNstudenti());
      ps.setString(4, questionario.getNome());
      ps.setString(5, questionario.getDescription());
      ps.setString(6, questionario.getTematica());
      
      if (!(ps.executeUpdate() > 0)) {
        Logger.getGlobal().log(Level.INFO, "Oggetto QuestionarioBean non memorizzato");
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
      ps = connection.prepareStatement(QuestionarioSQL.DO_DELETE);
      
      ps.setInt(1, code);
      
      if (ps.executeUpdate() > 0) {
        deleted = true;
      } else {
        Logger.getGlobal().log(Level.INFO, "Oggetto QuestionarioBean non rimosso");
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
    QuestionarioBean questionarioBean = (QuestionarioBean) product;
    boolean updated = false;
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(QuestionarioSQL.DO_UPDATE);
      
      ps.setInt(1, questionarioBean.getQuestions());
      ps.setInt(2, questionarioBean.getNstudenti());
      ps.setString(3, questionarioBean.getNome());
      ps.setString(4, questionarioBean.getDescription());
      ps.setString(5, questionarioBean.getTematica());
      ps.setInt(6, questionarioBean.getID());
      
      if (ps.executeUpdate() > 0) {
        updated = true;
      } else {
        Logger.getGlobal().log(Level.INFO, "Oggetto QuestionarioBean non aggiornato");
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
  
  public AbstractBean doRetrieveByNome(String text) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    QuestionarioBean questionarioBean = null;
        
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(QuestionarioSQL.DO_RETRIEVE_BY_NOME);
      
      ps.setString(1, text);
      
      rs = ps.executeQuery();
      
      if (rs.next()) {
        int id = rs.getInt("id");
        int questions = rs.getInt("questions");
        int nstudenti = rs.getInt("nstudenti");
        String nome = rs.getString("nome");
        String description = rs.getString("description");
        String tematica = rs.getString("tematica");
        
        questionarioBean = new QuestionarioBean(id, questions, nstudenti, nome, description, tematica);
      } else {
        Logger.getGlobal().log(Level.INFO, "Nessun Questionario Trovato Con Il Nome Specificato");
      }
      
    } finally {
      try {
        if (ps != null)
          ps.close();
      } finally {
        DriverManagerConnectionPool.releaseConnection(connection);
      }
    }
    
    return questionarioBean;
  }
  
  public void incrementNstudenti(AbstractBean product) throws SQLException {
    QuestionarioBean questionarioBean = (QuestionarioBean) product;
    
    synchronized (questionarioBean) {
      questionarioBean.setNstudenti(questionarioBean.getNstudenti() + 1);
    }
    
    if (!doUpdate(questionarioBean)) {
      Logger.getGlobal().log(Level.INFO, "Numero studenti del questionario non incrementato");
    }
    
  }
  
  public void decrementNstudenti(AbstractBean product) throws SQLException {
    QuestionarioBean questionarioBean = (QuestionarioBean) product;
    
    synchronized (questionarioBean) {
      questionarioBean.setNstudenti(questionarioBean.getNstudenti());
    }
    
    if (!doUpdate(questionarioBean)) {
      Logger.getGlobal().log(Level.INFO, "Numero studenti del questionario non decrementato");
    }
    
  }
  
  public void updateNstudenti(int idQuestionario, int nStudenti) throws SQLException {
    QuestionarioBean questionarioBean = null;
    
    try {
      questionarioBean = (QuestionarioBean) doRetrieveByKey(idQuestionario);
    } catch(SQLException e) {
      Logger.getGlobal().log(Level.SEVERE, e.getMessage());
    }
    
    synchronized (questionarioBean) {
      questionarioBean.setNstudenti(nStudenti);
    }
    
    if (!doUpdate(questionarioBean)) {
      Logger.getGlobal().log(Level.INFO, "Numeri studenti del questionario aggiornato");
    }
    
  }
}
