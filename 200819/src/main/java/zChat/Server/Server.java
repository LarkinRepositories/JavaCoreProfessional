package zChat.Server;
/*
 Добавить на серверную сторону чата логирование, с выводом информации о действиях на сервере
 (запущен DONE, произошла ошибка DONE, клиент подключился DONE, клиент прислал сообщение/команду DONE).
 */

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Server {

    private Vector<ClientHandler> clients;
    private ExecutorService executorService;
    public static final Logger LOG = LogManager.getLogger(Server.class.getName());

    public Server() throws SQLException, ClassNotFoundException {
        clients = new Vector<>();
        ServerSocket server = null;
        Socket socket = null;
        this.executorService = Executors.newCachedThreadPool();
        try {
            DbManager.connect();
            String test = DbManager.getNickNameByLoginAndPass("login1", "password4");
            System.out.println(test);
            server = new ServerSocket(8189);
            LOG.info("Server started");
            //System.out.println("Server is running!");
            while (true) {
                socket = server.accept();
                new ClientHandler(this, socket);
            }
        } catch (IOException e) {
            e.printStackTrace();
            LOG.error(e.getClass().getName() + e.getMessage());
        } finally {
            try {
                socket.close();
            } catch (IOException | NullPointerException e) {
                e.printStackTrace();
                LOG.error(e.getClass().getName() + e.getMessage());
            }
            try {
                server.close();
                LOG.info("Server shutdown");
            } catch (IOException | NullPointerException e) {
                e.printStackTrace();
                LOG.error(e.getClass().getName() + e.getMessage());
            }
            DbManager.disconnect();
            executorService.shutdown();
        }
    }

    public boolean isNicknameBusy(String nickname) {
        for (ClientHandler client : clients) {
            if (client.getNickname().equalsIgnoreCase(nickname)) return true;
        }
        return false;
    }

    public boolean isBlacklisted(ClientHandler client, String nickname) throws SQLException {
        return DbManager.getBlacklistedUsers(client.getUserID()).contains(nickname);
    }

    public boolean isBlacklisted(String nickname) throws SQLException {
        for (ClientHandler client:clients) {
            if (DbManager.getBlacklistedUsers(client.getUserID()).contains(nickname)) {
                return true;
            }
        }
        return false;
    }

    public synchronized void broadcastMessage(String message) {
        for (ClientHandler client: clients) {
            client.sendMessage(message);
        }
    }

    public synchronized void whisper(ClientHandler donor, String acceptor, String message) throws SQLException {
        if (!isBlacklisted(donor, acceptor)) {
            for (ClientHandler client : clients) {
                if (client.getNickname().equalsIgnoreCase(acceptor)) {
                    client.sendMessage(String.format("%s whispers: %s", donor.getNickname(), message));
                    donor.sendMessage(String.format("You whispered to %s: %s", acceptor, message));
                    return;
                }
            }
            donor.sendMessage(String.format("There're no user with %s nickname in chat", acceptor));
        } else {
            donor.sendMessage(String.format("%s is blacklisted!", acceptor));
        }
    }


    public void subscribe(ClientHandler client) {
       clients.add(client);
       LOG.info(String.format("Client connected: %s", client.getNickname()));
    }

    public void unsubscribe(ClientHandler client) {
        clients.remove(client);
        LOG.info(String.format("Client disconnected: %s", client.getNickname()));
    }

    public ExecutorService getExecutorService() {
        return executorService;
    }
}
