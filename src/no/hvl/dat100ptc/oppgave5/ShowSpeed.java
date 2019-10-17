package no.hvl.dat100ptc.oppgave5;

import javax.swing.JOptionPane;

import easygraphics.EasyGraphics;
import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;
import no.hvl.dat100ptc.oppgave2.GPSData;
import no.hvl.dat100ptc.oppgave2.GPSDataFileReader;
import no.hvl.dat100ptc.oppgave3.GPSUtils;
import no.hvl.dat100ptc.oppgave4.GPSComputer;

public class ShowSpeed extends EasyGraphics {
			
	private static int MARGIN = 50;
	private static int BARHEIGHT = 200; // assume no speed above 200 km/t

	private GPSComputer gpscomputer;
	private GPSPoint[] gpspoints;
	
	public ShowSpeed() {

		String filename = JOptionPane.showInputDialog("GPS data filnavn: ");
		gpscomputer = new GPSComputer(filename);

		gpspoints = gpscomputer.getGPSPoints();
		
	}
	
	// read in the files and draw into using EasyGraphics
	public static void main(String[] args) {
		launch(args);
	}

	public void run() {

		int N = gpspoints.length-1; // number of data points
		
		makeWindow("Speed profile", 2*MARGIN + 2 * N, 2 * MARGIN + BARHEIGHT);
		
		showSpeedProfile(MARGIN + BARHEIGHT,N);
	}
	
	
	
	public void showSpeedProfile(int ybase, int N) {
		
		System.out.println("Angi tidsskalering i tegnevinduet ...");
		int timescaling = Integer.parseInt(getText("Tidsskalering"));
				
		// TODO - START
		double asGet;
		int asRounded;
		double line;
		int lineRounded;
		for (int n=0,m=0; n<gpscomputer.speeds().length; n++) {
			line = gpscomputer.speeds()[n];
			lineRounded = (int)Math.ceil(line);
			
			if (lineRounded<0) {
				lineRounded = 0;
			}
			
			asGet = gpscomputer.averageSpeed();
			asRounded = (int)Math.ceil(asGet);
			
			m = m+3;
			setColor(0,255,0);
			drawLine(MARGIN, BARHEIGHT-asRounded, timescaling, BARHEIGHT-asRounded);
			setColor(0,0,255);
			drawLine(MARGIN + m, ybase, MARGIN + m, BARHEIGHT-lineRounded);
		}
		// TODO - SLUTT
	}
}
