package com.yy.game.job;

import com.yy.game.entity.DataInfo;
import org.apache.commons.lang3.SerializationUtils;
import org.gearman.client.GearmanJobResult;
import org.gearman.client.GearmanJobResultImpl;
import org.gearman.worker.AbstractGearmanFunction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReadDataJob extends AbstractGearmanFunction {

    private Logger logger = LoggerFactory.getLogger(ReadDataJob.class);

    @Override
    public GearmanJobResult executeFunction() {
        DataInfo data = SerializationUtils.deserialize((byte[]) this.data);
        logger.warn("this thread ID is [{}], i will do [{}]", Thread.currentThread().getId(), data.toString());
        return new GearmanJobResultImpl(this.jobHandle, true, SerializationUtils.serialize(data.toString()), new byte[0], new byte[0], 0, 0);
    }
}
