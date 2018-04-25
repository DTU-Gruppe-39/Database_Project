package dto01917;

public class PersonerDTO {
	String oprNavn;                
	/** Operatoer-initialer min. 2 max. 3 karakterer */
	String ini;                 
	/** Operatoer cpr-nr 10 karakterer */
	String cpr;  
	
	public PersonerDTO(String oprNavn, String ini, String cpr)
	{
		this.oprNavn = oprNavn;
		this.ini = ini;
		this.cpr = cpr;
	}

	public String getOprNavn() {
		return oprNavn;
	}

	public void setOprNavn(String oprNavn) {
		this.oprNavn = oprNavn;
	}

	public String getIni() {
		return ini;
	}

	public void setIni(String ini) {
		this.ini = ini;
	}

	public String getCpr() {
		return cpr;
	}

	public void setCpr(String cpr) {
		this.cpr = cpr;
	}
	
	public String toString() { return oprNavn + "\t" + ini + "\t" + cpr + "\t"; }
}
