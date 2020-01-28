import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TCPEchoServer {

    public static final int MYPORT = 6000;
    public static int BUFSIZE = 1024;

    public static void main(String[] args) {
        try {

            // Parse buffer-size
            if (args.length >= 1) {
                BUFSIZE = ArgParser.tryParse(args[0]);
            }

            // Create server socket and bind to port.
            ServerSocket socket = new ServerSocket(MYPORT);

            // Create ExecutorService with expandable thread-pool
            ExecutorService es = Executors.newCachedThreadPool();

            while (true) {
                // Accept incoming requests
                Socket incoming = socket.accept();

                // Create task to respond to client
                System.out.println("Creating thread for incoming request: " + incoming.getInetAddress() + " " + incoming.getPort());

                //TCPTransmitTask task = new TCPTransmitTask(incoming,,"Pong!");

                // DO THIS IN TASK

                // Read input
                InputStream is = incoming.getInputStream();
                byte[] buf = new byte[BUFSIZE];

                int br = is.read(buf);
                // Write input as output
                PrintWriter pw = new PrintWriter(incoming.getOutputStream());
                System.out.println(new String(buf));
                //pw.write(is.read(buf));

                // Create separate thread for the task
                //es.submit(task);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
