package com.toursim.application.rating;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.toursim.application.user.User;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RRating
{

    private int id;
    private int stars;
    private String comment;

    private String userName;
    private int itineraryId;
    private int cityId;
    private int attractionId;

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public int getStars() {
        return stars;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public int getItineraryId()
    {
        return itineraryId;
    }

    public void setItineraryId(int itineraryId)
    {
        this.itineraryId = itineraryId;
    }

    public int getCityId()
    {
        return cityId;
    }

    public void setCityId(int cityId)
    {
        this.cityId = cityId;
    }

    public int getAttractionId()
    {
        return attractionId;
    }

    public void setAttractionId(int attractionId)
    {
        this.attractionId = attractionId;
    }
}