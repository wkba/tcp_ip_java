import java.net.*;
import java.io.*;

public class TCPEchoClient {

  public static void main(String[] args) throws IOException {

    if ((args.length < 3) || (args.length > 4))
      throw new IllegalArgumentException("Parameter(s): <Server> <Word> <Port> <userName>");

    String server = args[0];
    String message = args[3] + ": "+ args[1];
    byte[] byteBuffer = message.getBytes();


    int servPort = Integer.parseInt(args[2]);

    Socket socket = new Socket(server, servPort);
    System.out.println("Connected to server...sending echo string");

    InputStream in = socket.getInputStream();
    OutputStream out = socket.getOutputStream();

    out.write(byteBuffer);

    int totalBytesRcvd = 0;
    int bytesRcvd;
    while (totalBytesRcvd < byteBuffer.length) {
      if ((bytesRcvd = in.read(byteBuffer, totalBytesRcvd, byteBuffer.length - totalBytesRcvd)) == -1)
        throw new SocketException("Connection close prematurely");
      totalBytesRcvd += bytesRcvd;
    }

    System.out.println(new String(byteBuffer));
    socket.close();
  }
}