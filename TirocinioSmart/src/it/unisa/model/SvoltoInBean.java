package it.unisa.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.logging.Logger;
import java.util.logging.Level;

public class SvoltoInBean extends AbstractBean implements Serializable, Cloneable {
  private static final long serialVersionUID = 1L;
  private int progettoFormativoID;
  private int strutturaOspitanteID;
  private int tutorAzID;
  private Date inizioPeriodo;
  private Date terminePeriodo;
  
  public SvoltoInBean() {
    super();
  }
  
  public SvoltoInBean(
      int progettoFormativoID,
      int strutturaOspitanteID,
      int tutorAzID,
      Date inizioPeriodo, 
      Date terminePeriodo) {
    super();
    this.progettoFormativoID = progettoFormativoID;
    this.strutturaOspitanteID = strutturaOspitanteID;
    this.tutorAzID = tutorAzID;
    this.inizioPeriodo = inizioPeriodo;
    this.terminePeriodo = terminePeriodo;
  }

  public int getProgettoFormativoID() {
    return progettoFormativoID;
  }

  public void setProgettoFormativoID(int progettoFormativoID) {
    this.progettoFormativoID = progettoFormativoID;
  }

  public int getStrutturaOspitanteID() {
    return strutturaOspitanteID;
  }

  public void setStrutturaOspitanteID(int strutturaOspitanteID) {
    this.strutturaOspitanteID = strutturaOspitanteID;
  }

  public int getTutorAzID() {
    return tutorAzID;
  }

  public void setTutorAzID(int tutorAzID) {
    this.tutorAzID = tutorAzID;
  }

  public Date getInizioPeriodo() {
    return inizioPeriodo;
  }

  public void setInizioPeriodo(Date inizioPeriodo) {
    this.inizioPeriodo = inizioPeriodo;
  }

  public Date getTerminePeriodo() {
    return terminePeriodo;
  }

  public void setTerminePeriodo(Date terminePeriodo) {
    this.terminePeriodo = terminePeriodo;
  }
  
  @Override
  public String toString() {
    StringBuilder str = new StringBuilder();
    str.append(getClass().getName() + "{");
    str.append("progettoFormativoID=" + progettoFormativoID + ", ");
    str.append("strutturaOspitanteID=" + strutturaOspitanteID + ", ");
    str.append("tutorAzID=" + tutorAzID + ", ");
    str.append("inizioPeriodo=" + inizioPeriodo + ", ");
    str.append("terminePeriodo=" + terminePeriodo + "}");
    return str.toString();
  }
  
  @Override
  public boolean equals(Object o) {
    if (o == null)
      return false;
    if (!getClass().getName().equals(o.getClass().getName()))
      return false;
    SvoltoInBean svoltoInBean = (SvoltoInBean) o;
    if (progettoFormativoID == svoltoInBean.getProgettoFormativoID() &&
        strutturaOspitanteID == svoltoInBean.getStrutturaOspitanteID() &&
        tutorAzID == svoltoInBean.getTutorAzID() &&
        inizioPeriodo.equals(svoltoInBean.getInizioPeriodo()) &&
        terminePeriodo.equals(svoltoInBean.getTerminePeriodo()))
      return true;
    return false;
  }
  
  @Override
  public SvoltoInBean clone() throws CloneNotSupportedException {
    SvoltoInBean svoltoInBean = null;
    try {
      svoltoInBean = (SvoltoInBean) super.clone();
    } catch (CloneNotSupportedException e) {
      Logger.getGlobal().log(Level.SEVERE, e.getMessage());
    }
    return svoltoInBean;
  }
}
