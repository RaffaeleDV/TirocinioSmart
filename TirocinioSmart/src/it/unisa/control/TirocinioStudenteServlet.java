package it.unisa.control;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.unisa.model.StudenteBean;
import it.unisa.model.StudenteModelDM;
import it.unisa.model.TirocinioBean;
import it.unisa.model.UfficioBean;
import it.unisa.model.RegistroBean;
import it.unisa.model.ProgettoFormativoBean;
import it.unisa.model.TirocinioModelDM;

@WebServlet("/TirocinioStudenteServlet")
public class TirocinioStudenteServlet extends HttpServlet {

  @Override
  protected void doGet(HttpRequest request, HttpResponse response) throws SQLException, ServletException, IOException{
    HttpSession session = request.getSession(false);
    StudenteBean studenteBean = null;
    TirocinioBean tirocinioBean = null;
    UfficioBean ufficioBean = null;
    RegistroBean registroBean = null;
    ProgettoFormativoBean progettoBean = null;
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
        tirocinioBean = (TirocinioBean)session.getAttribute("SessionTirocinio");
        if (tirocinioBean == null) {
          try {
            if (TirocinioModelDM.tirocinioStudente(studenteBean.getMatricola(), idTirocinio)) {
              tirocinioBean = TirocinioModelDM.loadTirocinio(idTirocinio);
              session.setAttribute("SessionTirocinio", tirocinioBean);
            } else {
              //redirect to an [error] page
            }
          } catch(SQLException e) {
            //redirect to an [error] page
          }
        } else {
          if (tirocinioBean.getId() != idTirocinio) {
            try {
              if (TirocinioModelDM.tirocinioStudente(studenteBean.getMatricola(), idTirocinio)) {
                tirocinioBean = TirocinioModelDM.loadTirocinio(idTirocinio);
                session.setAttribute("SessionTirocinio", tirocinioBean);
              }else {
                //redirect to an [error] page
              }
            } catch(SQLException e) {
              //redirect to an [error] page
            }
          }
        }
        
        if (tirocinioBean != null) {
          ufficioBean = TirocinioModelDM.loadUfficio(tirocinioBean);
          registroBean = TirocinioModelDM.loadRegistro(tirocinioBean);
          progettoBean = TirocinioModelDM.loadProgettoFormativo(tirocinioBean);
        } else {
          //redirect to an [error] page
        }
        
        if (login == new Boolean(true) && ufficioBean != null && registroBean != null && progettoBean != null) {
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
    //redirect to an [error] page
  }
  
  @Override
  protected void doPost(HttpRequest request, HttpResponse response) throws SQLException, IOException, ServletException {
    doGet(request, response);
  }
}
