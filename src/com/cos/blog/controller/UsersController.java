package com.cos.blog.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cos.blog.action.Action;
import com.cos.blog.action.user.UsersJoinAction;
import com.cos.blog.action.user.UsersJoinProcAction;
import com.cos.blog.action.user.UsersLoginAction;
import com.cos.blog.action.user.UsersLoginProcAction;
import com.cos.blog.action.user.UsersLogoutAction;
import com.cos.blog.action.user.UsersProfileUploadAction;
import com.cos.blog.action.user.UsersProfileUploadProcAction;
import com.cos.blog.action.user.UsersUpdateAction;
import com.cos.blog.action.user.UsersUpdateProcAction;
import com.cos.blog.action.user.UsersUsernameCheckAction;

@WebServlet("/user")
public class UsersController extends HttpServlet {
	private final static String TAG = "UsersController : ";
	private static final long serialVersionUID = 1L;

	public UsersController() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.setAttribute("path", request.getContextPath());
		String cmd = request.getParameter("cmd");
		System.out.println(TAG + "router() : " + cmd);

		Action action = router(cmd);
		action.execute(request, response);

	}

	private Action router(String cmd) {
		if (cmd.equals("join")) {
			// 회원 가입 페이지로 이동
			return new UsersJoinAction();
		} else if (cmd.equals("joinProc")) {
			// 회원 가입 진행 한 후 -> index.jsp로 이동
			return new UsersJoinProcAction();
		} else if (cmd.equals("update")) {
			return new UsersUpdateAction();
			// 회원 수정 페이지로 이동 (세션에 User 오브젝트를 가지고 있을 예정)
		} else if (cmd.equals("updateProc")) {
			return new UsersUpdateProcAction();
			// 회원 수정을 진행 한 후 -> index.jsp로 이동
		} else if (cmd.equals("delete")) {
			// 회원 삭제를 진행 한 후 -> 로그아웃을 하고(세션해지) -> index.jsp로 이동
			// 실제론 회원 탈퇴했는지 안했는지 값을 0이나 1로 바꿔서 표기함
		} else if (cmd.equals("login")) {
			// 회원 로그인 페이지로 이동
			return new UsersLoginAction();
		} else if (cmd.equals("loginProc")) {
			// 회원 로그인을 수행한 후 -> 세션에 등록을 하고 -> index.jsp로 이동
			return new UsersLoginProcAction();
		}else if (cmd.equals("logout")) {
			// 로그아웃
			return new UsersLogoutAction();
		}else if (cmd.equals("usernameCheck")) {
			// 회원이름 중복 확인
			return new UsersUsernameCheckAction();
		}else if (cmd.equals("profileUpload")) {
			// 회원이름 중복 확인
			return new UsersProfileUploadAction();
		}else if (cmd.equals("profileUploadProc")) {
			// 회원이름 중복 확인
			return new UsersProfileUploadProcAction();
		}
		return null;
	}
}
