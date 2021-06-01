
public class Ibeacon  extends EnvData implements Comparable<Ibeacon>{
	private int rssi;
	
	
	public Ibeacon(String mac,String timestamp,int rssi) {
		super(mac,timestamp);
		setRssi(rssi);
	}
	public int getRssi() {
		return rssi;
	}
	public void setRssi(int rssi) {
		this.rssi = rssi;
	}
	
	@Override
	public String toString() {
		return "[ Mac=  "+getMac()+" TimeStamp=  "+getTimeStamp() + " RSSI=  "+getRssi()+" ]";
	}
	@Override
	public int compareTo(Ibeacon o) {
		Integer a1=this.rssi;
		Integer  a2=o.rssi;
		return 	a2.compareTo(a1) ;
	}
}