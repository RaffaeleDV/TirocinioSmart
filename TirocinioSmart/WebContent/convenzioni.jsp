<section id="tutor-convenzioni">
    <h1 id="conv-heading">Convenzioni</h1>
    <div id="wrap-tutor-convenzioni">
        <div id="wrap-convenzioni">
            <%@ include file="lista-convenzioni.jsp" %>
        </div>
    </div>
    <span id="registra-convenzione">
        <input class="ts-button" name="nuova-convenzione" type="button" 
            value="Registra Nuova Convenzione"/>
    </span>
    </br>
    <span id="new-convenzione">
        <textarea id="tutor-conv" placeholder="Convenzione"
            ros="40" cols="40">
        </textarea>
        <label id="wrap-ts-input">
            <input class="ts-button" name="compila-conv" 
                type="button" value="Compila"/>
            <input class="ts-button" name="invio-conv" 
                type="button" value="Invio"/>
        </label>
    </span>
</section>
