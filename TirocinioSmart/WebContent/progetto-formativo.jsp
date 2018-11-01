<section id="prog-formativo">
	<div style="color:black;" align="center" >
	    <span id="wrap-prog-formativo-info">
	        <b class="prog-formativo-info">Progetto Formativo Info:</b>
	        <p> 
	            <%= progettoFormativoBean.getInfo(); %>
	        </p>
	        <b class="prog-formativo-info">Progetto Formativo Approvazione:</b>
	        <p>
	            <%= progettoFormativoBean.getApprovazione(); %>
	        </p>
	    </span>
	    <label id="wrap-ts-input">
	        <input class="ts-button" name="visualizza-prog-formativo" 
	            type="button" value="Visualizza"/>
	        <input class="ts-button" name="approva-prog-formativo" 
	            type="button" value="Approva"/>
	    </label>
	</div>
	
</section>