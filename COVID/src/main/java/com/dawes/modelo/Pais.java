package com.dawes.modelo;

public class Pais {
	
    private String continentEnglishName;
    private String countryEnglishName;
    private String provinceName;
    private int currentConfirmedCount;
    private int confirmedCount;
    private int curedCount;
    private int deadCount;
    private long updateTime;
    
    
	public String getContinentEnglishName() {
		return continentEnglishName;
	}
	public void setContinentEnglishName(String continentEnglishName) {
		this.continentEnglishName = continentEnglishName;
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
	public int getCuredCount() {
		return curedCount;
	}
	public void setCuredCount(int curedCount) {
		this.curedCount = curedCount;
	}
	public long getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(long updateTime) {
		this.updateTime = updateTime;
	}
	public String getCountryEnglishName() {
		return countryEnglishName;
	}
	public void setCountryEnglishName(String countryEnglishName) {
		this.countryEnglishName = countryEnglishName;
	}
	public int getDeadCount() {
		return deadCount;
	}
	public void setDeadCount(int deadCount) {
		this.deadCount = deadCount;
	}
	public String getProvinceName() {
		return provinceName;
	}
	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

}
