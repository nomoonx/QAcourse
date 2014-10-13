package com.noMoonStudio.PITdemo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * Created by noMoon on 2014-10-13.
 */
public class building {

    public List<String> checkBuildings(List<String> buildings, String code) {
	List<String> result = new ArrayList<String>();
	for (int j = 0; j < buildings.size(); j++) {
	    String tester = code;
	    String building = buildings.get(j);
	    int times = building.length();
	    for (int k = 0; k < times && tester.length() != 0; k++) {
		if (tester.substring(0, 1).equalsIgnoreCase(
			building.substring(0, 1))) {
		    tester = tester.substring(1, tester.length());
		}
		if (tester.length() == 0) {
		    result.add(buildings.get(j));
		}
		building = building.substring(1, building.length());
	    }
	}
	return result;
    }

    public static void main(String[] args) throws IOException {
	FileReader file = new FileReader("BUILDING.IN");
	BufferedReader input = new BufferedReader(file);
	int numSets = (Integer.parseInt(input.readLine()));
	Vector buildings, printers;
	int numWords, j, k, times;
	String building;
	String code, tester;
	for (int h = 0; h < numSets; h++) {
	    buildings = new Vector();
	    printers = new Vector();
	    numWords = (Integer.parseInt(input.readLine()));
	    for (k = 0; k < numWords; k++) {
		building = input.readLine();
		buildings.add(building);
	    }
	    code = input.readLine();
	    for (j = 0; j < buildings.size(); j++) {
		tester = code;
		building = (String) buildings.get(j);
		times = building.length();
		for (k = 0; k < times && tester.length() != 0; k++) {
		    if (tester.substring(0, 1).equalsIgnoreCase(
			    building.substring(0, 1))) {
			tester = tester.substring(1, tester.length());
		    }
		    if (tester.length() == 0) {
			printers.add(buildings.get(j));
		    }
		    building = building.substring(1, building.length());
		}
	    }
	    System.out.println("Data Set " + (h + 1) + ":");
	    for (j = 0; j < printers.size(); j++) {
		System.out.println((String) printers.get(j));
	    }
	}
    }
}
