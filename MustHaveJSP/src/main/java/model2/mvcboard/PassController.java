package model2.mvcboard;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fileupload.FileUtil;
import utils.JSFunction;

/**
 * Servlet implementation class PassController
 */
@WebServlet("/mvcboard/pass.do")
public class PassController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("mode", req.getParameter("mode"));
		req.getRequestDispatcher("/14MVCBoard/Pass.jsp").forward(req,resp);
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//매개변수 저장
		String idx = req.getParameter("idx");
		String mode = req.getParameter("mode");
		String pass = req.getParameter("pass");
		
		System.out.println("PassController.java - 33 lines --------------------");
		System.out.println("[" + idx + "]");
		System.out.println("[" + mode + "]");
		System.out.println("[" + pass + "]");
		
		//비밀번호 확인
		MVCBoardDAO dao = new MVCBoardDAO();
		boolean confirmed = dao.confirmPassword(pass, idx);
		dao.close();
		
		if (confirmed) { //비밀번호 일치
			//수정 모드
			if (mode.equals("edit")) {
				HttpSession session = req.getSession();
				session.setAttribute("pass", pass);
				resp.sendRedirect("../mvcboard/edit.do?idx=" + idx);
			} else if (mode.equals("delete")) { //삭제 모드
				dao = new MVCBoardDAO();
				MVCBoardDTO dto = dao.selectView(idx);
				int result = dao.deletePost(idx); //게시물 삭제
				dao.close();
				if (result == 1) { //게시물 삭제 성공 시 첨부파일도 삭제
					String saveFileName = dto.getSfile();
					FileUtil.deleteFile(req, "/Uploads", saveFileName);
				}
				System.out.println("58 lines --------------------------");
				JSFunction.alertLocation(resp, "삭제되었습니다.", "../mvcboard/list.do");
			}
		} else { //비밀번호 불일치
			JSFunction.alertBack(resp, "비밀번호 검증에 실패했습니다.");
		}
		
	}

}
