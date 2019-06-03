import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.port.Port;
import lejos.hardware.sensor.SensorMode;
import java.util.ArrayList;
import lejos.hardware.Button;
import lejos.utility.Delay;
import lejos.hardware.lcd.LCD;
import lejos.hardware.sensor.MindsensorsAbsoluteIMU;

public class compassIMU {
	public static void main(String[] args) {
		Port port = LocalEV3.get().getPort("S1");
		
		MindsensorsAbsoluteIMU IMU = new MindsensorsAbsoluteIMU(port);
		IMU.setGyroFilter(0);
		SensorMode Rate = (SensorMode) IMU.getCompassMode();
		
		float[] sample = new float[Rate.sampleSize()];
		
		while (Button.getButtons()!= Button.ID_ESCAPE ){
			Rate.fetchSample(sample, 0);
			for(int x=0; x<sample.length; x++){
				LCD.drawString(sample[x]+"", 0, x);
			}
			Delay.msDelay(100);
		}
		
		IMU.close();
	}
}