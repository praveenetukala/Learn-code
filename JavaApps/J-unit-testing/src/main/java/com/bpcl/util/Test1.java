package com.bpcl.util;

import java.io.OutputStream;
import java.net.Socket;

public class Test1 {

	public static void main(String[] args) {
		try {
			Socket socket = null;
			OutputStream outputStream = null;

			try {
				socket = new Socket("10.10.14.24", 9004);
				outputStream = socket.getOutputStream();
			} catch (Exception e) {
				System.out.println("Socket not connected--->>>");
				e.printStackTrace();

				socket = null;
				outputStream = null;
			}

			if (socket != null && outputStream != null) {
				// Buffer Clear
				String cmd = "CQI" + "\r" + "\n";

				outputStream.write(cmd.getBytes());
				outputStream.flush();

				int bufferRead;
				String bufferOutRead = "";

				while ((bufferRead = socket.getInputStream().read()) > -1) {
					bufferOutRead = bufferOutRead + (char) bufferRead;
					System.out.println(bufferOutRead);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
