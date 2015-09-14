package com.swapi.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Oleur on 22/12/2014.
 * Vehicle model represents a single transport craft that does not have hyperdrive capability.
 * 
 * Modified by Tim Jeffcoat 14/09/2015
 * Made class java bean.  
 */
public class Vehicle implements Serializable {
    private String name;
    private String model;

    @SerializedName("vehicle_class")
	private String vehicleClass;

    private String manufacturer;

    @SerializedName("cost_in_credits")
	private String costInCredits;

    private String length;
    private String crew;
    private String passengers;

    @SerializedName("max_atmosphering_speed")
	private String maxAtmospheringSpeed;

    @SerializedName("cargo_capacity")
	private String cargoCapacity;

    private String consumables;
    public String created;
    public String edited;
    private String url;

    @SerializedName("pilots")
	private ArrayList<String> pilotsUrls;

    @SerializedName("films")
	private ArrayList<String> filmsUrls;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getVehicleClass() {
		return vehicleClass;
	}

	public void setVehicleClass(String vehicleClass) {
		this.vehicleClass = vehicleClass;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getCostInCredits() {
		return costInCredits;
	}

	public void setCostInCredits(String costInCredits) {
		this.costInCredits = costInCredits;
	}

	public String getLength() {
		return length;
	}

	public void setLength(String length) {
		this.length = length;
	}

	public String getCrew() {
		return crew;
	}

	public void setCrew(String crew) {
		this.crew = crew;
	}

	public String getPassengers() {
		return passengers;
	}

	public void setPassengers(String passengers) {
		this.passengers = passengers;
	}

	public String getMaxAtmospheringSpeed() {
		return maxAtmospheringSpeed;
	}

	public void setMaxAtmospheringSpeed(String maxAtmospheringSpeed) {
		this.maxAtmospheringSpeed = maxAtmospheringSpeed;
	}

	public String getCargoCapacity() {
		return cargoCapacity;
	}

	public void setCargoCapacity(String cargoCapacity) {
		this.cargoCapacity = cargoCapacity;
	}

	public String getConsumables() {
		return consumables;
	}

	public void setConsumables(String consumables) {
		this.consumables = consumables;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public ArrayList<String> getPilotsUrls() {
		return pilotsUrls;
	}

	public void setPilotsUrls(ArrayList<String> pilotsUrls) {
		this.pilotsUrls = pilotsUrls;
	}

	public ArrayList<String> getFilmsUrls() {
		return filmsUrls;
	}

	public void setFilmsUrls(ArrayList<String> filmsUrls) {
		this.filmsUrls = filmsUrls;
	}
}
