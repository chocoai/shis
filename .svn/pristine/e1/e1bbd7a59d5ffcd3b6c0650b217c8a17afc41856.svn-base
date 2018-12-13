  package com.mibo.modules.app.action;
  
  import com.jfinal.aop.Clear;
  import com.mibo.common.base.BaseAction;
  import com.mibo.common.interceptor.UserTokenInterceptor;
  import com.mibo.modules.app.service.UserService;
  
  public class UserAction extends BaseAction
  {
/* 10 */   private static final UserService userService = UserService.userService;
    
  
  
    public void userCode()
    {
/* 16 */     String phone = getPara("phone");
/* 17 */     String purpose = getPara("purpose");
/* 18 */     renderResponse(userService.userCode(phone, purpose, getUser()));
    }
    
  
  
    @Clear({UserTokenInterceptor.class})
    public void register()
    {
/* 26 */     if (!isPost()) {
/* 27 */       renderErrorRequest();
/* 28 */       return;
      }
/* 30 */     String phone = getPara("phone");
/* 31 */     String password = getPara("password");
/* 32 */     String code = getPara("code");
/* 33 */     renderResponse(userService.register(phone, password, code));
    }
    
  
  
    @Clear({UserTokenInterceptor.class})
    public void login()
    {
/* 41 */     if (!isPost()) {
/* 42 */       renderErrorRequest();
/* 43 */       return;
      }
/* 45 */     String phone = getPara("phone");
/* 46 */     String password = getPara("password");
/* 47 */     UserService userService = (UserService)enhance(UserService.class);
/* 48 */     renderResponse(userService.login(phone, password));
    }
    
  
  
    @Clear({UserTokenInterceptor.class})
    public void forgetPwd()
    {
/* 56 */     String phone = getPara("phone");
/* 57 */     String password = getPara("password");
/* 58 */     String code = getPara("code");
/* 59 */     renderResponse(userService.forgetPwd(phone, password, code));
    }
    
  
  
    public void verifyPwd()
    {
/* 66 */     String password = getPara("password");
/* 67 */     renderResponse(userService.verifyPwd(password, getUser()));
    }
    
  
  
    public void updatePwd()
    {
/* 74 */     String password = getPara("password");
/* 75 */     renderResponse(userService.updatePwd(password, getUser()));
    }
    
  
  
    public void verifyPhone()
    {
/* 82 */     String phone = getPara("phone");
/* 83 */     String code = getPara("code");
/* 84 */     renderResponse(userService.verifyPhone(phone, code, getUser()));
    }
    
  
  
    public void updatePhone()
    {
/* 91 */     String phone = getPara("phone");
/* 92 */     String code = getPara("code");
/* 93 */     renderResponse(userService.updatePhone(phone, code, getUser()));
    }
  }


/* Location:              C:\Users\liqp\Desktop\shis.zip!\shis\WEB-INF\classes\com\mibo\modules\app\action\UserAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */