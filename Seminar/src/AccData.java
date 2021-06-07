import java.lang.Math;
public class AccData extends EnvData {

	private double x;
	private double y;
	private double z;
	
	public AccData(String mac,String timeStamp) {
		super(mac,timeStamp);
		
	}

	
	
	public static boolean isAccelerometerRawData(String rawdata) {
		if(rawdata.length() == 52) {
		String frameType=rawdata.substring(22, 24);
		String versionNumber=rawdata.substring(24, 26);
		if(frameType.equals("A1") && versionNumber.equals("03")) {
		return true;
		}
		}
		return false;
		
		}
	
	public void parseRawData(String rawdata) {
		 if(rawdata.length() == 52) {
		String xbyte1=rawdata.substring(28, 30);
		String xbyte2=rawdata.substring(30, 32);
		double x =ConversionUtils.computeFixedPointFormat(xbyte1,xbyte2);
		setX(x);
		String ybyte1=rawdata.substring(32, 34);
		String ybyte2=rawdata.substring(34, 36);
		double y =ConversionUtils.computeFixedPointFormat(ybyte1,ybyte2);
		setY(y);
		String zbyte1=rawdata.substring(36, 38);
		String zbyte2=rawdata.substring(38, 40);
		double z =ConversionUtils.computeFixedPointFormat(zbyte1,zbyte2);
		setZ(z);
		 }
		}
	
	
	
	public double SumVector() {
		
		return Math.sqrt(Math.pow(getX(),2)+Math.pow(getY(),2)+Math.pow(getZ(),2));
	}
	
	@Override
	public String toString() {
		return "TimeStamp =  "
				+ getTimeStamp() +  " gMac= " + getMac() +" AccData [x=" + x + ", y=" + y + ", z=" + z +  "]";
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getZ() {
		return z;
	}

	public void setZ(double z) {
		this.z = z;
	}
	
}
