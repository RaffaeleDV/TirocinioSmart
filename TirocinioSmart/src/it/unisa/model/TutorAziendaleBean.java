
import java.io.Serializable;

public class TutorAziendaleBean extends AbstractBean implements Serializable, Cloneable{

	private int id;
	private String nome;
	private String password;	

	public TutorAziendaleBean() {
		
	}
	
	public TutorAziendaleBean(int id, String nome, String password, ConvenzioneBean convenzioneBean) {
		this.id = id;
		this.nome = nome;
		this.password = password;
		this.convenzioneBean = convenzioneBean;
	}
	
	public int getId() {
		return id;
	}
	
	public String getNome() {
		return nome;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append(getClass().getName() + "[");
		str.append("id=" + id + ", ");
		str.append("nome=" + nome + ", ");
		str.append("password=" + password + "]");
		return str.toString();
	}
	
	@Override
	public boolean equals(Object other) {
		if(other == null)
			return false;
		if(!getClass().getName().equals(other.getClass().getName()))
			return false;
		TutorAziendaleBean tutorAziendaleBean = (TutorAziendaleBean)other;
		if(id == tutorAziendaleBean.getId() &&
				nome.equals(tutorAziendaleBean.getNome()) &&
				password.equals(tutorAziendaleBean.getPassword()))
			return true;
		return false;
	}
	
	@Override
	public TutorAziendaleBean clone() throws CloneNotSupportedException{
		TutorAziendaleBean tutorAziendaleBean = null;
		try {
			tutorAziendaleBean = (TutorAziendaleBean)super.clone();
		}catch(CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return tutorAziendaleBean;
	}
}
