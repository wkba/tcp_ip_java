import java.net.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.*;

public class TCPEchoServer {

  private static final int BUFSIZE = 100;

  public static void main(String[] args) throws IOException {

    String content = "";

    if (args.length != 1)
      throw new IllegalArgumentException("Parameter(s): <Port>");
    int servPort = Integer.parseInt(args[0]);

    ServerSocket servSock = new ServerSocket(servPort);

    int recvMsgSize;
    byte[] byteBuffer = new byte[BUFSIZE];

    for (;;) {
      Socket clntSock = servSock.accept();     // Get client connection

      System.out.println("Handling client at " + clntSock.getInetAddress().getHostAddress() + " on port " + clntSock.getPort());
      InputStream in = clntSock.getInputStream();
      OutputStream out = clntSock.getOutputStream();

      while ((recvMsgSize = in.read(byteBuffer)) != -1){
        out.write(byteBuffer, 0, recvMsgSize);
	System.out.println("Received: " + new String(byteBuffer));
        content = content + (new String(byteBuffer));
      }
      clntSock.close();

      // File file = new File("C:\\Users\\is0288hi\\Desktop\\output.txt");
      // FileWriter filewriter = new FileWriter(file);
      // filewriter.write(content);
      // filewriter.close();
    }
  }
}
