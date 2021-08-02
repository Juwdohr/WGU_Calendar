package Models;

import java.time.*;

public class Report {
    Month month;
    String type;
    long total;

    public Report(Month month, String type, long total) {
        this.month = month;
        this.type = type;
        this.total = total;
    }

    public Month getMonth() {
        return month;
    }

    public void setMonth(Month month) {
        this.month = month;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }
}