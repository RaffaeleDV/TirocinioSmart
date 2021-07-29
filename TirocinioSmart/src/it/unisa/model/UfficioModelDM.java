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
import it.unisa.sql.UfficioSQL;

public class UfficioModelDM implements BeansModel {

  public static final UfficioModelDM INSTANCE = new UfficioModelDM();

  private UfficioModelDM() {

  }
  
  @Override
  public AbstractBean doRetrieveByKey(int code) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    UfficioBean ufficioBean = null;
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(UfficioSQL.DO_RETRIEVE_BY_KEY);
      
      ps.setInt(1, code);
      
      rs = ps.executeQuery();
      
      if (rs.next()) {
        int id = rs.getInt("id");
        int strutturaOspitanteID = rs.getInt("strutturaOspitanteID");
        String email = rs.getString("email");
        String nome = rs.getString("nome");
        String pass = rs.getString("pass");
        
        ufficioBean = new UfficioBean(id, strutturaOspitanteID, email, nome, pass);
      } else {
        Logger.getGlobal().log(Level.SEVERE, "Oggetto UfficioBean Non Trovato.");
      }
    } finally {
      try {
        if (ps != null)
          ps.close();
      } finally {
        DriverManagerConnectionPool.releaseConnection(connection);
      }
    }
    
    return ufficioBean;
  }

  @Override
  public Collection<AbstractBean> doRetrieveAll(String order) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    Collection<AbstractBean> uffici = null;
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(UfficioSQL.DO_RETRIEVE_ALL);
      
      rs = ps.executeQuery();
      
      uffici = new ArrayList<AbstractBean>();
      
      while (rs.next()) {
        int id = rs.getInt("id");
        int strutturaOspitanteID = rs.getInt("strutturaOspitanteID");
        String email = rs.getString("email");
        String nome = rs.getString("nome");
        String pass = rs.getString("pass");
        
        uffici.add(new UfficioBean(id, strutturaOspitanteID, email, nome, pass));
      }
      
    } finally {
      try {
        if (ps != null)
          ps.close();
      } finally {
        DriverManagerConnectionPool.releaseConnection(connection);
      }
    }
    return uffici;
  }

  @Override
  public void doSave(AbstractBean product) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    UfficioBean ufficioBean = (UfficioBean) product;
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(UfficioSQL.DO_SAVE);
      
      ps.setInt(1, ufficioBean.getID());
      ps.setInt(2, ufficioBean.getStrutturaOspitanteID());
      ps.setString(3, ufficioBean.getEmail());
      ps.setString(4, ufficioBean.getNome());
      ps.setString(5, ufficioBean.getPass());
      
      if (!(ps.executeUpdate() > 0)) {
        Logger.getGlobal().log(Level.SEVERE, "Oggetto UfficioBean Non Mmorizzato.");
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
      ps = connection.prepareStatement(UfficioSQL.DO_DELETE);
      
      ps.setInt(1, code);
      
      if (ps.executeUpdate() > 0) {
        deleted = true;
      } else {
        Logger.getGlobal().log(Level.SEVERE, "Oggetto UfficioBean Non Rimosso.");
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
  
  public boolean doUpdate(AbstractBean product, int ufficioID) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    UfficioBean ufficioBean = (UfficioBean) product;
    boolean updated = false;
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(UfficioSQL.DO_UPDATE);
      
      ps.setInt(1, ufficioBean.getID());
      ps.setInt(2, ufficioBean.getStrutturaOspitanteID());
      ps.setString(3, ufficioBean.getEmail());
      ps.setString(4, ufficioBean.getNome());
      ps.setString(5, ufficioBean.getPass());
      ps.setInt(6, ufficioID);
      
      if (ps.executeUpdate() > 0) {
        updated = true;
      } else {
        Logger.getGlobal().log(Level.SEVERE, "Oggetto UfficioBean Non Aggiornato.");
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

  public Collection<AbstractBean> doRetrieveByStrutturaOspitante(int code) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    Collection<AbstractBean> uffici = null;
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(UfficioSQL.DO_RETRIEVE_BY_STRUTTURA_OSPITANTE);
      
      ps.setInt(1, code);
      
      rs = ps.executeQuery();
      
      uffici = new ArrayList<AbstractBean>();
      
      while (rs.next()) {
        int id = rs.getInt("id");
        String email = rs.getString("email");
        String nome = rs.getString("nome");
        String pass = rs.getString("pass");
        
        uffici.add(new UfficioBean(id, code, email, nome, pass));
      }
      
    } finally {
      try {
        if (ps != null)
          ps.close();
      } finally {
        DriverManagerConnectionPool.releaseConnection(connection);
      }
    }
    return uffici;
  }
  
  public Collection<AbstractBean> doRetrieveByEmail(String text) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    Collection<AbstractBean> uffici = null;
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(UfficioSQL.DO_RETRIEVE_BY_EMAIL);
      
      ps.setString(1, text);
      
      rs = ps.executeQuery();
      
      uffici = new ArrayList<AbstractBean>();
      
      while (rs.next()) {
        int id = rs.getInt("id");
        int strutturaOspitanteID = rs.getInt("strutturaOspitanteID");
        String email = rs.getString("email");
        String nome = rs.getString("nome");
        String pass = rs.getString("pass");
        
        uffici.add(new UfficioBean(id, strutturaOspitanteID, email, nome, pass));
      }
      
    } finally {
      try {
        if (ps != null)
          ps.close();
      } finally {
        DriverManagerConnectionPool.releaseConnection(connection);
      }
    }
    return uffici;
  }
  
  public AbstractBean doRetrieveByNome(String text) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    UfficioBean ufficioBean = null;
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(UfficioSQL.DO_RETRIEVE_BY_NOME);
      
      ps.setString(1, text);
      
      rs = ps.executeQuery();
      
      if (rs.next()) {
        int id = rs.getInt("id");
        int strutturaOspitanteID = rs.getInt("strutturaOspitanteID");
        String email = rs.getString("email");
        String nome = rs.getString("nome");
        String pass = rs.getString("pass");
        
        ufficioBean = new UfficioBean(id, strutturaOspitanteID, email, nome, pass);
      } else {
    	  Logger.getGlobal().log(Level.SEVERE, "Oggetto UfficioBean Non Trovato.");
      }
      
    } finally {
      try {
        if (ps != null)
          ps.close();
      } finally {
        DriverManagerConnectionPool.releaseConnection(connection);
      }
    }
    return ufficioBean;
  }
  
  public Collection<AbstractBean> doRetrieveQuestionari(AbstractBean abstractBean) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    UfficioBean ufficioBean = null;
    Collection<AbstractBean> questionari = null;
    
    if (abstractBean != null &&
        abstractBean.getClass().getName().equals(UfficioBean.class.getName())) {
      ufficioBean = (UfficioBean) abstractBean;
    } else {
      Logger.getGlobal().log(Level.SEVERE, "Istanza Di Oggetto UfficioBean Non Valida.");
      return null;
    }
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(UfficioSQL.DO_RETRIEVE_QUESTIONARI_BY_UTENTE);
      
      ps.setInt(1, ufficioBean.getID());
      
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
