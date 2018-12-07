package it.unisa.control;

import java.io.IOException;
import java.sql.SQLException;

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

@WebServlet("/ConsegnaRegistroTirocinioServlet")
public class ConsegnaRegistroTirocinioServlet extends HttpServlet {
  
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doPost(request, response);
  }
  
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    HttpSession session = request.getSession(false);
    StudenteBean studenteBean = null;
    RegistroBean registroBean = null;
    String idRegistro = null;
    boolean consegna = false;
    Boolean login = new Boolean(false);
    int id = -1;
    
    idRegistro = (String)request.getParameter("id").toString();
    if (idRegistro != null) {
      id = Integer.valueOf(idRegistro);
      if (id < 0) {
        //redirect to an [error] page
      }
    }
    
    if (session != null) {
      studenteBean = (StudenteBean)session.getAttribute("SessionStudente");
      login = (Boolean)session.getAttribute("Loggato");
      if (studenteBean != null) {
        registroBean = (RegistroBean)session.getAttribute("SessionRegistro");
        
        if (login != null) {
          if (login != new Boolean(true)) {
            RequestDispatcher view = request.getRequestDispatcher("login-page.jsp");
            view.forward(request, response);
            return;
          }
        } else {
          RequestDispatcher view = request.getRequestDispatcher("login-page.jsp");
          view.forward(request, response);
          return;
        }
        
        if (registroBean == null) {
          try {
            if (RegistroModelDM.studenteRegistro(studenteBean.getMatricola(), id)) {
              registroBean = RegistroModelDM.loadRegistro(id);
              session.setAttribute("SessionRegistro", registroBean);
            } else {
              //redirect to an [error] page
            }
          } catch(SQLException e) {
            //redirect to an [error] page
          }
        }
        
        if (login == (new Boolean(true)) && registroBean != null) {
          if (!registroBean.getConsegna() && !registroBean.getConfermaTutorAcc() && !registroBean.getConfermaTutorAz()) {
            registroBean.setConsegna(true);
            try {
              RegistroModelDM.doUpdateRegistro(registroBean);
            } catch (SQLException e) {
              // TODO Auto-generated catch block
              e.printStackTrace();
            }
            consegna = true;
          } else {
            consegna = false;
            //redirect to an [error] page
          }
        } else {
          consegna = false;
          //redirect to an [error] page
        }
      } else {
        RequestDispatcher view = request.getRequestDispatcher("login-page.jsp");
        view.forward(request, response);
        return;
      }
    } else {
      RequestDispatcher view = request.getRequestDispatcher("login-page.jsp");
      view.forward(request, response);
      return;
    }
    
    JSONObject json = new JSONObject();
    if (consegna) {
      json.put("consegna", "true");
    } else {
      /*
       * il registro non viene consegnato quando
       *   lo studente non risulta loggato,
       *   non è stato trovato il registro,
       *   il registro non risulta dello studente,
       *   il registro risulta già consegnato,
       *   l' id del registro non coincide con l' id di un registro memorizzato,
       *   
       */
      json.put("consegna", "false");
    }
    response.setContentType("application/json");
    try {
      response.getWriter().print(json.toString());
    } catch(IOException e) {
      //redirect to an [error] page
    }
  }
}
