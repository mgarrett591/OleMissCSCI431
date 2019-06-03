import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;

import lejos.hardware.Bluetooth;
import lejos.hardware.motor.Motor;
import lejos.remote.nxt.NXTCommConnector;
import lejos.remote.nxt.NXTConnection;
import lejos.robotics.RegulatedMotor;
import lejos.utility.Delay;

public class EV3ReceiveClay {
	public static void main(String[] args) throws Exception {
		NXTCommConnector connector = Bluetooth.getNXTCommConnector();
		
		System.out.println("Waiting for connection ..." +Math.random());
		NXTConnection con = connector.waitForConnection(0,  NXTConnection.RAW);
		System.out.println("Connected");
		
		DataInputStream dis = con.openDataInputStream();
		DataOutputStream dos = con.openDataOutputStream();
		
		
		Motor.B.synchronizeWith(new RegulatedMotor[] {Motor.C});
		
//		Motor.B.startSynchronization();
//		Motor.B.endSynchronization();
		
		byte[] n = new byte[1];
		while (true) {
			try {
				if(dis.read(n)==-1)
					break;
			} catch (EOFException e) {
				break;
			}
			System.out.println("Read " + n[0]);
			
//forward			
			if (n[0]==3) {
				Motor.B.startSynchronization();
				
				Motor.B.setSpeed(Motor.B.getMaxSpeed());
				Motor.C.setSpeed(Motor.C.getMaxSpeed());
				Motor.B.forward();
				Motor.C.forward();
				Motor.B.endSynchronization();
				
			}
//			go right
			if (n[0]==1) {
				Motor.B.startSynchronization();

				Motor.B.setSpeed(Motor.B.getMaxSpeed()/8);
				Motor.C.setSpeed(Motor.C.getMaxSpeed()/8);
				Motor.B.backward();
				Motor.C.forward();
				
				Motor.B.endSynchronization();
			}			
//			go left
			if (n[0]==2) {
				Motor.B.startSynchronization();

				Motor.B.setSpeed(Motor.B.getMaxSpeed()/8);
				Motor.C.setSpeed(Motor.C.getMaxSpeed()/8);
				Motor.B.forward();
				Motor.C.backward();
				
				Motor.B.endSynchronization();
			}			
//			go backwards
			if (n[0]==4) {
				Motor.B.startSynchronization();

				Motor.B.setSpeed(Motor.B.getMaxSpeed()/4);
				Motor.C.setSpeed(Motor.C.getMaxSpeed()/4);
				Motor.B.backward();
				Motor.C.backward();

				Motor.B.endSynchronization();
			}		
//			stay
			if (n[0]==0) {
				
				Motor.B.startSynchronization();

				Motor.B.setSpeed(Motor.B.getMaxSpeed()/2);
				Motor.C.setSpeed(Motor.C.getMaxSpeed()/2);
				
				Motor.B.endSynchronization();
				
				Delay.msDelay(50);
				
				Motor.B.startSynchronization();

				Motor.B.stop();
				Motor.C.stop();
				
				Motor.B.endSynchronization();
			}			
			
			dos.write(n);
			dos.flush();
			
		}
		Delay.msDelay(100);
		dis.close();
		dos.close();
		con.close();
	}
}