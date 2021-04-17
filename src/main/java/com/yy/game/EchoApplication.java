package com.yy.game;

import org.gearman.client.GearmanClient;
import org.gearman.client.GearmanClientImpl;
import org.gearman.common.GearmanJobServerConnection;
import org.gearman.common.GearmanNIOJobServerConnection;

import java.io.IOException;

public class EchoApplication {

    private GearmanClient client;

    public EchoApplication() {
        GearmanJobServerConnection connection = new GearmanNIOJobServerConnection(Constant.GEARMAN_HOST, Constant.GEARMAN_PORT); // 创建Gearman服务connection
        client = new GearmanClientImpl();
        client.addJobServer(connection);
    }

    public String echo(String input) throws IOException {
        byte[] data = input.getBytes(); // 发送数据
        byte[] reply = ((GearmanClientImpl) client).echo(data); // 调用Gearman
        return new String(reply, "UTF-8");
    }

    public static void main(String... args) {
        EchoApplication app = new EchoApplication();

        String reply = null;
        try {
            reply = app.echo("hello");
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("reply is ===> " + reply);
    }
}
