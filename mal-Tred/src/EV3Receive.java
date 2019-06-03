import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import lejos.hardware.Bluetooth;
import lejos.remote.nxt.NXTCommConnector;
import lejos.remote.nxt.NXTConnection;
import lejos.utility.Delay;

public class EV3Receive { // This is the receiver

public static void main(String[] args) throws Exception {
	NXTCommConnector connector =
	Bluetooth.getNXTCommConnector();

	System.out.println("Waiting for connection ...");
	NXTConnection con = connector.waitForConnection(0, NXTConnection.RAW);
	System.out.println("Connected");
	
	DataInputStream dis = con.openDataInputStream();
	DataOutputStream dos =con.openDataOutputStream();

	byte[] n = new byte[8];
	while(true)
	{
		try{
		if (dis.read(n) == -1)
	break;
	} catch (EOFException e)
	{
		break;
	}
System.out.println("Read " + n[0]+n[1]+n[2]+n[3]+ n[4]+n[5]+ n[6]+n[7]);
dos.write(n);
dos.flush();
}

Delay.msDelay(1000);

dis.close();
dos.close();
con.close();

}
}