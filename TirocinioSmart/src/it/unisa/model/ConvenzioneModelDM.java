package it.unisa.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import it.unisa.database.DriverManagerConnectionPool;
import it.unisa.sql.ConvenzioneSQL;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;
import java.util.Collection;

public class ConvenzioneModelDM implements BeansModel {

  @Override
  public AbstractBean doRetrieveByKey(int code) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    ConvenzioneBean convenzioneBean = null;
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(ConvenzioneSQL.DO_RETRIEVE_BY_KEY);
      
      ps.setInt(1, code);
      
      rs = ps.executeQuery();
      
      if (rs.next()) {
        int id = rs.getInt("id");
        int tutorAzID = rs.getInt("tutorAzID");
        String info = rs.getString("info");
        String azienda = rs.getString("azienda");
        
        convenzioneBean = new ConvenzioneBean(id, info, azienda, tutorAzID);
      } else {
        Logger.getGlobal().log(Level.INFO, "Convenzione con l' id specificato non trovato");
      }
      
    } finally {
      try {
        if (ps != null)
          ps.close();
      } finally {
        DriverManagerConnectionPool.releaseConnection(connection);
      }
    }
    
    return convenzioneBean;
  }

  @Override
  public Collection<AbstractBean> doRetrieveAll(String order) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    Collection<AbstractBean> convenzioni = null;
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(ConvenzioneSQL.DO_RETRIEVE_ALL);
      
      rs = ps.executeQuery();
      
      convenzioni = new ArrayList<AbstractBean>();
      
      while (rs.next()) {
        int id = rs.getInt("id");
        int tutorAzID = rs.getInt("tutorAzID");
        String info = rs.getString("info");
        String azienda = rs.getString("azienda");
        
        convenzioni.add(new ConvenzioneBean(id, info, azienda, tutorAzID));
      }
      
    } finally {
      try {
        if (ps != null)
          ps.close();
      } finally {
        DriverManagerConnectionPool.releaseConnection(connection);
      }
    }
    
    return convenzioni;
  }

  @Override
  public void doSave(AbstractBean product) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    ConvenzioneBean convenzioneBean = null;
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(ConvenzioneSQL.DO_SAVE);
    
      convenzioneBean = (ConvenzioneBean) product;
      
      ps.setInt(1, convenzioneBean.getID());
      ps.setString(2, convenzioneBean.getInfo());
      ps.setString(3, convenzioneBean.getAzienda());
      ps.setInt(4, convenzioneBean.getTutorAzID());
      
      if (!(ps.executeUpdate() > 0)) {
        Logger.getGlobal().log(Level.INFO, "Oggetto ConvenzioneBean non memorizzato");
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
      ps = connection.prepareStatement(ConvenzioneSQL.DO_DELETE);
      
      ps.setInt(1, code);
      
      if (ps.executeUpdate() > 0) {
        deleted = true;
      } else {
        Logger.getGlobal().log(Level.INFO, "Oggetto ConvenzioneBean non rimosso");
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
    ConvenzioneBean convenzioneBean = (ConvenzioneBean) product;
    boolean updated = false;
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(ConvenzioneSQL.DO_UPDATE);
      
      ps.setInt(1, convenzioneBean.getTutorAzID());
      ps.setString(2, convenzioneBean.getInfo());
      ps.setString(3, convenzioneBean.getAzienda());
      ps.setInt(4, convenzioneBean.getID());
      
      if (ps.executeUpdate() > 0) {
        updated = true;
      } else {
        Logger.getGlobal().log(Level.INFO, "Oggetto ConvenzionBean non aggiornato");
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

  public Collection<AbstractBean> doRetrieveByInfo(String info) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    Collection<AbstractBean> convenzioni = null;
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(ConvenzioneSQL.DO_RETRIEVE_BY_INFO);
      
      ps.setString(1, info);
      
      rs = ps.executeQuery();
      
      convenzioni = new ArrayList<AbstractBean>();
      
      
      while (rs.next()) {
        int id = rs.getInt("id");
        String azienda = rs.getString("azienda");
        int tutorAzID = rs.getInt("tutorAzID");
        
        convenzioni.add(new ConvenzioneBean(id, info, azienda, tutorAzID));
      }
      
    } finally {
      try {
        if (ps != null)
          ps.close();
      } finally {
        DriverManagerConnectionPool.releaseConnection(connection);
      }
    }
    
    return convenzioni;
  }
  
  public Collection<AbstractBean> doRetrieveByAzienda(String azienda) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    Collection<AbstractBean> convenzioni = null;
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(ConvenzioneSQL.DO_RETRIEVE_BY_AZIENDA);
      
      ps.setString(1, azienda);
      
      rs = ps.executeQuery();
      
      convenzioni = new ArrayList<AbstractBean>();
      
      while (rs.next()) {
        int id = rs.getInt("id");
        String info = rs.getString("info");
        int tutorAzID = rs.getInt("tutorAzID");
        
        convenzioni.add(new ConvenzioneBean(id, info, azienda, tutorAzID));
      }
      
    } finally {
      try {
        if (ps != null)
          ps.close();
      } finally {
        DriverManagerConnectionPool.releaseConnection(connection);
      }
    }
    
    return convenzioni;
  }
  
  public Collection<AbstractBean> doRetrieveByTutorAz(int code) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    Collection<AbstractBean> convenzioni = null;
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(ConvenzioneSQL.DO_RETRIEVE_BY_TUTOR_AZ);
      
      ps.setInt(1, code);
      
      rs = ps.executeQuery();
      
      convenzioni = new ArrayList<AbstractBean>();
      
      while (rs.next()) {
        int id = rs.getInt("id");
        String info = rs.getString("info");
        String azienda = rs.getString("azienda");
        
        convenzioni.add(new ConvenzioneBean(id, info, azienda, code));
      }
      
    } finally {
      try {
        if (ps != null)
          ps.close();
      } finally {
        DriverManagerConnectionPool.releaseConnection(connection);
      }
    }
    
    return convenzioni;
  }
}
