package it.unisa.sql;

public class TSTutorSQL {
  public static final String queryTutorById = "SELECT * FROM " + TABLE_NAME + " WHERE id = ?;";
  public static final String insertTutor = "INSERT INTO " + TABLE_NAME + " VALUES (?,?,?,?,?);";
  public static final String queryTirociniTutorAziendale = "";
  public static final String queryTirociniTutorAccademico = "";
  public static final String queryUfficioTutorAziendale = "";
  public static final String queryUfficioTutorAccademico = "";
  public static final String queryRegistroTutorAccademico = "";
  public static final String queryRegistroTutorAziendale = "";
  public static final String queryProgettoFormativoTirocinio = "";
}
