package com.minademo;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello World!");

        SockListen sockServer = new SockListen();
        sockServer.startListen();
    }
}
