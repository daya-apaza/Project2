import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class TripPoint {
	private double lat;
	private double lon;
	private int time;
	private static ArrayList<TripPoint> trip = new ArrayList<TripPoint>();

	//Constructor
	public TripPoint(int time, double lat, double lon) {
		this.time = time;
		this.lat = lat;
		this.lon = lon;
	}
	
	//getter
	public int getTime() {
		return time;
	}

	public double getLat() {
		return lat;
	}

	public double getLon() {
		return lon;
	}
	
	public static ArrayList<TripPoint> getTrip() {
		return new ArrayList<>(trip);
	}
	
	//Calculating distance
	//Use Harversine formula to calculating the distance between two GPS coordinates in km
	public static double haversineDistance(TripPoint a, TripPoint b) {
		//Earth radius in km
		final double R = 6371.0;
				//convert degree to radians since Java trig functions expect rad inputs
		double lat1 = Math.toRadians(a.getLat());
		double lon1 = Math.toRadians(a.getLon());

		double lat2 = Math.toRadians(b.getLat());
		double lon2 = Math.toRadians(b.getLon());
		
		//calculating change in lat and lon
		double deltaLat = lat2 - lat1;
		double deltaLon = lon2 - lon1;
		
		//implementing harversine formula
		double h = Math.pow(Math.sin(deltaLat / 2), 2)
				+ Math.cos(lat1) * Math.cos(lat2) * Math.pow(Math.sin(deltaLon / 2), 2);
		//get angular distance
		double c = 2 * Math.atan2(Math.sqrt(h), Math.sqrt(1 - h));
		
		//angular distance in km
		return R * c;
	}
		// avr speed = distance/time units km/h
	public static double avgSpeed(TripPoint a, TripPoint b) {
		double distance = haversineDistance(a, b);
		
		//use absolute to get positive result
		double minutes = Math.abs(a.getTime() - b.getTime());

		double hours = minutes / 60.0;

		return distance / hours;
	}

	//solve for sum of distance
		public static double totalDistance() {
		double total = 0;

		for (int i = 0; i < trip.size() - 1; i++) {

			TripPoint a = trip.get(i);
			TripPoint b = trip.get(i + 1);

			total += haversineDistance(a, b);
		}

		return total;
	}
	
	//total time in hours
	public static double totalTime() {
		int totalMinutes = trip.get(trip.size() - 1).getTime();
		return totalMinutes / 60.0;
	}
	
