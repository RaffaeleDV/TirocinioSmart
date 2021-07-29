package it.unisa.logout;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.json.JSONObject;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/LogoutUfficioServlet")
public class LogoutUfficioServlet extends HttpServlet {
  /**
   * 
   */
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
        request.setAttribute("ErroreLogout", Boolean.valueOf(true));
        response.sendRedirect(request.getContextPath() + redirectPage);
        Logger.getGlobal().log(Level.SEVERE, "Logout Without Login");
      }
    } else {
      redirectPage = "500-error.jsp";
      request.setAttribute("ErroreLogout", Boolean.valueOf(true));
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
