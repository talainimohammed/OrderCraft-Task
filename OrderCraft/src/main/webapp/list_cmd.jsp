<%@page import="controller.ArticleDAO"%>
<%@page import="controller.ClientDAO"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="model.Commande"%>
<%@page import="java.util.ArrayList"%>
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
</head>
<body>
  <div class="container">
    <div class="row">
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
            <div>
                <h1>Liste des Commandes </h1>
                <table class="table">
                  <tr>
                    <th scope="col">Id Commande</th><th scope="col">Nom Client</th><th scope="col">Libelle Article</th><th scope="col">Quantite</th><th scope="col">Etat</th><th scope="col">Date Commande</th><th scope="col">Date Mise a Jour</th><th>Operation</th>
                  </tr>
                   <%ArrayList<String> std =  (ArrayList<String>)request.getAttribute("datacmd"); 
                   ClientDAO cl=new ClientDAO();
                   ArticleDAO ar=new ArticleDAO();
			        for(String s:std){
			        String [] line=s.split(",");
			        %> 
			            <tr> 
			                <td><%=line[0]%></td> 
			                <td><%=cl.afficherClientsAvecId(Integer.parseInt(line[1])).getNom()%></td> 
			                <td><%=ar.afficherArticleAvecId(Integer.parseInt(line[5])).getLibelle()%></td> 
			                <td><%=line[6]%></td> 
			                <td><%=line[2]%></td>
			                <td><%=line[3]%></td> 
			                <td><%=line[4]%></td> 
			                <td><a href="CommandeServlet?op=del&id=<%=line[0]%>">Supprimer</a><a href="CommandeServlet?op=mod&id=<%=line[0]%>">Modifier</a><a href="CommandeServlet?op=show&id=<%=line[0]%>">Infos</a></td>
			            </tr> 
			            <%}%>
                </table>
            </div>
        </section>
    </div>
    
</body>
</html>