package olx;

import java.util.List;

import org.slf4j.*;


import org.beryx.textio.TextIO;
import org.beryx.textio.TextIoFactory;


public class House extends Property {
    int NoOfBedrooms;
    int NoOfBathrooms;

    public int getNoOfBedrooms() {
		return NoOfBedrooms;
	}
	public void setNoOfBedrooms(int noOfBedrooms) {
		NoOfBedrooms = noOfBedrooms;
	}
	public int getNoOfBathrooms() {
		return NoOfBathrooms;
	}
	public void setNoOfBathrooms(int noOfBathrooms) {
		NoOfBathrooms = noOfBathrooms;
	}
	TextIO textIO = TextIoFactory.getTextIO();

    public House(String tittle, int price, String description, Location loc, UserAccount creator, int areaUnits, PropertyType propertyType, int noOfBedrooms, int noOfBathrooms) {
        super( tittle, price, description, loc,creator, areaUnits, propertyType);
        NoOfBedrooms = noOfBedrooms;
        NoOfBathrooms = noOfBathrooms;
    }
    public House(int views, int shares, int likes, String tittle, int price,  String description, Status status, Location loc, List<Report> rep, AdminAccount admin, UserAccount creator, int areaUnits, PropertyType propertyType, int noOfBedrooms, int noOfBathrooms) {
        super(views, shares, likes, tittle, price, description, status, loc, rep, admin,creator, areaUnits, propertyType);
        NoOfBedrooms = noOfBedrooms;
        NoOfBathrooms = noOfBathrooms;
    }

    @Override
    public void viewAdvertisement(){
        super.viewAdvertisement();
        OLX.terminal.println("Bedrooms "+NoOfBedrooms);
        OLX.terminal.println("Bathrooms "+NoOfBathrooms);

    }
    @Override
    public void editAdvertisement() {
        this.viewAdvertisement();

        super.editAdvertisement();

        this.NoOfBathrooms = textIO.newIntInputReader().withDefaultValue(this.NoOfBathrooms).read("No Of Bathrooms");

        this.NoOfBedrooms= textIO.newIntInputReader().withDefaultValue(this.NoOfBedrooms).read("No Of Bedrooms");


    }
}



