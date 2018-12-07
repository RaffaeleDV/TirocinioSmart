package it.unisa.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import it.unisa.database.DriverManagerConnectionPool;
import it.unisa.sql.TSRegistroSQL;

public class RegistroModelDM {

  public static final String TABLE_NAME = "registro";
  
  public static void loadInfo(RegistroBean registroBean) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    String selectSQL = "SELECT * FROM " + TABLE_NAME + " WHERE id = ?;";
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(selectSQL);
      
      ps.setInt(1, registroBean.getId());
      
      ResultSet rs = ps.executeQuery();
      while (rs.next()) {
        registroBean.setNome(rs.getString("nome"));
        registroBean.setDescrizione(rs.getString("descrizione"));
        registroBean.setConsegna(rs.getBoolean("consegna"));
        registroBean.setConfermaTutorAcc(rs.getBoolean("confermaTutorAcc"));
        registroBean.setConfermaTutorAz(rs.getBoolean("confermaTutorAz"));
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
  
  public static RegistroBean loadRegistro(int id) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    RegistroBean registroBean = null;
    String selectSQL = "SELECT * FROM " + TABLE_NAME + " WHERE id = ?;";
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(selectSQL);
      
      ps.setInt(1, id);
      
      ResultSet rs = ps.executeQuery();
      while (rs.next()) {
        registroBean = new RegistroBean();
        registroBean.setId(id);
        registroBean.setDescrizione(rs.getString("descrizione"));
        registroBean.setNome(rs.getString("nome"));
        registroBean.setConsegna(rs.getBoolean("consegna"));
        registroBean.setConfermaTutorAcc(rs.getBoolean("confermaTutorAcc"));
        registroBean.setConfermaTutorAz(rs.getBoolean("confermaTutorAz"));
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
  
  public static void saveRegistro(RegistroBean reg) throws SQLException {
    boolean consegna = false, confermaTutorAcc = false, confermaTutorAz = false;
    int id = -1;
    String nome = null, descrizione = null;
    
    
    id = reg.getId();
    nome = reg.getNome();
    descrizione = reg.getDescrizione();
    consegna = reg.getConsegna();
    confermaTutorAcc = reg.getConfermaTutorAcc();
    confermaTutorAz = reg.getConfermaTutorAz();
    
    Connection connection = null;
    PreparedStatement ps = null;
    String selectSQL = "INSERT INTO " + TABLE_NAME + " VALUES (?,?,?,?,?,?);";
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(selectSQL);
      
      ps.setInt(1, id);
      ps.setString(2, "'" + nome + "'");
      ps.setString(3, "'" + descrizione + "'");
      ps.setBoolean(4, consegna);
      ps.setBoolean(5, confermaTutorAcc);
      ps.setBoolean(6, confermaTutorAz);
      
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
  
  public static void doUpdateRegistro(RegistroBean registroBean) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    String updateSQL = TSRegistroSQL.updateRegistro;
    
    if (registroBean == null)
      return;
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(updateSQL);
      
      ps.setString(1, "'" + registroBean.getNome() + "'");
      ps.setString(2, "'" + registroBean.getDescrizione() + "'");
      ps.setBoolean(3, registroBean.getConsegna());
      ps.setBoolean(4, registroBean.getConfermaTutorAcc());
      ps.setBoolean(5, registroBean.getConfermaTutorAz());
      
      ps.executeUpdate();
      connection.commit();
    } finally {
      try {
        if(ps != null)
          ps.close();
      } finally {
        DriverManagerConnectionPool.releaseConnection(connection);
      }
    }
  }
  
  public static StudenteBean loadStudente(RegistroBean registroBean) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    StudenteBean studenteBean = null;
    String selectSQL = TSRegistroSQL.queryRegistroStudente;
    
    if (registroBean == null)
      return null;
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(selectSQL);
      
      ps.setInt(1, registroBean.getId());
      
      ResultSet rs = ps.executeQuery();
      if (rs.next()) {
        studenteBean = new StudenteBean();
        studenteBean.setCfu(rs.getString("cfu"));
        studenteBean.setMatricola(rs.getString("matricola"));
        studenteBean.setNome(rs.getString("nome"));
        studenteBean.setPassword(rs.getString("pass"));
        studenteBean.setRegistro(rs.getInt("registro"));
        studenteBean.setTirocinio(rs.getInt("tirocinio"));
        studenteBean.setTutorAccID(rs.getInt("tutorAccID"));
        studenteBean.setTutorAzID(rs.getInt("tutorAzID"));
      }
    } finally {
      try {
        if (ps != null)
          ps.close();
      } finally {
        DriverManagerConnectionPool.releaseConnection(connection);
      }
    }
    
    return studenteBean;
  }
  
  
  public static UfficioBean loadUfficio(RegistroBean registroBean) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    UfficioBean ufficioBean = null;
    String selectSQL = TSRegistroSQL.queryRegistroUfficio;
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(selectSQL);
      
      /*
       * ps set dei valori
       */
      
      ResultSet rs = ps.executeQuery();
      if (rs.next()) {
        System.out.println("UfficioTrovato");
        ufficioBean = new UfficioBean();
        ufficioBean.setId(rs.getInt("id"));
        ufficioBean.setNome(rs.getString("nome"));
        ufficioBean.setPassword(rs.getString("pass"));
      }
    } finally {
      try {
        if (ps != null)
          ps.close();
      } finally {
        DriverManagerConnectionPool.releaseConnection(connection);
      }
    }
    
    return ufficioBean;
  }
  
  public static TutorBean loadTutorAziendale(RegistroBean registroBean) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    TutorBean tutorBean = null;
    String selectSQL = TSRegistroSQL.queryRegistroTutorAziendale;
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(selectSQL);
      
      /*
       * ps set dei valori
       */
      
      ResultSet rs = ps.executeQuery();
      if (rs.next()) {
        System.out.println("TutorAziendaleTrovato");
        tutorBean = new TutorBean();
        tutorBean.setConvenzioneID(rs.getInt("convenzioneID"));
        tutorBean.setId(rs.getInt("id"));
        tutorBean.setNome(rs.getString("nome"));
        tutorBean.setPassword(rs.getString("pass"));
        tutorBean.setTipo(rs.getString("tipo"));
      }
    } finally {
      try {
        if (ps != null)
          ps.close();
      } finally {
        DriverManagerConnectionPool.releaseConnection(connection);
      }
    }
    
    return tutorBean;
  }
  
  public static TutorBean loadTutorAccademico(RegistroBean registroBean) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    TutorBean tutorBean = null;

    String selectSQL = TSRegistroSQL.queryRegistroTutorAccademico;
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(selectSQL);
      
      /*
       * ps set dei valori
       */
      
      ResultSet rs = ps.executeQuery();
      if (rs.next()) {
        System.out.println("TutorAziendaleTrovato");
        tutorBean.setConvenzioneID(rs.getInt("convenzioneID"));
        tutorBean.setId(rs.getInt("id"));
        tutorBean.setNome(rs.getString("nome"));
        tutorBean.setPassword(rs.getString("pass"));
        tutorBean.setTipo(rs.getString("tipo"));
      }
    } finally {
      try {
        if (ps != null)
          ps.close();
      } finally {
        DriverManagerConnectionPool.releaseConnection(connection);
      }
    }
    
    return tutorBean;
  }
  
  public static ProgettoFormativoBean loadTirocinio(RegistroBean registroBean) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    ProgettoFormativoBean tirocinioBean = null;
    String selectSQL = TSRegistroSQL.queryRegistroTirocinio;
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(selectSQL);
      
      /*
       * ps set dei valori
       */
      
      ResultSet rs = ps.executeQuery();
      if (rs.next()) {
        System.out.println("TirocinioTrovato");
        tirocinioBean = new ProgettoFormativoBean();
        tirocinioBean.setApprovazione(rs.getBoolean("approvazione"));
        tirocinioBean.setId(rs.getInt("id"));
        tirocinioBean.setInfo(rs.getString("info"));
      }
    } finally {
      try {
        if (ps != null)
          ps.close();
      } finally {
        DriverManagerConnectionPool.releaseConnection(connection);
      }
    }
    
    return tirocinioBean;
  }
  
  public static boolean studenteRegistro(String matricola, int idRegistro) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    StudenteBean studenteBean = null;
    boolean studenteRegistro = false;
    String selectSQL = TSRegistroSQL.queryIsStudenteRegistro;
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(selectSQL);
      
      /*
       * ps set dei valori
       */
      
      ResultSet rs = ps.executeQuery();
      if (rs.next()) {
        studenteRegistro = true;
      } else {
        studenteRegistro = false;
      }
    } finally {
      try {
        if (ps != null)
          ps.close();
      } finally {
        DriverManagerConnectionPool.releaseConnection(connection);
      }
    }
    return studenteRegistro;
  }
  
  public static ArrayList<RegistroBean> loadRegistriTirocinio(int idTirocinio) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    String selectSQL = TSRegistroSQL.queryRegistriTirocinio;
    RegistroBean registroBean = null;
    ArrayList<RegistroBean> registriTirocinio = new ArrayList<RegistroBean>();
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(selectSQL);
      
      /*
       * ps set dei valori nella query
       */
      
      ResultSet rs = ps.executeQuery();
      while (rs.next()) {
        System.out.println("StudenteTrovato");
        registroBean = new RegistroBean();
        registroBean.setDescrizione(rs.getString("descrizione"));
        registroBean.setId(rs.getInt("id"));
        registroBean.setNome(rs.getString("nome"));
        registroBean.setConsegna(rs.getBoolean("consegna"));
        registroBean.setConfermaTutorAcc(rs.getBoolean("confermaTutorAcc"));
        registroBean.setConfermaTutorAz(rs.getBoolean("confermaTutorAz"));
        registriTirocinio.add(registroBean);
      }
    } finally {
      try {
        if (ps != null) 
          ps.close();
      } finally {
        DriverManagerConnectionPool.releaseConnection(connection);
      }
    }
    return registriTirocinio;
  }
  
  public static boolean tutorRegistro(int idTutor, int idRegistro) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    String selectSQL = TSRegistroSQL.queryIsTutorRegistro;
    boolean tutorRegistro = false;
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(selectSQL);
      
      /*
       * ps set dei valori nella query
       */
      
      ResultSet rs = ps.executeQuery();
      if (rs.next()) {
        System.out.println("TutorTrovato");
        tutorRegistro = true;
      } else {
        tutorRegistro = false;
      }
    } finally {
      try {
        if (ps != null) 
          ps.close();
      } finally {
        DriverManagerConnectionPool.releaseConnection(connection);
      }
    }
    return tutorRegistro;
  }
  
  public static ArrayList<RegistroBean> loadRegistriUfficio(int idUfficio) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    String selectSQL = TSRegistroSQL.queryRegistriUfficio;
    ArrayList<RegistroBean> registriBean = new ArrayList<RegistroBean>();
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(selectSQL);
      
      /*
       * ps set dei valori della query
       */
      
      ResultSet rs = ps.executeQuery();
      while (rs.next()) {
        int idRegistro = rs.getInt("id");
        String nome = rs.getString("nome");
        String descrizione  = rs.getString("descrizione");
        boolean consegna = rs.getBoolean("consegna");
        boolean confermaTutorAcc = rs.getBoolean("confermaTutorAcc");
        boolean confermaTutorAz = rs.getBoolean("confermaTutorAz");
        RegistroBean registroBean = new RegistroBean(idRegistro, nome , descrizione, 
            consegna, confermaTutorAcc, confermaTutorAz);
        registriBean.add(registroBean);
      }
    } finally {
      try {
        if (ps != null) 
          ps.close();
      } finally {
        DriverManagerConnectionPool.releaseConnection(connection);
      }
    }
    return registriBean;
  }
  
  public static ArrayList<RegistroBean> loadRegistriStudente(StudenteBean studenteBean) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    ArrayList<RegistroBean> registriBean = new ArrayList<RegistroBean>();
    String selectSQL = TSRegistroSQL.queryRegistriStudente;
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(selectSQL);
      
      ps.setInt(1, studenteBean.getRegistro());
      
      ResultSet rs = ps.executeQuery();
      while (rs.next()) {
        int idRegistro = rs.getInt("id");
        String nome = rs.getString("nome");
        String descrizione = rs.getString("descrizione");
        boolean consegna = rs.getBoolean("consegna");
        boolean confermaTutorAcc = rs.getBoolean("confermaTutorAcc");
        boolean confermaTutorAz = rs.getBoolean("confermaTutorAz");
        RegistroBean registroBean = new RegistroBean(idRegistro, nome, descrizione,
            consegna, confermaTutorAcc, confermaTutorAz);
        registriBean.add(registroBean);
      }
    } finally {
      try {
        if (ps != null)
          ps.close();
      } finally {
        DriverManagerConnectionPool.releaseConnection(connection);
      }
    }
    return registriBean;
  }
}