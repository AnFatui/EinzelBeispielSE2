package com.example.einzelbeispiel.TCP;
import java.io.*;
import java.net.*;

public class Network {

    String sentence;
    String modifiedSentence;

    BufferedReader inFromUser;
    Socket clientSocket;
    DataOutputStream outToServer;

    BufferedReader inFromServer;

    public void doBidding(int studentID) {
        {
            try {
                clientSocket = new Socket("se2-isys.aau.at", 53212);
                //inFromUser = new BufferedReader(new InputStreamReader(System.in));
                outToServer = new DataOutputStream(clientSocket.getOutputStream());
                inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                sentence = String.valueOf(studentID);
                outToServer.writeBytes(sentence + "\n");
                modifiedSentence = inFromServer.readLine();

                clientSocket.close();

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }


    public String getModifiedSentence(){
        return modifiedSentence;
    }

}
