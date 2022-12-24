package exercise.connections;

public interface Connection {
    // BEGIN
    void connect();
    String getCurrentState();
    void write(String text);
    void disconnect();
    // END
}
