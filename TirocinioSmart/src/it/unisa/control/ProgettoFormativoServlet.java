package it.unisa.control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import it.unisa.model.AbstractBean;
import it.unisa.model.ProgettoFormativoBean;
import it.unisa.model.ProgettoFormativoModelDM;
import it.unisa.model.StudenteBean;
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
 * Servlet implementation class ProgettoFormativoServlet
 */
@WebServlet("/ProgettoFormativoServlet")
public class ProgettoFormativoServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  /**
  * @see HttpServlet#HttpServlet()
  */
  public ProgettoFormativoServlet() {
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
    ProgettoFormativoBean progettoFormativoBean = null;
    String progettoFormativoID = request.getParameter("id").trim();
    int id = -1;
    
    session = request.getSession(false);
    if (session != null) {
      utenteBean = (AbstractBean) session.getAttribute("SessionUser");
      if (utenteBean.getClass().getName().equals(StudenteBean.class.getName())) {
        studenteBean = (StudenteBean) utenteBean;
        try {
          progettoFormativoBean = (ProgettoFormativoBean) ProgettoFormativoModelDM.INSTANCE.doRetrieveByMatricola(studenteBean.getMatricola());
          request.setAttribute("RequestProgettoFormativoBean", progettoFormativoBean);
        } catch (SQLException e) {
          Logger.getGlobal().log(Level.SEVERE, "Oggetto ProgettoFormativoBean Non Trovato.");
          //redirect to an error page
          //set the error message
          RequestDispatcher view = request.getRequestDispatcher("404-page.jsp");
          view.forward(request, response);
        }
      } else if (utenteBean.getClass().getName().equals(TutorBean.class.getName()) ||
          utenteBean.getClass().getName().equals(UfficioBean.class.getName())) {
        try {
          id = Integer.parseInt(progettoFormativoID);
        } catch (NumberFormatException e) {
          //redirect to an [error] page
          //set the 500 error message
          RequestDispatcher view = request.getRequestDispatcher("500-page.jsp");
          view.forward(request, response);
        }
    	if (id >= 0) {
          try {
            progettoFormativoBean = (ProgettoFormativoBean) ProgettoFormativoModelDM.INSTANCE.doRetrieveByKey(id);
            request.setAttribute("RequestProgettoFormativoBean", progettoFormativoBean);
          } catch (SQLException e) {
            Logger.getGlobal().log(Level.SEVERE, "Oggetto ProgettoFormativoBean Non Trovato.");
            //redirect to an [error] page
            //set the error message
            RequestDispatcher view = request.getRequestDispatcher("404-page.jsp");
            view.forward(request, response);
          }
    	} else {
    	  Logger.getGlobal().log(Level.SEVERE, "ID ProgettoFormativoBean Non Valido.");
          //redirect to an [error] page
          //set the error message
          RequestDispatcher view = request.getRequestDispatcher("500-page.jsp");
          view.forward(request, response);
        }
      } else {
        response.sendRedirect(request.getContextPath() + "/login-page.jsp");
      }
    } else {
      Logger.getGlobal().log(Level.SEVERE, "Oggetto HttpSession Non Trovato.");
      //redirect to an [login] page
      RequestDispatcher view = request.getRequestDispatcher("login-page.jsp");
      view.forward(request, response);
    }
    RequestDispatcher view = request.getRequestDispatcher("progetto-formativo.jsp");
    view.forward(request, response);
  }
}
