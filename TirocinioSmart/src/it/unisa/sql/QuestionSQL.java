package it.unisa.sql;

public class QuestionSQL {
  
  public static final String TABLE_NAME = "question";
  
  public static final String DO_RETRIEVE_BY_KEY = 
    "SELECT * " + 
    "FROM " + TABLE_NAME + " " +
    "WHERE id = ?;";
  
  public static final String DO_SAVE = 
    "INSERT INTO " + TABLE_NAME + " " + 
    "VALUES (?, ?, ?, ?, ?);";
  
  public static final String DO_DELETE = 
    "DELETE FROM " + TABLE_NAME + " " + 
    "WHERE id = ?;";
  
  public static final String DO_RETRIEVE_ALL = 
    "SELECT * " + 
    "FROM " + TABLE_NAME + ";";
  
  public static final String DO_RETRIEVE_BY_DESCRIPTION = 
    "SELECT * " + 
    "FROM " + TABLE_NAME + " " + 
    "WHERE description LIKE ?;";
  
  public static final String DO_UPDATE = 
    "UPDATE " + TABLE_NAME + " " + 
    "SET maxChooses = ?, maxAnswers = ?, question = ?, description = ? " + 
    "WHERE id = ?;";
  
  public static final String DO_RETRIEVE_BY_QUESTIONARIO = 
    "SELECT " + QuestionSQL.TABLE_NAME + ".* " +
    "FROM " + QuestionSQL.TABLE_NAME + ", " + IncludeSQL.TABLE_NAME + " " + 
    "WHERE " + IncludeSQL.TABLE_NAME + ".questionID = " + TABLE_NAME + ".id AND " + IncludeSQL.TABLE_NAME + ".questionarioID = ?;";
}
