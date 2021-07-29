package it.unisa.model;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PercorsoFormativoBean extends AbstractBean implements Serializable, Cloneable {
  private static final long serialVersionUID = 1L;
  private int id;
  private int progettoFormativoID;
  private String percorso;
  
  public PercorsoFormativoBean() {
    super();
  }
  
  public PercorsoFormativoBean(
      int id,
      int progettoFormativoID,
      String percorso) {
    super();
    this.id = id;
    this.progettoFormativoID = progettoFormativoID;
    this.percorso = percorso;
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

  public String getPercorso() {
    return percorso;
  }

  public void setPercorso(String percorso) {
    this.percorso = percorso;
  }
  
  @Override
  public String toString() {
    StringBuilder str = new StringBuilder();
    str.append(getClass().getName() + "{");
    str.append("id=" + id + ", ");
    str.append("progettoFormativoID=" + progettoFormativoID + ", ");
    str.append("percorso=" + percorso + "}");
    return str.toString();
  }
  
  @Override
  public boolean equals(Object o) {
    if (o == null)
      return false;
    if (!getClass().getName().equals(o.getClass().getName()))
      return false;
    PercorsoFormativoBean percorsoFormativoBean = (PercorsoFormativoBean) o;
    if (id == percorsoFormativoBean.getID() &&
        progettoFormativoID == percorsoFormativoBean.getProgettoFormativoID() &&
        percorso.equals(percorsoFormativoBean.getPercorso()))
      return true;
    return false;
  }
  
  @Override
  public PercorsoFormativoBean clone() throws CloneNotSupportedException {
    PercorsoFormativoBean percorsoFormativoBean = null;
    try {
      percorsoFormativoBean = (PercorsoFormativoBean) super.clone();
    } catch (CloneNotSupportedException e){
      Logger.getGlobal().log(Level.SEVERE, e.getMessage());
    }
    return percorsoFormativoBean;
  }
}
