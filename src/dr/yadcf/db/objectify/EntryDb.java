package dr.yadcf.db.objectify;


public class EntryDb {

	private String engine;
	private String browser;
	private String platform;
	private String version;
	private String grade;
	
	
	public EntryDb(String engine, String browser, String platform, String version, String grade) {
		super();
		this.engine = engine;
		this.browser = browser;
		this.platform = platform;
		this.version = version;
		this.grade = grade;
	}


	public String getEngine() {
		return engine;
	}


	public void setEngine(String engine) {
		this.engine = engine;
	}


	public String getBrowser() {
		return browser;
	}


	public void setBrowser(String browser) {
		this.browser = browser;
	}


	public String getPlatform() {
		return platform;
	}


	public void setPlatform(String platform) {
		this.platform = platform;
	}


	public String getVersion() {
		return version;
	}


	public void setVersion(String version) {
		this.version = version;
	}


	public String getGrade() {
		return grade;
	}


	public void setGrade(String grade) {
		this.grade = grade;
	}


}


