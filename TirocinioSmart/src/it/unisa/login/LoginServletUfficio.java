package it.unisa.login;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import it.unisa.model.UfficioBean;
import it.unisa.model.UfficioModelDM;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginServletUfficio
 */
@WebServlet("/LoginServletUfficio")
public class LoginServletUfficio extends HttpServlet {
  private static final long serialVersionUID = 1L;
  
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    String nome = request.getParameter("login-nome");
    String pass = request.getParameter("login-password");
    String redirectPage = null;
    UfficioBean uff;

    uff = new UfficioBean();
    uff.setNome(nome);
    uff.setPass(pass);

    if (Validate.checkUser(uff)) {

      try {
        uff = (UfficioBean) UfficioModelDM.INSTANCE.doRetrieveByNome(nome);
      } catch (SQLException e) {
        Logger.getGlobal().log(Level.SEVERE, e.getMessage());
      }
      request.getSession().setAttribute("Loggato", Boolean.valueOf(true));
      request.getSession().setAttribute("SessionUser", uff);
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
    // TODO Auto-generated method stub
    doGet(request, response);
  }

}
