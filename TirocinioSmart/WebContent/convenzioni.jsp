<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isThreadSafe="false"
    import="java.util.List"
    import="it.unisa.model.AbstractBean"
    import="it.unisa.model.ConvenzioneBean" 
    import="java.util.logging.Level"
    import="java.util.logging.Logger" %>
<section class="convenzioni-wrapper">
  <h1 class="title">Convenzioni</h1>
  <div class="convenzioni-wrapper">
  <%
    List<AbstractBean> convs = (List<AbstractBean>) session.getAttribute("SessionConvenzioni");
  
    if (convs != null) {
      for(AbstractBean product: convs) {
        ConvenzioneBean convenzioneBean = (ConvenzioneBean) product;
        if (convenzioneBean != null) {
  %>
          <div id="info-wrapper" class="wrapper">
            <p class="info">
              ID Convenzione: <%= convenzioneBean.getID() %>
            </p>
           </div>
  <%
        }
      }
    } else {
      Logger.getGlobal().log(Level.INFO, "Nessuna convenzione trovata per il tutor specificato");
    }
  %>
  </div>
  <span class="wrapper">
    <input class="button" name="nuova-convenzione" type="button" value="Registra Nuova Convenzione"/>
  </span>
  <span id="info-convenzione-wrapper" class="wrapper">
    <textarea id="info-convenzione" placeholder="Convenzione" rows="10" cols="40"></textarea>
    <label id="button-wrapper" class="wrapper">
      <input class="button" name="compila-conv" type="button" value="Compila"/>
      <input class="button" name="invio-conv" type="button" value="Invio"/>
    </label>
  </span>
</section>
