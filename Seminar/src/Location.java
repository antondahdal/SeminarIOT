import java.io.File;
import java.util.ArrayList;
import java.util.Date;

import org.jfree.data.time.Millisecond;


public class Location {
	private String mac;
	private int rssi;
	private int meters;
	

	protected ArrayList<Location> locList=new ArrayList<Location>();
		public Location() {}
			public Location(String mac,int rssi,int meters) {
				this.mac=mac;
				this.rssi=rssi;
				this.meters=meters;
						
			}
			public Location(String mac,int rssi) {
				this.mac=mac;
				this.rssi=rssi;
						
			}
			//x=rssi
			//y=meters
			
		public void print() {
			for(int i=0;i<locList.size();i++) {
				System.out.println(locList.get(i).meters);
			}
		}
	public double geteSlope() {
		double slope=0;
		double sumX=0;
		double sumY=0;
		double sumXY=0;
		double sumSquareX=0;
		for(Location e:locList) {
				sumX+=e.getRssi();
				sumY+=e.getMeters();
				sumXY+=e.getRssi()*e.getMeters();
				sumSquareX+=Math.pow(e.getRssi(), 2) ;
			}
		
		slope=( sumXY-(sumX*sumY))/((sumSquareX)-(Math.pow(sumX, 2))); 
		return slope;
	}
	public String getMac() {
		return mac;
	}

	public int getRssi() {
		return rssi;
	}

	public int getMeters() {
		return meters;
	}
	

	public double getB() {
		double sumX=0;
		double sumY=0;
		double sumXY=0;
		double sumSquareX=0;
		for(Location e:locList) {
			sumX+=e.getRssi();
			sumY+=e.getMeters();
			sumXY+=e.getRssi()*e.getMeters();
			sumSquareX+=Math.pow(e.getRssi(), 2) ;
			}
		
		double B=0;
		B=((sumY*sumSquareX)-(sumX*sumXY))/((sumSquareX)-(Math.pow(sumX, 2)));
		return B;
		}
	public double getP() {
		double sumX=0;
		double sumY=0;
		double sumXY=0;
		double sumSquareX=0;
		double sumSquareY=0;
		double P=0;
		for(Location e:locList) {
			sumX+=e.getRssi();
			sumY+=e.getMeters();
			sumXY+=e.getRssi()*e.getMeters();
			sumSquareX+=Math.pow(e.getRssi(), 2) ;
			sumSquareY+=Math.pow(e.getMeters(), 2) ;
			}
		
		P= (Math.pow((sumXY-(sumX*sumY)), 2))  /(((sumSquareX)-(Math.pow(sumX, 2)))*((sumSquareY)-((Math.pow(sumY, 2)))));
		return P;
	}
	public double PredictedMeters(int rssi) {
		double prdeictedHum=0;
		
		prdeictedHum=((geteSlope()*rssi)+getB());
		
		return prdeictedHum;
	}
	public void plotFromFile(File file) {
		byte[] data= FileUtils.readCopy(file);
		String content=new String(data);
		String lines[]=content.split("\n");
		for(String line:lines) {
			String fields[]=line.split(",");
			String mac=fields[0];
			String rssi=fields[1];
			String meters=fields[2];
			  int rssi1=Integer.parseInt(rssi);
			 int meters1=Integer.parseInt(meters);
			 Location loc=new Location(mac, rssi1,meters1);
			 locList.add(loc);
			 }
		
	}
	
	
	
	
	
	
}
