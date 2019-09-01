package it.unisa.sql;

public class StrutturaOspitanteSQL {
  
  public static final String TABLE_NAME = "struttura_ospitante";
  
  public static final String DO_RETRIEVE_BY_KEY = 
    "SELECT * " + 
    "FROM " + TABLE_NAME + " " + 
    "WHERE id = ?;";
  
  public static final String DO_RETRIEVE_ALL = 
    "SELECT * " + 
    "FROM " + TABLE_NAME + ";";
  
  public static final String DO_SAVE = 
    "INSERT INTO " + TABLE_NAME + " " + 
    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";
  
  public static final String DO_DELETE = 
    "DELETE FROM " + TABLE_NAME + " " + 
    "WHERE id = ?;";
  
  public static final String DO_UPDATE =
    "UPDATE " + TABLE_NAME + " " +
    "SET id = ?, nome = ?, ambitoLavorativo = ?, nazione = ?, regione = ?, citta = ?, via = ?, ncivico = ?, ndipendenti = ? " +
    "WHERE id = ?;";
  
  public static final String DO_RETRIEVE_BY_INDIRIZZO = 
    "SELECT * " + 
    "FROM " + TABLE_NAME + " " + 
    "WHERE nazione LIKE ? AND regione LIKE ? AND citta LIKE ? AND via LIKE ? AND ncivico BETWEEN ? AND ?;";
  
  public static final String DO_RETRIEVE_BY_AMBITO_LAVORATIVO = 
    "SELECT * " + 
    "FROM " + TABLE_NAME + " " + 
    "WHERE ambitoLavorativo LIKE ?;";
  
  public static final String DO_RETRIEVE_BY_NDIPENDENTI = 
    "SELECT * " + 
    "FROM " + TABLE_NAME + " " + 
    "WHERE ndipendenti BETWEEN ? AND ?;";
  
  public static final String DO_RETRIEVE_BY_NOME =
    "SELECT * " +
    "FROM " + TABLE_NAME + " " +
    "WHERE nome LIKE ?;";
  
  public static final String DO_RETRIEVE_BY_ATTIVITA_TIROCINIO =
    "SELECT * " +
    "FROM " + TABLE_NAME + ", " + AttivitaTirocinioSQL.TABLE_NAME + " " +
    "WHERE " + TABLE_NAME + ".id = " + AttivitaTirocinioSQL.TABLE_NAME + ".strutturaOspitanteID AND " + AttivitaTirocinioSQL.TABLE_NAME + ".id = ?;";
  
}
