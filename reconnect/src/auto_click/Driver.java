package auto_click;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.InputEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Driver {
	public static void main(String[] args) {
		killPidReconnect();
	}

	public static void killPidReconnect() {
		Robot robot = null;
		try {
			robot = new Robot();
		} catch (AWTException e) {
			e.printStackTrace();
		}
		ArrayList<String> pids = My_Process_task.getPid();
		if (pids.size() == 0) {
			openvpnRestart();
			sleep_sec(20);
		} else {
			My_Process_task.killPid(pids);
			sleep_sec(20);
			openvpnRestart();
			sleep_sec(20);
		}
		String keyImagePath_b = Driver.class.getClassLoader()
				.getResource("pics/disconnect_btn.png").getPath();
		int[] x_y_b = getCenterXY(keyImagePath_b);
		clickNum(x_y_b, robot, 2);
		sleep_sec(5);
		clickNum(new int[] { 0, 0 }, robot, 0);

	}

	public static void sleep_sec(int sec) {
		try {
			Thread.sleep(1000 * sec);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void openvpnRestart() {
		try {
			BufferedReader br = new BufferedReader(new FileReader(new File(
					Driver.class.getClassLoader()
							.getResource("openVPN_path.txt").getPath())));

			String path = null;
			while ((path = br.readLine()).startsWith("#")) {
				continue;
			}
			Runtime.getRuntime().exec(path);
			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
		}
	}

	public static void reconnect() {

		Robot robot = null;
		try {
			robot = new Robot();
		} catch (AWTException e) {
			e.printStackTrace();
		}

		String keyImagePath = Driver.class.getClassLoader()
				.getResource("pics/disconnect_btn.png").getPath();
		int[] x_y = getCenterXY(keyImagePath);

		String keyImagePath_b = Driver.class.getClassLoader()
				.getResource("pics/broken_btn.png").getPath();
		int[] x_y_b = getCenterXY(keyImagePath_b);

		int[] x_y_real = x_y[0] != 0 ? x_y : x_y_b;
		if (x_y_real[0] != 0) {
			clickNum(x_y_real, robot, 2);
		}

		// ####################################################
		robot.delay(5000);
		// 点击reconnect按钮
		String keyImagePath1 = Driver.class.getClassLoader()
				.getResource("pics/reconnect_btn.png").getPath();
		;
		int[] x_y1 = getCenterXY(keyImagePath1);
		if (x_y1[0] != 0) {
			clickNum(x_y1, robot, 2);
		} else {
			String keyImagePath2 = Driver.class.getClassLoader()
					.getResource("pics/task_bar_btn.png").getPath();
			;
			int[] x_y2 = getCenterXY(keyImagePath2);
			String keyImagePath3 = Driver.class.getClassLoader()
					.getResource("pics/task_bar_2_btn.png").getPath();
			;
			int[] x_y3 = getCenterXY(keyImagePath3);
			int[] x_y_real1 = x_y2[0] != 0 ? x_y2 : x_y3;
			clickNum(x_y_real1, robot, 2);
		}
	}

	public static void clickNum(int[] x_y, Robot robot, int num) {
		robot.mouseMove(x_y[0], x_y[1]);
		for (int i = 0; i < num; i++) {
			// 模拟鼠标按下左键
			robot.delay(200);
			robot.mousePress(InputEvent.BUTTON1_MASK);
			robot.delay(200);
			// 模拟鼠标松开左键
			robot.mouseRelease(InputEvent.BUTTON1_MASK);
		}
	}

	public static int[] getCenterXY(String keyImagePath) {
		FindKeyImageCenterXY centerXY = new FindKeyImageCenterXY(keyImagePath);
		int[] x_y = centerXY.getCenterXY();
		return x_y;
	}

}
