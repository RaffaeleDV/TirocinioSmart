package it.unisa.control;

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
import it.unisa.model.ProgettoFormativoBean;
import it.unisa.model.ProgettoFormativoModelDM;
import java.sql.SQLException;

/**
 * Servlet implementation class ApprovazioneProgettoFormativoStudente
 */
@WebServlet("/ApprovazioneProgettoFormativoStudente")
public class ApprovazioneProgettoFormativoStudente extends HttpServlet {
  private static final long serialVersionUID = 1L;
  private static final ProgettoFormativoModelDM progettoFormativoModelDM = new ProgettoFormativoModelDM();   
    
  /**
   * @see HttpServlet#HttpServlet()
   */
  public ApprovazioneProgettoFormativoStudente() {
    super();
    // TODO Auto-generated constructor stub
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
	int id = -1;
	boolean approvazione = false;
	
	getServletContext().setAttribute("ContextProgettoFormativoModelDM", progettoFormativoModelDM);
	
	idProgettoFormativo = (String) request.getParameter("id").toString();
	if (idProgettoFormativo != null) {
	  if (!idProgettoFormativo.equals("")) {
	    id = Integer.valueOf(idProgettoFormativo);
	  } else {
	    Logger.getGlobal().log(Level.INFO, "idProgettoFormativo come stringa vuota nella approvazione di un progetto formativo");
	    //redirect to an [error] page
	    //set del valore per SessionErrorMessage404
	    RequestDispatcher view = request.getRequestDispatcher("404-page.jsp");
	    view.forward(request, response);
	  }
	} else {
	  Logger.getGlobal().log(Level.INFO, "idProgettoFormativo nullo nella approvazione di un progetto formativo");
	  //redirect to an [error] page
	  RequestDispatcher view = request.getRequestDispatcher("404-page.jsp");
	  view.forward(request, response);
	}
	
	session = request.getSession(false);
	if (session != null) {
	  if (id >= 0) {
	    try {
	      progettoFormativoBean = (ProgettoFormativoBean) progettoFormativoModelDM.doRetrieveByKey(id);
	    } catch (SQLException e) {
	      Logger.getGlobal().log(Level.SEVERE, e.getMessage());
	      //redirect to an [error] page
	      //set del valore per SessionErrorMessage500
	      RequestDispatcher view = request.getRequestDispatcher("500-page.jsp");
	      view.forward(request, response);
	    }
	    
	    if (progettoFormativoBean != null) {
	      if (!progettoFormativoBean.getApprovazione()) {
  	        progettoFormativoBean.setApprovazione(true);
  	        try {
  	          if (!progettoFormativoModelDM.doUpdate(progettoFormativoBean)) {
  	            Logger.getGlobal().log(Level.INFO, "ProggettoFormativo non approvato");
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
	        Logger.getGlobal().log(Level.INFO, "Il ProgettoFormativo risulta già approvato nella approvazione di progetto formativo");
	      }
	    } else {
	      Logger.getGlobal().log(Level.INFO, "ProgettoFormativo non trovato con l' id specificato");
	      //redirect to an [error] page
	      //set del valore per SessionErrorMessage404
	      RequestDispatcher view = request.getRequestDispatcher("404-page.jsp");
	      view.forward(request, response);
	    }
	  } else {
	    Logger.getGlobal().log(Level.INFO, "idProgettoFormativo come valore negativo nella approvazione di un progetto formativo");
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