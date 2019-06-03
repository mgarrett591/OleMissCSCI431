import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.port.Port;
import lejos.hardware.sensor.SensorMode;
import lejos.remote.nxt.NXTCommConnector;
import lejos.remote.nxt.NXTConnection;

import java.io.DataInputStream;
import java.io.DataOutputStream;

import lejos.hardware.Bluetooth;
import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.hardware.sensor.MindsensorsAbsoluteIMU;
import lejos.utility.Delay;

public class Draco {	
	private static final String EV3 = "00:16:53:44:0c:e0";
	
	public static void main(String[] args) throws Exception{
		
		//start conntect copyPasteBlock
		NXTCommConnector connector = Bluetooth.getNXTCommConnector();
		NXTConnection connection = connector.connect(EV3,NXTConnection.RAW);
		
		DataInputStream input = connection.openDataInputStream();
		DataOutputStream output = connection.openDataOutputStream();
		
		
		int cnt = 0;
		byte[] o = new byte[1];
		byte[] r = new byte[1];
		//end conntect copyPaseBlock
		
		Port port = LocalEV3.get().getPort("S1");
		
		MindsensorsAbsoluteIMU IMU = new MindsensorsAbsoluteIMU(port);
		IMU.setGyroFilter(0);
		SensorMode Rate = (SensorMode) IMU.getAccelerationMode();
		
		float[] acceleration = new float[Rate.sampleSize()];
		
		String command = "";
		o[0] = 0;
		double deadZone=1.4;
		while (Button.getButtons() != Button.ID_ESCAPE) {
			
			Rate.fetchSample(acceleration, 0);
		
			if(acceleration[0]>deadZone&&acceleration[2]<8.7){
				command = "backward";
				o[0]=4;
			}
			else if(acceleration[0]<-deadZone&&acceleration[2]<9.4){
				command = "forward ";
				o[0]=3;
			}
			else if(acceleration[1]<-deadZone&&acceleration[2]<9.4){
				command = "left    ";
				o[0]=2;
			}
			else if(acceleration[1]>deadZone&&acceleration[2]<9.4){
				command = "right   ";
				o[0]=1;
			}
			else{
				command = "stay    ";
				o[0]=0;
			}
			LCD.drawString("X "+acceleration[0] ,0 , 1);
			LCD.drawString("Y "+acceleration[1] ,0 , 2);
			LCD.drawString("Z "+acceleration[2] ,0 , 3);
			LCD.drawString(command, 0, 4);
			output.write(o);
			output.flush();
			Delay.msDelay(100);
		}
		
		//start conntect copyPasteBlock
		output.close();
		input.close();
		connection.close();
		//end conntect copyPaseBlock
				
		IMU.close();
	}
	
	
	
}