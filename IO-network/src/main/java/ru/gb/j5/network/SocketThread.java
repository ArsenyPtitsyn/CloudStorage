package ru.gb.j5.network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class SocketThread extends Thread {

    private  final byte[] buf = new byte[256];
    private final SocketThreadListener listener;
    private final Socket socket;
    private DataInputStream dIs;
    private DataOutputStream dOs;

    public SocketThread(SocketThreadListener listener, String name, Socket socket) {
        super(name);
        this.listener = listener;
        this.socket = socket;
        start();
    }

    @Override
    public void run() {
        try {
            listener.onSocketStart(this, socket);
            dIs = new DataInputStream(socket.getInputStream());
            listener.onSocketReady(this, socket);
            while (!isInterrupted()) {
                int tmp;
                tmp = dIs.read(buf);
                listener.onReceiveBytes(this, socket, tmp);
            }
        } catch (IOException e) {
            listener.onSocketException(this, e);
        } finally {
            close();
        }
    }

    public boolean sendBytes(int tmp) {
        try {
            dOs.write(buf, 0, tmp);
            dOs.flush();
            return true;
        } catch (IOException e) {
            listener.onSocketException(this, e);
            close();
            return false;
        }
    }

    public void close() {
        try {
            dIs.close();
        } catch (IOException e) {
            listener.onSocketException(this, e);
        }
        interrupt();
        try {
            socket.close();
        } catch (IOException e) {
            listener.onSocketException(this, e);
        }
        listener.onSocketStop(this);
    }
}
