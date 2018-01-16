package auto_click;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class My_Process_task {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}

	static void killPid(ArrayList<String> pids) {
		for (String s : pids) {
			try {
				Runtime.getRuntime().exec("taskkill /F /PID " + s);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	static ArrayList<String> getPid() {
		ArrayList<String> pids = new ArrayList<String>();
		String cmd = "tasklist  /nh";
		try {
			Runtime runtime = Runtime.getRuntime();
			Process process = runtime.exec(cmd);
			BufferedReader br = new BufferedReader(new InputStreamReader(
					process.getInputStream()));
			String line = null;
			while ((line = br.readLine()) != null) {
				if (line.indexOf("openvpn") != -1) {
					String pid = line.split("\\s+")[1];
					pids.add(pid);

				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return pids;
	}
}
