import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.motor.Motor;
import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.hardware.sensor.SensorMode;
import lejos.robotics.RegulatedMotor;
import lejos.utility.Delay;
import lejos.hardware.lcd.LCD;

public class mal {
 
	
	
	public static final int knockBack = 1250;
	public static final int turnTime = 1000;
	public static final double obsticalTolerance = .2;
	public static final double collisionTolerance = 0;
	public static RegulatedMotor[] syncList;
	
	
	public static final int speed = 900;
	public static final int backSpeed = 600;
	public static final int turnSpeed = 150;
	public static SensorMode distancefinder;
	public static EV3UltrasonicSensor ultra;
	public static EV3TouchSensor leftBumper;
	public static float[] leftTsample;
	public static EV3TouchSensor rightBumper;
	public static float[] rightTsample;
	public static SensorMode touchproviderRight;
	public static SensorMode touchproviderLeft;
	public static final int antiDrift = 22;
	
	public static void setup(){
		syncList = new RegulatedMotor[1];
		syncList[0] = Motor.D;
		Port SonicPort = LocalEV3.get().getPort("S4");
		ultra = new EV3UltrasonicSensor(SonicPort);
		
		Port leftPort = LocalEV3.get().getPort("S3");
		leftBumper = new EV3TouchSensor(leftPort);
		touchproviderLeft = (SensorMode) leftBumper.getTouchMode();
		leftTsample = new float[leftBumper.sampleSize()];
		
		Port rightPort = LocalEV3.get().getPort("S2");
		rightBumper = new EV3TouchSensor(rightPort);
		touchproviderRight = (SensorMode) rightBumper.getTouchMode();
		rightTsample = new float[rightBumper.sampleSize()];
		
		
		
		distancefinder = (SensorMode) ultra.getDistanceMode();
		
		Sound.beep();
	}
	
	public static void main(String[] args){
		
		setup();
		
		
		while(isNotEscapePressed()){
			
			goForward();
			
			if(isNotEscapePressed()){
				stepBack();
				lookRight();
			}
			if(isObstical()&&isNotEscapePressed()){
				lookLeft();
				lookLeft();
			}
				
			if(isObstical()&&isNotEscapePressed()){
				lookLeft();
			}
			if(isNotEscapePressed()){
				Delay.msDelay(1000);
			}
			
		}
		
		
		shutdown();
	}
	
	public static void goForward(){
		
		LCD.drawString("goForward", 0, 0);
		Motor.D.setSpeed(speed);
		Motor.C.setSpeed(speed+antiDrift);
		Motor.C.synchronizeWith(syncList);
		Motor.C.startSynchronization();
		Motor.D.forward();
		Motor.C.backward();
		Motor.C.endSynchronization();
		//int zig = 0;
		while(!isObstical()&&isNotEscapePressed()){
		//	zig++;
		//	if(zig>11)
		//		zig=-5;
		//	Motor.D.setSpeed(speed+zig);
		//	Motor.C.setSpeed(speed+zig+antiDrift);
		}
		Motor.C.synchronizeWith(syncList);
		Motor.C.startSynchronization();
		Motor.D.stop();
		Motor.C.stop();
		Motor.C.endSynchronization();
	}
	
	public static void lookLeft(){
		LCD.drawString("LookLeft", 0, 0);
		
		Motor.D.setSpeed(turnSpeed);
		Motor.C.setSpeed(turnSpeed+antiDrift);
		Motor.C.synchronizeWith(syncList);
		Motor.C.startSynchronization();
		Motor.D.forward();
		Motor.C.forward();
		Motor.C.endSynchronization();
		Delay.msDelay(turnTime);
		Motor.C.synchronizeWith(syncList);
		Motor.C.startSynchronization();
		Motor.D.stop();
		Motor.C.stop();
		Motor.C.endSynchronization();
	}
	
	public static void lookRight(){
		LCD.drawString("lookRight", 0, 0);
		Motor.D.setSpeed(turnSpeed);
		Motor.C.setSpeed(turnSpeed+antiDrift);
		
		Motor.C.synchronizeWith(syncList);
		Motor.C.startSynchronization();
		Motor.D.backward();
		Motor.C.backward();
		Motor.C.endSynchronization();
		Delay.msDelay(turnTime);
		Motor.C.synchronizeWith(syncList);
		Motor.C.startSynchronization();
		Motor.D.stop();
		Motor.C.stop();
		Motor.C.endSynchronization();
	}
	
	public static boolean isObstical(){
		touchproviderLeft.fetchSample(leftTsample, 0);
		touchproviderRight.fetchSample(rightTsample, 0);
		
		
		LCD.drawString("isObstical", 0, 0);
		float[] sample = new float[ultra.sampleSize()];
		distancefinder.fetchSample(sample, 0);
		return sample[0]<obsticalTolerance || leftTsample[0]>collisionTolerance || rightTsample[0]>collisionTolerance;
	}
	
	public static void stepBack(){
		LCD.drawString("stepBack", 0, 0);
		Motor.D.setSpeed(backSpeed);
		Motor.C.setSpeed(backSpeed+antiDrift);
		Motor.C.synchronizeWith(syncList);
		Motor.C.startSynchronization();
		Motor.D.backward();
		Motor.C.forward();
		Motor.C.endSynchronization();
		Delay.msDelay(knockBack);
		Motor.C.synchronizeWith(syncList);
		Motor.C.startSynchronization();
		Motor.D.stop();
		Motor.C.stop();
		Motor.C.endSynchronization();
	}
	
	public static void shutdown(){
		LCD.drawString("shutdown", 0, 0);
		ultra.close();
		leftBumper.close();
		rightBumper.close();
	}
	
	public static boolean isNotEscapePressed(){
		
		return Button.getButtons()!=Button.ID_ESCAPE;
	}
}
