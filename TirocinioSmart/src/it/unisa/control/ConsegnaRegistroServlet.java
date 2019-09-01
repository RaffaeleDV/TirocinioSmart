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
@WebServlet("/ConsegnaRegistroServlet")
public class ConsegnaRegistroServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;
  
  /**
   * @see HttpServlet#HttpServlet()
   */
  public ConsegnaRegistroServlet() {
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
    
    idRegistro = (String) request.getParameter("id").toString();
    if (idRegistro != null) {
      if (!idRegistro.equals("")) {
        id = Integer.valueOf(idRegistro);
      } else {
        Logger.getGlobal().log(Level.INFO, "ID RegistroBean Non Valido.");
        //redirect to an [error] page
        //set del valore per SessionErrorMessage404
        RequestDispatcher view = request.getRequestDispatcher("404-page.jsp");
        view.forward(request, response);
      }
    } else {
      Logger.getGlobal().log(Level.INFO, "ID RegistroBean Non Valido.");
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
          RequestDispatcher view = request.getRequestDispatcher("login-page.jsp");
          view.forward(request, response);
        } else {
          studenteBean = (StudenteBean) userBean;
        }
      } else {
        RequestDispatcher view = request.getRequestDispatcher("login-page.jsp");
        view.forward(request, response);
      }
      
      if (registroBean == null) {
        if (id >= 0) {
          try {
            if (RegistroModelDM.INSTANCE.isStudenteRegistro(studenteBean.getMatricola(), id)) {
              registroBean = (RegistroBean) RegistroModelDM.INSTANCE.doRetrieveByKey(id);
            } else {
              Logger.getGlobal().log(Level.INFO, "ID RegistroBean Non Valido.");
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
          Logger.getGlobal().log(Level.INFO, "ID RegistroBean Non Valido.");
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
            if (!RegistroModelDM.INSTANCE.doUpdate(registroBean, registroBean.getID())) {
              Logger.getGlobal().log(Level.INFO, "Oggetto RegistroBean Non Aggiornato.");
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
          Logger.getGlobal().log(Level.INFO, "Oggetto RegistroBean Risulta Già Consegnato.");
        }
      } else {
        Logger.getGlobal().log(Level.INFO, "Oggetto RegistroBean Non Trovato.");
        //redirect to an [error] page
        //set del valore per SessionErrorMessage404
        RequestDispatcher view = request.getRequestDispatcher("404-page.jsp");
        view.forward(request, response);
      }
    } else {
      Logger.getGlobal().log(Level.INFO, "Oggetto HttpSession Non Trovato.");
      RequestDispatcher view = request.getRequestDispatcher("login-page.jsp");
      view.forward(request, response);
    }
    
    JSONObject json = new JSONObject();
    if (consegna) {
      json.put("consegna", true);
    } else {
      json.put("consegna", false);
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
