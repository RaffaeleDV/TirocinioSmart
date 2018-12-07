package it.unisa.sql;

import it.unisa.model.RegistroModelDM;
import it.unisa.model.StudenteModelDM;

public class TSRegistroSQL {
  public static final String queryRegistroByID = "SELECT * FROM " + RegistroModelDM.TABLE_NAME + " WHERE id = ?;";
  public static final String insertRegistro = "INSERT INTO " + RegistroModelDM.TABLE_NAME + " VALUES (?,?,?,?,?,?);";
  public static final String updateRegistro = "UPDATE " + RegistroModelDM.TABLE_NAME + " SET nome = ?, descrizione = ?, consegna = ?, confermaTutorAcc = ?, confermaTutorAz = ? WHERE id = ?;";
  
  public static final String queryRegistroStudente = "SELECT s.matricola, s.nome, s.cfu, s.pass, s.tutorAccID, s.tutorAzID, s.tirocinio, s.registro FROM " + RegistroModelDM.TABLE_NAME + " AS r, " + StudenteModelDM.TABLE_NAME + " AS s" + " WHERE s.registro = ? AND s.registro = r.id;";
  public static final String queryRegistroUfficio = "";
  public static final String queryRegistroTutorAziendale = "";
  public static final String queryRegistroTutorAccademico = "";
  public static final String queryRegistroTirocinio = "";
  public static final String queryIsStudenteRegistro = "SELECT * FROM " + RegistroModelDM.TABLE_NAME + " AS r, " + StudenteModelDM.TABLE_NAME + " AS s" + " WHERE s.registro = ? AND s.registro = r.id;";
  public static final String queryRegistriTirocinio = "";
  public static final String queryIsTutorRegistro = "";
  public static final String queryRegistriUfficio = "";
  public static final String queryRegistriStudente = "SELECT * FROM " + RegistroModelDM.TABLE_NAME + " WHERE id = ?;";
}
