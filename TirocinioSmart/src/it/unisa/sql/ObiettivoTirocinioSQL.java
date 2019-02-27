package it.unisa.sql;

public class ObiettivoTirocinioSQL {
  
  public static final String TABLE_NAME = "obiettivo_tirocinio";
  
  public static final String DO_RETRIEVE_BY_KEY = 
    "SELECT * " + 
    "FROM " + TABLE_NAME + " " + 
    "WHERE id = ?;";
  
  public static final String DO_RETRIEVE_ALL = 
    "SELECT * " + 
    "FROM " + TABLE_NAME + ";";
  
  public static final String DO_SAVE = 
    "INSERT INTO " + TABLE_NAME + " " + 
    "VALUES (?, ?, ?, ?);";
  
  public static final String DO_DELETE = 
    "DELETE FROM " + TABLE_NAME + " " + 
    "WHERE id = ?;";
  
  public static final String DO_UPDATE =
    "UPDATE " + TABLE_NAME + " " +
    "SET progettoFormativoID = ?, obiettivo = ?, priorita = ? " +
    "WHERE id = ?;";
  
  public static final String DO_RETRIEVE_BY_PROGETTO_FORMATIVO = 
    "SELECT * " + 
    "FROM " + TABLE_NAME + " " + 
    "WHERE progettoFormativoID = ?;";
  
  public static final String DO_RETRIEVE_BY_OBIETTIVO = 
    "SELECT * " + 
    "FROM " + TABLE_NAME + " " + 
    "WHERE obiettivo LIKE ?;";
  
  public static final String DO_RETRIEVE_BY_PRIORITA = 
    "SELECT * " + 
    "FROM " + TABLE_NAME + " " + 
    "WHERE priorita LIKE ?;";
}
