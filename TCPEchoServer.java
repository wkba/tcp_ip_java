import java.net.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.*;

public class TCPEchoServer {

  private static final int BUFSIZE = 100;

  public static void main(String[] args) throws IOException {

    String content = "message log";

    if (args.length != 1)
      throw new IllegalArgumentException("Parameter(s): <Port>");
    int servPort = Integer.parseInt(args[0]);

    ServerSocket servSock = new ServerSocket(servPort);

  
    System.out.println("");
    System.out.println("* chat start *");
    System.out.println("[name] : [message]");
    System.out.println("");

    for (;;) {
      int recvMsgSize;
      byte[] byteBuffer = new byte[BUFSIZE];
      Socket clntSock = servSock.accept();     // Get client connection

      //System.out.println("Handling client at " + clntSock.getInetAddress().getHostAddress() + " on port " + clntSock.getPort());
      InputStream in = clntSock.getInputStream();
      OutputStream out = clntSock.getOutputStream();

      while ((recvMsgSize = in.read(byteBuffer)) != -1){
        out.write(byteBuffer, 0, recvMsgSize);
	      System.out.println(new String(byteBuffer));
        content = content + (new String(byteBuffer)) + "\n";
      }
      clntSock.close();

      // For win
      //File file = new File("C:\\Users\\is0288hi\\Desktop\\output.txt");

      //For Mac
      File file = new File("/Users/wakabashi/Desktop/tcp/output.txt");
      FileWriter filewriter = new FileWriter(file);
      filewriter.write(content);
      filewriter.close();
    }
  }
}
