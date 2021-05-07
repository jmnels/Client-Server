import java.net.*;
import java.util.*; 
import java.io.*;
import java.util.Scanner;
 
 
 public class Concurrent_server {
 
        public static void main(String[] args) {
      
            if(args.length < 1) return;

            int port = Integer.parseInt(args[0]);

            try(ServerSocket serversocket = new ServerSocket(port)) {
       
                System.out.println("Server is listening on port: " + port);

                while(true) {
        
                    Socket sock = serversocket.accept();
                    
		    
		    new S_Thread(sock).start();
		}
	    }
         catch(IOException ex) {
                System.out.println("Server exception " + ex.getMessage());
                ex.printStackTrace();
                        }
                }
 }

 class S_Thread extends Thread {
   	
	 private Socket sock;

   	 public S_Thread(Socket sock) {
	   	 this.sock = sock;
   	 }	

   	 public void run() {


                try {
                    InputStream input = sock.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(input));

                    OutputStream output = sock.getOutputStream();
                    PrintWriter writer = new PrintWriter(output, true);

                    String text;
                    String s;
		    ArrayList<String>  op = new ArrayList<String>();
                    text = reader.readLine();
                    int choice = Integer.parseInt(text);
		    int size;

                    do {
         
                        System.out.println(choice);

                        switch(choice) {
          
                            case 1:    
                                Process p = Runtime.getRuntime().exec("date");
                                BufferedReader stdinput = new BufferedReader(new InputStreamReader(p.getInputStream()));
                                while((s = stdinput.readLine()) != null) {
                                        op.add(s);
                                }
                                writer.println(op.size());
                                for(String string : op){
                                    writer.println("Date and Time: " + string);
                                    writer.flush();
                                }
                                break;
                            case 2:
                                p = Runtime.getRuntime().exec("uptime");
                                stdinput = new BufferedReader(new InputStreamReader(p.getInputStream()));
                                while((s = stdinput.readLine()) != null) {
                                        op.add(s);
                                }
                                writer.println(op.size());
                                for(String string : op){
                                    writer.println("Uptime: " + string);
                                    writer.flush();
                                }
                                break;
                            case 3:
                                p = Runtime.getRuntime().exec("vmstat");
                                stdinput = new BufferedReader(new InputStreamReader(p.getInputStream()));
                                while((s = stdinput.readLine()) != null) {
					op.add(s);
				}
				writer.println(op.size());
				for(String string : op){
                                    writer.println("Memory Useage: " + string);
				    writer.flush();
                                }
                                break;
                            case 4:
                                p = Runtime.getRuntime().exec("netstat");
                                stdinput = new BufferedReader(new InputStreamReader(p.getInputStream()));
                                while((s = stdinput.readLine()) != null) {
                                        op.add(s);
                                }
                                writer.println(op.size());
                                for(String string : op){
                                    writer.println("Netstat: " + string);
                                    writer.flush();
                                }
                                break;
                            case 5:
                                p = Runtime.getRuntime().exec("who");
                                stdinput = new BufferedReader(new InputStreamReader(p.getInputStream()));
                                while((s = stdinput.readLine()) != null) {
                                        op.add(s);
                                }
                                writer.println(op.size());
                                for(String string : op){
                                    writer.println("Current Users: " + string);
                                    writer.flush();
                                }
                                break;
                            case 6:
                                p = Runtime.getRuntime().exec("ps");
                                stdinput = new BufferedReader(new InputStreamReader(p.getInputStream()));
                                while((s = stdinput.readLine()) != null) {
                                        op.add(s);
                                }
                                writer.println(op.size());
                                for(String string : op){
                                    writer.println("Running Processes: " + string);
                                    writer.flush();
                                }
                                break;
			    case 7:
				System.out.println("Exiting..");
				this.sock.close();
				System.exit(0);	
				break;
			    default:
				writer.println("1");
				writer.println("Invalid Input");
				writer.flush();
                        }
			
                    }while((text = reader.readLine()) != null);
				
			}
		
         catch(IOException ex) {
                System.out.println("Server exception " + ex.getMessage());
                ex.printStackTrace();
			}
	 try {
	 this.sock.close();
	 }
         catch(IOException e) {
	 	e.printStackTrace();
	 }

 	 }
 }






















































































