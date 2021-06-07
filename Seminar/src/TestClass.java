import java.util.Scanner;

public class TestClass {

	public static void main(String[] args) {
		Scanner in=new Scanner(System.in);
		Comminicuator.getInstance().start();
		System.out.println("What part of The Project You Want To Run(1-3)");
		System.out.println("the first for the Heat Detcting "+"\n"+"Second For Montring The movement  "+"\n"+"Third For Fall Detecting ");
		int part=in.nextInt();
		Controller controller=new Controller(part);
System.out.println(controller.getNumOfChapter());
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
