package com.tedaneblake.echoServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
	
	public static void main(String[] args) {
		 try(ServerSocket serverSocket = new ServerSocket(5000)){
			 Socket socket = serverSocket.accept();
			 System.out.println("Client accepted");
			 // wrap input with BufferedReader			 
			 BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			 // wrap out with PrintWriter
			 // PrintWriter 2nd parameter specifies whether or not to automatically flush the output stream the PrintWriter is using
			 PrintWriter output  = new PrintWriter(socket.getOutputStream(), true);
			 
			 //read data from client
			 
			 //we want the server to stay alive until the client doesn't need anymore
			 //create an infinite loop until client sends exit
			 while(true) {
				 String echoString = input.readLine(); 
				 if(echoString.equals("exit")) {
					 break;
				 }
				 output.println("Echo from server: " + echoString);
			 }
			 
		 }catch (IOException e) {
			 System.out.println("Server exception "+ e.getMessage());
			 e.printStackTrace();
		 }
	}
}
