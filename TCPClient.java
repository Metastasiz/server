import java.io.BufferedReader;
        import java.io.IOException;
        import java.io.InputStreamReader;
        import java.io.PrintWriter;
        import java.net.InetAddress;
        import java.net.Socket;
        import java.net.UnknownHostException;


public class TCPClient {
    public static void main(String args[]) throws UnknownHostException {

        Socket socket = null;
        PrintWriter out = null;
        BufferedReader in = null;
        String address = "192.168.0.214";
        int port = 44444;
        try {
            socket = new Socket(address, port);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        }
        catch (UnknownHostException e) {
            System.out.println("Unknown host");
            System.exit(-1);
        }
        catch (IOException e) {
            System.out.println("No I/O");
            System.exit(-1);
        }
        try {
            String a = in.readLine();
            int a1 = Integer.parseInt(a);
            System.out.println("first = " + a);
            String b = in.readLine();
            int b1 = Integer.parseInt(b);
            System.out.println("first = " + b);
            out.println(a1*b1);
            System.out.println(in.readLine());
            /*
            System.out.println(in.readLine() + " OK 1");
            out.println("pepe");
            out.println("ga");
            System.out.println(in.readLine() + " OK 2");
             */
        }
        catch (IOException e) {
            System.out.println("Error during communication");
            System.exit(-1);
        }
        try {
            socket.close();
        }
        catch (IOException e) {
            System.out.println("Cannot close the socket");
            System.exit(-1);
        }
    }
}
