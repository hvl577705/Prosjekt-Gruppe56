package no.hvl.dat100ptc.oppgave4;

import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;
import no.hvl.dat100ptc.oppgave2.GPSData;
import no.hvl.dat100ptc.oppgave2.GPSDataConverter;
import no.hvl.dat100ptc.oppgave2.GPSDataFileReader;
import no.hvl.dat100ptc.oppgave3.GPSUtils;

public class GPSComputer {
	
	private GPSPoint[] gpspoints;
	
	public GPSComputer(String filename) {

		GPSData gpsdata = GPSDataFileReader.readGPSFile(filename);
		gpspoints = gpsdata.getGPSPoints();

	}

	public GPSComputer(GPSPoint[] gpspoints) {
		this.gpspoints = gpspoints;
	}
	
	public GPSPoint[] getGPSPoints() {
		return this.gpspoints;
	}
	
	
	
	// beregn total distances (i meter)
	public double totalDistance() {

		double distance = 0;

		// TODO - START
		double d;
		for (int n=0,m=1; n<gpspoints.length && m<gpspoints.length; n++,m++) {
			d = GPSUtils.distance(gpspoints[n], gpspoints[m]);
			distance = distance + d;
		}
		return distance;
		// TODO - SLUTT
	}
	
	
	
	// beregn totale høydemeter (i meter)
	public double totalElevation() {

		double elevation = 0;

		// TODO - START
		double e;
		
		for (int n=0; n<gpspoints.length; n++) {
		e = gpspoints[n].getElevation();
			if (e>elevation) {
			elevation = e;
			}
		}
		return elevation;
		// TODO - SLUTT

	}

	
	
	// beregn total tiden for hele turen (i sekunder)
	public int totalTime() {
		
		int time = 0;
		int t;
		for (int n=0; n<gpspoints.length; n++) {
			t = gpspoints[n].getTime();
			if (t>time) {
				time = t;
			}
			time = time - gpspoints[0].getTime();
		}
	return time;
	}
		
	
	
	
	// beregn gjennomsnitshastighets mellom hver av gps punktene

	public double[] speeds() {
		// TODO - START
		double[] speed = new double[gpspoints.length-1];
		for (int n=0,m=1; n<gpspoints.length && m<gpspoints.length; n++,m++) {
			speed[n] = GPSUtils.speed(gpspoints[n], gpspoints[m]);
		}
		return speed;
		// TODO - SLUTT
	}
	
	
	
	public double maxSpeed() {
		
		double maxspeed = 0;
		
		// TODO - START
			maxspeed = GPSUtils.findMax(speeds());
		return maxspeed;
		// TODO - SLUTT
		
	}

	
	
	public double averageSpeed() {

		double average = 0;
		
		// TODO - START
		double ms = totalDistance() / totalTime();
		average = ms * 3.6;
		return average;
		// TODO - SLUTT
		
	}

	
	
	
	/*
	 * bicycling, <10 mph, leisure, to work or for pleasure 4.0 bicycling,
	 * general 8.0 bicycling, 10-11.9 mph, leisure, slow, light effort 6.0
	 * bicycling, 12-13.9 mph, leisure, moderate effort 8.0 bicycling, 14-15.9
	 * mph, racing or leisure, fast, vigorous effort 10.0 bicycling, 16-19 mph,
	 * racing/not drafting or >19 mph drafting, very fast, racing general 12.0
	 * bicycling, >20 mph, racing, not drafting 16.0
	 */

	// conversion factor m/s to miles per hour
	public static double MS = 2.236936;

	// beregn kcal gitt weight og tid der kjøres med en gitt hastighet
	public double kcal(double weight, int secs, double speed) {

		double kcal;

		// MET: Metabolic equivalent of task angir (kcal x kg-1 x h-1)
		double met = 0;		
		double speedmph = speed * MS;
		// TODO - START
		double hr = secs/ (60.0*60.0);

		if (speedmph < 10.0) {
			met = 4.0;
		} else if (speedmph >= 10.0 && speedmph < 12.0) {
			met = 6.0;
		} else if (speedmph >= 12.0 && speedmph < 14.0) {
			met = 8.0;
		} else if (speedmph >= 14.0 && speedmph < 16.0) {
			met = 10.0;
		} else if (speedmph >= 16.0 && speedmph < 20.0) {
			met = 12.0;
		} else {
			met = 16.0;
		}
		
		kcal = met * weight * hr;
		return kcal;
		// TODO - SLUTT
	}

	
	
	public double totalKcal(double weight) {

		double totalkcal = 0;
		// TODO - START
		totalkcal = kcal(weight, totalTime(), averageSpeed()/3.6);
		return totalkcal;
		// TODO - SLUTT
	}
	
	
	
	private static double WEIGHT = 80.0;
	
	public void displayStatistics() {
		double distRound = (Math.round((totalDistance()/1000)*100.0)/100.0);
		double eleRound = (Math.round(totalElevation()*100.0)/100.0);
		double mspdRound = (Math.round(maxSpeed()*100.0)/100.0);
		double aspdRound = (Math.round(averageSpeed()*100.0)/100.0);
		double energyRound = (Math.round(totalKcal(WEIGHT)*100.0)/100.0);
		System.out.println("==============================================");
		System.out.println(String.format("%-15s", "Total Time") + ":" +
				GPSUtils.formatTime(totalTime()));
		System.out.println(String.format("%-15s", "Total Distance") + ":" +
				String.format("%13s", distRound + " km"));
		System.out.println(String.format("%-15s", "Total Elevation") + ":" +
				String.format("%10s", eleRound) + " m");
		System.out.println(String.format("%-15s", "Max speed") + ":" + 
				String.format("%10s", mspdRound) + " km/t");
		System.out.println(String.format("%-15s", "Average speed") + ":" +
				String.format("%10s", aspdRound) + " km/t");
		System.out.println(String.format("%-15s", "Energy speed") + ":" +
				String.format("%10s", energyRound) + " kcal");
		System.out.println("==============================================");
		// TODO - SLUTT
		
	}

}
