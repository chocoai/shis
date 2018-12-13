  package com.mibo.modules.al.sma.conf;
  
  import com.aliyuncs.DefaultAcsClient;
  import com.aliyuncs.profile.DefaultProfile;
  import com.aliyuncs.profile.IClientProfile;
  import com.jfinal.kit.LogKit;
  import com.mibo.common.constant.Global;
  
  public class SmaConfig
  {
/* 11 */   private static final String ACCESSKEY = Global.getKeys("ali.access.key");
/* 12 */   private static final String ACCESSSECRET = Global.getKeys("ali.access.secret");
    
/* 14 */   private static boolean initialized = false;
/* 15 */   private static DefaultAcsClient client = null;
    
/* 17 */   private static void initialize() { if (initialized)
/* 18 */       return;
/* 19 */     client = init();
/* 20 */     initialized = true;
    }
    
  
  
    private static DefaultAcsClient init()
    {
      try
      {
/* 29 */       DefaultProfile.addEndpoint("cn-shanghai", "cn-shanghai", "Iot", "iot.cn-shanghai.aliyuncs.com");
/* 30 */       IClientProfile profile = DefaultProfile.getProfile("cn-shanghai", ACCESSKEY, ACCESSSECRET);
/* 31 */       return new DefaultAcsClient(profile);
      }
      catch (Exception e) {
/* 34 */       e.printStackTrace();
/* 35 */       LogKit.error("ALI SMA SDK初始化错误！", e); }
/* 36 */     return null;
    }
    
  
  
  
  
    public static DefaultAcsClient getClient()
    {
/* 45 */     if (client == null) {
/* 46 */       initialize();
      }
/* 48 */     return client;
    }
  }


/* Location:              C:\Users\liqp\Desktop\shis.zip!\shis\WEB-INF\classes\com\mibo\modules\al\sma\conf\SmaConfig.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */