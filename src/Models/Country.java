package Models;

import java.sql.Date;

public class Country {
    private int countryId;
    private String countryName;
    private Date created;
    private String createdBy;
    private Date lastUpdated;
    private String lastUpdatedBy;

    public Country() {
        this.countryId = -1;
        this.countryName = null;
        this.created = null;
        this.createdBy = null;
        this.lastUpdated = null;
        this.lastUpdatedBy = null;
    }

    public Country(int countryId, String countryName, Date created, String createdBy, Date lastUpdated, String lastUpdatedBy) {
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

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }
}