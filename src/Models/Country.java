package Models;

import java.sql.Timestamp;

public class Country {
    private int countryId;
    private String countryName;
    private Timestamp created;
    private String createdBy;
    private Timestamp lastUpdated;
    private String lastUpdatedBy;

    public Country() {
        this.countryId = -1;
        this.countryName = null;
        this.created = null;
        this.createdBy = null;
        this.lastUpdated = null;
        this.lastUpdatedBy = null;
    }

    public Country(int countryId, String countryName, Timestamp created, String createdBy, Timestamp lastUpdated, String lastUpdatedBy) {
        this.countryId = countryId;
        this.countryName = countryName;
        this.created = created;
        this.createdBy = createdBy;
        this.lastUpdated = lastUpdated;
        this.lastUpdatedBy = lastUpdatedBy;
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

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Timestamp getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Timestamp lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }
}