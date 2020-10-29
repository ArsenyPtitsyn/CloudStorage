package ru.gb.cloud.server.core;

import ru.gb.j5.network.ServerSocketThread;
import ru.gb.j5.network.ServerSocketThreadListener;
import ru.gb.j5.network.SocketThread;
import ru.gb.j5.network.SocketThreadListener;

import java.net.ServerSocket;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class CloudServer implements ServerSocketThreadListener, SocketThreadListener {

    CloudServerListener listener;

    public CloudServer(CloudServerListener listener) {
        this.listener = listener;
    }

    private ServerSocketThread server;
    private final DateFormat DATE_FORMAT = new SimpleDateFormat("HH:mm:ss: ");

    public void start(int port) {
        if (server == null || !server.isAlive())
            server = new ServerSocketThread(this, "Server", port, 2000);
        else
            putLog("Server already started");
    }

    public void stop() {
        if (server == null || !server.isAlive()) {
            putLog("Server is not running");
        } else {
            server.interrupt();
        }
    }

    private void putLog(String msg) {
        msg = DATE_FORMAT.format(System.currentTimeMillis()) +
                Thread.currentThread().getName() + ":" + msg;
        listener.onCloudServerMessage(msg);
    }

    /**
     * Server Socket Thread Listener Methods!
     */

    @Override
    public void onServerStart(ServerSocketThread thread) {
        putLog("Server started");
    }

    @Override
    public void onServerCreated(ServerSocketThread thread, ServerSocket server) {
        putLog("Server created");
    }

    @Override
    public void onServerTimeout(ServerSocketThread thread, ServerSocket server) {
        // putLog("PING? PONG!");
    }

    @Override
    public void onSocketAccepted(ServerSocketThread thread, ServerSocket server, Socket socket) {
        putLog("Client connected");
        String name = "SocketThread " +socket.getInetAddress() + ":" + socket.getPort();
        new ClientThread(name, this, socket);
    }

    @Override
    public void onServerException(ServerSocketThread thread, Throwable throwable) {
        throwable.printStackTrace();
    }

    @Override
    public void onServerStop(ServerSocketThread thread) {
        putLog("Server stopped");
    }

    /**
     * Socket Thread Listener Methods!
     **/

    @Override
    public void onSocketStart(SocketThread thread, Socket socket) {
        putLog("Client connected");
    }

    @Override
    public void onSocketReady(SocketThread thread, Socket socket) {
        putLog("Client is ready to use storage cloud");
    }

    @Override
    public void onReceiveBytes(SocketThread thread, Socket socket, int i) {
        // Do something useful with received bytes!
    }

    @Override
    public void onSocketException(SocketThread thread, Throwable throwable) {
        throwable.printStackTrace();
        thread.close();
    }

    @Override
    public void onSocketStop(SocketThread thread) {
        putLog("Client disconnected");
    }
}
