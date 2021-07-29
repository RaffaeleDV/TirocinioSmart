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
import it.unisa.sql.AttivitaTirocinioSQL;

public class AttivitaTirocinioModelDM implements BeansModel {

  public static final AttivitaTirocinioModelDM INSTANCE = new AttivitaTirocinioModelDM();

  private AttivitaTirocinioModelDM() {

  }

  @Override
  public AbstractBean doRetrieveByKey(int code) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    AttivitaTirocinioBean attivitaTirocinioBean = null;
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(AttivitaTirocinioSQL.DO_RETRIEVE_BY_KEY);
      ps.setInt(1, code);
      
      rs = ps.executeQuery();
      
      if (rs.next()) {
        int registroID = rs.getInt("registroID");
        int strutturaOspitanteID = rs.getInt("strutturaOspitanteID");
        String descrizione = rs.getString("descrizione");
        Date data = rs.getDate("data");
        int ore = rs.getInt("ore");
        
        attivitaTirocinioBean = new AttivitaTirocinioBean(code, registroID, strutturaOspitanteID, descrizione, data, ore);
      } else {
        Logger.getGlobal().log(Level.SEVERE, "Oggetto AttivitaTirocinioBean Non Trovato.");
      }
      
    } finally {
      try {
        if (ps != null)
          ps.close();
      } finally {
        DriverManagerConnectionPool.releaseConnection(connection);
      }
    }
    
    return attivitaTirocinioBean;
  }

  @Override
  public Collection<AbstractBean> doRetrieveAll(String order) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    Collection<AbstractBean> attivitaTirocinio = null;
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(AttivitaTirocinioSQL.DO_RETRIEVE_ALL);
      
      rs = ps.executeQuery();
      
      attivitaTirocinio = new ArrayList<AbstractBean>();
      
      while (rs.next()) {
        int id = rs.getInt("id");
        int registroID = rs.getInt("registroID");
        int strutturaOspitanteID = rs.getInt("strutturaOspitanteID");
        String descrizione = rs.getString("descrizione");
        Date data = rs.getDate("data");
        int ore = rs.getInt("ore");
        
        attivitaTirocinio.add(new AttivitaTirocinioBean(id, registroID, strutturaOspitanteID, descrizione, data, ore));
      }
      
    } finally {
      try {
        if (ps != null)
          ps.close();
      } finally {
        DriverManagerConnectionPool.releaseConnection(connection);
      }
    }
    
    return attivitaTirocinio;
  }

  @Override
  public void doSave(AbstractBean product) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(AttivitaTirocinioSQL.DO_SAVE);
      
      AttivitaTirocinioBean attivita = (AttivitaTirocinioBean) product;
      
      ps.setInt(1, attivita.getID());
      ps.setInt(2, attivita.getRegistroID());
      ps.setInt(3, attivita.getStrutturaOspitanteID());
      ps.setString(4, attivita.getDescrizione());
      ps.setDate(5, attivita.getData());
      ps.setInt(6, attivita.getOre());
      
      if (!(ps.executeUpdate() > 0)) {
        Logger.getGlobal().log(Level.SEVERE, "Oggetto AttivitaTirocinioBean Non Memorizzato.");
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
      ps = connection.prepareStatement(AttivitaTirocinioSQL.DO_DELETE);
      
      ps.setInt(1, code);
      
      if (ps.executeUpdate() > 0) {
        deleted = true;
      } else {
        Logger.getGlobal().log(Level.SEVERE, "Oggetto AttivitaTirocinioBean Non Rimosso.");
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
  
  public boolean doUpdate(AbstractBean product, int oldID) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    AttivitaTirocinioBean attivitaTirocinioBean = (AttivitaTirocinioBean) product;
    boolean updated = false;
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(AttivitaTirocinioSQL.DO_UPDATE);
      
      ps.setInt(1, attivitaTirocinioBean.getID());
      ps.setInt(2, attivitaTirocinioBean.getRegistroID());
      ps.setInt(3, attivitaTirocinioBean.getStrutturaOspitanteID());
      ps.setString(4, attivitaTirocinioBean.getDescrizione());
      ps.setDate(5, attivitaTirocinioBean.getData());
      ps.setInt(6, attivitaTirocinioBean.getOre());
      ps.setInt(7, oldID);
      
      if (ps.executeUpdate() > 0) {
        updated = true;
      } else {
        Logger.getGlobal().log(Level.SEVERE, "Oggetto AttivitaTirocinioBean Non Aggiornato.");
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
  
  public Collection<AbstractBean> doRetrieveByRegistro(int code) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    Collection<AbstractBean> attivitaDelRegistro = null;
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(AttivitaTirocinioSQL.DO_RETRIEVE_BY_REGISTRO);
      
      ps.setInt(1, code);
      
      rs = ps.executeQuery();
      
      attivitaDelRegistro = new ArrayList<AbstractBean>();
      
      while (rs.next()) {
        int id = rs.getInt("id");
        int strutturaOspitanteID = rs.getInt("strutturaOspitanteID");
        String descrizione = rs.getString("descrizione");
        Date data = rs.getDate("data");
        int ore = rs.getInt("ore");
        
        attivitaDelRegistro.add(new AttivitaTirocinioBean(id, code, strutturaOspitanteID, descrizione, data, ore));
      }
      
    } finally {
      try {
        if (ps != null)
          ps.close();
      } finally {
        DriverManagerConnectionPool.releaseConnection(connection);
      }
    }
    
    return attivitaDelRegistro;
  }
  
  public Collection<AbstractBean> doRetrieveByStrutturaOspitante(int code) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    Collection<AbstractBean> attivitaPerStrutturaOspitante = null;
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(AttivitaTirocinioSQL.DO_RETRIEVE_BY_STRUTTURA_OSPITANTE);
      
      ps.setInt(1, code);
      
      rs = ps.executeQuery();
      
      attivitaPerStrutturaOspitante = new ArrayList<AbstractBean>();
      
      while (rs.next()) {
        int id = rs.getInt("id");
        int registroID = rs.getInt("registroID");
        String descrizione = rs.getString("descrizione");
        Date data = rs.getDate("data");
        int ore = rs.getInt("ore");
        
        attivitaPerStrutturaOspitante.add(new AttivitaTirocinioBean(id, registroID, code, descrizione, data, ore));
      }
      
    } finally {
      try {
        if (ps != null)
          ps.close();
      } finally {
        DriverManagerConnectionPool.releaseConnection(connection);
      }
    }
    
    return attivitaPerStrutturaOspitante;
  }
  
  public Collection<AbstractBean> doRetrieveByDescrizione(String text) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    Collection<AbstractBean> attivita = null;
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(AttivitaTirocinioSQL.DO_RETRIEVE_BY_DESCRIZIONE);
      
      ps.setString(1, text);
      
      rs = ps.executeQuery();
      
      attivita = new ArrayList<AbstractBean>();
      
      while (rs.next()) {
        int id = rs.getInt("id");
        int registroID = rs.getInt("registroID");
        int strutturaOspitanteID = rs.getInt("strutturaOspitanteID");
        String descrizione = rs.getString("descrizione");
        Date data = rs.getDate("data");
        int ore = rs.getInt("ore");
        attivita.add(new AttivitaTirocinioBean(id, registroID, strutturaOspitanteID, descrizione, data, ore));
      }
    } finally {
      try {
        if (ps != null)
          ps.close();
      } finally {
        DriverManagerConnectionPool.releaseConnection(connection);
      }
    }
    return attivita;
  }
  
  public Collection<AbstractBean> doRetrieveByDataBetween(Date startTime, Date endTime) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    Collection<AbstractBean> attivitaTraDate = null;
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(AttivitaTirocinioSQL.DO_RETRIEVE_BY_DATA_BETWEEN);
      
      ps.setDate(1, startTime);
      ps.setDate(2, endTime);
      
      rs = ps.executeQuery();
      
      attivitaTraDate = new ArrayList<AbstractBean>();
      
      while (rs.next()) {
        int id = rs.getInt("id");
        int registroID = rs.getInt("registroID");
        int strutturaOspitanteID = rs.getInt("strutturaOspitanteID");
        String descrizione = rs.getString("descrizione");
        Date data = rs.getDate("data");
        int ore = rs.getInt("ore");
        
        attivitaTraDate.add(new AttivitaTirocinioBean(id, registroID, strutturaOspitanteID, descrizione, data, ore));
      }
      
    } finally {
      try {
        if (ps != null)
          ps.close();
      } finally {
        DriverManagerConnectionPool.releaseConnection(connection);
      }
    }
    
    return attivitaTraDate;
  }
  
  public Collection<AbstractBean> doRetrieveByOreBetween(int inizio, int termine) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    Collection<AbstractBean> attivitaTraOre = null;
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(AttivitaTirocinioSQL.DO_RETRIEVE_BY_ORE_BETWEEN);
      
      ps.setInt(1, inizio);
      ps.setInt(2, termine);
      
      rs = ps.executeQuery();
      
      attivitaTraOre = new ArrayList<AbstractBean>();
      
      while (rs.next()) {
        int id = rs.getInt("id");
        int registroID = rs.getInt("registroID");
        int strutturaOspitanteID = rs.getInt("strutturaOspitanteID");
        String descrizione = rs.getString("descrizione");
        Date data = rs.getDate("data");
        int ore = rs.getInt("ore");
        
        attivitaTraOre.add(new AttivitaTirocinioBean(id, registroID, strutturaOspitanteID, descrizione, data, ore));
      }
      
    } finally {
      try {
        if (ps != null)
          ps.close();
      } finally {
        DriverManagerConnectionPool.releaseConnection(connection);
      }
    }
    
    return attivitaTraOre;
  }
  
  public AbstractBean doRetrieveRegistro(AbstractBean product) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    RegistroBean registroBean = null;
    AttivitaTirocinioBean attivitaTirocinioBean = (AttivitaTirocinioBean) product;
    
    if (product.getClass().getName().equals(AttivitaTirocinioBean.class.getName())) {
      attivitaTirocinioBean = (AttivitaTirocinioBean) product;
    } else {
      return null;
    }
    
    try {
      connection = (Connection) DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(AttivitaTirocinioSQL.DO_RETRIEVE_REGISTRO);
      
      ps.setInt(1, attivitaTirocinioBean.getRegistroID());
      
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
        if (ps != null) {
          ps.close();
        }
      } finally {
        DriverManagerConnectionPool.releaseConnection(connection);
      }
    }
    return registroBean;
  }
  
  public AbstractBean doRetrieveStrutturaOspitante(AbstractBean product) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    StrutturaOspitanteBean strutturaOspitanteBean = null;
    AttivitaTirocinioBean attivitaTirocinioBean = null;
    
    if (product.getClass().getName().equals(AttivitaTirocinioBean.class.getName())) {
      attivitaTirocinioBean = (AttivitaTirocinioBean) product;
    }
    
    try {
      connection = (Connection) DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(AttivitaTirocinioSQL.DO_RETRIEVE_STRUTTURA_OSPITANTE);
      
      ps.setInt(1, attivitaTirocinioBean.getStrutturaOspitanteID());
      
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
        Logger.getGlobal().log(Level.SEVERE, "Oggetto StrutturaOspitanteBean Non Trovato.");
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
}
