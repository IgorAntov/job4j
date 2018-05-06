package ru.job4j.tracker;

import java.util.Date;

public class Item {

String name;
String description;
String Id;
Date createDate;


public Item(String name, String description){
    this.name = name;
    this.description = description;
}

public String getName(){
    return this.name;
}

public String getDescription(){
    return this.description;
}

public String getId(){
  return this.Id;
}

public void setId(String Id) {
    this.Id = Id;
}

}
