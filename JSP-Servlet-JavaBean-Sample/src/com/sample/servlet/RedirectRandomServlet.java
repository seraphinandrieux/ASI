package com.sample.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sample.controler.CardDao;
import com.sample.model.CardModel;

@WebServlet("/random")//A ajouter au pattern pour afficher une carte au hasard
public class RedirectRandomServlet extends HttpServlet {
	private static final String RCARD = "rcard";
	private static final long serialVersionUID = 1L;
	private CardDao dao;
       
    public RedirectRandomServlet() {
        super();
    }
    //Nous rentrons dans cette methode lorsque l'on charge la page http://localhost:8080/JSP-Servlet-JavaBean-Sample/random
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		getDao();
		CardModel randCard=this.dao.getRandomCards();
		request.setAttribute(RCARD, randCard);
		this.getServletContext().getRequestDispatcher( "/WEB-INF/displayRandom.jsp" ).forward( request, response );
	}
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		//DO NOTHING
	}
	
	public void getDao(){
		if(this.getServletContext().getAttribute("DAO")!=null){
			this.dao=(CardDao)this.getServletContext().getAttribute("DAO");
		}else{
			this.dao=new CardDao();
			this.getServletContext().setAttribute("DAO",this.dao);
		}
	}

}
