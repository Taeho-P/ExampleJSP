package servlet;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LifeCycle
 */
@WebServlet("/13Servlet/LifeCycle.do")
public class LifeCycle extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		System.out.println("init() 호출");
	}

	public void myPostConstruct() {
		System.out.println("myPostConstruct() 호출");
	}
	
	public void destroy() {
		System.out.println("destroy() 호출");
	}

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("service() 호출");
		super.service(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet() 호출");
		request.getRequestDispatcher("/13Servlet/LifeCycle.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doPost() 호출");
		request.getRequestDispatcher("/13Servlet/LifeCycle.jsp").forward(request, response);
	}
	
	public void myPreDestroy() {
		System.out.println("myPreDestroy() 호출");
	}

}
