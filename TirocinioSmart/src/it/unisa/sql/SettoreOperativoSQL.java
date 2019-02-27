package it.unisa.sql;

public class SettoreOperativoSQL {
  
  public static final String TABLE_NAME = "settore_operativo";
  
  public static final String DO_RETRIEVE_BY_KEY = 
    "SELECT * " + 
    "FROM " + TABLE_NAME + " " + 
    "WHERE id = ?;";
  
  public static final String DO_RETRIEVE_ALL = 
    "SELECT * " + 
    "FROM " + TABLE_NAME + ";";
  
  public static final String DO_SAVE = 
    "INSERT INTO " + TABLE_NAME + " " + 
    "VALUES (?, ?);";
  
  public static final String DO_DELETE = 
    "DELETE FROM " + TABLE_NAME + " " + 
    "WHERE id = ?;";
  
  public static final String DO_UPDATE =
    "UPDATE " + TABLE_NAME + " " +
    "SET descrizione = ? " +
    "WHERE id = ?;";
  
  public static final String DO_RETRIEVE_BY_DESCRIZIONE = 
    "SELECT * " + 
    "FROM " + TABLE_NAME + " " + 
    "WHERE descrizione LIKE ?;";
}
