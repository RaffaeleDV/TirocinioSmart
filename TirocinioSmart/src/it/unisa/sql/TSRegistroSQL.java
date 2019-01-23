package it.unisa.sql;

import it.unisa.model.RegistroModelDM;
import it.unisa.model.StudenteModelDM;

public class TSRegistroSQL {
  public static final String TABLE_NAME = "registro";
  public static String queryRegistroByID = "SELECT * FROM " + RegistroModelDM.TABLE_NAME + " WHERE id = ?;";
  public static String insertRegistro = "INSERT INTO " + RegistroModelDM.TABLE_NAME + " VALUES (?,?,?,?,?,?);";
  public static String updateRegistro = "UPDATE " + RegistroModelDM.TABLE_NAME + " SET id = ?, nome = ?, descrizione = ?, consegna = ?, confermaTutorAcc = ?, confermaTutorAz = ? WHERE id = ?;";
  
  public static String queryRegistroStudente = "SELECT s.matricola, s.nome, s.cfu, s.pass, s.tutorAccID, s.tutorAzID, s.tirocinio, s.registro FROM " + RegistroModelDM.TABLE_NAME + " AS r, " + StudenteModelDM.TABLE_NAME + " AS s" + " WHERE s.registro = ? AND s.registro = r.id;";
  public static String queryRegistroUfficio = "";
  public static String queryRegistroTutorAziendale = "";
  public static String queryRegistroTutorAccademico = "";
  public static String queryRegistroTirocinio = "";
  public static String queryIsStudenteRegistro = "SELECT * FROM " + RegistroModelDM.TABLE_NAME + " AS r, " + StudenteModelDM.TABLE_NAME + " AS s" + " WHERE s.registro = ? AND s.registro = r.id;";
  public static String queryRegistriTirocinio = "";
  
  public static String queryRegistriUfficio = "";
  public static String queryRegistriStudente = "SELECT * FROM " + RegistroModelDM.TABLE_NAME + " WHERE id = ?;";
  public static String loadRegistroStudente = "SELECT r.* FROM studente AS s, registro AS r WHERE s.registro = r.id AND s.matricola = ?;";
  
  public static String queryRegistriTutorAz = "SELECT r.* FROM studente AS s, registro AS r WHERE s.registro = r.id AND s.tutorAzID = ?;";
  public static String queryRegistriTutorAcc = "SELECT r.* FROM studente AS s, registro AS r WHERE s.registro = r.id AND s.tutorAccID = ?;";
  public static String queryIsTutorAccRegistro = "SELECT * FROM studente AS s WHERE s.tutorAccID = ? AND s.registro = ?;";
  public static String queryIsTutorAzRegistro = "SELECT * FROM studente AS s WHERE s.tutorAzID = ? AND s.registro = ?;";
  public static String queryRegistri = "SELECT * FROM " + TABLE_NAME;
}
