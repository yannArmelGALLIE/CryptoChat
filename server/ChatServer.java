import java.io.*;
import java.net.*;
import java.util.*;

class ClientHandler implements Runnable {
    private Socket clientSocket;
    private PrintWriter writer;
    private String clientUsername;

    private static List<ClientHandler> clients = new ArrayList<>();

    public ClientHandler(Socket socket) {
        this.clientSocket = socket;
    }

    public void run() {
        String key = "uyfyiyuvchcgkckgckxclyclccgkcgluccgccctcucotcfufofuuvuvouvuvvo";
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            writer = new PrintWriter(clientSocket.getOutputStream(), true);

            clientUsername = reader.readLine();
            System.out.println(clientUsername + " est connecté !");

            synchronized (clients) {
                clients.add(this);
            }

            broadcast(clientUsername + " a rejoint le chat !", null);
            broadcastUserList();

            String message;
            while ((message = reader.readLine()) != null) {

                String encrypted = CryptoUtils.encrypt(message, key);
                System.out.println(clientUsername + " : " + encrypted);

                synchronized (clients) {
                    for (ClientHandler client : clients) {
                        if (client != this) {
                            String decrypted = CryptoUtils.decrypt(encrypted, key);
                            client.writer.println(clientUsername + " : " + decrypted);
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            synchronized (clients) {
                clients.remove(this);

                broadcast("USER_LEFT:" + clientUsername, this);
            broadcastUserList();
            }
            try {
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(clientUsername + " s'est déconnecté.");
        }
    }

    private void broadcast(String message, ClientHandler excludeUser) {
        synchronized (clients) {
            for (ClientHandler client : clients) {
                if (client != excludeUser) {
                    client.writer.println(message);
                }
            }
        }
    }

    // 🔸 Diffuse la liste complète des utilisateurs connectés
    private void broadcastUserList() {
        synchronized (clients) {
            StringBuilder userList = new StringBuilder();
            for (ClientHandler client : clients) {
                userList.append(client.clientUsername).append(",");
            }
            if (!clients.isEmpty()) {
                userList.setLength(userList.length() - 1); // Supprime la dernière virgule
            }
            for (ClientHandler client : clients) {
                client.writer.println("USER_LIST:" + userList);
            }
        }
    }
}

public class ChatServer {
    public static void main(String[] args) throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(1234)) {
            System.out.println("Le serveur est en attente sur le port 1234...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                new Thread(new ClientHandler(clientSocket)).start();
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
            encrypHexa += String.format("%02x", (byte) temp);
            keyItr++;
            if (keyItr >= key.length()) {
                keyItr = 0;
            }
        }
        return encrypHexa;
    }

    public static String decrypt(String message, String key) {
        String hexToDeci = "";
        for (int i = 0; i < message.length() - 1; i += 2) {
            String output = message.substring(i, (i + 2));
            int decimal = Integer.parseInt(output, 16);
            hexToDeci += (char) decimal;
        }

        String decryptText = "";
        int keyItr = 0;
        for (int i = 0; i < hexToDeci.length(); i++) {
            int temp = hexToDeci.charAt(i) ^ key.charAt(keyItr);
            decryptText += (char) temp;
            keyItr++;
            if (keyItr >= key.length()) {
                keyItr = 0;
            }
        }
        return decryptText;
    }
}
