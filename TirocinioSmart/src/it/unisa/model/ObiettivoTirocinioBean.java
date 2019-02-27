package it.unisa.model;

import java.io.Serializable;
import java.util.logging.Logger;
import java.util.logging.Level;

public class ObiettivoTirocinioBean extends AbstractBean implements Serializable, Cloneable {
  private int id;
  private int progettoFormativoID;
  private String obiettivo;
  /*
   * media,
   * alta,
   * bassa
   */
  private String priorita;
  
  public ObiettivoTirocinioBean() {
    
  }
  
  public ObiettivoTirocinioBean(
      int id,
      int progettoFormativoID,
      String obiettivo,
      String priorita) {
    this.id = id;
    this.progettoFormativoID = progettoFormativoID;
    this.obiettivo = obiettivo;
    this.priorita = priorita;
  }

  public int getID() {
    return id;
  }

  public void setID(int id) {
    this.id = id;
  }

  public int getProgettoFormativoID() {
    return progettoFormativoID;
  }

  public void setProgettoFormativoID(int progettoFormativoID) {
    this.progettoFormativoID = progettoFormativoID;
  }

  public String getObiettivo() {
    return obiettivo;
  }

  public void setObiettivo(String obiettivo) {
    this.obiettivo = obiettivo;
  }

  public String getPriorita() {
    return priorita;
  }

  public void setPriorita(String priorita) {
    this.priorita = priorita;
  }
  
  @Override
  public String toString() {
    StringBuilder str = new StringBuilder();
    str.append(getClass().getName() + "[");
    str.append("id=" + id + ", ");
    str.append("progettoFormativoID=" + progettoFormativoID + ", ");
    str.append("obiettivo=" + obiettivo + ", ");
    str.append("priorita=" + priorita + "]");
    return str.toString();
  }
  
  @Override
  public boolean equals(Object o) {
    
    if (o == null)
      return false;
    
    if (!getClass().getName().equals(o.getClass().getName()))
      return false;
    
    ObiettivoTirocinioBean obiettivoTirocinioBean = (ObiettivoTirocinioBean) o;
    
    if (
        id == obiettivoTirocinioBean.getID() &&
        progettoFormativoID == obiettivoTirocinioBean.getProgettoFormativoID() &&
        obiettivo.equals(obiettivoTirocinioBean.getObiettivo()) &&
        priorita == obiettivoTirocinioBean.getPriorita())
      return true;
    
    return false;
  }
  
  @Override
  public ObiettivoTirocinioBean clone() throws CloneNotSupportedException {
    ObiettivoTirocinioBean obbiettivoTirocinioBean = null;
    
    try {
      obbiettivoTirocinioBean = (ObiettivoTirocinioBean) super.clone();
    } catch (CloneNotSupportedException e) {
      Logger.getGlobal().log(Level.INFO, e.getMessage());
    }
    
    return obbiettivoTirocinioBean;
  }
}
