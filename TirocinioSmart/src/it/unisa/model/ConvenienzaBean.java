package it.unisa.model;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConvenienzaBean extends AbstractBean implements Serializable, Cloneable {

  private int id;
  private int convenzioneID;
  private String descrizione;
  
  public ConvenienzaBean() {
    
  }
  
  public ConvenienzaBean(
      int id, 
      int convenzioneID, 
      String descrizione) {
    this.id = id;
    this.convenzioneID = convenzioneID;
    this.descrizione = descrizione;
  }

  public int getID() {
    return id;
  }

  public void setID(int id) {
    this.id = id;
  }

  public int getConvenzioneID() {
    return convenzioneID;
  }

  public void setConvenzioneID(int convenzioneID) {
    this.convenzioneID = convenzioneID;
  }

  public String getDescrizione() {
    return descrizione;
  }

  public void setDescrizione(String descrizione) {
    this.descrizione = descrizione;
  }
  
  @Override
  public String toString() {
    StringBuilder str = new StringBuilder();
    str.append(getClass().getName() + "[");
    str.append("id=" + id + ", ");
    str.append("convenzioneID=" + convenzioneID + ", ");
    str.append("descrizione=" + descrizione + "]");
    return str.toString();
  }
  
  @Override
  public boolean equals(Object o) {
    
    if (o == null)
      return false;
    
    if (!getClass().getName().equals(o.getClass().getName()))
      return false;
    
    ConvenienzaBean convenienzaBean = (ConvenienzaBean) o;
    
    if (
        id == convenienzaBean.getID() &&
        convenzioneID == convenienzaBean.getConvenzioneID() &&
        descrizione.equals(convenienzaBean.getDescrizione()))
      return true;
    
    return false;
  }
  
  @Override
  public ConvenienzaBean clone() throws CloneNotSupportedException {
    ConvenienzaBean convenienzaBean = null;
    
    try {
      convenienzaBean = (ConvenienzaBean) super.clone();
    } catch (CloneNotSupportedException e) {
      Logger.getGlobal().log(Level.SEVERE, e.getMessage());
    }
    
    return convenienzaBean;
  }
}
