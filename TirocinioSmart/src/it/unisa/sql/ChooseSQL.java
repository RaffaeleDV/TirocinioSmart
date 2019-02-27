package it.unisa.sql;

public class ChooseSQL {
  
  public static final String TABLE_NAME = "choose";
  
  public static final String DO_RETRIEVE_BY_KEY = 
    "SELECT * " + 
    "FROM " + TABLE_NAME + " " + 
    "WHERE id = ?;";
  
  public static final String DO_RETRIEVE_ALL = 
    "SELECT * " + 
    "FROM " + TABLE_NAME + ";";
  
  public static final String DO_SAVE = 
    "INSERT INTO " + TABLE_NAME +  " " + 
    "VALUES (?, ?, ?);";
  
  public static final String DO_DELETE = 
    "DELETE FROM " + TABLE_NAME + " " + 
    "WHERE id = ?;";
  
  public static final String DO_UPDATE = 
    "UPDATE " + TABLE_NAME + " " + 
    "SET description = ?, tipo = ? " + 
    "WHERE id = ?;";
  
  public static final String DO_RETRIEVE_BY_DESCRIPTION = 
    "SELECT * " + 
    "FROM " + TABLE_NAME + " " + 
    "WHERE description LIKE ?;";
  
  public static final String DO_RETRIEVE_BY_TIPO = 
    "SELECT * " + 
    "FROM " + TABLE_NAME + " " + 
    "WHERE tipo LIKE ?;"; 
  
  public static final String DO_RETRIEVE_BY_QUESTION = 
    "SELECT " + TABLE_NAME + ".* " + 
    "FROM " + TABLE_NAME + ", " + RispondeSQL.TABLE_NAME + " " + 
    "WHERE " + TABLE_NAME + ".id = " + RispondeSQL.TABLE_NAME + ".chooseID AND " + RispondeSQL.TABLE_NAME + " questionID = ?;";
}
