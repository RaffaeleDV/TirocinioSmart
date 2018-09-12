
import java.io.Serializable;

public class ProgettoFormativoBean extends AbstractBean implements Serializable, Cloneable{

	private int id;
	private String info;
	private boolean approvazione;
	
	public ProgettoFormativoBean() {
		
	}
	
	public ProgettoFormativoBean(int id, String info, boolean approvazione) {
		this.id = id;
		this.info = info;
		this.approvazione = approvazione;
	}
	
	public int getId() {
		return id;
	}
	
	public String getInfo() {
		return info;
	}
	
	public boolean getApprovazione() {
		return approvazione;
	}
	
	public void setId(int id) {
		this.id = id;
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
		str.append(getClass().getName()+ "[");
		str.append("id="+id+", ");
		str.append("info="+info+", ");
		str.append("approvazione="+approvazione+ "]");
		return str.toString();
	}
	
	@Override
	public boolean equals(Object other) {
		if(other == null)
			return false;
		if(!getClass().getName().equals(other.getClass().getName()))
			return false;
		ProgettoFormativoBean progettoFormativoBean = (ProgettoFormativoBean)other;
		if(id == progettoFormativoBean.getId() && info.equals(progettoFormativoBean.getInfo()) && approvazione == progettoFormativoBean.getApprovazione())
			return true;
		return false;
	}
	
	@Override
	public ProgettoFormativoBean clone() throws CloneNotSupportedException{
		ProgettoFormativoBean progettoFormativoBean = null;
		try {
			progettoFormativoBean = (ProgettoFormativoBean)super.clone();
		}catch(CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return progettoFormativoBean;
	}
}
