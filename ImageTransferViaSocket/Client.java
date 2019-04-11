import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
	
	public static void main(String[] args) throws UnknownHostException, IOException {
		
		Socket client_socket = new Socket("localhost", 8080);
		DataOutputStream out = new DataOutputStream(client_socket.getOutputStream());
		
		// Some image
		BufferedImage img = new BufferedImage(10,10,10);
		
		// Image to byte array
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(img, "jpg", baos);
        baos.flush();

        byte[] data = baos.toByteArray();
        baos.flush();

        // Send size of this byte array
        out.writeInt(data.length);
        
        // Send bytes
        for(int i = 0; i < data.length; i++){
            out.writeByte(data[i]);
        }
        
        client_socket.close();
		
	}
	
}
