package kr.co.pyo.chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {

	public static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		Socket ss = null;
		PrintWriter pw = null;
		BufferedReader br = null;
		try {
			// 1.서버소켓을 생성한다
			ss = new Socket("localhost", 5007);
			// 2.서버소켓에서 송수신기
			pw = new PrintWriter(ss.getOutputStream());
			br = new BufferedReader(new InputStreamReader(ss.getInputStream()));
			// 3 .대화를 나눈다. (프로토콜) 클라이언트 : 보낸다 ,화면보여준다,입력한다,보낸다
			while (true) {
				//입력한다
				System.out.print("서버에 보낼 메세지: ");
				String sendData = sc.nextLine();
				//보낸다
				pw.println(sendData);
				pw.flush();
				if(sendData.equals("quit")) {
					System.out.println("클라이언트가 종료를 요청했습니다.");
					break;
				}
				//무한 대기
				String receivData = br.readLine();
				//화면보여준다.
				System.out.println("서버에 보낸 메세지: " + receivData);
			}
		} catch (Exception e) {
			System.out.println("서버가 닫혔습니다.");
		} finally {
			try {
				ss.close();
				pw.close();
				br.close();
			} catch (IOException e) {}

		}
		System.out.println("클라이언트 종료");

	}

}