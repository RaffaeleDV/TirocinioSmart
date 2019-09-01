package it.unisa.sql;

public class RiguardaSQL {

  public static final String TABLE_NAME = "riguarda";
  
  public static final String DO_RETRIEVE_ALL = 
    "SELECT * " + 
    "FROM " + TABLE_NAME + ";";
  
  public static final String DO_RETRIEVE_BY_KEY =
    "SELECT * " +
    "FROM " + TABLE_NAME + " " +
    "WHERE progettoFormativoID = ? AND settoreOperativoID = ?;";
  
  public static final String DO_UPDATE =
    "UPDATE " + TABLE_NAME + " " +
    "SET progettoFormativoID = ? ,settoreOperativoID = ? ,priorita = ? " +
    "WHERE progettoFormativoID = ? AND settoreOperativoID = ?;";
  
  public static final String DO_SAVE = 
    "INSERT INTO " + TABLE_NAME + " " + 
    "VALUES (?, ?, ?);";
  
  public static final String DO_DELETE =
    "DELETE " +
    "FROM " + TABLE_NAME + " " +
    "WHERE progettoFormativoID = ? AND settoreOperativoID = ?;";

  public static final String DO_RETRIEVE_BY_PROGETTO_FORMATIVO = 
    "SELECT * " + 
    "FROM " + TABLE_NAME + " " + 
    "WHERE idProgettoFormativo = ?;";
  
  public static final String DO_RETRIEVE_BY_SETTORE_OPERATIVO = 
    "SELECT * " + 
    "FROM " + TABLE_NAME + " " + 
    "WHERE idSettoreOperativo = ?;";
  
  public static final String DO_RETRIEVE_BY_PRIORITA =
    "SELECT * " +
    "FROM " + TABLE_NAME + " " +
    "WHERE priorita BETWEEN ? AND ?;";
}
