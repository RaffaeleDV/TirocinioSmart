package it.unisa.sql;

public class ConvenzioneSQL {
  
  public static final String TABLE_NAME = "convenzione";
  
  public static final String DO_RETRIEVE_BY_KEY = 
    "SELECT * " + 
    "FROM " + TABLE_NAME + " " + 
    "WHERE id = ?;";
  
  public static final String DO_RETRIEVE_ALL = 
    "SELECT * " + 
    "FROM " + TABLE_NAME + ";";
  
  public static final String DO_SAVE = 
    "INSERT INTO " + TABLE_NAME + " " + 
    "VALUES (?, ?, ?, ?, ?);";
  
  public static final String DO_DELETE = 
    "DELETE FROM " + TABLE_NAME + " " + 
    "WHERE id = ?;";
  
  public static final String DO_UPDATE =
    "UPDATE " + TABLE_NAME + " " +
    "SET id = ?, info = ?, descrizione = ?, tutorAzID = ?, tutorAccID = ? " +
    "WHERE id = ?;";
  
  public static final String DO_RETRIEVE_BY_INFO = 
    "SELECT * " + 
    "FROM " + TABLE_NAME + " " + 
    "WHERE info LIKE ?;";
  
  public static final String DO_RETRIEVE_BY_CONVENIENZA =
    "SELECT * " +
    "FROM " + TABLE_NAME + " " +
    "WHERE descrizione LIKE ?;";
  
  public static final String DO_RETRIEVE_BY_TUTOR_ACC =
    "SELECT * " +
    "FROM " + TABLE_NAME + " " +
    "WHERE tutorAccID = ?;";
  
  public static final String DO_RETRIEVE_BY_TUTOR_AZ = 
    "SELECT * " + 
    "FROM " + TABLE_NAME + " " + 
    "WHERE tutorAzID = ?;";
}
