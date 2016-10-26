//file name: UDPClient.java
//this program is used in client's PC, request for date from server.
//date:3/25/2014
//programmer: Fangyu Zhang
import java.io.*;
import java.net.*;

class UDPClient
{
   public static void main(String args[]) throws Exception
   {
      
    
      boolean flag=true;
      /*print a brief instruction of this program for user*/
      System.out.println("This program is used to ask data from a server.");
      System.out.println("Request format: 1. date (print data and lucky number)");
      System.out.println("                2. date-d (print date only)");
      System.out.println("                3. date-t (print time and number)");
      System.out.println("                4. date-n (print numbers only)");
      System.out.println("please enter host address,like: 127.0.0.1");
      DatagramSocket clientSocket = new DatagramSocket();
      
      BufferedReader inFromUser2 =
              new BufferedReader(new InputStreamReader(System.in));
              String Address = inFromUser2.readLine();
              InetAddress host= InetAddress.getByName(Address);
      while(flag)
      {
    	  byte[] sendData = new byte[1024];
          byte[] receiveData = new byte[1024];
          BufferedReader inFromUser =
          new BufferedReader(new InputStreamReader(System.in));
          //String Address = inFromUser.readLine();
        // InetAddress host= InetAddress.getByName(Address);
    	  System.out.println("Enter your demand please.");
    	  String send=inFromUser.readLine();
    	  sendData=send.getBytes();
	      DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, host, 5432);
	     // System.out.println(host);
	      clientSocket.send(sendPacket);
	      DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
	      clientSocket.receive(receivePacket);
	      String recieve = new String(receivePacket.getData());
	      if(send.length()>4)
	      {
	    	  /*if send="date-d", print date only*/
		      if(send.substring(0, 4).equalsIgnoreCase("date")&&send.indexOf("-d")!=-1)
		      {
		    	  String date;
		    	  date=recieve.substring(0, 10);
		    	  String year=recieve.substring(20,24);
		    	  System.out.println("Today is: "+ date+", "+year);
		    	  
		    	  
		      }
		      /*if send="date-t", print time and lucky numbers */
		      else if(send.substring(0, 4).equalsIgnoreCase("date")&&send.indexOf("-t")!=-1)
		      {
		    	  String time;
		    	  String luckyNumber;
		    	  time=recieve.substring(11, 19);
		    	  luckyNumber=recieve.substring(25);
		    	  System.out.println(time+" Your lucky numbers are "+luckyNumber);
		    	 
		      }
		      /*if send="date-t", only print lucky numbers */
		      else if(send.substring(0, 4).equalsIgnoreCase("date")&&send.indexOf("-n")!=-1)
		      {
		    	  String luckyNumber;
		    	  luckyNumber=recieve.substring(25);
		    	  System.out.println("Your lucky numbers are "+luckyNumber);
		    	 
		      }
	      }
	      /*if send="date", print date and lucky numbers */
	      else if(send.equalsIgnoreCase("date"))
	      {
	    	  String date=recieve.substring(0,10);
	    	  String year=recieve.substring(20,24);
	    	  String luckyNumber=recieve.substring(25);
	    	  System.out.println("Today is: "+date+", "+year+" Your lucky numbers are "+luckyNumber);
	      }
	      /*user didn't give the right demand */
	      else
	      {
	    	  System.out.println("sorry, your demand is wrong");
	      }
	      while(true)
	      {
		      System.out.println("Do you want to continue? y/n");
		      
		      InputStreamReader converter = new InputStreamReader(System.in);
		      BufferedReader in = new BufferedReader(converter);
		      String command = in.readLine();
		      
		      char ch = command.charAt(0);
		 
		      if(ch=='n')
		      {
		    	  flag=false;
		      	  break;
	      		}
		      else if(ch=='y')
		      {
		          flag=true;
		          break;
		      }
		      else
		      {
		    	  System.out.println("please enter again! ");
		      }
	      
	    	  
	      }
	    	  
      }
      clientSocket.close();// user don't want to continue, close the socket
   }
} 
