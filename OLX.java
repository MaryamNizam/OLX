package olx;

import java.time.LocalDate;

import java.util.*;
import org.slf4j.*;


import org.beryx.textio.TextIO;
import org.beryx.textio.TextIoFactory;
import org.beryx.textio.TextTerminal;

public class OLX {
    private static TextIO textIO = TextIoFactory.getTextIO();
    public static TextTerminal terminal = textIO.getTextTerminal();
    public static DBConnection DBCON = new DBConnection();
    
    private static OLX instance=null;
    private SessionState sessionState = new SessionInactive();
    private List<Advertisement> advertisements = new ArrayList<Advertisement>();
    private List<Location> locations = new ArrayList<>();
    private AdminAccount administrator;
    private List<Account> accounts = new ArrayList<>();
    public List<Advertisement> getAdvertisements() {
        return advertisements;
    }
    private Account activeAccount = null;
    private OLX(){};
    public static OLX getInstance(){
        if(instance==null)
            instance=new OLX();
        return instance;
    }
    public void setState(SessionState s)
    {
        sessionState=s;
    }

    public void setAdministrator(AdminAccount administrator) {
        this.administrator = administrator;
    }

    public void addLocation(Location loc) {
        locations.add(loc);
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void addAdvertisement(Advertisement ad) {
    	this.getAdminForApproval().addAdForApproval(ad);
        advertisements.add(ad);
    }
    public void addAccount(Account acc) {
        accounts.add(acc);
    }

    public AdminAccount getAdministrator() {
        return administrator;
    }

    public void viewAdvertisements(){
        for(int i=0;i<advertisements.size();i++){
            advertisements.get(i).viewAdvertisement();
        }
    }

    public boolean registerUser() {
            OLX.terminal.println("----------------------Register Now-----------------");
            String name = textIO.newStringInputReader().withMinLength(1).read("Username");
            String password = textIO.newStringInputReader().withMinLength(8).withInputMasking(true).read("Password");
            String email = textIO.newStringInputReader().withMinLength(8).read("Email");
            String phone = textIO.newStringInputReader().withMinLength(11).read("Phone Number");
            String block = textIO.newStringInputReader().withMinLength(1).read("Block");
            String Society = textIO.newStringInputReader().withMinLength(1).read("Society");
            String city = textIO.newStringInputReader().withMinLength(1).read("City");
            String state = textIO.newStringInputReader().withMinLength(1).read("State");
            Location loc=new Location(block,Society,city,state);
            this.addLocation(loc);
            this.addAccount(new UserAccount(name, LocalDate.now(),email,phone,password,this,loc));
            DBCON.insertUser(name, "empty", email, password, phone, block, Society, city, state);
            return sessionState.logInUser();
    }

    public boolean logInUser() { return sessionState.logInUser();
    }
    public void logOutUser() {
        sessionState.logOut();
    }

    public UserAccount getCurrentUserAccount() {
        return sessionState.getCurrentUserAccount();
    }
    public AdminAccount getCurrentAdminAccount() {
        return sessionState.getCurrentAdminAccount();
    }

    public boolean logInAdmin() {
        return sessionState.loginAdmin();
    }

    public void setActiveAccount(Account account) {
        activeAccount = account;
    }
    public Account getActiveAccount() {
    	return activeAccount;
    }

    public void deleteAd(Advertisement ad) {
        advertisements.remove(ad);
    }

    public AdminAccount getAdminForApproval() {
    	return this.getAdministrator();  //duplicate functions. Marked for remova in next update.
    }

    public void search(String searchKeyword) {
		String category = textIO.newEnumInputReader(Categories.class).read("Please choose the category").name();
		try {
			List<Advertisement> adsReturned = OLX.DBCON.Search(searchKeyword, category);
				//QUERY DB TO GET RESULTS HERE.
				QueryBuilder builder = new QueryBuilder(adsReturned);
				OLX.terminal.println("Now enter filters. If you do not want to specify a filter, leave it empty");
				int priceUpBound = textIO.newIntInputReader()
						.withDefaultValue(Integer.MAX_VALUE)
						.read("Price upper bound");
				int priceLowerBound = textIO.newIntInputReader()
						.withMaxVal(Integer.MAX_VALUE - 1)
						.withDefaultValue(0)
						.read("Price Lower Bound");
				
				if(priceUpBound != Integer.MAX_VALUE && priceLowerBound != 0) {
					builder.addIntFilter(new RangeFilter<Integer>("Price", priceLowerBound, priceUpBound));
				}
				
				AdvertisementViewer viewer = new AdvertisementViewer(builder.getResults());
				viewer.display();

		}
		catch(Exception e) {
			System.out.println(e);
		}
    }
    /*
    static Condition userInputCondition(){
        OLX.terminal.println("Enter 1 for New or 2 for used");
        Scanner input=new Scanner(System.in);
        int i=input.nextInt();
        if(i==1){return Condition.NEW;};
        return Condition.USED;
    }*/


}
