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

  public static final AnswerModelDM INSTANCE = new AnswerModelDM();

  private AnswerModelDM() {

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
    Collection<AbstractBean> answers = null;
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(AnswerSQL.DO_RETRIEVE_ALL);
      
      rs = ps.executeQuery();
      
      answers = new ArrayList<AbstractBean>();
      
      while (rs.next()) {
        int questionID = rs.getInt("questionID");
        int utenteID = rs.getInt("utenteID");
        int chooseID = rs.getInt("chooseID");
        String tipoUtente = rs.getString("tipoUtente");
        Date answerDate = rs.getDate("answerDate");
        
        answers.add(new AnswerBean(questionID, utenteID, chooseID, tipoUtente, answerDate));
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
    AnswerBean answerBean = (AnswerBean) product;
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(AnswerSQL.DO_SAVE);
      
      ps.setInt(1, answerBean.getQuestionID());
      ps.setInt(2, answerBean.getUtenteID());
      ps.setInt(3, answerBean.getChooseID());
      ps.setString(4, answerBean.getTipoUtente());
      ps.setDate(5, answerBean.getAnswerDate());
      
      if (!(ps.executeUpdate() > 0)) {
        Logger.getGlobal().log(Level.SEVERE, "Oggetto AnswerBean Non Memorizzato.");
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
    
  public boolean doDelete(int codeQuestionID, int codeUtenteID, int codeChooseID, AbstractBean utenteBean) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    boolean deleted = false;
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(AnswerSQL.DO_DELETE);

      ps.setInt(1, codeQuestionID);
      ps.setInt(2, codeUtenteID);
      ps.setInt(3, codeChooseID);
      
      if (!(ps.executeUpdate() > 0)) {
        Logger.getGlobal().log(Level.SEVERE, "Oggetto AnswerBean Non Rimosso.");
      } else {
        deleted = true;
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
  
  public boolean doUpdate(AbstractBean product, int codeUtente, int codeQuestion, int codeChoose) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    AnswerBean answerBean = (AnswerBean) product;
    boolean updated = false;
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(AnswerSQL.DO_UPDATE);

      ps.setInt(1, answerBean.getQuestionID());
      ps.setInt(2, answerBean.getUtenteID());
      ps.setInt(3, answerBean.getChooseID());
      ps.setString(4, answerBean.getTipoUtente());
      ps.setDate(5, answerBean.getAnswerDate());
      ps.setInt(6, codeQuestion);
      ps.setInt(7, codeUtente);
      ps.setInt(8, codeChoose);
      
      if (ps.executeUpdate() > 0) {
        updated = true;
      } else {
        Logger.getGlobal().log(Level.SEVERE, "Oggetto AnswerBean Non Aggiornato.");
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
  
  public AbstractBean doRetrieveByKey(int codeQuestion, int codeUtente, int codeChoose) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    AnswerBean answerBean = null;
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(AnswerSQL.DO_RETRIEVE_BY_KEY);
      ps.setInt(1, codeUtente);
      ps.setInt(2, codeQuestion);
      ps.setInt(3, codeChoose);
      
      rs = ps.executeQuery();
      if (rs.next()) {
        String tipoUtente = rs.getString("tipoUtente");
        Date answerDate = rs.getDate("answerDate");
        answerBean = new AnswerBean(codeQuestion, codeUtente, codeChoose, tipoUtente, answerDate);
      } else {
        Logger.getGlobal().log(Level.SEVERE, "Oggetto AnswerBean Non Trovato.");
      }
    } finally {
      try {
        if (ps != null)
          ps.close();
      } finally {
        DriverManagerConnectionPool.releaseConnection(connection);
      }
    }
    return answerBean;
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
        int utenteID = rs.getInt("utenteID");
        int chooseID = rs.getInt("chooseID");
        String tipoUtente = rs.getString("tipoUtente");
        Date answerDate = rs.getDate("answerDate");
        
        answers.add(new AnswerBean(questionID, utenteID, chooseID, tipoUtente, answerDate));
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
  
  public Collection<AbstractBean> doRetrieveByUtente(AbstractBean utenteBean) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    String tipoUtente = utenteBean.getClass().getName();
    int idUtente = -1;
    Collection<AbstractBean> answers = null;
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(AnswerSQL.DO_RETRIEVE_BY_UTENTE);
      if (tipoUtente.equals(StudenteBean.class.getName())) {
        StudenteBean studenteBean = (StudenteBean) utenteBean;
        idUtente = studenteBean.getID();
        ps.setInt(1, idUtente);
      } else if (tipoUtente.equals(TutorBean.class.getName())) {
        TutorBean tutorBean = (TutorBean) utenteBean;
        idUtente = tutorBean.getID();
        ps.setInt(1, idUtente);
      } else if (tipoUtente.equals(UfficioBean.class.getName())) {
        UfficioBean ufficioBean = (UfficioBean) utenteBean;
        idUtente = ufficioBean.getID();
        ps.setInt(1, idUtente);
      } else {
        Logger.getGlobal().log(Level.SEVERE, "Istanza Di UtenteBean Non Valida.");
      }
      
      rs = ps.executeQuery();
      
      answers = new ArrayList<AbstractBean>();
      
      while (rs.next()) {
        int questionID = rs.getInt("questionID");
        int chooseID = rs.getInt("chooseID");
        Date answerDate = rs.getDate("answerDate");
        answers.add(new AnswerBean(questionID, idUtente, chooseID, tipoUtente, answerDate));
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
    Collection<AbstractBean> answersBean = null;
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(AnswerSQL.DO_RETRIEVE_BY_CHOOSE);
      
      ps.setInt(1, code);
      
      rs = ps.executeQuery();
      
      answersBean = new ArrayList<AbstractBean>();
      
      while (rs.next()) {
        int questionID = rs.getInt("questionID");
        int utenteID = rs.getInt("utenteID");
        String tipoUtente = rs.getString("tipoUtente");
        Date answerDate = rs.getDate("answerDate");
        
        answersBean.add(new AnswerBean(questionID, utenteID, code, tipoUtente, answerDate));
      }
      
    } finally {
      try {
        if (ps != null)
          ps.close();
      } finally {
        DriverManagerConnectionPool.releaseConnection(connection);
      }
    }
    return answersBean;
  }
  
  public Collection<AbstractBean> doRetrieveByAnswerDateBetween(Date startDate, Date endDate) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    Collection<AbstractBean> answersBean = null;
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(AnswerSQL.DO_RETRIEVE_BY_ANSWER_DATE_BETWEEN);
      
      ps.setDate(1, startDate);
      ps.setDate(2, endDate);
      
      rs = ps.executeQuery();
      
      answersBean = new ArrayList<AbstractBean>();
      
      while (rs.next()) {
        int questionID = rs.getInt("questionID");
        int utenteID = rs.getInt("utenteID");
        int chooseID = rs.getInt("chooseID");
        String tipoUtente = rs.getString("tipoUtente");
        Date answerDate = rs.getDate("answerDate");
        
        answersBean.add(new AnswerBean(questionID, utenteID, chooseID, tipoUtente, answerDate));
      }
      
    } finally {
      try {
        if (ps != null)
          ps.close();
      } finally {
        DriverManagerConnectionPool.getConnection();
      }
    }
    return answersBean;
  }
}
