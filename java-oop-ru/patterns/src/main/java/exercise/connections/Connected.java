package exercise.connections;

import exercise.TcpConnection;

// BEGIN
public class Connected implements Connection {
    private final TcpConnection connection;

    public Connected(TcpConnection connection) {
        this.connection = connection;
    }


    @Override
    public void connect() {
        System.out.println("Error: is already connected");
    }

    @Override
    public String getCurrentState() {
        return "connected";
    }

    @Override
    public void write(String text) {
        System.out.printf("Send message: %s\n", text);
    }

    @Override
    public void disconnect() {
        System.out.println("success");
        connection.changeConnection(new Disconnected(connection));
    }
}
// END
