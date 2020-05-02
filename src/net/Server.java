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

public class Server {
	//TODO. ���۾�
	private ServerSocket serverSocket = null;
	private Socket socket = null;
	private boolean isOpened = false;

	public Server() {

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

	public void communicate() {
		try {
			Socket socket = serverSocket.accept(); // Ŭ���̾�Ʈ�� ������ �㰡�Ѵ�.(Accept)
			InetAddress clientAddress = socket.getInetAddress(); // Ŭ���̾�Ʈ�� �ּҸ� �����´�.
			System.out.println(getTime() + clientAddress + " ���� Ŭ���̾�Ʈ�� �����߽��ϴ�.");
			OutputStream out = socket.getOutputStream(); // Ŭ���̾�Ʈ ������ ����Ʈ ��Ʈ���� �����´�.
			InputStream in = socket.getInputStream(); // Ŭ���̾�Ʈ ������ ����Ʈ ��Ʈ���� �Է��Ѵ�.

			PrintWriter pw = new PrintWriter(new OutputStreamWriter(out));
			BufferedReader br = new BufferedReader(new InputStreamReader(in));

			String recvStr = null; // ���� ���ڿ�
			while ((recvStr = br.readLine()) != null) {
				System.out.println(getTime() + " Ŭ���̾�Ʈ�κ��� ���� ���ڿ� : " + recvStr);
				pw.println(recvStr); // �޽����� Ŭ���̾�Ʈ���� ����
				pw.flush(); // ���۸� ���
			}

			pw.close(); // ��Ʈ�� �ݱ�
			br.close(); // ���� �ݱ�
			socket.close(); // ���� �ݱ�
		} catch (Exception e) {
			e.printStackTrace(); // ���� ó��
		}
	}

	static String getTime() {
		SimpleDateFormat f = new SimpleDateFormat("[hh:mm:ss]"); // ��¥ ���
		return f.format(new Date());
	}

}
