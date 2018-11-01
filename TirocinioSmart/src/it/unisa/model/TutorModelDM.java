package it.unisa.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import it.unisa.database.DriverManagerConnectionPool;

public class TutorModelDM {

  private static final String TABLE_NAME = "tutor";


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

        tutor.setNome(rs.getString("nome"));
        tutor.setPassword(rs.getString("pass"));
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

        tutor.setNome(rs.getString("nome"));
        tutor.setPassword(rs.getString("pass"));
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

    String nome, pass, tipo;
    int id, convenzioneID;

    nome = tutor.getNome();
    pass = tutor.getPassword();
    tipo = tutor.getTipo();
    id = tutor.getId();
    convenzioneID = tutor.getConvenzioneID();


    Connection connection = null;
    PreparedStatement ps = null;

    String selectSQL = "INSERT INTO " + TABLE_NAME + " VALUES (?,?,?,?,?)";

    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(selectSQL);

      ps.setInt(1, id);
      ps.setString(2, nome);
      ps.setString(3, pass);
      ps.setString(4, tipo);
      ps.setInt(5, convenzioneID);

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



  
  
  
  public static ArrayList<TirocinioBean> loadTirocini(TutorBean tutor) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    ArrayList<TirocinioBean> tirocini = new ArrayList<TirocinioBean>();
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
      if (selectSQL != null) {
        ps = connection.prepareStatement(selectSQL);
      } else {
        //throw an exception
      }
      
      /*
       * ps set dei valori
       */
      
      ResultSet rs = ps.executeQuery();      
      while (rs.next()) {
        System.out.println("TutorTrovato");
        TirocinioBean tirocinio = new TirocinioBean();
        tirocinio.setCfu(rs.getInt("cfu"));
        tirocinio.setId(rs.getInt("id"));
        tirocinio.setMatricolaStudente(rs.getString("matricola"));
        tirocinio.setNome(rs.getString("nome"));
        tirocinio.setProgettoFormativoId(rs.getInt("progetto_formativo"));
        tirocinio.setRegistroId(rs.getInt("id"));
        tirocinio.setUfficioId(rs.getInt("ufficio"));
        tirocini.add(tirocinio);
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
    UfficioBean ufficio = new UfficioBean();
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
      ps = connection.prepareStatement(connection);
      
      /*
       * ps set dei valori
       */
      
      ResultSet rs = ps.executeQuery();
      while(rs.next()) {
        System.out.println("TutorTrovato");
        ufficio.setId(rs.getInt("id"));
        ufficio.setNome(rs.getString("nome"));
        ufficio.setPassword(rs.getString("pass"));
      }
    } finally {
      try {
        if (ps != null)
          ps.close();
      } finally {
        DriverManagerConnectionPool.realiseConnection(connection);
      }
    }
    
    return ufficio;
  }

  public static RegistroBean loadRegistro(TutorBean tutor) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    RegistroBean registro = new RegistroBean();
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
      ps = connection.prepareStatement(connection);
      
      /*
       * ps set dei valori
       */
      
      ResultSet rs = ps.executeQuery();
      while (rs.next()) {
        System.out.println("RegistroTrovato");
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
      ps = connection.prepareStatement(connection);
      
      /*
       * ps set dei valori
       */
      
      ResultSet rs = ps.executeQuery();
      if (rs.next()) {
        System.out.println("ProgettoFormativoTrovato");
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
