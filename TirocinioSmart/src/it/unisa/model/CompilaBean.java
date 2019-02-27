package it.unisa.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.logging.Logger;
import java.util.logging.Level;

public class CompilaBean extends AbstractBean implements Serializable, Cloneable {
  private int studenteID;
  private int questionarioID;
  private Date dataCompilazione;
  
  public CompilaBean() {
    
  }
  
  public CompilaBean(
      int studenteID, 
      int questionarioID, 
      Date dataCompilazione) {
    this.studenteID = studenteID;
    this.questionarioID = questionarioID;
    this.dataCompilazione = dataCompilazione;
  }

  public int getStudenteID() {
    return studenteID;
  }

  public void setStudenteID(int studenteID) {
    this.studenteID = studenteID;
  }

  public int getQuestionarioID() {
    return questionarioID;
  }

  public void setQuestionarioID(int questionarioID) {
    this.questionarioID = questionarioID;
  }

  public Date getDataCompilazione() {
    return dataCompilazione;
  }

  public void setDataCompilazione(Date dataCompilazione) {
    this.dataCompilazione = dataCompilazione;
  }
  
  @Override
  public  String toString() {
    StringBuilder str = new StringBuilder();
    str.append(getClass().getName() + "[");
    str.append("studenteID=" + studenteID + ", ");
    str.append("questionarioID=" + questionarioID + ", ");
    str.append("dataCompilazione=" + dataCompilazione + "]");
    return str.toString();
  }
  
  @Override
  public boolean equals(Object o) {
    
    if (o == null)
      return false;
    
    if (!getClass().getName().equals(o.getClass().getName()))
      return false;
    
    CompilaBean compilaBean = (CompilaBean) o;
    
    if (
        studenteID == compilaBean.getStudenteID() &&
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
