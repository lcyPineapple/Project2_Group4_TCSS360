package GUI.sensorSuite2.src;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ConnectException;
import java.net.Socket;
import java.util.Scanner;

/**
 * A Simple Class that opens the correct input file for the Envoy Data.
 * @author Nicol
 *
 */
public class EnvoyDataIO {
	
	private Boolean myTestBoolean;
	/**
	 * @param theFilePath
	 */
	public EnvoyDataIO() {
		myTestBoolean = false;

	}
	
	/**
	 * gets the output stream to write the data to.
	 * 
	 * @param theFP String representing the correct FilePath.
	 * @return the output Stream
	 */
	public PrintStream getOutputStream(String theFP) {
		PrintStream outputFile = null;
		try { //opening output file to write data to.
			outputFile = new PrintStream(theFP);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		return outputFile;
	}
	
	/**
	 * Fills the file with data coming from the socket.
	 * @param theSocket the socket number.
	 * @param theFrequency is how much data is to be written to the output FIle.
	 * @param theTimeInterval is how long the data is gathered from circuit.
	 * @param theOutputFile is the outputFile to write to opened in main.
	 * @throws ConnectException 
	 */
	public void fillFile(int theSocket, int theFrequency,
						int theTimeInterval, PrintStream theOutputFile) throws ConnectException {
		
		if (theFrequency <= 0 ) {
			throw new IllegalArgumentException();
		} else if (theTimeInterval < 0) {
			throw new IllegalArgumentException();
		}
		
		int index = 0;		
		
		//Changes when the amount of data matches the data frequency variable
		boolean flag = true;
		
        while(flag) {
        	if (index >= theFrequency) {
        		flag = false;
        	}
        	index++;
        	
            try {                   	
                String test = "127.0.0.1";             
                var socket = new Socket(test, theSocket);
                
                //receives data from connected socket
                var in = new Scanner(socket.getInputStream());
                
                String lineOfData = in.nextLine();
                theOutputFile.append(lineOfData + "\n");                      

                in.close();
                socket.close();
                Thread.sleep(theTimeInterval);
                myTestBoolean = true;
                
            } catch (Exception e) {
            	String temp = "Connection refused: connect";
            	if (e.getLocalizedMessage().equals(temp)) {
            		throw new ConnectException();
            	}
            	e.printStackTrace();
            	System.out.println("Trouble reading data from client socket.");
            	System.exit(1);
            }

        }
	}
	
	/**
	 * @return Boolean used for testing.
	 */
	public boolean getTestBoolean() {
		return myTestBoolean;
	}

}
