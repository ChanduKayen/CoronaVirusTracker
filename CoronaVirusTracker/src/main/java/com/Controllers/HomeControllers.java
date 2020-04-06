package com.Controllers;

import java.util.List;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.covidtracker.CoronavirusDataService;
import com.model.locationStats.LocationStats;

@Controller
public class HomeControllers {
	@Autowired
    CoronavirusDataService coronavirusDataService;
	
	@GetMapping("/")
	public String home(Model model) {
		List<LocationStats> allstats = coronavirusDataService.getAllstats(); 
    	int count =0;

		for(LocationStats stats : allstats) {
        	count = count + Integer.parseInt(stats.getLatestTotalCases());
        }
		model.addAttribute("latestStats",coronavirusDataService.getAllstats());
		model.addAttribute("totalReportedCases",count);
		return "home";
	}
	
}
