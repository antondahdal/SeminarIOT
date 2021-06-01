

public class EnvData {
	private String mac;
	private String timeStamp;
	
	public EnvData(String mac,String timeStamp){
		setMac(mac);
		setTimeStamp(timeStamp);
	}
	
	public void setMac(String mac) {
		this.mac=mac;
	}
	public String getMac() {
		return mac;
	}
	
	public void setTimeStamp(String timeStamp) {
		this.timeStamp=timeStamp;
	}
	public String getTimeStamp(){
		return timeStamp;
	}
	@Override
	public String toString(){
		return "EnvData  ["+super.toString()+getMac()+getTimeStamp()+" ]";
	}
	
	
}
