package it.unisa.sql;

public class SvoltoInSQL {
  
  public static final String TABLE_NAME = "svolto_in";
  
  public static final String DO_RETRIEVE_ALL = 
    "SELECT * " + 
    "FROM " + TABLE_NAME + ";";
  
  public static final String DO_SAVE = 
    "INSERT INTO " + TABLE_NAME + " " + 
    "VALUES (?, ?, ?, ?, ?);";
  
  public static final String DO_RETRIEVE_BY_PROGETTO_FORMATIVO = 
    "SELECT * " + 
    "FROM " + TABLE_NAME + " " + 
    "WHERE idProgettoFormativo = ?;";
  
  public static final String DO_RETRIEVE_BY_STRUTTURA_OSPITANTE = 
    "SELECT * " + 
    "FROM " + TABLE_NAME + " " + 
    "WHERE idStrutturaOspitante = ?;";
  
  public static final String DO_RETRIEVE_BY_TUTOR_AZ = 
    "SELECT * " + 
    "FROM " + TABLE_NAME + " " + 
    "WHERE tutorAzID = ?;";
  
  public static final String DO_RETRIEVE_BY_INIZIO_PERIODO = 
    "SELECT * " + 
    "FROM " + TABLE_NAME + " " + 
    "WHERE inizioPeriodo = ?;";
  
  public static final String DO_RETRIEVE_BY_TERMINE_PERIODO = 
    "SELECT * " + 
    "FROM " + TABLE_NAME + " " + 
    "WHERE terminePeriodo = ?;";
  
  public static final String DO_RETRIEVE_IN_PERIODO = 
    "SELECT * " + 
    "FROM " + TABLE_NAME + " " + 
    "WHERE (inizioPeriodo BETWEEN ? AND inizioPeriodo) AND (terminePeriodo BETWEEN terminePeriodo AND ?);";
}
