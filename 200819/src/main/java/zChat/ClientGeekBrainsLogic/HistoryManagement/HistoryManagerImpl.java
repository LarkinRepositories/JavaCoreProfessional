package zChat.ClientGeekBrainsLogic.HistoryManagement;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import zChat.ClientGeekBrainsLogic.UI.Controller.ChatWindow;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class HistoryManagerImpl implements HistoryManager {
    private final static String HISTORY_FILE = "history.txt";

    private String nickname = null;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }


    @Override
    public File getOrCreateHistoryFile() throws IOException {
        File historyPath = new File("/history/" + nickname);
        File history = new File(String.format("/history/%s/%s", nickname, HISTORY_FILE));
        if (!historyPath.exists() && !history.exists()) {
            System.out.println(historyPath.mkdirs());
            System.out.println(history.createNewFile());
        }
        return history;
    }

    @Override
    public void storeMessage(String msg) throws IOException {
        List<String> history1 = loadHistory(100);
        history1.add(0, msg);
        if (history1.size() >= 100)
            history1.remove(history1.size()-1);
        try (PrintWriter historyWriter = new PrintWriter(new FileWriter(HISTORY_FILE))) {
            history1.forEach(historyWriter::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<String> loadHistory(int limit) throws IOException {
        File history = getOrCreateHistoryFile();
        try (Stream<String> stream = Files.lines(history.toPath())) {
            return stream.limit(limit).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}
