package com.cos.blog.action.user;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cos.blog.action.Action;
import com.cos.blog.model.RoleType;
import com.cos.blog.model.Users;
import com.cos.blog.repository.UsersRepository;
import com.cos.blog.util.Script;

public class UsersLoginProcAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 0. 유효성 검사
		if
		(
				request.getParameter("username").equals("") ||
				request.getParameter("username") ==null ||
				request.getParameter("password").equals("") ||
				request.getParameter("password") ==null
		) {
			return;
		}
		// 1. 파라메터 받기 (x-www-form-urlencoded 라는 MIME타입 key=value)
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		// 2. DB연결 - UserRepositroy의 로그인 메소드 호출
		UsersRepository usersRepository = UsersRepository.getInstance();
		Users user = usersRepository.findByUsernameAndPassword(username, password);
		
		// 3. index.jsp 페이지로 이동
		if(user != null) {
			//session은 request가 들고 있다.
			HttpSession session = request.getSession();
			
			if(request.getParameter("remember") != null) {
//				Cookie cookie = new Cookie("remember", user.getUsername());
//				response.addCookie(cookie);
				response.setHeader("Set-Cookie", "remember="+user.getUsername());
			}else {
				Cookie cookie = new Cookie("remember", null);
				cookie.setMaxAge(0);
				response.addCookie(cookie);
			}

			session.setAttribute("principal", user);
			Script.href("로그인 성공", "/blog5/board?cmd=home", response);
		}else {
			Script.back("로그인에 실패하였습니다.", response);
		}
	}
}
	