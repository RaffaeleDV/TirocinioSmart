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

    String nome, pass, tipo;
    int id, convenzioneID;

    nome = tutor.getNome();
    pass = tutor.getPass();
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


}
