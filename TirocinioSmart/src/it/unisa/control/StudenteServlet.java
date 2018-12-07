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
import it.unisa.model.RegistroBean;
import it.unisa.model.RegistroModelDM;
import it.unisa.model.StudenteBean;

@WebServlet("/StudenteServlet")
public class StudenteServlet extends HttpServlet {

  /*
   * gli oggetti non in sessione, da mettere nella sessione
   */
  
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    HttpSession session = request.getSession(false);
    StudenteBean studenteBean = null;
    ArrayList<RegistroBean> registriBean = null;
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
        registriBean = (ArrayList<RegistroBean>)session.getAttribute("SessionRegistri");
        tirociniBean = (ArrayList<ProgettoFormativoBean>)session.getAttribute("SessionTirocini");
        if (registriBean == null) {
          try {
            registriBean = RegistroModelDM.loadRegistriStudente(studenteBean);
            session.setAttribute("SessionRegistri", registriBean);
          } catch(SQLException e) {
            //redirect to an [error] page
          }
        }
        
        if (tirociniBean == null) {
          try {
            /*
             * nella pagina occorre distinguere i tirocini correnti
             */
            tirociniBean = ProgettoFormativoModelDM.loadTirociniStudente(studenteBean);
            session.setAttribute("SessionTirocini", tirociniBean);
          } catch(SQLException e) {
            //redirect to an [error] page
          }
        }
        
        if (login == new Boolean(true) && registriBean != null && tirociniBean != null) {
          RequestDispatcher view = request.getRequestDispatcher("studente-page.jsp");
          view.forward(request, response);
        }
      } else {
        session.setAttribute("Loggato", new Boolean(false));
        RequestDispatcher view = request.getRequestDispatcher("login-page.jsp");
        view.forward(request, response);
      }
    } else {
      RequestDispatcher view = request.getRequestDispatcher("login-page.jsp");
      view.forward(request, response);
    }
    //redirect to an [error] page
  }
  
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    doGet(request, response);
  }
}
