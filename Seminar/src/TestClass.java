
public class TestClass {

	public static void main(String[] args) {
		Comminicuator.getInstance().start();
		Controller controller=new Controller();

		Comminicuator.getInstance().addListener(controller);
		String topic="/gw/ac233fc02255/status";
		int qos=0;
	try {
		Comminicuator.getInstance().subscribe(topic, qos);
		
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	}
	}

}
