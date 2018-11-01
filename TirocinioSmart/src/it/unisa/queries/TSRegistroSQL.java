package it.unisa.sql;

public class TSRegistroSQL {
  public static final String TABLE_NAME = "registro";
  
  public static final String queryRegistroByID = "SELECT * FROM " + TABLE_NAME + " WHERE id = ?;";
  public static final String insertRegistro = "INSERT INTO " + TABLE_NAME + " VALUES (?,?,?,?,?,?);";
  public static final String updateRegistro = "UPDATE " + RegistroModelDM.TABLE_NAME + " SET nome = ?, descrizione = ?, consegna = ?, confermaTutorAcc = ?, confermaTutorAz = ? WHERE id = ?;";
  
  public static final String queryRegistroStudente = "SELECT s.matricola, s.nome, s.cfu, s.pass, s.tutorAccID, s.tutorAzID, s.tirocinio, s.registro FROM " + TABLE_NAME " AS r, " + StudenteModelDM.TALBE_NAME + " AS s" + " WHERE s.registro = ? AND s.registro = r.id;";
  public static final String queryRegistroUfficio = "";
  public static final String queryRegistroTutorAziendale = "";
  public static final String queryRegistroTutorAccademico = "";
  public static final String queryRegistroTirocinio = "";
  public static final String queryIsStudenteRegistro = "SELECT * FROM " + TABLE_NAME " AS r, " + StudenteModelDM.TALBE_NAME + " AS s" + " WHERE s.registro = ? AND s.registro = r.id;";
  public static final String queryRegistriTirocinio = "";
  public static final String queryIsTutorRegistro = "";
  public static final String queryRegistriUfficio = "";
  public static final String queryRegistriStudente = "SELECT * FROM " + TABLE_NAME + " WHERE id = ?;";
}
