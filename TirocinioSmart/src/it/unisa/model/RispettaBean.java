package it.unisa.model;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RispettaBean extends AbstractBean implements Serializable, Cloneable {
  private static final long serialVersionUID = 1L;
  private int convenzioneID;
  private int progettoFormativoID;
  private int assicurazioneID;

  public RispettaBean() {
    super();
  }
  
  public RispettaBean(
      int convenzioneID,
      int progettoFormativoID,
      int assicurazioneID) {
    super();
    this.convenzioneID = convenzioneID;
    this.progettoFormativoID = progettoFormativoID;
    this.assicurazioneID = assicurazioneID;
  }
  
  public int getConvenzioneID() {
    return convenzioneID;
  }
  
  public int getProgettoFormativoID() {
    return progettoFormativoID;
  }
  
  public int getAssicurazioneID() {
    return assicurazioneID;
  }
  
  public void setConvezioneID(int convenzioneID) {
    this.convenzioneID = convenzioneID;
  }
  
  public void setProgFormativoID(int progettoFormativoID) {
    this.progettoFormativoID = progettoFormativoID;
  }
  
  public void setAssicurazioneID(int assicurazioneID) {
    this.assicurazioneID = assicurazioneID;
  }
  
  public String toStrnig() {
    StringBuilder str = new StringBuilder();
    str.append(getClass().getName() + "{");
    str.append("convenzioneID=" + convenzioneID + ", ");
    str.append("progettoFormativoID=" + progettoFormativoID + ", ");
    str.append("assicurazioneID=" + assicurazioneID + "}");
    return str.toString();
  }
  
  public boolean equals(Object o) {
    if (o == null)
      return false;
    if (!getClass().getName().equals(o.getClass().getName()))
      return false;
    RispettaBean rispettaBean = (RispettaBean) o;
    if (convenzioneID == rispettaBean.getConvenzioneID() &&
        progettoFormativoID == rispettaBean.getProgettoFormativoID() &&
        assicurazioneID == rispettaBean.getAssicurazioneID())
      return true;
    return false;
  }
  
  public Object clone() throws CloneNotSupportedException {
    RispettaBean rispettaBean = null;
    try {
      rispettaBean = (RispettaBean) super.clone();
    } catch (CloneNotSupportedException e) {
      Logger.getGlobal().log(Level.SEVERE, e.getMessage());
    }
    return rispettaBean;
  }
}
