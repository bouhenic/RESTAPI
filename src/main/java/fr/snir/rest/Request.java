package fr.snir.rest;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Request {
    private String url = "jdbc:mysql://171.17.0.3:3306/METEO?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private String login ="root"; //identifiant bdd mysql
    private String passwd = "root";//identifiant bdd mysql
    private Connection cn =null;
    private Statement st = null;
    private ResultSet rs = null;
    private String value = null;
    private List<String> liste =  new ArrayList<String>() ; //création d'une liste pour stocker id,temp et time


    public List GetTemp() { //Méthode pour lire la base de donnée
        this.url = url;
        this.login = login;
        this.passwd = passwd;
        this.cn = cn;
        this.st = st;
        this.rs = rs;

        try {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");//chargement du pilote mysql
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            cn = DriverManager.getConnection(url,login,passwd);//ouverture d'une connexion à la base
            st = cn.createStatement(); //création d'une requête
            String sql = "SELECT * FROM TEMPERATURE";

            rs = st.executeQuery(sql); //exécution de la requête et récupération du résultat

            while (rs.next()){
                liste.add(rs.getString("TIME")) ; // récupération de chaque ligne et stockage dans une liste
                liste.add(rs.getString("TEMP")) ;
                liste.add(rs.getString("ID")) ;
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        finally {
            try {
                cn.close(); //fermeture de la connexion
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            try {
                st.close();//fermeture de la requête
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        return liste;
    }


    public String  AddTemp(String value) { //méthode pour ajouter un enregistrement
        this.url = url;
        this.login = login;
        this.passwd = passwd;
        this.value = value;
        this.cn = cn;
        this.st = st;
        this.rs = rs;

        try {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            cn = DriverManager.getConnection(url,login,passwd);
            st = cn.createStatement();
            String sql = "INSERT INTO TEMPERATURE(TEMP) VALUES('"+value+"')";

            st.executeUpdate(sql); //exécution de la mise à jour

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        finally {
            try {
                cn.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            try {
                st.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return "température ajoutée";
    }

    public List GetOneTemp(int ID) { //méthode pour sélectionner un enregistrement
        this.url = url;
        this.login = login;
        this.passwd = passwd;
        this.cn = cn;
        this.st = st;
        this.rs = rs;

        try {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            cn = DriverManager.getConnection(url,login,passwd);
            st = cn.createStatement();
            String sql = "SELECT * FROM TEMPERATURE WHERE ID="+ID+" LIMIT 1";

            rs = st.executeQuery(sql);


            while (rs.next()){
                //System.out.println(rs.getString("TIME")+" "+rs.getString("TEMP"));
                //System.out.println(rs.getString("TEMP"));
                liste.add(rs.getString("TIME")) ;
                liste.add(rs.getString("TEMP")) ;
                liste.add(rs.getString("ID")) ;
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        finally {
            try {
                cn.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            try {
                st.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

            return liste;
        }

    }

    public List UpdateOneTemp(int ID,String value) { //méthode pour mettre à jour un enregistrement
        this.url = url;
        this.login = login;
        this.passwd = passwd;
        this.cn = cn;
        this.st = st;
        this.rs = rs;

        try {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            cn = DriverManager.getConnection(url,login,passwd);
            st = cn.createStatement();
            String sql = "UPDATE TEMPERATURE SET TEMP='"+value+"' WHERE id="+ID;

            st.executeUpdate(sql);


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        finally {
            try {
                cn.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            try {
                st.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

            return liste;
        }

    }

    public String deleteTempById(int ID) { //méthode pour effacer un enregistrement
        this.url = url;
        this.login = login;
        this.passwd = passwd;
        this.cn = cn;
        this.st = st;
        this.rs = rs;

        try {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            cn = DriverManager.getConnection(url,login,passwd);
            st = cn.createStatement();
            String sql = "DELETE FROM TEMPERATURE WHERE id="+ID;

            st.executeUpdate(sql);


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        finally {
            try {
                cn.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            try {
                st.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

            return "Enregistrement effacé";
        }

    }

}