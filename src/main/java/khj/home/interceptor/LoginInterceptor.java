package khj.home.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoginInterceptor extends HandlerInterceptorAdapter{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();
		if(session.getAttribute("loginMember") == null) {
			request.setAttribute("msg", "로그인 후 이용가능합니다.");
			request.setAttribute("url", "/member/login");
			request.getRequestDispatcher("/WEB-INF/views/page/error.jsp").forward(request, response);
			return false;
		}
		return true;
	}
}
