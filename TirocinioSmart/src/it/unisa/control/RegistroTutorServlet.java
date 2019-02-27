package it.unisa.control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import it.unisa.model.RegistroBean;
import it.unisa.model.RegistroModelDM;
import it.unisa.model.StudenteBean;
import it.unisa.model.TutorBean;
import it.unisa.model.UfficioBean;
import it.unisa.model.AbstractBean;

/**
 * Servlet implementation class RegistroTutorServlet
 */
@WebServlet("/RegistroTutorServlet")
public class RegistroTutorServlet extends HttpServlet {

  private static final RegistroModelDM registroModelDM = new RegistroModelDM();
  
  
  /**
   * * @see HttpServlet#HttpServlet()
   * */
  public RegistroTutorServlet() {
    super();
  }
  
  /**
   * * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
   * */
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doPost(request, response);
  }

  /**
   * * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
   * */
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    HttpSession session = null;
    RegistroBean registroBean = null;
    TutorBean tutorBean = null;
    String idRegistro = null;
    int id = -1;
    
    getServletContext().setAttribute("ContextRegistroModelDM", registroModelDM);
    
    idRegistro = (String) request.getParameter("id").toString();
    if (idRegistro != null) {
      if (!idRegistro.equals("")) {
        id = Integer.valueOf(idRegistro);
      } else {
        Logger.getGlobal().log(Level.INFO, "idRegistro stringa vuota nella consegna di un registro da parte di uno studente");
        //redirect to an [error] page
        //set del valore per SessionErrorMessage404
        RequestDispatcher view = request.getRequestDispatcher("404-page.jsp");
        view.forward(request, response);
      }
    } else {
      Logger.getGlobal().log(Level.INFO, "idRegistro nullo nella consegna di un registo da parte di uno studente");
      //redirect to an [error] page
      //set del valore per SessionErrorMessage404
      RequestDispatcher view = request.getRequestDispatcher("404-page.jsp");
      view.forward(request, response);
    }
    
    session = request.getSession(false);
    if (session != null) {
      AbstractBean userBean = (AbstractBean) session.getAttribute("SessionUser");
      
      if (userBean != null) {
        if (!userBean.getClass().getName().equals(TutorBean.class.getName())) {
          Logger.getGlobal().log(Level.INFO, "L' utente non risulta loggato come tutor");
          RequestDispatcher view = request.getRequestDispatcher("login-page.jsp");
          view.forward(request, response);
        } else {
          tutorBean = (TutorBean) userBean;
        }
      } else {
        Logger.getGlobal().log(Level.INFO, "Nessun utente loggato");
        RequestDispatcher view = request.getRequestDispatcher("login-page.jsp");
        view.forward(request, response);
      }
      
      if (id >= 0) {
        try {
          if (tutorBean.getTipo().equals("Accademico")) {
            if (registroModelDM.isTutorAccRegistro(tutorBean.getID(), id)) {
              registroBean = (RegistroBean) registroModelDM.doRetrieveByKey(id);
            }
          } else if (tutorBean.getTipo().equals("Aziendale")) {
            if (registroModelDM.isTutorAzRegistro(tutorBean.getID(), id)) {
              registroBean = (RegistroBean) registroModelDM.doRetrieveByKey(id);
            }
          } else {
            Logger.getGlobal().log(Level.INFO, "Tipo di tutor non valido nella visualizzazione di un registro da parte del tutor");
            //redirect to an [error] page
          }
          
          if (registroBean == null) {
            Logger.getGlobal().log(Level.INFO, "Registro non trovato con l' id specificato nell' accesso da parte di un tutor");
            //redirect to an [error] page
            //set del valore per SessionErrorMessage404
            RequestDispatcher view = request.getRequestDispatcher("404-page.jsp");
            view.forward(request, response);
          }
        } catch(SQLException e) {
          Logger.getGlobal().log(Level.SEVERE, e.getMessage());
          //redirect to an [error] page
          //set del valore per SessionErrorMessage500
          RequestDispatcher view = request.getRequestDispatcher("500-page.jsp");
          view.forward(request, response);
        }
      } else {
        Logger.getGlobal().log(Level.INFO, "ID negativo nella visualizzazione di un registro da parte di un tutor");
        //redirect to an [error] page
        //set del valore per SessionErrorMessage404
        RequestDispatcher view = request.getRequestDispatcher("404-page.jsp");
        view.forward(request, response);
      }        
    } else {
      Logger.getGlobal().log(Level.INFO, "Nessuna sessione nella ricerca di tirocini da parte di un tutor");
      RequestDispatcher view = request.getRequestDispatcher("login-page.jsp");
      view.forward(request, response);
    }
    
    Logger.getGlobal().log(Level.INFO, "Registro Da Visualizzare: " + registroBean.toString());
    RequestDispatcher view = request.getRequestDispatcher("registro-page.jsp");
    view.forward(request, response);
  }
}