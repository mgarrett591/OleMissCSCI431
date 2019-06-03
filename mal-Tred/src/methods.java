import lejos.hardware.Button;
import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.motor.Motor;
import lejos.hardware.port.Port;
import lejos.utility.Delay;
import lejos.hardware.sensor.*;
import lejos.hardware.Sound;

public class methods {
	private final int knockBack = 1000;
	private final int turnTime = 1000;
	private final double obsticalTolerance = .30;
	private final int speed = 1000;
	private final int turnSpeed = 1000;
	private SensorMode distancefinder;
	private EV3UltrasonicSensor ultra;
	
	public methods(){
		Port port = LocalEV3.get().getPort("S4");
		ultra = new EV3UltrasonicSensor(port);
		distancefinder = (SensorMode) ultra.getDistanceMode();
		
		Sound.beep();
	}
	
	public void goForward(){
		
		Motor.D.setSpeed(speed);
		Motor.C.setSpeed(-speed);
		Motor.D.forward();
		Motor.C.forward();
		while(!isObstical()){}
		Motor.D.stop();
		Motor.C.stop();
	}
	
	public void lookLeft(){
		Motor.D.setSpeed(turnSpeed);
		Motor.C.setSpeed(-turnSpeed);
		Motor.D.forward();
		Motor.C.forward();
		Delay.msDelay(turnTime);
		Motor.D.stop();
		Motor.C.stop();
	}
	
	public void lookRight(){
		Motor.D.setSpeed(turnSpeed);
		Motor.C.setSpeed(-turnSpeed);
		Motor.D.forward();
		Motor.C.forward();
		Delay.msDelay(turnTime);
		Motor.D.stop();
		Motor.C.stop();
	}
	
	public boolean isObstical(){
		float[] sample = new float[ultra.sampleSize()];
		distancefinder.fetchSample(sample, 0);
		return sample[0]<obsticalTolerance;
	}
	
	public void stepBack(){
		
		Motor.D.setSpeed(speed);
		Motor.C.setSpeed(-speed);
		Motor.D.forward();
		Motor.C.forward();
		Delay.msDelay(knockBack);
		Motor.D.stop();
		Motor.C.stop();
	}
	
	public void shutdown(){
		ultra.close();
	}
	
	public boolean isEscapePressed(){
		
		return Button.getButtons()!=Button.ID_ESCAPE;
	}
}