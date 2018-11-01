package it.unisa.control;

import java.util.ArrayList;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.unisa.model.RegistroBean;
import it.unisa.model.TirocinioBean;
import it.unisa.model.RegistroModelDM;
import it.unisa.model.StudenteBean;
import it.unisa.model.TirocinioModelDM;

@WebServlet("/StudenteServlet")
public class StudenteServlet extends HttpServlet {

  /*
   * gli oggetti non in sessione, da mettere nella sessione
   */
  
  @Override
  protected void doGet(HttpRequest request, HttpResponse response) throws ServletException, SQLException, IOException {
    HttpSession session = request.getSession(false);
    StudenteBean studenteBean = null;
    ArrayList<RegistroBean> registriBean = null;
    ArrayList<TirocinioBean> tirociniBean = null;
    Boolean login = new Boolean(false);
    
    if (session != null) {
      studenteBean = (StudenteBeansession.getAttribute("SessionStudente");
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
        tirociniBean = (ArrayList<TirocinioBean>)session.getAttribute("SessionTirocini");
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
            tirociniBean = TirocinioModelDM.loadTirociniStudente(studenteBean);
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
        RequestDispatcher RequestDispatcher view = request.getRequestDispatcher("login-page.jsp");
        view.forward(request, response);
      }
    } else {
      RequestDispatcher RequestDispatcher view = request.getRequestDispatcher("login-page.jsp");
      view.forward(request, response);
    }
    //redirect to an [error] page
  }
  
  @Override
  protected void doPost(HttpRequest request, HttpResponse response) throws ServletException, SQLException, IOException{
    doGet(request, response);
  }
}
