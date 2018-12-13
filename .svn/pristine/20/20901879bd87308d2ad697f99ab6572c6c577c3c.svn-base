  package com.mibo.common.handler;
  
  import com.jfinal.core.JFinal;
  import com.jfinal.handler.Handler;
  import com.jfinal.kit.JsonKit;
  import com.jfinal.kit.LogKit;
  import com.mibo.common.base.BaseAction;
  import com.mibo.common.constant.Global;
  import com.mibo.common.result.Response;
  import javax.servlet.ServletOutputStream;
  import javax.servlet.http.HttpServletRequest;
  import javax.servlet.http.HttpServletResponse;
  
  public class NotFoundHandler
    extends Handler
  {
    public void handle(String target, HttpServletRequest request, HttpServletResponse response, boolean[] isHandled)
    {
/* 19 */     if (target.indexOf('.') != -1) {
/* 20 */       return;
      }
      
/* 23 */     if (Global.DEVMODE) {
/* 24 */       response = BaseAction.setCrossDomainRequest(response);
      }
      
/* 27 */     if (JFinal.me().getAction(target, new String[1]) == null) {
/* 28 */       String url = request.getRequestURI();
/* 29 */       if (url.indexOf("/api") >= 0) {
/* 30 */         LogKit.error("api not find url===============>" + url);
/* 31 */         response = notFound(response);
        } else {
/* 33 */         LogKit.error("page not find url===============>" + url);
        }
      }
/* 36 */     this.next.handle(target, request, response, isHandled);
    }
    
  
  
  
    public HttpServletResponse notFound(HttpServletResponse response)
    {
      try
      {
/* 46 */       ServletOutputStream sos = response.getOutputStream();
/* 47 */       sos.print(JsonKit.toJson(new Response(Integer.valueOf(404), "resource is not found")));
/* 48 */       sos.close();
      } catch (Exception e) {
/* 50 */       e.printStackTrace();
/* 51 */       LogKit.error("api接口找不到异常信息==========>" + e);
      }
/* 53 */     return response;
    }
  }


/* Location:              C:\Users\liqp\Desktop\shis.zip!\shis\WEB-INF\classes\com\mibo\common\handler\NotFoundHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */