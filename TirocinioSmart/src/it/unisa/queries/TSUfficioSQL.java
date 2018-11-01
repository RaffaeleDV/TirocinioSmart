package it.unisa.sql;

public class TSUfficioSQL {
  public static final String queryUfficioById = "SELECT * FROM " + TABLE_NAME + " WHERE id = ?;";
  public static final String insertUfficio = "INSERT INTO " + TABLE_NAME + " VALUES (?,?,?);";
}
