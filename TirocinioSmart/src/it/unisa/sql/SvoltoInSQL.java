package it.unisa.sql;

public class SvoltoInSQL {
  
  public static final String TABLE_NAME = "svolto_in";
  
  public static final String DO_RETRIEVE_ALL = 
    "SELECT * " + 
    "FROM " + TABLE_NAME + ";";
  
  public static final String DO_RETRIEVE_BY_KEY =
    "SELECT * " +
    "FROM " + TABLE_NAME + " " +
    "WHERE progettoFormativoID = ? AND strutturaOspitanteID = ? AND tutorAzID = ?;";

  public static final String DO_SAVE = 
    "INSERT INTO " + TABLE_NAME + " " + 
    "VALUES (?, ?, ?, ?, ?);";
  
  public static final String DO_UPDATE =
    "UPDATE " + TABLE_NAME + " " +
    "SET progettoFormativoID = ?, strutturaOspitanteID = ?, tutorAzID = ?, inizioPeriodo = ?, terminePeriodo = ? " +
    "WHERE progettoFormativoID = ? AND strutturaOspitanteID = ? AND tutorAzID = ?;";
  
  public static final String DO_DELETE =
    "DELETE " +
    "FROM " + TABLE_NAME + " " +
    "WHERE progettoFormativoID = ? AND strutturaOspitanteID = ? AND tutorAzID = ?;";
  
  public static final String DO_RETRIEVE_BY_PROGETTO_FORMATIVO = 
    "SELECT * " + 
    "FROM " + TABLE_NAME + " " + 
    "WHERE progettoFormativoID = ?;";
  
  public static final String DO_RETRIEVE_BY_STRUTTURA_OSPITANTE = 
    "SELECT * " + 
    "FROM " + TABLE_NAME + " " + 
    "WHERE strutturaOspitanteID = ?;";
  
  public static final String DO_RETRIEVE_BY_TUTOR_AZ = 
    "SELECT * " + 
    "FROM " + TABLE_NAME + " " + 
    "WHERE tutorAzID = ?;";
  
  public static final String DO_RETRIEVE_BY_INIZIO_PERIODO = 
    "SELECT * " + 
    "FROM " + TABLE_NAME + " " + 
    "WHERE inizioPeriodo BETWEEN ? AND ?;";
  
  public static final String DO_RETRIEVE_BY_TERMINE_PERIODO = 
    "SELECT * " + 
    "FROM " + TABLE_NAME + " " + 
    "WHERE terminePeriodo BETWEEN ? AND ?;";
  
  public static final String DO_RETRIEVE_IN_PERIODO = 
    "SELECT * " + 
    "FROM " + TABLE_NAME + " " + 
    "WHERE inizioPeriodo = ? AND terminePeriodo = ?;";
}
