package it.unisa.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.logging.Logger;
import java.util.logging.Level;

public class CompilaBean extends AbstractBean implements Serializable, Cloneable {
  private static final long serialVersionUID = 1L;
  private int utenteID;
  private String tipoUtente;
  private int questionarioID;
  private Date dataCompilazione;
  
  public CompilaBean() {
    super();
  }
  
  public CompilaBean(
      int utenteID,
      String tipoUtente,
      int questionarioID,
      Date dataCompilazione) {
    super();
    this.utenteID = utenteID;
    this.tipoUtente = tipoUtente;
    this.questionarioID = questionarioID;
    this.dataCompilazione = dataCompilazione;
  }

  public int getUtenteID() {
    return utenteID;
  }
  
  public int getQuestionarioID() {
    return questionarioID;
  }
  
  public String getTipoUtente() {
    return tipoUtente;
  }
  
  public Date getDataCompilazione() {
    return dataCompilazione;
  }
  
  public void setUtenteID(int utenteID) {
    this.utenteID = utenteID;
  }
  
  public void setQuestionarioID(int questionarioID) {
    this.questionarioID = questionarioID;
  }
  
  public void setTipoUtente(String tipoUtente) {
    this.tipoUtente = tipoUtente;
  }
  
  public void setDataCompilazione(Date dataCompilazione) {
    this.dataCompilazione = dataCompilazione;
  }
  
  @Override
  public  String toString() {
    StringBuilder str = new StringBuilder();
    str.append(getClass().getName() + "{");
    str.append("utenteID=" + utenteID + ", ");
    str.append("questionarioID=" + questionarioID + ", ");
    str.append("tipoUtente=" + tipoUtente + ", ");
    str.append("dataCompilazione=" + dataCompilazione + "}");
    return str.toString();
  }
  
  @Override
  public boolean equals(Object o) {
    if (o == null)
      return false;
    if (!getClass().getName().equals(o.getClass().getName()))
      return false;
    CompilaBean compilaBean = (CompilaBean) o;
    if (utenteID == compilaBean.getUtenteID() &&
        tipoUtente.equals(compilaBean.getTipoUtente()) &&
        questionarioID == compilaBean.getQuestionarioID() &&
        dataCompilazione.equals(compilaBean.getDataCompilazione()))
      return true;
    return false;
  }
  
  @Override
  public CompilaBean clone() throws CloneNotSupportedException {
    CompilaBean compilaBean = null;
    try {
      compilaBean = (CompilaBean) super.clone();
    } catch (CloneNotSupportedException e) {
      Logger.getGlobal().log(Level.SEVERE, e.getMessage());
    }
    return compilaBean;
  }
}
