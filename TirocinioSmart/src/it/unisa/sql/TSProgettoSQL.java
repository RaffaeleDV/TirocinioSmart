package it.unisa.sql;

import it.unisa.model.ProgettoFormativoModelDM;

public class TSProgettoSQL {
  public static final String queryProgettoByID = "SELECT * FROM " + ProgettoFormativoModelDM.TABLE_NAME + " WHERE id = ?;";
  public static final String insertProgetto = "INSERT INTO " + ProgettoFormativoModelDM.TABLE_NAME + " VALUES (?,?,?);";
  public static final String isTirocinioStudenteQuery = "";
  public static final String tirociniStudenteQuery = "";
  public static final String tirocinioStudenteQuery = "";
  public static final String ufficioTirocinioQuery = "";
  public static final String registroTirocinioQuery = "";
  public static final String studenteTirocinioQuery = "";
  public static final String tirociniUfficioQuery = "";
}
