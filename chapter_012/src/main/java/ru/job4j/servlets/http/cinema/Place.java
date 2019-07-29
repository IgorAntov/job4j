package ru.job4j.servlets.http.cinema;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Place place1 = (Place) o;
        return Objects.equals(place, place1.place) &&
                Objects.equals(status, place1.status) &&
                Objects.equals(desc, place1.desc);
    }

    @Override
    public int hashCode() {

        return Objects.hash(place, status, desc);
    }
}
