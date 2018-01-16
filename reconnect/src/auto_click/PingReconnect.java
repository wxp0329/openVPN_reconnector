package auto_click;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PingReconnect {
	public static void main(String[] args) {
		System.out.println("请不要关闭该窗口，否则程序不生效，最小化即可！！");
		Driver.sleep_sec(60);		
		while (true) {
			if (!isConnected()) {
				Driver.killPidReconnect();
			} else {
				Driver.sleep_sec(60);
			}
			Driver.sleep_sec(60);
		}
	}

	public static boolean isConnected() {
		Runtime runtime = Runtime.getRuntime();
		try {
			Process process = runtime.exec("ping " + "www.baidu.com");
			InputStream is = process.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			String line = null;
			StringBuffer sb = new StringBuffer();
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
			is.close();
			isr.close();
			br.close();
			// Date d = new Date();
			// String s = null;
			// DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			// s = sdf.format(d);
			if (null != sb && !sb.toString().equals("")) {
				if (sb.toString().indexOf("TTL") > 0) {
					// 网络畅通
					// System.out.println(s+" ping is connected ...");
					return true;
				} else {
					// System.out.println(s+" ping is not connected !!!");
					return false;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
