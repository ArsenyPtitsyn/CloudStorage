package ru.gb.j5.network;

import java.net.Socket;

public interface SocketThreadListener {
    void onSocketStart(SocketThread thread, Socket socket);
    void onSocketReady(SocketThread thread, Socket socket);
    void onReceiveBytes(SocketThread thread, Socket socket, int i);
    void onSocketException(SocketThread thread, Throwable throwable);
    void onSocketStop(SocketThread thread);
}
