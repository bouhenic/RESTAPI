package fr.snir.rest;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;

import javax.ws.rs.*;
import java.io.IOException;

//Création de l'API REST en utilisant la dépendance RESTEASY

@Path("/")
public class RestService {
    @GET // Requête GET
    @Path("/temperature")
    @Produces("application/json")
    public Objet getProductInJSON() {
        Objet objetJava =new Objet(); //création d'un objet java
        Request requete = new Request(); //création d'une requête sql
        int nb = requete.GetTemp().size(); //recherche du nombre total d'enregistrement
        objetJava.setDate(String.valueOf(requete.GetTemp().get(nb-3))); //Ecriture du timestamp dans l'objet créé
        objetJava.setTemp(String.valueOf(requete.GetTemp().get(nb-2)));//Ecriture de la température dans l'objet créé
        objetJava.setId(String.valueOf(requete.GetTemp().get(nb-1)));//Ecriture de l'id dans l'objet créé
        return objetJava; //retour de l'objet java en objet json
    }

    @POST // Requête POST
    @Path("/temperature")
    @Produces("application/x-www-form-urlencoded")
    @Consumes("application/x-www-form-urlencoded")
    public String addmessage(@FormParam("TEMP") String temp){
        Request requete = new Request();//création d'une requête sql
        requete.AddTemp(temp);//Lancement de la méthode d'ajout
        return "temp="+temp+" ajoutée"; //création du message de retour
    }

    @GET // Requête GET sur un enregistrement
    @Path("/temperature/{id}")
    @Produces("application/json")
    public Objet getTempById(@PathParam("id") int id) {

        Objet objetJava =new Objet();
        Request requete = new Request();
        int nb = requete.GetOneTemp(id).size();
        objetJava.setDate(String.valueOf(requete.GetOneTemp(id).get(nb-3)));
        objetJava.setTemp(String.valueOf(requete.GetOneTemp(id).get(nb-2)));
        objetJava.setId(String.valueOf(requete.GetOneTemp(id).get(nb-1)));
        return objetJava;
    }

    @PUT // Requête put pour une mise à jour
    @Path("/temperature/{id}")
    @Produces("application/json")
    @Consumes("application/x-www-form-urlencoded")
    public String updateTempById(@PathParam("id") int id,String tempjson) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper(); //création d'un objet objectMapper pour désérialiser un objet JSON
        JsonNode jsonNode = objectMapper.readTree(tempjson); //Lecture de l'objet JSON reçudans le corps de la requête PUT
        String temp = jsonNode.get("temp").asText();//sélection de la valeur de la température
        Request requete = new Request();//création d'une requête sql
        requete.UpdateOneTemp(id,temp);//Mise à jour de l'enregistrement demandé avec la valeur de la température.
        return "mise à jour effectuée de l'enregistrement " + id;
    }

    @DELETE // Requête Delete pour effacer un enregistrement
    @Path("/temperature/{id}")
    @Produces("application/json")
    public String deleteTempById(@PathParam("id") int id) {

        Objet objetJava =new Objet();
        Request requete = new Request();
        requete.deleteTempById(id);
        return "effacement enregistrement "+id+" effectué";
    }


}
