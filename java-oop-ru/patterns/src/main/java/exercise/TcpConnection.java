package exercise;
import exercise.connections.Connection;
import exercise.connections.Disconnected;

import java.util.List;
import java.util.ArrayList;

// BEGIN
public class TcpConnection {

    private final String ip;
    private final int port;

    private Connection connection;
    public TcpConnection(String ip, int port) {
        this.ip = ip;
        this.port = port;
        this.connection = new Disconnected(this);
    }

    public void changeConnection(Connection connection) {
        this.connection = connection;
    }

    public void connect() {
        connection.connect();
    }

    public String getCurrentState() {
        return connection.getCurrentState();
    }

    public void write(String text) {
        connection.write(text);
    }

    public void disconnect() {
        connection.disconnect();
    }
}
// END
