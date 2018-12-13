  package com.mibo.modules.app.action;
  
  import com.mibo.common.base.BaseAction;
  import com.mibo.modules.app.service.UserSceneService;
  
  public class UserSceneAction extends BaseAction
  {
/*  8 */   private static final UserSceneService userSceneService = UserSceneService.userSceneService;
    
  
  
    public void add()
    {
/* 14 */     String gatewayId = getPara("gatewayId");
/* 15 */     String type = getPara("type");
/* 16 */     String name = getPara("name");
/* 17 */     String data = getPara("data");
/* 18 */     String isHome = getPara("isHome");
/* 19 */     renderResponse(userSceneService.add(gatewayId, type, name, data, isHome));
    }
    
  
  
    public void update()
    {
/* 26 */     String sceneId = getPara("sceneId");
/* 27 */     String gatewayId = getPara("gatewayId");
/* 28 */     String type = getPara("type");
/* 29 */     String name = getPara("name");
/* 30 */     String data = getPara("data");
/* 31 */     String isHome = getPara("isHome");
/* 32 */     renderResponse(userSceneService.update(getUserId(), sceneId, gatewayId, type, name, data, isHome));
    }
    
  
  
    public void delete()
    {
/* 39 */     String sceneId = getPara("sceneId");
/* 40 */     renderResponse(userSceneService.delete(getUserId(), sceneId));
    }
    
  
  
    public void getList()
    {
/* 47 */     String gatewayId = getPara("gatewayId");
/* 48 */     renderResponse(userSceneService.getList(gatewayId));
    }
    
  
  
    public void home()
    {
/* 55 */     renderResponse(userSceneService.home(getUserId()));
    }
    
  
  
    public void send()
    {
/* 62 */     String deviceName = getPara("deviceName");
/* 63 */     String sceneID = getPara("sceneID");
/* 64 */     renderResponse(userSceneService.send(getUserId(), deviceName, sceneID));
    }
  }


/* Location:              C:\Users\liqp\Desktop\shis.zip!\shis\WEB-INF\classes\com\mibo\modules\app\action\UserSceneAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */