package test.net;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class OutputStreamClient {
	/**
	 * サーバーポート
	 */
	private final static int SERVER_PORT = 65333;

	/**
	 * サーバホスト名
	 */
	private final static String SERVER_ADDRESS = "osamu-faith";

	public static void main(String[] args) {
		/**
		 * サーバソケット
		 */
		Socket socket = null;

		OutputStream os = null;
		try {
			socket = new Socket(SERVER_ADDRESS,SERVER_PORT);
			os = socket.getOutputStream();
			os.write(10);
			os.flush();
		} catch(IOException e) {
			e.printStackTrace();
		} finally {
			if(os != null) {
				try {
					os.close();
				} catch(IOException e) {
					e.printStackTrace();
				}
			}

			if(socket != null) {
				try {
					socket.close();
				} catch(IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

}
