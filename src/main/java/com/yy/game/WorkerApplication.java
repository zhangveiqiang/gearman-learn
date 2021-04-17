package com.yy.game;

import com.yy.game.client.ClientRunner;
import com.yy.game.job.ReadDataJob;
import com.yy.game.worker.WorkerRunner;
import org.gearman.worker.GearmanFunction;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class WorkerApplication {

    private ExecutorService workers = Executors.newFixedThreadPool(2);

    public static void main(String... args) {
        WorkerApplication app = new WorkerApplication();

        List<Class<? extends GearmanFunction>> functions = new ArrayList<>();
        functions.add(ReadDataJob.class);
        WorkerRunner worker1 = new WorkerRunner(functions);
        WorkerRunner worker2 = new WorkerRunner(functions);
        app.workers.submit(worker1);
        app.workers.submit(worker2);
    }
}
