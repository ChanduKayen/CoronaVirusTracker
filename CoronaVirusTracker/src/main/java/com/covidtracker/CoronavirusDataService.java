package com.covidtracker;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.model.locationStats.LocationStats;
@Service
public class CoronavirusDataService {
    private List<LocationStats> allstats = new ArrayList<>();
	private static String VIRUS_DATA_URL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";
	
	
	public List<LocationStats> getAllstats() {
		return allstats;
	}

	@PostConstruct
	@Scheduled(cron = "* * 1 * * *") // Runs this method first hour of every day
	public void fetchvirusData() throws IOException {
		List<LocationStats> latestStats = new ArrayList<LocationStats>();
		URL url = new URL(VIRUS_DATA_URL);
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("GET");
		BufferedReader in = new BufferedReader(new InputStreamReader(
				con.getInputStream()));
				/*String inputLine;	
				while ((inputLine = in.readLine()) != null) {
				System.out.println(inputLine);
				} */
		Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(in);
		for (CSVRecord record : records) {
		     
				LocationStats locationStats = new LocationStats();
				locationStats.setState(record.get("Province/State"));
				locationStats.setCountry(record.get("Country/Region"));
				locationStats.setLatestTotalCases(record.get(record.size()-1));
		        latestStats.add(locationStats);
		}
				this.allstats = latestStats;
	           			
	}
	
}
