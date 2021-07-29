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
import it.unisa.sql.QuestionarioSQL;

public class QuestionarioModelDM implements BeansModel {

  public static final QuestionarioModelDM INSTANCE = new QuestionarioModelDM();  

  private QuestionarioModelDM() {

  }

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
        Logger.getGlobal().log(Level.SEVERE, "Oggetto QuestionarioBean Non Trovato.");
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
      ps.setString(3, questionario.getNome());
      ps.setString(4, questionario.getDescription());
      ps.setString(5, questionario.getTematica());
      ps.setInt(6, questionario.getNstudenti());
      
      if (!(ps.executeUpdate() > 0)) {
        Logger.getGlobal().log(Level.SEVERE, "Oggetto QuestionarioBean Non Memorizzato.");
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
        Logger.getGlobal().log(Level.SEVERE, "Oggetto QuestionarioBean Non Rimosso.");
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

  public boolean doUpdate(AbstractBean product, int codeQuestionario) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    QuestionarioBean questionarioBean = (QuestionarioBean) product;
    boolean updated = false;
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(QuestionarioSQL.DO_UPDATE);
      
      ps.setInt(1, questionarioBean.getID());
      ps.setInt(2, questionarioBean.getQuestions());
      ps.setInt(3, questionarioBean.getNstudenti());
      ps.setString(4, questionarioBean.getNome());
      ps.setString(5, questionarioBean.getDescription());
      ps.setString(6, questionarioBean.getTematica());
      ps.setInt(7, codeQuestionario);
      
      if (ps.executeUpdate() > 0) {
        updated = true;
      } else {
        Logger.getGlobal().log(Level.SEVERE, "Oggetto QuestionarioBean Non Aggiornato.");
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
  
  public Collection<AbstractBean> doRetrieveByTematica(String text) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    Collection<AbstractBean> questionari = null;
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(QuestionarioSQL.DO_RETRIEVE_BY_TEMATICA);
      
      ps.setString(1, text);
      
      questionari = new ArrayList<AbstractBean>();
      
      rs = ps.executeQuery();
      
      while (rs.next()) {
        int id = rs.getInt("id");
        int questions = rs.getInt("questions");
        int nstudenti = rs.getInt("nstudenti");
        String nome = rs.getString("nome");
        String description = rs.getString("description");
        String tematica = rs.getString("tematica");
        questionari.add(new QuestionarioBean(id, questions, nstudenti, nome, description, tematica));
      }
    } finally {
      try {
        if (ps != null)
          ps.close();
      } finally {
        DriverManagerConnectionPool.releaseConnection(connection);
      }
    }
    return questionari;
  }
  
  public Collection<AbstractBean> doRetrieveByNStudenti(int starting, int ending) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    Collection<AbstractBean> questionari = null;
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(QuestionarioSQL.DO_RETRIEVE_BY_QUESTIONS);
      
      ps.setInt(1, starting);
      ps.setInt(2, ending);
      
      questionari = new ArrayList<AbstractBean>();
      
      rs = ps.executeQuery();
      while (rs.next()) {
        int id = rs.getInt("id");
        int questions = rs.getInt("questions");
        int nstudenti = rs.getInt("nstudenti");
        String nome = rs.getString("nome");
        String description = rs.getString("description");
        String tematica = rs.getString("tematica");
        questionari.add(new QuestionarioBean(id, questions, nstudenti, nome, description, tematica));
      }
    } finally {
      try {
        if (ps != null)
          ps.close();
      } finally {
        DriverManagerConnectionPool.releaseConnection(connection);
      }
    }
    return questionari;
  }
  
  public Collection<AbstractBean> doRetrieveByQuestions(int starting, int ending) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    Collection<AbstractBean> questionari = null;
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(QuestionarioSQL.DO_RETRIEVE_BY_QUESTIONS);
      
      ps.setInt(1, starting);
      ps.setInt(2, ending);
      
      questionari = new ArrayList<AbstractBean>();
      
      rs = ps.executeQuery();
      while (rs.next()) {
        int id = rs.getInt("id");
        int questions = rs.getInt("questions");
        int nstudenti = rs.getInt("nstudenti");
        String nome = rs.getString("nome");
        String description = rs.getString("description");
        String tematica = rs.getString("tematica");
        questionari.add(new QuestionarioBean(id, questions, nstudenti, nome, description, tematica));
      }
    } finally {
      try {
        if (ps != null)
          ps.close();
      } finally {
        DriverManagerConnectionPool.releaseConnection(connection);
      }
    }
    return questionari;
  }
  
  public Collection<AbstractBean> doRetrieveByDescription(String text) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    Collection<AbstractBean> questionari = null;
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(QuestionarioSQL.DO_RETRIEVE_BY_DESCRIPTION);
      
      ps.setString(1, text);
      
      questionari = new ArrayList<AbstractBean>();
      
      rs = ps.executeQuery();
      
      while (rs.next()) {
        int id = rs.getInt("id");
        int questions = rs.getInt("questions");
        int nstudenti = rs.getInt("nstudenti");
        String nome = rs.getString("nome");
        String description = rs.getString("description");
        String tematica = rs.getString("tematica");
        
        questionari.add(new QuestionarioBean(id, questions, nstudenti, nome, description, tematica));
      }
    } finally {
      try {
        if (ps != null)
          ps.close();
      } finally {
        DriverManagerConnectionPool.releaseConnection(connection);
      }
    }
    return questionari;
  }
  
  public Collection<AbstractBean> doRetrieveByNome(String text) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    Collection<AbstractBean> questionari = null;
        
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(QuestionarioSQL.DO_RETRIEVE_BY_NOME);
      
      ps.setString(1, text);
      
      questionari = new ArrayList<AbstractBean>();
      
      rs = ps.executeQuery();
      
      while (rs.next()) {
        int id = rs.getInt("id");
        int questions = rs.getInt("questions");
        int nstudenti = rs.getInt("nstudenti");
        String nome = rs.getString("nome");
        String description = rs.getString("description");
        String tematica = rs.getString("tematica");
        
        questionari.add(new QuestionarioBean(id, questions, nstudenti, nome, description, tematica));
      }
      
    } finally {
      try {
        if (ps != null)
          ps.close();
      } finally {
        DriverManagerConnectionPool.releaseConnection(connection);
      }
    }
    return questionari;
  }
  
  public void incrementNstudenti(AbstractBean product) throws SQLException {
    QuestionarioBean questionarioBean = (QuestionarioBean) product;
    
    synchronized (questionarioBean) {
      questionarioBean.setNstudenti(questionarioBean.getNstudenti() + 1);
    }
    
    if (!doUpdate(questionarioBean, questionarioBean.getID())) {
      Logger.getGlobal().log(Level.SEVERE, "Incremento Numero Studenti Fallito.");
    }
    
  }
  
  public void decrementNstudenti(AbstractBean product) throws SQLException {
    QuestionarioBean questionarioBean = (QuestionarioBean) product;
    
    synchronized (questionarioBean) {
      questionarioBean.setNstudenti(questionarioBean.getNstudenti());
    }
    
    if (!doUpdate(questionarioBean, questionarioBean.getID())) {
      Logger.getGlobal().log(Level.SEVERE, "Decremento Numero Studenti Fallito.");
    }
    
  }
  
  public boolean updateNstudenti(int codeQuestionario, int nStudenti) throws SQLException {
    QuestionarioBean questionarioBean = null;
    
    try {
      questionarioBean = (QuestionarioBean) doRetrieveByKey(codeQuestionario);
    } catch(SQLException e) {
      Logger.getGlobal().log(Level.SEVERE, e.getMessage());
    }
    
    synchronized (questionarioBean) {
      questionarioBean.setNstudenti(nStudenti);
    }
    
    if (!doUpdate(questionarioBean, questionarioBean.getID())) {
      Logger.getGlobal().log(Level.SEVERE, "Aggiornamento Numero Studenti Fallito.");
      return false;
    }
    return true;
  }
}
