package olx;

import java.util.List;

import org.slf4j.*;


import org.beryx.textio.TextIO;
import org.beryx.textio.TextIoFactory;


public class Mobile extends Advertisement {
    String make;
    Condition condition;

    TextIO textIO = TextIoFactory.getTextIO();

    public Mobile( String tittle, int price, String description, Location loc, UserAccount creator, String make, Condition condition) {
        super(tittle, price,"Mobile", description, loc,creator);
        this.make = make;
        this.condition = condition;
    }
    public Mobile(int views, int shares, int likes, String tittle, int price, String description, Status status, Location loc, List<Report> rep, AdminAccount admin, UserAccount creator, String make, Condition condition) {
        super(views, shares, likes, tittle, price,"Mobile", description, status, loc, rep, admin, creator);
        this.make = make;
        this.condition = condition;
    }
    @Override
    public void viewAdvertisement(){
        super.viewAdvertisement();
        OLX.terminal.println("Make "+make);
        OLX.terminal.println("Condition "+condition);
    }
    @Override
    public void editAdvertisement() {


        OLX.terminal.println("Enter new values or press \"Enter\" to skip");

        super.editAdvertisement();

        this.make = textIO.newStringInputReader().withDefaultValue(this.make).read("Make");

        this.condition= textIO.newEnumInputReader(Condition.class).withDefaultValue(this.condition).read("Condition");


    }

    public String getMake() {
		return make;
	}
	public void setMake(String make) {
		this.make = make;
	}
	public Condition getCondition() {
		return condition;
	}
	public void setCondition(Condition condition) {
		this.condition = condition;
	}
	@Override
    public boolean satisfyQuery(QueryBuilder builder) {

        boolean satisfies = true;
        for(Filter<String> filter : builder.getStringFilters()) {
            if(filter.getName().equalsIgnoreCase("Condition")) {
                satisfies = satisfies && filter.check(this.condition.name());
            }
            if(filter.getName().equalsIgnoreCase("Make")) {
                satisfies = satisfies && filter.check(this.make);
            }
        }
        return satisfies && super.satisfyQuery(builder);
    }
}
