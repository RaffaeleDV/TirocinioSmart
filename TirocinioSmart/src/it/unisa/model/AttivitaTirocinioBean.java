package it.unisa.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AttivitaTirocinioBean extends AbstractBean implements Serializable, Cloneable {
  private static final long serialVersionUID = 1L;
  private int id;
  private int registroID;
  private int strutturaOspitanteID;
  private String descrizione;
  private Date data;
  private int ore;
  
  public AttivitaTirocinioBean() {
    super();
  }
  
  public AttivitaTirocinioBean(
      int id,
      int registroID,
      int strutturaOspitanteID,
      String descrizione,
      Date data,
      int ore) {
    super();
    this.id = id;
    this.registroID = registroID;
    this.strutturaOspitanteID = strutturaOspitanteID;
    this.descrizione = descrizione;
    this.data = data;
    this.ore = ore;
  }

  public int getID() {
    return id;
  }

  public void setID(int id) {
    this.id = id;
  }

  public int getRegistroID() {
    return registroID;
  }

  public void setRegistroID(int registroID) {
    this.registroID = registroID;
  }

  public int getStrutturaOspitanteID() {
    return strutturaOspitanteID;
  }

  public void setStrutturaOspitanteID(int strutturaOspitanteID) {
    this.strutturaOspitanteID = strutturaOspitanteID;
  }

  public String getDescrizione() {
    return descrizione;
  }

  public void setDescrizione(String descrizione) {
    this.descrizione = descrizione;
  }

  public Date getData() {
    return data;
  }

  public void setData(Date data) {
    this.data = data;
  }
  
  public int getOre() {
    return ore;
  }
  
  public void setOre(int ore) {
    this.ore = ore;
  }
  
  @Override
  public String toString() {
    StringBuilder str = new StringBuilder();
    str.append(getClass().getName() + "{");
    str.append("id=" + id + ", ");
    str.append("registroID=" + registroID + ", ");
    str.append("strutturaOspitanteID=" + strutturaOspitanteID + ", ");
    str.append("descrizione=" + descrizione + ", ");
    str.append("data=" + data + ", ");
    str.append("ore=" + ore + "}");
    return str.toString();
  }
  
  @Override
  public boolean equals(Object o) {
    if (o == null)
      return false;
    if (!getClass().getName().equals(o.getClass().getName()))
      return false;
    AttivitaTirocinioBean attivitaTirocinioBean = (AttivitaTirocinioBean) o;
    if (id == attivitaTirocinioBean.getID() &&
        registroID == attivitaTirocinioBean.getRegistroID() &&
        strutturaOspitanteID == attivitaTirocinioBean.getStrutturaOspitanteID() &&
        descrizione.equals(attivitaTirocinioBean.getDescrizione()) &&
        data.equals(attivitaTirocinioBean.getData()) &&
        ore == attivitaTirocinioBean.getOre())
      return true;
    return false;
  }
  
  @Override
  public AttivitaTirocinioBean clone() throws CloneNotSupportedException {
    AttivitaTirocinioBean attivitaTirocinioBean = null;
    try {
      attivitaTirocinioBean = (AttivitaTirocinioBean) super.clone();
    } catch (CloneNotSupportedException e) {
      Logger.getGlobal().log(Level.SEVERE, e.getMessage());
    }
    return attivitaTirocinioBean;
  }
}
