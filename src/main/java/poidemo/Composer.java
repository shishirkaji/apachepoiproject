package poidemo;

public class Composer {
	private String fName;
	private String lName;
	
	public Composer(String fName, String lName) {
		this.fName= fName;
		this.lName = lName;
		
	}
	public Composer () {
		
	}
	@Override
	public String toString() {
		return this.fName+" "+this.lName;
	}
	
	public String getfName() {
		return fName;
	}
	public void setfName(String fName) {
		this.fName = fName;
	}
	public String getlName() {
		return lName;
	}
	public void setlName(String lName) {
		this.lName = lName;
	}

}
