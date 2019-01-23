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

@WebServlet("/ModificaRegistroTirocinioStudenteServlet")
public class ModificaRegistroTirocinioStudenteServlet extends HttpServlet{

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    HttpSession session = request.getSession(false);
    RegistroBean registroBean = null;
    StudenteBean studenteBean = null;
    String idRegistro = (String)request.getParameter("id").toString();
    String nome = (String)request.getParameter("nome").toString();
    String descrizione = (String)request.getParameter("descrizione").toString();
    boolean modifica = false;
    int id = -1;
    
    if (idRegistro != null) {
      id = Integer.valueOf(idRegistro);
      if (id < 0) {
        //redirect to an [error] page
      }
    }
    
    if (session != null) {
      studenteBean = (StudenteBean) session.getAttribute("SessionUser");
      
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
        
        if (studenteBean != null && registroBean != null) {
          if (Integer.valueOf(idRegistro) != registroBean.getId() || !nome.equals(registroBean.getNome()) || !descrizione.equals(registroBean.getDescrizione())) {
            int oldId = registroBean.getId();
            registroBean.setId(Integer.valueOf(idRegistro));
            registroBean.setNome(nome);
            registroBean.setDescrizione(descrizione);
            session.setAttribute("SessionRegistro", registroBean);
            try {
              RegistroModelDM.doUpdateRegistro(oldId, registroBean);
            } catch (SQLException e) {
              // TODO Auto-generated catch block
              e.printStackTrace();
            }
            modifica = true;
          }
        } else {
          modifica = false;
          //redirect to an [error] page
        }
      } else {
        RequestDispatcher view = request.getRequestDispatcher("login-page.jsp");
        view.forward(request, response);
      }
    } else {
      RequestDispatcher view = request.getRequestDispatcher("login-page.jsp");
      view.forward(request, response);
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
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    doPost(request, response);
  }
}
