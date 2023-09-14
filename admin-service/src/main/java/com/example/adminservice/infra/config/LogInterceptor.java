package com.example.adminservice.infra.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Slf4j
public class LogInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String userId = request.getHeader("X-User-Id");
//        if(userId.isBlank()){
//            log.error("요청한 사용자가 없습니다.");
//            return false;
//        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        String userId = request.getHeader("X-User-Id");
        String method = request.getMethod();
        String uri = request.getRequestURI();
        Map<String,String []> parameterMap = request.getParameterMap();

        if(method.equals("GET")){
            log.info("{}가 {} 로 조회 요청하였습니다",userId,handler.toString());
        }else if(method.equals("POST")){
            log.info("{}가 {}로 {}정보의 등록 요청하였습니다",userId,uri,parameterMap);
        }else if (method.equals("DELETE")) {
            log.info("{}가 {}로 삭제 요청하였습니다",userId,uri);
        }else if (method.equals("PUT")){
            log.info("{}가 {}로 {}정보 수정 요청하였습니다",userId,uri,parameterMap);
        }
    }
}
