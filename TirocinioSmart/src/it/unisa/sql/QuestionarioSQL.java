package it.unisa.sql;

public class QuestionarioSQL {
  public static String TABLE_NAME = "questionario";
  public static String doRetrieveByKey = "SELECT * FROM " + TABLE_NAME + " WHERE id = ?;";
  public static String doSave = "INSERT INTO " + TABLE_NAME + " VALUES (?,?,?,?,?,?);";
  public static String doDelete = "DELETE FROM " + TABLE_NAME + " WHERE id = ?;";
  public static String doRetrieveAll = "SELECT * FROM " + TABLE_NAME + ";";
}
