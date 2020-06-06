package com.cos.blog.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cos.blog.action.Action;
import com.cos.blog.model.Board;
import com.cos.blog.model.Users;
import com.cos.blog.repository.BoardRepository;
import com.cos.blog.util.Script;

public class BoardWriteProcAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//인증 확인
		HttpSession session = request.getSession();
		if(session.getAttribute("principal")==null) {
			Script.getMessage("잘못된 접급입니다.", response);
			return;
		}
		//세션에서 값 가져오기
		Users principal = (Users) session.getAttribute("principal");
		
		//title과 content 값이 null인지 확인
		if(request.getParameter("title").equals("")||
			request.getParameter("title") ==null||
			request.getParameter("content").equals("")||
			request.getParameter("content") ==null) {
			return;
		}
		// title과 content 값 받기
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		
		// board 오브젝트에 담기
		Board board = Board.builder()
				.userId(principal.getId())
				.title(title)
				.content(content)
				.readCount(0)
				.build();
		
		BoardRepository boardRepository = BoardRepository.getInstance();
		int result = boardRepository.save(board);
		
		if (result==1) {
			Script.href("글쓰기 성공", "index.jsp",response);
		}else {
			Script.back("글쓰기 실패", response);
		}
	}
}
