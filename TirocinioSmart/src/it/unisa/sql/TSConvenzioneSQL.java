package it.unisa.sql;

import it.unisa.model.ConvenzioneModelDM;

public class TSConvenzioneSQL {
  public static final String queryConvenzioneByID = "SELECT * FROM " + ConvenzioneModelDM.TABLE_NAME + " WHERE id = ?;";
  public static final String createConvenzione = "INSERT INTO " + ConvenzioneModelDM.TABLE_NAME + " VALUES (?,?);";
}
