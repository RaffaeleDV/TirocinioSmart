package it.unisa.sql;

public class IncludeSQL {
  public static String TABLE_NAME = "include";
  public static String doSave = "INSERT INTO " + TABLE_NAME + " VALUES(?,?);";
  public static String retreiveByQuestionario = "SELECT * FROM " + TABLE_NAME + " WHERE questionario = ?;";
  public static String retreiveByQuestion = "SELECT * FROM " + TABLE_NAME + " WHERE question = ?;";
  public static String deleteByQuestionario = "DELETE FROM " + TABLE_NAME + " WHERE questionario = ?;";
  public static String deleteByQuestion = "DELETE FROM " + TABLE_NAME + " WHERE question = ?;";
}
