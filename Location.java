package olx;
import java.util.ArrayList;
import java.util.List;

public class Location {

    String Block;
    String SocietyName;
    String City;
    String State;
    List<Advertisement> Ads;
    List<UserAccount> uAcc;


    public Location( String block, String societyName, String city, String state) {

        this.Block = block;
        this.SocietyName = societyName;
        this.City = city;
        this.State = state;
        this.Ads = new ArrayList<Advertisement>();
        this.uAcc =new ArrayList<UserAccount>();
    }


    public Location( String block, String societyName, String city, String state, List<Advertisement> ads, List<UserAccount> uAcc) {

        this.Block = block;
        this.SocietyName = societyName;
        this.City = city;
        this.State = state;
        this.Ads = ads;
        this.uAcc = uAcc;
    }

    public void addAds(Advertisement a)
    {
        Ads.add(a);
    }

    public void adduAcc(UserAccount a)
    {
        uAcc.add(a);
    }

    @Override
    public boolean equals(Object o) {

        // If the object is compared with itself then return true
        if (o == this) {
            return true;
        }

        /* Check if o is an instance of Complex or not
          "null instanceof [type]" also returns false */
        if (!(o instanceof Location)) {
            return false;
        }

        // typecast o to Complex so that we can compare data members
        Location loc = (Location) o;


        if(loc.Block.equalsIgnoreCase(this.Block) && loc.SocietyName.equalsIgnoreCase(this.SocietyName) && loc.City.equalsIgnoreCase(this.City) && loc.State.equalsIgnoreCase(this.State))
        {
            return true;
        }else return false;

    }

    @Override
    public String toString() {
        return "Location{" +
                "Block='" + this.Block + '\'' +
                ", Society Name='" + this.SocietyName + '\'' +
                ", City=" + this.City + '\'' +
                ", State=" + this.State +'}';
    }
}

