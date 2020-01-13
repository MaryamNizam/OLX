package olx;

import java.util.*;
import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;

import org.slf4j.*;
import org.beryx.textio.TextIO;
import org.beryx.textio.TextIoFactory;
public class AdminAccount extends Account {
	//do no lower to interface here. We need indexed access.
	ArrayList<Advertisement> adsWaitingList;
	ArrayList<Report> reportsWaitingList;

	TextIO textIO = TextIoFactory.getTextIO();

	private boolean hasWaitingAds = false;
	public AdminAccount(String name, LocalDate joinDate, String email, String phoneNumber, String password) {
		super(name, joinDate, email, phoneNumber, password);
		adsWaitingList = new ArrayList<>();
		reportsWaitingList = new ArrayList<>();
	}
	
	
	public AdminAccount(String string, Date date, String string2, String string3, String string4) {
		super(string, date.toInstant()
			      .atZone(ZoneId.systemDefault())
			      .toLocalDate(), string2, string3, string4);
		adsWaitingList = new ArrayList<>();
		reportsWaitingList = new ArrayList<>();
	}


	public boolean hasWaitingAds() {
		return hasWaitingAds;
	}
	public void setHasWaitingAds(boolean hasWaitingAds) {
		this.hasWaitingAds = hasWaitingAds;
	}
	
	public void viewAdsForApproval() {
		this.hasWaitingAds = false;
		if(adsWaitingList.size() == 0) {
			OLX.terminal.println("No ads to view");
			return;
		}
		for(int i = 0; i < adsWaitingList.size(); i++) {
			OLX.terminal.println(i + ": " + adsWaitingList.get(i).getTittle());
		}
		int userInput = textIO.newIntInputReader().withMinVal(-1).withMaxVal( adsWaitingList.size() - 1).read("Ad no[-1 to go back]"); //get this from user usint TextIO with suitable up limit decided by size of list.
		if(userInput == -1) {
			return;
		}
		decideApproval(adsWaitingList.get(userInput));
	}
	
	public void viewReportsForApproval() {
		if(this.reportsWaitingList.size() == 0) {
			OLX.terminal.println("No reports to view");
			return;
		}
		for(int i = 0; i < reportsWaitingList.size(); i++) {
			OLX.terminal.println(i +": " + reportsWaitingList.get(i).getAd().getTittle());
		}

		int userInput =textIO.newIntInputReader().withMinVal(-1).withMaxVal( reportsWaitingList.size() - 1).read("Report no[-1 to go back]"); //get this from user usint TextIO with suitable up limit decided by size of list.
		if(userInput == -1) {
			return;
		}
		decideReport(reportsWaitingList.get(userInput));
		
	}
	
	public void decideApproval(Advertisement ad) {
		ad.viewAdvertisement();
		boolean approval =  textIO.newBooleanInputReader().read("Approval");
		OLX.terminal.printf("Ad has been %s", approval ? "Approved" : "Disapproved");
		ad.approveDisapprove(approval, this);
		adsWaitingList.remove(ad);
		this.viewAdsForApproval();
	}
	
	public void decideReport(Report report) {
		report.viewReport();
		boolean decision = textIO.newBooleanInputReader().read("Decision");
		OLX.terminal.printf("Report has been %s", decision ? "Approved" : "Disapproved");
		report.decideReport(decision, this);
		reportsWaitingList.remove(report);
		this.viewReportsForApproval();
	}
	
	
	public void addReportForApproval(Report report) {
		this.reportsWaitingList.add(report);
	}
	
	public void addAdForApproval(Advertisement ad) {
		this.hasWaitingAds = true;
		this.adsWaitingList.add(ad);
	}
	
	

}
