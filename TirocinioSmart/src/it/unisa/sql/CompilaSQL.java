package it.unisa.sql;

public class CompilaSQL {
  public static String TABLE_NAME = "compila";
  public static String doSave = "INSERT INTO " + TABLE_NAME + " VALUES (?,?,?,?);";
  public static String retreiveByUtente = "SELECT * FROM " + TABLE_NAME + " WHERE studente = ?;";
  public static String retreiveByQuestionario = "SELECT * FROM " + TABLE_NAME + " WHERE questionario = ?;";
}
