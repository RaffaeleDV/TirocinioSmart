package it.unisa.model;

import java.io.Serializable;
import java.util.logging.Logger;
import java.util.logging.Level;

public class SettoreOperativoBean extends AbstractBean implements Serializable, Cloneable {

  private int id;
  private String descrizione;
  
  public SettoreOperativoBean() {
    
  }
  
  public SettoreOperativoBean(
      int id,
      String descrizione) {
    this.id = id;
    this.descrizione = descrizione;
  }

  public int getID() {
    return id;
  }

  public void setID(int id) {
    this.id = id;
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
    str.append("descrizione=" + descrizione + "]");
    return str.toString();
  }
  
  @Override
  public boolean equals(Object o) {
    
    if (o == null)
      return false;
    
    if (getClass().getName().equals(o.getClass().getName()))
      return false;
    
    SettoreOperativoBean settoreOperativoBean = (SettoreOperativoBean) o;
    
    if (
        id == settoreOperativoBean.getID() &&
        descrizione.equals(settoreOperativoBean.getDescrizione()))
      return true;
    
    return false;
  }
  
  @Override
  public SettoreOperativoBean clone() throws CloneNotSupportedException {
    SettoreOperativoBean settoreOperativoBean = null;
    
    try {
      settoreOperativoBean = (SettoreOperativoBean) super.clone();
    } catch (CloneNotSupportedException e) {
      Logger.getGlobal().log(Level.SEVERE, e.getMessage());
    }
    
    return settoreOperativoBean;
  }
}
