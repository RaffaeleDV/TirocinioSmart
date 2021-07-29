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
import it.unisa.sql.TutorSQL;

public class TutorModelDM implements BeansModel {

  public static final TutorModelDM INSTANCE = new TutorModelDM();

  private TutorModelDM() {

  }

  @Override
  public AbstractBean doRetrieveByKey(int code) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    TutorBean tutorBean = null;
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(TutorSQL.DO_RETRIEVE_BY_KEY);
      
      ps.setInt(1, code);
      
      rs = ps.executeQuery();
      
      if (rs.next()) {
        String email = rs.getString("email");
        String nome = rs.getString("nome");
        String pass = rs.getString("pass");
        String tipo = rs.getString("tipo");
        
        tutorBean = new TutorBean(code, email, nome, pass, tipo);
      } else {
        Logger.getGlobal().log(Level.SEVERE, "Oggetto TutorBean Non Trovato.");
      }
      
    } finally {
      try {
        if (ps != null)
          ps.close();
      } finally {
        DriverManagerConnectionPool.releaseConnection(connection);
      }
    }
    
    return tutorBean;
  }

  @Override
  public Collection<AbstractBean> doRetrieveAll(String order) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    Collection<AbstractBean> tutors = null;
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(TutorSQL.DO_RETRIEVE_ALL);
      
      rs = ps.executeQuery();
      
      tutors = new ArrayList<AbstractBean>();
      
      while (rs.next()) {
        int id = rs.getInt("id");
        String email = rs.getString("email");
        String nome = rs.getString("nome");
        String pass = rs.getString("pass");
        String tipo = rs.getString("tipo");
        
        tutors.add(new TutorBean(id, email, nome, pass, tipo));
      }
      
    } finally {
      try {
        if (ps != null)
          ps.close();
      } finally {
        DriverManagerConnectionPool.releaseConnection(connection);
      }
    }
    return tutors;
  }

  @Override
  public void doSave(AbstractBean product) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    TutorBean tutorBean = null;
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(TutorSQL.DO_SAVE);
      
      tutorBean = (TutorBean) product;
      
      ps.setInt(1, tutorBean.getID());
      ps.setString(2, tutorBean.getEmail());
      ps.setString(3, tutorBean.getNome());
      ps.setString(4, tutorBean.getPass());
      ps.setString(5, tutorBean.getTipo());
      
      if (!(ps.executeUpdate() > 0)) {
        Logger.getGlobal().log(Level.SEVERE, "Oggetto TutorBean Non Memorizzato.");
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
      ps = connection.prepareStatement(TutorSQL.DO_DELETE);
      
      ps.setInt(1, code);
      
      if (ps.executeUpdate() > 0) {
        deleted = true;
      } else {
        Logger.getGlobal().log(Level.SEVERE, "Oggetto TutorBean Non Rimosso.");
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
  
  public boolean doUpdate(AbstractBean product, int tutorID) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    TutorBean tutorBean = (TutorBean) product;
    boolean updated = false;
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(TutorSQL.DO_UPDATE);

      ps.setInt(1, tutorBean.getID());
      ps.setString(2, tutorBean.getEmail());
      ps.setString(3, tutorBean.getNome());
      ps.setString(4, tutorBean.getPass());
      ps.setString(5, tutorBean.getTipo());
      ps.setInt(6, tutorID);
      
      if (ps.executeUpdate() > 0) {
        updated = true;
      } else {
        Logger.getGlobal().log(Level.SEVERE, "Oggetto TirocinioBean Non Aggiornato.");
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
  
  public AbstractBean doRetrieveTutorByEmail(String text) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    TutorBean tutorBean = null;
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(TutorSQL.DO_RETRIEVE_BY_EMAIL);
      
      ps.setString(1, text);
      
      rs = ps.executeQuery();
      
      if (rs.next()) {
        int id = rs.getInt("id");
        String email = rs.getString("email");
        String nome = rs.getString("nome");
        String pass = rs.getString("pass");
        String tipo = rs.getString("tipo");
        
        tutorBean = new TutorBean(id, email, nome, pass, tipo);
      } else {
        Logger.getGlobal().log(Level.SEVERE, "Oggetto TutorBean Non Trovato.");
      }
      
    } finally {
      try {
        if (ps != null)
          ps.close();
      } finally {
        DriverManagerConnectionPool.releaseConnection(connection);
      }
    }
    return tutorBean;
  }
  
  public AbstractBean doRetrieveTutorByNome(String text) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    TutorBean tutorBean = null;
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(TutorSQL.DO_RETRIEVE_BY_NOME);
      
      ps.setString(1, text);
      
      rs = ps.executeQuery();
      
      
      if (rs.next()) {
        int id = rs.getInt("id");
        String email = rs.getString("email");
        String nome = rs.getString("nome");
        String pass = rs.getString("pass");
        String tipo = rs.getString("tipo");
        
        tutorBean = new TutorBean(id, email, nome, pass, tipo);
      } else {
        Logger.getGlobal().log(Level.SEVERE, "Oggetto TutorBean Non Trovato.");
      }
      
    } finally {
      try {
        if (ps != null)
          ps.close();
      } finally {
        DriverManagerConnectionPool.releaseConnection(connection);
      }
    }
    return tutorBean;
  }
  
  public Collection<AbstractBean> doRetrieveTutorByTipo(String text) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    Collection<AbstractBean> tutors = null;
   
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(TutorSQL.DO_RETRIEVE_BY_TIPO);
      
      ps.setString(1, text);
      
      rs = ps.executeQuery();
      
      tutors = new ArrayList<AbstractBean>();
      
      while (rs.next()) {
        int id = rs.getInt("id");
        String email = rs.getString("email");
        String nome = rs.getString("nome");
        String pass = rs.getString("pass");
        String tipo = rs.getString("tipo");
        
        tutors.add(new TutorBean(id, email, nome, pass, tipo));
      }
      
    } finally {
      try {
        if (ps != null)
          ps.close();
      } finally {
        DriverManagerConnectionPool.releaseConnection(connection);
      }
    }
    return tutors;
  }
  
  public Collection<AbstractBean> doRetrieveQuestionari(AbstractBean abstractBean) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    TutorBean tutorBean = null;
    Collection<AbstractBean> questionari = null;
    
    if (abstractBean != null &&
        abstractBean.getClass().getName().equals(TutorBean.class.getName())) {
      tutorBean = (TutorBean) abstractBean;
    }
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(TutorSQL.DO_RETRIEVE_QUESTIONARI_BY_UTENTE);
      
      ps.setInt(1, tutorBean.getID());
      
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
}
