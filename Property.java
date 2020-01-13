package olx;

import java.util.List;

import org.slf4j.*;


import org.beryx.textio.TextIO;
import org.beryx.textio.TextIoFactory;

public class Property extends Advertisement{
    int AreaUnits;
    PropertyType propertyType;

    TextIO textIO = TextIoFactory.getTextIO();

    public Property( String tittle, int price, String description, Location loc, UserAccount creator,  int areaUnits, PropertyType propertyType) {
        super(tittle, price, "Property", description,  loc,  creator);
        AreaUnits = areaUnits;
        this.propertyType = propertyType;
    }

    public Property(int views, int shares, int likes, String tittle, int price, String description, Status status, Location loc, List<Report> rep, AdminAccount admin, UserAccount creator,  int areaUnits, PropertyType propertyType) {
        super(views, shares, likes, tittle, price, "Property", description, status, loc, rep, admin, creator);
        AreaUnits = areaUnits;
        this.propertyType = propertyType;
    }
    @Override
    public void viewAdvertisement(){
        super.viewAdvertisement();
        OLX.terminal.println("AreaUnits "+AreaUnits);
        OLX.terminal.println("Property "+propertyType);
    }
    @Override
    public void editAdvertisement() {
        this.viewAdvertisement();



        super.editAdvertisement();

        this.AreaUnits = textIO.newIntInputReader().withDefaultValue(this.AreaUnits).read("AreaUnits");

        this.propertyType= textIO.newEnumInputReader(PropertyType.class).withDefaultValue(this.propertyType).read("Property Type");


    }

    public PropertyType getPropertyType() {
		return propertyType;
	}

	public void setPropertyType(PropertyType propertyType) {
		this.propertyType = propertyType;
	}

	@Override
    public boolean satisfyQuery(QueryBuilder builder) {

        boolean satisfies = true;
        for(Filter<String> filter : builder.getStringFilters()) {
            if(filter.getName().equalsIgnoreCase("PropertyType")) {
                satisfies = satisfies && filter.check(this.propertyType.name());
            }
        }
        return satisfies && super.satisfyQuery(builder);
    }

}
