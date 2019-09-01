package it.unisa.sql;

public class RispettaSQL {
  public final static String TABLE_NAME = "rispetta";
  public final static String DO_RETRIEVE_BY_KEY =
    "SELECT * " +
    "FROM " + TABLE_NAME + " " +
    "WHERE " + "convenzioneID = ? AND progettoFormativoID = ?;";
  
  public final static String DO_RETRIEVE_ALL =
    "SELECT * " +
    "FROM " + TABLE_NAME + ";";
  
  public final static String DO_SAVE =
    "INSERT INTO " + TABLE_NAME + " " +
    "VALUES (?, ?, ?);";
  
  public final static String DO_DELETE =
    "DELETE FROM " + TABLE_NAME + " " +
    "WHERE convenzioneID = ? AND progettoFormativoID = ?;";
  
  public static final String DO_UPDATE =
    "UPDATE " + TABLE_NAME + " " +
    "SET convenzioneID = ?, progettoFormativoID = ?, assicurazioneID = ? " +
    "WHERE convenzioneID = ? AND progettoFormativoID = ?;";
  
  public final static String DO_RETRIEVE_BY_CONVENZIONE =
    "SELECT * " +
    "FROM " + TABLE_NAME + " " +
    "WHERE convenzioneID = ?";
  
  public final static String DO_RETRIEVE_BY_PROGETTOFORMATIVO =
    "SELECT * " +
    "FROM " + TABLE_NAME + " " +
    "WHERE progettoFormativoID = ?;";
  
  public final static String DO_RETRIEVE_BY_ID_ASSICURAZIONE =
    "SELECT * " +
    "FROM " + TABLE_NAME + " " +
    "WHERE assicurazioneID = ?;";
}
