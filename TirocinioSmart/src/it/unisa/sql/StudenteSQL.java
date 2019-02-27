package it.unisa.sql;

public class StudenteSQL {
  
  public static final String TABLE_NAME = "studente";
  
  public static final String DO_RETRIEVE_BY_KEY = 
    "SELECT * " + 
    "FROM " + TABLE_NAME + " " + 
    "WHERE id = ?;";
  
  public static final String DO_RETRIEVE_ALL = 
    "SELECT * " + 
    "FROM " + TABLE_NAME + ";";
  
  public static final String DO_SAVE = 
    "INSERT INTO " + TABLE_NAME + " " + 
    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
  
  public static final String DO_DELETE = 
    "DELETE FROM " + TABLE_NAME + " " + 
    "WHERE id = ?;";
  
  public static final String DO_UPDATE =
    "UPDATE " + TABLE_NAME + " " +
    "SET matricola = ?, nome = ?, cfu = ?, pass = ?, occupazione = ?, tutorAccID = ?, tutorAzID = ?, progettoFormativoID = ?, registroID = ? " +
    "WHERE id = ?;";
  
  public static final String DO_RETRIEVE_BY_MATRICOLA = 
    "SELECT * " + 
    "FROM " + TABLE_NAME + " " + 
    "WHERE matricola = ?;";
  
  public static final String DO_RETRIEVE_BY_NOME = 
    "SELECT * " + 
    "FROM " + TABLE_NAME + " " + 
    "WHERE nome = ?;";
  
  public static final String DO_RETRIEVE_BY_CFU = 
    "SELECT * " + 
    "FROM " + TABLE_NAME + " " + 
    "WHERE cfu = ?;";
  
  public static final String DO_RETRIEVE_BY_OCCUPAZIONE = 
    "SELECT * " + 
    "FROM " + TABLE_NAME + " " + 
    "WHERE occupazione LIKE ?;";
  
  public static final String DO_RETRIEVE_BY_TUTOR_ACC = 
    "SELECT * " + 
    "FROM " + TABLE_NAME + " " + 
    "WHERE tutorAccID = ?;";
  
  public static final String DO_RETRIEVE_BY_TUTOR_AZ = 
    "SELECT * " + 
    "FROM " + TABLE_NAME + " " + 
    "WHERE tutorAzID = ?;";
  
  public static final String DO_RETRIEVE_BY_TIROCINIO = 
    "SELECT * " + 
    "FROM " + TABLE_NAME + " " + 
    "WHERE tirocinio = ?;";
  
  public static final String DO_RETRIEVE_BY_REGISTRO = 
    "SELECT * " + 
    "FROM " + TABLE_NAME + " " + 
    "WHERE registro = ?;";
  
  public static final String DO_RETRIEVE_BY_PROGETTO_FORMATIVO =
    "SELECT * " +
    "FROM " + TABLE_NAME + " " +
    "WHERE tirocinio = ?;";
}
