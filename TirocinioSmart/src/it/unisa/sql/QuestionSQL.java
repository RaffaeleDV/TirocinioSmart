package it.unisa.sql;

public class QuestionSQL {
  public static String TABLE_NAME = "question";
  public static String doRetrieveByKey = "SELECT * FROM " + TABLE_NAME + " WHERE id = ?;";
  public static String doSave = "INSERT INTO " + TABLE_NAME + " VALUES (?,?,?,?,?);";
  public static String doDelete = "DELETE FROM " + TABLE_NAME + " WHERE id = ?;";
  public static String doRetrieveAll = "SELECT * FROM " + TABLE_NAME + ";";
  public static String retreiveQuestionsByQuestionario = "SELECT q.* FROM question AS q, include AS i WHERE q.id = i.question AND i.questionario = ?;";
}
