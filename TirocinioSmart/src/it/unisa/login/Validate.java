package it.unisa.login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import it.unisa.database.DriverManagerConnectionPool;
import it.unisa.model.StudenteBean;
import it.unisa.model.TutorBean;
import it.unisa.model.UfficioBean;


public class Validate {

  public static boolean checkUser(StudenteBean stud) {

    boolean st = false;

    Connection conn = null;
    try {
      conn = DriverManagerConnectionPool.getConnection();

      PreparedStatement ps =
          conn.prepareStatement("SELECT * FROM studente WHERE nome = ? AND pass = ? ");

      ps.setString(1, stud.getNome());
      ps.setString(2, stud.getPass());
      ResultSet rs = ps.executeQuery();
      st = rs.next();

    } catch (SQLException e) {
      e.printStackTrace();
    }
    return st;
  }

  public static boolean checkUser(TutorBean tut) {

    boolean st = false;

    Connection conn = null;
    try {
      conn = DriverManagerConnectionPool.getConnection();

      PreparedStatement ps =
          conn.prepareStatement("SELECT * FROM tutor WHERE email = ? AND pass = ? ");

      ps.setString(1, tut.getEmail());
      ps.setString(2, tut.getPass());
      ResultSet rs = ps.executeQuery();
      st = rs.next();
      
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return st;
  }

  public static boolean checkUser(UfficioBean tut) {

    boolean st = false;

    Connection conn = null;
    try {
      conn = DriverManagerConnectionPool.getConnection();

      PreparedStatement ps =
          conn.prepareStatement("SELECT * FROM ufficio WHERE nome = ? AND pass = ? ");

      ps.setString(1, tut.getNome());
      ps.setString(2, tut.getPass());
      ResultSet rs = ps.executeQuery();
      st = rs.next();
      
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return st;
  }

}
