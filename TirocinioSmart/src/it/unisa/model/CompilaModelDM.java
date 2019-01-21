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
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public boolean doDelete(int code) throws SQLException {
    return false;
  }
  
  @Override
  public void doSave(AbstractBean product) throws SQLException {
    CompilaBean compila = (CompilaBean) product;
    Connection connection = null;
    PreparedStatement ps = null;
    
    try {
      
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(CompilaSQL.doSave);
      ps.setString(1, compila.getUtente());
      ps.setInt(2, compila.getQuestionario());
      ps.setDate(3, compila.getData_inizio());
      ps.setDate(4, compila.getData_fine());
      
      int rowCount = ps.executeUpdate();
      
      if (!(rowCount >= 1)) {
        Logger.getGlobal().log(Level.SEVERE, "CompilaBean has not been saved");
      }
      
    } finally {
      try {
        if (ps != null)
          ps.close();
      } finally {
        DriverManagerConnectionPool.releaseConnection(connection);
      }
    }
  }
  
  public Collection<CompilaBean> retreiveByUtente(CompilaBean compila) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    Collection<CompilaBean> comps = new ArrayList<CompilaBean>();
    
    try {
      
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(CompilaSQL.retreiveByUtente);
      ps.setString(1, compila.getUtente());
      rs = ps.executeQuery();
      
      while (rs.next()) {
        String utente = rs.getString("studente");
        int questionario = rs.getInt("questionario");
        Date data_inizio = rs.getDate("data_inizio");
        Date data_fine = rs.getDate("data_fine");
        CompilaBean c = new CompilaBean(utente, questionario, data_inizio, data_fine);
        System.out.println(c.toString());
        comps.add(c);
      }
      
    } finally {
      try {
        if (ps != null)
          ps.close();
      } finally {
        DriverManagerConnectionPool.releaseConnection(connection);
      }
    }
    
    return comps;
  }
  
  public Collection<CompilaBean> retreiveByQuestionario(CompilaBean compila) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    Collection<CompilaBean> comps = new ArrayList<CompilaBean>();
    
    try {
      
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(CompilaSQL.retreiveByQuestionario);
      ps.setInt(1, compila.getQuestionario());
      rs = ps.executeQuery();
      
      while (rs.next()) {
        String studente = rs.getString("studente");
        int questionario = rs.getInt("questionario");
        Date data_inizio = rs.getDate("data_inizio");
        Date data_fine = rs.getDate("data_fine");
        CompilaBean c = new CompilaBean(studente, questionario, data_inizio, data_fine);
        comps.add(c);
      }
      
    } finally {
      try {
        if (ps != null)
          ps.close();
      } finally {
        DriverManagerConnectionPool.releaseConnection(connection);
      }
    }
    
    return comps;
  }
}
