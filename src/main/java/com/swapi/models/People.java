package com.swapi.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Oleur on 21/12/2014.
 * People model represents an individual person or character within the Star Wars universe.
 * 
 * Modified by Tim Jeffcoat 14/09/2015
 * Made class java bean.  
 */
public class People implements Serializable {
    private String name;

    @SerializedName("birth_year")
	private String birthYear;

    @SerializedName("films")
	private ArrayList<String> filmsUrls;

    private String gender;

    @SerializedName("hair_color")
	private String hairColor;

    private String height;

    @SerializedName("homeworld")
	private String homeWorldUrl;

    private String mass;

    @SerializedName("skin_color")
	private String skinColor;

    public String created;
    public String edited;
    private String url;
    @SerializedName("species")
    public ArrayList<String> speciesUrls;

    @SerializedName("starships")
    public ArrayList<String> starshipsUrls;

    @SerializedName("vehicles")
    public ArrayList<String> vehiclesUrls;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBirthYear() {
		return birthYear;
	}

	public void setBirthYear(String birthYear) {
		this.birthYear = birthYear;
	}

	public ArrayList<String> getFilmsUrls() {
		return filmsUrls;
	}

	public void setFilmsUrls(ArrayList<String> filmsUrls) {
		this.filmsUrls = filmsUrls;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getHairColor() {
		return hairColor;
	}

	public void setHairColor(String hairColor) {
		this.hairColor = hairColor;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getHomeWorldUrl() {
		return homeWorldUrl;
	}

	public void setHomeWorldUrl(String homeWorldUrl) {
		this.homeWorldUrl = homeWorldUrl;
	}

	public String getMass() {
		return mass;
	}

	public void setMass(String mass) {
		this.mass = mass;
	}

	public String getSkinColor() {
		return skinColor;
	}

	public void setSkinColor(String skinColor) {
		this.skinColor = skinColor;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
