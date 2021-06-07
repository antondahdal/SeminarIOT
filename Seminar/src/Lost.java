import java.time.LocalDateTime;
import java.util.Date;

public class Lost extends Ibeacon {
	
	LocalDateTime date=LocalDateTime.now();
	
	public Lost(String mac,String timestamp,int rssi) {
		super(mac,timestamp,rssi);
	
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime now) {
		this.date = now;
	}
	
	
	
	}