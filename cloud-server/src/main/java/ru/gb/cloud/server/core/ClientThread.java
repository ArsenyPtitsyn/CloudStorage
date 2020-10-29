package ru.gb.cloud.server.core;

import ru.gb.j5.network.SocketThread;
import ru.gb.j5.network.SocketThreadListener;

import java.net.Socket;

public class ClientThread extends SocketThread {

    private String nickname;
    private boolean isAuthorised;
    private boolean isReconnecting;

    public ClientThread(String name, SocketThreadListener listener, Socket socket) {
        super(listener, name, socket);
    }

    public boolean isReconnecting() {
        return isReconnecting;
    }

    public String getNickname() {
        return nickname;
    }

    public boolean isAuthorised() {
        return isAuthorised;
    }

    void reconnect() {
        isReconnecting = true;
        close();
    }


}
