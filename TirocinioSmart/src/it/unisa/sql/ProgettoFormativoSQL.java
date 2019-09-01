package it.unisa.sql;

public class ProgettoFormativoSQL {
  
  public static final String TABLE_NAME = "prog_form";
  
  public static final String DO_RETRIEVE_ALL = 
    "SELECT * " + 
    "FROM " + TABLE_NAME + ";";
  
  public static final String DO_RETRIEVE_BY_KEY = 
    "SELECT * " + 
    "FROM " + TABLE_NAME + " " + 
    "WHERE id = ?;";
  
  public static final String DO_SAVE = 
    "INSERT INTO " + TABLE_NAME + " " + 
    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
  
  public static final String DO_DELETE = 
    "DELETE FROM " + TABLE_NAME + " " + 
    "WHERE id = ?;";
  
  public static final String DO_UPDATE =
    "UPDATE " + TABLE_NAME + " " +
    "SET id = ?, info = ?, formazione = ?, modalita = ?, responsabile = ?, inizioPeriodo = ?, terminePeriodo = ?, dataRilascio = ?, approvazioneGenitori = ?, approvazioneRespo = ?, approvazioneTutorAcc = ?, approvazioneTutorAz = ?, ufficioID = ? " + 
    "WHERE id = ?;";
  
  public static final String DO_RETRIEVE_BY_INFO = 
    "SELECT * " + 
    "FROM " + TABLE_NAME + " " + 
    "WHERE info LIKE ?;";
  
  public static final String DO_RETRIEVE_BY_ID_ASSICURAZIONE = 
    "SELECT " + TABLE_NAME + ".* " + 
    "FROM " + TABLE_NAME + ", " + RispettaSQL.TABLE_NAME + " " +
    "WHERE " + TABLE_NAME + ".id = " + RispettaSQL.TABLE_NAME + ".progFormativoID AND " + RispettaSQL.TABLE_NAME + ".assicurazioneID = ?;";
  
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
    "WHERE responsabile LIKE ?;";
  
  public static final String DO_RETRIEVE_BY_INIZIO_PERIODO = 
    "SELECT * " + 
    "FROM " + TABLE_NAME + " " + 
    "WHERE inizioPeriodo BETWEEN ? AND ?;";
  
  public static final String DO_RETRIEVE_BY_TERMINE_PERIODO = 
    "SELECT * " + 
    "FROM " + TABLE_NAME + " " + 
    "WHERE terminePeriodo BETWEEN ? AND ?;";
  
  public static final String DO_RETRIEVE_BY_DATA_RILASCIO = 
    "SELECT * " + 
    "FROM " + TABLE_NAME + " " + 
    "WHERE dataRilascio BETWEEN ? AND ?;";
  
  public static final String DO_RETRIEVE_BY_APPROVAZIONE = 
    "SELECT * " + 
    "FROM " + TABLE_NAME + " " + 
    "WHERE approvazioneGenitori = ? AND approvazioneRespo = ? AND approvazioneTutorAcc = ? AND approvazioneTutorAz = ?;";
  
  public static final String DO_RETRIEVE_BY_APPROVAZIONE_TUTOR_ACC =
    "SELECT * " +
    "FROM " + TABLE_NAME + " " +
    "WHERE approvazioneTutorAcc = ?;";
  
  public static final String DO_RETRIEVE_BY_APPROVAZIONE_TUTOR_AZ =
    "SELECT * " +
    "FROM " + TABLE_NAME + " " +
    "WHERE approvazioneTutorAz = ?;";
  
  public static final String DO_RETRIEVE_BY_APPROVAZIONE_GENITORI = 
    "SELECT * " + 
    "FROM " + TABLE_NAME + " " + 
    "WHERE approvazioneGenitori = ?;";
  
  public static final String DO_RETRIEVE_BY_APPROVAZIONE_RESPO = 
    "SELECT * " + 
    "FROM " + TABLE_NAME + " " + 
    "WHERE approvazioneRespo = ?;";
  
  public static final String DO_RETRIEVE_BY_MATRICOLA =
    "SELECT " + TABLE_NAME + ".* " +
    "FROM " + TABLE_NAME + ", " + StudenteSQL.TABLE_NAME + " " +
    "WHERE " + TABLE_NAME + ".id = " + StudenteSQL.TABLE_NAME + ".progettoFormativoID AND " + StudenteSQL.TABLE_NAME + ".matricola = ?;";
		  
  public static final String DO_RETRIEVE_BY_UFFICIO = 
    "SELECT * " + 
    "FROM " + TABLE_NAME + " " + 
    "WHERE ufficioID = ?;";

  public static final String DO_RETRIEVE_BY_CONVENZIONE =
    "SELECT * " +
    "FROM " + TABLE_NAME + " " +
    "WHERE ;";

  public static final String DO_RETRIEVE_BY_TUTOR =
    "SELECT " + TABLE_NAME + ".* " +
    "FROM " + StudenteSQL.TABLE_NAME + ", " + TABLE_NAME + " " +
    "WHERE " + StudenteSQL.TABLE_NAME + ".progettoFormativoID = " + TABLE_NAME + ".id AND " + "(" + StudenteSQL.TABLE_NAME + ".tutorAccID = ? OR " + StudenteSQL.TABLE_NAME + ".tutorAzID = ?);";
  
  public static final String DO_RETRIEVE_IDS_ASSICURAZIONI =
    "SELECT " + TABLE_NAME + ".* " +
    "FROM " + TABLE_NAME + ", " + RispettaSQL.TABLE_NAME + " " +
    "WHERE id = " + RispettaSQL.TABLE_NAME + ".progFormativoID AND id = ?;";

  public static final String IS_STUDENTE_PROGETTO_FORMATIVO =
    "SELECT * " +
    "FROM " + StudenteSQL.TABLE_NAME + " " +
    "WHERE id = ? AND progettoFormativoID = ?;";
}
