import java.util.*; 
import java.io.*;
import java.util.Scanner;
import java.net.*;
import java.lang.*;
  
public class ConcurrentClient extends Thread { 

    static double TurnAroundTime;
    static int selection = 0;

    public static void main(String[] args) 
    {     
        int port;
        String hostname;
        long StartTime = System.nanoTime();
        int clientsToSpawn;
        Scanner keyboardinput = new Scanner(System.in);

        System.out.print("Enter network address: ");
        hostname = keyboardinput.nextLine();

        System.out.print("Enter port number: ");
        port = keyboardinput.nextInt();
	
	while(selection != 7) {

        System.out.print("Select an option:\n" + "1: Retreivew date and time.\n" + "2: Retrieve uptime.\n"
                        + "3: Retrieve memory use.\n" + "4: Retrieve netstat.\n" + "5: Retrieve current users\n"
                        + "6: Retrieve running rocesses.\n" + "7: Quit.\n");

        selection = keyboardinput.nextInt();

	if(selection == 7) {
	        clientsToSpawn = 1;
	
	}
	else {
	System.out.print("Select amount of clients to spawn: ");
        clientsToSpawn = keyboardinput.nextInt();
	}

        Thread[] clientArray = new Thread[clientsToSpawn];
        

        for(int i = 0; i < clientsToSpawn; i++) {
                clientArray[i] = new Thread() {
                double thisClientStartTime = System.currentTimeMillis();
                
                    public void start() {
         
                        try (Socket socket = new Socket(hostname, port)) {
                        double thisClientStartTime = System.currentTimeMillis();
                        InputStream input = socket.getInputStream();
                        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                        OutputStream output = socket.getOutputStream();
                        PrintWriter writer = new PrintWriter(output, true);

                     	writer.println(selection);
			
			String s = reader.readLine();
			int r = Integer.parseInt(s);

			for(int t = 0; t < r; t++) {
				s = reader.readLine();
				System.out.println(s);
			}
		      
                        double elapsedTime = System.currentTimeMillis() - thisClientStartTime;

                        System.out.println("Client thread turnaround time: " + elapsedTime + "ms\n");
                        TurnAroundTime = TurnAroundTime + elapsedTime;
			}
                        catch(IOException ex) {
                            System.out.println("I/O error: " + ex.getMessage());              
						}catch(Exception e){
                            System.out.println(e);              
						}
					}//end start
				}; //end client array
                
                System.out.println("Client number " + (i + 1) + " has been generated\n");

		}//for loop
		
        for(int j = 0; j < clientsToSpawn; j++) {
            clientArray[j].start();
		  }
        
	if(selection == 7) System.exit(0);
        
        System.out.println("\nTotal turn-around time: " + TurnAroundTime + "ms.");
        System.out.println("Average tutn-around time " + (TurnAroundTime/clientsToSpawn) + "ms");
	}
    }
}



























































