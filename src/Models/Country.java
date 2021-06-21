package Models;

import java.sql.Timestamp;

public class Country {
    private int countryId;
    private String countryName;

    public Country() {
        this.countryId = -1;
        this.countryName = null;
    }

    public Country(int countryId, String countryName, Timestamp created, String createdBy, Timestamp lastUpdated, String lastUpdatedBy) {
        this.countryId = countryId;
        this.countryName = countryName;
    }

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }
}