package it.unisa.login;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import it.unisa.model.TutorBean;
import it.unisa.model.TutorModelDM;

/**
 * Servlet implementation class LoginServletTutor
 */
@WebServlet("/LoginServletTutor")
public class LoginServletTutor extends HttpServlet {
  private static final long serialVersionUID = 1L;

  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    String nome = request.getParameter("login-nome");
    String pass = request.getParameter("login-password");
    String redirectPage = null;
    TutorBean tut;


    tut = new TutorBean();
    tut.setNome(nome);
    tut.setPassword(pass);


    if (Validate.checkUser(tut)) {

      try {
        TutorModelDM.loadInfo(tut);

      } catch (SQLException e) {
        e.printStackTrace();
      }
      request.getSession().setAttribute("Loggato", new Boolean(true));
      request.getSession().setAttribute("SessionUser", tut);
      request.getSession().setAttribute("ErroreLogin", new Boolean(false));
      redirectPage = "/login-success.jsp";

    } else {
      request.getSession().setAttribute("Loggato", new Boolean(false));
      request.getSession().setAttribute("ErroreLogin", new Boolean(true));
      redirectPage = "/login-page.jsp";

    }
    response.sendRedirect(request.getContextPath() + redirectPage);

  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    doGet(request, response);
  }

}
