package ru.job4j.servlets.http.cinema;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public class Place {
    private String place;
    private String status;
    private String desc;

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
