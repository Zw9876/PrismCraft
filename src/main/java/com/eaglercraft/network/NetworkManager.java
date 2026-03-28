package com.eaglercraft.network;

import com.eaglercraft.js.Browser;
import com.eaglercraft.js.WebSocket;
import com.eaglercraft.js.WebSocketCallback;
import com.eaglercraft.js.WebSocketEvent;
import org.teavm.jso.JSBody;
import org.teavm.jso.JSObject;

import java.util.ArrayDeque;
import java.util.Queue;

public class NetworkManager {
    public static final int READY_STATE_CONNECTING = 0;
    public static final int READY_STATE_OPEN = 1;
    public static final int READY_STATE_CLOSING = 2;
    public static final int READY_STATE_CLOSED = 3;

    private WebSocket socket;
    private final Queue<byte[]> incomingPackets = new ArrayDeque<>();
    private boolean connected = false;
    private String serverAddress;
    private NetworkListener listener;

    public interface NetworkListener {
        void onConnected();
        void onDisconnected();
        void onPacketReceived(byte[] data);
        void onError(String message);
    }

    public void connect(String address, NetworkListener listener) {
        this.serverAddress = address;
        this.listener = listener;
        System.out.println("Connecting to: " + address);

        socket = Browser.createWebSocket(address, "eaglercraft");
        setSocketBinary(socket);

        socket.addEventListener("open", event -> {
            connected = true;
            System.out.println("Connected to server: " + address);
            if (listener != null) listener.onConnected();
        });

        socket.addEventListener("message", event -> {
            WebSocketEvent wsEvent = (WebSocketEvent) event;
            byte[] data = extractBytes(wsEvent.getData());
            if (data != null) {
                incomingPackets.add(data);
                if (listener != null) listener.onPacketReceived(data);
            }
        });

        socket.addEventListener("close", event -> {
            connected = false;
            System.out.println("Disconnected from server");
            if (listener != null) listener.onDisconnected();
        });

        socket.addEventListener("error", event -> {
            System.out.println("WebSocket error");
            if (listener != null) listener.onError("WebSocket error");
        });
    }

    @JSBody(params = {"socket"}, script = "socket.binaryType = 'arraybuffer';")
    private static native void setSocketBinary(JSObject socket);

    @JSBody(params = {"data"}, script =
            "if (data instanceof ArrayBuffer) {" +
                    "    return new Int8Array(data);" +
                    "} else if (typeof data === 'string') {" +
                    "    var enc = new TextEncoder();" +
                    "    return enc.encode(data);" +
                    "}" +
                    "return null;")
    private static native byte[] extractBytes(JSObject data);

    public void sendPacket(byte[] data) {
        if (connected && socket != null) {
            sendBytes(socket, data);
        }
    }

    @JSBody(params = {"socket", "data"}, script =
            "socket.send(new Uint8Array(data).buffer);")
    private static native void sendBytes(JSObject socket, byte[] data);

    public void disconnect() {
        if (socket != null) {
            socket.close();
            connected = false;
        }
    }

    public boolean isConnected() { return connected; }
    public String getServerAddress() { return serverAddress; }

    public Queue<byte[]> getIncomingPackets() { return incomingPackets; }
}