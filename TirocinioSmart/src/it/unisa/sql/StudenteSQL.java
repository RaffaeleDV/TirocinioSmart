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
    "SET id = ?, matricola = ?, nome = ?, cfu = ?, pass = ?, occupazione = ?, tutorAccID = ?, tutorAzID = ?, progettoFormativoID = ?, registroID = ? " +
    "WHERE id = ?;";
  
  public static final String DO_RETRIEVE_BY_MATRICOLA = 
    "SELECT * " + 
    "FROM " + TABLE_NAME + " " + 
    "WHERE matricola LIKE ?;";
  
  public static final String DO_RETRIEVE_BY_NOME = 
    "SELECT * " + 
    "FROM " + TABLE_NAME + " " + 
    "WHERE nome LIKE ?;";
  
  public static final String DO_RETRIEVE_BY_CFU = 
    "SELECT * " + 
    "FROM " + TABLE_NAME + " " + 
    "WHERE cfu LIKE ?;";
  
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
  
  public static final String DO_RETRIEVE_BY_REGISTRO = 
    "SELECT * " + 
    "FROM " + TABLE_NAME + " " + 
    "WHERE registroID = ?;";
  
  public static final String DO_RETRIEVE_BY_PROGETTO_FORMATIVO =
    "SELECT * " +
    "FROM " + TABLE_NAME + " " +
    "WHERE progettoFormativoID = ?;";
  
  public static final String DO_RETRIEVE_QUESTIONARI_BY_UTENTE =
    "SELECT " + QuestionarioSQL.TABLE_NAME + ".* " +
    "FROM " + QuestionarioSQL.TABLE_NAME + ", " + CompilaSQL.TABLE_NAME + " " +
    "WHERE " + QuestionarioSQL.TABLE_NAME + ".id = " + CompilaSQL.TABLE_NAME + ".questionarioID AND " + CompilaSQL.TABLE_NAME + ".utenteID = ?;";
  
  public static final String DO_RETRIEVE_REGISTRO =
    "SELECT * " +
    "FROM " + RegistroSQL.TABLE_NAME + " " +
    "WHERE id = ?;";
}
