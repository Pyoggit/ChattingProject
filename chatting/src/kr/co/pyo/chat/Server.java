package kr.co.pyo.chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {

	public static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		// 1.서버소켓
		ServerSocket ss = null;
		Socket clientSocket = null;
		PrintWriter pw = null;
		BufferedReader br = null;
		try {
			ss = new ServerSocket(5007);
			// 2.클라이언트 접속을 기다린다.(접속할때까지 무한대기)
			System.out.println("서버가 클라이언트를 접속하기를 기다리는중");
			clientSocket = ss.accept();
			// 3. inputstream => BufferedReader outputstream => PrintWriter
			br = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			pw = new PrintWriter(clientSocket.getOutputStream());
			System.out.println(" 대화 송수신기 완성");
			// 4.대화를 나눈다. (프로토콜) 서버 : 읽는다,화면보여준다,입력한다,보낸다
			while (true) {
				String data = br.readLine();
				if (data.equalsIgnoreCase("quit")) {
					break;
				}
				// 2.화면 보여준다
				System.out.println("client: " + data);
				// 3.입력한다(클라이언트에 보낼 메세지)
				System.out.println("보낼 메세지 입력: ");
				String sendData = sc.nextLine();
				//보낸다
				pw.println(sendData);
				pw.flush();
			}
		} catch (IOException e) {

			e.printStackTrace();
		} finally {
			try {
				clientSocket.close();
				ss.close();
				br.close();
				pw.close();
			} catch (IOException e) {
			}
		}

		System.out.println("서버소켓종료");
	}

}