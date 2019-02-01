<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    isThreadSafe="false"
    import="java.util.ArrayList"
    import="it.unisa.model.ConvenzioneBean" %>
<section class="convenzioni-wrapper">
  <h1 class="title">Convenzioni</h1>
  <div class="convenzioni-wrapper">
  <%
    ArrayList<ConvenzioneBean> convs =
        (ArrayList<ConvenzioneBean>) session.getAttribute("SessionConvenzioni");
  %>
  <%
    for(int i = 0; i < convs.size(); i++) {
      ConvenzioneBean convenzioneBean = convs.get(i);
      if (convenzioneBean != null) {
  %>
        <div id="info-wrapper" class="wrapper">
          <p class="info">
            ID Convenzione: <%= convenzioneBean.getId() %>
          </p>
         </div>
  <%
      }
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
