<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isThreadSafe="false"
    import="java.util.ArrayList"
    import="it.unisa.model.ConvenzioneBean" %>
<div>
  <%
    ArrayList<ConvenzioneBean> convs = 
      (ArrayList<ConvenzioneBean>)session.getAttribute("SessionConvenzioni");
  %>
  <%
    for(int i = 0; i < convs.size(); i++) {
      ConvenzioneBean convenzioneBean = convs.get(i);
      if (convenzioneBean != null) {
  %>
        <div id="nome-convenzione-wrapper">
          <p class="nome-convenzione-info">
            ID Convenzione: <%= convenzioneBean.getId() %>
          </p>
        </div>
  <%   
      }
    }
  %>
</div>