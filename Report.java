package olx;

public class Report {
    private String content;
    private String category;
    private Status status;
    private AdminAccount admin;
    Advertisement ad;
    
    
	public Report(String content, String category, Status status, AdminAccount admin, Advertisement ad) {
		this.content = content;
		this.category = category;
		this.status = status;
		this.admin = admin;
		this.ad = ad;
	}
	public Report(String content, String category, Advertisement ad) {
		this.content = content;
		this.category = category;
		this.ad = ad;
	}
	
	
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public Advertisement getAd() {
		return ad;
	}
	public void setAd(Advertisement ad) {
		this.ad = ad;
	}
	
	public void decideReport(boolean decision, AdminAccount judge) {
		this.admin = judge;
		if(decision) {
			this.status = Status.APPROVED;
		}
		else {
			this.status = Status.NOT_APPROVED;
		}
	}

	@Override
	public String toString() {
		return "Report{" +
				"content='" + content + '\'' +
				", category='" + category + '\'' +
				", status=" + status +'}';
	}

	public void viewReport() {

		this.toString();

          OLX.terminal.println("The details of Report of the Advertisement : ");  
            
	  OLX.terminal.println("Content of report: ");
          OLX.terminal.println(content);
          OLX.terminal.println("Category of report: ");
          OLX.terminal.println(category);
          OLX.terminal.println("Status of report: ");
          OLX.terminal.println(status.name());
          OLX.terminal.println("Title of Advertisement: ");
          OLX.terminal.println(ad.tittle); 
          OLX.terminal.println("Type of Advertisement: ");
          OLX.terminal.println(ad.type);       
          OLX.terminal.println("Description of Advertisement  : ");
          OLX.terminal.println(ad.description);

	}
    
}
