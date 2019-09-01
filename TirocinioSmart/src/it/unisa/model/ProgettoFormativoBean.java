package it.unisa.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.logging.Logger;
import java.util.logging.Level;

public class ProgettoFormativoBean extends AbstractBean implements Serializable, Cloneable {
  private static final long serialVersionUID = -5993865784733111204L;
  private int id;
  private String info;
  private String formazione;
  private String modalita;
  private String responsabile;
  private Date inizioPeriodo;
  private Date terminePeriodo;
  private Date dataRilascio;
  private boolean approvazioneGenitori;
  private boolean approvazioneRespo;
  private boolean approvazioneTutorAcc;
  private boolean approvazioneTutorAz;
  private int ufficioID;

  public ProgettoFormativoBean() {
    super();
  }

  public ProgettoFormativoBean(
      int id,
      String info,
      String formazione,
      String modalita,
      String responsabile,
      Date inizioPeriodo,
      Date terminePeriodo,
      Date dataRilascio,
      boolean approvazioneGenitori,
      boolean approvazioneRespo,
      boolean approvazioneTutorAcc,
      boolean approvazioneTutorAz,
      int ufficioID) {
    super();
    this.id = id;
    this.info = info;
    this.formazione = formazione;
    this.modalita = modalita;
    this.responsabile = responsabile;
    this.inizioPeriodo = inizioPeriodo;
    this.terminePeriodo = terminePeriodo;
    this.dataRilascio = dataRilascio;
    this.approvazioneGenitori = approvazioneGenitori;
    this.approvazioneRespo = approvazioneRespo;
    this.approvazioneTutorAcc = approvazioneTutorAcc;
    this.approvazioneTutorAz = approvazioneTutorAz;
    this.ufficioID = ufficioID;
  }

  public int getID() {
	return id;
  }

  public void setID(int id) {
	this.id = id;
  }

  public String getInfo() {
	return info;
  }

  public void setInfo(String info) {
	this.info = info;
  }

  public String getFormazione() {
	return formazione;
  }

  public void setFormazione(String formazione) {
	this.formazione = formazione;
  }

  public String getModalita() {
	return modalita;
  }

  public void setModalita(String modalita) {
	this.modalita = modalita;
  }

  public String getResponsabile() {
	return responsabile;
  }

  public void setResponsabile(String responsabile) {
	this.responsabile = responsabile;
  }

  public Date getInizioPeriodo() {
	return inizioPeriodo;
  }

  public void setInizioPeriodo(Date inizioPeriodo) {
	this.inizioPeriodo = inizioPeriodo;
  }

  public Date getTerminePeriodo() {
	return terminePeriodo;
  }

  public void setTerminePeriodo(Date terminePeriodo) {
	this.terminePeriodo = terminePeriodo;
  }

  public Date getDataRilascio() {
	return dataRilascio;
  }

  public void setDataRilascio(Date dataRilascio) {
	this.dataRilascio = dataRilascio;
  }

  public boolean isApprovazioneGenitori() {
	return approvazioneGenitori;
  }

  public void setApprovazioneGenitori(boolean approvazioneGenitori) {
	this.approvazioneGenitori = approvazioneGenitori;
  }

  public boolean isApprovazioneRespo() {
	return approvazioneRespo;
  }

  public void setApprovazioneRespo(boolean approvazioneRespo) {
	this.approvazioneRespo = approvazioneRespo;
  }

  public boolean isApprovazioneTutorAcc() {
	return approvazioneTutorAcc;
  }

  public void setApprovazioneTutorAcc(boolean approvazioneTutorAcc) {
	this.approvazioneTutorAcc = approvazioneTutorAcc;
  }

  public boolean isApprovazioneTutorAz() {
	return approvazioneTutorAz;
  }

  public void setApprovazioneTutorAz(boolean approvazioneTutorAz) {
	this.approvazioneTutorAz = approvazioneTutorAz;
  }

  public int getUfficioID() {
	return ufficioID;
  }

  public void setUfficioID(int ufficioID) {
	this.ufficioID = ufficioID;
  }

  @Override
  public String toString() {
    StringBuilder str = new StringBuilder();
    str.append(getClass().getName() + "{");
    str.append("id=" + id + ", ");
    str.append("info=" + info + ", ");
    str.append("formazione=" + formazione + ", ");
    str.append("modalita=" + modalita + ", ");
    str.append("responsabile=" + responsabile + ", ");
    str.append("inizioPeriodo=" + inizioPeriodo + ", ");
    str.append("terminePeriodo=" + terminePeriodo + ", ");
    str.append("dataRilascio=" + dataRilascio + ", ");
    str.append("approvazioneGenitori=" + approvazioneGenitori + ", ");
    str.append("approvazioneRespo=" + approvazioneRespo + ", ");
    str.append("approvazioneTutorAcc=" + approvazioneTutorAcc + ", ");
    str.append("approvazioneTutorAz=" + approvazioneTutorAz + ", ");
    str.append("ufficioID=" + ufficioID + "}");
    return str.toString();
  }

  @Override
  public boolean equals(Object other) {
    if (other == null)
      return false;
    if (!getClass().getName().equals(other.getClass().getName()))
      return false;
    ProgettoFormativoBean progettoFormativoBean = (ProgettoFormativoBean) other;
    if (id == progettoFormativoBean.getID() && 
        info.equals(progettoFormativoBean.getInfo()) &&
        formazione.equals(progettoFormativoBean.getFormazione()) &&
        modalita.equals(progettoFormativoBean.getModalita()) &&
        responsabile.equals(progettoFormativoBean.getResponsabile()) &&
        inizioPeriodo.equals(progettoFormativoBean.getInizioPeriodo()) &&
        terminePeriodo.equals(progettoFormativoBean.getTerminePeriodo()) &&
        dataRilascio.equals(progettoFormativoBean.getDataRilascio()) &&
        approvazioneGenitori == progettoFormativoBean.isApprovazioneGenitori() &&
        approvazioneRespo == progettoFormativoBean.isApprovazioneRespo() &&
        approvazioneTutorAcc == progettoFormativoBean.isApprovazioneTutorAcc() &&
        approvazioneTutorAz == progettoFormativoBean.isApprovazioneTutorAz() &&
        ufficioID == progettoFormativoBean.getUfficioID())
      return true;
    return false;
  }

  @Override
  public ProgettoFormativoBean clone() throws CloneNotSupportedException {
    ProgettoFormativoBean progettoFormativoBean = null;
    try {
      progettoFormativoBean = (ProgettoFormativoBean) super.clone();
    } catch (CloneNotSupportedException e) {
      Logger.getGlobal().log(Level.SEVERE, e.getMessage());
    }
    return progettoFormativoBean;
  }
}
