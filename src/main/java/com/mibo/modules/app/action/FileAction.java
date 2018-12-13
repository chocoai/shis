  package com.mibo.modules.app.action;
  
  import com.jfinal.aop.Clear;
  import com.mibo.common.base.BaseAction;
  import com.mibo.common.interceptor.SignInterceptor;
  import com.mibo.common.interceptor.TimestampsInterceptor;
  import com.mibo.modules.app.service.FileService;
  
  @Clear({TimestampsInterceptor.class, SignInterceptor.class})
  public class FileAction extends BaseAction
  {
/* 12 */   private static final FileService fs = FileService.fs;
    
    public void upload() {
/* 15 */     if (isPost()) {
/* 16 */       if (!isPost()) {
/* 17 */         renderErrorRequest();
/* 18 */         return;
        }
/* 20 */       renderResponse(fs.uploadImg(getFiles()));
/* 21 */       return;
      }
/* 23 */     render("upload.html");
    }
  }


/* Location:              C:\Users\liqp\Desktop\shis.zip!\shis\WEB-INF\classes\com\mibo\modules\app\action\FileAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */