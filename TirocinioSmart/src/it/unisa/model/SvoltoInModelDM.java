package it.unisa.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Date;
import java.sql.ResultSet;
import java.util.Collection;
import java.util.logging.Logger;
import java.util.ArrayList;
import java.util.logging.Level;
import it.unisa.database.DriverManagerConnectionPool;
import it.unisa.sql.SvoltoInSQL;

public class SvoltoInModelDM implements BeansModel {

  @Override
  public AbstractBean doRetrieveByKey(int code) throws SQLException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Collection<AbstractBean> doRetrieveAll(String order) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    Collection<AbstractBean> svolto = null;
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(SvoltoInSQL.DO_RETRIEVE_ALL);
      
      rs = ps.executeQuery();
      
      svolto = new ArrayList<AbstractBean>();
      
      while (rs.next()) {
        int progettoFormativoID = rs.getInt("progettoFormativoID");
        int strutturaOspitanteID = rs.getInt("strutturaOspitanteID");
        int tutorAzID = rs.getInt("tutorAzID");
        Date inizioPeriodo = rs.getDate("inizioPeriodo");
        Date terminePeriodo = rs.getDate("terminePeriodo");
        
        svolto.add(new SvoltoInBean(progettoFormativoID, strutturaOspitanteID, tutorAzID, inizioPeriodo, terminePeriodo));
      }
      
    } finally {
      try {
        if (ps != null)
          ps.close();
      } finally {
        DriverManagerConnectionPool.releaseConnection(connection);
      }
    }
    
    return svolto;
  }

  @Override
  public void doSave(AbstractBean product) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    SvoltoInBean svoltoInBean = null;
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(SvoltoInSQL.DO_SAVE);
      
      svoltoInBean = (SvoltoInBean) product;
      
      ps.setInt(1, svoltoInBean.getProgettoFormativoID());
      ps.setInt(2, svoltoInBean.getStrutturaOspitanteID());
      ps.setInt(3, svoltoInBean.getTutorAzID());
      ps.setDate(4, svoltoInBean.getInizioPeriodo());
      ps.setDate(5, svoltoInBean.getTerminePeriodo());
      
      if (!(ps.executeUpdate() > 0)) {
        Logger.getGlobal().log(Level.INFO, "Oggetto SvoltoInBean non memorizzato");
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
    // TODO Auto-generated method stub
    return false;
  }
  
  public Collection<AbstractBean> doRetrieveByProgettoFormativo(int code) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    Collection<AbstractBean> svolto = null;
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(SvoltoInSQL.DO_RETRIEVE_BY_PROGETTO_FORMATIVO);
      
      ps.setInt(1, code);
      
      rs = ps.executeQuery();
      
      svolto = new ArrayList<AbstractBean>();
      
      while (rs.next()) {
        int strutturaOspitanteID = rs.getInt("strutturaOspitanteID");
        int tutorAzID = rs.getInt("tutorAzID");
        Date inizioPeriodo = rs.getDate("inizioPeriodo");
        Date terminePeriodo = rs.getDate("terminePeriodo");
        
        svolto.add(new SvoltoInBean(code, strutturaOspitanteID, tutorAzID, inizioPeriodo, terminePeriodo));
      }
      
    } finally {
      try {
        if (ps != null)
          ps.close();
      } finally {
        DriverManagerConnectionPool.releaseConnection(connection);
      }
    }
    
    return svolto;
  }
  
  public Collection<AbstractBean> doRetrieveByStrutturaOspitante(int code) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    Collection<AbstractBean> svolto = null;
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(SvoltoInSQL.DO_RETRIEVE_BY_STRUTTURA_OSPITANTE);
      
      ps.setInt(1, code);
      
      rs = ps.executeQuery();
      
      svolto = new ArrayList<AbstractBean>();
      
      while (rs.next()) {
        int progettoFormativoID = rs.getInt("progettoFormativoID");
        int tutorAzID = rs.getInt("tutorAzID");
        Date inizioPeriodo = rs.getDate("inizioPeriodo");
        Date terminePeriodo = rs.getDate("terminePeriodo");
        
        svolto.add(new SvoltoInBean(progettoFormativoID, code, tutorAzID, inizioPeriodo, terminePeriodo));
      }
      
    } finally {
      try {
        if (ps != null)
          ps.close();
      } finally {
        DriverManagerConnectionPool.releaseConnection(connection);
      }
    }
    
    return svolto;
  }
  
  public Collection<AbstractBean> doRetrieveByTutorAz(int code) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    Collection<AbstractBean> svolto = null;
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(SvoltoInSQL.DO_RETRIEVE_BY_TUTOR_AZ);
      
      ps.setInt(1, code);
      
      rs = ps.executeQuery();
      
      svolto = new ArrayList<AbstractBean>();
      
      while (rs.next()) {
        int progettoFormativoID = rs.getInt("progettoFormativoID");
        int strutturaOspitanteID = rs.getInt("strutturaOspitanteID");
        Date inizioPeriodo = rs.getDate("inizioPeriodo");
        Date terminePeriodo = rs.getDate("terminePeriodo");
        
        svolto.add(new SvoltoInBean(progettoFormativoID, strutturaOspitanteID, code, inizioPeriodo, terminePeriodo));
      }
      
    } finally {
      try {
        if (ps != null)
          ps.close();
      } finally {
        DriverManagerConnectionPool.releaseConnection(connection);
      }
    }
    
    return svolto;
  }
  
  public Collection<AbstractBean> doRetrieveByInizioPeriodo(Date inizioPeriodo) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    Collection<AbstractBean> svolto = null;
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(SvoltoInSQL.DO_RETRIEVE_BY_INIZIO_PERIODO);
      
      ps.setDate(1, inizioPeriodo);
      
      rs = ps.executeQuery();
      
      svolto = new ArrayList<AbstractBean>();
      
      while (rs.next()) {
        int progettoFormativoID = rs.getInt("progettoFormativoID");
        int strutturaOspitanteID = rs.getInt("strutturaOspitanteID");
        int tutorAzID = rs.getInt("tutorAzID");
        Date terminePeriodo = rs.getDate("terminePeriodo");
        
        svolto.add(new SvoltoInBean(progettoFormativoID, strutturaOspitanteID, tutorAzID, inizioPeriodo, terminePeriodo));
      }
      
    } finally {
      try {
        if (ps != null)
          ps.close();
      } finally {
        DriverManagerConnectionPool.releaseConnection(connection);
      }
    }
    
    return svolto;
  }
  
  public Collection<AbstractBean> doRetrieveByTerminePeriodo(Date terminePeriodo) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    Collection<AbstractBean> svolto = null;
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(SvoltoInSQL.DO_RETRIEVE_BY_TERMINE_PERIODO);
      
      ps.setDate(1, terminePeriodo);
      
      rs = ps.executeQuery();
      
      svolto = new ArrayList<AbstractBean>();
      
      while (rs.next()) {
        int progettoFormativoID = rs.getInt("progettoFormativoID");
        int strutturaOspitanteID = rs.getInt("strutturaOspitanteID");
        int tutorAzID = rs.getInt("tutorAzID");
        Date inizioPeriodo = rs.getDate("inizioPeriodo");
        
        svolto.add(new SvoltoInBean(progettoFormativoID, strutturaOspitanteID, tutorAzID, inizioPeriodo, terminePeriodo));
      }
      
    } finally {
      try {
        if (ps != null)
          ps.close();
      } finally {
        DriverManagerConnectionPool.releaseConnection(connection);
      }
    }
    
    return svolto;
  }

  public Collection<AbstractBean> doRetreiveInPeriodo(Date startDate, Date endDate) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    Collection<AbstractBean> svolto = null;
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(SvoltoInSQL.DO_RETRIEVE_IN_PERIODO);
      
      ps.setDate(1, startDate);
      ps.setDate(2, endDate);
      
      rs = ps.executeQuery();
      
      svolto = new ArrayList<AbstractBean>();
      
      while (rs.next()) {
        int progettoFormativoID = rs.getInt("progettoFormativoID");
        int strutturaOspitanteID = rs.getInt("strutturaOspitanteID");
        int tutorAzID = rs.getInt("tutorAzID");
        Date inizioPeriodo = rs.getDate("inizioPeriodo");
        Date terminePeriodo = rs.getDate("terminePeriodo");
        
        svolto.add(new SvoltoInBean(progettoFormativoID, strutturaOspitanteID, tutorAzID, inizioPeriodo, terminePeriodo));
      }

    } finally {
      try {
        if (ps != null)
          ps.close();
      } finally {
        DriverManagerConnectionPool.releaseConnection(connection);
      }
    }
    
    return svolto;
  }
}
