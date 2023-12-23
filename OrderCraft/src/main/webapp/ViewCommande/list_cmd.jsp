<%@page import="controller.ArticleDAO"%>
<%@page import="controller.ClientDAO"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="model.Commande"%>
<%@page import="java.util.ArrayList"%>

<jsp:include page="/Componement/Header.jsp"></jsp:include>
        <section class="rightside">
            <div class="nav" style="margin-top: 10px">
                <ul class="nav nav-pills mb-3" >
                    <li class="nav-item">
                        <a class="nav-link btn btn-outline-primary" style="margin-right: 10px"  href="CommandeServlet?op=add" >Nouveau Commande</a>
                      </li>
                      <li class="nav-item">
                        <a class="nav-link btn btn-outline-primary"  href="CommandeServlet" >Liste des Commandes</a>
                      </li>
                  </ul>
            </div>
            <div>
                <h1>Liste des Commandes </h1>
                <input type="text" id="SearchCommande" onkeyup="myFunction()" placeholder="Recherche Par Nom Client" title="Taper Nom Client" style="width: 500px;">
                
                <table class="table" id="Table">
                  <tr>
                    <th scope="col">Id Commande</th><th scope="col">Nom Client</th><th scope="col">Etat</th><th scope="col">Date Commande</th><th>Operation</th>
                  </tr>
                   <%ArrayList<Commande> std =  (ArrayList<Commande>)request.getAttribute("datacmd"); 
                   ClientDAO cl=new ClientDAO();
                   ArticleDAO ar=new ArticleDAO();
			        for(Commande s:std){
			        %> 
			            <tr> 
			                <td><%=s.getId_commande()%></td> 
			                <td><%=cl.afficherClientsAvecId(s.getId_client()).getNom()+" "+cl.afficherClientsAvecId(s.getId_client()).getPrenom()%></td> 
			                <td><%=s.getEtat()%></td>
			                <td><%=s.getcreated_at()%></td>  
			                <td><a class="btn btn-danger" href="CommandeServlet?op=del&id=<%=s.getId_commande()%>">Supprimer</a>&nbsp;&nbsp;<a class="btn btn-info" href="CommandeServlet?op=show&id=<%=s.getId_commande()%>">Infos</a></td>
			            </tr> 
			            <%}%>
                </table>
            </div>
        </section>
    </div>
    <script>
function myFunction() {
  var input, filter, table, tr, td, i, txtValue;
  input = document.getElementById("SearchCommande");
  filter = input.value.toUpperCase();
  table = document.getElementById("Table");
  tr = table.getElementsByTagName("tr");
  for (i = 0; i < tr.length; i++) {
    td = tr[i].getElementsByTagName("td")[1];
    if (td) {
      txtValue = td.textContent || td.innerText;
      if (txtValue.toUpperCase().indexOf(filter) > -1) {
        tr[i].style.display = "";
      } else {
        tr[i].style.display = "none";
      }
    }       
  }
}
</script>    
</body>
</html>