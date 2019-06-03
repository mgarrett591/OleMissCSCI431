package testUltraSonicSensor;
import lejos.hardware.*;
import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.lcd.LCD;
import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.hardware.sensor.SensorMode;
import lejos.utility.Delay;

public class TestUltra{
 
	public static void main(String[] args){
		Port port = LocalEV3.get().getPort("S1");
		
		EV3UltrasonicSensor ultra = new EV3UltrasonicSensor(port);
	
		SensorMode distancefinder = (SensorMode) ultra.getDistanceMode();
		float[] sample = new float[ultra.sampleSize()];
	
		Sound.beep();
	
		while(Button.getButtons()!=Button.ID_ESCAPE){
			distancefinder.fetchSample(sample, 0);
			LCD.drawString("Distance = "+sample[0], 0, 0);
			Delay.msDelay(100);
		}
	ultra.close();
	}
}