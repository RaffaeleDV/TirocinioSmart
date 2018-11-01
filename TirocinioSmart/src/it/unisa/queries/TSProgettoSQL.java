package it.unisa.sql;

public class TSProgettoCRUD {
  public static final String queryProgettoByID = "SELECT * FROM " + TABLE_NAME + " WHERE id = ?;";
  public static final String insertProgetto = "INSERT INTO " + TABLE_NAME + " VALUES (?,?,?);";
}
