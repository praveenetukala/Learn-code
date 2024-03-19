package com.bpcl.util;

import java.io.InputStream;
import java.net.Socket;

public class Test {

	public static void main(String[] args) {
		try {
			long readValCount = 0;
			Socket socket = null;
			InputStream inputStream = null;

			try {
				socket = new Socket("10.10.14.24", 9004);
				inputStream = socket.getInputStream();
			} catch (Exception e) {
				System.out.println("Socket not connected--->>>");
				e.printStackTrace();
			}

			if (socket != null && inputStream != null) {
				byte[] buffer = new byte[1024];
				int read;
				while (true) {
					if (readValCount < inputStream.available()) {
						while ((read = inputStream.read(buffer)) >= -1) {
							String data = new String(buffer, 0, read);

							System.out.println("Data ---->>> " + data);

							readValCount = inputStream.available();
							break;
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
