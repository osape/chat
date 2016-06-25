package test.net.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import test.net.DataTransfer;

/**
 * 10回クライアントからのリクエストを受け付ける
 * 受信したデータをクライアントに送信する
 * @author Osamu Takahashi
 *
 */
public class OutputServerThread extends Thread {
	/**
	 * サーバーポート
	 */
	private final int SERVER_PORT;

	/**
	 * データトランスファーオブジェクト
	 */
	private DataTransfer dt;

	/**
	 * サーバーポートを設定する
	 * @param sERVER_PORT サーバーポート
	 */
	public OutputServerThread(int sERVER_PORT,DataTransfer dt) {
		super();
		SERVER_PORT = sERVER_PORT;
		this.dt = dt;
	}

	/**
	 * スレッドを開始
	 */
	@Override
	public void run() {
		ServerSocket serverSocket = null;
		Socket socket = null;
		InputStream is = null;
		OutputStream os = null;
		try {
			serverSocket = new ServerSocket(SERVER_PORT);
			socket = serverSocket.accept();
			os = socket.getOutputStream();

			for(int i = 0;i < 10;i++) {
				byte[] buf = dt.get();
				os.write(buf);
				System.out.println("クライアントにデータを送信 : " + buf[0]);
				sleep(5000);
			}
		} catch(IOException e) {
			e.printStackTrace();
		} catch(InterruptedException e) {
			e.printStackTrace();
		} finally {
			if(is != null) {
				try {
					is.close();
				} catch(IOException e) {
					e.printStackTrace();
				}
			}

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
