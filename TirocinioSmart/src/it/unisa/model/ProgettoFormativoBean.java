package it.unisa.model;

import java.io.Serializable;
import java.sql.Date;

public class ProgettoFormativoBean extends AbstractBean implements Serializable, Cloneable {

  /**
  * 
  */
  private static final long serialVersionUID = -5993865784733111204L;
  private int id;
  private String info;
  private String idsAssicurazioni;
  private String formazione;
  private String modalita;
  private String responsabile;
  private Date inizioPeriodo;
  private Date terminePeriodo;
  private Date dataRilascio;
  private boolean approvazione;
  private boolean approvazioneGenitori;
  private boolean approvazioneRespo;
  private boolean approvazioneTutorAcc;
  private boolean approvazioneTutorAz;
  private int ufficioID;
  private int convenzioneID;
  /*
   * non necessario, si trovano direttamente nell' entita dello studente
   */
  private int tutorAccID;

  public ProgettoFormativoBean() {

  }

  public ProgettoFormativoBean(
      int id, 
      String info,
      String idsAssicurazioni,
      String formazione,
      String modalita,
      String responsabile,
      Date inizioPeriodo,
      Date terminePeriodo,
      Date dataRilascio,
      boolean approvazione,
      boolean approvazioneGenitori,
      boolean approvazioneRespo,
      boolean approvazioneTutorAcc,
      boolean approvazioneTutorAz,
      int ufficioID,
      int convenzioneID,
      int tutorAccID) {
    this.id = id;
    this.info = info;
    this.idsAssicurazioni = idsAssicurazioni;
    this.formazione = formazione;
    this.modalita = modalita;
    this.responsabile = responsabile;
    this.inizioPeriodo = inizioPeriodo;
    this.terminePeriodo = terminePeriodo;
    this.dataRilascio = dataRilascio;
    this.approvazione = approvazione;
    this.approvazioneGenitori = approvazioneGenitori;
    this.approvazioneRespo = approvazioneRespo;
    this.approvazioneTutorAcc = approvazioneTutorAcc;
    this.approvazioneTutorAz = approvazioneTutorAz;
    this.ufficioID = ufficioID;
    this.convenzioneID = convenzioneID;
    this.tutorAccID = tutorAccID;
  }

  public void setID(int id) {
    this.id = id;
  }
  
  public int getID() {
    return id;
  }

  public String getInfo() {
    return info;
  }

  public String getIdsAssicurazioni() {
    return idsAssicurazioni;
  }
  
  public void setIdsAssicurazioni(String idsAssicurazioni) {
    this.idsAssicurazioni = idsAssicurazioni;
  }
  
  public void setFormazione(String formazione) {
    this.formazione = formazione;
  }
  
  public String getFormazione() {
    return formazione;
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
  
  public boolean getApprovazioneGenitori() {
    return approvazioneGenitori;
  }
  
  public void setApprovazioneGenotori(boolean approvazioneGenitori) {
    this.approvazioneGenitori = approvazioneGenitori;
  }
  
  public boolean getApprovazioneRespo() {
    return approvazioneRespo;
  }
  
  public void setApprovazioneRespo(boolean approvazioneRespo) {
    this.approvazioneRespo = approvazioneRespo;
  }
  
  public boolean getApprovazioneTutorAcc() {
    return approvazioneTutorAcc;
  }
  
  public void setApprovazioneTutorAcc(boolean approvazioneTutorAcc) {
    this.approvazioneTutorAcc = approvazioneTutorAcc;
  }
  
  public boolean getApprovazioneTutorAz() {
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

  public int getConvenzioneID() {
    return convenzioneID;
  }

  public void setConvenzioneID(int convenzioneID) {
    this.convenzioneID = convenzioneID;
  }

  public int getTutorAccID() {
    return tutorAccID;
  }

  public void setTutorAccID(int tutorAccID) {
    this.tutorAccID = tutorAccID;
  }

  public void setApprovazioneGenitori(boolean approvazioneGenitori) {
    this.approvazioneGenitori = approvazioneGenitori;
  }

  public boolean getApprovazione() {
    return approvazione;
  }

  public void setInfo(String info) {
    this.info = info;
  }

  public void setApprovazione(boolean approvazione) {
    this.approvazione = approvazione;
  }

  @Override
  public String toString() {
    StringBuilder str = new StringBuilder();
    str.append(getClass().getName() + "[");
    str.append("id=" + id + ", ");
    str.append("info=" + info + ", ");
    str.append("idsAssicurazioni=" + idsAssicurazioni + ", ");
    str.append("formazione=" + formazione + ", ");
    str.append("modalita=" + modalita + ", ");
    str.append("responsabile=" + responsabile + ", ");
    str.append("inizioPeriodo=" + inizioPeriodo + ", ");
    str.append("terminePeriodo=" + terminePeriodo + ", ");
    str.append("dataRilascio=" + dataRilascio + ", ");
    str.append("approvazione=" + approvazione + ", ");
    str.append("approvazioneGenitori=" + approvazioneGenitori + ", ");
    str.append("approvazioneRespo=" + approvazioneRespo + ", ");
    str.append("approvazioneTutorAcc=" + approvazioneTutorAcc + ", ");
    str.append("approvazioneTutorAz=" + approvazioneTutorAz + ", ");
    str.append("ufficioID=" + ufficioID + ", ");
    str.append("convenzioneID=" + convenzioneID + ", ");
    str.append("tutorAccID=" + tutorAccID + "]");
    return str.toString();
  }

  @Override
  public boolean equals(Object other) {
    
    if (other == null)
      return false;
    
    if (!getClass().getName().equals(other.getClass().getName()))
      return false;
    
    ProgettoFormativoBean progettoFormativoBean = (ProgettoFormativoBean) other;
    
    if (
        id == progettoFormativoBean.getID() && 
        info.equals(progettoFormativoBean.getInfo()) &&
        idsAssicurazioni.equals(progettoFormativoBean.getIdsAssicurazioni()) &&
        formazione.equals(progettoFormativoBean.getFormazione()) &&
        modalita.equals(progettoFormativoBean.getModalita()) &&
        responsabile.equals(progettoFormativoBean.getResponsabile()) &&
        inizioPeriodo.equals(progettoFormativoBean.getInizioPeriodo()) &&
        terminePeriodo.equals(progettoFormativoBean.getTerminePeriodo()) &&
        dataRilascio.equals(progettoFormativoBean.getDataRilascio()) &&
        approvazione == progettoFormativoBean.getApprovazione() &&
        approvazioneGenitori == progettoFormativoBean.getApprovazioneGenitori() &&
        approvazioneRespo == progettoFormativoBean.getApprovazioneRespo() &&
        approvazioneTutorAcc == progettoFormativoBean.getApprovazioneTutorAcc() &&
        approvazioneTutorAz == progettoFormativoBean.getApprovazioneTutorAz() &&
        ufficioID == progettoFormativoBean.getUfficioID() &&
        convenzioneID == progettoFormativoBean.getConvenzioneID() &&
        tutorAccID == progettoFormativoBean.getTutorAccID())
      return true;
    
    return false;
  }

  @Override
  public ProgettoFormativoBean clone() throws CloneNotSupportedException {
    ProgettoFormativoBean progettoFormativoBean = null;
    
    try {
      progettoFormativoBean = (ProgettoFormativoBean) super.clone();
    } catch (CloneNotSupportedException e) {
      e.printStackTrace();
    }
    
    return progettoFormativoBean;
  }
}
