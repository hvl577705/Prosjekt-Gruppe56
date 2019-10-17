package no.hvl.dat100ptc.oppgave3;

import static java.lang.Math.*;

import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;

public class GPSUtils {

	public static double findMax(double[] da) {

		double max; 
		
		max = da[0];
		
		for (double d : da) {
			if (d > max) {
				max = d;
			}
		}
		
		return max;
	}

	
	
	public static double findMin(double[] da) {

		double min;

		// TODO - START
		min = da[0];
		
		for (double d : da) {
			if (d < min) {
				min = d;
			}
		}
		return min;
		// TODO - SLUTT
	}

	
	
	public static double[] getLatitudes(GPSPoint[] gpspoints) {

		// TODO - START
		double[] lats = new double[gpspoints.length];
		
		int n=0;
		for (n=0; n<gpspoints.length; n++) {
		if (n<gpspoints.length) {
			lats[n] = gpspoints[n].getLatitude();
		}
		}
		return lats;
		// TODO - SLUTT
	}

	
	
	public static double[] getLongitudes(GPSPoint[] gpspoints) {

		// TODO - START
		double[] longs = new double[gpspoints.length];
		
		int n=0;
		for (n=0; n<gpspoints.length; n++) {
		if (n<gpspoints.length) {
			longs[n] = gpspoints[n].getLongitude();
		}
		}
		return longs;
		// TODO - SLUTT

	}

	
	
	private static int R = 6371000; // jordens radius

	public static double distance(GPSPoint gpspoint1, GPSPoint gpspoint2) {

		double d;
		double latitude1, longitude1, latitude2, longitude2;

		// TODO - START
		
		latitude1 = gpspoint1.getLatitude();
		longitude1 = gpspoint1.getLongitude();
		latitude2 = gpspoint2.getLatitude();
		longitude2 = gpspoint2.getLongitude();
		
        double dLat = Math.toRadians(latitude2 - latitude1); 
        double dLon = Math.toRadians(longitude2 - longitude1); 
  
        latitude1 = Math.toRadians(latitude1); 
        latitude2 = Math.toRadians(latitude2); 
  
        double a = Math.pow(Math.sin(dLat / 2), 2) +  
                   Math.pow(Math.sin(dLon / 2), 2) *  
                   Math.cos(latitude1) *  
                   Math.cos(latitude2); 

        double c = 2 * Math.asin(Math.sqrt(a));
        
        d = R * c;
        return d; 
		
		// TODO - SLUTT

	}

	
	
	public static double speed(GPSPoint gpspoint1, GPSPoint gpspoint2) {

		int secs;
		double speed;

		// TODO - START
		int secs1 = gpspoint1.getTime();
		int secs2 = gpspoint2.getTime();
		secs = secs2-secs1;
		
		double mps;
		double d;
		d = distance(gpspoint1, gpspoint2);
		mps = d / secs;
		speed = mps * 3.6;
		
		return speed;
		// TODO - SLUTT
	}

	
	
	public static String formatTime(int secs) {

		String timestr;
		String TIMESEP = ":";

		
		// TODO - START
		int hr = secs / 3600;
		int restHr = secs % 3600;
		int min = restHr / 60;
		int sec = restHr % 60;
		timestr = "  "+String.format("%02d", hr) + TIMESEP + String.format("%02d", min)+
					TIMESEP + String.format("%02d", sec);
		return timestr;
		// TODO - SLUTT

	}
	
	
	private static int TEXTWIDTH = 10;

	public static String formatDouble(double d) {

		String str;

		// TODO - START
		double dRounded = Math.round(d*100.0)/100.0;
		str = String.format("%" + TEXTWIDTH + "s", dRounded);
		return str;
	}
		// TODO - SLUTT
		
}
