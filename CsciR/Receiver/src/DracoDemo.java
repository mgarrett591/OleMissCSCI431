import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.port.Port;
import lejos.hardware.sensor.SensorMode;
import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.hardware.sensor.MindsensorsAbsoluteIMU;
import lejos.utility.Delay;


public class DracoDemo {
	public static void main(String[] args) throws Exception{
		
		Port port = LocalEV3.get().getPort("S1");
		
		MindsensorsAbsoluteIMU IMU = new MindsensorsAbsoluteIMU(port);
		IMU.setGyroFilter(0);
		SensorMode Rate = (SensorMode) IMU.getAccelerationMode();
		
		float[] acceleration = new float[Rate.sampleSize()];
		
		String command = "";
		Byte data = 0;
		double deadZone=1.4;
		while (Button.getButtons() != Button.ID_ESCAPE) {
			
			Rate.fetchSample(acceleration, 0);
		
			if(acceleration[0]>deadZone&&acceleration[2]<9.4){
				command = "backward";
				data=4;
			}
			else if(acceleration[0]<-deadZone&&acceleration[2]<9.4){
				command = "forward ";
				data=3;
			}
			else if(acceleration[1]<-deadZone&&acceleration[2]<9.4){
				command = "left    ";
				data=2;
			}
			else if(acceleration[1]>deadZone&&acceleration[2]<9.4){
				command = "right   ";
				data=1;
			}
			else{
				command = "stay    ";
				data=0;
			}
			LCD.drawString("X "+acceleration[0] ,0 , 1);
			LCD.drawString("Y "+acceleration[1] ,0 , 2);
			LCD.drawString("Z "+acceleration[2] ,0 , 3);
			LCD.drawString(command, 0, 4);
		}
				
		IMU.close();
	}
	
}