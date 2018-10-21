package it.unisa.login;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import it.unisa.model.StudenteBean;
import it.unisa.model.StudenteModelDM;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServletStudente extends HttpServlet {
  private static final long serialVersionUID = 1L;

  /**
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    String nome = request.getParameter("login-nome");
    String pass = request.getParameter("login-password");
    String redirectPage = null;
    StudenteBean stud;

    stud = new StudenteBean();
    stud.setNome(nome);
    stud.setPassword(pass);

    if (Validate.checkUser(stud)) {

      try {
        StudenteModelDM.loadInfo(stud);

      } catch (SQLException e) {
        e.printStackTrace();
      }
      request.getSession().setAttribute("Loggato", new Boolean(true));
      request.getSession().setAttribute("SessionUser", stud);
      request.getSession().setAttribute("ErroreLogin", new Boolean(false));
      redirectPage = "/login-success.jsp";

    } else {
      request.getSession().setAttribute("Loggato", new Boolean(false));
      request.getSession().setAttribute("ErroreLogin", new Boolean(true));
      redirectPage = "/login-page.jsp";

    }
    response.sendRedirect(request.getContextPath() + redirectPage);

  }

  /**
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    doGet(request, response);
  }

}
