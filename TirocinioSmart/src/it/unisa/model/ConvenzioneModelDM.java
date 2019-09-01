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

  public static final ConvenzioneModelDM INSTANCE = new ConvenzioneModelDM();

  private ConvenzioneModelDM() {

  }

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
        String info = rs.getString("info");
        String descrizione = rs.getString("descrizione");
        int tutorAzID = rs.getInt("tutorAzID");
        int tutorAccID = rs.getInt("tutorAccID");
        
        convenzioneBean = new ConvenzioneBean(code, info, descrizione, tutorAzID, tutorAccID);
      } else {
        Logger.getGlobal().log(Level.SEVERE, "Oggetto ConvenzioneBean Non Trovato.");
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
        String info = rs.getString("info");
        String descrizione = rs.getString("descrizione");
        int tutorAzID = rs.getInt("tutorAzID");
        int tutorAccID = rs.getInt("tutorAccID");
        
        convenzioni.add(new ConvenzioneBean(id, info, descrizione, tutorAzID, tutorAccID));
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
      ps.setString(3, convenzioneBean.getDescrizione());
      ps.setInt(4, convenzioneBean.getTutorAzID());
      ps.setInt(5, convenzioneBean.getTutorAccID());
      
      if (!(ps.executeUpdate() > 0)) {
        Logger.getGlobal().log(Level.SEVERE, "Oggetto ConvenzioneBean Non Memorizzato.");
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
        Logger.getGlobal().log(Level.SEVERE, "Oggetto ConvenzioneBean Non Rimosso.");
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
  
  public boolean doUpdate(AbstractBean product, int codeConvenzione) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    ConvenzioneBean convenzioneBean = (ConvenzioneBean) product;
    boolean updated = false;
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(ConvenzioneSQL.DO_UPDATE);
      
      ps.setInt(1, convenzioneBean.getID());
      ps.setString(2, convenzioneBean.getInfo());
      ps.setString(3, convenzioneBean.getDescrizione());
      ps.setInt(4, convenzioneBean.getTutorAzID());
      ps.setInt(5, convenzioneBean.getTutorAccID());
      ps.setInt(6, codeConvenzione);
      
      if (ps.executeUpdate() > 0) {
        updated = true;
      } else {
        Logger.getGlobal().log(Level.SEVERE, "Oggetto ConvenzionBean Non Aggiornato.");
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

  public Collection<AbstractBean> doRetrieveByInfo(String text) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    Collection<AbstractBean> convenzioni = null;
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(ConvenzioneSQL.DO_RETRIEVE_BY_INFO);
      
      ps.setString(1, text);
      
      rs = ps.executeQuery();
      
      convenzioni = new ArrayList<AbstractBean>();
      
      while (rs.next()) {
        int id = rs.getInt("id");
        String info = rs.getString("info");
        String descrizione = rs.getString("descrizione");
        int tutorAzID = rs.getInt("tutorAzID");
        int tutorAccID = rs.getInt("tutorAccID");
        
        convenzioni.add(new ConvenzioneBean(id, info, descrizione, tutorAzID, tutorAccID));
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
  
  public Collection<AbstractBean> doRetrieveByConvenienza(String text) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    Collection<AbstractBean> convenzioni = null;
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(ConvenzioneSQL.DO_RETRIEVE_BY_CONVENIENZA);

      rs = ps.executeQuery();
      
      ps.setString(1, text);
      
      convenzioni = new ArrayList<AbstractBean>();
      
      while (rs.next()) {
        int id = rs.getInt("id");
        String info = rs.getString("info");
        String descrizione = rs.getString("descrizione");
        int tutorAzID = rs.getInt("tutorAzID");
        int tutorAccID = rs.getInt("tutorAccID");
        
        convenzioni.add(new ConvenzioneBean(id, info, descrizione, tutorAzID, tutorAccID));
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
  
  public Collection<AbstractBean> doRetrieveByTutorAcc(int codeTutorAcc) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    Collection<AbstractBean> convenzioni = null;
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(ConvenzioneSQL.DO_RETRIEVE_BY_TUTOR_ACC);
      
      ps.setInt(1, codeTutorAcc);

      rs = ps.executeQuery();
      
      convenzioni = new ArrayList<AbstractBean>();
      
      while (rs.next()) {
        int id = rs.getInt("id");
        String info = rs.getString("info");
        String descrizione = rs.getString("descrizione");
        int tutorAzID = rs.getInt("tutorAzID");
        
        convenzioni.add(new ConvenzioneBean(id, info, descrizione, tutorAzID, codeTutorAcc));
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
  
  public Collection<AbstractBean> doRetrieveByTutorAz(int codeTutorAz) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    Collection<AbstractBean> convenzioni = null;
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(ConvenzioneSQL.DO_RETRIEVE_BY_TUTOR_AZ);
      
      ps.setInt(1, codeTutorAz);
      
      rs = ps.executeQuery();
      
      convenzioni = new ArrayList<AbstractBean>();
      
      while (rs.next()) {
        int id = rs.getInt("id");
        String info = rs.getString("info");
        String descrizione = rs.getString("descrizione");
        int tutorAccID = rs.getInt("tutorAccID");
        
        convenzioni.add(new ConvenzioneBean(id, info, descrizione, codeTutorAz, tutorAccID));
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
