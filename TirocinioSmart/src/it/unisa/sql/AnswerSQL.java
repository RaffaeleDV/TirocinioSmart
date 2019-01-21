package it.unisa.sql;

public class AnswerSQL {
  public static String TABLE_NAME = "answer";
  public static String doSave = "INSERT INTO " + TABLE_NAME + " VALUES (?,?,?,?);";
  public static String retreiveByQuestion = "SELECT * FROM " + TABLE_NAME + " WHERE quest = ?;";
  public static String retreiveByUtente = "SELECT * FROM " + TABLE_NAME + " WHERE utente = ?;";
  public static String retreiveByChoose = "SELECT * FROM " + TABLE_NAME + " WHERE choose = ?;";
}
