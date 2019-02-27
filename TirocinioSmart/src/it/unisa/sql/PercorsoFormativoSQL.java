package it.unisa.sql;

public class PercorsoFormativoSQL {
  
  public static final String TABLE_NAME = "percorso_formativo";
  
  public static final String DO_RETRIEVE_BY_KEY = 
    "SELECT * " + 
    "FROM " + TABLE_NAME + " " + 
    "WHERE id = ?;";
  
  public static final String DO_RETRIEVE_ALL = 
    "SELECT * " + 
    "FROM " + TABLE_NAME + ";";
  
  public static final String DO_SAVE = 
    "INSERT INTO " + TABLE_NAME + " " + 
    "VALUES (?, ?, ?);";
  
  public static final String DO_DELETE = 
    "DELETE FROM " + TABLE_NAME + " " + 
    "WHERE id = ?;";
  
  public static final String DO_UPDATE =
    "UPDATE " + TABLE_NAME + " " +
    "SET progettoFormativoID = ?, percorso = ? " +
    "WHERE id = ?;";
  
  public static final String DO_RETRIEVE_BY_PROGETTO_FORMATIVO = 
    "SELECT * " + 
    "FROM " + TABLE_NAME + " " + 
    "WHERE progettoFormativoID = ?;";
  
  public static final String DO_RETRIEVE_BY_PERCORSO = 
    "SELECT * " + 
    "FROM " + TABLE_NAME + " " + 
    "WHERE percorso LIKE ?;";
}