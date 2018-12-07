package it.unisa.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import it.unisa.database.DriverManagerConnectionPool;

public class StudenteModelDM {

  public static final String TABLE_NAME = "studente";


  public static void loadInfo(StudenteBean stud) throws SQLException {

    Connection connection = null;
    PreparedStatement ps = null;

    String selectSQL = "SELECT * FROM " + TABLE_NAME + " WHERE matricola = ?";

    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(selectSQL);

      ps.setString(1, stud.getMatricola());

      ResultSet rs = ps.executeQuery();

      while (rs.next()) {
        stud.setNome(rs.getString("nome"));
        stud.setCfu(rs.getString("cfu"));
        stud.setPassword(rs.getString("pass"));
        stud.setTutorAccID(rs.getInt("tutorAccID"));
        stud.setTutorAzID(rs.getInt("tutorAzID"));
        stud.setTirocinio(rs.getInt("tirocinio"));
        stud.setRegistro(rs.getInt("registro"));
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


  public static StudenteBean loadStudente(String matricola) throws SQLException {

    Connection connection = null;
    PreparedStatement ps = null;

    StudenteBean stud = new StudenteBean();

    stud.setMatricola(matricola);

    String selectSQL = "SELECT * FROM " + TABLE_NAME + " WHERE matricola = ?";

    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(selectSQL);

      ps.setString(1, matricola);

      ResultSet rs = ps.executeQuery();

      while (rs.next()) {
        stud.setNome(rs.getString("nome"));
        stud.setCfu(rs.getString("cfu"));
        stud.setPassword(rs.getString("pass"));
        stud.setTutorAccID(rs.getInt("tutorAccID"));
        stud.setTutorAzID(rs.getInt("tutorAzID"));
        stud.setTirocinio(rs.getInt("tirocinio"));
        stud.setRegistro(rs.getInt("registro"));
      }



    } finally {

      try {
        if (ps != null)
          ps.close();
      } finally {
        DriverManagerConnectionPool.releaseConnection(connection);
      }
    }
    return stud;

  }

  public static void saveStudente(StudenteBean stud) throws SQLException {

    String matricola, nome, cfu, pass;
    int tutorAccID, tutorAzID, tirocinio, registro;

    matricola = stud.getMatricola();
    nome = stud.getNome();
    cfu = stud.getCfu();
    pass = stud.getPassword();
    tutorAccID = stud.getTutorAccID();
    tutorAzID = stud.getTutorAzID();
    tirocinio = stud.getTirocinio();
    registro = stud.getRegistro();


    Connection connection = null;
    PreparedStatement ps = null;

    String selectSQL = "INSERT INTO " + TABLE_NAME + " VALUES (?,?,?,?,?,?,?,?)";

    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(selectSQL);

      ps.setString(1, matricola);
      ps.setString(2, nome);
      ps.setString(3, cfu);
      ps.setString(4, pass);
      ps.setInt(5, tutorAccID);
      ps.setInt(6, tutorAzID);
      ps.setInt(7, tirocinio);
      ps.setInt(8, registro);

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



}
