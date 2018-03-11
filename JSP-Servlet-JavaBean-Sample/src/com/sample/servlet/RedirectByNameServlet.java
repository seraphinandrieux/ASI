package com.sample.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sample.controler.CardDao;
import com.sample.model.CardModel;

@WebServlet("/byName") //A ajouter au pattern pour afficher une carte par nom
public class RedirectByNameServlet extends HttpServlet {
	private static final String NCARD = "ncard";
	private static final long serialVersionUID = 1L;
	private CardDao dao;//On recup le controlleur pour communiquer les infos a traiter et envoyer sur la vue
       
    public RedirectByNameServlet() {
        super();
    }

    	//Methode GET qui va recup les infos via l'URL
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		getDao();
		CardModel randCard=this.dao.getCardByName(request.getParameter("name"));
		request.setAttribute(NCARD, randCard);//On envoie a la requete l'attribut NCARD avec les infos randcard
		this.getServletContext().getRequestDispatcher( "/WEB-INF/displayByName.jsp" ).forward( request, response );//On indique au servlet ou recuperer la page statique a renvoyer
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		//DO NOTHING
	}
	
	public void getDao(){// On recupere les infos du DAO pour transmettre les infos a nos pages .jsp
		if(this.getServletContext().getAttribute("DAO")!=null){
			this.dao=(CardDao)this.getServletContext().getAttribute("DAO");
		}else{
			this.dao=new CardDao();
			this.getServletContext().setAttribute("DAO",this.dao);
		}
	}

}

