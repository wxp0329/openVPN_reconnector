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

public class CopyOfPingReconnect {
	public static void main(String[] args) {
		try {
			Thread.sleep(1000 * 60);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			System.out.println("starting openVPN ...");
			BufferedReader br = new BufferedReader(new FileReader(new File(
					Driver.class.getClassLoader()
							.getResource("openVPN_path.txt").getPath())));

			String path = null;
			while ((path = br.readLine()).startsWith("#")) {
				continue;
			}
			Runtime.getRuntime().exec(path);
			br.close();
			System.out.println("started openVPN !!!");
			System.out.println("不要关闭该命令窗口，否则程序不能运行！！！");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("starting openVPN failed !!!");
		}
		try {
			Thread.sleep(1000 * 10);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		while (true) {
			
			if (!isConnected()) {
				Driver.reconnect();
			}else{
				try {
					Thread.sleep(1000 * 60 * 10);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
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
