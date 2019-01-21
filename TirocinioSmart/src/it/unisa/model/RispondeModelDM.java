package it.unisa.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;
import it.unisa.database.DriverManagerConnectionPool;
import it.unisa.sql.RispondeSQL;

public class RispondeModelDM implements BeansModel {

  @Override
  public AbstractBean doRetrieveByKey(int code) throws SQLException {
    return null;
  }

  @Override
  public Collection<AbstractBean> doRetrieveAll(String order) throws SQLException {
    return null;
  }

  @Override
  public void doSave(AbstractBean product) throws SQLException {
    RispondeBean risponde = (RispondeBean) product;
    Connection connection = null;
    PreparedStatement ps = null;
    
    try {
      
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(RispondeSQL.doSave);
      ps.setInt(1, risponde.getQuest());
      ps.setInt(2, risponde.getChoose());
      int rowCount = ps.executeUpdate();
      
      if (!(rowCount >= 1)) {
        Logger.getGlobal().log(Level.SEVERE, "RispondeBean not saved");
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

  @Override
  public boolean doDelete(int code) throws SQLException {
    return false;
  }

  public Collection<RispondeBean> retreiveByQuestion(RispondeBean risponde) throws SQLException {
    Collection<RispondeBean> risposte = new ArrayList<RispondeBean>();
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    
    try {
      
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(RispondeSQL.retreiveByQuestion);
      ps.setInt(1, risponde.getQuest());
      rs = ps.executeQuery();
      
      while (rs.next()) {
        int choose = rs.getInt("choose");
        RispondeBean r = new RispondeBean(risponde.getQuest(), choose);
        risposte.add(r);
      }
      
    } finally {
      try {
        if (ps != null)
          ps.close();
      } finally {
        DriverManagerConnectionPool.releaseConnection(connection);
      }
    }
    
    return risposte;
  }
  
  public Collection<RispondeBean> retreiveByChoose(RispondeBean risponde) throws SQLException {
    Collection<RispondeBean> risposte = new ArrayList<RispondeBean>();
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    
    try {
      
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(RispondeSQL.retreiveByQuestion);
      ps.setInt(1, risponde.getChoose());
      rs = ps.executeQuery();
      
      while (rs.next()) {
        int quest = rs.getInt("quest");
        RispondeBean r = new RispondeBean(quest, risponde.getChoose());
        risposte.add(r);
      }
      
    } finally {
      try {
        if (ps != null)
          ps.close();
      } finally {
        DriverManagerConnectionPool.releaseConnection(connection);
      }
    }
    
    return risposte;
  }
}
