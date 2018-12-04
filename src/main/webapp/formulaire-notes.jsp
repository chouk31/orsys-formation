<%-- 
    Document   : formulaire-notes
    Created on : 14 mars 2015, 16:40:20
    Author     : olivier charles
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"
  import="com.occonseils.examen.EvaluateurNiveau" %>
  
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Formulaire de saisie des notes</title>
    </head>
    <body>
        <% String cours = request.getParameter("cours");
           String examen = request.getParameter("examen"); 
           if ( cours == null ) cours = "";
           if ( examen == null ) examen = "";
        %>
            
        <form action="formulaire-notes.jsp" method="post">
                <p>Votre note de cours : <input type="text" name="cours" value="<%out.print(cours);%>" /></p>
                <p>Votre note d'examen : <input type="text" name="examen" value="<%out.print(examen);%>" /></p>
                <p><input type="submit" value="Evaluer le niveau"></p>
        </form>
        <%
            
            try {
                if ( cours != "" && examen != "" )
                {
                   String niveau = EvaluateurNiveau.evaluerNiveau( cours , examen);
                   out.print("<div id=\"niveau\">Niveau global : "+niveau+"</div>");
                }
            }
            catch( Exception e)
            {
               out.print("<div id=\"erreur\">"+e.getMessage()+"</div>"); 
            }
        %>
    </body>
</html>
