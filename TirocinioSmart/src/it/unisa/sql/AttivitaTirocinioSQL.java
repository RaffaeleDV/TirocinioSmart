package it.unisa.sql;

public class AttivitaTirocinioSQL {
  
  public static final String TABLE_NAME = "attivita_tirocinio";
  
  public static final String DO_RETRIEVE_BY_KEY = 
    "SELECT * " + 
    "FROM " + TABLE_NAME + " " + 
    "WHERE id = ?;";
  
  public static final String DO_RETRIEVE_ALL = 
    "SELECT * " + 
    "FROM " + TABLE_NAME + ";";
  
  public static final String DO_SAVE = 
    "INSERT INTO " + TABLE_NAME + " " + 
    "VALUES (?, ?, ?, ?, ?, ?);";
  
  public static final String DO_DELETE = 
    "DELETE FROM " + TABLE_NAME + " " + 
    "WHERE id = ?;";
  
  public static final String DO_UPDATE = 
    "UPDATE " + TABLE_NAME + " " +
    "SET registroID = ?, strutturaOspitanteID = ?, descrizione = ?, data = ?, ore = ? " +
    "WHERE id = ?;";
  
  public static final String DO_RETRIEVE_BY_REGISTRO = 
    "SELECT * " + 
    "FROM " + TABLE_NAME + " " + 
    "WHERE registroID = ?;";
  
  public static final String DO_RETRIEVE_BY_STRUTTURA_OSPITANTE = 
    "SELECT * " + 
    "FROM " + TABLE_NAME + " " + 
    "WHERE strutturaOspitanteID = ?;";
  
  public static final String DO_RETRIEVE_BY_DATA_BETWEEN = 
    "SELECT * " + 
    "FROM " + TABLE_NAME + " " + 
    "WHERE data BETWEEN ? AND ?;";
  
  public static final String DO_RETRIEVE_BY_ORE_BETWEEN = 
    "SELECT * " + 
    "FROM " + TABLE_NAME + " " + 
    "WHERE ore BETWEEN ? AND ?;";
}
