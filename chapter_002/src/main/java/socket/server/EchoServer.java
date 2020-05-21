package socket.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.StringJoiner;

/**
 * 0. Что такое Socket? [#281988]
 * @since 20.05.2020
 * @author Kirill Asmanov
 */
public class EchoServer {

    private static final Logger LOG = LoggerFactory.getLogger(EchoServer.class.getName());
    /**
     * Handle the input from client
     * @param in input stream
     * @return input as a string
     * @throws IOException
     */
    public String inputHandler(BufferedReader in) throws IOException {
        StringJoiner result = new StringJoiner(System.lineSeparator());
        while (in.ready()) {
            result.add(in.readLine());
        }
        return result.toString();
    }

    /**
     * Handle output from server
     * @param response response from client
     * @param out output stream
     * @throws IOException
     */
    public void outputHandler(String response, OutputStream out) throws IOException {
        if (response == null) {
            out.write("HTTP/1.1 400 ERROR\r\n\r\n".getBytes());
            out.write("Cannot handle empty response".getBytes());
            LOG.warn("Cannot handle empty response");
            return;
        }
        out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
        out.write(getMessage(response).getBytes());
        LOG.info("Response send");
    }

    /**
     * Checks is the server should be turned off
     * @param response response from client
     * @return true if it's should
     */
    public boolean checkSwitchCommand(String response) {
        return !getMessage(response).equalsIgnoreCase("EXIT");
    }

    /**
     * Extract message from client response
     * @param response response from client
     * @return message
     */
    private String getMessage(String response) {
        return Arrays.stream(response.split(System.lineSeparator()))
                .filter(s -> s.contains("GET"))
                .map(s -> s.split("\\s"))
                .flatMap(Arrays::stream)
                .filter(s -> s.contains("/?msg="))
                .map(s -> s.replace("/?msg=", ""))
                .findFirst().orElse("EMPTY_RESPONSE");
    }

    public static void main(String[] args) throws IOException {
        EchoServer echo = new EchoServer();
        try (ServerSocket server = new ServerSocket(9000)) {
            LOG.info("Server is started!");
            boolean active = true;
            while (active) {
                Socket socket = server.accept();
                LOG.info("Client connected!");
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
                    String inputResponse = echo.inputHandler(in);
                    System.out.println(inputResponse);

                    LOG.info("Get message:" + echo.getMessage(inputResponse) + System.lineSeparator());

                    echo.outputHandler(inputResponse, out);
                    active = echo.checkSwitchCommand(inputResponse);
                } catch (Exception e) {
                    LOG.error("Catch exception: ", e);
                }
            }
            LOG.info("Server stopped!");
        } catch (Exception e) {
            LOG.error("Catch exception: ", e);
        }
    }
}