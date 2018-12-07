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
import it.unisa.model.TutorBean;
import it.unisa.model.TutorModelDM;

@WebServlet("/TirociniTutorServlet")
public class TirociniTutorServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    HttpSession session = request.getSession(false);
    ArrayList<ProgettoFormativoBean> tirociniBean = null;
    TutorBean tutorBean = null;
    Boolean login = new Boolean(false);
    
    if (session != null) {
      tutorBean = (TutorBean)session.getAttribute("SessionTutor");
      login = (Boolean)session.getAttribute("Loggato");
      
      if (tutorBean != null) {
        tirociniBean = (ArrayList<ProgettoFormativoBean>)session.getAttribute("SessionTirocini");
        if (tirociniBean == null) {
          try {
            tirociniBean = TutorModelDM.loadTirociniTutor(tutorBean);
          } catch(SQLException e) {
            //redirect to an [error] page
          }
        }
        
        if (login == new Boolean(true) && tirociniBean != null) {
          RequestDispatcher view = request.getRequestDispatcher("tirocini-tutor-page.jsp");
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
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doGet(request, response);
  }
}
