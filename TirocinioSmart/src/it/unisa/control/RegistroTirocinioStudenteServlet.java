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
import it.unisa.model.StudenteModelDM;
import it.unisa.model.RegistroBean;
import it.unisa.model.RegistroModelDM;
import it.unisa.model.TirocinioBean;
import it.unisa.model.UfficioBean;
import it.unisa.model.TutorBean;

@WebServlet("/RegistroTirocinioStudenteServlet")
public class RegistroTirocinioStudenteServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
    HttpSession session = request.getSession(false);
    RegistroBean registroBean = null;
    StudenteBean studenteBean = null;
    UfficioBean ufficioBean = null;
    TutorBean tutorAziendaleBean = null;
    TutorBean tutorAccademicoBean = null;
    TirocinioBean tirocinioBean = null;
    String idRegistro = (String)request.getParameter("id").toString();
    Boolean login = new Boolean(false);
    int id = -1;
    
    if (idRegistro != null) {
      id = Integer.valueOf(idRegistro);
      if (id < 0) {
        //redirect to an [error] page
      }
    } else {
      //redirect to an [error] page
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
            if(RegistroModelDM.studenteRegistro(studenteBean.getMatricola(), id)) {
              registroBean = RegistroModelDM.loadRegistro(id);
              session.setAttribute("SessionRegistro", registroBean);
            } else {
              //redirect to an [error] page
            }
          } catch (SQLException e) {
            //redirect to an [error] page
          }
        } else {
          if (registroBean.getId() != id) {
            try {
              if(RegistroModelDM.studenteRegistro(studenteBean.getMatricola(), id)) {
                registroBean = RegistroModelDM.loadRegistro(id);
                session.setAttribute("SessionRegistro", registroBean);
              } else {
                //redirect to an [error] page
              }
            } catch (SQLException e) {
              //redirect to an [error] page
            }
          }
        }
        
        if (login == new Boolean(true) && registroBean != null) {
          try {    
            ufficioBean = RegistroModelDM.loadUfficio(registroBean);
            tutorAziendaleBean = RegistroModelDM.loadTutorAziendale(registroBean);
            tutorAccademicoBean = RegistroModelDM.loadTutorAccademico(registroBean);
            tirocinioBean = RegistroModelDM.loadTirocinio(registroBean);
            if (ufficioBean != null && tutorAziendaleBean != null && tutorAccademicoBean != null && tirocinioBean != null) {
              RequestDispatcher view = request.getRequestDispatcher("registro-tirocinio-studente-page.jsp");
              view.forward(request, response);
            } else {
              //redirect to an [error] page
            }
          } catch(SQLException e) {
            //redirect to an [error] page
          } catch(IOException e) {
            //redircet to an [error] page
          }
        } else {
          //redirect to an [error] page
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
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
    doGet(request, response);
  }
}
