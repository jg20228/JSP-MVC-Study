package com.cos.blog.action.user;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cos.blog.action.Action;
import com.cos.blog.model.RoleType;
import com.cos.blog.model.Users;
import com.cos.blog.repository.UsersRepository;
import com.cos.blog.util.SHA256;
import com.cos.blog.util.Script;

public class UsersUpdateProcAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (session.getAttribute("principal") == null) {
			Script.getMessage("잘못된 접근입니다.", response);
			return;
		}
		// 0. 유효성 검사
		if (request.getParameter("id").equals("") || request.getParameter("id") == null
				|| request.getParameter("password").equals("") || request.getParameter("password") == null
				|| request.getParameter("email").equals("") || request.getParameter("email") == null
				|| request.getParameter("address").equals("") || request.getParameter("address") == null) {
			Script.back("입력되지 않은 필드가 있습니다.", response);
			return;
		}
		// 1. 파라메터 받기 (x-www-form-urlencoded 라는 MIME타입 key=value)
		int id = Integer.parseInt(request.getParameter("id"));
		String rawPassword = request.getParameter("password");
		
		String password = SHA256.encodeSha256(rawPassword);
		String email = request.getParameter("email");
		String address = request.getParameter("address");
		// 2. User - 오브젝트 변환
		Users user = Users.builder().id(id).password(password).email(email).address(address).build();
		// 3. DB연결 - UserRepositroy의 save() 호출
		UsersRepository usersRepository = UsersRepository.getInstance();
		int result = usersRepository.update(user);

		// 4. index.jsp 페이지로 이동
		if (result == 1) {
			//수정 완료시 principal을 id값으로 셀렉트하고 만들어서 다시 담음
			Users principal = usersRepository.findById(id);
			session.setAttribute("principal", principal);

			Script.href("회원수정 성공", "/blog5/index.jsp", response);
		} else {
			Script.back("회원수정 실패", response);
		}
	}
}
