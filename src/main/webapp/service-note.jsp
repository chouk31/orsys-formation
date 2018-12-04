<%-- 
    Document   : service-note.jsp
    Created on : 15 mars 2015, 20:09:50
    Author     : olivier CHARLES
--%>

<%@page contentType="text/html; charset=UTF-8"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="com.occonseils.examen.EvaluateurNiveau"%>

<%
    System.out.println("Entree dans service-note.jsp");
    JSONObject obj=new JSONObject();
    String cours = request.getParameter("cours");
    String examen = request.getParameter("examen"); 
    if ( cours == null ) cours = "";
    if ( examen == null ) examen = "";
       
    //Simulation attente serveur de 1 seconde
    long t = System.currentTimeMillis();
    while( System.currentTimeMillis() - t < 1000 );
    
    
    String note = EvaluateurNiveau.evaluerNiveau(cours,examen);
    obj.put("noteglobale",note);
    out.print(obj);
    out.flush();
%>
