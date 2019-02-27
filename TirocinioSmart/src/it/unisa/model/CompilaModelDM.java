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
import it.unisa.sql.CompilaSQL;
import java.sql.Date;

public class CompilaModelDM implements BeansModel {

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
    Collection<AbstractBean> compilato = null;
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(CompilaSQL.DO_RETRIEVE_ALL);
      
      rs = ps.executeQuery();
      
      compilato = new ArrayList<AbstractBean>();
      
      while (rs.next()) {
        int studenteID = rs.getInt("studenteID");
        int questionarioID = rs.getInt("questionarioID");
        Date dataCompilazione = rs.getDate("dataCompilazione");
        
        compilato.add(new CompilaBean(studenteID, questionarioID, dataCompilazione));
      }
      
    } finally {
      try {
        if (ps != null)
          ps.close();
      } finally {
        DriverManagerConnectionPool.getConnection();
      }
    }
    
    return compilato;
  }

  @Override
  public boolean doDelete(int code) throws SQLException {
    return false;
  }
  
  @Override
  public void doSave(AbstractBean product) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    CompilaBean compila = (CompilaBean) product;
    
    try {
      
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(CompilaSQL.DO_SAVE);
      
      ps.setInt(1, compila.getStudenteID());
      ps.setInt(2, compila.getQuestionarioID());
      ps.setDate(3, compila.getDataCompilazione());
      
      if (!(ps.executeUpdate() > 0)) {
        Logger.getGlobal().log(Level.INFO, "L' oggetto CompilaBean non è stato memorizzato");
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
  
  public Collection<AbstractBean> doRetrieveByStudente(int code) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    Collection<AbstractBean> compilato = null;
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(CompilaSQL.DO_RETRIEVE_BY_STUDENTE);
      
      ps.setInt(1, code);
      
      rs = ps.executeQuery();
      
      compilato = new ArrayList<AbstractBean>();
      
      while (rs.next()) {
        int studente = rs.getInt("studenteID");
        int questionario = rs.getInt("questionarioID");
        Date dataCompilazione = rs.getDate("dataCompilazione");
        
        compilato.add(new CompilaBean(studente, questionario, dataCompilazione));
      }
      
    } finally {
      try {
        if (ps != null)
          ps.close();
      } finally {
        DriverManagerConnectionPool.releaseConnection(connection);
      }
    }
    
    return compilato;
  }
  
  public Collection<AbstractBean> doRetrieveByQuestionario(int code) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    Collection<AbstractBean> compilato = null;
    
    try {
      
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(CompilaSQL.DO_RETRIEVE_BY_QUESTIONARIO);
      
      ps.setInt(1, code);
      
      rs = ps.executeQuery();
      
      compilato = new ArrayList<AbstractBean>();
      
      while (rs.next()) {
        int studenteID = rs.getInt("studenteID");
        int questionarioID = rs.getInt("questionarioID");
        Date dataCompilazione = rs.getDate("dataCompilazione");
        
        compilato.add(new CompilaBean(studenteID, questionarioID, dataCompilazione));
      }
      
    } finally {
      try {
        if (ps != null)
          ps.close();
      } finally {
        DriverManagerConnectionPool.releaseConnection(connection);
      }
    }
    
    return compilato;
  }
  
  public Collection<AbstractBean> doRetrieveByDataCompilazioneBetween(Date startDate, Date endDate) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    Collection<AbstractBean> compilato = null;
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(CompilaSQL.DO_RETRIEVE_BY_DATA_COMPILAZIONE_BETWEEN);
      
      ps.setDate(1, startDate);
      ps.setDate(2, endDate);
      
      rs = ps.executeQuery();
      
      compilato = new ArrayList<AbstractBean>();
      
      while (rs.next()) {
        int studenteID = rs.getInt("studenteID");
        int questionarioID = rs.getInt("questionarioID");
        Date dataCompilazione = rs.getDate("dataCompilazione");
        
        compilato.add(new CompilaBean(studenteID, questionarioID, dataCompilazione));
      }
      
    } finally {
      try {
        if (ps != null)
          ps.close();
      } finally {
        DriverManagerConnectionPool.releaseConnection(connection);
      }
    }
    
    return compilato;
  }
}
