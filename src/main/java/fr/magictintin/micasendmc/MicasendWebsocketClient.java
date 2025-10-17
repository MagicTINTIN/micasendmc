package fr.magictintin.micasendmc;

import java.net.URI;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import fr.magictintin.micasendmc.MicaSend.VoidCallback;

public class MicasendWebsocketClient extends WebSocketClient {
    protected WebSocketClient client;
    // Session userSession = null;
    private final VoidCallback callback;
    // private final String uri;

    public MicasendWebsocketClient(String uri, VoidCallback callback) {
        super(URI.create(uri));
        this.callback = callback;
        // this.uri = uri;

        // // client = new WebSocketClient();
        // try {
        // WebSocketContainer container = ContainerProvider.getWebSocketContainer();
        // container.connectToServer(this, URI.create(this.uri));
        // } catch (Exception e) {
        // throw new RuntimeException(e);
        // }
        connect();
    }

    @Override
    public void onOpen(ServerHandshake handshakedata) {
        System.out.println("connected");
    }

    @Override
    public void onMessage(String message) {
        callback.execute();
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        System.out.println("Disconnected, reconnecting...");
        connect();
    }

    @Override
    public void onError(Exception ex) {
        System.err.println(ex.getMessage());
    }

    public void sendMessage(String message) {
        if (isOpen()) {
            send("micasend:" + message);
        }
        else {
            connect();
        }
    }
}
