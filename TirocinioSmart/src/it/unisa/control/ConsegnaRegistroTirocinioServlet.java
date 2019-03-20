package it.unisa.control;

import java.io.IOException;
import java.sql.SQLException;
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
import it.unisa.model.AbstractBean;
import it.unisa.model.RegistroBean;
import it.unisa.model.RegistroModelDM;
import it.unisa.model.StudenteBean;

/**
 * Servlet implementation class ConsegnaRegistroTirocinioServlet
 */
@WebServlet("/ConsegnaRegistroTirocinioServlet")
public class ConsegnaRegistroTirocinioServlet extends HttpServlet {
  
  private static final RegistroModelDM registroModelDM = new RegistroModelDM();
  
  /**
   * @see HttpServlet#HttpServlet()
   */
  public ConsegnaRegistroTirocinioServlet() {
    super();
  }
  
  /**
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
   */
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doPost(request, response);
  }
  
  /**
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
   */
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    HttpSession session = null;
    StudenteBean studenteBean = null;
    RegistroBean registroBean = null;
    String idRegistro = null;
    boolean consegna = false;
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
        if (!userBean.getClass().getName().equals(StudenteBean.class.getName())) {
          Logger.getGlobal().log(Level.INFO, "L' utente non risulta loggato come studente");
          RequestDispatcher view = request.getRequestDispatcher("login-page.jsp");
          view.forward(request, response);
        } else {
          studenteBean = (StudenteBean) userBean;
        }
      } else {
        Logger.getGlobal().log(Level.INFO, "Nessun utente loggato");
        RequestDispatcher view = request.getRequestDispatcher("login-page.jsp");
        view.forward(request, response);
      }
      
      if (registroBean == null) {
        if (id >= 0) {
          try {
            if (registroModelDM.isStudenteRegistro(studenteBean.getMatricola(), id)) {
              registroBean = (RegistroBean) registroModelDM.doRetrieveByKey(id);
            } else {
              Logger.getGlobal().log(Level.INFO, "Il registro con id: " + id + " non risulta dello studente nella consegna da parte di uno studente");
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
          Logger.getGlobal().log(Level.INFO, "ID registro negativo nella consegna da parte di uno studente");
          //redirect to an [error] page
          //set del valore per SessionErrorMessage404
          RequestDispatcher view = request.getRequestDispatcher("404-page.jsp");
          view.forward(request, response);
        }
      }
      
      if (registroBean != null) {
        if (!registroBean.getConsegna()) {
          registroBean.setConsegna(true);
          try {
            if (!registroModelDM.doUpdate(registroBean)) {
              Logger.getGlobal().log(Level.INFO, "Registro non aggiornato nella consegna del registro da parte di uno studente");
              //redirect to an [error] page
              //set del valore per SessionErrorMessage500
              RequestDispatcher view = request.getRequestDispatcher("500-page.jsp");
              view.forward(request, response);
            } else {
              consegna = true;
            }
          } catch (SQLException e) {
            Logger.getGlobal().log(Level.SEVERE, e.getMessage());
            //redirect to an [error] page
            //set del valore per SessionErrorMessage500
            RequestDispatcher view = request.getRequestDispatcher("500-page.jsp");
            view.forward(request, response);
          }
        } else {
          Logger.getGlobal().log(Level.INFO, "Il registro risulta già consegnato nella consegna da parte di uno studente");
        }
      } else {
        Logger.getGlobal().log(Level.INFO, "Nessuno registro trovato con l' id specificato nella consegna di registro da parte dello studente");
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
    
    JSONObject json = new JSONObject();
    if (consegna) {
      json.put("consegna", "true");
    } else {
      json.put("consegna", "false");
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
}
