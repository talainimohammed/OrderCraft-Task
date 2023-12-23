<%@page import="model.Etat"%>
<%@page import="model.Article"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<jsp:include page="/Componement/Header.jsp"></jsp:include>
    <script src="js/jquery-3.7.1.js"></script>
    <link rel="stylesheet" href="css/style.css">

        <section class="rightside">
            <div class="nav" style="margin-top: 10px">
                <ul class="nav nav-pills mb-3" >
                    <li class="nav-item">
                        <a class="nav-link btn btn-outline-primary" style="margin-right: 10px"  href="CommandeServlet?op=add" >Nouveau Commande</a>
                      </li>
                      <li class="nav-item">
                        <a class="nav-link btn btn-outline-primary" href="CommandeServlet" >Liste des Commandes</a>
                      </li>
                  </ul>
            </div>
            <div>
                <h1>Nouveau Commande </h1>
                <form action="CommandeServlet" name="cmdform" method="post">
                <input type="hidden" name="op" value="addcmd">
                <input type="hidden" name="listart" id="listart">
                    <label for="">Nom Client</label>
                    <input type="text"  id="nom" required="required">
                    <br><br>
                    
                    <div id="infosclient"> </div>  
                    <div id="addclient">
                    <label>Client n'existe pas</label>
                    <a href="ClientServlet?op=add">Ajouter Client</a><br><br>
                     </div> 
                    <label for="">Libelle Article</label>
                    <input type="text"  id="libelle" required="required"><br><br>
                    <table id="infosarticle" class="table"> </table>  
                    
                    <label for="">Quantite</label>
                    <input type="number"id="qtyv" required="required" value="1"><br><br>
                    <a class="btn btn-primary" id="addarticle">Ajouter l'article Dans list</a><br><br>
                    <label for="">Etat</label>
                    <select name="etat">
				   <%for(Etat etat:Etat.values()){%>
				      <option value="<%=etat.name() %>"><%=etat.name() %></option>
				   <%} %>
				</select><br><br>
                   
                    <input type="submit" class="btn btn-success" value="Enregistrer Commande">
                </form>
            </div>
            <div class="rightdiv" style="background-color: #7BD3EA;width: 400px; position: absolute;right:50px; top:150px; ">
             <table class="table" id="">
             <th>Libelle</th><th>Prix</th><th>Qty</th><th>Total</th>
             </table>
             <table class="table" id="articlechoisie">
             </table>
             <table class="table" id="total">
             </table>
            </div>
        </section>
    </div>
<script> 
var clientjson=null;
$("#addclient").hide();
$(document).ready(function() {
	$('#nom').blur(function() {
		$.ajax({
			url : 'CommandeServlet?op=cl',
			data : {
				nom : $('#nom').val()
			},
			success : function(responseData) {
                //var jsonResponse = JSON.parse(responseData);
                 clientjson = responseData[0];
                
                if($.trim(responseData)){
                $('#infosclient').append('<input type="hidden" name="idclient" value="'+responseData[0]["id_client"]+'" id="idclient" />');
                $( "#infosclient" ).append("<label for='prenom'>Prenom client : "+responseData[0]["prenom"]+"</label><br><br>")
                $( "#infosclient" ).append("<label for='tel'>Tel client : "+responseData[0]["tel"]+"</label><br><br>")
                $( "#infosclient" ).append("<label for='adresse'>Adresse Client: "+responseData[0]["adresse"]+"</label><br><br>")
                $("#addclient").hide();
                }
                else{
                	$("#infosclient").hide();
                	 $("#addclient").show();
                    //$( "#infosclient" ).append("<label for='prenom'>Prenom client : "+responseData[0]["prenom"]+"</label><br><br>")
                }
			}
		});
	});
	
});
var articlejson=null;
$(document).ready(function() { 
	$('#libelle').blur(function() {
		$.ajax({
			url : 'CommandeServlet?op=ar',
			data : {
				nom : $('#libelle').val()
			},
			success : function(responseData) {
                articlejson = responseData[0];
              
                if($.trim(responseData)){
                $("#infosarticle").empty();
                $( "#infosarticle" ).append(" <th>Libelle</th><th>categorie</th><th>prix</th><th>stock</th>")
                $( "#infosarticle" ).append("<tr>")
				$( "#infosarticle" ).append("<td>"+responseData[0]["libelle"]+"</td>")
				$( "#infosarticle" ).append("<td>"+responseData[0]["categorie"]+"</td>")
				$( "#infosarticle" ).append("<td>"+responseData[0]["prix"]+"</td>")
				$( "#infosarticle" ).append("<td>"+responseData[0]["stock"]+"</td>")
			    $( "#infosarticle" ).append("</tr>")

                //$("#addclient").hide();
                }
                else{
                	//$("#infosclient").hide();
                	// $("#addclient").show();
                    //$( "#infosclient" ).append("<label for='prenom'>Prenom client : "+responseData[0]["prenom"]+"</label><br><br>")
                }
			}
		});
	});
});
var listart=[];
$('#addarticle').click( function() {
	articlejson["qty"]=$('#qtyv').val()
	listart.push(articlejson);
	//console.log(JSON.stringify(listart));
$("#articlechoisie tr").remove();
var s=0;
	$.each(listart, function(index, articlejson) {
	   var row = "<tr>" +
	       "<td>" + articlejson["libelle"] + "</td>" +
	       "<td>" + articlejson["prix"] + "</td>" +
	       "<td>" + articlejson["qty"] + "</td>" +
	       "<td>" + (articlejson["prix"] * articlejson["qty"]) + "</td>" +
	       "</tr>";
	   $("#articlechoisie").append(row);
	   s+=(articlejson["prix"] * articlejson["qty"]);
	});
	$("#total").empty();
	var row1="<tr><td></td><td></td><td>Total Commande</td><td>"+s+"</td></tr>";
	$("#total").append(row1);
		
	$('#listart').val(JSON.stringify(listart));
	console.log(document.getElementById('listart').value);
	});

</script>  
</body>
</html>