package it.unisa.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import it.unisa.database.DriverManagerConnectionPool;
import it.unisa.sql.TSTutorSQL;
import java.util.ArrayList;
public class TutorModelDM {

  public static final String TABLE_NAME = "tutor";


  public static void loadInfo(TutorBean tutor) throws SQLException {

    Connection connection = null;
    PreparedStatement ps = null;


    String selectSQL = "SELECT * FROM " + TABLE_NAME + " WHERE id = ?";

    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(selectSQL);

      ps.setInt(1, tutor.getId());
      ResultSet rs = ps.executeQuery();

      while (rs.next()) {
        tutor.setEmail(rs.getString("email"));
        tutor.setNome(rs.getString("nome"));
        tutor.setPass(rs.getString("pass"));
        tutor.setTipo(rs.getString("tipo"));
        tutor.setConvenzioneID(rs.getInt("convenzioneID"));
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

  public static TutorBean loadTutor(int id) throws SQLException {

    Connection connection = null;
    PreparedStatement ps = null;

    TutorBean tutor = new TutorBean();

    tutor.setId(id);

    String selectSQL = "SELECT * FROM " + TABLE_NAME + " WHERE id = ?";

    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(selectSQL);

      ps.setInt(1, tutor.getId());
      ResultSet rs = ps.executeQuery();

      while (rs.next()) {
        tutor.setEmail(rs.getString("email"));
        tutor.setNome(rs.getString("nome"));
        tutor.setPass(rs.getString("pass"));
        tutor.setTipo(rs.getString("tipo"));
        tutor.setConvenzioneID(rs.getInt("convenzioneID"));
      }



    } finally {

      try {
        if (ps != null)
          ps.close();
      } finally {
        DriverManagerConnectionPool.releaseConnection(connection);
      }
    }
    return tutor;

  }

  public static void saveTutor(TutorBean tutor) throws SQLException {

    String nome, pass, tipo, email;
    int id, convenzioneID;

    nome = tutor.getNome();
    pass = tutor.getPass();
    tipo = tutor.getTipo();
    id = tutor.getId();
    convenzioneID = tutor.getConvenzioneID();
    email = tutor.getEmail();

    Connection connection = null;
    PreparedStatement ps = null;

    String selectSQL = "INSERT INTO " + TABLE_NAME + " VALUES (?,?,?,?,?,?)";

    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(selectSQL);

      ps.setInt(1, id);
      ps.setString(2, email);
      ps.setString(3, nome);
      ps.setString(4, pass);
      ps.setString(5, tipo);
      ps.setInt(6, convenzioneID);

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

  
  
  public static ArrayList<ProgettoFormativoBean> loadTirociniTutor(TutorBean tutor) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    ArrayList<ProgettoFormativoBean> tirocini = new ArrayList<ProgettoFormativoBean>();
    String tipo = tutor.getTipo();
    String selectSQL = null;
    
    if (tipo != null) {
      if (tipo.equals("aziendale")) {
        selectSQL = TSTutorSQL.queryTirociniTutorAziendale;
      } else {
        if (tipo.equals("accademico")) {
          selectSQL = TSTutorSQL.queryTirociniTutorAccademico;
        } else {
          //throw an exception
          return null;
        }
      }
    } else {
      //throw an exception
      return null;
    }
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(selectSQL);
      
      /*
       * ps set dei valori
       */
      
      ResultSet rs = ps.executeQuery();      
      while (rs.next()) {
        System.out.println("TutorTrovato");
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
  
  public static UfficioBean loadUfficio(TutorBean tutor) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    UfficioBean ufficio = null;
    String selectSQL = null;
    String tipo = tutor.getTipo();
    
    if (tipo != null) {
      if (tipo.equals("aziendale")) {
        selectSQL = TSTutorSQL.queryUfficioTutorAziendale;
      } else {
        if (tipo.equals("accademico")) {
          selectSQL = TSTutorSQL.queryUfficioTutorAccademico;
        } else {
          //throw an exception
          return null;
        }
      }
    } else {
      //throw an exception
      return null;
    }
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(selectSQL);
      
      /*
       * ps set dei valori
       */
      
      ResultSet rs = ps.executeQuery();
      if (rs.next()) {
        System.out.println("TutorTrovato");
        ufficio = new UfficioBean();
        ufficio.setId(rs.getInt("id"));
        ufficio.setNome(rs.getString("nome"));
        ufficio.setPass(rs.getString("pass"));
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

  public static RegistroBean loadRegistro(TutorBean tutor) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    RegistroBean registro = null;
    String selectSQL = null;
    String tipo = tutor.getTipo();
    
    if (tipo != null) {
      if (tipo.equals("aziendale")) {
        selectSQL = TSTutorSQL.queryRegistroTutorAziendale;
      } else {
        if (tipo.equals("accademico")) {
          selectSQL = TSTutorSQL.queryRegistroTutorAccademico;
        } else {
          //throw an exception
          return null;
        }
      }
    } else {
      //throw an exception
      return null;
    }
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(selectSQL);
      
      /*
       * ps set dei valori
       */
      
      ResultSet rs = ps.executeQuery();
      if (rs.next()) {
        System.out.println("RegistroTrovato");
        registro = new RegistroBean();
        registro.setDescrizione(rs.getString("descrizione"));
        registro.setId(rs.getInt("id"));
        registro.setNome(rs.getString("nome"));
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
  
  public static ProgettoFormativoBean loadProgettoFormativo(int idTirocinio) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    ProgettoFormativoBean progetto = null;
    String selectSQL = TSTutorSQL.queryProgettoFormativoTirocinio;
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(selectSQL);
      
      /*
       * ps set dei valori
       */
      
      ResultSet rs = ps.executeQuery();
      if (rs.next()) {
        System.out.println("ProgettoFormativoTrovato");
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
}
