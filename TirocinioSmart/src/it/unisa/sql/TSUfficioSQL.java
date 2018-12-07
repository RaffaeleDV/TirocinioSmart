package it.unisa.sql;

import it.unisa.model.UfficioModelDM;

public class TSUfficioSQL {
  public static final String queryUfficioById = "SELECT * FROM " + UfficioModelDM.TABLE_NAME + " WHERE id = ?;";
  public static final String insertUfficio = "INSERT INTO " + UfficioModelDM.TABLE_NAME + " VALUES (?,?,?);";
}
