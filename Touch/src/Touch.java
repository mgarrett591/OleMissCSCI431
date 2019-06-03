import lejos.hardware.sensor.*;
import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.port.*;
import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.hardware.lcd.LCD;
import lejos.utility.Delay;

public class Touch {
	public static void main(String[] args) {
		Port port = LocalEV3.get().getPort("S1");
		EV3TouchSensor touch = new EV3TouchSensor(port);
		SensorMode touchprovider = (SensorMode) touch.getTouchMode();
		
		float[] sample = new float[touch.sampleSize()];
		Sound.beep();
		
		while (Button.getButtons()!= Button.ID_ESCAPE ){
			touchprovider.fetchSample(sample, 0);
			LCD.drawString("touch = " + sample[0],0,0);
			Delay.msDelay(100);
		}
		
		touch.close();
	}
}