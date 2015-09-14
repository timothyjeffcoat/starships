package com.swapi.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Oleur on 22/12/2014.
 * Starship model represents a single transport craft that has hyperdrive capability.
 * 
 * Modified by Tim Jeffcoat 14/09/2015
 * Made class java bean.   
 */
public class Starship extends Vehicle implements Serializable {

    @SerializedName("starship_class")
	private String starshipClass;

    @SerializedName("hyperdrive_rating")
	private String hyperdriveRating;

    @SerializedName("MGLT")
	private String mglt;

	public String getStarshipClass() {
		return starshipClass;
	}

	public void setStarshipClass(String starshipClass) {
		this.starshipClass = starshipClass;
	}

	public String getHyperdriveRating() {
		return hyperdriveRating;
	}

	public void setHyperdriveRating(String hyperdriveRating) {
		this.hyperdriveRating = hyperdriveRating;
	}

	public String getMglt() {
		return mglt;
	}

	public void setMglt(String mglt) {
		this.mglt = mglt;
	}

}
