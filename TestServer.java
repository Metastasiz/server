import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class TestServer {
    public class ServerThread extends Thread{
        private Socket socket;
        private Random rand;
        public ServerThread(Socket socket, Random rand){
            super();
            this.socket = socket;
            this.rand = rand;
        }
        public void run(){
            try {
                System.out.println("IP other side: " + socket.getInetAddress());
                System.out.println("Port other side: " + socket.getPort());
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(socket.getOutputStream(),true);
                int a = rand.nextInt(10000);
                int b = rand.nextInt(10000);
                System.out.println("a = %d, b = %d, ab = %d".formatted(a,b,a*b));
                out.println(a);
                out.println(b);
                String resp = in.readLine();
                try{
                    int r = Integer.parseInt(resp);
                    if (r == a*b){
                        out.println("Correct");
                        System.out.println("Correct");
                    }
                    else {
                        out.println("Wrong");
                        System.out.println("Wrong");
                    }
                }catch (NumberFormatException e){
                    System.out.println("Wrong format");
                }
                /*
                out.println("this is m server");
                String first = in.readLine();
                String second = in.readLine();
                out.println(first+second);
                String resp = first + second;
                System.out.println(resp);

                 */

            } catch (IOException e) {
                System.out.println("something 1");
            }
            try {
                socket.close();
            } catch (IOException e) {
                System.out.println("something 2");
            }
        }
    }
    public void listenSocket(int port){
        Random rand = new Random(22);
        ServerSocket server = null;
        Socket client = null;
        try{
            server = new ServerSocket(port);
        }
        catch (IOException e) {
            System.out.println("Could not listen");
            System.exit(-1);
        }
        System.out.println("Server listens on port: " + server.getLocalPort());

        while(true){
            try{
                client = server.accept();
            }
            catch (IOException e){
                System.out.println("Accept failed");
                System.exit(-1);
            }
            (new ServerThread(client,rand)).start();
        }
    }
    public static void main(String[] arg){
        TestServer server = new TestServer();
        server.listenSocket(44444);
    }
}
