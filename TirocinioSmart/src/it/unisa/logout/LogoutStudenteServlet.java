package it.unisa.logout;

import java.io.IOException;
import java.util.logging.Logger;
import java.util.logging.Level;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

@WebServlet("/LogoutStudenteServlet")
public class LogoutStudenteServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  /*
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    HttpSession session = null;
    String redirectPage = "login-page.jsp";
    boolean loggato = false;
    boolean logout = false;
    
    if (request.getSession() != null) {
      session = request.getSession();
      loggato = (boolean) session.getAttribute("Loggato");
      if (loggato == true) {
        session.invalidate();
        request.logout();
        redirectPage = "login-page.jsp";
      } else {
        redirectPage = "500-error.jsp";
        request.setAttribute("ErroreLogout", new Boolean(true));
        response.sendRedirect(request.getContextPath() + redirectPage);
        Logger.getGlobal().log(Level.SEVERE, "Logout Senza Un Login.");
      }
    } else {
      redirectPage = "500-error.jsp";
      request.setAttribute("ErroreLogout", new Boolean(true));
      response.sendRedirect(request.getContextPath() + redirectPage);
      Logger.getGlobal().log(Level.SEVERE, "Logout Without Session");
    }
    
    JSONObject json = new JSONObject();
    if (logout == true) {
      json.put("logout", "true");
    } else {
      json.put("logout", "false");
    }
    response.setContentType("application/json");
    try {
      response.getWriter().print(json.toString());
    } catch(IOException e) {
      Logger.getGlobal().log(Level.SEVERE, e.getMessage());
      //redirect to an [error] page
      //set del valore per SessionErrorMessage500
      RequestDispatcher view = request.getRequestDispatcher("500-page.jsp");
      view.forward(request, response);
    }
  }
  
  /*
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doGet(request, response);
  }
}
