package it.unisa.control;

import java.io.IOException;
import java.sql.Date;
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

import it.unisa.model.RegistroBean;
import it.unisa.model.RegistroModelDM;
import it.unisa.model.StudenteBean;
import it.unisa.model.AbstractBean;

/**
 * Servlet implementation class ModificaRegistroTirocinioStudenteServlet
 */
@WebServlet("/ModificaRegistroTirocinioStudenteServlet")
public class ModificaRegistroTirocinioStudenteServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  /**
   * @see HttpServlet#HttpServlet()
   */
  public ModificaRegistroTirocinioStudenteServlet() {
    super();
  }
  
  /**
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
   */
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    doPost(request, response);
  }
  
  /**
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
   */
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    HttpSession session = null;
    RegistroBean registroBean = null;
    StudenteBean studenteBean = null;
    String idRegistro = (String) request.getParameter("id").toString().trim();
    String nome = (String) request.getParameter("nome").toString().trim();
    String descrizione = (String) request.getParameter("descrizione").toString().trim();
    String oldIDRegistro = (String) request.getParameter("old_id").toString().trim();
    Date ultimoAggiornamento = null;
    boolean modifica = false;
    int id = -1;
    int oldID = -1;
    
    if (oldIDRegistro == null || idRegistro == null ||
        nome == null || descrizione == null || nome.equals("")) {
      Logger.getGlobal().log(Level.SEVERE, "Info. Non Valide Nella Modifica Di Un Registro.");
      //redirect to an [error] page
      //set the error message
      RequestDispatcher view = request.getRequestDispatcher("500-page.jsp");
      view.forward(request, response);
    }
    
    try {
      oldID = Integer.valueOf(oldIDRegistro);
      id = Integer.valueOf(idRegistro);
    } catch (NumberFormatException e) {
      Logger.getGlobal().log(Level.SEVERE, "ID RegistroBean Non Valido.");
      //redirect to an [error] page
      //set the error message
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
            if (RegistroModelDM.INSTANCE.isStudenteRegistro(studenteBean.getMatricola(), oldID)) {
              registroBean = (RegistroBean) RegistroModelDM.INSTANCE.doRetrieveByKey(oldID);
            } else {
              Logger.getGlobal().log(Level.SEVERE, "ID RegistroBean Non Valido.");
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
          Logger.getGlobal().log(Level.SEVERE, "ID RegistroBean Non Valido.");
          //redirect to an [error] page
          //set del valore per SessionErrorMessage400
          RequestDispatcher view = request.getRequestDispatcher("404-page.jsp");
          view.forward(request, response);
        }
      }
      
      if (registroBean != null) {
        if (id != registroBean.getID() || !nome.equals(registroBean.getNome()) || !descrizione.equals(registroBean.getDescrizione())) {
          ultimoAggiornamento = new java.sql.Date(new java.util.Date().getTime());
          registroBean.setID(id);
          registroBean.setNome(nome);
          registroBean.setDescrizione(descrizione);
          registroBean.setUltimoAgg(ultimoAggiornamento);
          
          try {
            if (!RegistroModelDM.INSTANCE.doUpdate(registroBean, oldID)) {
              Logger.getGlobal().log(Level.INFO, "Registro Non Aggiornato.");
              //redirect to an [error] page
              //set del valore per SessionErrorMessage500
              RequestDispatcher view = request.getRequestDispatcher("500-page.jsp");
              view.forward(request, response);
            } else {
              modifica = true;
            }
          } catch (SQLException e) {
            Logger.getGlobal().log(Level.SEVERE, e.getMessage());
            //redirect to an [error] page
            //set del valore per SessionErrorMessage500
            RequestDispatcher view = request.getRequestDispatcher("500-page.jsp");
            view.forward(request, response);
          }
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
    if (modifica == true) {
      json.put("modifica", true);
      json.put("ultimo_aggiornamento", ultimoAggiornamento);
    } else {
      json.put("modifica", false);
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
