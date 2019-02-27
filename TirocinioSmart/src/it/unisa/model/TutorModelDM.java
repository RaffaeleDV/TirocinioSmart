package it.unisa.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.logging.Logger;
import it.unisa.database.DriverManagerConnectionPool;
import it.unisa.sql.StudenteSQL;
import it.unisa.sql.TutorSQL;
import java.util.logging.Level;
import java.util.ArrayList;

public class TutorModelDM implements BeansModel {

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
        int convenzioneID = rs.getInt("convenzioneID");
        
        tutorBean = new TutorBean(code, convenzioneID, email, nome, pass, tipo);
      } else {
        Logger.getGlobal().log(Level.INFO, "Tutor con l' id specificato non trovato");
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
        int convenzioneID = rs.getInt("convenzioneID");
        
        tutors.add(new TutorBean(id, convenzioneID, email, nome, pass, tipo));
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
      ps.setInt(2, tutorBean.getConvenzioneID());
      ps.setString(3, tutorBean.getEmail());
      ps.setString(4, tutorBean.getNome());
      ps.setString(5, tutorBean.getPass());
      ps.setString(6, tutorBean.getTipo());
      
      if (!(ps.executeUpdate() > 0)) {
        Logger.getGlobal().log(Level.INFO, "Oggetto TutorBean non memorizzato");
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
        Logger.getGlobal().log(Level.INFO, "Oggetto TutorBean non rimosso");
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
    TutorBean tutorBean = (TutorBean) product;
    boolean updated = false;
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(TutorSQL.DO_UPDATE);

      ps.setInt(1, tutorBean.getConvenzioneID());
      ps.setString(2, tutorBean.getEmail());
      ps.setString(3, tutorBean.getNome());
      ps.setString(4, tutorBean.getPass());
      ps.setString(5, tutorBean.getTipo());
      ps.setInt(6, tutorBean.getID());
      
      if (ps.executeUpdate() > 0) {
        updated = true;
      } else {
        Logger.getGlobal().log(Level.INFO, "Oggetto TirocinioBean non aggiornato");
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
  
  public AbstractBean doRetrieveTutorByEmail(String email) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    TutorBean tutorBean = null;
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(TutorSQL.DO_RETRIEVE_TUTORS_BY_EMAIL);
      
      ps.setString(1, email);
      
      rs = ps.executeQuery();
      
      if (rs.next()) {
        int id = rs.getInt("id");
        String nome = rs.getString("nome");
        String pass = rs.getString("pass");
        String tipo = rs.getString("tipo");
        int convenzioneID = rs.getInt("convenzioneID");
        
        tutorBean = new TutorBean(id, convenzioneID, email, nome, pass, tipo);
      } else {
        Logger.getGlobal().log(Level.INFO, "Nessun tutor trovato con quella email");
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
  
  public AbstractBean doRetrieveTutorByNome(String nome) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    TutorBean tutorBean = null;
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(TutorSQL.DO_RETRIEVE_TUTORS_BY_NOME);
      
      ps.setString(1, nome);
      
      rs = ps.executeQuery();
      
      
      if (rs.next()) {
        int id = rs.getInt("id");
        String email = rs.getString("email");
        String pass = rs.getString("pass");
        String tipo = rs.getString("tipo");
        int convenzioneID = rs.getInt("convenzioneID");
        
        tutorBean = new TutorBean(id, convenzioneID, email, nome, pass, tipo);
      } else {
        Logger.getGlobal().log(Level.INFO, "Nessun Tutor trovato con il nome specificato");
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
  
  public Collection<AbstractBean> doRetrieveTutorByTipo(String tipo) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    Collection<AbstractBean> tutors = null;
   
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(TutorSQL.DO_RETRIEVE_TUTORS_BY_TIPO);
      
      ps.setString(1, tipo);
      
      rs = ps.executeQuery();
      
      tutors = new ArrayList<AbstractBean>();
      
      while (rs.next()) {
        int id = rs.getInt("id");
        String email = rs.getString("email");
        String nome = rs.getString("nome");
        String pass = rs.getString("pass");
        int convenzioneID = rs.getInt("convenzioneID");
        
        tutors.add(new TutorBean(id, convenzioneID, email, nome, pass, tipo));
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
  
  public Collection<AbstractBean> doRetrieveTutorByConvenzione(int code) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    Collection<AbstractBean> tutors = null;
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(TutorSQL.DO_RETRIEVE_TUTORS_BY_CONVENZIONE);
      
      ps.setInt(1, code);
      
      rs = ps.executeQuery();
      
      tutors = new ArrayList<AbstractBean>();
      
      while (rs.next()) {
        int id = rs.getInt("id");
        String email = rs.getString("email");
        String nome = rs.getString("nome");
        String pass = rs.getString("pass");
        String tipo = rs.getString("tipo");
        
        tutors.add(new TutorBean(id, code, email, nome, pass, tipo));
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
}
