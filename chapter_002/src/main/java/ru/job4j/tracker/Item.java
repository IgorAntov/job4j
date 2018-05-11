package ru.job4j.tracker;

import java.util.Date;

public class Item {

String name;
String description;
String id;
Date createDate;


public Item(String name, String description) {
    this.name = name;
    this.description = description;
}

public String getName() {
    return this.name;
}

public String getDescription() {
    return this.description;
}

public String getId() {
  return this.id;
}

public void setId(String id) {
    this.id = id;
}

}
