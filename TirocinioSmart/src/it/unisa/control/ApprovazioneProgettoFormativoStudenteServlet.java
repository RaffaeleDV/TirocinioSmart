package it.unisa.control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.json.JSONObject;

import it.unisa.model.AbstractBean;
import it.unisa.model.ProgettoFormativoBean;
import it.unisa.model.ProgettoFormativoModelDM;
import it.unisa.model.TutorBean;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class ApprovazioneProgettoFormativoStudente
 */
@WebServlet("/ApprovazioneProgettoFormativoStudente")
public class ApprovazioneProgettoFormativoStudenteServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;
  
  /**
   * @see HttpServlet#HttpServlet()
   */
  public ApprovazioneProgettoFormativoStudenteServlet() {
    super();
  }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doPost(request, response);
  }

  

  /**
    * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
    */

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // TODO Auto-generated method stub
    HttpSession session = null;
    ProgettoFormativoBean progettoFormativoBean = null;
    String idProgettoFormativo = null;
    TutorBean tutorBean = null;
    AbstractBean utenteBean = null;
    int id = -1;
    boolean approvazione = false;
	
    idProgettoFormativo = (String) request.getParameter("id").toString();
    if (idProgettoFormativo != null) {
      if (!idProgettoFormativo.equals("")) {
        id = Integer.valueOf(idProgettoFormativo);
      } else {
        Logger.getGlobal().log(Level.INFO, "ID progetto formativo non valido.");
        //redirect to an [error] page
        //set del valore per SessionErrorMessage404
        RequestDispatcher view = request.getRequestDispatcher("404-page.jsp");
        view.forward(request, response);
      }
    } else {
      Logger.getGlobal().log(Level.INFO, "ID progetto formativo non valido.");
      //redirect to an [error] page
      RequestDispatcher view = request.getRequestDispatcher("404-page.jsp");
      view.forward(request, response);
    }
	
    session = request.getSession(false);
    if (session != null) {
      utenteBean = (AbstractBean) session.getAttribute("SessionUser");
      if (utenteBean.getClass().equals(TutorBean.class.getName())) {
        tutorBean = (TutorBean) utenteBean;
      }
      if (id >= 0) {
        try {
          progettoFormativoBean = (ProgettoFormativoBean) ProgettoFormativoModelDM.INSTANCE.doRetrieveByKey(id);
        } catch (SQLException e) {
          Logger.getGlobal().log(Level.SEVERE, e.getMessage());
          //redirect to an [error] page
          //set del valore per SessionErrorMessage500
          RequestDispatcher view = request.getRequestDispatcher("500-page.jsp");
          view.forward(request, response);
        }
        
        if (progettoFormativoBean != null) {
          if (tutorBean != null) {
            if (tutorBean.getTipo().equals("Aziendale")) {
              if (!progettoFormativoBean.isApprovazioneTutorAz()) {
                progettoFormativoBean.setApprovazioneTutorAz(true);
                try {
                  if (!ProgettoFormativoModelDM.INSTANCE.doUpdate(progettoFormativoBean, progettoFormativoBean.getID())) {
                    Logger.getGlobal().log(Level.INFO, "Proggetto formativo non approvato.");
                    //redirect to an [error] page
                    //set del valore per SessionErrorMessage500
                    RequestDispatcher view = request.getRequestDispatcher("500-page.jsp");
                    view.forward(request, response);
                  } else {
                    approvazione = true;
                  }
                } catch (SQLException e) {
                  Logger.getGlobal().log(Level.SEVERE, e.getMessage());
                  //redirect to an [error] page
                  //set del valore per SessionErrorMessage500
                  RequestDispatcher view = request.getRequestDispatcher("500-page.jsp");
                  view.forward(request, response);
                }
              } else {
                Logger.getGlobal().log(Level.INFO, "Il ProgettoFormativo risulta gia' approvato.");
              }
            } else if (tutorBean.getTipo().equals("Accademico")) {
              if (!progettoFormativoBean.isApprovazioneTutorAcc()) {
          	    progettoFormativoBean.setApprovazioneTutorAcc(true);
                try {
       	          if (!ProgettoFormativoModelDM.INSTANCE.doUpdate(progettoFormativoBean, progettoFormativoBean.getID())) {
          	        Logger.getGlobal().log(Level.INFO, "Proggetto formativo non approvato.");
          	        //redirect to an [error] page
          	        //set del valore per SessionErrorMessage500
          	        RequestDispatcher view = request.getRequestDispatcher("500-page.jsp");
          	        view.forward(request, response);
          	      } else {
          	        approvazione = true;
          	      }
          	    } catch (SQLException e) {
          	      Logger.getGlobal().log(Level.SEVERE, e.getMessage());
          	      //redirect to an [error] page
          	      //set del valore per SessionErrorMessage500
          	      RequestDispatcher view = request.getRequestDispatcher("500-page.jsp");
                  view.forward(request, response);
          	    }
        	  } else {
        	    Logger.getGlobal().log(Level.INFO, "Il progetto formativo risulta gia' approvato.");
        	  }
            } else {
              Logger.getGlobal().log(Level.SEVERE, "Istanza di TutorBean non valida.");
              //redirect to an [error] page
    	      //set del valore per SessionErrorMessage404
    	      RequestDispatcher view = request.getRequestDispatcher("404-page.jsp");
    	      view.forward(request, response);
            }
          } else {
            //redirect to an [login] page
            RequestDispatcher view = request.getRequestDispatcher("login-page.jsp");
            view.forward(request, response);
          }
	    } else {
	      Logger.getGlobal().log(Level.INFO, "Progetto formativo non trovato.");
	      //redirect to an [error] page
	      //set del valore per SessionErrorMessage404
	      RequestDispatcher view = request.getRequestDispatcher("404-page.jsp");
	      view.forward(request, response);
	    }
	  } else {
	    Logger.getGlobal().log(Level.INFO, "ID progetto formativo non valido.");
	    //redirect to an [error] page
	    //set del valore per SessionErrorMessage404
	    RequestDispatcher view = request.getRequestDispatcher("404-page.jsp");
        view.forward(request, response);
	  }
	} else {
	  //redirect to an [login] page
	  RequestDispatcher view = request.getRequestDispatcher("login-page.jsp");
	  view.forward(request, response);
	}
	
	JSONObject json = new JSONObject();
	if (approvazione) {
	  json.put("approvazione", true);
	} else {
	  json.put("approvazione", false);
	}
	response.setContentType("application/json");
	try {
	  response.getWriter().print(json.toString());
	} catch (IOException e) {
	  Logger.getGlobal().log(Level.SEVERE, e.getMessage());
	  //redirect to an [error] page
	  //set del valore per SessionErrorMessage500
	  RequestDispatcher view = request.getRequestDispatcher("500-page.jsp");
      view.forward(request, response);
	}
  }
}