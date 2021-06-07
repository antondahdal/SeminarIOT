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
			 checkIFStillOut();
			 

			if (e instanceof HTData) {
				HTData x = (HTData) e;
				if (x.getTemp() > 9) {

				
				}
				if (x.getTemp() < 5) {
					System.out.println("Heat Up The Room");
				}
			}

			if (e instanceof Ibeacon) {
				Ibeacon x = (Ibeacon) e;
				Lost y = new Lost(x.getMac(), x.getTimeStamp(), x.getRssi());

				if (location.PredictedMeters(y.getRssi()) > 10) {
					if (Out.size() == 0) {
						Out.add(y);
					}
					for (Lost l : Out) {
						if (!l.getMac().equals(y.getMac())) {

							y.setDate(now);
							System.out.println(y.getDate().getHour());
							Out.add(y);
						}
					}
					
				}

				
				  if(location.PredictedMeters(y.getRssi())<=10&&Out.contains(y)) {
					  Out.remove(y);
				  }
				 
			}
			
			if(e instanceof AccData) {
				AccData accData=(AccData)e;
				System.out.println(accData.SumVector());
				if(accData.SumVector()>0.3&&accData.SumVector()<0.5) {
					System.out.println("fall");
				}
			}
		}
	}

	public void checkIFStillOut() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();

		for (Lost l : Out) {
			if (l.getDate().getDayOfMonth() == now.getDayOfMonth()) {
				if (l.getDate().getHour() - now.getHour() > 8) {
					System.out.println("The Eldery is Out of his home for more Than 8 Hours Check him Now!!");
				}
			}
			if (l.getDate().getDayOfMonth() < now.getDayOfMonth() && l.getDate().getHour() > 7) {
				System.out.println("The Eldery is Out of his home for more Than 8 Hours Check him Now!!");

			}

		}
	}

	
}
