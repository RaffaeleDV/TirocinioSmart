package it.unisa.sql;

public class QuestionarioSQL {
  
  public static final String TABLE_NAME = "questionario";
  
  public static final String DO_RETRIEVE_BY_KEY = 
    "SELECT * " + 
    "FROM " + TABLE_NAME + " " + 
    "WHERE id = ?;";
  
  public static final String DO_SAVE = 
    "INSERT INTO " + TABLE_NAME + " " + 
    "VALUES (?, ?, ?, ?, ?, ?);";
  
  public static final String DO_DELETE = 
    "DELETE FROM " + TABLE_NAME + " " + 
    "WHERE id = ?;";
  
  public static final String DO_UPDATE = 
    "UPDATE " + TABLE_NAME + " " + 
    "SET id = ?, questions = ?, nstudenti = ?, nome = ?, description = ?, tematica = ? " + 
    "WHERE id = ?;";
  
  public static final String DO_RETRIEVE_ALL = 
    "SELECT * " + 
    "FROM " + TABLE_NAME + ";";
  
  public static final String DO_RETRIEVE_BY_NOME = 
    "SELECT * " + 
    "FROM " + TABLE_NAME + " " + 
    "WHERE nome LIKE ?;";
  
  public static final String DO_RETRIEVE_BY_DESCRIPTION =
    "SELECT * " +
    "FROM " + TABLE_NAME + " " +
    "WHERE description LIKE ?;";
  
  public static final String DO_RETRIEVE_BY_TEMATICA =
    "SELECT * " +
    "FROM " + TABLE_NAME + " " +
    "WHERE tematica LIKE ?;";
  
  public static final String DO_RETRIEVE_BY_QUESTIONS =
    "SELECT * " +
    "FROM " + TABLE_NAME + " " +
    "wHERE questions BETWEEN ? AND ?;";
  
  public static final String DO_RETRIEVE_BY_NSTUDENTI =
    "SELECT * " +
    "FROM " + TABLE_NAME + " " +
    "WHERE nstudenti BETWEEN ? AND ?;";
}