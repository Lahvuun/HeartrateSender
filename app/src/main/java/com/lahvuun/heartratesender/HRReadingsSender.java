package com.lahvuun.heartratesender;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.LinkedBlockingQueue;

public class HRReadingsSender implements Runnable {
    private LinkedBlockingQueue readingsQueue;
    private String host;
    private int port;

    public HRReadingsSender(LinkedBlockingQueue queue, String h, int p) {
        readingsQueue = queue;
        host = h;
        port = p;
    }

    @Override
    public void run() {
        try (
                Socket socket = new Socket(host, port);
                PrintWriter pw = new PrintWriter(socket.getOutputStream(), true)
                ){
            while (true) {
                int reading = (int) readingsQueue.take();
                pw.println(reading);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
