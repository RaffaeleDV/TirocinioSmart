package it.unisa.sql;

public class TSStudenteSQL {
  public static final String queryStudenteByMatricola = "SELECT * FROM " + TABLE_NAME + " WHERE matricola = ?;";
  public static final String insertStudente = "INSERT INTO " + TABLE_NAME + " VALUES (?,?,?,?,?,?,?,?);";
}
