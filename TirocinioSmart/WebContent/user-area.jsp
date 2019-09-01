<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="it.unisa.model.StudenteBean"
    import="it.unisa.model.TutorBean"
    import="it.unisa.model.UfficioBean"%>
<section id="user-area-container" class="container">
  <%
    Object areaUtente = session.getAttribute("SessionUser");
  %>
  <div id="menu-ts-wrapper" class="container">
    <nav id="menu-ts-nav" class="container">
      <ul id="user-list" class="horizontal-list">
        <li class="menu-item">
          <span>
            <img id="autente-ico" src="images/autente-ico.png"/>
            <a id="autente-ilink" class="item-link" onmouseover="showAreaUtente()">Area Utente</a>
          </span>
        </li>
        <li class="menu-item">
          <span>
            <img id="aquestionario-ico" src="images/aquestionario-ico.png"/>
            <a id="aquestionario-ilink" class="item-link" onmouseover="showAreaQuestionario()">Area Questionario</a>
          </span>
        </li>
      </ul>
      <ul id="tirocinio-list" class="horizontal-list">
        <li class="menu-item">
          <span>
            <img id="atirocinio-ico" src="images/atirocinio-ico.png"/>
            <a id="atirocinio-ilink" class="item-link" onmouseover="showAreaTirocinio()">Area Tirocino</a>
          </span>
        </li>
        <li class="menu-item">
          <span>
            <img id="areg-ico" src="images/areg-ico.png"/>
            <a id="aregistro-ilink" class="item-link" onmouseover="showAreaRegistro()">Area Registro</a>
          </span>
        </li>
        <li class="menu-item">
          <span>
            <img id="aprog-ico" src="images/aprog-ico.png"/>
            <a id="aprogetto-formativo-ilink" class="item-link" onmouseover="showAreaProgettoFormativo()">Area Progetto Formativo</a>
          </span>
        </li>
      </ul>
    </nav>
  </div>
  <div id="autente" class="area" onmouseover="onAreaUtente()" onmouseout="outAreaUtente()">
    <div id="utenteinfo-wrapper" class="container">
      <nav id="anav" class="container">
        <ul id="alist" class="horizontal-list">
          <li>
            <%
              if (areaUtente.getClass().getName().equals(StudenteBean.class.getName())) {
            %>
                <img id="utentelog-ico" class="ico" src="images/user-icon.png"/>
            <%
              } else if (areaUtente.getClass().getName().equals(TutorBean.class.getName())) {
            %>
                <img id="utentelog-ico" class="ico" src="images/tutor-icon.png"/>
            <%
              } else if (areaUtente.getClass().getName().equals(UfficioBean.class.getName())) {
            %>
                <img id="utentelog-ico" class="ico" src="images/ufficio-icon.png"/>
            <%
              }
             %>
           </li>
           <li>
             <p id="utenteinfo" class="text">
               <%
                 StudenteBean areaStudente = null;
                 TutorBean areaTutor = null;
                 UfficioBean areaUfficio = null;
                 if (areaUtente.getClass().getName().equals(StudenteBean.class.getName())) {
                   areaStudente = (StudenteBean) areaUtente;
               %>
                   <%= areaStudente.getNome().toString() %>
               <%
                 } else if (areaUtente.getClass().getName().equals(TutorBean.class.getName())) {
                   areaTutor = (TutorBean) areaUtente;
               %>
                   <%= areaTutor.getNome().toString() %>
               <%
                 } else if (areaUtente.getClass().getName().equals(UfficioBean.class.getName())) {
                   areaUfficio = (UfficioBean) areaUtente;
               %>
                   <%= areaUfficio.getNome().toString() %>
               <%
                 }
               %>
             </p>
           </li>
           <li>
             <p id="utenteinfo" class="text">
               <%
                 if (areaUtente.getClass().getName().equals(StudenteBean.class.getName())) {
               %>    
                   <%= areaStudente.getMatricola().toString() %>
               <%
                 }
               %>
             </p>
           </li>
           <li id="logb-wrapper" class="container">
             <a href="login-page.jsp"><button id="logb" class="button" onclick="logoutUtente()">logout</button></a>
           </li>
        </ul>
      </nav>
    </div>
    <div id="panoramica-utente-wrapper" class="container">
      <img id="panoramica-ico" src="images/discs.png" class="ico"/>
      <nav id="menu-utente-nav" class="container">
        <ul class="vertical-list">
          <li><a id="alink" class="link" href="profilo-utente-page.jsp">Il Mio Profilo TS</a></li>
          <li><a id="alink" class="link" href="tirocinante-page.jsp">Tirocinante</a></li>
          <!-- info generali riguardo il tirocinante, il registro ed il progetto formativo -->
          <!-- l' ufficio di un' ente ha la possibilita' di stipulare una convenzione con il dipartimento a cui l' azienda afferisce -->
          <li><a id="alink" class="link" href="convenzioni-page.jsp">Convenzioni</a></li>
          <!-- per accettare o rifiutare una stipula di convenzione, o stipulare una nuova convenzione -->
        </ul>
      </nav>
    </div>
  </div>
  <div id="aquestionario" class="area" onmouseover="onAreaQuestionario()" onmouseout="outAreaQuestionario()">
    <nav id="menu-questionario-nav" class="vertical-list">
      <ul class="vertical-list">
        <!-- i questionari svolti, non svolti(con scadenza), quelli proposti(con riferimento a chi li ha proposti) -->
        <li><a id="alink" class="link" href="questionario-valutazione-page.jsp">Proponi Questionario Di Valutazione</a></li>
      </ul>
    </nav>
  </div>
  <div id="atirocinio" class="area" onmouseover="onAreaTirocinio()" onmouseout="outAreaTirocinio()">
    <nav id="menu-tirocinio-nav" class="vertical-list">
      <ul class="vertical-list">
        <!-- per accedere ai tirocini(ufficio, studente e tutor) -->
        <li><a id="alink" class="link" href="tirocinio-page.jsp">Info Tirocinio</a></li>
        <li><a id="alink" class="link" href="tirocinio-studente-page.jsp">Accedi Al Tirocinio Dello Studente</a></li>
        <li><a id="alink" class="link" href="tirocinio-tutor-page.jsp">Accedi Al Tirocinio Del Tutor</a></li>
      </ul>
    </nav>
  </div>
  <div id="aregistro" class="area" onmouseover="onAreaRegistro()" onmouseout="outAreaRegistro()">
    <nav id="menu-registro-nav" class="vertical-list">
      <ul class="vertical-list">
        <li><a id="alink" class="link" href="registro-page.jsp">Accedi Al Registro</a></li>
        <li><a id="alink" class="link" href="attivita-registro-page.jsp">Attivita Del Registro</a></li>
        <li><a id="alink" class="link" href="aggiungi-attivita-page.jsp">Aggiungi Attivita</a></li>
      </ul>
    </nav>
  </div>
  <div id="aprogetto-formativo" class="area" onmouseover="onAreaProgettoFormativo()" onmouseout="outAreaProgettoFormativo()">
    <nav id="menu-progetto-formativo-nav" class="container">
      <ul class="vertical-list">
        <li><a id="alink" class="link" href="progetto-formativo-page.jsp">Accedi Al Progetto Formativo</a></li>
        <li><a id="alink" class="link" href="approvazione-progetto-formativo-page.jsp">Approva Progetto Formativo</a></li>
        <!-- <li><a id="alink" class="link" href="modifica-progetto-formativo-page.jsp">Modifica Progetto Formativo</a></li> -->
        <li><a id="alink" class="link" href="proponi-progetto-formativo-page.jsp">Proponi Progetto Formativo</a></li>
      </ul>
    </nav>
  </div>
  <script type="text/javascript">
    var onAUtente = false;
    var onAQuestionario = false;
    var onATirocinio = false;
    var onARegistro = false;
    var onAProgettoFormativo = false;
    var shownAreaUtente = false;
    var shownAreaQuestionario = false;
    var shownAreaTirocinio = false;
    var shownAreaRegistro = false;
    var shownAreaProgettoFormativo = false;
    
    $("#autente").css('display', 'none');
    $("#aquestionario").css('display', 'none');
    $("#atirocinio").css('display', 'none');
    $("#aregistro").css('display', 'none');
    $("#aprogetto-formativo").css('display', 'none');
    
    function onAreaUtente() {
    	onAUtente = true;
    	$("#autente").css('display', 'block');
      $("#aquestionario").css('display', 'none');
      $("#atirocinio").css('display', 'none');
      $("#aregistro").css('display', 'none');
      $("#aprogetto-formativo").css('display', 'none');
    };
    
    function onAreaQuestionario() {
    	onAQuestionario = true;
    	$("#autente").css('display', 'none');
      $("#aquestionario").css('display', 'block');
      $("#atirocinio").css('display', 'none');
      $("#aregistro").css('display', 'none');
      $("#aprogetto-formativo").css('display', 'none');
    };
    
    function onAreaTirocinio() {
    	onATirocinio = true;
    	$("#autente").css('display', 'none');
      $("#aquestionario").css('display', 'none');
      $("#atirocinio").css('display', 'block');
      $("#aregistro").css('display', 'none');
      $("#aprogetto-formativo").css('display', 'none');
    };
    
    function onAreaRegistro() {
    	onARegistro = true;
    	$("#autente").css('display', 'none');
      $("#aquestionario").css('display', 'none');
      $("#atirocinio").css('display', 'none');
      $("#aregistro").css('display', 'block');
      $("#aprogetto-formativo").css('display', 'none');
    };
    
    function onAreaProgettoFormativo() {
      onAProgettoFormativo = true;
      $("#autente").css('display', 'none');
      $("#aquestionario").css('display', 'none');
      $("#atirocinio").css('display', 'none');
      $("#aregistro").css('display', 'none');
      $("#aprogetto-formativo").css('display', 'block');
    };
    
    function outAreaUtente() {
    	if (onAreaUtente == true) {
    		onAreaUtente = false;
        $("#autente").css('display', 'none');
    	} else {
    		console.log("OutAreaUtente Without OnAreaUtente");
    	}
    };
    
    function outAreaQuestionario() {
    	if (onAreaQuestionario == true) {
        onAreaQuestionario = false;
        $("#aquestionario").css('display', 'none');
    	} else {
    		console.log("OutAreaQuestionario Without OnAreaQuestionario");
    	}
    };
    
    function outAreaRegistro() {
    	if (onAreaRegistro == true) {
    		onAreaRegistro = false;
    		$("#aregistro").css('display', 'none');
    	} else {
    		console.log("OutAreaRegistro Without OnAreaRegistro")
    	}
    };
    
    function outAreaTirocinio() {
    	if (onAreaTirocinio == true) {
    		onAreaTirocinio = false;
    		$("#atirocinio").css('display', 'none');
    	} else {
    		console.log("OutAreaTirocinio Without OnAreaTirocinio");
    	}
    };
    
    function outAreaProgettoFormativo() {
    	if (onAreaProgettoFormativo == true) {
    		onAreaProgettoFormativo = false;
    		$("#aprogetto-formativo").css('display', 'none');
    	} else {
    		console.log("OutAreaProgettoFormativo Without OnAreaProgettoFormativo");
    	}
    };
    
    function showAreaUtente() {
    	if (shownAreaUtente == false) {
    		shownAreaUtente = true;
    		shownAreaQuestionario = false;
    		shownAreaTirocinio = false;
    		shownAreaRegistro = false;
    		shownAreaProgettoFormativo = false;
    		$("#autente").css('display', 'block');
    		$("#aquestionario").css('display', 'none');
    		$("#aregistro").css('display', 'none');
    		$("#atirocinio").css('display', 'none');
    		$("#aprogetto-formativo").css('display', 'none');
    		$("#autente-ilink").css('background-image', 'repeating-linear-gradient(#fafafa, #a9a9a9 90%)');
    		$("#aquestionario-ilink").css('background-image', 'none');
    		$("#atirocinio-ilink").css('background-image', 'none');
    		$("#aregistro-ilink").css('background-image', 'none');
    		$("#aprogetto-formativo-ilink").css('background-image', 'none');
    	} else {
    		shownAreaUtente = false;
    		$("#autente").css('display', 'none');
    		$("#autente-ilink").css('background-image', 'none');
        $("#aquestionario-ilink").css('background-image', 'none');
        $("#atirocinio-ilink").css('background-image', 'none');
        $("#aregistro-ilink").css('background-image', 'none');
        $("#aprogetto-formativo-ilink").css('background-image', 'none');
    	}
    };

    function showAreaQuestionario() {
      if (shownAreaQuestionario == false) {
    	  shownAreaQuestionario = true;
    	  shownAreaUtente = false;
    	  shownAreaTirocinio = false;
    	  shownAreaRegistro = false;
    	  shownAreaProgettoFormativo = false;
    	  $("#autente").css('display', 'none');
        $("#aquestionario").css('display', 'block');
        $("#aregistro").css('display', 'none');
        $("#atirocinio").css('display', 'none');
        $("#aprogetto-formativo").css('display', 'none');
        $("#autente-ilink").css('background-image', 'none');
        $("#aquestionario-ilink").css('background-image', 'repeating-linear-gradient(#fafafa, #a9a9a9 90%)');
        $("#atirocinio-ilink").css('background-image', 'none');
        $("#aregistro-ilink").css('background-image', 'none');
        $("#aprogetto-formativo-ilink").css('background-image', 'none');
      } else {
    	  shownAreaQuestionario = false;
    	  $("#aquestionario").css('display', 'none');
    	  $("#autente-ilink").css('background-image', 'none');
        $("#aquestionario-ilink").css('background-image', 'none');
        $("#atirocinio-ilink").css('background-image', 'none');
        $("#aregistro-ilink").css('background-image', 'none');
        $("#aprogetto-formativo-ilink").css('background-image', 'none');
      }
    };

    function showAreaTirocinio() {
      if (shownAreaTirocinio == false) {
    	  shownAreaQuestionario = false;
    	  shownAreaUtente = false;
    	  shownAreaTirocinio = true;
    	  shownAreaRegistro = false;
    	  shownAreaProgettoFormativo = false;
    	  $("#autente").css('display', 'none');
        $("#aquestionario").css('display', 'none');
        $("#aregistro").css('display', 'none');
        $("#atirocinio").css('display', 'block');
        $("#aprogetto-formativo").css('display', 'none');
        $("#autente-ilink").css('background-image', 'none');
        $("#aquestionario-ilink").css('background-image', 'none');
        $("#atirocinio-ilink").css('background-image', 'repeating-linear-gradient(#fafafa, #a9a9a9 90%)');
        $("#aregistro-ilink").css('background-image', 'none');
        $("#aprogetto-formativo-ilink").css('background-image', 'none');
      } else {
    	  shownAreaQuestionario = false;
    	  $("#atirocinio").css('display', 'none');
    	  $("#autente-ilink").css('background-image', 'none');
        $("#aquestionario-ilink").css('background-image', 'none');
        $("#atirocinio-ilink").css('background-image', 'none');
        $("#aregistro-ilink").css('background-image', 'none');
        $("#aprogetto-formativo-ilink").css('background-image', 'none');
      }
    };
  
    function showAreaRegistro() {
      if (shownAreaRegistro == false) {
    	  shownAreaRegistro = true;
    	  shownAreaQuestionario = false;
        shownAreaUtente = false;
        shownAreaProgettoFormativo = false;
        shownAreaTirocinio = false;
    	  $("#autente").css('display', 'none');
        $("#aquestionario").css('display', 'none');
        $("#aregistro").css('display', 'block');
        $("#atirocinio").css('display', 'none');
        $("#aprogetto-formativo").css('display', 'none');
        $("#autente-ilink").css('background-image', 'none');
        $("#aquestionario-ilink").css('background-image', 'none');
        $("#atirocinio-ilink").css('background-image', 'none');
        $("#aregistro-ilink").css('background-image', 'repeating-linear-gradient(#fafafa, #a9a9a9 90%)');
        $("#aprogetto-formativo-ilink").css('background-image', 'none');
      } else {
    	  shownAreaRegistro = false;
    	  $("#aregistro").css('display', 'none');
    	  $("#autente-ilink").css('background-image', 'none');
        $("#aquestionario-ilink").css('background-image', 'none');
        $("#atirocinio-ilink").css('background-image', 'none');
        $("#aregistro-ilink").css('background-image', 'none');
        $("#aprogetto-formativo-ilink").css('background-image', 'none');
      }
    };
  
    function showAreaProgettoFormativo() {
      if (shownAreaProgettoFormativo == false) {
    	  shownAreaRegistro = false;
        shownAreaQuestionario = false;
        shownAreaUtente = false;
        shownAreaTirocinio = false;
    	  shownAreaProgettoFormativo = true;
    	  $("#autente").css('display', 'none');
        $("#aquestionario").css('display', 'none');
        $("#aregistro").css('display', 'none');
        $("#atirocinio").css('display', 'none');
        $("#aprogetto-formativo").css('display', 'block');
        $("#autente-ilink").css('background-image', 'none');
        $("#aquestionario-ilink").css('background-image', 'none');
        $("#atirocinio-ilink").css('background-image', 'none');
        $("#aregistro-ilink").css('background-image', 'none');
        $("#aprogetto-formativo-ilink").css('background-image', 'repeating-linear-gradient(#fafafa, #a9a9a9 90%)');
      } else {
    	  shownAreaProgettoFormativo = false;
    	  $("#aprogetto-formativo").css('display', 'none');
    	  $("#autente-ilink").css('background-image', 'none');
        $("#aquestionario-ilink").css('background-image', 'none');
        $("#atirocinio-ilink").css('background-image', 'none');
        $("#aregistro-ilink").css('background-image', 'none');
        $("#aprogetto-formativo-ilink").css('background-image', 'none');
      }
    };
  
    function logoutUtente() {
    	var none = "none";
      $.ajax({
    	  type : "POST",
    	  url : "LogoutStudenteServlet",
    	  contentType : "application/x-www-form-urlencoded",
    	  datatype : "json",
    	  data : "none=" + none,
    	  success : function (data) {
    		  if (data.logout == true) {
    			  console.log("L' utente ha effettuato il logout con successo");
    		  } else {
    			  console.log("L' utente ha effettuato il logout, ma la richiesta non e' stata eseguita");
    		  }
    	  },
    	  error : function(error) {
    		  console.log("Errore: " + error)
    	  }
      })
    };
  </script>
</section>