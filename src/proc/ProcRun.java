package proc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ProcRun {

	public static String run(String[] cmd) throws IOException {
		//String addr = "http://www.naver.com";
		// String[] cmd = new String[] { "rundll32", "url.dll", "FileProtocolHandler",
		// addr };
		//Process process = new  ProcessBuilder(cmd).start();
		Process process = null;
		String str = null;
		BufferedReader stdOut=null;
		StringBuilder sb = new StringBuilder();
		
		try {
			// ���μ��� ������ ���Ͽ� �ܺ� ���α׷� ����
			process = new ProcessBuilder(cmd).start();

			// �ܺ� ���α׷��� ǥ����� ���� ���ۿ� ����
			stdOut = new BufferedReader(new InputStreamReader(process.getInputStream()));

			// ǥ����� ���¸� ���
			while ((str = stdOut.readLine()) != null) {
				System.out.println(str);
				sb.append(str);
			}
			
		} catch (IOException e) {
			e.printStackTrace();

		}
		finally {
			if(stdOut!=null)
				stdOut.close();
		}
		
		return sb.toString();
	}
}
