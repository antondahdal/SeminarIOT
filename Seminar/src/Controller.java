import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Controller implements DataObservable {
	Location location = new Location();
	public ArrayList<Lost> Out = new ArrayList<Lost>();
	int numOfChapter=0;

	public Controller(int chapterNum) {
numOfChapter=chapterNum;
File PredictMetersFile = new File("Copy of all.csv");
		location.plotFromFile(PredictMetersFile);
	}
	
	
	public void setNumOfChapter(int numOfChapter) {
		this.numOfChapter = numOfChapter;
	}
	public int getNumOfChapter() {
		return numOfChapter;
	}
	
	@Override
	public void onDataReceived(List<EnvData> list) {
		

		for (EnvData e : list) {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
			LocalDateTime now = LocalDateTime.now();
			
			 
			 if(getNumOfChapter()== 1){
			if (e instanceof HTData) {
				HTData x = (HTData) e;
				System.out.println(x.getTemp());
				if (x.getTemp() > 70) {
					System.out.println("The temperature Is"+x.getTemp()+"there's a Fire. Check !!");
				
				}
				if (x.getTemp() <10) {
					System.out.println("Heat Up The House It's Very Cold !!");
				}
			}
			 }
			 
			 
			 if(getNumOfChapter()==2){
			if (e instanceof Ibeacon) {
				 checkIFStillOut();
				Ibeacon x = (Ibeacon) e;
				System.out.println(x.getRssi());
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
			 }
			 if(getNumOfChapter()==3){
			if(e instanceof AccData) {
				AccData accData=(AccData)e;
				System.out.println(accData.getX());
				if(accData.SumVector()>0.3&&accData.SumVector()<0.5) {
					System.out.println("The elderly Fell Check Him Now!!");
				}
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
	

