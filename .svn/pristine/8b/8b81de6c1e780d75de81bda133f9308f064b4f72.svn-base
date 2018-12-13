  package com.mibo.modules.app.action;
  
  import com.jfinal.aop.Clear;
  import com.mibo.common.base.BaseAction;
  import com.mibo.common.interceptor.UserTokenInterceptor;
  import com.mibo.modules.app.service.CommonService;
  
  @Clear({UserTokenInterceptor.class})
  public class CommonAction extends BaseAction
  {
/* 11 */   private static final CommonService cs = CommonService.commonService;
    
  
  
    public void version()
    {
/* 17 */     String type = getPara("type");
/* 18 */     String no = getPara("no");
/* 19 */     renderResponse(cs.updateVersion(type, no));
    }
    
  
  
    public void code()
    {
/* 26 */     String phone = getPara("phone");
/* 27 */     String purpose = getPara("purpose");
/* 28 */     renderResponse(cs.getCode(phone, purpose));
    }
  }


/* Location:              C:\Users\liqp\Desktop\shis.zip!\shis\WEB-INF\classes\com\mibo\modules\app\action\CommonAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */