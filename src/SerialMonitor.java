import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;

public class SerialMonitor {

	static SerialPort serialPort1;
	static SerialPort serialPort2;

	public static void main(String[] args) {
		serialPort1 = new SerialPort("COM3");
		serialPort2 = new SerialPort("COM12");
		try {
			serialPort1.openPort();
			serialPort1.setParams(9600, 8, 1, 0);
			serialPort1.addEventListener(new SerialPortReader1());

			serialPort2.openPort();
			serialPort2.setParams(9600, 8, 1, 0);
			serialPort2.addEventListener(new SerialPortReader2());
		} catch (SerialPortException ex) {
			ex.printStackTrace();
		}

		GUISwing.View();

	}

	// COM3 -> COM12 にデータを流す
	static class SerialPortReader1 implements SerialPortEventListener {
		public void serialEvent(SerialPortEvent event) {
			try {
				byte[] buffer = serialPort1.readBytes();
				System.out.println(bin2hex(buffer));
				serialPort2.writeBytes(buffer);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	// COM12 -> COM3 にデータを流す
	static class SerialPortReader2 implements SerialPortEventListener {
		public void serialEvent(SerialPortEvent event) {
			try {
				byte buffer[] = serialPort2.readBytes();
				serialPort1.writeBytes(buffer);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	static String bin2hex(byte[] data) {
		StringBuffer sb = new StringBuffer();
		for (byte b : data) {
			String s = Integer.toHexString(0xff & b);
			if (s.length() == 1) sb.append("0");
			sb.append(s);
		}
		return sb.toString();
	}
}