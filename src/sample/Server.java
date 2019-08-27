package sample;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Runnable {

    ServerSocket serverSocket;
    Socket socket;
    DataOutputStream out;
    DataInputStream in;
    ServerController controller;


    public Server(ServerController controller) {
        this.controller = controller;

    }


    public void send(String output) {

        try {
            out.writeUTF(output);


        } catch (IOException e) {
            e.printStackTrace();
        }

    }



    public void close(){
        try {
            in.close();
            out.close();
            socket.close();
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void run() {

        try {

            serverSocket = new ServerSocket(1337);
            socket = serverSocket.accept();
            out = new DataOutputStream(socket.getOutputStream());
            in = new DataInputStream(socket.getInputStream());

            while(true){
                controller.chatWindowText.appendText(in.readUTF() + "\n");
            }



        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
