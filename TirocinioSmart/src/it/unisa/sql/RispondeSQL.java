package it.unisa.sql;

public class RispondeSQL {
  
  public static final String TABLE_NAME = "risponde";
  
  public static final String DO_RETRIEVE_ALL =
    "SELECT * " + 
    "FROM " + TABLE_NAME + ";";
  
  public static final String DO_RETRIEVE_BY_KEY =
    "SELECT * " +
    "FROM " + TABLE_NAME + " " +
    "WHERE questionID = ? AND chooseID = ?;";
  
  public static final String DO_UPDATE =
    "UPDATE " + TABLE_NAME + " " +
    "SET questionID = ?, chooseID = ? " +
    "WHERE questionID = ? AND chooseID = ?;";
  
  public static final String DO_DELETE =
    "DELETE " +
    "FROM " + TABLE_NAME + " " +
    "WHERE questionID = ? AND chooseID = ?;";
  
  public static final String DO_SAVE = 
    "INSERT INTO " + TABLE_NAME + " " + 
    "VALUES (?, ?);";
  
  public static final String DO_RETRIEVE_BY_QUESTION = 
    "SELECT * " + 
    "FROM " + TABLE_NAME + " " + 
    "WHERE questionID = ?;";
  
  public static  final String DO_RETRIEVE_BY_CHOOSE = 
    "SELECT * " + 
    "FROM " + TABLE_NAME + " " + 
    "WHERE chooseID = ?;";
}
