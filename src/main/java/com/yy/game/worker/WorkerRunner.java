package com.yy.game.worker;

import com.yy.game.Constant;
import org.gearman.common.GearmanJobServerConnection;
import org.gearman.common.GearmanNIOJobServerConnection;
import org.gearman.worker.GearmanFunction;
import org.gearman.worker.GearmanWorker;
import org.gearman.worker.GearmanWorkerImpl;

import java.util.ArrayList;
import java.util.List;

public class WorkerRunner implements Runnable {
    // 所有定义好的工作
    private List<Class<? extends GearmanFunction>> functions;

    private GearmanWorker worker;

    public WorkerRunner(List<Class<? extends GearmanFunction>> functions) {
        // Gearman connection
        GearmanJobServerConnection connection = new GearmanNIOJobServerConnection(Constant.GEARMAN_HOST, Constant.GEARMAN_PORT);
        // 初始化worker
        this.worker = new GearmanWorkerImpl();
        this.worker.addServer(connection);

        this.functions = new ArrayList<Class<? extends GearmanFunction>>();
        this.functions.addAll(functions);
    }

    public void run() {
        this.start();
    }

    private void start() {
        // 将所有定义好的工作，注册到worker中
        for (Class<? extends GearmanFunction> function : functions) {
            worker.registerFunction(function);
        }
        // 启动worker
        worker.work();
    }
}
