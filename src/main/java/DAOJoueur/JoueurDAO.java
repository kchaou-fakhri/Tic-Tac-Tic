package DAOJoueur;

import IDAO.IDAO;
import model.Joureur;
import model.UtilitairesBD;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class JoueurDAO implements IDAO {

    Connection cn;
    public JoueurDAO(){
        UtilitairesBD.seConnecter("src\\main\\java\\model\\DBParameters");    }

    @Override
    public Boolean insert(Object joureur) {
        boolean verify = false;
        Joureur _joureur = ((Joureur) joureur);
        try {

            String UpdateSQL = "insert into Joueur values ('"+_joureur.getId()+"','"
                    +_joureur.getFirstName()+"','"
                    +_joureur.getLastName()+"','"
                    +_joureur.getScore()+"')";


            Statement s = cn.createStatement();
            verify = s.execute(UpdateSQL);
            System.out.println(UpdateSQL);

        }catch (Exception e){
            e.printStackTrace();

        }
        return verify;
    }

    @Override
    public Joureur getById(Object id) {

        Joureur joureur = new Joureur();
        try{
            ResultSet resultSet = UtilitairesBD.OuvrirReq("SELECT * FROM Joueur where id = '"+id+"'");

            if (resultSet != null){
                resultSet.next();

                joureur.setId(resultSet.getString(1));
                joureur.setFirstName(resultSet.getString(2));
                joureur.setLastName(resultSet.getString(3));
                joureur.setScore(resultSet.getDouble(4));

            }

        }catch (Exception e){
            e.printStackTrace();

        }
        return joureur;
    }

    @Override
    public ArrayList getAll() {
        ArrayList<Joureur> joureurArrayList = new ArrayList<>();
        Joureur joureur;
        try {
            Statement statement = cn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Joueur");

            if (resultSet != null){
                while (resultSet.next()){
                    joureur = new Joureur();
                    joureur.setId(resultSet.getString(1));
                    joureur.setFirstName(resultSet.getString(2));
                    joureur.setLastName(resultSet.getString(3));
                    joureur.setScore(resultSet.getDouble(4));

                    joureurArrayList.add(joureur);
                }
            }


        }catch (Exception e){
            e.printStackTrace();
        }


        return joureurArrayList;
    }
}
