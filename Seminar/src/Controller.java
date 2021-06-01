import java.io.File;
import java.util.List;

import java.io.File;
import java.io.FileWriter;

public class Controller implements DataObservable{
	Location location=new Location();
	
	public Controller() {
		File PredictMetersFile=new File("Copy of all.csv");
		location.plotFromFile(PredictMetersFile);
	}

	@Override
	public void onDataReceived(List<EnvData> list) {
		 for (EnvData e:list) {
			 
				if(e instanceof HTData) {
				  HTData x=(HTData)e;
				  if(x.getTemp()>9) {
						/*
						 * System.out.println(x.getTemp()); System.out.println(x.getHumidty());
						 */
				  }
				  if(x.getTemp()<5) {
				  System.out.println("Heat Up The Room"); }
				}
				
				
				if(e instanceof Ibeacon) {
					Ibeacon x=(Ibeacon)e;
					// x predict meters 
					//if x.predict>15M&&time>12H
					//sysout()ef7s weno
					System.out.println(location.PredictedMeters(x.getRssi()));
				}
		}
		 
	}

}
