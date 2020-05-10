package GUI.sensorSuite2.src;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.util.Random;

public class Sensors {

    private static Random rand = new Random();
    
    // Port for server to listen on
    private static int port = 9876;
    
    public static void main(String[] theArgs) throws IOException {
    	
    	// Try to create an open socket
    	try (var listener = new ServerSocket(port)) {
				
			System.out.println("Sensors Online");
				
			// Continually run the server
			while (true) {
				
				// Try to accept a connection to the server
				try (var socket = listener.accept()) {
					
					// Send the data to the connected client
					var out = new PrintWriter(socket.getOutputStream(), true);
					out.println(getWindSpeed() + " " + getWindDirection() + " "
								+ getTemperature() + " " + getHumidity() + " " + getBar() + " " + getRain());
				}
				catch (IOException e){
					System.exit(1);
				}
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
    	
    	
    }

    public static int getWindSpeed() {
        return rand.nextInt(30) + 10;
    }

    public static String getWindDirection() {
        int dir = rand.nextInt(8);
        switch (dir) {
            case 0:
                return "North";
            case 1:
                return "East";
            case 2:
                return "South";
            case 3: 
                return "West";
            case 4:
                return "Northeast";
            case 5:
                return "Northwest";
            case 6:
                return "Southeast";
            default:
                return "Southwest";
        }
    }

    public static int getTemperature() {
        return rand.nextInt(50) + 30;
    }

    public static int getHumidity() {
        return rand.nextInt(30) + 20;
    }

    public static int getBar() {
        return rand.nextInt(100);
    }
    
    public static int getRain() {
    	return rand.nextInt(2);
    }

}
