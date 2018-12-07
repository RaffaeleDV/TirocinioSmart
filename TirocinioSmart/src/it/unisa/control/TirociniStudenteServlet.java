package it.unisa.control;

import java.util.ArrayList;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import it.unisa.model.ProgettoFormativoBean;
import it.unisa.model.ProgettoFormativoModelDM;
import it.unisa.model.StudenteBean;

@WebServlet("/TirociniStudenteServlet")
public class TirociniStudenteServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    HttpSession session = request.getSession(false);
    StudenteBean studenteBean = null;
    ArrayList<ProgettoFormativoBean> tirociniBean = null;
    Boolean login = new Boolean(false);
    
    if (session != null) {
      studenteBean = (StudenteBean)session.getAttribute("SessionStudente");
      login = (Boolean)session.getAttribute("Loggato");
      
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
      
      if (studenteBean != null) {
        tirociniBean = (ArrayList<ProgettoFormativoBean>)session.getAttribute("SessionTirocini");
        if (tirociniBean == null) {
          try {
            tirociniBean = ProgettoFormativoModelDM.loadTirociniStudente(studenteBean);
          } catch(SQLException e) {
            //redirect to an [error] page
          }
        }
        
        if (login == new Boolean(true) && tirociniBean != null) {
          RequestDispatcher view = request.getRequestDispatcher("tirocini-studente-page.jsp");
          view.forward(request, response);
        } else {
          //redirect to an [error] page
        }
      } else {
        session.setAttribute("Loggato", new Boolean(false));
        RequestDispatcher view = request.getRequestDispatcher("login-page.jsp");
        view.forward(request, response);
        return;
      }
    } else {
      RequestDispatcher view = request.getRequestDispatcher("login-page.jsp");
      view.forward(request, response);
      return;
    }
  }
  
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    doGet(request, response);
  }
}
