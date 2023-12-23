<%@page import="controller.ArticleDAO"%>
<%@page import="controller.ClientDAO"%>
<%@page import="model.Article"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
	<script src="js/jquery-3.7.1.js" type="text/javascript"></script>

</head>
<body>
    <div class="container">
            <div class="row">
                <div class="col-4">
                    <div class="list-group" >
                        <a class="list-group-item list-group-item-action"   href="CommandeServlet"  >Gestion des Commandes</a>
                        <a class="list-group-item list-group-item-action"   href="ClientServlet"  >Gestion des Client</a>
                        <a class="list-group-item list-group-item-action"  href="ArticleServlet"  >Gestion des Articles</a>
                      </div>
                </div>
            </div>
        <section class="rightside">
            <div class="nav">
                <ul class="nav nav-pills mb-3" >
                    <li class="nav-item">
                        <a class="nav-link"   href="CommandeServlet?op=add" >Nouveau Commande</a>
                      </li>
                      <li class="nav-item">
                        <a class="nav-link" href="CommandeServlet" >Liste des Commandes</a>
                      </li>
                  </ul>
            </div>
            <div class="">
            <%ArrayList<String> std =  (ArrayList<String>)request.getAttribute("datacmd"); 
                   ClientDAO cl=new ClientDAO();
                   ArticleDAO ar=new ArticleDAO();
                   System.out.print(std);
			        for(String s:std){
			        String [] line=s.split(",");
			        }%> 
        <div class="">
            <h1>Informations de Commande</h1>
            <p>Numero de Commande: 123456789</p>
            <p>Date de Commande: 2023-12-22</p>
            <p>Etat de Commande: EnCours</p>
        </div>

        <div class="">
            <h2>Informations Client </h2>
            <table>
                <tr>
                    <th>Nom Client :</th>
                    <td>John Doe</td>
                </tr>
                <tr>
                    <th>Tel :</th>
                    <td>(555) 555-5555</td>
                </tr>
                <tr>
                    <th>Address :</th>
                    <td>maroc</td>
                </tr>
            </table>

            <h2>Details de Commande</h2>
            <table>
                <tr>
                    <th>Article</th>
                    <th>Quantite</th>
                    <th>Prix Unitaire</th>
                    <th>Total</th>
                </tr>
                <tr>
                    <td>Article A</td>
                    <td>2</td>
                    <td>50.00</td>
                    <td>100.00</td>
                </tr>
            </table>

            <div>
                <p><strong>Total: 100.00</strong></p>
            </div>
        </div>
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
                //var jsonResponse = JSON.parse(responseData);
                articlejson = responseData[0];
              
                if($.trim(responseData)){
                /*$('#infosarticle').append('<input type="hidden" name="idarticle" value="'+responseData[0]["id_article"]+'" id="idarticle" />');*/
                $( "#infosarticle" ).append("<tr>")
                $( "#infosarticle" ).append("<td>"+responseData[0]["id_article"]+"</td>")
				$( "#infosarticle" ).append("<td>"+responseData[0]["libelle"]+"</td>")
				$( "#infosarticle" ).append("<td>"+responseData[0]["categorie"]+"</td>")
				$( "#infosarticle" ).append("<td>"+responseData[0]["prix"]+"</td>")
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
	$('#listart').val(JSON.stringify(listart));
	console.log(document.getElementById('listart').value);
	});

</script>  
</body>
</html>