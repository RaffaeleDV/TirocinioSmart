package it.unisa.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Collection;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.util.ArrayList;
import it.unisa.database.DriverManagerConnectionPool;
import it.unisa.sql.ProgettoFormativoSQL;

public class ProgettoFormativoModelDM implements BeansModel {

  public static final ProgettoFormativoModelDM INSTANCE = new ProgettoFormativoModelDM();

  private ProgettoFormativoModelDM() {

  }

  @Override
  public AbstractBean doRetrieveByKey(int code) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    ProgettoFormativoBean progettoFormativoBean = null;
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(ProgettoFormativoSQL.DO_RETRIEVE_BY_KEY);
      
      ps.setInt(1, code);
      
      rs = ps.executeQuery();
      
      if (rs.next()) {
        String info = rs.getString("info");
        String formazione = rs.getString("formazione");
        String modalita = rs.getString("modalita");
        String responsabile = rs.getString("responsabile");
        Date inizioPeriodo = rs.getDate("inizioPeriodo");
        Date terminePeriodo = rs.getDate("terminePeriodo");
        Date dataRilascio = rs.getDate("dataRilascio");
        boolean approvazioneGenitori = rs.getBoolean("approvazioneGenitori");
        boolean approvazioneRespo = rs.getBoolean("approvazioneRespo");
        boolean approvazioneTutorAcc = rs.getBoolean("approvazioneTutorAcc");
        boolean approvazioneTutorAz = rs.getBoolean("approvazioneTutorAz");
        int ufficioID = rs.getInt("ufficioID");
        
        progettoFormativoBean = new ProgettoFormativoBean(code, info, formazione, modalita, responsabile, inizioPeriodo, terminePeriodo, dataRilascio, approvazioneGenitori, approvazioneRespo, approvazioneTutorAcc, approvazioneTutorAz, ufficioID);
      } else {
        Logger.getGlobal().log(Level.SEVERE, "Oggetto ProgettoFormativoBean Non Trovato.");
      }
      
    } finally {
      try {
        if (ps != null)
          ps.close();
      } finally {
        DriverManagerConnectionPool.releaseConnection(connection);
      }
    }
    
    return progettoFormativoBean;
  }

  @Override
  public Collection<AbstractBean> doRetrieveAll(String order) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    Collection<AbstractBean> progetti = null;
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(ProgettoFormativoSQL.DO_RETRIEVE_ALL);
      
      rs = ps.executeQuery();
      
      progetti = new ArrayList<AbstractBean>();
      
      while (rs.next()) {
        int id = rs.getInt("id");
        String info = rs.getString("info");
        String formazione = rs.getString("formazione");
        String modalita = rs.getString("modalita");
        String responsabile = rs.getString("responsabile");
        Date inizioPeriodo = rs.getDate("inizioPeriodo");
        Date terminePeriodo = rs.getDate("terminePeriodo");
        Date dataRilascio = rs.getDate("dataRilascio");
        boolean approvazioneGenitori = rs.getBoolean("approvazioneGenitori");
        boolean approvazioneRespo = rs.getBoolean("approvazioneRespo");
        boolean approvazioneTutorAcc = rs.getBoolean("approvazioneTutorAcc");
        boolean approvazioneTutorAz = rs.getBoolean("approvazioneTutorAz");
        int ufficioID = rs.getInt("ufficioID");
        
        progetti.add(new ProgettoFormativoBean(id, info, formazione, modalita, responsabile, inizioPeriodo, terminePeriodo, dataRilascio, approvazioneGenitori, approvazioneRespo, approvazioneTutorAcc, approvazioneTutorAz, ufficioID));
      }
      
    } finally {
      try {
        if (ps != null)
          ps.close();
      } finally {
        DriverManagerConnectionPool.releaseConnection(connection);
      }
    }
    return progetti;
  }

  @Override
  public void doSave(AbstractBean product) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    ProgettoFormativoBean progettoFormativoBean = (ProgettoFormativoBean) product;
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(ProgettoFormativoSQL.DO_SAVE);
      
      ps.setInt(1, progettoFormativoBean.getID());
      ps.setString(2, progettoFormativoBean.getInfo());
      ps.setString(3, progettoFormativoBean.getFormazione());
      ps.setString(4, progettoFormativoBean.getModalita());
      ps.setString(5, progettoFormativoBean.getResponsabile());
      ps.setDate(6, progettoFormativoBean.getInizioPeriodo());
      ps.setDate(7, progettoFormativoBean.getTerminePeriodo());
      ps.setDate(8, progettoFormativoBean.getDataRilascio());
      ps.setBoolean(9, progettoFormativoBean.isApprovazioneGenitori());
      ps.setBoolean(10, progettoFormativoBean.isApprovazioneRespo());
      ps.setBoolean(11, progettoFormativoBean.isApprovazioneTutorAcc());
      ps.setBoolean(12, progettoFormativoBean.isApprovazioneTutorAz());
      ps.setInt(13, progettoFormativoBean.getUfficioID());
      
      if (!(ps.executeUpdate() > 0)) {
        Logger.getGlobal().log(Level.SEVERE, "Oggetto ProgettoFormativoBean Non Memorizzato.");
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
      ps = connection.prepareStatement(ProgettoFormativoSQL.DO_DELETE);
      
      ps.setInt(1, code);
      
      if (ps.executeUpdate() > 0) {
        deleted = true;
      } else {
        Logger.getGlobal().log(Level.SEVERE, "Oggetto ProgettoFormativoBean Non Rimosso.");
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
  
  public boolean doUpdate(AbstractBean product, int codeProgettoFormativo) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    ProgettoFormativoBean progettoFormativoBean = (ProgettoFormativoBean) product;
    boolean updated = false;
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(ProgettoFormativoSQL.DO_UPDATE);
      
      ps.setInt(1, progettoFormativoBean.getID());
      ps.setString(2, progettoFormativoBean.getInfo());
      ps.setString(3, progettoFormativoBean.getFormazione());
      ps.setString(4, progettoFormativoBean.getModalita());
      ps.setString(5, progettoFormativoBean.getResponsabile());
      ps.setDate(6, progettoFormativoBean.getInizioPeriodo());
      ps.setDate(7, progettoFormativoBean.getTerminePeriodo());
      ps.setDate(8, progettoFormativoBean.getDataRilascio());
      ps.setBoolean(9, progettoFormativoBean.isApprovazioneGenitori());
      ps.setBoolean(10, progettoFormativoBean.isApprovazioneRespo());
      ps.setBoolean(11, progettoFormativoBean.isApprovazioneTutorAcc());
      ps.setBoolean(12, progettoFormativoBean.isApprovazioneTutorAz());
      ps.setInt(13, progettoFormativoBean.getUfficioID());
      ps.setInt(14, codeProgettoFormativo);
      
      if (ps.executeUpdate() > 0) {
        updated = true;
      } else {
        Logger.getGlobal().log(Level.SEVERE, "Oggetto ProgettoFormativoBean Non Aggiornato.");
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
    Collection<AbstractBean> progetti = null;
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(ProgettoFormativoSQL.DO_RETRIEVE_BY_INFO);
      
      ps.setString(1, text);
      
      rs = ps.executeQuery();
      
      progetti = new ArrayList<AbstractBean>();
      
      while (rs.next()) {
        int id = rs.getInt("id");
        String info = rs.getString("info");
        String formazione = rs.getString("formazione");
        String modalita = rs.getString("modalita");
        String responsabile = rs.getString("responsabile");
        Date inizioPeriodo = rs.getDate("inizioPeriodo");
        Date terminePeriodo = rs.getDate("terminePeriodo");
        Date dataRilascio = rs.getDate("dataRilascio");
        boolean approvazioneGenitori = rs.getBoolean("approvazioneGenitori");
        boolean approvazioneRespo = rs.getBoolean("approvazioneRespo");
        boolean approvazioneTutorAcc = rs.getBoolean("approvazioneTutorAcc");
        boolean approvazioneTutorAz = rs.getBoolean("approvazioneTutorAz");
        int ufficioID = rs.getInt("ufficioID");
        
        progetti.add(new ProgettoFormativoBean(id, info, formazione, modalita, responsabile, inizioPeriodo, terminePeriodo, dataRilascio, approvazioneGenitori, approvazioneRespo, approvazioneTutorAcc, approvazioneTutorAz, ufficioID));
      }
      
    } finally {
      try {
        if (ps != null)
          ps.close();
      } finally {
        DriverManagerConnectionPool.releaseConnection(connection);
      }
    }
    return progetti;
  }
  
  public Collection<AbstractBean> doRetrieveByIdAssicurazione(int code) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    Collection<AbstractBean> progetti = null;
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(ProgettoFormativoSQL.DO_RETRIEVE_BY_ID_ASSICURAZIONE);
      
      ps.setInt(1, code);
      
      rs = ps.executeQuery();
      
      progetti = new ArrayList<AbstractBean>();
      
      while (rs.next()) {
        int id = rs.getInt("id");
        String info = rs.getString("info");
        String formazione = rs.getString("formazione");
        String modalita = rs.getString("modalita");
        String responsabile = rs.getString("responsabile");
        Date inizioPeriodo = rs.getDate("inizioPeriodo");
        Date terminePeriodo = rs.getDate("terminePeriodo");
        Date dataRilascio = rs.getDate("dataRilascio");
        boolean approvazioneGenitori = rs.getBoolean("approvazioneGenitori");
        boolean approvazioneRespo = rs.getBoolean("approvazioneRespo");
        boolean approvazioneTutorAcc = rs.getBoolean("approvazioneTutorAcc");
        boolean approvazioneTutorAz = rs.getBoolean("approvazioneTutorAz");
        int ufficioID = rs.getInt("ufficioID");
        
        progetti.add(new ProgettoFormativoBean(id, info, formazione, modalita, responsabile, inizioPeriodo, terminePeriodo, dataRilascio, approvazioneGenitori, approvazioneRespo, approvazioneTutorAcc, approvazioneTutorAz, ufficioID));
      }
      
    } finally {
      try {
        if (ps != null)
          ps.close();
      } finally {
        DriverManagerConnectionPool.releaseConnection(connection);
      }
    }
    return progetti;
  }
  
  public Collection<AbstractBean> doRetrieveByFormazione(String text) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    Collection<AbstractBean> progetti = null;
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(ProgettoFormativoSQL.DO_RETRIEVE_BY_FORMAZIONE);
      
      ps.setString(1, text);
      
      rs = ps.executeQuery();
      
      progetti = new ArrayList<AbstractBean>();
      
      while (rs.next()) {
        int id = rs.getInt("id");
        String info = rs.getString("info");
        String formazione = rs.getString("formazione");
        String modalita = rs.getString("modalita");
        String responsabile = rs.getString("responsabile");
        Date inizioPeriodo = rs.getDate("inizioPeriodo");
        Date terminePeriodo = rs.getDate("terminePeriodo");
        Date dataRilascio = rs.getDate("dataRilascio");
        boolean approvazioneGenitori = rs.getBoolean("approvazioneGenitori");
        boolean approvazioneRespo = rs.getBoolean("approvazioneRespo");
        boolean approvazioneTutorAcc = rs.getBoolean("approvazioneTutorAcc");
        boolean approvazioneTutorAz = rs.getBoolean("approvazioneTutorAz");
        int ufficioID = rs.getInt("ufficioID");
        
        progetti.add(new ProgettoFormativoBean(id, info, formazione, modalita, responsabile, inizioPeriodo, terminePeriodo, dataRilascio, approvazioneGenitori, approvazioneRespo, approvazioneTutorAcc, approvazioneTutorAz, ufficioID));
      }
      
    } finally {
      try {
        if (ps != null)
          ps.close();
      } finally {
        DriverManagerConnectionPool.releaseConnection(connection);
      }
    }
    return progetti;
  }
  
  public Collection<AbstractBean> doRetrieveByModalita(String text) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    Collection<AbstractBean> progetti = null;
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(ProgettoFormativoSQL.DO_RETRIEVE_BY_MODALITA);
      
      ps.setString(1, text);
      
      rs = ps.executeQuery();
      
      progetti = new ArrayList<AbstractBean>();
      
      while (rs.next()) {
        int id = rs.getInt("id");
        String info = rs.getString("info");
        String formazione = rs.getString("formazione");
        String modalita = rs.getString("modalita");
        String responsabile = rs.getString("responsabile");
        Date inizioPeriodo = rs.getDate("inizioPeriodo");
        Date terminePeriodo = rs.getDate("terminePeriodo");
        Date dataRilascio = rs.getDate("dataRilascio");
        boolean approvazioneGenitori = rs.getBoolean("approvazioneGenitori");
        boolean approvazioneRespo = rs.getBoolean("approvazioneRespo");
        boolean approvazioneTutorAcc = rs.getBoolean("approvazioneTutorAcc");
        boolean approvazioneTutorAz = rs.getBoolean("approvazioneTutorAz");
        int ufficioID = rs.getInt("ufficioID");
        
        progetti.add(new ProgettoFormativoBean(id, info, formazione, modalita, responsabile, inizioPeriodo, terminePeriodo, dataRilascio, approvazioneGenitori, approvazioneRespo, approvazioneTutorAcc, approvazioneTutorAz, ufficioID));
      }
      
    } finally {
      try {
        if (ps != null)
          ps.close();
      } finally {
        DriverManagerConnectionPool.releaseConnection(connection);
      }
    }
    return progetti;
  }
  
  public Collection<AbstractBean> doRetrieveByResponsabile(String text) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    Collection<AbstractBean> progetti = null;
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(ProgettoFormativoSQL.DO_RETRIEVE_BY_RESPONSABILE);
      
      ps.setString(1, text);
      
      rs = ps.executeQuery();
      
      progetti = new ArrayList<AbstractBean>();
      
      while (rs.next()) {
        int id = rs.getInt("id");
        String info = rs.getString("info");
        String formazione = rs.getString("formazione");
        String modalita = rs.getString("modalita");
        String responsabile = rs.getString("responsabile");
        Date inizioPeriodo = rs.getDate("inizioPeriodo");
        Date terminePeriodo = rs.getDate("terminePeriodo");
        Date dataRilascio = rs.getDate("dataRilascio");
        boolean approvazioneGenitori = rs.getBoolean("approvazioneGenitori");
        boolean approvazioneRespo = rs.getBoolean("approvazioneRespo");
        boolean approvazioneTutorAcc = rs.getBoolean("approvazioneTutorAcc");
        boolean approvazioneTutorAz = rs.getBoolean("approvazioneTutorAz");
        int ufficioID = rs.getInt("ufficioID");
        
        progetti.add(new ProgettoFormativoBean(id, info, formazione, modalita, responsabile, inizioPeriodo, terminePeriodo, dataRilascio, approvazioneGenitori, approvazioneRespo, approvazioneTutorAcc, approvazioneTutorAz, ufficioID));
      }
      
    } finally {
      try {
        if (ps != null)
          ps.close();
      } finally {
        DriverManagerConnectionPool.releaseConnection(connection);
      }
    }
    return progetti;
  }
  
  public Collection<AbstractBean> doRetrieveByInizioPeriodo(Date inizioPeriodo) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    Collection<AbstractBean> progetti = null;
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(ProgettoFormativoSQL.DO_RETRIEVE_BY_INIZIO_PERIODO);
      
      ps.setDate(1, inizioPeriodo);
      
      rs = ps.executeQuery();
      
      progetti = new ArrayList<AbstractBean>();
      
      while (rs.next()) {
        int id = rs.getInt("id");
        String info = rs.getString("info");
        String formazione = rs.getString("formazione");
        String modalita = rs.getString("modalita");
        String responsabile = rs.getString("responsabile");
        Date terminePeriodo = rs.getDate("terminePeriodo");
        Date dataRilascio = rs.getDate("dataRilascio");
        boolean approvazioneGenitori = rs.getBoolean("approvazioneGenitori");
        boolean approvazioneRespo = rs.getBoolean("approvazioneRespo");
        boolean approvazioneTutorAcc = rs.getBoolean("approvazioneTutorAcc");
        boolean approvazioneTutorAz = rs.getBoolean("approvazioneTutorAz");
        int ufficioID = rs.getInt("ufficioID");
        
        progetti.add(new ProgettoFormativoBean(id, info, formazione, modalita, responsabile, inizioPeriodo, terminePeriodo, dataRilascio, approvazioneGenitori, approvazioneRespo, approvazioneTutorAcc, approvazioneTutorAz, ufficioID));
      }
      
    } finally {
      try {
        if (ps != null)
          ps.close();
      } finally {
        DriverManagerConnectionPool.releaseConnection(connection);
      }
    }
    return progetti;
  }
  
  public Collection<AbstractBean> doRetrieveByTerminePeriodo(Date terminePeriodo) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    Collection<AbstractBean> progetti = null;
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(ProgettoFormativoSQL.DO_RETRIEVE_BY_TERMINE_PERIODO);
      
      ps.setDate(1, terminePeriodo);
      
      rs = ps.executeQuery();
      
      progetti = new ArrayList<AbstractBean>();
      
      while (rs.next()) {
        int id = rs.getInt("id");
        String info = rs.getString("info");
        String formazione = rs.getString("formazione");
        String modalita = rs.getString("modalita");
        String responsabile = rs.getString("responsabile");
        Date inizioPeriodo = rs.getDate("inizioPeriodo");
        Date dataRilascio = rs.getDate("dataRilascio");
        boolean approvazioneGenitori = rs.getBoolean("approvazioneGenitori");
        boolean approvazioneRespo = rs.getBoolean("approvazioneRespo");
        boolean approvazioneTutorAcc = rs.getBoolean("approvazioneTutorAcc");
        boolean approvazioneTutorAz = rs.getBoolean("approvazioneTutorAz");
        int ufficioID = rs.getInt("ufficioID");
        
        progetti.add(new ProgettoFormativoBean(id, info, formazione, modalita, responsabile, inizioPeriodo, terminePeriodo, dataRilascio, approvazioneGenitori, approvazioneRespo, approvazioneTutorAcc, approvazioneTutorAz, ufficioID));
      }
      
    } finally {
      try {
        if (ps != null)
          ps.close();
      } finally {
        DriverManagerConnectionPool.releaseConnection(connection);
      }
    }
    return progetti;
  }
  
  public Collection<AbstractBean> doRetrieveByDataRilascio(Date dataRilascio) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    Collection<AbstractBean> progetti = null;
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(ProgettoFormativoSQL.DO_RETRIEVE_BY_DATA_RILASCIO);
      
      ps.setDate(1, dataRilascio);
      
      rs = ps.executeQuery();
      
      progetti = new ArrayList<AbstractBean>();
      
      while (rs.next()) {
        int id = rs.getInt("id");
        String info = rs.getString("info");
        String formazione = rs.getString("formazione");
        String modalita = rs.getString("modalita");
        String responsabile = rs.getString("responsabile");
        Date inizioPeriodo = rs.getDate("inizioPeriodo");
        Date terminePeriodo = rs.getDate("terminePeriodo");
        boolean approvazioneGenitori = rs.getBoolean("approvazioneGenitori");
        boolean approvazioneRespo = rs.getBoolean("approvazioneRespo");
        boolean approvazioneTutorAcc = rs.getBoolean("approvazioneTutorAcc");
        boolean approvazioneTutorAz = rs.getBoolean("approvazioneTutorAz");
        int ufficioID = rs.getInt("ufficioID");
        
        progetti.add(new ProgettoFormativoBean(id, info, formazione, modalita, responsabile, inizioPeriodo, terminePeriodo, dataRilascio, approvazioneGenitori, approvazioneRespo, approvazioneTutorAcc, approvazioneTutorAz, ufficioID));
      }
      
    } finally {
      try {
        if (ps != null)
          ps.close();
      } finally {
        DriverManagerConnectionPool.releaseConnection(connection);
      }
    }
    return progetti;
  }
  
  public Collection<AbstractBean> doRetrieveByApprovazione(boolean approvazione) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    Collection<AbstractBean> progetti = null;
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(ProgettoFormativoSQL.DO_RETRIEVE_BY_APPROVAZIONE);
      
      ps.setBoolean(1, approvazione);
      
      rs = ps.executeQuery();
      
      progetti = new ArrayList<AbstractBean>();
      
      while (rs.next()) {
        int id = rs.getInt("id");
        String info = rs.getString("info");
        String formazione = rs.getString("formazione");
        String modalita = rs.getString("modalita");
        String responsabile = rs.getString("responsabile");
        Date inizioPeriodo = rs.getDate("inizioPeriodo");
        Date terminePeriodo = rs.getDate("terminePeriodo");
        Date dataRilascio = rs.getDate("dataRilascio");
        boolean approvazioneGenitori = rs.getBoolean("approvazioneGenitori");
        boolean approvazioneRespo = rs.getBoolean("approvazioneRespo");
        boolean approvazioneTutorAcc = rs.getBoolean("approvazioneTutorAcc");
        boolean approvazioneTutorAz = rs.getBoolean("approvazioneTutorAz");
        int ufficioID = rs.getInt("ufficioID");
        
        progetti.add(new ProgettoFormativoBean(id, info, formazione, modalita, responsabile, inizioPeriodo, terminePeriodo, dataRilascio, approvazioneGenitori, approvazioneRespo, approvazioneTutorAcc, approvazioneTutorAz, ufficioID));
      }
      
    } finally {
      try {
        if (ps != null)
          ps.close();
      } finally {
        DriverManagerConnectionPool.releaseConnection(connection);
      }
    }
    return progetti;
  }
  
  public Collection<AbstractBean> doRetrieveByApprovazioneGenitori(boolean approvazioneGenitori) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    Collection<AbstractBean> progetti = null;
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(ProgettoFormativoSQL.DO_RETRIEVE_BY_APPROVAZIONE_GENITORI);
      
      ps.setBoolean(1, approvazioneGenitori);
      
      rs = ps.executeQuery();
      
      progetti = new ArrayList<AbstractBean>();
      
      while (rs.next()) {
        int id = rs.getInt("id");
        String info = rs.getString("info");
        String formazione = rs.getString("formazione");
        String modalita = rs.getString("modalita");
        String responsabile = rs.getString("responsabile");
        Date inizioPeriodo = rs.getDate("inizioPeriodo");
        Date terminePeriodo = rs.getDate("terminePeriodo");
        Date dataRilascio = rs.getDate("dataRilascio");
        boolean approvazioneRespo = rs.getBoolean("approvazioneRespo");
        boolean approvazioneTutorAcc = rs.getBoolean("approvazioneTutorAcc");
        boolean approvazioneTutorAz = rs.getBoolean("approvazioneTutorAz");
        int ufficioID = rs.getInt("ufficioID");
        
        progetti.add(new ProgettoFormativoBean(id, info, formazione, modalita, responsabile, inizioPeriodo, terminePeriodo, dataRilascio, approvazioneGenitori, approvazioneRespo, approvazioneTutorAcc, approvazioneTutorAz, ufficioID));
      }
      
    } finally {
      try {
        if (ps != null)
          ps.close();
      } finally {
        DriverManagerConnectionPool.releaseConnection(connection);
      }
    }
    return progetti;
  }
  
  public Collection<AbstractBean> doRetrieveByApprovazioneRespo(boolean approvazioneRespo) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    Collection<AbstractBean> progetti = null;
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(ProgettoFormativoSQL.DO_RETRIEVE_BY_APPROVAZIONE_RESPO);
      
      ps.setBoolean(1, approvazioneRespo);
      
      rs = ps.executeQuery();
      
      progetti = new ArrayList<AbstractBean>();
      
      while (rs.next()) {
        int id = rs.getInt("id");
        String info = rs.getString("info");
        String formazione = rs.getString("formazione");
        String modalita = rs.getString("modalita");
        String responsabile = rs.getString("responsabile");
        Date inizioPeriodo = rs.getDate("inizioPeriodo");
        Date terminePeriodo = rs.getDate("terminePeriodo");
        Date dataRilascio = rs.getDate("dataRilascio");
        boolean approvazioneGenitori = rs.getBoolean("approvazioneGenitori");
        boolean approvazioneTutorAcc = rs.getBoolean("approvazioneTutorAcc");
        boolean approvazioneTutorAz = rs.getBoolean("approvazioneTutorAz");
        int ufficioID = rs.getInt("ufficioID");
        
        progetti.add(new ProgettoFormativoBean(id, info, formazione, modalita, responsabile, inizioPeriodo, terminePeriodo, dataRilascio, approvazioneGenitori, approvazioneRespo, approvazioneTutorAcc, approvazioneTutorAz, ufficioID));
      }
      
    } finally {
      try {
        if (ps != null)
          ps.close();
      } finally {
        DriverManagerConnectionPool.releaseConnection(connection);
      }
    }
    return progetti;
  }
  
  public Collection<AbstractBean> doRetrieveByApprovazioneTutorAcc(boolean approvazioneTutorAcc) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    Collection<AbstractBean> progetti = null;
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(ProgettoFormativoSQL.DO_RETRIEVE_BY_APPROVAZIONE_TUTOR_ACC);
      
      ps.setBoolean(1, approvazioneTutorAcc);
      
      rs = ps.executeQuery();
      
      progetti = new ArrayList<AbstractBean>();
      
      while (rs.next()) {
        int id = rs.getInt("id");
        String info = rs.getString("info");
        String formazione = rs.getString("formazione");
        String modalita = rs.getString("modalita");
        String responsabile = rs.getString("responsabile");
        Date inizioPeriodo = rs.getDate("inizioPeriodo");
        Date terminePeriodo = rs.getDate("terminePeriodo");
        Date dataRilascio = rs.getDate("dataRilascio");
        boolean approvazioneGenitori = rs.getBoolean("approvazioneGenitori");
        boolean approvazioneRespo = rs.getBoolean("approvazioneRespo");
        boolean approvazioneTutorAz = rs.getBoolean("approvazioneTutorAz");
        int ufficioID = rs.getInt("ufficioID");
        
        progetti.add(new ProgettoFormativoBean(id, info, formazione, modalita, responsabile, inizioPeriodo, terminePeriodo, dataRilascio, approvazioneGenitori, approvazioneRespo, approvazioneTutorAcc, approvazioneTutorAz, ufficioID));
      }
      
    } finally {
      try {
        if (ps != null)
          ps.close();
      } finally {
        DriverManagerConnectionPool.releaseConnection(connection);
      }
    }
    return progetti;
  }
  
  public Collection<AbstractBean> doRetrieveByApprovazioneTutorAz(boolean approvazioneTutorAz) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    Collection<AbstractBean> progetti = null;
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(ProgettoFormativoSQL.DO_RETRIEVE_BY_APPROVAZIONE_TUTOR_AZ);
      
      ps.setBoolean(1, approvazioneTutorAz);
      
      rs = ps.executeQuery();
      
      progetti = new ArrayList<AbstractBean>();
      
      while (rs.next()) {
        int id = rs.getInt("id");
        String info = rs.getString("info");
        String formazione = rs.getString("formazione");
        String modalita = rs.getString("modalita");
        String responsabile = rs.getString("responsabile");
        Date inizioPeriodo = rs.getDate("inizioPeriodo");
        Date terminePeriodo = rs.getDate("terminePeriodo");
        Date dataRilascio = rs.getDate("dataRilascio");
        boolean approvazioneGenitori = rs.getBoolean("approvazioneGenitori");
        boolean approvazioneTutorAcc = rs.getBoolean("approvazioneTutorAcc");
        boolean approvazioneRespo = rs.getBoolean("approvazioneRespo");
        int ufficioID = rs.getInt("ufficioID");
        
        progetti.add(new ProgettoFormativoBean(id, info, formazione, modalita, responsabile, inizioPeriodo, terminePeriodo, dataRilascio, approvazioneGenitori, approvazioneRespo, approvazioneTutorAcc, approvazioneTutorAz, ufficioID));
      }
      
    } finally {
      try {
        if (ps != null)
          ps.close();
      } finally {
        DriverManagerConnectionPool.releaseConnection(connection);
      }
    }
    return progetti;
  }
  
  public AbstractBean doRetrieveByMatricola(String matricola) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    AbstractBean progettoBean = null;
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(ProgettoFormativoSQL.DO_RETRIEVE_BY_MATRICOLA);
      
      ps.setString(1, matricola);
      
      rs = ps.executeQuery();
      
      if (rs.next()) {
        int id = rs.getInt("id");
        String info = rs.getString("info");
        String formazione = rs.getString("formazione");
        String modalita = rs.getString("modalita");
        String responsabile = rs.getString("responsabile");
        Date inizioPeriodo = rs.getDate("inizioPeriodo");
        Date terminePeriodo = rs.getDate("terminePeriodo");
        Date dataRilascio = rs.getDate("dataRilascio");
        boolean approvazioneGenitori = rs.getBoolean("approvazioneGenitori");
        boolean approvazioneRespo = rs.getBoolean("approvazioneRespo");
        boolean approvazioneTutorAcc = rs.getBoolean("approvazioneTutorAcc");
        boolean approvazioneTutorAz = rs.getBoolean("approvazioneTutorAz");
        int ufficioID = rs.getInt("ufficioID");
        
        progettoBean = new ProgettoFormativoBean(id, info, formazione, modalita, responsabile, inizioPeriodo, terminePeriodo, dataRilascio, approvazioneGenitori, approvazioneRespo, approvazioneTutorAcc, approvazioneTutorAz, ufficioID);
      
      } else {
        Logger.getGlobal().log(Level.SEVERE, "Oggetto Progetto Formativo Non Trovato.");
      }
      
    } finally {
      try {
        if (ps != null)
          ps.close();
      } finally {
        DriverManagerConnectionPool.releaseConnection(connection);
      }
    }
    return progettoBean;
  }
  
  public Collection<AbstractBean> doRetrieveByUfficio(int code) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    Collection<AbstractBean> progetti = null;
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(ProgettoFormativoSQL.DO_RETRIEVE_BY_UFFICIO);
      
      ps.setInt(1, code);
      
      rs = ps.executeQuery();
      
      progetti = new ArrayList<AbstractBean>();
      
      while (rs.next()) {
        int id = rs.getInt("id");
        String info = rs.getString("info");
        String formazione = rs.getString("formazione");
        String modalita = rs.getString("modalita");
        String responsabile = rs.getString("responsabile");
        Date inizioPeriodo = rs.getDate("inizioPeriodo");
        Date terminePeriodo = rs.getDate("terminePeriodo");
        Date dataRilascio = rs.getDate("dataRilascio");
        boolean approvazioneGenitori = rs.getBoolean("approvazioneGenitori");
        boolean approvazioneRespo = rs.getBoolean("approvazioneRespo");
        boolean approvazioneTutorAcc = rs.getBoolean("approvazioneTutorAcc");
        boolean approvazioneTutorAz = rs.getBoolean("approvazioneTutorAz");
        
        progetti.add(new ProgettoFormativoBean(id, info, formazione, modalita, responsabile, inizioPeriodo, terminePeriodo, dataRilascio, approvazioneGenitori, approvazioneRespo, approvazioneTutorAcc, approvazioneTutorAz, code));
      }
      
    } finally {
      try {
        if (ps != null)
          ps.close();
      } finally {
        DriverManagerConnectionPool.releaseConnection(connection);
      }
    }
    return progetti;
  }
  
  public Collection<AbstractBean> doRetrieveByConvenzione(int code) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    Collection<AbstractBean> progetti = null;
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(ProgettoFormativoSQL.DO_RETRIEVE_BY_CONVENZIONE);
      
      ps.setInt(1, code);
      
      rs = ps.executeQuery();
      
      progetti = new ArrayList<AbstractBean>();
      
      while (rs.next()) {
        int id = rs.getInt("id");
        String info = rs.getString("info");
        String formazione = rs.getString("formazione");
        String modalita = rs.getString("modalita");
        String responsabile = rs.getString("responsabile");
        Date inizioPeriodo = rs.getDate("inizioPeriodo");
        Date terminePeriodo = rs.getDate("terminePeriodo");
        Date dataRilascio = rs.getDate("dataRilascio");
        boolean approvazioneGenitori = rs.getBoolean("approvazioneGenitori");
        boolean approvazioneRespo = rs.getBoolean("approvazioneRespo");
        boolean approvazioneTutorAcc = rs.getBoolean("approvazioneTutorAcc");
        boolean approvazioneTutorAz = rs.getBoolean("approvazioneTutorAz");
        int ufficioID = rs.getInt("ufficioID");
        
        progetti.add(new ProgettoFormativoBean(id, info, formazione, modalita, responsabile, inizioPeriodo, terminePeriodo, dataRilascio, approvazioneGenitori, approvazioneRespo, approvazioneTutorAcc, approvazioneTutorAz, ufficioID));
      }
      
    } finally {
      try {
        if (ps != null)
          ps.close();
      } finally {
        DriverManagerConnectionPool.releaseConnection(connection);
      }
    }
    return progetti;
  }
  
  public Collection<AbstractBean> doRetrieveByTutor(TutorBean tutorBean) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    Collection<AbstractBean> progetti = null;
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(ProgettoFormativoSQL.DO_RETRIEVE_BY_TUTOR);
      
      ps.setInt(1, tutorBean.getID());
      
      rs = ps.executeQuery();
      
      progetti = new ArrayList<AbstractBean>();
      
      while (rs.next()) {
        int id = rs.getInt("id");
        String info = rs.getString("info");
        String formazione = rs.getString("formazione");
        String modalita = rs.getString("modalita");
        String responsabile = rs.getString("responsabile");
        Date inizioPeriodo = rs.getDate("inizioPeriodo");
        Date terminePeriodo = rs.getDate("terminePeriodo");
        Date dataRilascio = rs.getDate("dataRilascio");
        boolean approvazioneGenitori = rs.getBoolean("approvazioneGenitori");
        boolean approvazioneRespo = rs.getBoolean("approvazioneRespo");
        boolean approvazioneTutorAcc = rs.getBoolean("approvazioneTutorAcc");
        boolean approvazioneTutorAz = rs.getBoolean("approvazioneTutorAz");
        int ufficioID = rs.getInt("ufficioID");
        
        progetti.add(new ProgettoFormativoBean(id, info, formazione, modalita, responsabile, inizioPeriodo, terminePeriodo, dataRilascio, approvazioneGenitori, approvazioneRespo, approvazioneTutorAcc, approvazioneTutorAz, ufficioID));
      }
      
    } finally {
      try {
        if (ps != null)
          ps.close();
      } finally {
        DriverManagerConnectionPool.releaseConnection(connection);
      }
    }
    return progetti;
  }
  
  public Collection<Integer> doRetrieveAssicurazioniIDSByProgettoFormativo(ProgettoFormativoBean progettoFormativoBean) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    Collection<Integer> idsAssicurazioni = null;
    
    try {
      connection = (Connection) DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(ProgettoFormativoSQL.DO_RETRIEVE_IDS_ASSICURAZIONI);
      
      ps.setInt(1, progettoFormativoBean.getID());
      idsAssicurazioni = new ArrayList<Integer>();
      rs = ps.executeQuery();
      while (rs.next()) {
        Integer id = rs.getInt("assicurazioneID");
        idsAssicurazioni.add(id);
      }
    } finally {
      try {
        if (ps != null)
          ps.close();
      } finally {
        DriverManagerConnectionPool.releaseConnection(connection);
      }
    }
    return idsAssicurazioni;
  }
  
  public boolean isStudenteProgettoFormativo(StudenteBean studenteBean, int codeProgettoFormativo) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    boolean isStudenteProgettoFormativo = false;
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(ProgettoFormativoSQL.IS_STUDENTE_PROGETTO_FORMATIVO);
      
      ps.setInt(1, studenteBean.getID());
      ps.setInt(2, codeProgettoFormativo);
      
      rs = ps.executeQuery();
      
      if (rs.next()) {
        isStudenteProgettoFormativo = true;
      } else {
        Logger.getGlobal().log(Level.SEVERE, "Il Progetto Formativo Non Risulta Dello Studente.");
      }
      
    } finally {
      try {
        if (ps != null)
          ps.close();
      } finally {
        DriverManagerConnectionPool.releaseConnection(connection);
      }
    }
    return isStudenteProgettoFormativo;
  }
  
  public Collection<Object> doRetrieveIDSAssicurazioni(ProgettoFormativoBean progettoFormativoBean) throws SQLException {
    /*
     * to implement
     */
    return null;
  }
}
