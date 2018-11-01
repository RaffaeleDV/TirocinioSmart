package it.unisa.sql;

public class TSConvenzioneQueries {
  public static final String queryConvenzioneByID = "SELECT * FROM " + TABLE_NAME + " WHERE id = ?;";
  public static final String createConvenzione = "INSERT INTO " + TABLE_NAME + " VALUES (?,?);";
}
