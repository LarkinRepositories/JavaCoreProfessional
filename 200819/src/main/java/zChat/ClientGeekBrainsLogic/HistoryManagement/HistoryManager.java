package zChat.ClientGeekBrainsLogic.HistoryManagement;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

public interface HistoryManager {

    File getOrCreateHistoryFile() throws IOException;

    void storeMessage(String msg) throws IOException;

    default void storeMessage(String...messages) throws IOException {
        for (String message:messages) {
            storeMessage(message);
        }
    }

    default void storeMessage(Collection<String> messages) throws IOException {
        for (String message:messages) {
            storeMessage(message);
        }
    }

    List<String> loadHistory(int limit) throws IOException;

    default List<String> loadHistory() throws IOException {
        return loadHistory(100);
    }

}
