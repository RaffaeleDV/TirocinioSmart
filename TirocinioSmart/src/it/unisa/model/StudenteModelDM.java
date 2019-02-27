package it.unisa.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.util.ArrayList;
import it.unisa.database.DriverManagerConnectionPool;
import it.unisa.sql.StudenteSQL;

public class StudenteModelDM implements BeansModel {

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
        Logger.getGlobal().log(Level.INFO, "Oggetto StudenteBean con l' id specificato non trovato");
      
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
      ps.setString(3, studenteBean.getPass());
      ps.setString(4, studenteBean.getNome());
      ps.setString(5, studenteBean.getCfu());
      ps.setString(6, studenteBean.getOccupazione());
      ps.setInt(7, studenteBean.getTutorAccID());
      ps.setInt(8, studenteBean.getTutorAzID());
      ps.setInt(9, studenteBean.getProgettoFormativoID());
      ps.setInt(10, studenteBean.getRegistroID());
      
      if (!(ps.executeUpdate() > 0)) {
        Logger.getGlobal().log(Level.INFO, "Oggetto StudenteBean non memorizzato");
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
        Logger.getGlobal().log(Level.INFO, "Oggetto StudenteBean non rimosso");
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
    StudenteBean studenteBean = (StudenteBean) product;
    boolean updated = false;
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(StudenteSQL.DO_UPDATE);
      
      ps.setString(1, studenteBean.getMatricola());
      ps.setString(2, studenteBean.getNome());
      ps.setString(3, studenteBean.getCfu());
      ps.setString(4, studenteBean.getPass());
      ps.setString(5, studenteBean.getOccupazione());
      ps.setInt(6, studenteBean.getTutorAccID());
      ps.setInt(7, studenteBean.getTutorAzID());
      ps.setInt(8, studenteBean.getProgettoFormativoID());
      ps.setInt(9, studenteBean.getRegistroID());
      ps.setInt(10, studenteBean.getID());
      
      if (ps.executeUpdate() > 0) {
        updated = true;
      } else {
        Logger.getGlobal().log(Level.INFO, "Oggetto StudenteBean non aggiornato");
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
  
  public AbstractBean doRetrieveByMatricola(String matricola) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    StudenteBean studenteBean = null;
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(StudenteSQL.DO_RETRIEVE_BY_MATRICOLA);
      
      ps.setString(1, matricola);
      
      rs = ps.executeQuery();
      
      if (rs.next()) {
        int id = rs.getInt("id");
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
        Logger.getGlobal().log(Level.INFO, "Oggetto StudenteBean con la matricola specificata non trovato");
      
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
  
  public AbstractBean doRetrieveByNome(String nome) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    StudenteBean studenteBean = null;
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(StudenteSQL.DO_RETRIEVE_BY_NOME);
      
      ps.setString(1, nome);
      
      rs = ps.executeQuery();
      
      if (rs.next()) {
        int id = rs.getInt("id");
        String matricola = rs.getString("matricola");
        String pass = rs.getString("pass");
        String cfu = rs.getString("cfu");
        String occupazione = rs.getString("occupazione");
        int tutorAccID = rs.getInt("tutorAccID");
        int tutorAzID = rs.getInt("tutorAzID");
        int progettoFormativoID = rs.getInt("progettoFormativoID");
        int registroID = rs.getInt("registroID");
        
        studenteBean = new StudenteBean(id, matricola, pass, nome, cfu, occupazione, tutorAccID, tutorAzID, progettoFormativoID, registroID);        
      } else
        Logger.getGlobal().log(Level.INFO, "Oggetto StudenteBean non trovato con il nome specificato");
      
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
  
  public Collection<AbstractBean> doRetrieveByCfu(String cfu) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    Collection<AbstractBean> studenti = null;
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(StudenteSQL.DO_RETRIEVE_BY_CFU);
      
      ps.setString(1, cfu);
      
      rs = ps.executeQuery();
      
      studenti = new ArrayList<AbstractBean>();
      
      while (rs.next()) {
        int id = rs.getInt("id");
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
        int progettoFormativoID = rs.getInt("progettoFormativoID");
        int registroID = rs.getInt("registroID");
        
        studenteBean = new StudenteBean(id, matricola, pass, nome, cfu, occupazione, tutorAccID, tutorAzID, progettoFormativoID, registroID);
      } else {
        Logger.getGlobal().log(Level.INFO, "Oggetto StudenteBean non trovato con il progetto formativo specificato");
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
}
