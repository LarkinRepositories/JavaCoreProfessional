package zChat.Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.sql.SQLException;

public class ClientHandler {
    private Server server;
    private Socket socket;
    private DataOutputStream out;
    private DataInputStream in;
    private String nickname;
    private int userID;

    public ClientHandler(Server server, Socket socket) throws IOException {
            this.socket = socket;
            this.server = server;
            this.in = new DataInputStream(socket.getInputStream());
            this.out = new DataOutputStream(socket.getOutputStream());
            server.getExecutorService().execute(() -> {
                try {
                    recieveMessages();
                } catch (IOException | SQLException e) {
                    e.printStackTrace();
                    Server.LOG.error(e.getClass().getName() + e.getMessage());
                }
            });
    }

    private void recieveMessages() throws IOException, SQLException {
        while (true) {
            String str = in.readUTF();
            if (str.startsWith("/new")) {
                String[] tokens = str.split(" ");
                DbManager.createNewAccount(tokens[1], tokens[2], tokens[3]);
                Server.LOG.info(String.format("New account created: %s", tokens[1]));
                continue;
            }

            if (str.startsWith("/auth")) {
                String[] tokens = str.split(" ");
                userID = DbManager.getUserIDByLoginAndPass(tokens[1], tokens[2]);
                DbManager.passToHashCode(userID, tokens[2]);
                nickname = DbManager.getNickNameByLoginAndPass(tokens[1], tokens[2]);
                if (nickname != null)  {
                    if (!server.isNicknameBusy(nickname)) {
                        sendMessage("/authok " +nickname);
                        server.subscribe(ClientHandler.this);
                        server.broadcastMessage(nickname + " joined the conversation");
                        break;
                    }  else {
                        sendMessage(String.format("%s is already authorized", nickname));
                        Server.LOG.info(String.format("%s tries to authorize from different devices at the same time", nickname));
                    }
                } else {
                    sendMessage("Incorrect login/password!");
                }
            }
        }

        while (true) {
            String message = in.readUTF();
            System.out.println(nickname+": " + message);
            if (message.equals("/end")) {
                out.writeUTF("/serverClosed");
                break;
            }

            if(message.startsWith("/changeNick ")) {
                String[] tokens = message.split(" ");
                String nickname = tokens[1];
                DbManager.changeNickname(this.userID, nickname);
                this.nickname = nickname;
                sendMessage("/nickChanged "+nickname);
                continue;
            }

            if (message.startsWith("/w ")){
                String[] tokens = message.split(" ");
                String nickname = tokens[1];
                String msg = message.substring(4 + nickname.length());
                server.whisper(this, nickname, msg);
                continue;
            }
            if (message.startsWith("/ignore ")) {
                String[] tokens = message.split(" ");
                String nickname = tokens[1];
                DbManager.blacklist(this.userID, nickname);
                Server.LOG.info(String.format("%s blacklisted %s", this.nickname, nickname));
                //sendMessage(nickname + " blacklisted");
                continue;
            }
            server.broadcastMessage(nickname + ": " + message);
            Server.LOG.info(String.format("%s : %s", nickname, message));
        }
    }

    public synchronized void sendMessage(String message) {
        try {
            out.writeUTF(message);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
            Server.LOG.error(e.getClass().getName() + e.getMessage());
        }
    }
    public String getNickname() {
        return nickname;
    }

    public int getUserID() {
        return userID;
    }

}
