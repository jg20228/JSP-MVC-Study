/**
 * 
 */

function deleteById(boardId){
	$.ajax({
		type : "POST",
		url : `/blog5/board?cmd=delete&id=${boardId}`,
		dataType : "text"
	}).done(function(result){
		if(result==='1'){
			alert("삭제 성공");
			location.href="/blog5/index.jsp";
		} else{
			alert("삭제 실패");
		}
		
	}).fail(function(error){
		alert("서버 오류");
	});
}