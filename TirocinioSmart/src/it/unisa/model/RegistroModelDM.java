package it.unisa.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import it.unisa.database.DriverManagerConnectionPool;

public class RegistroModelDM {

  private static final String TABLE_NAME = "registro";

  public static void loadInfo(RegistroBean registro) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;

    String selectSQL = "SELECT * FROM " + TABLE_NAME + " WHERE id = ?";

    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(selectSQL);

      ps.setInt(1, registro.getId());

      ResultSet rs = ps.executeQuery();

      while (rs.next()) {
        registro.setNome(rs.getString("nome"));
        registro.setDescrizione(rs.getString("descrizione"));

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

    RegistroBean registro = new RegistroBean();

    registro.setId(id);

    String selectSQL = "SELECT * FROM " + TABLE_NAME + " WHERE id = ?";

    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(selectSQL);

      ps.setInt(1, id);

      ResultSet rs = ps.executeQuery();

      while (rs.next()) {
        registro.setDescrizione(rs.getString("descrizione"));
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

  public static void saveRegistro(RegistroBean reg) throws SQLException {

    int id;
    String nome, descrizione;

    id = reg.getId();
    nome = reg.getNome();
    descrizione = reg.getDescrizione();

    Connection connection = null;
    PreparedStatement ps = null;
    String selectSQL = "INSERT INTO " + TABLE_NAME + " VALUES (?,?,?)";

    try {
      connection = DriverManagerConnectionPool.getConnection();
      ps = connection.prepareStatement(selectSQL);

      ps.setInt(1, id);
      ps.setString(2, nome);
      ps.setString(3, descrizione);

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
