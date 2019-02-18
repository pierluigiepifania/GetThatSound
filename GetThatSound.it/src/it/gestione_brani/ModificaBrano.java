package it.gestione_brani;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.model.BranoBean;
import it.model.BranoManager;

@WebServlet(description = "Modifica un brano", urlPatterns = { "/ModificaBrano" })
public class ModificaBrano extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ModificaBrano() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int song_id = Integer.parseInt(request.getParameter("song_id"));
			String titolo = request.getParameter("titolo");
			String album = request.getParameter("album");
			String artista = request.getParameter("artista");
			int user_id = Integer.parseInt(request.getParameter("user_id"));
			BranoBean bb = new BranoBean(song_id, titolo, artista, album, null, user_id);
			response.getWriter().println(BranoManager.modificaBrano(bb));
		} catch (SQLException e) {
			e.printStackTrace();
			response.getWriter().append("Error during servlet ModificaBrano");
		}
	}
}