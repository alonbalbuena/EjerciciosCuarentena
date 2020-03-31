package com.dawes.modelo;

public class Ciudades {
	
	private String cityName;
    private int currentConfirmedCount;
    private int confirmedCount;
    private int suspectedCount;
    private int curedCount;
    private int deadCount;
    private int locationId;
    private String cityEnglishName;
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public int getCurrentConfirmedCount() {
		return currentConfirmedCount;
	}
	public void setCurrentConfirmedCount(int currentConfirmedCount) {
		this.currentConfirmedCount = currentConfirmedCount;
	}
	public int getConfirmedCount() {
		return confirmedCount;
	}
	public void setConfirmedCount(int confirmedCount) {
		this.confirmedCount = confirmedCount;
	}
	public int getSuspectedCount() {
		return suspectedCount;
	}
	public void setSuspectedCount(int suspectedCount) {
		this.suspectedCount = suspectedCount;
	}
	public int getCuredCount() {
		return curedCount;
	}
	public void setCuredCount(int curedCount) {
		this.curedCount = curedCount;
	}
	public int getDeadCount() {
		return deadCount;
	}
	public void setDeadCount(int deadCount) {
		this.deadCount = deadCount;
	}
	public int getLocationId() {
		return locationId;
	}
	public void setLocationId(int locationId) {
		this.locationId = locationId;
	}
	public String getCityEnglishName() {
		return cityEnglishName;
	}
	public void setCityEnglishName(String cityEnglishName) {
		this.cityEnglishName = cityEnglishName;
	}

}
