package com.example.gameofthrones;

public class Character
{
    public String name;
    public String imageurl;
    public String houseName;
    public String houseImageUrl;
    public String houseId;
    public String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public String getHouseName() {
        return houseName;
    }

    public void setHouseName(String houseName) {
        this.houseName = houseName;
    }

    public String getHouseImageUrl() {
        return houseImageUrl;
    }

    public void setHouseImageUrl(String houseImageUrl) {
        this.houseImageUrl = houseImageUrl;
    }

    public String getHouseId() {
        return houseId;
    }

    public void setHouseId(String houseId) {
        this.houseId = houseId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Character(){

    }
    public Character(String name, String imageurl, String houseName, String houseImageUrl, String houseId, String description) {
        this.name = name;
        this.imageurl = imageurl;
        this.houseName = houseName;
        this.houseImageUrl = houseImageUrl;
        this.houseId = houseId;
        this.description = description;


    }
}
