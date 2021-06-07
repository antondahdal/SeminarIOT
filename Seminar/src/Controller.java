import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import sun.java2d.pipe.hw.AccelGraphicsConfig;

import java.io.File;
import java.io.FileWriter;
import java.security.Timestamp;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Controller implements DataObservable {
	Location location = new Location();
	public ArrayList<Lost> Out = new ArrayList<Lost>();

	public Controller() {
		File PredictMetersFile = new File("Copy of all.csv");
		location.plotFromFile(PredictMetersFile);
	}

	@Override
	public void onDataReceived(List<EnvData> list) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		for (EnvData e : list) {
			 
//we made the temp in this example 40 and more if there's a fire and 20 and less if it's very cold
			if (e instanceof HTData) {
				HTData x = (HTData) e;
				if (x.getTemp() > 40) {
					System.out.println("The temperature Is"+x.getTemp()+"there's a Fire. Check !!");
				
				}
				if (x.getTemp() <20) {
					System.out.println("Heat Up The House It's Very Cold !!");
				}
			}
			
			

			if (e instanceof Ibeacon) {
				 checkIFStillOut();
				Ibeacon x = (Ibeacon) e;
				Lost y = new Lost(x.getMac(), x.getTimeStamp(), x.getRssi());
				
				System.out.println(location.PredictedMeters(y.getRssi()));
				
				
				
				
				if (location.PredictedMeters(y.getRssi()) >= 10) {
					if (Out.size() == 0) {
						y.setDate(now);
						System.out.println(y.getMac()+" Just went Outside The House");
						Out.add(y);
					}
					for (Lost l : Out) {
						if (!l.getMac().equals(y.getMac())) {
							System.out.println(y.getMac()+" Just went Outside The House");

							y.setDate(now);
							Out.add(y);
						}
					}
					
				}

				
				  if(location.PredictedMeters(y.getRssi())<=9) {
					  Out=new ArrayList<Lost>();
						System.out.println(y.getMac()+" Is In the House");

							  Out.remove(y);
						  }
					  
					  
				  
				 
			}
			
			if(e instanceof AccData) {
				AccData accData=(AccData)e;
				if(accData.SumVector()>0.3&&accData.SumVector()<0.5) {
					System.out.println("The elderly Fell Check Him Now!!");
				}
			}
		}
	}

	public void checkIFStillOut() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		
		for (Lost l : Out) {
			LocalDateTime now = LocalDateTime.now();
			if (l.getDate().getDayOfMonth() == now.getDayOfMonth()&&location.PredictedMeters(l.getRssi())>6) {
				if (l.getDate().getHour() - now.getHour() > 8){
					System.out.println("The Eldery is Out of his home for more Than 8 Hours Check him Now!!");
				}
			}
			if (l.getDate().getDayOfMonth() < now.getDayOfMonth() && l.getDate().getHour() > 7) {
				System.out.println("The Eldery is Out of his home for more Than 8 Hours Check him Now!!");

			}

		}
	}

	
}
	

