package it.unisa.model;

import java.io.Serializable;

public class ConvenzioneBean extends AbstractBean implements Serializable, Cloneable {

  /**
   * 
   */
  private static final long serialVersionUID = 2768300536554372790L;
  private int id;
  private int tutorAzID;
  private String info;
  private String azienda;
  
  public ConvenzioneBean() {

  }

  public ConvenzioneBean(
      int id, 
      String info, 
      String azienda, 
      int tutorAzID) {
    this.id = id;
    this.info = info;
    this.azienda = azienda;
    this.tutorAzID = tutorAzID;
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

  public String getAzienda() {
    return azienda;
  }
  
  public void setAzienda(String azienda) {
    this.azienda = azienda;
  }
  
  @Override
  public String toString() {
    StringBuilder str = new StringBuilder();
    str.append(getClass().getName() + "[");
    str.append("id=" + id + ", ");
    str.append("info=" + info + ", ");
    str.append("azienda=" + azienda + ", ");
    str.append("tutorAzID=" + tutorAzID + "]");
    return str.toString();
  }

  @Override
  public boolean equals(Object other) {
    
    if (other == null)
      return false;
    
    if (!getClass().getName().equals(other.getClass().getName()))
      return false;
    
    ConvenzioneBean convenzioneBean = (ConvenzioneBean) other;
    
    if (
        id == convenzioneBean.getID() && 
        info.equals(convenzioneBean.getInfo()) &&
        azienda.equals(convenzioneBean.getAzienda()) &&
        tutorAzID == convenzioneBean.getTutorAzID())
      return true;
    
    return false;
  }

  @Override
  public ConvenzioneBean clone() throws CloneNotSupportedException {
    ConvenzioneBean convenzioneBean = null;
    
    try {
      convenzioneBean = (ConvenzioneBean) super.clone();
    } catch (CloneNotSupportedException e) {
      e.printStackTrace();
    }
    
    return convenzioneBean;
  }
}
