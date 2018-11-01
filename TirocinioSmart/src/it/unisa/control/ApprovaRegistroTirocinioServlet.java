package it.unisa.control;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;

import it.unisa.model.TutorBean;
import it.unisa.model.RegistroBean;
import it.unisa.model.RegistroModelDM;


@WebServlet("/ApprovaRegistroTirocinioServlet")
public class ApprovaRegistroTirocinioServlet extends HttpServlet {

  /*
   * Approvare un tirocinio equivale ad approvare un prog. formativo
   */
  
  @Override
  protected void doGet(HttpRequest request, HttpResponse response) throws SQLException, ServletException, IOException {
    doPost(request, response);
  }
  
  @Override
  protected void doPost(HttpRequest request, HttpResponse response) throws SQLException, IOException, ServletException {
    HttpSession session = request.getSession(false);
    TutorBean tutorBean = null;
    RegistroBean registroBean = null;
    String idRegistro = null;
    boolean approvazione = false;
    Boolean login = new Boolean(false);
    int id = -1;
    
    idRegistro = (String)request.getParameter("id").toString();
    if (idRegistro != null) {
      id = Integer.valueOf(idRegistro);
      if (id < 0) {
        //redirect to an [error] page
      }
    }
    
    if (session != null) {
      tutorBean = (TutorBean)session.getAttribute("SessionTutor");
      if (tutorBean != null) {
        login = (Boolean)session.getAttribute("Loggato");
        registroBean = (RegistroBean)session.getAttribute("SessionRegistro");
        
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
        
        if (registroBean == null) {
          try {
            if (RegistroModelDM.tutorRegistro(tutorBean.getId(), id)) {
              registroBean = RegistroModelDM.loadRegistro(id);
              session.setAttribute("SessionRegistro", registroBean);
            } else {
              //redirect to an [error] page
            }
          } catch(SQLException e) {
            //redirect to an [error] page
          }
        }
        
        if (login == (new Boolean(true)) && registroBean != null) {
          String tipo = tutorBean.getTipo();
          if (tipo != null) {
            switch (tipo) {
              case "accademico":
                registroBean.setConfermaTutorAcc(true);
                break;
              case "aziendale":
                registroBean.setConfermaTutorAz(true);
                break;
              default:
                //redirect to an [error] page
                break;
            }
            RegistroModelDM.doUpdateRegistro(registroBean);
            approvazione = true;
          } else {
            approvazione = false;
            //redirect to an [error] page
          }
        } else {
          approvazione = false;
          // user not logged or registro notfound
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
    
    JSONObject json = new JSONObject();
    if (approvazione) {
      json.put("approvazione", "true");
    } else {
      /*
       * il registro non viene approvato quando
       *   il tutor non risulta loggato,
       *   non è stato trovato il registro,
       *   il regsitro non risulta del tutor,
       *   la conferma dell' altro tutor non è stata ancora effettuata,
       *   l' id della richiesta non coincide con l' id di un registro memorizzato,
       *   il tipo del tutor loggato non corrisponde ad almeno uno di quelli ammissibili,
       *   
       */
      json.put("approvazione", "false");
    }
    response.setContentType("application/json");
    try {
      response.getWriter().print(json.toString());
    } catch(IOException e) {
      //redirect to an [error] page
    }
  }
}
