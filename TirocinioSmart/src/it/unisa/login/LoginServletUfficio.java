package it.unisa.login;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import it.unisa.model.UfficioBean;
import it.unisa.model.UfficioModelDM;

/**
 * Servlet implementation class LoginServletUfficio
 */
@WebServlet("/LoginServletUfficio")
public class LoginServletUfficio extends HttpServlet {
  private static final long serialVersionUID = 1L;
  private static final UfficioModelDM ufficioModelDM = new UfficioModelDM();
  
  

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
        uff = (UfficioBean) ufficioModelDM.doRetrieveByNome(nome);

      } catch (SQLException e) {
        e.printStackTrace();
      }
      request.getSession().setAttribute("Loggato", new Boolean(true));
      request.getSession().setAttribute("SessionUser", uff);
      request.getSession().setAttribute("ErroreLogin", new Boolean(false));
      redirectPage = "/home-page.jsp";

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
    // TODO Auto-generated method stub
    doGet(request, response);
  }

}
