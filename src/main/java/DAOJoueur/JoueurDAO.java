package DAOJoueur;

import IDAO.IDAO;
import model.Joueur;
import model.UtilitairesBD;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class JoueurDAO implements IDAO {

    Connection cn;
    public JoueurDAO(){
       cn = UtilitairesBD.seConnecter("src\\main\\java\\model\\DBParameters");
        
    }

    @Override
    public Boolean insert(Object joureur) {
        boolean verify = false;
        Joueur _joueur = ((Joueur) joureur);
        try {

            String UpdateSQL = "insert into Joueur values ('"+ _joueur.getId()+"','"
                    + _joueur.getFirstName()+"','"
                    + _joueur.getLastName()+"','"
                    + _joueur.getScore()+"')";


            Statement s = cn.createStatement();
            verify = s.execute(UpdateSQL);
            System.out.println(UpdateSQL);

        }catch (Exception e){
            e.printStackTrace();

        }
        return verify;
    }

    @Override
    public Joueur getById(Object id) {

        Joueur joueur = new Joueur();
        try{
            ResultSet resultSet = UtilitairesBD.OuvrirReq("SELECT * FROM Joueur where id = '"+id+"'");

            if (resultSet != null){
                resultSet.next();

                joueur.setId(resultSet.getString(1));
                joueur.setFirstName(resultSet.getString(2));
                joueur.setLastName(resultSet.getString(3));
                joueur.setScore(resultSet.getDouble(4));

            }

        }catch (Exception e){
            e.printStackTrace();

        }
        return joueur;
    }

    @Override
    public ArrayList getAll() {
        ArrayList<Joueur> joueurArrayList = new ArrayList<>();
        Joueur joueur;
        try {
            Statement statement = cn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Joueur");

            if (resultSet != null){
                while (resultSet.next()){
                    joueur = new Joueur();
                    joueur.setId(resultSet.getString(1));
                    joueur.setFirstName(resultSet.getString(2));
                    joueur.setLastName(resultSet.getString(3));
                    joueur.setScore(resultSet.getDouble(4));

                    joueurArrayList.add(joueur);
                }
            }


        }catch (Exception e){
            e.printStackTrace();
        }


        return joueurArrayList;
    }
}
