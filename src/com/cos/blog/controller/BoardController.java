package com.cos.blog.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cos.blog.action.Action;
import com.cos.blog.action.board.BoardDeleteAction;
import com.cos.blog.action.board.BoardDetailAction;
import com.cos.blog.action.board.BoardHomeAction;
import com.cos.blog.action.board.BoardUpdateAction;
import com.cos.blog.action.board.BoardUpdateProcAction;
import com.cos.blog.action.board.BoardWriteAction;
import com.cos.blog.action.board.BoardWriteProcAction;
import com.cos.blog.action.user.UsersJoinAction;
import com.cos.blog.action.user.UsersJoinProcAction;
import com.cos.blog.action.user.UsersLoginAction;

@WebServlet("/board")
public class BoardController extends HttpServlet {
	private final static String TAG = "BoardController : ";
	private static final long serialVersionUID = 1L;

	public BoardController() {
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
		String cmd = request.getParameter("cmd");
		System.out.println(TAG + "router() : " + cmd);

		Action action = router(cmd);
		action.execute(request, response);

	}

	private Action router(String cmd) {
		if (cmd.equals("home")) {
			return new BoardHomeAction(); // 회원 가입 페이지로 이동
		} else if (cmd.equals("write")) {
			return new BoardWriteAction(); // 글쓰기 페이지로 이동
		} else if (cmd.equals("writeProc")) {
			return new BoardWriteProcAction(); // 글쓰고  index로 이동
		}else if (cmd.equals("detail")) {
			return new BoardDetailAction(); // 상세보기
		}else if (cmd.equals("update")) {
			return new BoardUpdateAction(); // 상세보기
		}else if (cmd.equals("updateProc")) {
			return new BoardUpdateProcAction(); // 상세보기
		}else if (cmd.equals("delete")) {
			return new BoardDeleteAction(); // 상세보기
		}
		return null;
	}
}
