<%@page import="model.Client"%> 
<%@page import="java.util.ArrayList"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<jsp:include page="/Componement/Header.jsp"></jsp:include>

        <section class="rightside">
            <div class="nav" style="margin-top: 10px">
              <ul class="nav nav-pills mb-3">
                <li class="nav-item">
                  <a class="nav-link btn btn-outline-primary" style="margin-right: 10px"    href="ClientServlet?op=add" >Nouveau Client</a>
                </li>
                <li class="nav-item">
                  <a class="nav-link btn btn-outline-primary"    href="ClientServlet">Liste des Clients</a>
                </li>
              </ul>
            </div>
            <div>
                <h1>Liste des Clients </h1>
                <input type="text" id="SearchNom" onkeyup="myFunction()" placeholder="Recherche Par Nom Client" title="Taper Nom Client" style="width: 500px;">
          <table class="table" id="Table">
                  <tr>
                    <th scope="col">Nom Client</th><th scope="col">Prenom Client</th><th scope="col">Tel</th><th scope="col">Adresse</th><th scope="col"></th><th scope="col"></th>
                  </tr>
                  </tr>
                  <%ArrayList<Client> std =  (ArrayList<Client>)request.getAttribute("data"); 
			        for(Client s:std){%> 
			        <%-- Arranging data in tabular form 
			        --%> 
			            <tr> 
			                <td><%=s.getNom()%></td> 
			                <td><%=s.getPrenom()%></td> 
			                <td><%=s.getTel()%></td> 
			                <td><%=s.getAdresse()%></td> 
			                <td><a class="btn btn-danger" href="ClientServlet?op=del&id=<%=s.getId_client()%>">Supprimer</a></td>
    			            <td><a class="btn btn-primary" href="ClientServlet?op=mod&id=<%=s.getId_client()%>">Modifier</a></td>
			            
			            </tr> 
			            <%}%> 
                </table>
            </div>
        </section>
    </div>
  <script>
function myFunction() {
  var input, filter, table, tr, td, i, txtValue;
  input = document.getElementById("SearchNom");
  filter = input.value.toUpperCase();
  table = document.getElementById("Table");
  tr = table.getElementsByTagName("tr");
  for (i = 0; i < tr.length; i++) {
    td = tr[i].getElementsByTagName("td")[0];
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