package olx;
import java.util.*;

import org.slf4j.*;


import org.beryx.textio.TextIO;
import org.beryx.textio.TextIoFactory;


public class Advertisement {
    int views;
    int shares;
    int likes;
    String tittle;
    int price;
    String type;
    String description;
    Status status;
    Location loc;
    List<Report> reports;
    AdminAccount admin;
    UserAccount creator;

    TextIO textIO = TextIoFactory.getTextIO();

    public Advertisement( String tittle, int price, String type, String description, Location loc, UserAccount creator) {

        this.tittle = tittle;
        this.price = price;
        this.type = type;
        this.description = description;
        this.status =Status.NOT_APPROVED;
        this.loc = loc;
        this.creator = creator;
        this.admin=OLX.getInstance().getAdministrator();
        reports= new ArrayList<Report>();
    }

    public Advertisement(int views, int shares, int likes, String tittle, int price, String type, String description, Status status, Location loc, List<Report> reports, AdminAccount admin, UserAccount creator) {
        this.views = views;
        this.shares = shares;
        this.likes = likes;
        this.tittle = tittle;
        this.price = price;
        this.type = type;
        this.description = description;
        this.status = status;
        this.loc = loc;
        this.admin = admin;
        this.creator = creator;
        this.reports = reports;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void viewAdvertisement() {
    	OLX.terminal.println("=========VIEWING ADVERTISEMENT DETAILS=========");
        OLX.terminal.println("Title "+tittle);
        OLX.terminal.println("Price "+price);
        OLX.terminal.println("Type "+type);
        OLX.terminal.println("Description "+description);
        OLX.terminal.println("Views "+views);
        OLX.terminal.println("Likes "+likes);
    }

    class Sortbyprice implements Comparator<Advertisement>
    {
        // Used for sorting in ascending order of 
        // price 
        public int compare(Advertisement a, Advertisement b)
        {
            return a.price - b.price;
        }
    }


    public String share() {
    	return "OLX/" + this.creator.getName() + "/" + this.tittle;
    }
    
    public void editAdvertisement() {
        OLX.terminal.println("Enter new values or press \"Enter\" to skip");

        String t = textIO.newStringInputReader().withDefaultValue(this.tittle).read("Title");
        this.setTittle(t);

        int p= textIO.newIntInputReader().withDefaultValue(this.price).read("Price");
        this.setPrice(p);

        String descr = textIO.newStringInputReader().withDefaultValue(this.description).read("Description");
        this.setDescription(descr);

    }


    public void approveDisapprove(boolean decision, AdminAccount judge) {
        this.admin = judge;
        if(decision) {
            this.status = Status.APPROVED;
        }
        else {
            this.status = Status.NOT_APPROVED;
        }
    }
    public void likeAdvertisement() {
        likes++;
    }

    public UserAccount viewSeller() {
        return creator;
    }

    public boolean satisfyQuery(QueryBuilder builder) {
        for(Filter<Integer> filter : builder.getIntFilters()) {
            if(filter.getName().equalsIgnoreCase("Price")) {
                return filter.check(price);
            }
        }
        //in case there is no fitler of importance.
        return true;
    }

    public Report createReport(){
        OLX olx=OLX.getInstance();
        String c = textIO.newStringInputReader().read("Content");
        String cat = textIO.newStringInputReader().read("Category");

        AdminAccount admin = olx.getAdminForApproval();
        Report r=new Report(c,cat, Status.WAITING, admin,this);
        this.reports.add(r);
        admin.addReportForApproval(r);
        
        return r;

    }


    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public int getShares() {
        return shares;
    }

    public void setShares(int shares) {
        this.shares = shares;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Location getLoc() {
        return loc;
    }

    public void setLoc(Location loc) {
        this.loc = loc;
    }

    public List<Report> getReports() {
        return reports;
    }

    public void setReports(List<Report> reports) {
        this.reports = reports;
    }

    public AdminAccount getAdmin() {
        return admin;
    }

    public void setAdmin(AdminAccount admin) {
        this.admin = admin;
    }

    public UserAccount getCreator() {
        return creator;
    }

    public void setCreator(UserAccount creator) {
        this.creator = creator;
    }

    public TextIO getTextIO() {
        return textIO;
    }

    public void setTextIO(TextIO textIO) {
        this.textIO = textIO;
    }

    public String getTittle() {
        return tittle;
    }

    public int getPrice() {
        return price;
    }

    public String getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }


    public Report reportAdvertisement() {
    	return this.createReport(); //deprecate this function in next update.
    }

}