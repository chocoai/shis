package com.mibo.modules.app.action;

import com.jfinal.aop.Clear;
import com.mibo.common.base.BaseAction;
import com.mibo.modules.app.service.ProductService;

public class ProductAction extends BaseAction
{
    /*   9 */   private static final ProductService productService = ProductService.productService;



    public void registerGateway()
    {
        /*  15 */     String productModel = getPara("productModel");
        /*  16 */     String deviceName = getPara("deviceName");
        /*  17 */     ProductService productService = (ProductService)enhance(ProductService.class);
        /*  18 */     renderResponse(productService.registerGateway(getUserId(), productModel, deviceName));
    }



    public void registerDevice()
    {
        /*  25 */     String gatewayId = getPara("gatewayId");
        /*  26 */     String productModel = getPara("productModel");
        /*  27 */     String deviceName = getPara("deviceName");
        /*  28 */     ProductService productService = (ProductService)enhance(ProductService.class);
        /*  29 */     renderResponse(productService.registerDevice(getUserId(), gatewayId, productModel, deviceName));
    }

    //添加WIFI设备
    public void addWifiDevice()
    {
        String productModel = getPara("productModel");
        String deviceName = getPara("deviceName");
        ProductService productService = (ProductService)enhance(ProductService.class);
        renderResponse(productService.addWifiDevice(getUserId(), productModel, deviceName));
    }



    public void getGatewayList()
    {
        /*  36 */     renderResponse(productService.getGatewayList(getUserId()));
    }

    //设备列表wifi
    public void getWifiDeviceList()
    {
        renderResponse(productService.getWifiDeviceList(getUserId()));
    }



    public void gatewayDetails()
    {
        /*  43 */     String gatewayId = getPara("gatewayId");
        /*  44 */     renderResponse(productService.gatewayDetails(gatewayId));
    }



    public void getAllDevice()
    {
        /*  51 */     renderResponse(productService.getAllDevice(getUserId()));
    }



    public void getDevicList()
    {
        /*  58 */     String gatewayId = getPara("gatewayId");
        /*  59 */     renderResponse(productService.getDevicList(gatewayId));
    }



    public void getDeviceDetails()
    {
        /*  66 */     String deviceId = getPara("deviceId");
        /*  67 */     renderResponse(productService.getDeviceDetails(deviceId));
    }



    public void sendMessage()
    {
        /*  74 */     String deviceName = getPara("deviceName");
        /*  75 */     String topicFullName = getPara("topicFullName");
        /*  76 */     String messageContent = getPara("messageContent");
        /*  77 */     renderResponse(productService.sendMessage(deviceName, topicFullName, messageContent));
    }



    public void updateDeviceAlias()
    {
        /*  84 */     String id = getPara("id");
        /*  85 */     String alias = getPara("alias");
        /*  86 */     String type = getPara("type");
        /*  87 */     renderResponse(productService.updateDeviceAlias(id, alias, type));
    }



    public void setupDevice()
    {
        /*  94 */     String id = getPara("id");
        /*  95 */     String laying = getPara("laying");
        /*  96 */     String push = getPara("push");
        /*  97 */     renderResponse(productService.setupDevice(id, laying, push));
    }



    public void deviceLayingTime()
    {
        /* 104 */     String id = getPara("id");
        /* 105 */     String staTime = getPara("staTime");
        /* 106 */     String endTime = getPara("endTime");
        /* 107 */     renderResponse(productService.deviceLayingTime(id, staTime, endTime));
    }



    public void deleteDeviceLaying()
    {
        /* 114 */     String layingId = getPara("layingId");
        /* 115 */     renderResponse(productService.deleteDeviceLaying(layingId));
    }



    public void messageList()
    {
        /* 122 */     renderResponse(productService.messageList(getUserId(), getPageNo()));
    }



    public void delete()
    {
        /* 129 */     String id = getPara("id");
        /* 130 */     String type = getPara("type");
        /* 131 */     ProductService productService = (ProductService)enhance(ProductService.class);
        /* 132 */     renderResponse(productService.delete(getUserId(), id, type));
    }



    public void gatewayVersion()
    {
        /* 139 */     String id = getPara("id");
        /* 140 */     String name = getPara("name");
        /* 141 */     String no = getPara("no");
        /* 142 */     renderResponse(productService.gatewayVersion(id, name, no));
    }



    public void searchMessage()
    {
        /* 149 */     String keyword = getPara("keyword");
        /* 150 */     renderResponse(productService.searchMessage(getUserId(), keyword, getPageNo()));
    }






    public void deleteMessage()
    {
        /* 160 */     String ids = getPara("ids");
        /* 161 */     renderResponse(productService.delteMessage(ids));
    }



    public void deleteAllMessage()
    {
        /* 168 */     renderResponse(productService.deleteAllMessage(getUserId()));
    }



    public void delteALL()
    {
        /* 175 */     String id = getPara("id");
        /* 176 */     ProductService productService = (ProductService)enhance(ProductService.class);
        /* 177 */     renderResponse(productService.delteALL(getUserId(), id));
    }



    public void batteryPush()
    {
        /* 184 */     renderResponse(productService.batteryPush(getUserId()));
    }



    @Clear
    public void clears()
    {
        /* 192 */     renderResponse(productService.clears());
    }
}


/* Location:              C:\Users\liqp\Desktop\shis.zip!\shis\WEB-INF\classes\com\mibo\modules\app\action\ProductAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */