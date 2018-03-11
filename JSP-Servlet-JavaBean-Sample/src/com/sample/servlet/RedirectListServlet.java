package com.sample.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sample.controler.CardDao;
import com.sample.model.CardModel;

@WebServlet("/list") //A ajouter au pattern pour afficher la liste des cartes que le webservice nous fournis
public class RedirectListServlet extends HttpServlet {
	private static final String LCARD = "lcard";
	private static final long serialVersionUID = 1L;
	private CardDao dao;
       
    public RedirectListServlet() {
        super();
    }
  //Methode GET qui va recup les infos via l'URL
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		getDao();
		
		List<CardModel> listCards=this.dao.getCards();
		
		request.getSession().setAttribute(LCARD, listCards);
		
		this.getServletContext().getRequestDispatcher( "/WEB-INF/displayList.jsp" ).forward( request, response );
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		//DO NOTHING
	}
	// On recupere les infos du DAO pour transmettre les infos a nos pages .jsp
	public void getDao(){
		if(this.getServletContext().getAttribute("DAO")!=null){
			this.dao=(CardDao)this.getServletContext().getAttribute("DAO");
		}else{
			this.dao=new CardDao();
			this.getServletContext().setAttribute("DAO",this.dao);
		}
	}

}
