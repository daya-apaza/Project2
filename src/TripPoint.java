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
