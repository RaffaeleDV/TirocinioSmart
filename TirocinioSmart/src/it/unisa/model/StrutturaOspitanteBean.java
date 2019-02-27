package it.unisa.model;

import java.io.Serializable;
import java.util.logging.Logger;
import java.util.logging.Level;

public class StrutturaOspitanteBean extends AbstractBean implements Serializable, Cloneable {
  
  private int id;
  private String nome;
  private String ambitoLavorativo;
  private String nazione;
  private String regione;
  private String citta;
  private String via;
  private int ncivico;
  private int ndipendenti;
  
  public StrutturaOspitanteBean() {
    
  }
  
  public StrutturaOspitanteBean(
      int id,
      String nome,
      String ambitoLavorativo,
      String nazione,
      String regione,
      String citta,
      String via,
      int ncivico,
      int ndipendenti) {
    this.id = id;
    this.nome = nome;
    this.ambitoLavorativo = ambitoLavorativo;
    this.nazione = nazione;
    this.regione = regione;
    this.citta = citta;
    this.via = via;
    this.ncivico = ncivico;
    this.ndipendenti = ndipendenti;
  }

  public int getID() {
    return id;
  }

  public void setID(int id) {
    this.id = id;
  }
  
  public String getNome() {
    return nome;
  }
  
  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getAmbitoLavorativo() {
    return ambitoLavorativo;
  }

  public void setAmbitoLavorativo(String ambitoLavorativo) {
    this.ambitoLavorativo = ambitoLavorativo;
  }

  public String getNazione() {
    return nazione;
  }

  public void setNazione(String nazione) {
    this.nazione = nazione;
  }

  public String getRegione() {
    return regione;
  }

  public void setRegione(String regione) {
    this.regione = regione;
  }

  public String getCitta() {
    return citta;
  }

  public void setCitta(String citta) {
    this.citta = citta;
  }

  public String getVia() {
    return via;
  }

  public void setVia(String via) {
    this.via = via;
  }

  public int getNcivico() {
    return ncivico;
  }

  public void setNcivico(int ncivico) {
    this.ncivico = ncivico;
  }

  public int getNdipendenti() {
    return ndipendenti;
  }

  public void setNdipendenti(int ndipendenti) {
    this.ndipendenti = ndipendenti;
  }
  
  @Override
  public String toString() {
    StringBuilder str = new StringBuilder();
    str.append(getClass().getName() + "[");
    str.append("id=" + id + ", ");
    str.append("nome=" + nome + ", ");
    str.append("ambitoLavorativo=" + ambitoLavorativo + ", ");
    str.append("nazione=" + nazione + ", ");
    str.append("regione=" + regione + ", ");
    str.append("citta=" + citta + ", ");
    str.append("via=" + via + ", ");
    str.append("ncivico=" + ncivico + ", ");
    str.append("ndipendenti=" + ndipendenti + "]");
    return str.toString();
  }
  
  @Override
  public boolean equals(Object o) {
    
    if (o == null)
      return false;
    
    if (!getClass().getName().equals(o.getClass().getName()))
      return false;
    
    StrutturaOspitanteBean strutturaOspitanteBean = (StrutturaOspitanteBean) o;
    
    if (
        id == strutturaOspitanteBean.getID() &&
        nome.equals(strutturaOspitanteBean.getNome()) &&
        ambitoLavorativo.equals(strutturaOspitanteBean.getAmbitoLavorativo()) &&
        nazione.equals(strutturaOspitanteBean.getNazione()) &&
        regione.equals(strutturaOspitanteBean.getRegione()) &&
        citta.equals(strutturaOspitanteBean.getCitta()) &&
        via.equals(strutturaOspitanteBean.getVia()) &&
        ncivico == strutturaOspitanteBean.getNcivico() &&
        ndipendenti == strutturaOspitanteBean.getNdipendenti())
      return true;
    
    return false;
  }
  
  @Override
  public StrutturaOspitanteBean clone() throws CloneNotSupportedException {
    StrutturaOspitanteBean strutturaOspitanteBean = null;
    
    try {
      strutturaOspitanteBean = (StrutturaOspitanteBean) super.clone();
    } catch(CloneNotSupportedException e) {
      Logger.getGlobal().log(Level.SEVERE, e.getMessage());
    }
    
    return strutturaOspitanteBean;
  }
}
