package it.unisa.sql;

public class RispondeSQL {

  public static String TABLE_NAME = "risponde";
  public static String doSave = "INSERT INTO " + TABLE_NAME + " VALUES (?,?);";
  public static String retreiveByQuestion = "SELECT * FROM " + TABLE_NAME + " WHERE quest = ?;";
  public static String retreiveByChoose = "SELECT * FROM " + TABLE_NAME + " WHERE choose = ?;";
}
