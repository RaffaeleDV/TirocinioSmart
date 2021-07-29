package it.unisa.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

import it.unisa.database.DriverManagerConnectionPool;
import it.unisa.sql.CompilaSQL;

public class CompilaModelDM implements BeansModel {

  public static final CompilaModelDM INSTANCE = new CompilaModelDM();

  private CompilaModelDM() {

  }

  @Override
  public AbstractBean doRetrieveByKey(int code) throws SQLException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Collection<AbstractBean> doRetrieveAll(String order) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    Collection<AbstractBean> compilato = null;
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(CompilaSQL.DO_RETRIEVE_ALL);
      
      rs = ps.executeQuery();
      
      compilato = new ArrayList<AbstractBean>();
      
      while (rs.next()) {
        int utenteID = rs.getInt("utenteID");
        String tipoUtente = rs.getString("tipoUtente");
        int questionarioID = rs.getInt("questionarioID");
        Date dataCompilazione = rs.getDate("dataCompilazione");
        
        compilato.add(new CompilaBean(utenteID, tipoUtente, questionarioID, dataCompilazione));
      }
      
    } finally {
      try {
        if (ps != null)
          ps.close();
      } finally {
        DriverManagerConnectionPool.getConnection();
      }
    }
    return compilato;
  }

  @Override
  public boolean doDelete(int code) throws SQLException {
    return false;
  }
  
  public boolean doDelete(AbstractBean utenteBean, int codeQuestionario) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    boolean deleted = false;

    try {
      connection = DriverManagerConnectionPool.getConnection();
      if (utenteBean.getClass().getName().equals(StudenteBean.class.getName())) {
        StudenteBean studenteBean = (StudenteBean) utenteBean;
        ps = connection.prepareStatement(CompilaSQL.DO_DELETE);
        ps.setInt(1, studenteBean.getID());
      } else if (utenteBean.getClass().getName().equals(TutorBean.class.getName())) {
        TutorBean tutorBean = (TutorBean) utenteBean;
        ps = connection.prepareStatement(CompilaSQL.DO_DELETE);
        ps.setInt(1, tutorBean.getID());
      } else if (utenteBean.getClass().getName().equals(UfficioBean.class.getName())) {
        UfficioBean ufficioBean = (UfficioBean) utenteBean;
        ps = connection.prepareStatement(CompilaSQL.DO_DELETE);
        ps.setInt(1, ufficioBean.getID());
      } else {
        Logger.getGlobal().log(Level.SEVERE, "Istanza Di UtenteBean Non Valida.");
      }
      
      ps.setInt(2, codeQuestionario);
      if (!(ps.executeUpdate() > 0)) {
        Logger.getGlobal().log(Level.SEVERE, "Oggetto CompilaBean Non Rimosso.");
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
  
  @Override
  public void doSave(AbstractBean product) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    CompilaBean compilaBean = (CompilaBean) product;
    
    try {
      
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(CompilaSQL.DO_SAVE);
      
      ps.setInt(1, compilaBean.getUtenteID());
      ps.setString(2, compilaBean.getTipoUtente());
      ps.setInt(3, compilaBean.getQuestionarioID());
      ps.setDate(4, compilaBean.getDataCompilazione());
      
      if (!(ps.executeUpdate() > 0)) {
        Logger.getGlobal().log(Level.SEVERE, "Oggetto CompilaBean Non Memorizzato.");
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
  
  public boolean doUpdate(int codeUtente, int codeQuestionario) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    boolean updated = false;
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(CompilaSQL.DO_UPDATE);
      
      ps.setInt(1, codeUtente);
      ps.setInt(2, codeQuestionario);
      
      if (ps.executeUpdate() > 0) {
        updated = true;
      } else {
        Logger.getGlobal().log(Level.SEVERE, "Oggetto CompilaBean Non Aggiornato.");
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
  
  public AbstractBean doRetrieveByKey(int codeUtente, int codeQuestionario) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    CompilaBean compilaBean = null;
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(CompilaSQL.DO_RETRIEVE_BY_KEY);
      
      ps.setInt(1, codeUtente);
      ps.setInt(2, codeQuestionario);
      
      rs = ps.executeQuery();
      
      if (rs.next()) {
        String tipoUtente = rs.getString("tipoUtente");
        Date dataCompilazione = rs.getDate("dataCompilazione");
        compilaBean = new CompilaBean(codeUtente, tipoUtente, codeQuestionario, dataCompilazione);
      } else {
        Logger.getGlobal().log(Level.SEVERE, "Oggetto CompilaBean Non Trovato.");
      }
    } finally {
      try {
        if (ps != null)
          ps.close();
      } finally {
        DriverManagerConnectionPool.releaseConnection(connection);
      }
    }
    return compilaBean;
  }
  
  public Collection<AbstractBean> doRetrieveByUtente(AbstractBean utenteBean) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    Collection<AbstractBean> compilato = null;
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      if (utenteBean.getClass().getName().equals(StudenteBean.class.getName())) {
        StudenteBean studenteBean = (StudenteBean) utenteBean;
        ps = connection.prepareStatement(CompilaSQL.DO_RETRIEVE_BY_STUDENTE);
        ps.setInt(1, studenteBean.getID());
      } else if (utenteBean.getClass().getName().equals(TutorBean.class.getName())) {
        TutorBean tutorBean = (TutorBean) utenteBean;
        ps = connection.prepareStatement(CompilaSQL.DO_RETRIEVE_BY_TUTOR);
        ps.setInt(1, tutorBean.getID());
      } else if (utenteBean.getClass().getName().equals(UfficioBean.class.getName())) {
        UfficioBean ufficioBean = (UfficioBean) utenteBean;
        ps = connection.prepareStatement(CompilaSQL.DO_RETRIEVE_BY_UFFICIO);
        ps.setInt(1, ufficioBean.getID());
      }
      
      rs = ps.executeQuery();
      
      compilato = new ArrayList<AbstractBean>();
      
      while (rs.next()) {
        int utenteID = rs.getInt("utenteID");
        String tipoUtente = rs.getString("tipoUtente");
        int questionarioID = rs.getInt("questionarioID");
        Date dataCompilazione = rs.getDate("dataCompilazione");
        
        compilato.add(new CompilaBean(utenteID, tipoUtente, questionarioID, dataCompilazione));
      }
      
    } finally {
      try {
        if (ps != null)
          ps.close();
      } finally {
        DriverManagerConnectionPool.releaseConnection(connection);
      }
    }
    return compilato;
  }
  
  public Collection<AbstractBean> doRetrieveByQuestionario(int code) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    Collection<AbstractBean> compilato = null;
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(CompilaSQL.DO_RETRIEVE_BY_QUESTIONARIO);
      
      ps.setInt(1, code);
      
      rs = ps.executeQuery();
      
      compilato = new ArrayList<AbstractBean>();
      
      while (rs.next()) {
        int utenteID = rs.getInt("utenteID");
        String tipoUtente = rs.getString("tipoUtente");
        int questionarioID = rs.getInt("questionarioID");
        Date dataCompilazione = rs.getDate("dataCompilazione");
        
        compilato.add(new CompilaBean(utenteID, tipoUtente, questionarioID, dataCompilazione));
      }
      
    } finally {
      try {
        if (ps != null)
          ps.close();
      } finally {
        DriverManagerConnectionPool.releaseConnection(connection);
      }
    }
    return compilato;
  }
  
  public Collection<AbstractBean> doRetrieveByDataCompilazioneBetween(Date startDate, Date endDate) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    Collection<AbstractBean> compilato = null;
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(CompilaSQL.DO_RETRIEVE_BY_DATA_COMPILAZIONE_BETWEEN);
      
      ps.setDate(1, startDate);
      ps.setDate(2, endDate);
      
      rs = ps.executeQuery();
      
      compilato = new ArrayList<AbstractBean>();
      
      while (rs.next()) {
        int utenteID = rs.getInt("utenteID");
        String tipoUtente = rs.getString("tipoUtente");
        int questionarioID = rs.getInt("questionarioID");
        Date dataCompilazione = rs.getDate("dataCompilazione");
        
        compilato.add(new CompilaBean(utenteID, tipoUtente, questionarioID, dataCompilazione));
      }
      
    } finally {
      try {
        if (ps != null)
          ps.close();
      } finally {
        DriverManagerConnectionPool.releaseConnection(connection);
      }
    }
    return compilato;
  }
  
  public boolean isQuestionarioCompilato(AbstractBean productUtente, AbstractBean productQuestionario) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    StudenteBean studenteBean = null;
    TutorBean tutorBean = null;
    UfficioBean ufficioBean = null;
    QuestionarioBean questionarioBean = null;
    boolean compilato = false;
    
    if (productUtente != null &&
        productQuestionario != null &&
        productQuestionario.getClass().getName().equals(QuestionarioBean.class.getName())) {
      questionarioBean = (QuestionarioBean) productQuestionario;
    } else {
      return false;
    }
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(CompilaSQL.IS_QUESTIONARIO_COMPILATO);
      
      if (productUtente.getClass().getName().equals(StudenteBean.class.getName())) {
        studenteBean = (StudenteBean) productUtente;
        ps.setInt(1, studenteBean.getID());
      } else if (productUtente.getClass().getName().equals(TutorBean.class.getName())) {
        tutorBean = (TutorBean) productUtente;
        ps.setInt(1, tutorBean.getID());
      } else if(productUtente.getClass().getName().equals(UfficioBean.class.getName())) {
        ufficioBean = (UfficioBean) productUtente;
        ps.setInt(1, ufficioBean.getID());
      }
      ps.setInt(2, questionarioBean.getID());
      
      rs = ps.executeQuery();
      
      if (rs.next()) {
        compilato = true;
      }
    } finally {
      try {
        if (ps != null)
          ps.close();
      } finally {
        DriverManagerConnectionPool.releaseConnection(connection);
      }
    }
    return compilato;
  }
}
