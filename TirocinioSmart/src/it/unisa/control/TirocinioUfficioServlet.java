package it.unisa.control;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.unisa.model.TirocinioModelDM;
import it.unisa.model.UfficioBean;
import it.unisa.model.TirocinioBean;
import it.unisa.model.StudenteBean;
import it.unisa.model.RegistroBean;
import it.unisa.model.ProgettoFormativoBean;

@WebServlet("/TirocinioUfficioServlet")
public class TirocinioUfficioServlet extends HttpServlet {

  public static Logger log = Logger.getLogger("global");
  
  protected void doGet(HttpRequest request, HttpResponse response) throws SQLException, ServletException, IOException {
    doPost(request, response);
  }
  
  protected void doPost(HttpRequest request, HttpResponse response) throws SQLException, ServletException, IOException {
    HttpSession session = request.getSession(false);
    UfficioBean ufficioBean = null;
    TirocinioBean tirocinioBean = null;
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
          RequestDispatcher RequestDispatcher view = request.getRequestDispatcher("login-page.jsp");
          view.forward(request, response);
          return;
        }
      } else {
        RequestDispatcher RequestDispatcher view = request.getRequestDispatcher("login-page.jsp");
        view.forward(request, response);
        return;
      }
      
      if (ufficioBean != null) {
        tirocinioBean = (TirocinioBean)session.getAttribute("SessionTirocinio");
        if (tirocinioBean == null) {
          try {
            tirocinioBean = TirocinioModelDM.loadTirocinio(id);
            session.setAttribute("SessionTirocinio", tirocinioBean);
          } catch(SQLException e) {
            //redirect to an [error] page
          }
        } else {
          if (tirocinioBean.getId() != id) {
            try {
              tirocinioBean = TirocinioModelDM.loadTirocinio(id);
              session.setAttribute("SessionTirocinio", tirocinioBean);
            } catch(SQLException e) {
              //redirect to an [error] page
            }
          }
        }
        
        if (tirocinioBean != null) {
          studenteBean = TirocinioModelDM.loadStudente(tirocinioBean);
          registroBean = TirocinioModelDM.loadRegistro(tirocinioBean);
          progettoFormativoBean = TirocinioModelDM.loadProgettoFormativo(id);
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
}
