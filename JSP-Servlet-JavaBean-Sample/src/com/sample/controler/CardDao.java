package com.sample.controler;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.sample.model.CardModel;

import com.sample.utils.Tools;
//CardDao = contrôleur du MVC. Celui-ci va gérer la création des cartes et l'initialisation des variables et envoyer ces infos a nos servlets

public class CardDao {
	private final static String DB_LOCATION = "db-tp.cpe.fr";// infos sur la base de donnee 
	private final static int DB_PORT = 3306;
	private final static String DB_NAME = "binome36";
	private final static String DB_USER = "binome36";
	private final static String DB_PWD = "binome36";

	private String dblocation;
	private int dbport;
	private String dbName;
	private String username;
	private String pwd;

	private Random randomGenerator; //pour la fonctionnalite de carte aleatoire
	private List<CardModel> cardList; // pour recuperer une liste de cartes


	public CardDao(String dblocation, int dbport, String dbName, String username, String pwd) {
		this.dblocation = dblocation;
		this.dbport = dbport;
		this.dbName = dbName;
		this.username = username;
		this.pwd = pwd;
		//createCardList();
	}

	public CardDao() { //Constructeur par defaut
		this.dblocation = DB_LOCATION;
		this.dbport = DB_PORT;
		this.dbName = DB_NAME;
		this.username = DB_USER;
		this.pwd = DB_PWD;
		
	}

	public void addCard(CardModel card) { //on ajoute une carte a notre data base en recuperant le model de celle ci
		try {
			Class.forName("com.mysql.jdbc.Driver");
			java.sql.Connection cnx = java.sql.DriverManager.getConnection(
					"jdbc:mysql://" + this.dblocation + ":" + this.dbport + "/" + this.dbName, this.username, this.pwd);
			PreparedStatement st = cnx.prepareStatement("INSERT INTO Card (id, name, description,family,hp,energy,attack,defence,imgUrl) VALUES (?, ?, ?,?,?,?,?,?,?);");
			st.setString(1, card.getId());
			st.setString(2, card.getName());
			st.setString(3, card.getDescription());
			st.setString(4, card.getFamily());
			st.setInt(5, card.getHp());
			st.setInt(6, card.getEnergy());
			st.setInt(7, card.getAttack());
			st.setInt(8, card.getDefence());
			st.setString(9, card.getImgUrl());
			st.executeUpdate();//
			//cnx.commit();
			st.close();
			cnx.close();
		} catch (SQLException e) { // Exeption en cas de probleme
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public CardModel getCard(String id) { // On recupere le modele d'une carte en fonction de son id(le meme que celui de la bdd
		CardModel newCard = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			java.sql.Connection cnx = java.sql.DriverManager.getConnection(
					"jdbc:mysql://" + this.dblocation + ":" + this.dbport + "/" + this.dbName, this.username, this.pwd);
			PreparedStatement st = cnx.prepareStatement("select * from Card where id=?");
			st.setString(1, id);
			ResultSet rst = st.executeQuery();
			while (rst.next()) {
				newCard = new CardModel(rst.getString("name"), rst.getString("description"), rst.getString("family"),
						rst.getInt("hp"), rst.getInt("energy"), rst.getInt("defence"), rst.getInt("attack"),
						rst.getString("imgUrl"));
				newCard.setId(rst.getString("id"));
			}
			// st.executeUpdate("");//

			rst.close();
			st.close();
			cnx.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return newCard;
	}

	public CardModel getCardByName(String name) {//Recupere une carte en fonction de son nom
		CardModel newCard = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			java.sql.Connection cnx = java.sql.DriverManager.getConnection(
					"jdbc:mysql://" + this.dblocation + ":" + this.dbport + "/" + this.dbName, this.username, this.pwd);
			PreparedStatement st = cnx.prepareStatement("select * from Card where name=?");
			st.setString(1, name);
			ResultSet rst = st.executeQuery();
			while (rst.next()) {
				newCard = new CardModel(rst.getString("name"), rst.getString("description"), rst.getString("family"),
						rst.getInt("hp"), rst.getInt("energy"), rst.getInt("defence"), rst.getInt("attack"),
						rst.getString("imgUrl"));
				newCard.setId(rst.getString("id"));
			}
			rst.close();
			st.close();
			cnx.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return newCard;
	}

	public List<CardModel> getCards() { // Renvoie la liste de toutes les cartes dans la dbb
		List<CardModel> cardList;
		cardList = new ArrayList<>();
		try {

			Class.forName("com.mysql.jdbc.Driver");
			java.sql.Connection cnx = java.sql.DriverManager.getConnection(
					"jdbc:mysql://" + this.dblocation + ":" + this.dbport + "/" + this.dbName, this.username, this.pwd);
			PreparedStatement st = cnx.prepareStatement("select * from Card");
			ResultSet rst = st.executeQuery();
			while (rst.next()) {
				CardModel newCard = new CardModel(rst.getString("name"), rst.getString("description"),
						rst.getString("family"), rst.getInt("hp"), rst.getInt("energy"), rst.getInt("defence"),
						rst.getInt("attack"), rst.getString("imgUrl"));
				newCard.setId(rst.getString("id"));
				cardList.add(newCard);
			}
			// st.executeUpdate("");//
			rst.close();
			st.close();
			cnx.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cardList;
	}
	


	public String getCardsJson() {
		String jsonResult;
		List<CardModel> cardList = getCards();
		jsonResult = "[";
		boolean first=true;
		for (CardModel cardModel : cardList) {
			if(first){
				jsonResult=jsonResult+ Tools.toJsonString(cardModel);
				first=false;
			}else{
				jsonResult=jsonResult+","+Tools.toJsonString(cardModel);
			}
		}
		jsonResult=jsonResult+"]";
		
		return jsonResult;
	}
	
	public CardModel getRandomCards(){
		cardList=getCards();// return all cards of the db
		randomGenerator= new Random();// create the new object 
		int index=randomGenerator.nextInt(this.cardList.size());// we give a random index of the db
		return this.cardList.get(index);// we return the random card
	}
	
   
		
}
