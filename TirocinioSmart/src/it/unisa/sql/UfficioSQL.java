package it.unisa.sql;

public class UfficioSQL {

  public static final String TABLE_NAME  = "ufficio";
  
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
    "SET id = ?, strutturaOspitanteID = ?, email = ?, nome = ?, pass = ? " +
    "WHERE id = ?;";
  
  public static final String DO_RETRIEVE_BY_STRUTTURA_OSPITANTE = 
    "SELECT * " + 
    "FROM " + TABLE_NAME + " " + 
    "WHERE strutturaOspitanteID = ?;";
  
  public static final String DO_RETRIEVE_BY_EMAIL = 
    "SELECT * " + 
    "FROM " + TABLE_NAME + " " + 
    "WHERE email LIKE ?;";
  
  public static final String DO_RETRIEVE_BY_NOME = 
    "SELECT * " + 
    "FROM " + TABLE_NAME + " " + 
    "WHERE nome LIKE ?;";
  
  public static final String DO_RETRIEVE_QUESTIONARI_BY_UTENTE =
    "SELECT " + QuestionarioSQL.TABLE_NAME + ".* " +
    "FROM " + QuestionarioSQL.TABLE_NAME + ", " + CompilaSQL.TABLE_NAME + " " +
    "WHERE " + QuestionarioSQL.TABLE_NAME + ".id = " + CompilaSQL.TABLE_NAME + ".questionarioID AND " + CompilaSQL.TABLE_NAME + ".utenteID = ?;";
}
