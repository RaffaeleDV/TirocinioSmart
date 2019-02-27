package it.unisa.control;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.logging.Level;
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
import it.unisa.model.ProgettoFormativoModelDM;
import it.unisa.model.AbstractBean;

/**
 * Servlet implementation class TirociniTutorServlet
 */
@WebServlet("/TirociniTutorServlet")
public class TirociniTutorServlet extends HttpServlet {

  private static final ProgettoFormativoModelDM progettoFormativoModelDM = new ProgettoFormativoModelDM();
  private static final TutorModelDM tutorModelDM = new TutorModelDM();
  
  /**
   * @see HttpServlet#HttpServlet()
   */
  public TirociniTutorServlet() {
    super();
  }
  
  /**
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
   */
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    HttpSession session = request.getSession(false);
    List<AbstractBean> tirociniBean = null;
    TutorBean tutorBean = null;
    String idTutorParameter = request.getParameter("idTutor");
    int idTutor = -1;
    
    if (idTutorParameter != null) {
      if (!idTutorParameter.equals("")) {
        idTutor = Integer.valueOf(idTutorParameter);
      } else {
        Logger.getGlobal().log(Level.INFO, "idTutorParameter stringa vuota nella ricerca di tirocini da parte di un tutor");
        //redirect to an [error] page
      }
    } else {
      Logger.getGlobal().log(Level.INFO, "idTutorParameter nullo nella ricerca di tirocini da parte di un tutor");
      //redirect to an [error] page
    }
    
    if (session != null) {
      tutorBean = (TutorBean) session.getAttribute("SessionTutor");
      if (tutorBean != null) {
        tirociniBean = (List<AbstractBean>) session.getAttribute("SessionTirocini");
        if (tirociniBean == null) {
          if (idTutor >= 0) {
            try {
              tutorBean = (TutorBean) tutorModelDM.doRetrieveByKey(idTutor);
            } catch (SQLException e) {
              Logger.getGlobal().log(Level.SEVERE, e.getMessage());
              //redirect to an [error] page
            }
          } else {
            Logger.getGlobal().log(Level.INFO, "idTutor negativo nella ricerca di tirocini da parte di un tutor");
            //redirect to an [error] page
          }
          
          if (tutorBean != null) {
            try {
              tirociniBean = (List<AbstractBean>) progettoFormativoModelDM.doRetrieveByTutor(tutorBean.getID());
            } catch(SQLException e) {
              Logger.getGlobal().log(Level.SEVERE, e.getMessage());
              //redirect to an [error] page
            }
          }
        }
        
      } else {
        Logger.getGlobal().log(Level.INFO, "Il Tutor non risulta loggato");
        RequestDispatcher view = request.getRequestDispatcher("login-page.jsp");
        view.forward(request, response);
        return;
      }
    } else {
      Logger.getGlobal().log(Level.INFO, "Nessuna sessione nella ricerca di tirocini da parte di un tutor");
      RequestDispatcher view = request.getRequestDispatcher("login-page.jsp");
      view.forward(request, response);
      return;
    }
  }

  /**
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
   */
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doGet(request, response);
  }
}
