package com.yy.game.entity;

import java.io.Serializable;

public class DataInfo implements Serializable {

    private String dataId;

    private String dataType;

    private String dataPath;

    public DataInfo(String dataId, String dataType, String dataPath) {
        this.dataId = dataId;
        this.dataType = dataType;
        this.dataPath = dataPath;
    }

    public String getDataId() {
        return dataId;
    }

    public void setDataId(String dataId) {
        this.dataId = dataId;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getDataPath() {
        return dataPath;
    }

    public void setDataPath(String dataPath) {
        this.dataPath = dataPath;
    }

    @Override
    public String toString() {
        return "DataInfo{" +
                "dataId='" + dataId + '\'' +
                ", dataType='" + dataType + '\'' +
                ", dataPath='" + dataPath + '\'' +
                '}';
    }
}
