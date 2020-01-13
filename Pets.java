package olx;

import java.util.List;
import org.slf4j.*;


import org.beryx.textio.TextIO;
import org.beryx.textio.TextIoFactory;

public class Pets extends Advertisement{
    String breed;

    TextIO textIO = TextIoFactory.getTextIO();

    public Pets( String tittle, int price,  String description, Location loc,  UserAccount creator, String breed) {
        super( tittle, price,"Pets", description,  loc, creator);
        this.breed = breed;
    }

    public Pets(int views, int shares, int likes, String tittle, int price,  String description, Status status, Location loc, List<Report> rep, AdminAccount admin, UserAccount creator, String breed) {
        super(views, shares, likes, tittle, price,"Pets", description, status, loc, rep, admin, creator);
        this.breed = breed;
    }
    @Override
    public void viewAdvertisement(){
        super.viewAdvertisement();
        OLX.terminal.println("Breed "+breed);
    }
    @Override
    public void editAdvertisement() {


        OLX.terminal.println("Enter new values or press \"Enter\" to skip");

        super.editAdvertisement();

        this.breed = textIO.newStringInputReader().withDefaultValue(this.breed).read("breed");


    }

    public String getBreed() {
		return breed;
	}

	public void setBreed(String breed) {
		this.breed = breed;
	}

	@Override
    public boolean satisfyQuery(QueryBuilder builder) {

        boolean satisfies = true;
        for(Filter<String> filter : builder.getStringFilters()) {
            if(filter.getName().equalsIgnoreCase("breed")) {
                satisfies = satisfies && filter.check(breed);
            }
        }
        return satisfies && super.satisfyQuery(builder);
    }
}
