package olx;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.time.LocalDate;
import java.time.ZoneId;

import org.slf4j.*;


import org.beryx.textio.TextIO;
import org.beryx.textio.TextIoFactory;

public class UserAccount extends Account {

    TextIO textIO = TextIoFactory.getTextIO();

    Boolean hasUnreadMessages;
    Boolean hasUnopenedUpdates;
    Location loc;
    List<Advertisement> published;
    List<Advertisement> likedAds;
    List<Report> reportedAds;
    List<UserAccount> Followers;
    List<Chat> ChatsInitiated;

    public UserAccount(String name, LocalDate joinDate, String email, String phoneNumber, String password, OLX app, Location loc) {
        super(name, joinDate, email, phoneNumber, password);
        this.hasUnreadMessages = false;
        this.hasUnopenedUpdates = false;
        this.loc = loc;
        this.published = new ArrayList<Advertisement>();
        this.likedAds = new ArrayList<Advertisement>();
        reportedAds = new ArrayList<Report>();
        Followers = new ArrayList<UserAccount>();
        ChatsInitiated = new ArrayList<Chat>();
    }

    public UserAccount(String name, LocalDate joinDate, String email, String phoneNumber, String password, OLX app, boolean unReadMessages, boolean hasUnopenedUpdates, Location loc, List<Advertisement> published, List<Advertisement> likedAds, List<Report> reported, List<UserAccount> followers, List<Chat> chatsInitiated) {
        super(name, joinDate, email, phoneNumber, password);
        this.hasUnreadMessages = unReadMessages;
        this.hasUnopenedUpdates = hasUnopenedUpdates;
        this.loc = loc;
        this.published = published;
        this.likedAds = likedAds;
        reportedAds = reported;
        Followers = followers;
        ChatsInitiated = new ArrayList<>();
    }

	public UserAccount(String name, Date date, String email, String number, String password, Location loc2) {

		super(name, date.toInstant()
			      .atZone(ZoneId.systemDefault())
			      .toLocalDate(), email, number, password);
		
        this.published = new ArrayList<Advertisement>();
        this.likedAds = new ArrayList<Advertisement>();
        reportedAds = new ArrayList<Report>();
        Followers = new ArrayList<UserAccount>();
        ChatsInitiated = new ArrayList<Chat>();
		this.loc = loc2;
	}

	public Advertisement postAdvertisement(Advertisement ad) {
        return null;
        //have to implement this.
    }

    public void likeAdvertisement(Advertisement ad) {
    	OLX.DBCON.addLikes(this.Email, ad);
        likedAds.add(ad);
    }

    public void reportAdvertisement(Report report) {
        reportedAds.add(report);
    }

    public void deleteAdvertisement(Advertisement ad) {
        this.published.remove(ad);
    }

    public void sendMessage(UserAccount account, String message) {
        Chat exists = null;
        for (Chat chat : ChatsInitiated) {
            for (int i = 0; i < chat.getParticipants().length; i++) {
                if (account.equals(chat.getParticipants()[i])) {
                    exists = chat;
                }
            }
            if (exists != null) {
                break;
            }
        }

        if (exists == null) {
            exists = new Chat(this, account);
        }
        exists.addMessage(message, this);
    }

    public void followUser(UserAccount user) {
        user.addFollower(this);
    }

    public void addFollower(UserAccount user) {
    	OLX.DBCON.addFollower(user.Email, this.Email);
        Followers.add(user);
    }

    public void notifyUserOfMessage() {
        this.hasUnreadMessages = true;
    }


    public void notiftyUserOfFollowerUpdate() {
        this.hasUnopenedUpdates = true;
    }

    public void notifyFollowers() {
        for (UserAccount follower : Followers) {
            follower.notiftyUserOfFollowerUpdate();
        }
    }

    public Chat getChatWithUser(UserAccount user) {
    	Chat result = null;
    	
    	for(Chat chat : this.ChatsInitiated) {
    		if(chat.isParticipant(user)) {
    			result = chat;
    			break;
    		}
    	}
    	return result;
    }
    public void viewChats() {
    	this.hasUnreadMessages = false;
    	//implement view here.
        int i=0;
        int getThisPt=0;
        Message m1;
        for(Chat getChat:ChatsInitiated)
        {
          if(getChat.getFirstParticipant().Name==this.Name)
          {
             getThisPt=1;
          }
          else if(getChat.getSecoundParticipant().Name==this.Name)
          {
              getThisPt=2;
          }
          if(getThisPt==1)
          {
              OLX.terminal.println(i + "- " + getChat.getSecoundParticipant().Name);
          }
          else if(getThisPt==2)
          {
               OLX.terminal.println(i + "- " + getChat.getFirstParticipant().Name);
          
          }
          OLX.terminal.println(getChat.getLatestMessage());
          OLX.terminal.println(getChat.getTimeOfLatestMessage());
          i++;
        }
      
    }

    public void viewFollowerAds() {
        this.hasUnopenedUpdates = false;
        List<String> followeeEmails = OLX.DBCON.getFolloweeEmails(this.Email);
        for(String email : followeeEmails) {
        	try {
        		OLX.DBCON.getUserDetails(email).printPublishedAds();
        	}
        	catch(Exception e) {
        		System.out.println(e);
        	}
        	
        }
        
    }

    public void printPublishedAds(){
    	for(Advertisement ad : published) {
    		ad.viewAdvertisement();
    	}
    }

    public void viewUserProfile() {
    	OLX.terminal.println("=======USER PROFILE========");
    	OLX.terminal.printf("Name: %s\nEmail: %s\nPhone:%s\n", this.Name, this.Email, this.PhoneNumber);
    	
    }
    public void createAdvertisement() {

        String title = textIO.newStringInputReader().withMinLength(1).read("Title");
        int price = textIO.newIntInputReader().read("Price");

        String description = textIO.newStringInputReader().withMinLength(1).read("Description");

        OLX.terminal.println("Press 1 for Electronics");
        OLX.terminal.println("Press 2 for House");
        OLX.terminal.println("Press 3 for Jobs");
        OLX.terminal.println("Press 4 for Mobile");
        OLX.terminal.println("Press 5 for Pets");
        OLX.terminal.println("Press 6 for Property");
        OLX.terminal.println("Press 7 for Vehicle");


        //taking user input


        int inserted = textIO.newIntInputReader().withMaxVal(7).read("Enter Your Option");
        switch (inserted) {
            case 1:
                Electronics e;
                String make = textIO.newStringInputReader().withMinLength(1).read("Make");
                Condition cond = textIO.newEnumInputReader(Condition.class).read("Condition");
                e = new Electronics(title, price, description, this.loc, this, cond, make);
                OLX.getInstance().addAdvertisement(e);
                this.published.add(e);
                OLX.DBCON.insertAdvertisementElectronics(e.getCreator().getEmail(),
                		e.getTittle(), e.getPrice(), e.getDescription(), e.getStatus().name(), Categories.Electronic.name(), e.getCondition().name(), e.getMake());
                break;
            case 2:
                int b = textIO.newIntInputReader().read("No of Bedrooms");
                int b1 = textIO.newIntInputReader().read("No of Bathrooms");
                int a = textIO.newIntInputReader().read("Area Units");
                PropertyType Prop = textIO.newEnumInputReader(PropertyType.class).read("Property Type");
                House h;

                h = new House(title, price, description, this.loc, this, a,Prop, b, b1);
                OLX.getInstance().addAdvertisement(h);
                this.published.add(h);
                break;

            case 3:
                Jobs j;
                int n = textIO.newIntInputReader().read("No of Positions");
                String cn = textIO.newStringInputReader().withMinLength(1).read("Company Name");
                String d = textIO.newStringInputReader().withMinLength(1).read("Description");
                j = new Jobs(title, price, description, this.loc, this, n, cn, d);
                OLX.getInstance().addAdvertisement(j);
                this.published.add(j);
                OLX.DBCON.insertAdvertisementJobs(j.getCreator().getEmail(), j.getTittle(),
                		j.getPrice(), j.getDescription(), j.getStatus().name(), Categories.Job.name(), j.getCompanyName(), j.getDescription());
                break;

            case 4:
                Mobile m;
                String make1 = textIO.newStringInputReader().withMinLength(1).read("Make");
                Condition cond1 = textIO.newEnumInputReader(Condition.class).read("Condition");

                m = new Mobile(title, price, description, this.loc, this, make1, cond1);
                OLX.getInstance().addAdvertisement(m);
                this.published.add(m);
                OLX.DBCON.insertAdvertisementMobile(m.getCreator().getEmail(), m.getTittle(), m.getPrice(), m.getDescription(),
                		m.getStatus().name(), Categories.Mobile.name(), m.getCondition().name(), m.getMake());
                break;

            case 5:
                Pets p;
                String breed = textIO.newStringInputReader().withMinLength(1).read("Breed");
                p = new Pets(title, price, description, this.loc, this, breed);
                OLX.getInstance().addAdvertisement(p);
                this.published.add(p);
                OLX.DBCON.insertAdvertisementPets(p.getCreator().getEmail(),
                		p.getTittle(), p.getPrice(), p.getDescription(), p.getStatus().name(), 
                		Categories.Pet.name(), p.getBreed());
                break;
            case 6:
                Property pr;
                int a1 = textIO.newIntInputReader().read("Area Units");
                PropertyType Prop1= textIO.newEnumInputReader(PropertyType.class).read("Property Type");

                pr = new Property(title, price, description, this.loc, this, a1, Prop1);
                OLX.getInstance().addAdvertisement(pr);
                this.published.add(pr);
                OLX.DBCON.insertAdvertisementProperty(pr.getCreator().getEmail(), pr.getTittle(),
                		pr.getPrice(), pr.getDescription(), pr.getStatus().name(), 
                		Categories.Property.name(), pr.getPropertyType().name());
                break;
            case 7:
                Vehicle v;

                String make2 = textIO.newStringInputReader().withMinLength(1).read("Make");
                Condition cond2 = textIO.newEnumInputReader(Condition.class).read("Condition");
                int y = textIO.newIntInputReader().read("Year");
                Fuel f = textIO.newEnumInputReader(Fuel.class).read("Fuel");
                float km = textIO.newFloatInputReader().read("KiloMeter Driven");
                Date d1 = new Date(12 - 13 - 2010);


                v = new Vehicle(title, price, description, this.loc, this, make2, y, cond2, d1, f, km);
                OLX.getInstance().addAdvertisement(v);
                this.published.add(v);
                OLX.DBCON.insertAdvertisementVehicle(v.getCreator().getEmail(),
                		v.getTittle(), v.getPrice(), v.getDescription(), v.getStatus().name(), 
                		Categories.Vehicle.name(), v.getRegisteration().getYear(),
                		v.getCondition().name(), v.getMake(), (int)v.getKMdriven(), (float)6.9);
                break;
            default:
                break;

        }

    }

    public void viewProfile(){

        OLX.terminal.println("Name : " + this.Name);
        OLX.terminal.println("Join Date : " + this.JoinDate);
        OLX.terminal.println("Email : " + this.Email);
        OLX.terminal.println("Phone Number : " + this.PhoneNumber);
        OLX.terminal.println("Password : " + this.Password);
        OLX.terminal.println("Location : " + this.loc);
        OLX.terminal.println("Has Unread Messages : " + this.hasUnreadMessages);
        OLX.terminal.println("Has Unopened Updates : " + this.hasUnopenedUpdates);


    }

	public void publishedMenu() {
		OLX.terminal.println("================Logged In User ads viewing menu================");
		if(this.published.size() == 0) {
			OLX.terminal.println("You do not have any ads");
			return;
		}
		
		for(int i = 0; i < this.published.size(); i++) {
			OLX.terminal.printf("No: %d Title: %s\n", i, this.published.get(i).getTittle());
		}
		
		OLX.terminal.println("Press -1 to go back");
		OLX.terminal.println("Press 1 to edit ads");
		OLX.terminal.println("Press 2 to delete ads");
		
		int userInput = textIO.newIntInputReader().withMinVal(-1).withMaxVal(2).withDefaultValue(-1).read("Input");
		
		if(userInput == 1) {
			int adNumber = textIO.newIntInputReader().withMinVal(0).withMaxVal(this.published.size() - 1).read("Ad Number To Delete");
			this.published.get(adNumber).editAdvertisement();
			OLX.terminal.println("Ad has been edited successfully");
			this.publishedMenu();
		}
		else if(userInput == 2) {
			int adNumber = textIO.newIntInputReader().withMinVal(0).withMaxVal(this.published.size() - 1).read("Ad Number To Delete");
			this.published.remove(adNumber);
			OLX.terminal.println("Ad has been deleted successfully");
			this.publishedMenu();
		}
		
	}

	public void viewLikedAds() {
		for(Advertisement ad : likedAds) {
			ad.viewAdvertisement();
		}
		
	}
}