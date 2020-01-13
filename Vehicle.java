package olx;

import java.util.Date;
import java.util.List;

import org.slf4j.*;


import org.beryx.textio.TextIO;
import org.beryx.textio.TextIoFactory;


public class Vehicle extends Advertisement {
    String make;
    int year;
    Condition condition;
    Date registeration;
    Fuel fuel;
    float KMdriven;

    TextIO textIO = TextIoFactory.getTextIO();

    public Vehicle( String tittle, int price, String description, Location loc, UserAccount creator, String make, int year, Condition condition, Date registeration, Fuel fuel, float KMdriven) {
        super(tittle, price, "Vehicle", description, loc, creator);
        this.make = make;
        this.year = year;
        this.condition = condition;
        this.registeration = registeration;
        this.fuel = fuel;
        this.KMdriven = KMdriven;
    }

    public Vehicle(int views, int shares, int likes, String tittle, int price, String description, Status status, Location loc, List<Report> rep, AdminAccount admin, UserAccount creator, String make, int year, Condition condition, Date registeration, Fuel fuel, float KMdriven) {
        super(views, shares, likes, tittle, price, "Vehicle", description, status, loc, rep, admin, creator);
        this.make = make;
        this.year = year;
        this.condition = condition;
        this.registeration = registeration;
        this.fuel = fuel;
        this.KMdriven = KMdriven;
    }




    public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public Condition getCondition() {
		return condition;
	}

	public void setCondition(Condition condition) {
		this.condition = condition;
	}

	public Date getRegisteration() {
		return registeration;
	}

	public void setRegisteration(Date registeration) {
		this.registeration = registeration;
	}

	public Fuel getFuel() {
		return fuel;
	}

	public void setFuel(Fuel fuel) {
		this.fuel = fuel;
	}

	public float getKMdriven() {
		return KMdriven;
	}

	public void setKMdriven(float kMdriven) {
		KMdriven = kMdriven;
	}

	@Override
    public void viewAdvertisement(){
        super.viewAdvertisement();
        OLX.terminal.println("Make "+make);
        OLX.terminal.println("Condition "+condition);
        OLX.terminal.println("Registeration date "+registeration);
        OLX.terminal.println("Fuel "+fuel);
        OLX.terminal.println("Km Drivern "+KMdriven);

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
            if(filter.getName().equalsIgnoreCase("Fuel")) {
                satisfies = satisfies && filter.check(this.fuel.name());
            }
        }
        for(Filter<Integer> filter : builder.getIntFilters()) {
            if(filter.getName().equalsIgnoreCase("KMDriven")) {
                satisfies = satisfies && filter.check((int)this.KMdriven);
            }
            if(filter.getName().equalsIgnoreCase("Year")) {
                satisfies = satisfies && filter.check(year);
            }
        }
        return satisfies && super.satisfyQuery(builder);
    }

    @Override
    public void editAdvertisement() {


        OLX.terminal.println("Enter new values or press \"Enter\" to skip");

        super.editAdvertisement();

        this.make = textIO.newStringInputReader().withDefaultValue(this.make).read("Make");

        this.condition= textIO.newEnumInputReader(Condition.class).withDefaultValue(this.condition).read("Condition");
        this.fuel=textIO.newEnumInputReader(Fuel.class).withDefaultValue(this.fuel).read("Fuel");
        this.KMdriven=textIO.newFloatInputReader().withDefaultValue(this.KMdriven).read("KM driven");
        this.year=textIO.newIntInputReader().withDefaultValue(this.year).read("Year");

    }
}
