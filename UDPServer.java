//file name: UDPServer.java
//this program is used in server, response to client in UDP protocol.
//date:3/25/2014
//programmer: Fangyu Zhang
//import java.io.*;
import java.net.*;
import java.text.*;
import java.util.Calendar;
import java.util.Random;
class UDPServer
{
   public static void main(String args[]) throws Exception
      {
         DatagramSocket serverSocket = new DatagramSocket(5432);
            byte[] receiveData = new byte[1024];
            byte[] sendData = new byte[1024];
            Random regn = new Random();
            InetAddress addr = InetAddress.getLocalHost();
            System.out.println("hostAddress="+addr);
            while(true)
               {
                  DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                  serverSocket.receive(receivePacket);
                  String sentence = new String( receivePacket.getData());
                  System.out.println("RECEIVED: " + sentence);
                  InetAddress IPAddress = receivePacket.getAddress();
                  int port = receivePacket.getPort();
                  Calendar currentDate=Calendar.getInstance();
                  SimpleDateFormat formatter= new SimpleDateFormat("EEE MMM dd HH:mm:ss yyyy");
                  String sendBack=formatter.format(currentDate.getTime());
                  sendBack=sendBack+'!';
                  /*create the first 5 lucky numbers between 1-59*/
                  for(int i=0;i<5;i++)
                  {
                      int luckNumber=regn.nextInt(60);
                      /*if the number equal to 0, chose the next one*/
                      while(true){
                    	  
    	                  if(luckNumber==0)
    	                  {
    	                	  luckNumber=regn.nextInt(60);
    	                  }
    	                  else
    	                	  break;
                      }
                	  sendBack+=Integer.toString(luckNumber);
                	  sendBack+=',';
           
                  }
                  /*create the last lucky numbers between 1-35*/
                  int luckNumber=regn.nextInt(36);
                  /*if the number equal to 0, chose the next one*/
                  while(true){
                	  
	                  if(luckNumber==0)
	                  {
	                	  luckNumber=regn.nextInt(36);
	                  }
	                  else
	                	  break;
                  }
                  sendBack+=Integer.toString(luckNumber);
                  sendData=sendBack.getBytes();
                 
                  DatagramPacket sendPacket =
                  new DatagramPacket(sendData, sendData.length, IPAddress, port);
                  serverSocket.send(sendPacket);
                  /*print out what send to client*/
                  System.out.println("SEND: " +sendBack);
               }
      }
} 
