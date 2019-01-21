package it.unisa.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import it.unisa.database.DriverManagerConnectionPool;
import it.unisa.sql.ChooseSQL;
import java.util.ArrayList;

public class ChooseModelDM implements BeansModel {

  @Override
  public AbstractBean doRetrieveByKey(int code) throws SQLException {
    String description = null;
    String tipo = null;
    ChooseBean choose = new ChooseBean();
    Connection connection = null;
    PreparedStatement ps = null;
    
    try {
      
      connection = (Connection) DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(ChooseSQL.doRetrieveByKey);
      ps.setInt(1, code);
      ResultSet rs = ps.executeQuery();
      
      if (rs.next()) {
        choose.setId(code);
        choose.setDescription(rs.getString("description"));
        choose.setTipo(rs.getString("tipo"));
      }
      
    } finally {
      try {
        if (ps != null)
          ps.close();
      } finally {
        DriverManagerConnectionPool.releaseConnection(connection);
      }
    }
    
    return choose;
  }

  @Override
  public Collection<AbstractBean> doRetrieveAll(String order) throws SQLException {
    Collection<AbstractBean> chooses = new ArrayList<AbstractBean>();
    Connection connection = null;
    PreparedStatement ps = null;
    
    try {
      
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(ChooseSQL.doRetrieveAll);
      
      /*
       * codice per settare il ps in base al valore di order
       */
      
      ResultSet rs = ps.executeQuery();
      
      while(rs.next()) {
        int id = rs.getInt("id");
        String description = rs.getString("description");
        String tipo = rs.getString("tipo");
        chooses.add(new ChooseBean(id, description, tipo));
      }
      
    } finally {
      try {
        if (ps != null)
          ps.close();
      } finally {
        DriverManagerConnectionPool.releaseConnection(connection);
      }
    }
    
    return chooses;
  }

  @Override
  public void doSave(AbstractBean product) throws SQLException {
    ChooseBean choose = (ChooseBean) product;
    Connection connection = null;
    PreparedStatement ps = null;
    
    try {
      
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(ChooseSQL.doSave);
    
      ps.setInt(1, choose.getId());
      ps.setString(2, choose.getDescription());
      ps.setString(3, choose.getTipo());
    
      ps.executeUpdate();
    
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
    int rowCount = -1;
    
    try {
      
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(ChooseSQL.doDelete);
    
      ps.setInt(1, code);
    
      rowCount = ps.executeUpdate();

    } finally {
      try {
        if (ps != null)
          ps.close();
      } finally {
        DriverManagerConnectionPool.releaseConnection(connection);
      }
    }
    
    if (rowCount >= 1)
      return true;
    return false;
  }
  
  public Collection<ChooseBean> retreiveQuestionChooses(QuestionBean question) throws SQLException {
    Collection<ChooseBean> chooses = new ArrayList<ChooseBean>();
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    
    try {
      connection = (Connection) DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(ChooseSQL.retreiveQuestionChooses);
      ps.setInt(1, question.getId());
      rs = ps.executeQuery();
      while (rs.next()) {
        int id = rs.getInt("id");
        String description = rs.getString("description");
        String tipo = rs.getString("tipo");
        ChooseBean choose = new ChooseBean(id, description, tipo);
        chooses.add(choose);
      }
      
    } finally {
      try {
        if (ps != null)
          ps.close();
      } finally {
        DriverManagerConnectionPool.releaseConnection(connection);
      }
    }
    
    return chooses;
  }
}
