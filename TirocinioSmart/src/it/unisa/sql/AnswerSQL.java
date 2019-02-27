package it.unisa.sql;

public class AnswerSQL {
  
  public static final String TABLE_NAME = "answer";
  
  public static final String DO_RETRIEVE_ALL = 
    "SELECT * " + 
    "FROM " + TABLE_NAME + ";";
  
  public static final String DO_SAVE = 
    "INSERT INTO " + TABLE_NAME + " " + 
    "VALUES (?, ?, ?, ?);";
  
  public static final String DO_UPDATE = 
    "UPDATE " + TABLE_NAME + " " + 
    "SET answerDate = ? " + 
    "WHERE questionID = ? AND studenteID = ? AND chooseID = ?;";
  
  public static final String DO_RETRIEVE_BY_QUESTION = 
    "SELECT * " + 
    "FROM " + TABLE_NAME + " " + 
    "WHERE questionID = ?;";
  
  public static final String DO_RETRIEVE_BY_STUDENTE = 
    "SELECT * " + 
    "FROM " + TABLE_NAME + " " + 
    "WHERE studenteID = ?;";
  
  public static final String DO_RETRIEVE_BY_CHOOSE = 
    "SELECT * " + 
    "FROM " + TABLE_NAME + " " + 
    "WHERE chooseID = ?;";
  
  public static final String DO_RETRIEVE_BY_ANSWER_DATE_BETWEEN = 
    "SELECT * " + 
    "FROM " + TABLE_NAME + " " + 
    "WHERE answerDate BETWEEN ? AND ?;";
}