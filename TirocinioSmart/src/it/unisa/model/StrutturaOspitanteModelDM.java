package it.unisa.model;

import java.sql.SQLException;
import java.util.Collection;
import java.util.ArrayList;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import it.unisa.database.DriverManagerConnectionPool;
import it.unisa.sql.StrutturaOspitanteSQL;

public class StrutturaOspitanteModelDM implements BeansModel {

  @Override
  public AbstractBean doRetrieveByKey(int code) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    StrutturaOspitanteBean strutturaOspitanteBean = null;
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(StrutturaOspitanteSQL.DO_RETRIEVE_BY_KEY);
      
      ps.setInt(1, code);
      
      rs = ps.executeQuery();
      
      if (rs.next()) {
        int id = rs.getInt("id");
        String nome = rs.getString("nome");
        String ambitoLavorativo = rs.getString("ambitoLavorativo");
        String nazione = rs.getString("nazione");
        String regione = rs.getString("regione");
        String citta = rs.getString("citta");
        String via = rs.getString("via");
        int ncivico = rs.getInt("ncivico");
        int ndipendenti = rs.getInt("ndipendenti");
        
        strutturaOspitanteBean = new StrutturaOspitanteBean(id, nome, ambitoLavorativo, nazione, regione, citta, via, ncivico, ndipendenti);
      } else
        Logger.getGlobal().log(Level.INFO, "Oggetto StrutturaOspitanteBean con l' id specificato non trovato");
      
    } finally {
      try {
        if (ps != null)
          ps.close();
      } finally {
        DriverManagerConnectionPool.releaseConnection(connection);
      }
    }
    
    return strutturaOspitanteBean;
  }

  @Override
  public Collection<AbstractBean> doRetrieveAll(String order) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    Collection<AbstractBean> strutture = null;
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(StrutturaOspitanteSQL.DO_RETRIEVE_ALL);
      
      rs = ps.executeQuery();
      
      strutture = new ArrayList<AbstractBean>();
      
      while (rs.next()) {
        int id = rs.getInt("id");
        String nome = rs.getString("nome");
        String ambitoLavorativo = rs.getString("ambitoLavorativo");
        String nazione = rs.getString("nazione");
        String regione = rs.getString("regione");
        String citta = rs.getString("citta");
        String via = rs.getString("via");
        int ncivico = rs.getInt("ncivico");
        int ndipendenti = rs.getInt("ndipendenti");
        
        strutture.add(new StrutturaOspitanteBean(id, nome, ambitoLavorativo, nazione, regione, citta, via, ncivico, ndipendenti));
      }
      
    } finally {
      try {
        if (ps != null)
          ps.close();
      } finally {
        DriverManagerConnectionPool.releaseConnection(connection);
      }
    }
    
    return strutture;
  }

  @Override
  public void doSave(AbstractBean product) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    StrutturaOspitanteBean strutturaOspitanteBean = (StrutturaOspitanteBean) product;
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(StrutturaOspitanteSQL.DO_SAVE);
      
      ps.setInt(1, strutturaOspitanteBean.getID());
      ps.setString(2, strutturaOspitanteBean.getNome());
      ps.setString(3, strutturaOspitanteBean.getAmbitoLavorativo());
      ps.setString(4, strutturaOspitanteBean.getNazione());
      ps.setString(5, strutturaOspitanteBean.getRegione());
      ps.setString(6, strutturaOspitanteBean.getCitta());
      ps.setString(7, strutturaOspitanteBean.getVia());
      ps.setInt(8, strutturaOspitanteBean.getNcivico());
      ps.setInt(9, strutturaOspitanteBean.getNdipendenti());
      
      if (!(ps.executeUpdate() > 0)) {
        Logger.getGlobal().log(Level.INFO, "Oggetto StrutturaOspitanteBean non memorizzato");
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
      ps = connection.prepareStatement(StrutturaOspitanteSQL.DO_DELETE);
      
      ps.setInt(1, code);
      
      if (ps.executeUpdate() > 0) {
        deleted = true;
      } else {
        Logger.getGlobal().log(Level.INFO, "Oggetto StrutturaOspitanteBean non rimosso");
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
    StrutturaOspitanteBean strutturaOspitanteBean = (StrutturaOspitanteBean) product;
    boolean updated = false;
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(StrutturaOspitanteSQL.DO_UPDATE);
      
      ps.setString(1, strutturaOspitanteBean.getNome());
      ps.setString(2, strutturaOspitanteBean.getAmbitoLavorativo());
      ps.setString(3, strutturaOspitanteBean.getNazione());
      ps.setString(4, strutturaOspitanteBean.getRegione());
      ps.setString(5, strutturaOspitanteBean.getCitta());
      ps.setString(6, strutturaOspitanteBean.getVia());
      ps.setInt(7, strutturaOspitanteBean.getNcivico());
      ps.setInt(8, strutturaOspitanteBean.getNdipendenti());
      ps.setInt(9, strutturaOspitanteBean.getID());
      
      if (ps.executeUpdate() > 0) {
        updated = true;
      } else {
        Logger.getGlobal().log(Level.INFO, "Oggetto StrutturaOspitanteBean non aggiornato");
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
  
  public Collection<AbstractBean> doRetrieveByIndirizzo(String nazione, String regione, String citta, String via, int ncivico) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    Collection<AbstractBean> strutture = null;
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(StrutturaOspitanteSQL.DO_RETRIEVE_BY_INDIRIZZO);
      
      ps.setString(1, nazione);
      ps.setString(2, regione);
      ps.setString(3, citta);
      ps.setString(4, via);
      ps.setInt(5, ncivico);
      
      rs = ps.executeQuery();
      
      strutture = new ArrayList<AbstractBean>();
      
      while (rs.next()) {
        int id = rs.getInt("id");
        String nome = rs.getString("nome");
        String ambitoLavorativo = rs.getString("ambitoLavorativo");
        int ndipendenti = rs.getInt("ndipendenti");
        
        strutture.add(new StrutturaOspitanteBean(id, nome, ambitoLavorativo, nazione, regione, citta, via, ncivico, ndipendenti));
      }
      
    } finally {
      try {
        if (ps != null)
          ps.close();
      } finally {
        DriverManagerConnectionPool.releaseConnection(connection);
      }
    }
    
    return strutture;
  }
  
  public Collection<AbstractBean> doRetrieveByAmbitoLavorativo(String text) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    Collection<AbstractBean> strutture = null;
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(StrutturaOspitanteSQL.DO_RETRIEVE_BY_AMBITO_LAVORATIVO);
      
      ps.setString(1, text);
      
      rs = ps.executeQuery();
      
      strutture = new ArrayList<AbstractBean>();
      
      while (rs.next()) {
        int id = rs.getInt("id");
        String nome = rs.getString("nome");
        String ambitoLavorativo = rs.getString("ambitoLavorativo");
        String nazione = rs.getString("nazione");
        String regione = rs.getString("regione");
        String citta = rs.getString("citta");
        String via = rs.getString("via");
        int ncivico = rs.getInt("ncivico");
        int ndipendenti = rs.getInt("ndipendenti");
        
        strutture.add(new StrutturaOspitanteBean(id, nome, ambitoLavorativo, nazione, regione, citta, via, ncivico, ndipendenti));
      }
      
    } finally {
      try {
        if (ps != null)
          ps.close();
      } finally {
        DriverManagerConnectionPool.releaseConnection(connection);
      }
    }
    
    return strutture;
  }
  
  public Collection<AbstractBean> doRetrieveByNdipendenti(int number) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    Collection<AbstractBean> strutture = null;
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(StrutturaOspitanteSQL.DO_RETRIEVE_BY_NDIPENDENTI);
      
      ps.setInt(1, number);
      
      rs = ps.executeQuery();
      
      strutture = new ArrayList<AbstractBean>();
      
      while (rs.next()) {
        int id = rs.getInt("id");
        String nome = rs.getString("nome");
        String ambitoLavorativo = rs.getString("ambitoLavorativo");
        String nazione = rs.getString("nazione");
        String regione = rs.getString("regione");
        String citta = rs.getString("citta");
        String via = rs.getString("via");
        int ncivico = rs.getInt("ncivico");
        
        strutture.add(new StrutturaOspitanteBean(id, nome, ambitoLavorativo, nazione, regione, citta, via, ncivico, number));
      }
      
    } finally {
      try {
        if (ps != null)
          ps.close();
      } finally {
        DriverManagerConnectionPool.releaseConnection(connection);
      }
    }
    
    return strutture;
  }
  
  public Collection<AbstractBean> doRetrieveByNome(String text) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    Collection<AbstractBean> struttureOspitanti = null;
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(StrutturaOspitanteSQL.DO_RETRIEVE_BY_NOME);
      
      ps.setString(1, text);
      
      rs = ps.executeQuery();
      
      struttureOspitanti = new ArrayList<AbstractBean>();
      
      while (rs.next()) {
        int id = rs.getInt("id");
        String nome = rs.getString("nome");
        String ambitoLavorativo = rs.getString("ambitoLavorativo");
        String nazione = rs.getString("nazione");
        String regione = rs.getString("regione");
        String citta = rs.getString("citta");
        String via = rs.getString("via");
        int ncivico = rs.getInt("ncivico");
        int ndipendenti = rs.getInt("ndipendenti");
        
        struttureOspitanti.add(new StrutturaOspitanteBean(id, nome, ambitoLavorativo, nazione, regione, citta, via, ncivico, ndipendenti));
      }
      
    } finally {
      try {
        if (ps != null)
          ps.close();
      } finally {
        DriverManagerConnectionPool.releaseConnection(connection);
      }
    }
    
    return struttureOspitanti;
  }
  
  public AbstractBean doRetrieveByAttivitaTirocinio(int code) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    StrutturaOspitanteBean strutturaOspitanteBean = null;
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(StrutturaOspitanteSQL.DO_RETRIEVE_BY_ATTIVITA_TIROCINIO);
      
      ps.setInt(1, code);
      
      rs = ps.executeQuery();
      
      if (rs.next()) {
        int id = rs.getInt("id");
        String nome = rs.getString("nome");
        String ambitoLavorativo = rs.getString("ambitoLavorativo");
        String nazione = rs.getString("nazione");
        String regione = rs.getString("regione");
        String citta = rs.getString("citta");
        String via = rs.getString("via");
        int ncivico = rs.getInt("ncivico");
        int ndipendenti = rs.getInt("ndipendenti");
        
        strutturaOspitanteBean = new StrutturaOspitanteBean(id, nome, ambitoLavorativo, nazione, regione, citta, via, ncivico, ndipendenti);
      } else {
        Logger.getGlobal().log(Level.INFO, "Nessun attivita di tirocinio trovata con l' id della struttura ospitante specificato");
      }
      
    } finally {
      try {
        if (ps != null)
          ps.close();
      } finally {
        DriverManagerConnectionPool.releaseConnection(connection);
      }
    }
    
    return strutturaOspitanteBean;
  }
  
  public void incrementNdipendenti(AbstractBean product) throws SQLException {
    StrutturaOspitanteBean strutturaOspitanteBean = (StrutturaOspitanteBean) product;
    
    synchronized (strutturaOspitanteBean) {
      strutturaOspitanteBean.setNdipendenti(strutturaOspitanteBean.getNdipendenti() + 1);
    }
    
    if (!doUpdate(strutturaOspitanteBean)) {
      Logger.getGlobal().log(Level.INFO, "Numero dipendenti della struttura ospitante non incrementato");
    }
    
  }
  
  public void decrementNdipendenti(AbstractBean product) throws SQLException {
    StrutturaOspitanteBean strutturaOspitanteBean = (StrutturaOspitanteBean) product;
    
    synchronized (strutturaOspitanteBean) {
      strutturaOspitanteBean.setNdipendenti(strutturaOspitanteBean.getNdipendenti() - 1);
    }
    
    if (!doUpdate(strutturaOspitanteBean)) {
      Logger.getGlobal().log(Level.INFO, "Numero dipendenti della struttura ospitante non decrementato");
    }
    
  }
  
  public void updateNdipendenti(int idStrutturaOspitante, int nDipendenti) throws SQLException {
    StrutturaOspitanteBean strutturaOspitanteBean = null;
    
    try {
      strutturaOspitanteBean = (StrutturaOspitanteBean) doRetrieveByKey(idStrutturaOspitante);
    } catch (SQLException e) {
      Logger.getGlobal().log(Level.INFO, e.getMessage());
    }
    
    synchronized (strutturaOspitanteBean) {
      strutturaOspitanteBean.setNdipendenti(nDipendenti);
    }
    
    if (!doUpdate(strutturaOspitanteBean)) {
      Logger.getGlobal().log(Level.INFO, "Numero dipendenti della struttura ospitante aggiornato");
    }
    
  }
}
