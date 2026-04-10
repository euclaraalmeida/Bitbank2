package br.edu.ifpb.pweb2.bitbank.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import br.edu.ifpb.pweb2.bitbank.model.Correntista;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Component
public class AuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, 
        HttpServletResponse response, Object handler)

        throws Exception {
            boolean allowed = false;
            HttpSession httpSession = request.getSession(false);

            if (httpSession!=null && ((Correntista) httpSession.getAttribute("usuario")) !=null){
                allowed = true;

            }else {
                String baseUrl = request.getContextPath();
                String paginaLogin = baseUrl + "/auth";
                response.sendRedirect(response.encodeRedirectURL(paginaLogin));
                allowed = false;
            }
            return allowed;

        }
    }
    

