package it.unisa.control;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;

import it.unisa.model.RegistroBean;
import it.unisa.model.RegistroModelDM;
import it.unisa.model.StudenteBean;

@WebServlet("/ModificaRegistroTirocinioStudenteServlet")
public class ModificaRegistroTirocinioStudenteServlet extends HttpServlet{

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
    HttpSession session = request.getSession(false);
    RegistroBean registroBean = null;
    StudenteBean studenteBean = null;
    String idRegistro = (String)request.getParameter("id").toString();
    String nome = (String)request.getParameter("nome").toString();
    String descrizione = (String)request.getParameter("descrizione").toString();
    boolean modifica = false;
    Boolean login = new Boolean(false);
    
    int id = -1;
    
    if (idRegistro != null) {
      id = Integer.valueOf(idRegistro);
      if (id < 0) {
        //redirect to an [error] page
      }
    }
    if (session != null) {
      studenteBean = (StudenteBean)session.getAttribute("SessionStudente");
      login = (Boolean)session.getAttribute("Loggato");
      
      if (login != null) {
        if (login != new Boolean(true)) {
          RequestDispatcher RequestDispatcher view = request.getRequestDispatcher("login-page.jsp");
          view.forward(request, response);
          return;
        }
      } else {
        RequestDispatcher RequestDispatcher view = request.getRequestDispatcher("login-page.jsp");
        view.forward(request, response);
        return;
      }
      
      if (studenteBean != null) {
        registroBean = (RegistroBean)session.getAttribute("SessionRegistro"); 
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
          if (!nome.equals(registroBean.getNome()) || !descrizione.equals(registroBean.getDescrizione())) {
            registroBean.setNome(nome);
            registroBean.setDescrizione(descrizione);
            RegistroModelDM.doUpdateRegistro(registroBean);
            modifica = true;
          }
        } else {
          modifica = false;
          //redirect to an [error] page
        }
      } else {
        RequestDispatcher RequestDispatcher view = request.getRequestDispatcher("login-page.jsp");
        view.forward(request, response);
        return;
      }
    } else {
      RequestDispatcher RequestDispatcher view = request.getRequestDispatcher("login-page.jsp");
      view.forward(request, response);
      return;
    }
        
    JSONObject json = new JSONObject();
    if (modifica) {
      json.put("modifica", "true");
    } else {
      json.put("modifica", "false");
    }
    response.setContentType("application/json");
    try {
      response.getWriter().print(json.toString());
    } catch(IOException e) {
      //redirect to an [error] page
    }
  }
  
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletRequest, IOException{
    doGet(request, response);
  }
}
