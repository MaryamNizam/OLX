package olx;

import java.util.List;
import org.slf4j.*;


import org.beryx.textio.TextIO;
import org.beryx.textio.TextIoFactory;

public class Electronics extends Advertisement {
	Condition condition;
	String Make;


	TextIO textIO = TextIoFactory.getTextIO();


	public Electronics( String tittle, int price, String description, Location loc,UserAccount creator, Condition condition, String make) {
		super(tittle, price,"Electronics", description, loc, creator);
		this.condition = condition;
		Make = make;
	}


	public Electronics(int views, int shares, int likes, String tittle, int price, String description, Status status, Location loc, List<Report> rep, AdminAccount admin, UserAccount creator, Condition condition, String make) {
		super(views, shares, likes, tittle, price,"Electronics", description, status, loc, rep, admin, creator);
		this.condition = condition;
		Make = make;
	}

	public Condition getCondition() {
		return condition;
	}


	public void setCondition(Condition condition) {
		this.condition = condition;
	}


	public String getMake() {
		return Make;
	}


	public void setMake(String make) {
		Make = make;
	}


	@Override
	public boolean satisfyQuery(QueryBuilder builder) {

		boolean satisfies = true;
		for(Filter<String> filter : builder.getStringFilters()) {
			if(filter.getName().equalsIgnoreCase("Condition")) {
				satisfies = satisfies && filter.check(this.condition.name());
			}
			if(filter.getName().equalsIgnoreCase("Make")) {
				satisfies = satisfies && filter.check(this.Make);
			}
		}
		return satisfies && super.satisfyQuery(builder);
	}
	@Override
	public void viewAdvertisement(){
		super.viewAdvertisement();
		OLX.terminal.println("Make "+this.Make);
		OLX.terminal.println("Condition "+this.condition);

	}
	@Override
	public void editAdvertisement() {


		OLX.terminal.println("Enter new values or press \"Enter\" to skip");

		super.editAdvertisement();

		this.Make = textIO.newStringInputReader().withDefaultValue(this.Make).read("Make");

		this.condition= textIO.newEnumInputReader(Condition.class).withDefaultValue(this.condition).read("Condition");


	}

}
