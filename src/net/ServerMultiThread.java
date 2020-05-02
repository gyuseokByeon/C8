package net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ServerMultiThread {
	private ServerSocket serverSocket = null;
	private Socket socket = null;
	private boolean isOpened = false;
	private boolean isStop = false;

	public ServerMultiThread() {

	}

	public void finalize() throws IOException {
		if (serverSocket != null)
			serverSocket.close();
		if (socket != null)
			socket.close();
	}

	public boolean openSocket(int port) {
		try {
			serverSocket = new ServerSocket(port);
			System.out.println(getTime() + " ������ �غ�Ǿ����ϴ�.");
			isOpened = true;
		} catch (IOException e) {
			e.printStackTrace();
		} // ��Ʈ�� ���������� ���δ�(Bind)

		return isOpened;
	}

	public void setStop(boolean isStop) {
		this.isStop = isStop;
	}

	public void communicate() {
		try {

			while (!isStop) {
				Socket socket = serverSocket.accept(); // Ŭ���̾�Ʈ�� ������ �㰡�Ѵ�.(Accept)
				InetAddress clientAddress = socket.getInetAddress(); // Ŭ���̾�Ʈ�� �ּҸ� �����´�.
				System.out.println(getTime() + clientAddress + " ���� Ŭ���̾�Ʈ�� �����߽��ϴ�.");

				Thread t = new ServerThread(socket);
				ResultInterface.setThreadID(t.getName());
				t.start();
			}

		} catch (Exception e) {
			e.printStackTrace(); // ���� ó��
		}
	}

	static String getTime() {
		SimpleDateFormat f = new SimpleDateFormat("[hh:mm:ss]"); // ��¥ ���
		return f.format(new Date());
	}

}
