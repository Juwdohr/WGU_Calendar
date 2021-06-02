package Models;

import java.sql.Date;

public class Division {
    private int divisionId;
    private String divisionName;
    private Date created;
    private String createdBy;
    private Date lastUpdated;
    private String lastUpdatedBy;
    private int countryId;

    public Division() {
        this.divisionId = -1;
        this.divisionName = null;
        this.created = null;
        this.createdBy = null;
        this.lastUpdated = null;
        this.lastUpdatedBy = null;
        this.countryId = -1;
    }

    public Division(int divisionId, String divisionName, Date created, String createdBy, Date lastUpdated, String lastUpdatedBy, int countryId) {
        this.divisionId = divisionId;
        this.divisionName = divisionName;
        this.created = created;
        this.createdBy = createdBy;
        this.lastUpdated = lastUpdated;
        this.lastUpdatedBy = lastUpdatedBy;
        this.countryId = countryId;
    }

    public int getDivisionId() {
        return divisionId;
    }

    public void setDivisionId(int divisionId) {
        this.divisionId = divisionId;
    }

    public String getDivisionName() {
        return divisionName;
    }

    public void setDivisionName(String divisionName) {
        this.divisionName = divisionName;
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

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }
}
