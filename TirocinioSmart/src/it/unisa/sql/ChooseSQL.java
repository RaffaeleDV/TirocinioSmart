package it.unisa.sql;

public class ChooseSQL {
  public static String TABLE_NAME = "choose";
  public static String doRetrieveByKey = "SELECT * FROM " + TABLE_NAME + " WHERE id = ?;";
  public static String doSave = "INSERT INTO " + TABLE_NAME + " VALUES (?,?,?);";
  public static String doDelete = "DELETE FROM " + TABLE_NAME + " WHERE id = ?;";
  public static String doRetrieveAll = "SELECT * FROM choose;";
  public static String retreiveQuestionChooses = "SELECT c.* FROM choose AS c, risponde AS r WHERE r.idquest = ? AND c.id = r.idchoose;";
}
