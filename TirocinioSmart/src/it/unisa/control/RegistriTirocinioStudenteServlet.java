package it.unisa.control;

import java.util.ArrayList;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.unisa.model.StudenteBean;
import it.unisa.model.TirocinioBean;
import it.unisa.model.RegistroBean;
import it.unisa.model.RegistroModelDM;
import it.unisa.model.TirocinioModelDM;

@WebServlet("/RegistriTirocinioStudenteServlet")
public class RegistriTirocinioStudenteServlet extends HttpServlet {
  
  @Override
  protected void doGet(HttpRequest request, HttpResponse response) throws ServletException, SQLException, IOException {
    HttpSession session = request.getSession(false);
    StudenteBean studenteBean = null;
    TirocinioBean tirocinioBean = null;
    String idTirocinio = null;
    ArrayList<RegistroBean> registriBean = new ArrayList<RegistroBean>();
    Boolean login = new Boolean(false);
    int id = -1;
    
    idTirocinio = (String)request.getParameter("id").toString();
    if (idTirocinio != null) {
      id = Integer.valueOf(idTirocinio);
      if (id < 0) {
        //redirect to an [error] page
      }
    }
    
    if (session != null) {
      studenteBean  = (StudenteBean)session.getAttribute("SessionStudente");
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
        registriBean = (ArrayList<RegistroBean>)session.getAttribute("SessionRegistri");
        if (registriBean == null) {
          try {
            if (TirocinioModelDM.tirocinioStudente(studenteBean.getMatricola(), id)) {
              registriBean = RegistroModelDM.loadRegistriTirocinio(id);
              session.setAttribute("SessionRegistri", registriBean);
            } else {
              //redirect to an [error] page
            }
          } catch(SQLException e) {
            //redirect to an [error] page
          }
        }
       
        if (login == new Boolean(true) && registriBean != null) {
          RequestDispatcher view = request.getRequestDispatcher("registri-tirocinio-studente-page.jsp");
          view.forward(request, response);
        } else { 
          //redirect to an [error] page
        }
      } else {
        session.setAttribute("Loggato", new Boolean(false));
        RequestDispatcher RequestDispatcher view = request.getRequestDispatcher("login-page.jsp");
        view.forward(request, response);
        return;
      }
    } else {
      RequestDispatcher RequestDispatcher view = request.getRequestDispatcher("login-page.jsp");
      view.forward(request, response);
      return;
    }
  }
  
  @Override
  protected void doPost(HttpRequest request, HttpResponse response) throws ServletException, SQLException, IOException {
    doGet(request, response);
  }
}
