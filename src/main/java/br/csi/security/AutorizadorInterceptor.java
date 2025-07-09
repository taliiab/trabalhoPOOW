package br.csi.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AutorizadorInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {

        String uri = request.getRequestURI();
        if (uri.equals("/") || uri.equals("/login")) {

            return true;
        }


        if (request.getSession().getAttribute("usuarioLogado") == null) {

            response.sendRedirect("/");
            return false;
        }

        return true;
    }
}
