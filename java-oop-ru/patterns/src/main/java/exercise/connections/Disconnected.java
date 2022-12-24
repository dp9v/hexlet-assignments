package exercise.connections;

import exercise.TcpConnection;

// BEGIN
public class Disconnected implements Connection {
    private final TcpConnection connection;

    public Disconnected(TcpConnection connection) {
        this.connection = connection;
    }


    @Override
    public void connect() {
        System.out.println("success");
        connection.changeConnection(new Connected(connection));
    }

    @Override
    public String getCurrentState() {
        return "disconnected";
    }

    @Override
    public void write(String text) {
        System.out.println("Error: cant send message");
    }

    @Override
    public void disconnect() {
        System.out.println("Error: is already disconnected");
    }
}
// END
