package Models;


public class Division {
    private int id;
    private String divisionName;
    private int country;

    public Division(int id, String divisionName, int country) {
        this.id = id;
        this.divisionName = divisionName;
        this.country = country;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDivisionName() {
        return divisionName;
    }

    public void setDivisionName(String divisionName) {
        this.divisionName = divisionName;
    }

    public int getCountryId() {
        return country;
    }

    public void setCountry(int country) {
        this.country = country;
    }
}
