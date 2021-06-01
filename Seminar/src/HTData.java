
public class HTData extends EnvData {
	private double temp;
	private double humidty;
	
	public HTData(String mac,String timeStamp,double temp,double humidty) {
		super(mac,timeStamp);
		setTemp(temp);
		setHumidty(humidty);
	}

	public double getTemp() {
		return temp;
	}


	public void setTemp(double temp) {
		this.temp = temp;
	}

	public double getHumidty() {
		return humidty;
	}

	public void setHumidty(double humidty) {
		this.humidty = humidty;
	}

	@Override
	public String toString() {
		return"Mac=  "+ this.getMac() + "TimeStamp=  "+this.getTimeStamp()+ "  HTData [temp=" + temp + ", humidty=" + humidty + "]  ";
	}

	
}