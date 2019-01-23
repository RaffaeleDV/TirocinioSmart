package it.unisa.control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.List;
import java.util.HashMap;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import it.unisa.model.QuestionarioBean;
import it.unisa.model.QuestionarioModelDM;
import it.unisa.model.StudenteBean;
import it.unisa.model.AnswerBean;
import it.unisa.model.ChooseBean;
import it.unisa.model.ChooseModelDM;
import it.unisa.model.QuestionBean;
import it.unisa.model.QuestionModelDM;

/**
 * Servlet implementation class QuestionarioServlet
 */
@WebServlet("/EffettuaQuestionarioServlet")
public class EffettuaQuestionarioServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;
       
  /**
   * @see HttpServlet#HttpServlet()
   */
  public EffettuaQuestionarioServlet() {
    super();
  }

  /**
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	doPost(request, response);
  }

  /**
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	HttpSession session = request.getSession(false);
    String questionarioName = (String) request.getParameter("questionario").toString();
    StudenteBean studente = null;
    QuestionarioBean questionario = null;
    HashMap<QuestionBean, List<ChooseBean>> questChoosesMap =
        new HashMap<QuestionBean, List<ChooseBean>>();
    
    if (session != null) {
      studente = (StudenteBean) session.getAttribute("SessionUser");
      if (questionarioName != null) {
        if (!questionarioName.equals("")) {
          QuestionarioModelDM qmDM = new QuestionarioModelDM();
          try {
            questionario = (QuestionarioBean) qmDM.loadQuestionarioByNome(questionarioName);
            session.setAttribute("SessionQuestionario", questionario);
          } catch (SQLException e) {
            Logger.getGlobal().log(Level.SEVERE, e.getMessage());
            RequestDispatcher disp = request.getRequestDispatcher("login-page.jsp");
            disp.forward(request, response);
          }
        } else {
          Logger.getGlobal().log(Level.SEVERE, "Ricevuto un nome di questionario vuoto");
          RequestDispatcher disp = request.getRequestDispatcher("login-page.jsp");
          disp.forward(request, response);
        }
      } else {
        Logger.getGlobal().log(Level.SEVERE, "Nessun nome di questionario specificato, per poter effettuare un questionario");
        RequestDispatcher disp = request.getRequestDispatcher("login-page.jsp");
        disp.forward(request, response);
      }
      
      List<QuestionBean> questions = null;
      if (questionario != null) {
        try {
          questions = (List<QuestionBean>) QuestionModelDM.retreiveQuestionsByQuestionario(questionario);
        } catch(SQLException e) {
          Logger.getGlobal().log(Level.SEVERE, e.getMessage());
        }
      } else {
        Logger.getGlobal().log(Level.SEVERE, "Nessun questionario trovato");
        RequestDispatcher disp = request.getRequestDispatcher("login-page.jsp");
        disp.forward(request, response);
      }
      
      for (QuestionBean question: questions) {
        List<ChooseBean> questionChooses = null;
        if (question != null) {
          try {
            ChooseModelDM cmDM = new ChooseModelDM();
            questionChooses = (List<ChooseBean>) cmDM.retreiveQuestionChooses(question);
            if (questionChooses != null) {
              questChoosesMap.put(question, questionChooses);
            }
          } catch(SQLException e) {
            Logger.getGlobal().log(Level.SEVERE, e.getMessage());
            RequestDispatcher disp = request.getRequestDispatcher("login-page.jsp");
            disp.forward(request, response);
          }
        }
      }
      
      session.setAttribute("SessionQuestionChoosesMap", questChoosesMap);
      
    } else {
      //redirect to an [login] page
    }
    
    RequestDispatcher view = request.getRequestDispatcher("/questionario-page.jsp");
    view.forward(request, response);
  }
}
