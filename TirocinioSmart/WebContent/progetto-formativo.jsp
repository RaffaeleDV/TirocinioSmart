<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isThreadSafe="false"
    import="it.unisa.model.ProgettoFormativoBean"%>
<section id="prog-formativo">
	<div style="color:black;" align="center" >
	    <div class="wrap-prog-formativo">
	        <%
	          ProgettoFormativoBean progetto = 
	            (ProgettoFormativoBean)session.getAttribute("SessionProgetto");
	        %>
	        <div class="wrap-prog-formativo-info">
		        <b class="prog-formativo-info">ID Del Progetto Formativo:</b>
		        <p class="prog-formativo-info">
		        	<%= progetto.getId() %>
		        </p>
	        </div>
	        <div class="wrap-prog-formativo-info">
	        	<b class="prog-formativo-info">Informazioni Del Progetto Formativo:</b>
		        <p class="prog-formativo-info"> 
		            <%= progetto.getInfo() %>
		        </p>
	        </div>
	        <div class="wrap-prog-formativo-info">
	        	<b class="prog-formativo-info">Approvazione Del Progetto Formativo:</b>
		        <p class="prog-formativo-indo">
		            <%= progetto.getApprovazione() %>
		        </p>
	        </div>
	    </div>
	    <label id="wrap-ts-input">
	        <input class="ts-button" name="visualizza-prog-formativo" 
	            type="button" value="Visualizza"/>
	        <input class="ts-button" name="approva-prog-formativo" 
	            type="button" value="Approva"/>
	    </label>
	</div>
	
</section>