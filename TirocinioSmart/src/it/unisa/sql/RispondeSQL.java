package it.unisa.sql;

public class RispondeSQL {
  
  public static final String TABLE_NAME = "risponde";
  
  public static final String DO_RETRIEVE_ALL =
    "SELECT * " + 
    "FROM " + TABLE_NAME + ";";
  
  public static final String DO_SAVE = 
    "INSERT INTO " + TABLE_NAME + " " + 
    "VALUES (?, ?);";
  
  public static final String DO_RETRIEVE_BY_QUESTION = 
    "SELECT * " + 
    "FROM " + TABLE_NAME + " " + 
    "WHERE quest = ?;";
  
  public static  final String DO_RETRIEVE_BY_CHOOSE = 
    "SELECT * " + 
    "FROM " + TABLE_NAME + " " + 
    "WHERE choose = ?;";
}
