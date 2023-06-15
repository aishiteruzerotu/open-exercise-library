package com.nf.web.interceptor;


import com.nf.mvc.HandlerInterceptor;
import com.nf.mvc.annotation.Interceptor;
import com.nf.mvc.util.StringUtils;
import com.nf.vo.ResponseVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.nf.mvc.handler.HandlerHelper.json;

@Interceptor(values = {"/exercise/add","/exercise/delete","/exercise/update"
        ,"/admin/sign/up","/admin/update","/admin/delete"})
public class AdministratorsServiceInterceptor implements HandlerInterceptor {

    /**
     * 拦截用户是否登入
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String adminIdHeader = request.getHeader("adminId");
        //表明请求头有数据，意味着登录过了，所以放行
        if (StringUtils.hasText(adminIdHeader)) {
            return true;
        }

        //因为拦截器注解写了要拦截的地址，所以只会在请求地址就是要拦截的
        //地址时，才会进到此拦截器,因为请求头没有信息，所以阻止请求，要求登录
        //发一个响应给前端
        json(new ResponseVO(503,"请先登入",false))
                .render(request,response);
        return false;

    }

}
