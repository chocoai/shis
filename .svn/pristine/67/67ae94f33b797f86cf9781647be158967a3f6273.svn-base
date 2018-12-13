package com.mibo.modules.al.sma.util;

import java.util.List;

/**
 * Created by mibo-hsp on 2018/9/29.
 */
public class DeviceSendSceneDataBean {

    /**
     * id : 123
     * version : 1.0
     * multiparams : [{"Name":"\u201d0391DC12004B1200\u201d","Index":0,"OnOff":true},{"Name":"\u201d91DC1D0B004B1200\u201d","Index":3,"OnOff":true},{"Name":"\u201d5F3FC00D004B1200\u201d","Index":1,"OnOff":false}]
     * method : thing.service.property.set
     */

    private String id;
    private String version;
    private String method;
    private List<MultiparamsBean> multiparams;

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

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public List<MultiparamsBean> getMultiparams() {
        return multiparams;
    }

    public void setMultiparams(List<MultiparamsBean> multiparams) {
        this.multiparams = multiparams;
    }

    public static class MultiparamsBean {
        /**
         * Name : ”0391DC12004B1200”
         * Index : 0
         * OnOff : true
         */

        private String Name;
        private int Index;
        private boolean OnOff;

        public String getName() {
            return Name;
        }

        public void setName(String Name) {
            this.Name = Name;
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
    }
}
