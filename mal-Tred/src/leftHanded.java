import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.motor.Motor;
import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.hardware.sensor.SensorMode;
import lejos.utility.Delay;
import lejos.hardware.lcd.LCD;

public class leftHanded{
 
	public static void main(String[] args){
		
		setup();
		
		
		while(isNotEscapePressed()){
			
			goForward();
			
			stepBack();
			lookLeft();
			if(isObstical()){
				lookRight();
				lookRight();
			}
				
			if(isObstical()){
				lookRight();
			}
			Delay.msDelay(1000);
			
			
		}
		
		
		shutdown();
	}
	
	public static final int knockBack = 1000;
	public static final int turnTime = 2000;
	public static final double obsticalTolerance = .1;
	public static final int speed = 125;
	public static final int turnSpeed = 50;
	public static SensorMode distancefinder;
	public static EV3UltrasonicSensor ultra;
	
	public static void setup(){
		Port port = LocalEV3.get().getPort("S4");
		ultra = new EV3UltrasonicSensor(port);
		distancefinder = (SensorMode) ultra.getDistanceMode();
		
		Sound.beep();
	}
	
	public static void goForward(){
		LCD.drawString("goForward", 0, 0);
		Motor.D.setSpeed(speed);
		Motor.C.setSpeed(speed);
		Motor.D.forward();
		Motor.C.backward();
		while(!isObstical()){}
		Motor.D.stop();
		Motor.C.stop();
	}
	
	public static void lookLeft(){
		LCD.drawString("LookLeft", 0, 0);
		Motor.D.setSpeed(turnSpeed);
		Motor.C.setSpeed(turnSpeed);
		Motor.D.forward();
		Motor.C.forward();
		Delay.msDelay(turnTime);
		Motor.D.stop();
		Motor.C.stop();
	}
	
	public static void lookRight(){
		LCD.drawString("lookRight", 0, 0);
		Motor.D.setSpeed(turnSpeed);
		Motor.C.setSpeed(turnSpeed);
		Motor.D.backward();
		Motor.C.backward();
		Delay.msDelay(turnTime);
		Motor.D.stop();
		Motor.C.stop();
	}
	
	public static boolean isObstical(){
		LCD.drawString("isObstical", 0, 0);
		float[] sample = new float[ultra.sampleSize()];
		distancefinder.fetchSample(sample, 0);
		return sample[0]<obsticalTolerance;
	}
	
	public static void stepBack(){
		LCD.drawString("stepBack", 0, 0);
		Motor.D.setSpeed(speed);
		Motor.C.setSpeed(speed);
		Motor.D.backward();
		Motor.C.forward();
		Delay.msDelay(knockBack);
		Motor.D.stop();
		Motor.C.stop();
	}
	
	public static void shutdown(){
		LCD.drawString("shutdown", 0, 0);
		ultra.close();
	}
	
	public static boolean isNotEscapePressed(){
		
		return Button.getButtons()!=Button.ID_ESCAPE;
	}
}