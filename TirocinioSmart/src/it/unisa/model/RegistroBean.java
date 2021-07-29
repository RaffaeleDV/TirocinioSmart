package it.unisa.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RegistroBean extends AbstractBean implements Serializable, Cloneable {
  private static final long serialVersionUID = -2505094168799389057L;
  private int id;
  private String nome;
  private String descrizione;
  private Date primaIstituzione;
  private Date ultimoAgg;
  private boolean consegna; 
  private boolean confermaTutorAcc; 
  private boolean confermaTutorAz;
  private boolean confermaUff;  
  
  public RegistroBean() {
    super();
  }

  public RegistroBean(
      int id, 
      String nome, 
      String descrizione,
      Date primaIstituzione,
      Date ultimoAgg,
      boolean consegna, 
      boolean confermaTutorAcc, 
      boolean confermaTutorAz,
      boolean confermaUff) {
    super();
    this.id = id;
    this.nome = nome;
    this.descrizione = descrizione;
    this.primaIstituzione = primaIstituzione;
    this.ultimoAgg = ultimoAgg;
    this.consegna  = consegna;
    this.confermaTutorAcc = confermaTutorAcc;
    this.confermaTutorAz = confermaTutorAz;
    this.confermaUff = confermaUff;
  }

  public void setID(int id) {
    this.id = id;
  }
  
  public int getID() {
    return id;
  }

  public String getNome() {
    return nome;
  }

  public String getDescrizione() {
    return descrizione;
  }


  public void setNome(String nome) {
    this.nome = nome;
  }

  public void setDescrizione(String descrizione) {
    this.descrizione = descrizione;
  }
  
  public Date getPrimaIstituzione() {
    return primaIstituzione;
  }
  
  public void setPrimaIstituzione(Date primaIstituzione) {
    this.primaIstituzione = primaIstituzione;
  }
  
  public Date getUltimoAgg() {
    return ultimoAgg;
  }
  
  public void setUltimoAgg(Date ultimoAgg) {
    this.ultimoAgg = ultimoAgg;
  }
  
  public boolean getConsegna() {
    return consegna;
  }
  
  public boolean getConfermaTutorAcc() {
    return confermaTutorAcc;
  }
  
  public boolean getConfermaTutorAz() {
    return confermaTutorAz;
  }
  
  public void setConsegna(boolean consegna) {
    this.consegna = consegna;
  }
  
  public void setConfermaTutorAcc(boolean confermaTutorAcc) {
    this.confermaTutorAcc = confermaTutorAcc;
  }
  
  public void setConfermaTutorAz(boolean confermaTutorAz) {
    this.confermaTutorAz = confermaTutorAz;
  }

  public boolean getConfermaUff() {
    return confermaUff;
  }
  
  public void setConfermaUff(boolean confermaUff) {
    this.confermaUff = confermaUff;
  }
  
  @Override
  public String toString() {
    StringBuilder str = new StringBuilder();
    str.append(getClass().getName() + "{");
    str.append("id=" + id + ", ");
    str.append("nome=" + nome + ", ");
    str.append("descrizione=" + descrizione + ", ");
    str.append("primaIstituzione=" + primaIstituzione.toString() + ", ");
    str.append("ultimoAgg=" + ultimoAgg.toString() + ", ");
    str.append("consegna =" + consegna + ", ");
    str.append("confermaTutorAcc=" + confermaTutorAcc + ", ");
    str.append("confermaTutorAz=" + confermaTutorAz + ", ");
    str.append("confermaUff=" + confermaUff + "}");
    return str.toString();
  }

  @Override
  public boolean equals(Object other) {
    if (other == null)
      return false;
    if (!getClass().getName().equals(other.getClass().getName()))
      return false;
    RegistroBean registroBean = (RegistroBean) other;
    if (id == registroBean.getID() && 
        nome.equals(registroBean.getNome()) &&
        descrizione.equals(registroBean.getDescrizione()) &&
        primaIstituzione.equals(registroBean.getPrimaIstituzione()) &&
        ultimoAgg.equals(registroBean.getUltimoAgg()) &&
        consegna == registroBean.getConsegna() && 
        confermaTutorAcc == registroBean.getConfermaTutorAcc() &&
        confermaTutorAz == registroBean.getConfermaTutorAz() &&
        confermaUff == registroBean.getConfermaUff())
      return true;
    return false;
  }

  @Override
  public RegistroBean clone() throws CloneNotSupportedException {
    RegistroBean other = null;
    try {
      other = (RegistroBean) super.clone();
    } catch (CloneNotSupportedException e) {
      Logger.getGlobal().log(Level.SEVERE, e.getMessage());
    }
    return other;
  }
}
