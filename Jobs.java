package olx;

import java.util.List;
import org.slf4j.*;


import org.beryx.textio.TextIO;
import org.beryx.textio.TextIoFactory;

public class Jobs extends Advertisement {
    int noOfPositions;
    String companyName;
    String Description;

    TextIO textIO = TextIoFactory.getTextIO();

    public Jobs(String tittle, int price,  String description,Location loc, UserAccount creator, int noOfPositions, String companyName, String description1) {
        super( tittle, price, "Job", description,  loc, creator);
        this.noOfPositions = noOfPositions;
        this.companyName = companyName;
        Description = description1;
    }
    public Jobs(int views, int shares, int likes, String tittle, int price,  String description, Status status, Location loc, List<Report> rep, AdminAccount admin, UserAccount creator, int noOfPositions, String companyName, String description1) {
        super(views, shares, likes, tittle, price, "Job", description, status, loc, rep, admin, creator);
        this.noOfPositions = noOfPositions;
        this.companyName = companyName;
        Description = description1;
    }

    @Override
    public void viewAdvertisement(){
        super.viewAdvertisement();
        OLX.terminal.println("Number of Positions "+noOfPositions);
        OLX.terminal.println("Company Name "+companyName);
    }
    
    public int getNoOfPositions() {
		return noOfPositions;
	}
	public void setNoOfPositions(int noOfPositions) {
		this.noOfPositions = noOfPositions;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	@Override
    public void editAdvertisement() {


        OLX.terminal.println("Enter new values or press \"Enter\" to skip");

        super.editAdvertisement();

        this.companyName = textIO.newStringInputReader().withDefaultValue(this.companyName).read("Company Name");

        this.noOfPositions= textIO.newIntInputReader().withDefaultValue(this.noOfPositions).read("No of Positions");


    }
}
