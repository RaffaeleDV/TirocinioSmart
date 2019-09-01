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
import it.unisa.sql.ObiettivoTirocinioSQL;

public class ObiettivoTirocinioModelDM implements BeansModel {

  public static final ObiettivoTirocinioModelDM INSTANCE = new ObiettivoTirocinioModelDM();

  private ObiettivoTirocinioModelDM() {

  }

  @Override
  public AbstractBean doRetrieveByKey(int code) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    ObiettivoTirocinioBean obiettivoTirocinioBean = null;
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(ObiettivoTirocinioSQL.DO_RETRIEVE_BY_KEY);
      
      ps.setInt(1, code);
      
      rs = ps.executeQuery();
      
      if (rs.next()) {
        int progettoFormativoID = rs.getInt("progettoFormativoID");
        String obiettivo = rs.getString("obbiettivo");
        String priorita = rs.getString("priorita");
        
        obiettivoTirocinioBean = new ObiettivoTirocinioBean(code, progettoFormativoID, obiettivo, priorita);
      } else {
        Logger.getGlobal().log(Level.SEVERE, "Oggetto ObbiettivoTirocinioBean Non Trovato.");
      }
    } finally {
      try {
        if (ps != null)
          ps.close();
      } finally {
        DriverManagerConnectionPool.releaseConnection(connection);
      }
    }
    return obiettivoTirocinioBean;
  }

  @Override
  public Collection<AbstractBean> doRetrieveAll(String order) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    Collection<AbstractBean> obiettivi = null;
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(ObiettivoTirocinioSQL.DO_RETRIEVE_ALL);
      
      rs = ps.executeQuery();
      
      obiettivi = new ArrayList<AbstractBean>();
      
      while (rs.next()) {
        int id = rs.getInt("id");
        int progettoFormativoID = rs.getInt("progettoFormativoID");
        String obiettivo = rs.getString("obiettivo");
        String priorita = rs.getString("priorita");
        
        obiettivi.add(new ObiettivoTirocinioBean(id, progettoFormativoID, obiettivo, priorita));
      }
      
    } finally {
      try {
        if (ps != null)
          ps.close();
      } finally {
        DriverManagerConnectionPool.releaseConnection(connection);
      }
    }
    
    return obiettivi;
  }

  @Override
  public void doSave(AbstractBean product) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    ObiettivoTirocinioBean obiettivoTirocinioBean = (ObiettivoTirocinioBean) product;
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(ObiettivoTirocinioSQL.DO_SAVE);
      
      ps.setInt(1, obiettivoTirocinioBean.getID());
      ps.setInt(2, obiettivoTirocinioBean.getProgettoFormativoID());
      ps.setString(3, obiettivoTirocinioBean.getObiettivo());
      ps.setString(4, obiettivoTirocinioBean.getPriorita());
      
      if (!(ps.executeUpdate() > 0)) {
        Logger.getGlobal().log(Level.SEVERE, "Oggetto ObiettivoTirocinioBean Non Memorizzato.");
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
      ps = connection.prepareStatement(ObiettivoTirocinioSQL.DO_DELETE);
      
      ps.setInt(1, code);
      
      if (ps.executeUpdate() > 0) {
        deleted = true;
      } else {
        Logger.getGlobal().log(Level.SEVERE, "Oggetto ObiettivoTirocinioBean Non Rimosso.");
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
  
  public boolean doUpdate(AbstractBean product, int codeObiettivoTirocinio) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    ObiettivoTirocinioBean obiettivoTirocinioBean = (ObiettivoTirocinioBean) product;
    boolean updated = false;
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(ObiettivoTirocinioSQL.DO_UPDATE);
      
      ps.setInt(1, obiettivoTirocinioBean.getID());
      ps.setInt(2, obiettivoTirocinioBean.getProgettoFormativoID());
      ps.setString(3, obiettivoTirocinioBean.getObiettivo());
      ps.setString(4, obiettivoTirocinioBean.getPriorita());
      ps.setInt(5, codeObiettivoTirocinio);
      
      if (ps.executeUpdate() > 0) {
        updated = true;
      } else {
        Logger.getGlobal().log(Level.SEVERE, "Oggetto ObettivoTirocinioBean Non Aggiornato.");
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

  public Collection<AbstractBean> doRetrieveByProgettoFormativo(int code) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    Collection<AbstractBean> obiettivi = null;
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(ObiettivoTirocinioSQL.DO_RETRIEVE_BY_PROGETTO_FORMATIVO);
      
      ps.setInt(1, code);
      
      rs = ps.executeQuery();
      
      obiettivi = new ArrayList<AbstractBean>();
      
      while (rs.next()) {
        int id = rs.getInt("id");
        String obiettivo = rs.getString("obiettivo");
        String priorita = rs.getString("priorita");
        
        obiettivi.add(new ObiettivoTirocinioBean(id, code, obiettivo, priorita));
      }
      
    } finally {
      try {
        if (ps != null)
          ps.close();
      } finally {
        DriverManagerConnectionPool.releaseConnection(connection);
      }
    }
    return obiettivi;
  }
  
  public Collection<AbstractBean> doRetrieveByObiettivo(String text) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    Collection<AbstractBean> obiettivi = null;
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(ObiettivoTirocinioSQL.DO_RETRIEVE_BY_OBIETTIVO);
      
      ps.setString(1, text);
      
      rs = ps.executeQuery();
      
      obiettivi = new ArrayList<AbstractBean>();
      
      while (rs.next()) {
        int id = rs.getInt("id");
        int progettoFormativoID = rs.getInt("progettoFormativoID");
        String obiettivo = rs.getString("obiettivo");
        String priorita = rs.getString("priorita");
        
        obiettivi.add(new ObiettivoTirocinioBean(id, progettoFormativoID, obiettivo, priorita));
      }
      
    } finally {
      try {
        if (ps != null)
          ps.close();
      } finally {
        DriverManagerConnectionPool.releaseConnection(connection);
      }
    }
    return obiettivi;
  }
  
  public Collection<AbstractBean> doRetrieveByPriorita(String text) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    Collection<AbstractBean> obiettivi = null;
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(ObiettivoTirocinioSQL.DO_RETRIEVE_BY_PRIORITA);
      
      ps.setString(1, text);
      
      rs = ps.executeQuery();
      
      obiettivi = new ArrayList<AbstractBean>();
      
      while (rs.next()) {
        int id = rs.getInt("id");
        int progettoFormativo = rs.getInt("progettoFormativo");
        String priorita = rs.getString("priorita");
        String obiettivo = rs.getString("obiettivo");
        
        obiettivi.add(new ObiettivoTirocinioBean(id, progettoFormativo, obiettivo, priorita));
      }
      
    } finally {
      try {
        if (ps != null)
          ps.close();
      } finally {
        DriverManagerConnectionPool.releaseConnection(connection);
      }
    }
    return obiettivi;
  }
}





