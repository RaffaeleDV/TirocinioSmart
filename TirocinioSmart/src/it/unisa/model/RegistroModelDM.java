package it.unisa.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.sql.PreparedStatement;
import it.unisa.database.DriverManagerConnectionPool;
import it.unisa.sql.RegistroSQL;

public class RegistroModelDM implements BeansModel {

  public static final RegistroModelDM INSTANCE = new RegistroModelDM();

  private RegistroModelDM() {

  }

  @Override
  public AbstractBean doRetrieveByKey(int code) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    RegistroBean registroBean = null;
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(RegistroSQL.DO_RETRIEVE_BY_KEY);
      
      ps.setInt(1, code);
      
      rs = ps.executeQuery();
      
      if (rs.next()) {
        String nome = rs.getString("nome");
        String descrizione = rs.getString("descrizione");
        Date primaIstituzione = rs.getDate("primaIstituzione");
        Date ultimoAgg = rs.getDate("ultimoAgg");
        boolean consegna = rs.getBoolean("consegna");
        boolean confermaTutorAcc = rs.getBoolean("confermaTutorAcc");
        boolean confermaTutorAz = rs.getBoolean("confermaTutorAz");
        boolean confermaUff = rs.getBoolean("confermaUff");
        
        registroBean = new RegistroBean(code, nome, descrizione, primaIstituzione, ultimoAgg, consegna, confermaTutorAcc, confermaTutorAz, confermaUff);
      } else {
        Logger.getGlobal().log(Level.SEVERE, "Registro Non Trovato.");
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

  @Override
  public Collection<AbstractBean> doRetrieveAll(String order) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    Collection<AbstractBean> registri = null;
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(RegistroSQL.DO_RETRIEVE_ALL);
      
      rs = ps.executeQuery();
      
      registri = new ArrayList<AbstractBean>();
      
      while (rs.next()) {
        int id = rs.getInt("id");
        String nome = rs.getString("nome");
        String descrizione = rs.getString("descrizione");
        Date primaIstituzione = rs.getDate("primaIstituzione");
        Date ultimoAgg = rs.getDate("ultimoAgg");
        boolean consegna = rs.getBoolean("consegna");
        boolean confermaTutorAcc = rs.getBoolean("confermaTutorAcc");
        boolean confermaTutorAz = rs.getBoolean("confermaTutorAz");
        boolean confermaUff = rs.getBoolean("confermaUff");
        
        registri.add(new RegistroBean(id, nome, descrizione, primaIstituzione, ultimoAgg, consegna, confermaTutorAcc, confermaTutorAz, confermaUff));
      }
      
    } finally {
      try {
        if (ps != null)
          ps.close();
      } finally {
        DriverManagerConnectionPool.releaseConnection(connection);
      }
    }
    return registri;
  }

  @Override
  public void doSave(AbstractBean product) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    RegistroBean registroBean = null;
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(RegistroSQL.DO_SAVE);
      
      registroBean = (RegistroBean) product;
      
      ps.setInt(1, registroBean.getID());
      ps.setString(2, registroBean.getNome());
      ps.setString(3, registroBean.getDescrizione());
      ps.setDate(4, registroBean.getPrimaIstituzione());
      ps.setDate(5, registroBean.getUltimoAgg());
      ps.setBoolean(6, registroBean.getConsegna());
      ps.setBoolean(7, registroBean.getConfermaTutorAcc());
      ps.setBoolean(8, registroBean.getConfermaTutorAz());
      ps.setBoolean(9, registroBean.getConfermaUff());
      
      if (!(ps.executeUpdate() > 0)) {
        Logger.getGlobal().log(Level.SEVERE, "Oggetto RegistroBean Non Memorizzato.");
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
      ps = connection.prepareStatement(RegistroSQL.DO_DELETE);
      
      ps.setInt(1, code);
      
      if (ps.executeUpdate() > 0) {
        deleted = true;
      } else {
        Logger.getGlobal().log(Level.SEVERE, "Oggetto RegistroBean Non Rimosso.");
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
  
  public boolean doUpdate(AbstractBean product, int codeRegistro) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    RegistroBean registroBean = (RegistroBean) product;
    boolean updated = false;
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(RegistroSQL.DO_UPDATE);
      
      ps.setInt(1, registroBean.getID());
      ps.setString(2, registroBean.getNome());
      ps.setString(3, registroBean.getDescrizione());
      ps.setDate(4, registroBean.getPrimaIstituzione());
      ps.setDate(5, registroBean.getUltimoAgg());
      ps.setBoolean(6, registroBean.getConsegna());
      ps.setBoolean(7, registroBean.getConfermaTutorAcc());
      ps.setBoolean(8, registroBean.getConfermaTutorAz());
      ps.setBoolean(9, registroBean.getConfermaUff());
      ps.setInt(10, codeRegistro);
      
      if (ps.executeUpdate() > 0) {
        updated = true;
      } else {
        Logger.getGlobal().log(Level.SEVERE, "Oggetto RegistroBean Non Aggiornato.");
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
  
  public Collection<AbstractBean> doRetrieveByNome(String text) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    Collection<AbstractBean> registri = null;
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(RegistroSQL.DO_RETRIEVE_BY_NOME);
      
      ps.setString(1, text);
      
      rs = ps.executeQuery();
      
      registri = new ArrayList<AbstractBean>();
      
      while (rs.next()) {
        int id = rs.getInt("id");
        String nome = rs.getString("nome");
        String descrizione = rs.getString("descrizione");
        Date primaIstituzione = rs.getDate("primaIstituzione");
        Date ultimoAgg = rs.getDate("ultimoAgg");
        boolean consegna = rs.getBoolean("consegna");
        boolean confermaTutorAcc = rs.getBoolean("confermaTutorAcc");
        boolean confermaTutorAz = rs.getBoolean("confermaTutorAz");
        boolean confermaUff = rs.getBoolean("confermaUff");
        
        registri.add(new RegistroBean(id, nome, descrizione, primaIstituzione, ultimoAgg, consegna, confermaTutorAcc, confermaTutorAz, confermaUff));
      }
      
    } finally {
      try {
        if (ps != null)
          ps.close();
      } finally {
        DriverManagerConnectionPool.releaseConnection(connection);
      }
    }
    return registri;
  }
  
  public Collection<AbstractBean> doRetrieveByDescrizione(String text) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    Collection<AbstractBean> registri = null;
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(RegistroSQL.DO_RETRIEVE_BY_DESCRIZIONE);
      
      ps.setString(1, text);
      
      rs = ps.executeQuery();
      
      registri = new ArrayList<AbstractBean>();
      
      while (rs.next()) {
        int id = rs.getInt("id");
        String nome = rs.getString("nome");
        String descrizione = rs.getString("descrizione");
        Date primaIstituzione = rs.getDate("primaIstituzione");
        Date ultimoAgg = rs.getDate("ultimoAgg");
        boolean consegna = rs.getBoolean("consegna");
        boolean confermaTutorAcc = rs.getBoolean("confermaTutorAcc");
        boolean confermaTutorAz = rs.getBoolean("confermaTutorAz");
        boolean confermaUff = rs.getBoolean("confermaUff");
        
        registri.add(new RegistroBean(id, nome, descrizione, primaIstituzione, ultimoAgg, consegna, confermaTutorAcc, confermaTutorAz, confermaUff));
      }
      
    } finally {
      try {
        if (ps != null)
          ps.close();
      } finally {
        DriverManagerConnectionPool.releaseConnection(connection);
      }
    }
    
    return registri;
  }
  
  public Collection<AbstractBean> doRetrieveByPrimaIstituzione(Date starting, Date ending) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    Collection<AbstractBean> registri = null;
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(RegistroSQL.DO_RETRIEVE_BY_PRIMA_ISTITUZIONE);
      
      ps.setDate(1, starting);
      ps.setDate(2, ending);
      
      rs = ps.executeQuery();
      
      registri = new ArrayList<AbstractBean>();
      
      while (rs.next()) {
        int id = rs.getInt("id");
        String nome = rs.getString("nome");
        String descrizione = rs.getString("descrizione");
        Date primaIstituzione = rs.getDate("primaIstituzione");
        Date ultimoAgg = rs.getDate("ultimoAgg");
        boolean consegna = rs.getBoolean("consegna");
        boolean confermaTutorAcc = rs.getBoolean("confermaTutorAcc");
        boolean confermaTutorAz = rs.getBoolean("confermaTutorAz");
        boolean confermaUff = rs.getBoolean("confermaUff");
        
        registri.add(new RegistroBean(id, nome, descrizione, primaIstituzione, ultimoAgg, consegna, confermaTutorAcc, confermaTutorAz, confermaUff));
      }
      
    } finally {
      try {
        if (ps != null)
          ps.close();
      } finally {
        DriverManagerConnectionPool.releaseConnection(connection);
      }
    }
    return registri;
  }
  
  public Collection<AbstractBean> doRetrieveByUltimoAgg(Date starting, Date ending) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    Collection<AbstractBean> registri = null;
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(RegistroSQL.DO_RETRIEVE_BY_ULTIMO_AGG);
      
      ps.setDate(1, starting);
      ps.setDate(2, ending);
      
      rs = ps.executeQuery();
      
      registri = new ArrayList<AbstractBean>();
      
      while (rs.next()) {
        int id = rs.getInt("id");
        String nome = rs.getString("nome");
        String descrizione = rs.getString("descrizione");
        Date primaIstituzione = rs.getDate("primaIstituzione");
        Date ultimoAgg = rs.getDate("ultimoAgg");
        boolean consegna = rs.getBoolean("consegna");
        boolean confermaTutorAcc = rs.getBoolean("confermaTutorAcc");
        boolean confermaTutorAz = rs.getBoolean("confermaTutorAz");
        boolean confermaUff = rs.getBoolean("confermaUff");
        
        registri.add(new RegistroBean(id, nome, descrizione, primaIstituzione, ultimoAgg, consegna, confermaTutorAcc, confermaTutorAz, confermaUff));
      }
      
    } finally {
      try {
        if (ps != null)
          ps.close();
      } finally {
        DriverManagerConnectionPool.releaseConnection(connection);
      }
    }
    return registri;
  }
  
  public Collection<AbstractBean> doRetrieveByConsegna(boolean consegna) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    Collection<AbstractBean> registri = null;
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(RegistroSQL.DO_RETRIEVE_BY_CONSEGNA);
      
      ps.setBoolean(1, consegna);
      
      rs = ps.executeQuery();
      
      registri = new ArrayList<AbstractBean>();
      
      while (rs.next()) {
        int id = rs.getInt("id");
        String nome = rs.getString("nome");
        String descrizione = rs.getString("descrizione");
        Date primaIstituzione = rs.getDate("primaIstituzione");
        Date ultimoAgg = rs.getDate("ultimoAgg");
        boolean confermaTutorAcc = rs.getBoolean("confermaTutorAcc");
        boolean confermaTutorAz = rs.getBoolean("confermaTutorAz");
        boolean confermaUff = rs.getBoolean("confermaUff");
        
        registri.add(new RegistroBean(id, nome, descrizione, primaIstituzione, ultimoAgg, consegna, confermaTutorAcc, confermaTutorAz, confermaUff));
      }
      
    } finally {
      try {
        if (ps != null)
          ps.close();
      } finally {
        DriverManagerConnectionPool.releaseConnection(connection);
      }
    }
    
    return registri;
  }
  
  public Collection<AbstractBean> doRetrieveByConfermaTutorAcc(boolean confermaTutorAcc) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    Collection<AbstractBean> registri = null;
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(RegistroSQL.DO_RETRIEVE_BY_CONFERMA_TUTOR_ACC);
      
      ps.setBoolean(1, confermaTutorAcc);
      
      rs = ps.executeQuery();
      
      registri = new ArrayList<AbstractBean>();
      
      while (rs.next()) {
        int id = rs.getInt("id");
        String nome = rs.getString("nome");
        String descrizione = rs.getString("descrizione");
        Date primaIstituzione = rs.getDate("primaIstituzione");
        Date ultimoAgg = rs.getDate("ultimoAgg");
        boolean consegna = rs.getBoolean("consegna");
        boolean confermaTutorAz = rs.getBoolean("confermaTutorAz");
        boolean confermaUff = rs.getBoolean("confermaUff");
        
        registri.add(new RegistroBean(id, nome, descrizione, primaIstituzione, ultimoAgg, consegna, confermaTutorAcc, confermaTutorAz, confermaUff));
      }
      
    } finally {
      try {
        if (ps != null)
          ps.close();
      } finally {
        DriverManagerConnectionPool.releaseConnection(connection);
      }
    }
    return registri;
  }
  
  public Collection<AbstractBean> doRetrieveByConfermaTutorAz(boolean confermaTutorAz) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    Collection<AbstractBean> registri = null;
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(RegistroSQL.DO_RETRIEVE_BY_CONFERMA_TUTOR_AZ);
      
      ps.setBoolean(1, confermaTutorAz);
      
      rs = ps.executeQuery();
      
      registri = new ArrayList<AbstractBean>();
      
      while (rs.next()) {
        int id = rs.getInt("id");
        String nome = rs.getString("nome");
        String descrizione = rs.getString("descrizione");
        Date primaIstituzione = rs.getDate("primaIstituzione");
        Date ultimoAgg = rs.getDate("ultimoAgg");
        boolean consegna = rs.getBoolean("consegna");
        boolean confermaTutorAcc = rs.getBoolean("confermaTutorAcc");
        boolean confermaUff = rs.getBoolean("confermaUff");
        
        registri.add(new RegistroBean(id, nome, descrizione, primaIstituzione, ultimoAgg, consegna, confermaTutorAcc, confermaTutorAz, confermaUff));
      }
      
    } finally {
      try {
        if (ps != null)
          ps.close();
      } finally {
        DriverManagerConnectionPool.releaseConnection(connection);
      }
    }
    return registri;
  }
  
  public Collection<AbstractBean> doRetrieveByConfermaTutorUff(boolean confermaTutorUff) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    Collection<AbstractBean> registri = null;
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(RegistroSQL.DO_RETRIEVE_BY_CONFERMA_UFF);
      
      ps.setBoolean(1, confermaTutorUff);
      
      rs = ps.executeQuery();
      
      registri = new ArrayList<AbstractBean>();
      
      while (rs.next()) {
        int id = rs.getInt("id");
        String nome = rs.getString("nome");
        String descrizione = rs.getString("descrizione");
        Date primaIstituzione = rs.getDate("primaIstituzione");
        Date ultimoAgg = rs.getDate("ultimoAgg");
        boolean consegna = rs.getBoolean("consegna");
        boolean confermaTutorAcc = rs.getBoolean("confermaTutorAcc");
        boolean confermaTutorAz = rs.getBoolean("confermaTutorAz");
        
        registri.add(new RegistroBean(id, nome, descrizione, primaIstituzione, ultimoAgg, consegna, confermaTutorAcc, confermaTutorAz, confermaTutorUff));
      }
      
    } finally {
      try {
        if (ps != null)
          ps.close();
      } finally {
        DriverManagerConnectionPool.releaseConnection(connection);
      }
    }
    return registri;
  }
  
  public AbstractBean doRetrieveByStudente(int code) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    RegistroBean registroBean = null;
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(RegistroSQL.DO_RETRIEVE_BY_STUDENTE);
      
      ps.setInt(1, code);
      
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
  
  public Collection<AbstractBean> doRetrieveByTutorAz(int code) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    Collection<AbstractBean> registri = null;
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(RegistroSQL.DO_RETRIEVE_BY_TUTOR_AZ);
      
      ps.setInt(1, code);
      
      rs = ps.executeQuery();
      
      registri = new ArrayList<AbstractBean>();
      
      while (rs.next()) {
        int id = rs.getInt("id");
        String nome = rs.getString("nome");
        String descrizione = rs.getString("descrizione");
        Date primaIstituzione = rs.getDate("primaIstituzione");
        Date ultimoAgg = rs.getDate("ultimoAgg");
        boolean consegna = rs.getBoolean("consegna");
        boolean confermaTutorAcc = rs.getBoolean("confermaTutorAcc");
        boolean confermaTutorAz = rs.getBoolean("confermaTutorAz");
        boolean confermaUff = rs.getBoolean("confermaUff");
        
        registri.add(new RegistroBean(id, nome, descrizione, primaIstituzione, ultimoAgg, consegna, confermaTutorAcc, confermaTutorAz, confermaUff));
      }
      
    } finally {
      try {
        if (ps != null)
          ps.close();
      } finally {
        DriverManagerConnectionPool.releaseConnection(connection);
      }
    }
    
    return registri;
  }
  
  public Collection<AbstractBean> doRetrieveByTutorAcc(int code) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    Collection<AbstractBean> registri = null;
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(RegistroSQL.DO_RETRIEVE_BY_TUTOR_ACC);
      
      ps.setInt(1, code);
      
      rs = ps.executeQuery();
      
      registri = new ArrayList<AbstractBean>();
      
      while (rs.next()) {
        int id = rs.getInt("id");
        String nome = rs.getString("nome");
        String descrizione = rs.getString("descrizione");
        Date primaIstituzione = rs.getDate("primaIstituzione");
        Date ultimoAgg = rs.getDate("ultimoAgg");
        boolean consegna = rs.getBoolean("consegna");
        boolean confermaTutorAcc = rs.getBoolean("confermaTutorAcc");
        boolean confermaTutorAz = rs.getBoolean("confermaTutorAz");
        boolean confermaUff = rs.getBoolean("confermaUff");
        
        registri.add(new RegistroBean(id, nome, descrizione, primaIstituzione, ultimoAgg, consegna, confermaTutorAcc, confermaTutorAz, confermaUff));
      }
      
    } finally {
      try {
        if (ps != null)
          ps.close();
      } finally {
        DriverManagerConnectionPool.releaseConnection(connection);
      }
    }
    
    return registri;
  }
  
  public AbstractBean doRetrieveByProgettoFormativo(int code) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    RegistroBean registroBean = null;
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(RegistroSQL.DO_RETRIEVE_BY_PROGETTO_FORMATIVO);
      
      ps.setInt(1, code);
      
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
        Logger.getGlobal().log(Level.SEVERE, "Oggetto RegistroBean Non Trovato Con Il Progetto Formativo Specificato.");
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
  
  public Collection<AbstractBean> doRetrieveByUfficio(int code) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    Collection<AbstractBean> registri = null;
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(RegistroSQL.DO_RETRIEVE_BY_UFFICIO);
      
      ps.setInt(1, code);
      
      rs = ps.executeQuery();
      
      registri = new ArrayList<AbstractBean>();
      
      while(rs.next()) {
        int id = rs.getInt("id");
        String nome = rs.getString("nome");
        String descrizione = rs.getString("descrizione");
        Date primaIstituzione = rs.getDate("primaIstituzione");
        Date ultimoAgg = rs.getDate("ultimoAgg");
        boolean consegna = rs.getBoolean("consegna");
        boolean confermaTutorAcc = rs.getBoolean("confermaTutorAcc");
        boolean confermaTutorAz = rs.getBoolean("confermaTutorAz");
        boolean confermaUff = rs.getBoolean("confermaUff");
        
        registri.add(new RegistroBean(id, nome, descrizione, primaIstituzione, ultimoAgg, consegna, confermaTutorAcc, confermaTutorAz, confermaUff));
      }
      
    } finally {
      try {
        if (ps != null)
          ps.close();
      } finally {
        DriverManagerConnectionPool.releaseConnection(connection);
      }
    }
    return registri;
  }
    
  public boolean isStudenteRegistro(String text, int code) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    boolean isStudenteRegistro = false;
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(RegistroSQL.IS_STUDENTE_REGISTRO);
      
      ps.setString(1, text);
      ps.setInt(2, code);
      
      rs = ps.executeQuery();
      
      if (rs.next()) {
        isStudenteRegistro = true;
      } else {
        Logger.getGlobal().log(Level.SEVERE, "Gli Oggetti StudenteBean Ed RegistroBean Non Corrispondono.");
      }
      
    } finally {
      try {
        if (ps != null)
          ps.close();
      } finally {
        DriverManagerConnectionPool.releaseConnection(connection);
      }
    }
    return isStudenteRegistro;
  }
  
  public boolean isTutorAccRegistro(int idTutorAcc, int registro) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    boolean isTutorAccRegistro = false;
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(RegistroSQL.IS_TUTOR_ACC_REGISTRO);
      
      ps.setInt(1, idTutorAcc);
      ps.setInt(2, registro);
      
      rs = ps.executeQuery();
      
      if (rs.next()) {
        isTutorAccRegistro = true;
      } else {
        Logger.getGlobal().log(Level.SEVERE, "Gli Oggetti TutorBean Ed RegistroBean Non Corrispondono.");
      }
      
    } finally {
      try {
        if (ps != null)
          ps.close();
      } finally {
        DriverManagerConnectionPool.releaseConnection(connection);
      }
    }
    return isTutorAccRegistro;
  }
  
  public boolean isTutorAzRegistro(int idTutorAz, int registro) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    boolean isTutorAzRegistro = false;
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(RegistroSQL.IS_TUTOR_AZ_REGISTRO);
      
      ps.setInt(1, idTutorAz);
      ps.setInt(2, registro);
      
      rs = ps.executeQuery();
      
      if (rs.next()) {
        isTutorAzRegistro = true;
      } else {
        Logger.getGlobal().log(Level.SEVERE, "Gli Oggetti TutorBean Ed RegistroBean Non Corrispondono.");
      }
      
    } finally {
      try {
        if (ps != null)
          ps.close();
      } finally {
        DriverManagerConnectionPool.releaseConnection(connection);
      }
    }
    return isTutorAzRegistro;
  }
  
  public boolean isUfficioRegistro(int ufficioID, int registroID) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    boolean isUfficioRegistro = false;
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(RegistroSQL.IS_UFFICIO_REGISTRO);
      
      ps.setInt(1, ufficioID);
      ps.setInt(2, registroID);
      
      rs = ps.executeQuery();
      
      if (rs.next()) {
        isUfficioRegistro = true;
      } else {
        Logger.getGlobal().log(Level.SEVERE, "Gli Oggetti UfficioBean Ed RegistroBean Non Corrispondono.");
      }
      
    } finally {
      try {
        if (ps != null)
          ps.close();
      } finally {
        DriverManagerConnectionPool.releaseConnection(connection);
      }
    }
    return isUfficioRegistro;
  }
}