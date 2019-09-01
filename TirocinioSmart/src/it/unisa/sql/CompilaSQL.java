package it.unisa.sql;

public class CompilaSQL {
  
  public static final String TABLE_NAME = "compila";
  
  public static final String DO_SAVE = 
    "INSERT INTO " + TABLE_NAME + " " + 
    "VALUES (?, ?, ?, ?);";

  public static final String DO_DELETE =
    "DELETE " +
    "FROM " + TABLE_NAME + " " +
    "WHERE utenteID = ? AND questionarioID = ?;";
  
  public static final String DO_UPDATE =
    "UPDATE " + TABLE_NAME + " " +
    "SET utenteID = ?, questionarioID = ?, tipoUtente = ?, dataCompilazione = ? " +
    "WHERE utenteID = ? AND questionarioID = ?;"; 
  
  public static final String DO_RETRIEVE_ALL = 
    "SELECT * " + 
    "FROM " + TABLE_NAME + ";";
  
  public static final String DO_RETRIEVE_BY_KEY =
    "SELECT * " +
    "FROM " + TABLE_NAME + " " +
    "WHERE utenteID = ? AND questionarioID = ?;";
  
  public static final String DO_RETRIEVE_BY_STUDENTE = 
    "SELECT * " + 
    "FROM " + TABLE_NAME + " " + 
    "WHERE utenteID = ? AND tipoUtente = 'Studente';";
  
  public static final String DO_RETRIEVE_BY_TUTOR =
    "SELECT * " +
    "FROM " + TABLE_NAME + " " +
    "WHERE utenteID = ? AND tipoUtente = 'Tutor';";
  
  public static final String DO_RETRIEVE_BY_UFFICIO =
    "SELECT * " +
    "FROM " + TABLE_NAME + " " +
    "WHERE utenteID = ? AND tipoUtente = 'Ufficio';";
  
  public static final String DO_RETRIEVE_BY_QUESTIONARIO = 
    "SELECT * " + 
    "FROM " + TABLE_NAME + " " + 
    "WHERE questionarioID = ?;";
  
  public static final String DO_RETRIEVE_BY_DATA_COMPILAZIONE_BETWEEN = 
    "SELECT * " + 
    "FROM " + TABLE_NAME + " " + 
    "WHERE dataCompilazione BETWEEN ? AND ?;";
  
  public static final String IS_QUESTIONARIO_COMPILATO =
    "SELECT * " +
    "FROM " + TABLE_NAME + " " +
    "WHERE utenteID = ? AND questionarioID = ? AND dataCompilazione IS NOT NULL;";
}
