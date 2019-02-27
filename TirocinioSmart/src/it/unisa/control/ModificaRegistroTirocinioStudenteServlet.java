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

  private static final RegistroModelDM registroModelDM = new RegistroModelDM();
  
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
    String oldIDRegistro = (String) request.getParameter("old_id").toString();
    String idRegistro = (String) request.getParameter("id").toString();
    String nome = (String) request.getParameter("nome").toString();
    String descrizione = (String) request.getParameter("descrizione").toString();
    Date ultimoAggiornamento = null;
    boolean modifica = false;
    int id = -1;
    int oldID = -1;
    
    Logger.getGlobal().log(Level.INFO, "Richiesta di modifica di un registro di tiroinio di uno studente");
    
    getServletContext().setAttribute("ContextRegistroModelDM", registroModelDM);
    
    if (idRegistro != null) {
      if (!idRegistro.equals("")) {
        try {
          id = Integer.valueOf(idRegistro);
        } catch (NumberFormatException e) {
          Logger.getGlobal().log(Level.SEVERE, e.getMessage());
          //redirect to an [error] page
          //set del valore per SessionErrorMessage404
          RequestDispatcher view = request.getRequestDispatcher("404-page.jsp");
          view.forward(request, response);
        }
      } else {
        Logger.getGlobal().log(Level.INFO, "idRegistro come stringa vuota nella modifica di un registro da parte di uno studente");
        //redirect to an [error] page
        //set del valore per SessionErrorMessage404
        RequestDispatcher view = request.getRequestDispatcher("404-page.jsp");
        view.forward(request, response);
      }
    } else {
      Logger.getGlobal().log(Level.INFO, "idRegistro nullo nella modifica di un registro da parte di uno studente");
      //redirect to an [error] page
      //set del valore per SessionErrorMessage404
      RequestDispatcher view = request.getRequestDispatcher("404-page.jsp");
      view.forward(request, response);
    }
    
    if (nome == null) {
      if (nome.equals("")) {
        Logger.getGlobal().log(Level.INFO, "Nome come stringa vuota nella modifica di un registro");
        //redirect to an [error] page
        //set del valore per SessionErrorMessage404
        RequestDispatcher view = request.getRequestDispatcher("404-page.jsp");
        view.forward(request, response);
      }
    } else {
      Logger.getGlobal().log(Level.INFO, "Nome come valore nullo nella modifica di un registro");
      //redirect to an [error] page
      //set del valore per SessionErrorMessage404
      RequestDispatcher view = request.getRequestDispatcher("404-page.jsp");
      view.forward(request, response);      
    }
    
    if (descrizione == null) {
      if (descrizione.equals("")) {
        Logger.getGlobal().log(Level.INFO, "Descrizione come stringa vuota nella modifica di un registro");
        //redirect to an [error] page
        //set del valore per SessionErrorMessage404
        RequestDispatcher view = request.getRequestDispatcher("404-page.jsp");
        view.forward(request, response);
      }
    } else {
      Logger.getGlobal().log(Level.INFO, "Descrizione come stringa nulla nella modifica di un registro");
      //redirect to an [error] page
      //set del valore per SessionErrorMessage404
      RequestDispatcher view  = request.getRequestDispatcher("404-page.jsp");
      view.forward(request, response);
    }
    
    if (oldIDRegistro != null) {
      if (!oldIDRegistro.equals("")) {
        oldID = Integer.valueOf(oldIDRegistro);
      } else {
        Logger.getGlobal().log(Level.INFO, "oldIDRegistro come stringa vuota nella modifica di un registro da parte di uno studente");
        //redirect to an [error] page
        //set del valore per SessionErrorMessage404
        RequestDispatcher view = request.getRequestDispatcher("404-page.jsp");
        view.forward(request, response);
      }
    } else {
      Logger.getGlobal().log(Level.INFO, "oldIDRegistro nullo nella modifica di un registro da parte di uno studente");
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
            if (registroModelDM.isStudenteRegistro(studenteBean.getMatricola(), oldID)) {
              registroBean = (RegistroBean) registroModelDM.doRetrieveByKey(oldID);
            } else {
              Logger.getGlobal().log(Level.INFO, "Il registro non risulta dello studente nella modifica da parte di uno studente");
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
          Logger.getGlobal().log(Level.INFO, "id di registro negativo nella modifica di un registro da parte di uno studente");
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
            if (!registroModelDM.doUpdate(registroBean)) {
              Logger.getGlobal().log(Level.INFO, "Registro non aggiornato nella consegna del registro da parte di uno studente");
              //redirect to an [error] page
              //set del valore per SessionErrorMessage500
              RequestDispatcher view = request.getRequestDispatcher("500-page.jsp");
              view.forward(request, response);
            } 
          } catch (SQLException e) {
            Logger.getGlobal().log(Level.SEVERE, e.getMessage());
            //redirect to an [error] page
            //set del valore per SessionErrorMessage500
            RequestDispatcher view = request.getRequestDispatcher("500-page.jsp");
            view.forward(request, response);
          }
          modifica = true;
        }
      } else {
        modifica = false;
        Logger.getGlobal().log(Level.INFO, "studente o registro nulli nella modifica di un registro da parte di uno studente");
        //redirect to an [error] page
        //set del valore per SessionErrorMessage404
        RequestDispatcher view = request.getRequestDispatcher("404-page.jsp");
        view.forward(request, response);
      }
    } else {
      Logger.getGlobal().log(Level.INFO, "Nessuna session nella modifica di registro da parte di uno studente");
      RequestDispatcher view = request.getRequestDispatcher("login-page.jsp");
      view.forward(request, response);
    }

    JSONObject json = new JSONObject();
    if (modifica) {
      json.put("modifica", "true");
      json.put("ultimo_aggiornamento", ultimoAggiornamento);
    } else {
      json.put("modifica", "false");
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
