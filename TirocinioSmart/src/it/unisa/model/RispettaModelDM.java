package it.unisa.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.mysql.jdbc.Connection;

import it.unisa.database.DriverManagerConnectionPool;
import it.unisa.sql.RispettaSQL;

public class RispettaModelDM implements BeansModel {

  public static final RispettaModelDM INSTANCE = new RispettaModelDM();

  private RispettaModelDM() {

  }

  public AbstractBean doRetrieveByKey(int convenzioneID, int progFormativoID) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    RispettaBean rispettaBean = null;
    
    try {
      connection = (Connection) DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(RispettaSQL.DO_RETRIEVE_BY_KEY);
      
      ps.setInt(1, convenzioneID);
      ps.setInt(2, progFormativoID);
      
      rs = ps.executeQuery();
      
      if (rs.next()) {
        int assicurazioneID = rs.getInt("assicurazioneID");
        rispettaBean = new RispettaBean(convenzioneID, progFormativoID, assicurazioneID);
      } else {
        Logger.getGlobal().log(Level.SEVERE, "RispettaBean Non Trovato Con Gli IDs Specificati.");
      }
    } finally {
      try {
        if (ps != null)
          ps.close();
      } finally {
        DriverManagerConnectionPool.releaseConnection(connection);
      }
    }
    return rispettaBean;
  }

  @Override
  public Collection<AbstractBean> doRetrieveAll(String order) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    Collection<AbstractBean> rispettaList = null;
    
    try {
      connection = (Connection) DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(RispettaSQL.DO_RETRIEVE_ALL);
      
      rispettaList = new ArrayList<AbstractBean>();
      
      rs = ps.executeQuery();
      
      while (rs.next()) {
        int convenzioneID = rs.getInt("convenzioneID");
        int progFormativoID = rs.getInt("progettoFormativoID");
        int assicurazioneID = rs.getInt("assicurazioneID");
        rispettaList.add(new RispettaBean(convenzioneID, progFormativoID, assicurazioneID));
      }
    } finally {
      try {
        if (ps != null)
          ps.close();
      } finally {
        DriverManagerConnectionPool.releaseConnection(connection);
      }
    }
    return rispettaList;
  }

  @Override
  public void doSave(AbstractBean product) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    RispettaBean rispettaBean = (RispettaBean) product;
    
    try {
      connection = (Connection) DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(RispettaSQL.DO_SAVE);
      
      ps.setInt(1, rispettaBean.getConvenzioneID());
      ps.setInt(2, rispettaBean.getProgettoFormativoID());
      ps.setInt(3, rispettaBean.getAssicurazioneID());
      
      if (!(ps.executeUpdate() > 0)) {
        Logger.getGlobal().log(Level.SEVERE, "RispettaBean Non Memorizzato.");
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

  public boolean doDelete(int codeConvenzione, int codeProgettoFormativo) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    boolean deleted = false;
    
    try {
      connection = (Connection) DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(RispettaSQL.DO_DELETE);
      
      ps.setInt(1, codeConvenzione);
      ps.setInt(2, codeProgettoFormativo);
      
      if (ps.executeUpdate() > 0) {
        deleted = true;
      } else {
        Logger.getGlobal().log(Level.SEVERE, "RispettaBean Non Rimosso.");  
      }
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
  
  public boolean doUpdate(AbstractBean abstractBean, int codeConvenzione, int codeProgettoFormativo) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    RispettaBean rispettaBean = (RispettaBean) abstractBean;
    boolean updated = false;
    
    try {
      connection = (Connection) DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(RispettaSQL.DO_UPDATE);
      
      ps.setInt(1, rispettaBean.getConvenzioneID());
      ps.setInt(2, rispettaBean.getProgettoFormativoID());
      ps.setInt(3, rispettaBean.getAssicurazioneID());
      ps.setInt(4, codeConvenzione);
      ps.setInt(5, codeProgettoFormativo);
      
      if (ps.executeUpdate() > 0) {
        updated = true;
      } else {
        Logger.getGlobal().log(Level.SEVERE, "Oggetto RispettaBean Non Aggiornato.");
      }
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
  
  public Collection<AbstractBean> doRetrieveByConvenzione(int code) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    Collection<AbstractBean> rispettaList = null;
    
    try {
      connection = (Connection) DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(RispettaSQL.DO_RETRIEVE_BY_CONVENZIONE);
      
      ps.setInt(1, code);
      
      rispettaList = new ArrayList<AbstractBean>();
      
      rs = ps.executeQuery();
      
      while (rs.next()) {
        int convenzioneID = rs.getInt("convenzioneID");
        int progFormativoID = rs.getInt("progettoFormativoID");
        int assicurazioneID = rs.getInt("assicurazioneID");
        rispettaList.add(new RispettaBean(convenzioneID, progFormativoID, assicurazioneID));
      }
    } finally {
      try {
        if (ps != null)
          ps.close();
      } finally {
        DriverManagerConnectionPool.releaseConnection(connection);
      }
    }
    return rispettaList;
  }
  
  public Collection<AbstractBean> doRetrieveByProgettoFormativoID(int code) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    Collection<AbstractBean> rispettaList = null;
    
    try {
      connection = (Connection) DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(RispettaSQL.DO_RETRIEVE_BY_PROGETTOFORMATIVO);
      
      ps.setInt(1, code);
      
      rispettaList = new ArrayList<AbstractBean>();
      
      rs = ps.executeQuery();
      
      while (rs.next()) {
        int convenzioneID = rs.getInt("convenzioneID");
        int assicurazioneID = rs.getInt("assicurazioneID");
        rispettaList.add(new RispettaBean(convenzioneID, code, assicurazioneID));
      }
    } finally {
      try {
        if (ps != null)
          ps.close();
      } finally {
        DriverManagerConnectionPool.releaseConnection(connection);
      }
    }
    return rispettaList;
  }
  
  public Collection<AbstractBean> doRetrieveByAssicurazioneID(int code) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    Collection<AbstractBean> rispettaList = new ArrayList<AbstractBean>();
    
    try {
      connection = (Connection) DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(RispettaSQL.DO_RETRIEVE_BY_ID_ASSICURAZIONE);
      
      ps.setInt(1, code);
      
      rs = ps.executeQuery();
      while (rs.next()) {
        int convenzioneID = rs.getInt("convenzioneID");
        int progFormativoID = rs.getInt("progettoFormativoID");
        rispettaList.add(new RispettaBean(convenzioneID, progFormativoID, code));
      }
    } finally {
      try {
        if (ps != null)
          ps.close();
      } finally {
        DriverManagerConnectionPool.releaseConnection(connection);
      }
    }
    
    return rispettaList;
  }

  @Override
  public AbstractBean doRetrieveByKey(int code) throws SQLException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public boolean doDelete(int code) throws SQLException {
    // TODO Auto-generated method stub
    return false;
  }
}
