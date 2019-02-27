package it.unisa.sql;

public class ProgettoFormativoSQL {
  
  public static final String TABLE_NAME = "prog_form";
  
  public static final String DO_RETRIEVE_ALL = 
    "SELECT * " + 
    "FROM " + TABLE_NAME + ";";
  
  public static final String DO_SAVE = 
    "INSERT INTO " + TABLE_NAME + " " + 
    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
  
  public static final String DO_DELETE = 
    "DELETE FROM " + TABLE_NAME + " " + 
    "WHERE id = ?;";
  
  public static final String DO_UPDATE =
    "UPDATE " + TABLE_NAME + " " +
    "SET info = ?, idsAssicurazioni = ?, formazione = ?, modalita = ?, responsabile = ?, inizioPeriodo = ?, terminePeriodo = ?, dataRilascio = ?, approvazione = ?, approvazioneGenitori = ?, approvazioneRespo = ?, approvazioneTutorAcc = ?, approvazioneTutorAz = ?, ufficioID = ?, convenzioneID = ?, tutorAccID = ? " + 
    "WHERE id = ?;";
  
  public static final String DO_RETRIEVE_BY_KEY = 
    "SELECT * " + 
    "FROM " + TABLE_NAME + " " + 
    "WHERE id = ?;";
  
  public static final String DO_RETRIEVE_BY_INFO = 
    "SELECT * " + 
    "FROM " + TABLE_NAME + " " + 
    "WHERE info LIKE ?;";
  
  public static final String DO_RETRIEVE_BY_IDS_ASSICURAZIONI = 
    "SELECT * " + 
    "FROM " + TABLE_NAME + " " + 
    "WHERE idsAssicurazioni LIKE ?;";
  
  public static final String DO_RETRIEVE_BY_FORMAZIONE = 
    "SELECT * " + 
    "FROM " + TABLE_NAME + " " + 
    "WHERE formazione LIKE ?;";
  
  public static final String DO_RETRIEVE_BY_MODALITA = 
    "SELECT * " + 
    "FROM " + TABLE_NAME + " " + 
    "WHERE modalita LIKE ?;";
  
  public static final String DO_RETRIEVE_BY_RESPONSABILE = 
    "SELECT * " + 
    "FROM " + TABLE_NAME + " " + 
    "WHERE responsabile = ?;";
  
  public static final String DO_RETRIEVE_BY_INIZIO_PERIODO = 
    "SELECT * " + 
    "FROM " + TABLE_NAME + " " + 
    "WHERE inizioPeriodo = ?;";
  
  public static final String DO_RETRIEVE_BY_TERMINE_PERIODO = 
    "SELECT * " + 
    "FROM " + TABLE_NAME + " " + 
    "WHERE terminePeriodo = ?;";
  
  public static final String DO_RETRIEVE_BY_DATA_RILASCIO = 
    "SELECT * " + 
    "FROM " + TABLE_NAME + " " + 
    "WHERE dataRilascio = ?;";
  
  public static final String DO_RETRIEVE_BY_APPROVAZIONE = 
    "SELECT * " + 
    "FROM " + TABLE_NAME + " " + 
    "WHERE approvazione = ?;";
  
  public static final String DO_RETRIEVE_BY_APPROVAZIONE_GENITORI = 
    "SELECT * " + 
    "FROM " + TABLE_NAME + " " + 
    "WHERE approvazioneGenitori = ?;";
  
  public static final String DO_RETRIEVE_BY_APPROVAZIONE_RESPO = 
    "SELECT * " + 
    "FROM " + TABLE_NAME + " " + 
    "WHERE approvazioneRespo = ?;";
  
  public static final String DO_RETRIEVE_BY_APPROVAZIONE_TUTOR_ACC = 
    "SELECT * " + 
    "FROM " + TABLE_NAME + " " + 
    "WHERE approvazioneTutorAcc = ?;";
  
  public static final String DO_RETRIEVE_BY_APPROVAZIONE_TUTOR_AZ = 
    "SELECT * " + 
    "FROM " + TABLE_NAME + " " + 
    "WHERE approvazioneTutorAz = ?;";
  
  public static final String DO_RETRIEVE_BY_UFFICIO = 
    "SELECT * " + 
    "FROM " + TABLE_NAME + " " + 
    "WHERE ufficioID = ?;";
  
  public static final String DO_RETRIEVE_BY_CONVENZIONE = 
    "SELECT * " + 
    "FROM " + TABLE_NAME + " " + 
    "WHERE convenzioneID = ?;";
  
  public static final String DO_RETRIEVE_BY_TUTOR_ACC = 
    "SELECT * " + 
    "FROM " + TABLE_NAME + " " + 
    "WHERE tutorAccID = ?;";
  
  public static final String DO_RETRIEVE_BY_MATRICOLA =
    "SELECT * " +
    "FROM " + TABLE_NAME + ", " + StudenteSQL.TABLE_NAME + " " +
    "WHERE " + TABLE_NAME + ".id = " + StudenteSQL.TABLE_NAME + ".progettoFormativoID AND " + StudenteSQL.TABLE_NAME + ".matricola = ?;";
  
  public static final String DO_RETRIEVE_BY_TUTOR =
    "SELECT " + 
        TABLE_NAME + ".id, " +
        TABLE_NAME + ".info, " +
        TABLE_NAME + ".idsAssicurazioni, " +
        TABLE_NAME + ".formazione, " +
        TABLE_NAME + ".modalita, " +
        TABLE_NAME + ".responsabile, " +
        TABLE_NAME + ".inizioPeriodo, " +
        TABLE_NAME + ".terminePeriodo, " +
        TABLE_NAME + ".dataRilascio, " +
        TABLE_NAME + ".approvazioneGenitori, " +
        TABLE_NAME + ".approvazione, " +
        TABLE_NAME + ".approvazioneRespo, " +
        TABLE_NAME + ".approvazioneTutorAcc, " +
        TABLE_NAME + ".approvazioneTutorAz, " +
        TABLE_NAME + ".ufficioID, " +
        TABLE_NAME + ".convenzioneID, " +
        TABLE_NAME + ".tutorAccID " +
    "FROM " + StudenteSQL.TABLE_NAME + ", " + TABLE_NAME + " " +
    "WHERE " + StudenteSQL.TABLE_NAME + ".progettoFormativoID = " + TABLE_NAME + ".id AND " + "(" + StudenteSQL.TABLE_NAME + ".tutorAccID = ? OR " + StudenteSQL.TABLE_NAME + ".tutorAzID = ?);";
  
  public static final String IS_STUDENTE_PROGETTO_FORMATIVO =
    "SELECT * " +
    "FROM " + StudenteSQL.TABLE_NAME + " " +
    "WHERE progettoFormativoID = ?;";
}
