package it.unisa.control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import it.unisa.model.AbstractBean;
import it.unisa.model.RegistroBean;
import it.unisa.model.RegistroModelDM;
import it.unisa.model.StudenteBean;
import it.unisa.model.StudenteModelDM;
import it.unisa.model.TutorBean;
import it.unisa.model.UfficioBean;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class RegistroServlet
 */
@WebServlet("/RegistroServlet")
public class RegistroServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;
  
  /**
   * @see HttpServlet#HttpServlet()
   */
  public RegistroServlet() {
    super();
    // TODO Auto-generated constructor stub
  }

  /**
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // TODO Auto-generated method stub
    doPost(request, response);
  }

  /**
    * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
    */
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    HttpSession session = null;
    AbstractBean utenteBean = null;
    StudenteBean studenteBean = null;
    RegistroBean registroBean = null;
    String registroID = request.getParameter("id").trim();
    int id = -1;
    
    session = request.getSession(false);
    if (session != null) {
      utenteBean = (AbstractBean) session.getAttribute("SessionUser");
      if (utenteBean.getClass().getName().equals(StudenteBean.class.getName())) {
        studenteBean = (StudenteBean) utenteBean;
        try {
          registroBean = (RegistroBean) StudenteModelDM.INSTANCE.doRetrieveRegistro(studenteBean);
          request.setAttribute("RequestRegistroBean", registroBean);
        } catch(SQLException e) {
          Logger.getGlobal().log(Level.SEVERE, "Oggetto RegistroBean Non Trovato.");
          //redirect to an [error] page
          //set the error message
          RequestDispatcher view = request.getRequestDispatcher("404-page.jsp");
          view.forward(request, response);
        }
      } else if (utenteBean.getClass().getName().equals(TutorBean.class.getName()) ||
          utenteBean.getClass().getName().equals(UfficioBean.class.getName())) {
        try {
          id = Integer.valueOf(registroID);
        } catch (NumberFormatException e) {
          RequestDispatcher view = request.getRequestDispatcher("500-page.jsp");
          view.forward(request, response);
        }
        if (id >= 0) {
          try {
            registroBean = (RegistroBean) RegistroModelDM.INSTANCE.doRetrieveByKey(id);
            request.setAttribute("RequestRegistroBean", registroBean);
          } catch (SQLException e) {
            Logger.getGlobal().log(Level.SEVERE, "Oggetto RegistroBean Non Trovato.");
            //redirect to an [error] page
            //set the 404 error message
            RequestDispatcher view = request.getRequestDispatcher("404-page.jsp");
            view.forward(request, response);
          }
        } else {
          Logger.getGlobal().log(Level.SEVERE, "ID RegistroBean Non Valido.");
          //redirect to an [error] page
          //set the error message
          RequestDispatcher view = request.getRequestDispatcher("500-page.jsp");
          view.forward(request, response);
        }
      } else {
        RequestDispatcher view = request.getRequestDispatcher("login-page.jsp");
        view.forward(request, response);
      }
    } else {
      Logger.getGlobal().log(Level.SEVERE, "Oggetto HttpSession Non Trovato.");
      //redirect to an [error] page
      RequestDispatcher view = request.getRequestDispatcher("login-page.jsp");
      view.forward(request, response);
    }
    response.sendRedirect(request.getContextPath() + "/registro-page.jsp");
  }
}
