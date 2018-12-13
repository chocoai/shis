package com.mibo.modules.al.sma.util;

public class ControlBean {

    private String id;
    private String version;
    private ParamsBean params;
    private String method;
    private int code;
    private ParamsBean data;
    private String msg;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public ParamsBean getParams() {
        return params;
    }

    public void setParams(ParamsBean params) {
        this.params = params;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public ParamsBean getData() {
        return data;
    }

    public void setData(ParamsBean data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static class ParamsBean {
        /**
         * Index : 0
         * OnOff : true
         */

        private int Index;
        private boolean OnOff;
        private int Sensor; //0就是无警告关门，1就是有警告开门
        //场景的
        private int GroupID;
        private int SceneID;

        private int BatteryAlarm;
        
        private String DataType;
        private int State;

        public int getBatteryAlarm() {
            return BatteryAlarm;
        }

        public void setBatteryAlarm(int batteryAlarm) {
            BatteryAlarm = batteryAlarm;
        }

        public int getGroupID() {
            return GroupID;
        }

        public void setGroupID(int groupID) {
            GroupID = groupID;
        }

        public int getSceneID() {
            return SceneID;
        }

        public void setSceneID(int sceneID) {
            SceneID = sceneID;
        }

        public int getSensor() {
            return Sensor;
        }

        public void setSensor(int sensor) {
            Sensor = sensor;
        }

        public int getIndex() {
            return Index;
        }

        public void setIndex(int Index) {
            this.Index = Index;
        }

        public boolean isOnOff() {
            return OnOff;
        }

        public void setOnOff(boolean OnOff) {
            this.OnOff = OnOff;
        }

		public String getDataType() {
			return DataType;
		}

		public void setDataType(String dataType) {
			DataType = dataType;
		}

		public int getState() {
			return State;
		}

		public void setState(int state) {
			State = state;
		}
        
    }
}
