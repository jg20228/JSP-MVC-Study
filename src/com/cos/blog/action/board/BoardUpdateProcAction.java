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

public class BoardUpdateProcAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//인증 확인
		HttpSession session = request.getSession();
		if(session.getAttribute("principal")==null) {
			Script.getMessage("잘못된 접근입니다.", response);
			return;
		}
		//id, title과 content 값이 null인지 확인
		if(
			request.getParameter("id").equals("")||
			request.getParameter("id") ==null||
			request.getParameter("title").equals("")||
			request.getParameter("title") ==null||
			request.getParameter("content").equals("")||
			request.getParameter("content") ==null) {
			return;
		}
		// id, title과 content 값 받기
		int id = Integer.parseInt(request.getParameter("id"));
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		
		// board 오브젝트에 담기
		Board board = Board.builder()
				.id(id)
				.title(title)
				.content(content)
				.build();
		
		BoardRepository boardRepository = BoardRepository.getInstance();
		int result = boardRepository.update(board);
		
		if (result==1) {
			Script.href("수정 성공", "/blog5/board?cmd=detail&id="+id, response);
		}else {
			Script.back("수정 실패", response);
		}
	}
}
