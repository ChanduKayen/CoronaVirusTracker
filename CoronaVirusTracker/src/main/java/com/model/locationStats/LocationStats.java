package com.model.locationStats;

public class LocationStats {

	private String state;
	private String Country;
	private String latestTotalCases;
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return Country;
	}
	public void setCountry(String country) {
		Country = country;
	}
	public String getLatestTotalCases() {
		return latestTotalCases;
	}
	public void setLatestTotalCases(String latestTotalCases) {
		this.latestTotalCases = latestTotalCases;
	}
	@Override
	public String toString() {
		return "LocationStats [state=" + state + ", Country=" + Country + ", latestTotalCases=" + latestTotalCases
				+ "]";
	}
	
	
}
