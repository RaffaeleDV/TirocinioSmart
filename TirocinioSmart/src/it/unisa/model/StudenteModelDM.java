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
import it.unisa.sql.StudenteSQL;

public class StudenteModelDM implements BeansModel {

  public static final StudenteModelDM INSTANCE = new StudenteModelDM();

  private StudenteModelDM() {

  }

  @Override
  public AbstractBean doRetrieveByKey(int code) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    StudenteBean studenteBean = null;
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(StudenteSQL.DO_RETRIEVE_BY_KEY);
      
      ps.setInt(1, code);
      
      rs = ps.executeQuery();
      
      if (rs.next()) {
        int id = rs.getInt("id");
        String matricola = rs.getString("matricola");
        String pass = rs.getString("pass");
        String nome = rs.getString("nome");
        String cfu = rs.getString("cfu");
        String occupazione = rs.getString("occupazione");
        int tutorAccID = rs.getInt("tutorAccID");
        int tutorAzID = rs.getInt("tutorAzID");
        int progettoFormativoID = rs.getInt("progettoFormativoID");
        int registroID = rs.getInt("registroID");
        
        studenteBean = new StudenteBean(id, matricola, pass, nome, cfu, occupazione, tutorAccID, tutorAzID, progettoFormativoID, registroID);
      } else 
        Logger.getGlobal().log(Level.SEVERE, "Oggetto StudenteBean Non Trovato.");
      
    } finally {
      try {
        if (ps != null)
          ps.close();
      } finally {
        DriverManagerConnectionPool.releaseConnection(connection);
      }
    }
    return studenteBean;
  }

  @Override
  public Collection<AbstractBean> doRetrieveAll(String order) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    Collection<AbstractBean> studenti = null;
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(StudenteSQL.DO_RETRIEVE_ALL);
      
      rs = ps.executeQuery();
      
      studenti = new ArrayList<AbstractBean>();
      
      while (rs.next()) {
        int id = rs.getInt("id");
        String matricola = rs.getString("matricola");
        String pass = rs.getString("pass");
        String nome = rs.getString("nome");
        String cfu = rs.getString("cfu");
        String occupazione = rs.getString("occupazione");
        int tutorAccID = rs.getInt("tutorAccID");
        int tutorAzID = rs.getInt("tutorAzID");
        int progettoFormativoID = rs.getInt("progettoFormativoID");
        int registroID = rs.getInt("registroID");
        
        studenti.add(new StudenteBean(id, matricola, pass, nome, cfu, occupazione, tutorAccID, tutorAzID, progettoFormativoID, registroID));
      }
      
    } finally {
      try {
        if (ps != null)
          ps.close();
      } finally {
        DriverManagerConnectionPool.releaseConnection(connection);
      }
    }
    return studenti;
  }

  @Override
  public void doSave(AbstractBean product) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    StudenteBean studenteBean = (StudenteBean) product;
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(StudenteSQL.DO_SAVE);
      
      ps.setInt(1, studenteBean.getID());
      ps.setString(2, studenteBean.getMatricola());
      ps.setString(3, studenteBean.getNome());
      ps.setString(4, studenteBean.getCfu());
      ps.setString(5, studenteBean.getPass());
      ps.setString(6, studenteBean.getOccupazione());
      ps.setInt(7, studenteBean.getTutorAccID());
      ps.setInt(8, studenteBean.getTutorAzID());
      ps.setInt(9, studenteBean.getProgettoFormativoID());
      ps.setInt(10, studenteBean.getRegistroID());
      
      if (!(ps.executeUpdate() > 0)) {
        Logger.getGlobal().log(Level.SEVERE, "Oggetto StudenteBean Non Memorizzato.");
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
      ps = connection.prepareStatement(StudenteSQL.DO_DELETE);
      
      ps.setInt(1, code);
      
      if (ps.executeUpdate() > 0) {
        deleted = true;
      } else {
        Logger.getGlobal().log(Level.SEVERE, "Oggetto StudenteBean Non Rimosso.");
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
  
  public boolean doUpdate(AbstractBean product, int studenteID) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    StudenteBean studenteBean = (StudenteBean) product;
    boolean updated = false;
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(StudenteSQL.DO_UPDATE);
      
      ps.setInt(1, studenteBean.getID());
      ps.setString(2, studenteBean.getMatricola());
      ps.setString(3, studenteBean.getNome());
      ps.setString(4, studenteBean.getCfu());
      ps.setString(5, studenteBean.getPass());
      ps.setString(6, studenteBean.getOccupazione());
      ps.setInt(7, studenteBean.getTutorAccID());
      ps.setInt(8, studenteBean.getTutorAzID());
      ps.setInt(9, studenteBean.getProgettoFormativoID());
      ps.setInt(10, studenteBean.getRegistroID());
      ps.setInt(11, studenteID);
      
      if (ps.executeUpdate() > 0) {
        updated = true;
      } else {
        Logger.getGlobal().log(Level.SEVERE, "Oggetto StudenteBean Non Aggiornato.");
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
  
  public AbstractBean doRetrieveByMatricola(String text) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    StudenteBean studenteBean = null;
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(StudenteSQL.DO_RETRIEVE_BY_MATRICOLA);
      
      ps.setString(1, text);
      
      rs = ps.executeQuery();
      
      if (rs.next()) {
        int id = rs.getInt("id");
        String matricola = rs.getString("matricola");
        String pass = rs.getString("pass");
        String nome = rs.getString("nome");
        String cfu = rs.getString("cfu");
        String occupazione = rs.getString("occupazione");
        int tutorAccID = rs.getInt("tutorAccID");
        int tutorAzID = rs.getInt("tutorAzID");
        int progettoFormativoID = rs.getInt("progettoFormativoID");
        int registroID = rs.getInt("registroID");
        
        studenteBean = new StudenteBean(id, matricola, nome, cfu, occupazione, pass, tutorAccID, tutorAzID, progettoFormativoID, registroID);
      } else
        Logger.getGlobal().log(Level.SEVERE, "Oggetto StudenteBean Non Trovato.");
      
    } finally {
      try {
        if (ps != null)
          ps.close();
      } finally {
        DriverManagerConnectionPool.releaseConnection(connection);
      }
    }
    return studenteBean;
  }
  
  public AbstractBean doRetrieveByNome(String text) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    StudenteBean studenteBean = null;
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(StudenteSQL.DO_RETRIEVE_BY_NOME);
      
      ps.setString(1, text);
      
      rs = ps.executeQuery();
      
      if (rs.next()) {
        int id = rs.getInt("id");
        String matricola = rs.getString("matricola");
        String nome = rs.getString("nome");
        String pass = rs.getString("pass");
        String cfu = rs.getString("cfu");
        String occupazione = rs.getString("occupazione");
        int tutorAccID = rs.getInt("tutorAccID");
        int tutorAzID = rs.getInt("tutorAzID");
        int progettoFormativoID = rs.getInt("progettoFormativoID");
        int registroID = rs.getInt("registroID");
        
        studenteBean = new StudenteBean(id, matricola, pass, nome, cfu, occupazione, tutorAccID, tutorAzID, progettoFormativoID, registroID);        
      } else
        Logger.getGlobal().log(Level.SEVERE, "Oggetto StudenteBean non trovato con il nome specificato");
      
    } finally {
      try {
        if (ps != null)
          ps.close();
      } finally {
        DriverManagerConnectionPool.releaseConnection(connection);
      }
    }
    return studenteBean;
  }
  
  public Collection<AbstractBean> doRetrieveByCfu(String text) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    Collection<AbstractBean> studenti = null;
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(StudenteSQL.DO_RETRIEVE_BY_CFU);
      
      ps.setString(1, text);
      
      rs = ps.executeQuery();
      
      studenti = new ArrayList<AbstractBean>();
      
      while (rs.next()) {
        int id = rs.getInt("id");
        String cfu = rs.getString("cfu");
        String matricola = rs.getString("matricola");
        String pass = rs.getString("pass");
        String nome = rs.getString("nome");
        String occupazione = rs.getString("occupazione");
        int tutorAccID = rs.getInt("tutorAccID");
        int tutorAzID = rs.getInt("tutorAzID");
        int progettoFormativoID = rs.getInt("progettoFormativoID");
        int registroID = rs.getInt("registroID");
        
        studenti.add(new StudenteBean(id, matricola, pass, nome, cfu, occupazione, tutorAccID, tutorAzID, progettoFormativoID, registroID));
      }
      
    } finally {
      try {
        if (ps != null)
          ps.close();
      } finally {
        DriverManagerConnectionPool.releaseConnection(connection);
      }
    }
    
    return studenti;
  }
  
  public Collection<AbstractBean> doRetrieveByTutorAcc(int code) throws SQLException {
    Connection connection = null;  
    PreparedStatement ps = null;
    ResultSet rs = null;
    Collection<AbstractBean> studenti = null;
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(StudenteSQL.DO_RETRIEVE_BY_TUTOR_ACC);
      
      ps.setInt(1, code);
      
      rs = ps.executeQuery();
      
      studenti = new ArrayList<AbstractBean>();
      
      while (rs.next()) {
        int id = rs.getInt("id");
        String matricola = rs.getString("matricola");
        String pass = rs.getString("pass");
        String nome = rs.getString("nome");
        String cfu = rs.getString("cfu");
        String occupazione = rs.getString("occupazione");
        int tutorAzID = rs.getInt("tutorAzID");
        int progettoFormativoID = rs.getInt("progettoFormativoID");
        int registroID = rs.getInt("registroID");
        
        studenti.add(new StudenteBean(id, matricola, nome, cfu, pass, occupazione, code, tutorAzID, progettoFormativoID, registroID));
      }
      
    } finally {
      try {
        if (ps != null)
          ps.close();
      } finally {
        DriverManagerConnectionPool.releaseConnection(connection);
      }
    }
    return studenti;
  }
  
  public Collection<AbstractBean> doRetrieveByTutorAz(int code) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    Collection<AbstractBean> studenti = null;
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(StudenteSQL.DO_RETRIEVE_BY_TUTOR_AZ);
      
      ps.setInt(1, code);
      
      rs = ps.executeQuery();
      
      studenti = new ArrayList<AbstractBean>();
      
      while (rs.next()) {
        int id = rs.getInt("id");
        String matricola = rs.getString("matricola");
        String pass = rs.getString("pass");
        String nome = rs.getString("nome");
        String cfu = rs.getString("cfu");
        String occupazione = rs.getString("occupazione");
        int tutorAccID = rs.getInt("tutorAccID");
        int progettoFormativoID = rs.getInt("progettoFormativoID");
        int registroID = rs.getInt("registroID");
        
        studenti.add(new StudenteBean(id, matricola, nome, cfu, pass, occupazione, tutorAccID, code, progettoFormativoID, registroID));
      }
      
    } finally {
      try {
        if (ps != null)
          ps.close();
      } finally {
        DriverManagerConnectionPool.releaseConnection(connection);
      }
    }
    return studenti;
  }
  
  public Collection<AbstractBean> doRetrieveByOccupazione(String text) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    Collection<AbstractBean> studenti = null;
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(StudenteSQL.DO_RETRIEVE_BY_OCCUPAZIONE);
      
      ps.setString(1, text);
      
      rs = ps.executeQuery();
      
      studenti = new ArrayList<AbstractBean>();
      
      while (rs.next()) {
        int id = rs.getInt("id");
        String matricola = rs.getString("matricola");
        String pass = rs.getString("pass");
        String nome = rs.getString("nome");
        String cfu = rs.getString("cfu");
        String occupazione = rs.getString("occupazione");
        int tutorAccID = rs.getInt("tutorAccID");
        int tutorAzID = rs.getInt("tutorAzID");
        int progettoFormativoID = rs.getInt("progettoFormativoID");
        int registroID = rs.getInt("registroID");
        
        studenti.add(new StudenteBean(id, matricola, pass, nome, cfu, occupazione, tutorAccID, tutorAzID, progettoFormativoID, registroID));
      }
      
    } finally {
      try {
        if (ps != null)
          ps.close();
      } finally {
        DriverManagerConnectionPool.releaseConnection(connection);
      }
    }
    return studenti;
  }
  
  public Collection<AbstractBean> doRetrieveByRegistro(int code) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    Collection<AbstractBean> studenti = null;
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(StudenteSQL.DO_RETRIEVE_BY_REGISTRO);
      
      ps.setInt(1, code);
      
      rs = ps.executeQuery();
      
      studenti = new ArrayList<AbstractBean>();
      
      while (rs.next()) {
        int id = rs.getInt("id");
        String matricola = rs.getString("matricola");
        String pass = rs.getString("pass");
        String nome = rs.getString("nome");
        String cfu = rs.getString("cfu");
        String occupazione = rs.getString("occupazione");
        int tutorAccID = rs.getInt("tutorAccID");
        int tutorAzID = rs.getInt("tutorAzID");
        int progettoFormativoID = rs.getInt("progettoFormativoID");
        
        studenti.add(new StudenteBean(id, matricola, pass, nome, cfu, occupazione, tutorAccID, tutorAzID, progettoFormativoID, code));
      }
      
    } finally {
      try {
        if (ps != null)
          ps.close();
      } finally {
        DriverManagerConnectionPool.releaseConnection(connection);
      }
    }
    return studenti;
  }
 
  public AbstractBean doRetrieveByProgettoFormativo(int code) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    StudenteBean studenteBean = null;
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(StudenteSQL.DO_RETRIEVE_BY_PROGETTO_FORMATIVO);
      
      ps.setInt(1, code);
      
      rs = ps.executeQuery();
      
      if (rs.next()) {
        int id = rs.getInt("id");
        String matricola = rs.getString("matricola");
        String nome = rs.getString("nome");
        String cfu = rs.getString("cfu");
        String pass = rs.getString("pass");
        String occupazione = rs.getString("occupazione");
        int tutorAccID = rs.getInt("tutorAccID");
        int tutorAzID = rs.getInt("tutorAzID");
        int registroID = rs.getInt("registroID");
        
        studenteBean = new StudenteBean(id, matricola, pass, nome, cfu, occupazione, tutorAccID, tutorAzID, code, registroID);
      } else {
        Logger.getGlobal().log(Level.SEVERE, "Oggetto StudenteBean Non Trovato.");
      }
      
    } finally {
      try {
        if (ps != null)
          ps.close();
      } finally {
        DriverManagerConnectionPool.releaseConnection(connection);
      }
    }
    return studenteBean;
  }
  
  public Collection<AbstractBean> doRetrieveQuestionari(AbstractBean abstractBean) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    StudenteBean studenteBean = null;
    Collection<AbstractBean> questionari = null;
    
    if (abstractBean != null &&
        abstractBean.getClass().getName().equals(StudenteBean.class.getName())) {
      studenteBean = (StudenteBean) abstractBean;
    }
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(StudenteSQL.DO_RETRIEVE_QUESTIONARI_BY_UTENTE);
      
      ps.setInt(1, studenteBean.getID());
      
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
  
  public AbstractBean doRetrieveRegistro(AbstractBean product) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    RegistroBean registroBean = null;
    StudenteBean studenteBean = null;
    
    if (product.getClass().getName().equals(StudenteBean.class.getName())) {
      studenteBean = (StudenteBean) product;
    }
    
    try {
      connection = (Connection) DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(StudenteSQL.DO_RETRIEVE_REGISTRO);
      
      ps.setInt(1, studenteBean.getRegistroID());
      
      rs = ps.executeQuery();
      if (rs.next()) {
        int id = rs.getInt("id");
        String nome = rs.getString("nome");
        String descrizione = rs.getString("descrizione");
        Date primaIstituzione = rs.getDate("primaIstituzione");
        Date ultimoAgg = rs.getDate("ultimoAgg");
        boolean consegna = rs.getBoolean("consegna");
        boolean confermaTutorAcc = rs.getBoolean("confermaTutorAcc");
        boolean confermaTutorAz = rs.getBoolean("confermaTutorAz");
        boolean confermaUff = rs.getBoolean("confermaUff");
        
        registroBean = new RegistroBean(id, nome, descrizione, primaIstituzione, ultimoAgg, consegna, confermaTutorAcc, confermaTutorAz, confermaUff);
      } else {
        Logger.getGlobal().log(Level.SEVERE, "Oggetto RegistroBean Non Trovato.");
      }
    } finally {
      try {
        if (ps != null)
          ps.close();
      } finally {
        DriverManagerConnectionPool.releaseConnection(connection);
      }
    }
    return registroBean;
  }
}
