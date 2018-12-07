package it.unisa.model;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import it.unisa.database.DriverManagerConnectionPool;
import it.unisa.sql.TSProgettoSQL;;
public class ProgettoFormativoModelDM {

  public static final String TABLE_NAME = "prog_form";
  
  public static void loadInfo(ProgettoFormativoBean prog) throws SQLException{

    Connection connection = null;
    PreparedStatement ps = null;
    
    String selectSQL = "SELECT * FROM " + TABLE_NAME + " WHERE id = ?";
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(selectSQL);
      
      ps.setInt(1, prog.getId());
      
      ResultSet rs = ps.executeQuery();
      
      while(rs.next()) {
        prog.setApprovazione(rs.getBoolean("approvazione"));
        prog.setInfo(rs.getString("info"));
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
  
  
  public static ProgettoFormativoBean loadProgettoFormativo(int id) throws SQLException{
    
    Connection connection = null;
    PreparedStatement ps = null;
    
    ProgettoFormativoBean prog = new ProgettoFormativoBean();
    
    prog.setId(id);
    
    String selectSQL = "SELECT * FROM " + TABLE_NAME + " WHERE id = ?";
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(selectSQL);
      
      ps.setInt(1, id);
      
      ResultSet rs = ps.executeQuery();
      
      while(rs.next()) {
        prog.setApprovazione(rs.getBoolean("approvazione"));
        prog.setInfo(rs.getString("info"));
      }
      
    } finally {
      try {
        if (ps != null)
          ps.close();
        
      } finally {
        DriverManagerConnectionPool.releaseConnection(connection);
        
      }
      return prog;
      
    }
    
  }
  
  public static void saveProgettoFormativo(ProgettoFormativoBean prog) throws SQLException{
    
    int id;
    boolean approvazione;
    String info;
    
    id = prog.getId();
    approvazione = prog.getApprovazione();
    info = prog.getInfo();
    
    Connection connection = null;
    PreparedStatement ps = null;

    String selectSQL = "INSERT INTO " + TABLE_NAME + " VALUES (?,?,?)";

    try {

      connection = DriverManagerConnectionPool.getConnection();

      ps = connection.prepareStatement(selectSQL);
      
      ps.setInt(1, id);
      ps.setString(2, info);
      ps.setBoolean(3, approvazione);
      
      System.out.println(ps.toString());
      
      ps.executeUpdate();
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
  
  public static Boolean tirocinioStudente(String matricola, int idTirocinio) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;

    String selectSQL = TSProgettoSQL.isTirocinioStudenteQuery;

	try {
	  connection = DriverManagerConnectionPool.getConnection();
	  
	  ps = connection.prepareStatement(selectSQL);
	  
	  /*
	   * ps set dei valori
	   */
	  
	  ResultSet rs = ps.executeQuery();
	  if(rs.next()) {
	    return new Boolean(true);
	  }
	} finally {
	  try {
	    if (ps != null)
	      ps.close();
	  } finally {
	    DriverManagerConnectionPool.releaseConnection(connection);
	  }
	}
	
	return new Boolean(false);
  }
  
  public static ProgettoFormativoBean loadTirocinio(int idTirocinio) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    ProgettoFormativoBean progetto = null;
    String selectSQL = TSProgettoSQL.tirocinioStudenteQuery;

    try {
      connection = DriverManagerConnectionPool.getConnection();
      
      ps = connection.prepareStatement(selectSQL);
      
      /*
       * ps set dei valori
       */
      
      ResultSet rs = ps.executeQuery();
      if(rs.next()) {
        progetto = new ProgettoFormativoBean();
        progetto.setApprovazione(rs.getBoolean("approvazione"));
        progetto.setId(rs.getInt("id"));
        progetto.setInfo(rs.getString("info"));
      }
    } finally {
      try {
        if (ps != null)
          ps.close();
      } finally {
        DriverManagerConnectionPool.releaseConnection(connection);
      }
    }
    
    return progetto;
  }
  
  public static ArrayList<ProgettoFormativoBean> loadTirociniStudente(StudenteBean studente) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    ArrayList<ProgettoFormativoBean> tirociniStudente = new ArrayList<ProgettoFormativoBean>();
    String selectSQL = TSProgettoSQL.tirociniStudenteQuery;
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(selectSQL);
      
      /*
       * ps set dei valori
       */
      
      ResultSet rs = ps.executeQuery();
      while(rs.next()) {
        ProgettoFormativoBean progetto = new ProgettoFormativoBean();
        progetto.setApprovazione(rs.getBoolean("approvazione"));
        progetto.setId(rs.getInt("id"));
        progetto.setInfo(rs.getString("info"));
        tirociniStudente.add(progetto);
      }
    } finally {
      try {
        if (ps != null)
          ps.close();
      } finally {
        DriverManagerConnectionPool.releaseConnection(connection);
      }
    }
    return tirociniStudente;
  }
  
  public static UfficioBean loadUfficio(ProgettoFormativoBean progetto) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    UfficioBean ufficio = null;
    String selectSQL = TSProgettoSQL.ufficioTirocinioQuery;
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(selectSQL);
      
      /*
       * ps set dei valori
       */
      
      ResultSet rs = ps.executeQuery();
      if (rs.next()) {
        ufficio = new UfficioBean();
        ufficio.setId(rs.getInt("id"));
        ufficio.setNome(rs.getString("nome"));
        ufficio.setPassword(rs.getString("password"));
      }
      
    } finally {
      try {
        if (ps != null)
          ps.close();
      } finally {
        DriverManagerConnectionPool.releaseConnection(connection);
      }
    }
    return ufficio;
  }
  
  public static ArrayList<ProgettoFormativoBean> loadTirociniUfficio(UfficioBean ufficio) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    ArrayList<ProgettoFormativoBean> tirocini = new ArrayList<ProgettoFormativoBean>();
    String selectSQL = TSProgettoSQL.tirociniUfficioQuery;
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(selectSQL);
      
      /*
       * ps set dei valori
       */
      
      ResultSet rs = ps.executeQuery();
      while (rs.next()) {
        ProgettoFormativoBean progetto = new ProgettoFormativoBean();
        progetto.setApprovazione(rs.getBoolean("approvazione"));
        progetto.setId(rs.getInt("id"));
        progetto.setInfo(rs.getString("info"));
        tirocini.add(progetto);
      }
    } finally {
      try {
        if (ps != null) 
          ps.close();
      } finally {
        DriverManagerConnectionPool.releaseConnection(connection);
      }
    }
    return tirocini;
  }
  
  public static RegistroBean loadRegistro(ProgettoFormativoBean progetto) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    RegistroBean registro = null;
    String selectSQL = TSProgettoSQL.registroTirocinioQuery;
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(selectSQL);
      
      /*
       * ps set dei valori
       */
      
      ResultSet rs = ps.executeQuery();
      if (rs.next()) {
        registro = new RegistroBean();
        registro.setId(rs.getInt("id"));
        registro.setNome(rs.getString("nome"));
        registro.setDescrizione(rs.getString("descrizione"));
        registro.setConsegna(rs.getBoolean("consegna"));
        registro.setConfermaTutorAcc(rs.getBoolean("confermaTutorAcc"));
        registro.setConfermaTutorAz(rs.getBoolean("confermaTutorAz"));
      }
    } finally {
      try {
        if (ps != null)
          ps.close();
      } finally {
        DriverManagerConnectionPool.releaseConnection(connection);
      }
    }
    return registro;
  }
  
  public static StudenteBean loadStudente(ProgettoFormativoBean progetto) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    StudenteBean studente = null;
    String selectSQL = TSProgettoSQL.studenteTirocinioQuery;
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps  = connection.prepareStatement(selectSQL);
      
      /*
       * ps set dei valori
       */
      
      ResultSet rs = ps.executeQuery();
      if (rs.next()) {
        studente = new StudenteBean();
        studente.setCfu(rs.getString("cfu"));
        studente.setMatricola(rs.getString("matricola"));
        studente.setNome(rs.getString("nome"));
        studente.setPassword(rs.getString("password"));
        studente.setRegistro(rs.getInt("registro"));
        studente.setTirocinio(rs.getInt("tirocinio"));
        studente.setTutorAccID(rs.getInt("tutorAccID"));
        studente.setTutorAzID(rs.getInt("tutorAccID"));
      }
    } finally {
      try {
        if (ps != null)
          ps.close();
      } finally {
        DriverManagerConnectionPool.releaseConnection(connection);
      }
    }
    return studente;
  }
}
