package it.unisa.model;

import java.io.Serializable;
import java.sql.Date;

public class CompilaBean extends AbstractBean implements Serializable {
  private String studente;
  private int questionario;
  private Date data_inizio;
  private Date data_fine;
  
  public CompilaBean() {
    
  }
  
  public CompilaBean(String studente, int questionario, Date data_inizio, Date data_fine) {
    this.studente = studente;
    this.questionario = questionario;
    this.data_inizio = data_inizio;
    this.data_fine = data_fine;
  }

  public String getUtente() {
    return studente;
  }

  public void setUtente(String studente) {
    this.studente = studente;
  }

  public int getQuestionario() {
    return questionario;
  }

  public void setQuestionario(int questionario) {
    this.questionario = questionario;
  }

  public Date getData_inizio() {
    return data_inizio;
  }

  public void setData_inizio(Date data_inizio) {
    this.data_inizio = data_inizio;
  }

  public Date getData_fine() {
    return data_fine;
  }

  public void setData_fine(Date data_fine) {
    this.data_fine = data_fine;
  }
  
  @Override
  public  String toString() {
    StringBuilder str = new StringBuilder();
    str.append(getClass().getName() + "{");
    str.append("studente=" + studente + ", ");
    str.append("questionario=" + questionario + ", ");
    str.append("data_inizio=" + data_inizio + ", ");
    str.append("data_fine=" + data_fine + "}");
    return str.toString();
  }
}
