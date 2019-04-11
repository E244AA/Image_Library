import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	
	public static void main(String[] args) throws IOException {
		
		ServerSocket SERVER_SOCKET = new ServerSocket(8080);
		
		Socket client = SERVER_SOCKET.accept();
		
		DataInputStream in = new DataInputStream(client.getInputStream());
		
		// Size of byte array (Image)
		int len = in.readInt();
        byte[] data = new byte[len];

        // Read bytes
        for(int i = 0; i < len; i++){
            data[i] = in.readByte();
        }

        ByteArrayInputStream bais = new ByteArrayInputStream(data);
        
        // Get image from byte array
        BufferedImage img = ImageIO.read(bais);
        
        System.out.print(img.toString());
        
        client.close();
        SERVER_SOCKET.close();
		
	}
	
}
