import java.io.*;
import java.net.*;
import java.util.*;

class ClientHandler implements Runnable {
    private Socket clientSocket;

    private static List<PrintWriter> clientOutputs = new ArrayList<>();

    public ClientHandler(Socket socket) {
        this.clientSocket = socket;
    }

    public void run() {
        String key = "uyfyiyuvchcgkckgckxclyclccgkcg lu  ccgccctcucotcfufofuuvuvouvuvvo";
        try {
            BufferedReader reader = new BufferedReader( new InputStreamReader( clientSocket.getInputStream() ) );
            PrintWriter writer = new PrintWriter( clientSocket.getOutputStream(), true );
            synchronized (clientOutputs) {
                clientOutputs.add(writer);
            }

            String clientUsername = reader.readLine();
            String message;
            System.out.println(clientUsername + " est connecté !");

            while ((message = reader.readLine()) != null) {
                String encrypted = CryptoUtils.encrypt(message, key);
                System.out.println(clientUsername + " : " + encrypted);
                synchronized (clientOutputs) {
                    for (PrintWriter writer2 : clientOutputs) {
                        String decryted = CryptoUtils.decrypt(encrypted, key);
                        writer2.println(clientUsername + " : " + decryted);
                    }
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}

public class ChatServer {
    public static void main(String[] args) throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(1234)) {
            System.out.println("Le serveur est en attente sur le port 1234...");

            while (true) {
                Socket clienSocket = serverSocket.accept();
                new Thread( new ClientHandler(clienSocket) ).start();
            }
        }
    }
}

class CryptoUtils {

    public static String encrypt(String message, String key) {
        String encrypHexa = "";
        int keyItr = 0;

        for (int i = 0; i < message.length(); i++) {
            int temp = message.charAt(i) ^ key.charAt(keyItr);

            encrypHexa += String.format("%02x", (byte)temp);
            keyItr++;

            if (keyItr >= key.length()) {
                keyItr = 0;
            }
        }

        return encrypHexa;
    }

    public static String decrypt(String message, String key) {
        String hexToDeci = "";
        for (int i = 0; i < message.length() -1; i+=2) {
            String output = message.substring(i, (i+2));
            int decimal = Integer.parseInt(output, 16);
            hexToDeci += (char)decimal;
        }

        String decryptText = "";
        int keyItr = 0;
        for (int i = 0; i < hexToDeci.length(); i++) {
            int temp = hexToDeci.charAt(i) ^ key.charAt(keyItr);

            decryptText += (char)temp;
            keyItr++;

            if (keyItr >= key.length()) {
                keyItr = 0;
            }
        }

        return decryptText;
    }
}