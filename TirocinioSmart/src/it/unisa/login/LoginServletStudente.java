package it.unisa.login;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import it.unisa.model.StudenteBean;
import it.unisa.model.StudenteModelDM;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServletStudente")
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
    stud.setPass(pass);

    if (Validate.checkUser(stud)) {
      
      try {
        stud = (StudenteBean) StudenteModelDM.INSTANCE.doRetrieveByNome(nome);

      } catch (SQLException e) {
        Logger.getGlobal().log(Level.SEVERE, e.getMessage());
      }
      request.getSession().setAttribute("Loggato", Boolean.valueOf(true));//true
      request.getSession().setAttribute("SessionUser", stud);
      request.getSession().setAttribute("ErroreLogin", Boolean.valueOf(false));
      redirectPage = "/home-page.jsp";

    } else {
      request.getSession().setAttribute("Loggato", Boolean.valueOf(false));
      request.getSession().setAttribute("ErroreLogin", Boolean.valueOf(true));
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
