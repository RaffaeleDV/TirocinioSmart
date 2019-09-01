package it.unisa.model;

import java.io.Serializable;
import java.util.logging.Logger;
import java.util.logging.Level;

public class ConvenzioneBean extends AbstractBean implements Serializable, Cloneable {
  private static final long serialVersionUID = 1L;
  private int id;
  private String info;
  private String descrizione;
  private int tutorAzID;
  private int tutorAccID;
  
  public ConvenzioneBean() {
    super();
  }

  public ConvenzioneBean(
      int id,
      String info,
      String descrizione,
      int tutorAzID,
      int tutorAccID) {
    super();
    this.id = id;
    this.info = info;
    this.descrizione = descrizione;
    this.tutorAzID = tutorAzID;
    this.tutorAccID = tutorAccID;
  }

  public String getDescrizione() {
    return descrizione;
  }
  
  public void setDescrizione(String descrizione) {
    this.descrizione = descrizione;
  }
  
  public int getTutorAccID() {
    return tutorAzID;
  }
  
  public void setTutorAccID(int tutorAccID) {
    this.tutorAccID = tutorAccID;
  }
  
  public void setID(int id) {
    this.id = id;
  }
  
  public int getID() {
    return id;
  }
  
  public int getTutorAzID() {
    return tutorAzID;
  }
  
  public void setTutorAzID(int tutorAzID) {
    this.tutorAzID = tutorAzID;
  }
  
  public String getInfo() {
    return info;
  }

  public void setInfo(String info) {
    this.info = info;
  }
  
  @Override
  public String toString() {
    StringBuilder str = new StringBuilder();
    str.append(getClass().getName() + "{");
    str.append("id=" + id + ", ");
    str.append("info=" + info + ", ");
    str.append("descrizione=" + descrizione + ", ");
    str.append("tutorAzID=" + tutorAzID + ", ");
    str.append("tutorAccID=" + tutorAccID + "}");
    return str.toString();
  }

  @Override
  public boolean equals(Object other) {
    if (other == null)
      return false;
    if (!getClass().getName().equals(other.getClass().getName()))
      return false;
    ConvenzioneBean convenzioneBean = (ConvenzioneBean) other;
    if (id == convenzioneBean.getID() && 
        info.equals(convenzioneBean.getInfo()) &&
        descrizione.equals(convenzioneBean.getDescrizione()) &&
        tutorAzID == convenzioneBean.getTutorAzID() &&
        tutorAccID == convenzioneBean.getTutorAccID())
      return true;
    return false;
  }

  @Override
  public ConvenzioneBean clone() throws CloneNotSupportedException {
    ConvenzioneBean convenzioneBean = null;
    try {
      convenzioneBean = (ConvenzioneBean) super.clone();
    } catch (CloneNotSupportedException e) {
      Logger.getGlobal().log(Level.SEVERE, e.getMessage());
    }
    return convenzioneBean;
  }
}
