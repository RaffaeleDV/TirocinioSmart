package it.unisa.sql;

public class CompilaSQL {
  
  public static final String TABLE_NAME = "compila";
  
  public static final String DO_SAVE = 
    "INSERT INTO " + TABLE_NAME + " " + 
    "VALUES (?, ?, ?);";
  
  public static final String DO_RETRIEVE_ALL = 
    "SELECT * " + 
    "FROM " + TABLE_NAME + ";";
  
  public static final String DO_RETRIEVE_BY_STUDENTE = 
    "SELECT * " + 
    "FROM " + TABLE_NAME + " " + 
    "WHERE studenteID = ?;";
  
  public static final String DO_RETRIEVE_BY_QUESTIONARIO = 
    "SELECT * " + 
    "FROM " + TABLE_NAME + " " + 
    "WHERE questionarioID = ?;";
  
  public static final String DO_RETRIEVE_BY_DATA_COMPILAZIONE_BETWEEN = 
    "SELECT * " + 
    "FROM " + TABLE_NAME + " " + 
    "WHERE dataCompilazione BETWEEN ? AND ?;";
}
