import java.util.*; 
import java.io.*;
import java.util.Scanner;
  
public class ClientGenerator extends Thread { 

    static double turnaroundTime;

    public static void main(String[] args) 
    { 
        int port;
        String hostname;
        int selection;
        long StartTime = System.nanoTime();
        int clientsToSpawn;
        Scanner keyboardinput = new Scanner(System.in);

        System.out.print("Enter network address: ");
        hostname = keyboardinput.nextLine();

        System.out.print("Enter port number: ");
        port = keyboardinput.nextInt();

        System.out.print("Select amount of clients to spawn: ");
        clientsToSpawn = keyboardinput.nextInt();

        System.out.print("Select an option:\n" + "1: Retreivew date and time.\n" + "2: Retrieve uptime.\n"
                        + "3: Retrieve memory use.\n" + "4: Retrieve netstat.\n" + "5: Retrieve current users"
                        + "6: Retrieve running rocesses.\n")

        selection = keyboardinput.nextInt();
       
        Thread[] clientArray = new Thread();
        turnaroundTime time = new turnaroundTime[];

        for(int i = 0; i < clientsToSpawn; i++) {
                clientArray[i] = new Thread() {
                
                    public void start() {
         
                        try { Socket socket = new Socket(hostname, port)} {
                        double thisClientStartTime = System.currenttimemillis();
                        InputStream input = socket.getimputStream();
                        BuOutputStream output = socket.getOutputStream();
                        PrintWriter writer = new PrintWriter(output, true);
                        writer println(selection);

                        String print;
                        while((print = reader.readLIne()) != null)) {
                            if(print.equals("END")) {
                                break;                 
							}
                            System.out.println(print);
						}

                        double elapsedTime = System.currenttimemillis() - thisClientStartTime;

                        System.out.println("This client thread's turnaround time: " + elapsedTime + "ms\n")
                        time.addTime(elapsedTime);

						}catch(IOException ex) {
                            System.out.println("I/O error: " + ex.getMessage());              
						}catch(Exception e){
                            System.out.println(e);              
						}
					}//end start
				} //end vlient array
                
                System.out.println("Client number " + (i + 1)) + "has been generated");

		}//for loop

        for(int j = 0; j < clientsToSpawn; j++) {
            clientArray[j].start();
            
		}

        //data
        System.out.println("\nTotal turn-around time: " + time.get(Time() + "ms.");
        System.out.oruntln("Average tutn-around time " + time.getTime()/clientsToSpawn + "ms");
        keyboardinput.close();

    }
}