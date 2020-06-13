<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../include/nav.jsp"%>

<div class="container">
 	<%@ include file="../include/goBack.jsp"%>
	<c:if test="${sessionScope.principal.id == detailDto.boardDto.board.userId}">
		<a href="/blog5/board?cmd=update&id=${detailDto.boardDto.board.userId}" class="btn btn-warning">수정</a>
		<button class="btn btn-danger" onclick="deleteById(${detailDto.boardDto.board.id})">삭제</button>
	</c:if>
	<br />
	<br />

	<h6>
		작성자 : <i>${detailDto.boardDto.username}</i> 조회수 : <i>${detailDto.boardDto.board.readCount}</i>
	</h6>
	<br />

	<h3>
		<b>${detailDto.boardDto.board.title}</b>
	</h3>

	<div class="m-2">${detailDto.boardDto.board.content}</div>
	<hr />
	<!-- 댓글 박스 -->
	<div class="row bootstrap snippets">
		<div class="col-md-12">
			<div class="comment-wrapper">
				<div class="panel panel-info">
					<div class="panel-heading m-2">
						<b>Comment</b>
					</div>
					<div class="panel-body">
						<textarea id="reply__write__form" class="form-control" placeholder="write a comment..." rows="3"></textarea>
						<br>
						<button onclick="replyWrite(${detailDto.boardDto.board.id}, ${sessionScope.principal.id})" type="button" class="btn btn-primary pull-right">댓글쓰기</button>
						<div class="clearfix"></div>
						<hr />
						<!-- 댓글 리스트 시작-->
						<ul id="reply__list" class="media-list">

							<c:forEach var="replyDto" items="${detailDto.replyDtos}">
								<!-- 댓글 아이템 -->
								<li class="media">
									<img onerror="this.scr='/blog/image/userProfile.png'" 
									src="${replyDto.userProfile}" alt="" class="img-circle">
									<div class="media-body">
										<strong class="text-primary">${replyDto.username}</strong>
										<p>
											${replyDto.reply.content}
										</p>
									</div>
									</li>
							</c:forEach>
						</ul>
						<!-- 댓글 리스트 끝-->
					</div>
				</div>
			</div>

		</div>
	</div>
	<!-- 댓글 박스 끝 -->
</div>

<script src="/blog5/js/detail.js"></script>
<script src="/blog5/js/reply.js"></script>
<%@ include file="../include/footer.jsp"%>