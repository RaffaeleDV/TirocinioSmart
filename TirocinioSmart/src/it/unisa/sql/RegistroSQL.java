package it.unisa.sql;

public class RegistroSQL {
  
  public static final String TABLE_NAME = "registro";
  
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
      "SET id = ?, nome = ?, descrizione = ?, primaIstituzione = ?, ultimoAgg = ?, consegna = ?, confermaTutorAcc = ?, confermaTutorAz = ?, confermaUff = ? " + 
      "WHERE id = ?;";
  
  public static final String DO_RETRIEVE_BY_NOME = 
    "SELECT * " + 
    "FROM " + TABLE_NAME + " " + 
    "WHERE nome LIKE ?;";
  
  public static final String DO_RETRIEVE_BY_DESCRIZIONE = 
    "SELECT * " + 
    "FROM " + TABLE_NAME + " " + 
    "WHERE descrizione LIKE ?;";
  
  public static final String DO_RETRIEVE_BY_PRIMA_ISTITUZIONE = 
    "SELECT * " + 
    "FROM " + TABLE_NAME + " " + 
    "WHERE primaIstituzione BETWEEN ? AND ?;";
  
  public static final String DO_RETRIEVE_BY_ULTIMO_AGG = 
    "SELECT * " + 
    "FROM " + TABLE_NAME + " " + 
    "WHERE ultimoAgg BETWEEN ? AND ?;";
  
  public static final String DO_RETRIEVE_BY_CONSEGNA = 
    "SELECT * " + 
    "FROM " + TABLE_NAME + " " + 
    "WHERE consegna = ?;";
  
  public static final String DO_RETRIEVE_BY_CONFERMA_TUTOR_ACC = 
    "SELECT * " + 
    "FROM " + TABLE_NAME + " " + 
    "WHERE confermaTutorAcc = ?;";
  
  public static final String DO_RETRIEVE_BY_CONFERMA_TUTOR_AZ  = 
    "SELECT * " + 
    "FROM " + TABLE_NAME + " " + 
    "WHERE confermaTutorAz = ?;";
  
  public static final String DO_RETRIEVE_BY_CONFERMA_UFF = 
    "SELECT * " + 
    "FROM " + TABLE_NAME + " " + 
    "WHERE confermaUff = ?;";
  
  public static final String DO_RETRIEVE_BY_STUDENTE =
    "SELECT " + TABLE_NAME + ".* " +
    "FROM " + TABLE_NAME + ", " + StudenteSQL.TABLE_NAME + " " +
    "WHERE " + TABLE_NAME + ".id = " + StudenteSQL.TABLE_NAME + ".registroID AND " + StudenteSQL.TABLE_NAME + ".id = ?;";
  
  public static final String DO_RETRIEVE_BY_TUTOR_AZ =
    "SELECT " + TABLE_NAME + ".* " +
    "FROM " + TABLE_NAME + ", " + StudenteSQL.TABLE_NAME + ", " + TutorSQL.TABLE_NAME + " " +
    "WHERE " + TABLE_NAME + ".id = " + StudenteSQL.TABLE_NAME + ".registroID AND " + StudenteSQL.TABLE_NAME + ".tutorAzID = " + TutorSQL.TABLE_NAME + ".id AND " + TutorSQL.TABLE_NAME + ".id = ?;";
  
  public static final String DO_RETRIEVE_BY_TUTOR_ACC =
    "SELECT " + TABLE_NAME + ".* " +
    "FROM " + TABLE_NAME + ", " + StudenteSQL.TABLE_NAME + ", " + TutorSQL.TABLE_NAME + " " +
    "WHERE " + TABLE_NAME + ".id = " + StudenteSQL.TABLE_NAME + ".registroID AND " + StudenteSQL.TABLE_NAME + ".tutorAccID = " + TutorSQL.TABLE_NAME + ".id AND " + TutorSQL.TABLE_NAME + ".id = ?;";
  
  public static final String DO_RETRIEVE_BY_UFFICIO =
    "SELECT " +  TABLE_NAME + ".* " +
    "FROM " + TABLE_NAME + ", " + StudenteSQL.TABLE_NAME + ", " + ProgettoFormativoSQL.TABLE_NAME + " " +
    "WHERE " + TABLE_NAME + ".id = " + StudenteSQL.TABLE_NAME + ".registroID AND " + StudenteSQL.TABLE_NAME + ".progettoFormativoID = " + ProgettoFormativoSQL.TABLE_NAME + ".id AND " + ProgettoFormativoSQL.TABLE_NAME + ".ufficioID = ?;";
  
  public static final String DO_RETRIEVE_BY_PROGETTO_FORMATIVO =
    "SELECT " + TABLE_NAME + ".* " +
    "FROM " + TABLE_NAME + ", " + StudenteSQL.TABLE_NAME + " " +
    "WHERE " + StudenteSQL.TABLE_NAME + ".registroID = " + TABLE_NAME + ".id AND " + StudenteSQL.TABLE_NAME + ".progettoFormativoID = ?;";
  
  public static final String IS_STUDENTE_REGISTRO = 
    "SELECT * " + 
    "FROM " + StudenteSQL.TABLE_NAME + " " + 
    "WHERE matricola = ? AND registroID = ?;";
  
  public static final String IS_TUTOR_ACC_REGISTRO =
    "SELECT * " +
    "FROM " + StudenteSQL.TABLE_NAME + " " +
    "WHERE tutorAccID = ? AND registroID = ?;";
  
  public static final String IS_TUTOR_AZ_REGISTRO =
    "SELECT * " +
    "FROM " + StudenteSQL.TABLE_NAME + " " +
    "WHERE tutorAzID = ? AND registroID = ?;";
  
  public static final String IS_UFFICIO_REGISTRO =
    "SELECT * " +
    "FROM " + ProgettoFormativoSQL.TABLE_NAME + ", " + StudenteSQL.TABLE_NAME + ", " + TABLE_NAME + " " +
    "WHERE " + ProgettoFormativoSQL.TABLE_NAME + ".id = " + StudenteSQL.TABLE_NAME + ".progettoFormativoID AND " + StudenteSQL.TABLE_NAME + ".registroID = " + TABLE_NAME + ".id AND " + ProgettoFormativoSQL.TABLE_NAME + ".ufficioID = ? AND " + StudenteSQL.TABLE_NAME + ".registroID = ?;";
}
