package socket.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    /**
     * 0. Что такое Socket? [#281988]
     * @since 20.05.2020
     * @author Kirill Asmanov
     */
    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(9000)) {
            boolean active = true;
            while (active) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    String str = "-";
                    while (!(str).isEmpty()) {
                        str = in.readLine();
                        System.out.println(str);
                        if (str.startsWith("GET") && str.contains("?msg=Bye")) {
                            active = false;
                        }
                    }
                    out.write("HTTP/1.1 200 OK\r\n\\".getBytes());
                }
            }
        }
    }
}