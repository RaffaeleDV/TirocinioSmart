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
import it.unisa.model.UfficioBean;
import it.unisa.model.StudenteBean;
import it.unisa.model.RegistroBean;
import it.unisa.model.ProgettoFormativoBean;
import it.unisa.model.ProgettoFormativoModelDM;

@WebServlet("/TirocinioUfficioServlet")
public class TirocinioUfficioServlet extends HttpServlet {

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doPost(request, response);
  }
  
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    HttpSession session = request.getSession(false);
    UfficioBean ufficioBean = null;
    //TirocinioBean tirocinioBean = null;
    StudenteBean studenteBean = null;
    ProgettoFormativoBean progettoFormativoBean = null;
    RegistroBean registroBean = null;
    String id = (String)request.getParameter("id").toString();
    Boolean login = new Boolean(false);
    int idTirocinio = -1;
    
    if (id != null) {
      idTirocinio = Integer.valueOf(id);
      if (idTirocinio < 0) {
        //redirect to an [error] page
      }
    }
    
    if (session != null) {
      ufficioBean = (UfficioBean)session.getAttribute("SessionUfficio");
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
      
      if (ufficioBean != null) {
        progettoFormativoBean = (ProgettoFormativoBean)session.getAttribute("SessionTirocinio");
        if (progettoFormativoBean == null) {
          try {
            progettoFormativoBean = ProgettoFormativoModelDM.loadTirocinio(idTirocinio);
            session.setAttribute("SessionTirocinio", progettoFormativoBean);
          } catch(SQLException e) {
            //redirect to an [error] page
          }
        } else {
          if (progettoFormativoBean.getId() != idTirocinio) {
            try {
              progettoFormativoBean = ProgettoFormativoModelDM.loadTirocinio(idTirocinio);
              session.setAttribute("SessionTirocinio", progettoFormativoBean);
            } catch(SQLException e) {
              //redirect to an [error] page
            }
          }
        }
        
        if (progettoFormativoBean != null) {
          try {
            studenteBean = ProgettoFormativoModelDM.loadStudente(progettoFormativoBean);
            registroBean = ProgettoFormativoModelDM.loadRegistro(progettoFormativoBean);
          } catch (SQLException e) {
            //redirect to an [error] page
            e.printStackTrace();
          }
          
          //progettoFormativoBean = TirocinioModelDM.loadProgettoFormativo(id);
        } else {
          //redirect to an [error] page
        }
        
        if (login == new Boolean(true) && studenteBean != null && registroBean != null && progettoFormativoBean != null) {
          RequestDispatcher view = request.getRequestDispatcher("tirocinio-page.jsp");
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
}
