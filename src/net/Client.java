package net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;

public class Client {
	//TODO. ���۾�
	private Socket socket = null;
	private BufferedReader input = null;
	private OutputStream out = null;
	private InputStream in = null;
	private PrintWriter pw = null;
	private BufferedReader br = null;
	private boolean isConnected = false;

	public Client() {

	}

	public void finalize() throws Exception {
		clearSession();
	}

	private void clearSession() throws Exception {
		isConnected = false;
		if (input != null) {
			input.close();
			input = null;
		}
		if (out != null) {
			out.close();
			out = null;
		}
		if (in != null) {
			in.close();
			in = null;
		}
		if (pw != null) {
			pw.close();
			pw = null;
		}
		if (br != null) {
			br.close();
			br = null;
		}
		if (socket != null) {
			socket.close();
			socket = null;
		}
	}

	// ip:localhost and port
	public boolean connect(String ip, int port) {
		try {
			socket = new Socket(ip, port);
			System.out.println("Connected");
			out = socket.getOutputStream(); // ������ �������κ��� ����� ����
			in = socket.getInputStream(); // ������ �������κ��� �Է��� ����
			pw = new PrintWriter(new OutputStreamWriter(out)); // ��� ��Ʈ���� ��ȯ
			br = new BufferedReader(new InputStreamReader(in)); // �Է� ��Ʈ���� ��ȯ
			System.out.println("success open stream");
			isConnected = true;
		} catch (SocketException se) {
			se.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return isConnected;
	}

	public boolean sendString(String data) throws Exception {
		boolean isSuccess = false;
		if (isConnected) {
			try {
				// BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

				String myMsg = data; // ���� �޽���
				String echo = null; // �޴� �޽���
				// while ((myMsg = input.readLine()) != null) {
				// if (myMsg.equals("/q")) {
				// break; // ���� ����
				// }
				// }
				pw.println(myMsg); // PrintWriter�� �̿��Ͽ� �������� ����
				pw.flush(); // ���� ����
				echo = br.readLine(); // ������ ���۷� �޽����� �����ϸ� �̸� ����
				System.out.println("From Server: " + echo);
			} catch (Exception e) {
				e.printStackTrace();
				clearSession();
			}
		}
		return isSuccess;
	}
}