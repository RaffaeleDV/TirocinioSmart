package it.unisa.sql;

import it.unisa.model.StudenteModelDM;

public class TSStudenteSQL {
  public static final String queryStudenteByMatricola = "SELECT * FROM " + StudenteModelDM.TABLE_NAME + " WHERE matricola = ?;";
  public static final String insertStudente = "INSERT INTO " + StudenteModelDM.TABLE_NAME + " VALUES (?,?,?,?,?,?,?,?);";
}
