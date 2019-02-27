package it.unisa.model;

import java.io.Serializable;
import java.util.logging.Logger;
import java.util.logging.Level;

public class RiguardaBean extends AbstractBean implements Serializable, Cloneable {

  private int progettoFormativoID;
  private int settoreOperativoID;
  private int priorita;
  
  public RiguardaBean() {
    
  }
  
  public RiguardaBean(
      int progettoFormativoID,
      int settoreOperativoID,
      int priorita) {
    this.progettoFormativoID = progettoFormativoID;
    this.settoreOperativoID = settoreOperativoID;
    this.priorita = priorita;
  }

  public int getPriorita() {
    return priorita;
  }
  
  public void setPriorita(int priorita) {
    this.priorita = priorita;
  }
  
  public int getProgettoFormativoID() {
    return progettoFormativoID;
  }

  public void setProgettoFormativoID(int progettoFormativoID) {
    this.progettoFormativoID = progettoFormativoID;
  }

  public int getSettoreOperativoID() {
    return settoreOperativoID;
  }

  public void setSettoreOperativoID(int settoreOperativoID) {
    this.settoreOperativoID = settoreOperativoID;
  }
  
  @Override
  public String toString() {
    StringBuilder str = new StringBuilder();
    str.append(getClass().getName() + "[");
    str.append("progettoFormativoID=" + progettoFormativoID + ", ");
    str.append("settoreOperativoID=" + settoreOperativoID + ", ");
    str.append("priorita=" + priorita + "]");
    return str.toString();
  }
  
  @Override
  public boolean equals(Object o) {
    
    if (o == null)
      return false;
    
    if (!getClass().getName().equals(o.getClass().getName()))
      return false;
    
    RiguardaBean segueBean = (RiguardaBean) o;
    
    if (
        progettoFormativoID == segueBean.getProgettoFormativoID() &&
        settoreOperativoID == segueBean.getSettoreOperativoID() &&
        priorita == segueBean.getPriorita())
      return true;
    
    return false;
  }
  
  @Override
  public RiguardaBean clone() throws CloneNotSupportedException {
    RiguardaBean riguardaBean = null;
    
    try {
      riguardaBean = (RiguardaBean) super.clone();
    } catch(CloneNotSupportedException e) {
      Logger.getGlobal().log(Level.SEVERE, e.getMessage());
    }
    
    return riguardaBean;
  }
}
