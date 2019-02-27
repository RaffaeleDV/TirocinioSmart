package it.unisa.sql;

public class IncludeSQL {
  
  public static final String TABLE_NAME = "include";
  
  public static final String DO_RETRIEVE_ALL = 
    "SELECT * " + 
    "FROM " + TABLE_NAME + ";";
  
  public static final String DO_SAVE = 
    "INSERT INTO " + TABLE_NAME + " " + 
    "VALUES(?, ?);";
  
  public static final String DO_RETRIEVE_BY_QUESTIONARIO = 
    "SELECT * " + 
    "FROM " + TABLE_NAME + " " + 
    "WHERE questionarioID = ?;";
  
  public static final String DO_RETRIEVE_BY_QUESTION = 
    "SELECT * " + 
    "FROM " + TABLE_NAME + " " + 
    "WHERE questionID = ?;";
  
  public static final String DO_DELETE_BY_QUESTIONARIO = 
    "DELETE FROM " + TABLE_NAME + " " + 
    "WHERE questionarioID = ?;";
  
  public static final String DO_DELETE_BY_QUESTION = 
    "DELETE FROM " + TABLE_NAME + " " + 
    "WHERE questionID = ?;";
}
