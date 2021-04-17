package com.yy.game;

import com.yy.game.client.ClientRunner;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ClientApplication {

    private ExecutorService client = Executors.newSingleThreadExecutor();

    public static void main(String... args) {
        ClientApplication app = new ClientApplication();
        ClientRunner clientRunner = new ClientRunner();
        app.client.submit(clientRunner);
    }

}
