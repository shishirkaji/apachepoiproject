package poidemo;

import org.apache.poi.ss.usermodel.Cell;

public class Metadata {
	private String cdCode;
	private String trackTitle;
	private String version;
	private String instruments;
	private String date;
	private Composer composer;

	public Metadata(String cdCode, String trackTitle, String version, String instruments, String date, String fName,
			String lName) {
		this.cdCode = cdCode;
		this.trackTitle = trackTitle;
		this.version = version;
		this.instruments = instruments;
		this.date = date;
		this.composer = new Composer(fName, lName);

	}

	public Metadata() {
		this.composer = new Composer();
	}

	public void printCell() {
		System.out.println();
	}

	public String getCdCode() {
		return cdCode;
	}

	public void setCdCode(String cdCode) {
		this.cdCode = cdCode;
	}

	public String getTrackTitle() {
		return trackTitle;
	}

	public void setTrackTitle(String trackTitle) {
		this.trackTitle = trackTitle;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getInstruments() {
		return instruments;
	}

	public void setInstruments(String instruments) {
		this.instruments = instruments;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Composer getComposer() {
		return composer;
	}

	public void setComposer(Composer composer) {
		this.composer = composer;
	}
@Override
	public String toString() {
		String  string = "Cd Code:"+this.cdCode+" 	Track Title:"+this.trackTitle+
				"  Version : " +this.version+ " 	Instruments:"+this.instruments+
				"     Release date:"+this.date+"   Composer:"+this.composer.toString();
				return string;
		
	}

	public void print() {
		System.out.println(this.cdCode + "  " + this.date + "  " + this.instruments);
	}
}
