import lejos.hardware.motor.Motor;
import lejos.utility.Delay;
import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.hardware.Sound;

public class TestMotor {
	public static void main(String[] args){
		Motor.A.setSpeed(100); // degrees/sec
		Motor.A.forward();
		Delay.msDelay(4000);
		Motor.A.stop();
		Delay.msDelay(2000);
		// Motor.A.rotate(360);
		Motor.A.rotate(360, true);
		// Motor.A.rotateTo(0);
		
		while(Motor.A.isMoving()) {
		// Thread.yield();
		Sound.beep();
		Delay.msDelay(1000);
		}
	
		int angle = Motor.A.getTachoCount();
		LCD.drawInt(angle, 0, 0);
		Delay.msDelay(4000);
		
		while (Button.getButtons() != Button.ID_ESCAPE) {
		}
	}
}
