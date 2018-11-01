<div>
  <%
    for(int i = 0; i < convenzioniBean.size(); i++) {
      ConvenzioneBean convenzioneBean = convenzioniBean.get(i);
      if (convenzioneBean != null) {
  %>
        <div id="nome-convenzione-wrapper">
          <p class="nome-convenzione-info">
            Info: <%= convenzioneBean.getAzienda(); %>
          </p>
        </div>
  <%   
      }
    }
  %>
</div>