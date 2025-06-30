package com.kh.board;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.board.model.vo.Board;

/**
 * Servlet implementation class BoardInsertServlet
 */
@WebServlet("/board/insert")
public class BoardInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BoardInsertServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/board/insert.jsp").forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String title = request.getParameter("title");
		String nickname = request.getParameter("nickname");
		String content = request.getParameter("content");
		//
		Board b = new Board();
		b.setTitle(title);
		b.setNickname(nickname);
		b.setContent(content);
		b.setCreateDate(new Date());
		
		List<Board> list = (List<Board>)request.getSession().getAttribute("list");
		
		int no = list.stream().mapToInt(board -> b.getNo()).max().getAsInt();
		b.setNo(no+1);
		list.add(b);
		
		response.getWriter().print("service success");
		//
		
		
//		HttpSession session = request.getSession();
//		List<Board> list = (List<Board>)session.getAttribute("list");
//		
//		Board board = new Board(list.size()+1,title,nickname,content,new Date());
//		
//		list.add(board);
//		
//		session.setAttribute("list", list);
//		
//		request.getRequestDispatcher("/board/insert.jsp").forward(request, response);
	}
}
