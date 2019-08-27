package sample;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client implements Runnable{

    Socket socket;
    DataOutputStream out;
    DataInputStream in;

    String host = "localhost";
    int port = 1337;

    ClientController controller;

    public Client(ClientController controller) {
        this.controller = controller;

    }

    public void send(String output) {


        try {
            out.writeUTF(output);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void run() {
        try {
            socket = new Socket(host, port);
            out = new DataOutputStream(socket.getOutputStream());
            in = new DataInputStream(socket.getInputStream());

            while (true){
                controller.chatWindowText.appendText(in.readUTF() + "\n");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void close(){
        try {
            in.close();
            out.close();
            socket.close();


        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
