package ru.job4j.lsp.warehouse;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */

public class Food {
    private String name;
    private Date expiredDate;
    private Date createDate;
    private Double price;
    private int discount = 0;
    private String dateFormat = "yyyy-MM-dd hh:mm:ss";

    public Food(String name, String expiredDate, Double price) {
        this.name = name;
        this.expiredDate = parseDate(expiredDate);
        this.createDate = new Date();
        this.price = price;
    }

    /**
     * Method parses Date as String to Date with dateFormat.
     * @param dateString date as a String.
     * @return Date.
     */
    private Date parseDate(String dateString) {
        Date date = null;
        try {
            date = new Date((new SimpleDateFormat(this.dateFormat).parse(dateString)).getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public Date getExpiredDate() {
        return this.expiredDate;
    }

    public Date getCreateDate() {
        return this.createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = parseDate(createDate);
    }

    public int getDisscount() {
        return this.discount;
    }

    public void setDisscount(int disscount) {
        this.discount = disscount;
    }
}
