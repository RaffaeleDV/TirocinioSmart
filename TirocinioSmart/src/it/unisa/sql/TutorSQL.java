package it.unisa.sql;

public class TutorSQL {
  
  public static final String TABLE_NAME = "tutor";
  
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
    "SET convenzioneID = ?, email = ?, nome = ?, pass = ?, tipo = ? " +
    "WHERE id = ?";
  
  public static final String DO_RETRIEVE_TUTORS_BY_EMAIL = 
    "SELECT * " + 
    "FROM " + TABLE_NAME + " " + 
    "WHERE email = ?;";
  
  public static final String DO_RETRIEVE_TUTORS_BY_NOME = 
    "SELECT * " + 
    "FROM " + TABLE_NAME + " " + 
    "WHERE nome = ?;";
  
  public static final String DO_RETRIEVE_TUTORS_BY_TIPO = 
    "SELECT * " + 
    "FROM " + TABLE_NAME + " " + 
    "WHERE tipo = ?;";
  
  public static final String DO_RETRIEVE_TUTORS_BY_CONVENZIONE = 
    "SELECT * " + 
    "FROM " + TABLE_NAME + " " + 
    "WHERE convenzioneID = ?;";
}