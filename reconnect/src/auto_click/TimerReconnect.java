package auto_click;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

class TimerReconnect {
	Timer timer;

	public TimerReconnect(int time) {
		timer = new Timer();
		try {
			Thread.sleep(1000 * 1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			System.out.println("starting openVPN ...");
			Runtime.getRuntime().exec("E:\\OpenVPN\\bin\\openvpn-gui.exe");
			System.out.println("started openVPN !!!");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("starting openVPN failed !!!");
		}
		timer.schedule(new TimerTaskTest01(), 10 * 1000, time * 1000);
	}

	public static void main(String[] args) {
		System.out.println("timer begin....");
		new TimerReconnect(30);
	}
}

class TimerTaskTest01 extends TimerTask {

	public void run() {
		
		Driver.reconnect();
	}
}