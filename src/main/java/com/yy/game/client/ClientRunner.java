package com.yy.game.client;

import com.yy.game.Constant;
import com.yy.game.entity.DataInfo;
import com.yy.game.job.ReadDataJob;
import org.apache.commons.lang3.SerializationUtils;
import org.gearman.client.*;
import org.gearman.common.GearmanJobServerConnection;
import org.gearman.common.GearmanNIOJobServerConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;
import java.util.concurrent.ExecutionException;

public class ClientRunner implements Runnable {

    private Logger logger = LoggerFactory.getLogger(ClientRunner.class);

    private GearmanClient client;

    public ClientRunner() {
        GearmanJobServerConnection connection = new GearmanNIOJobServerConnection(Constant.GEARMAN_HOST, Constant.GEARMAN_PORT);
        this.client = new GearmanClientImpl();
        client.addJobServer(connection);
    }

    public void run() {
        this.start();
    }

    private void start() {
        while (true) {
            try {
                String function = ReadDataJob.class.getCanonicalName();
                DataInfo data = new DataInfo(UUID.randomUUID().toString(), "log", "/home/log/");
                // 创建一个工作，包含名字、数据（参数）、工作ID
                GearmanJob job = GearmanJobImpl.createJob(function, SerializationUtils.serialize(data), data.getDataId());
                this.client.submit(job);
                GearmanJobResult result = job.get();
                byte[] resData = result.getResults();
                String res = SerializationUtils.deserialize(resData);
                logger.warn("reply is [{}]", res);
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
    }
}
