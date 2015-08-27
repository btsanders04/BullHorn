

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import customTools.DBUtil;

/**
 * Servlet implementation class WriteBlog
 */
@WebServlet("/WriteBlog")
public class WriteBlog extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WriteBlog() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	*/
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("description", "Enter Description");
		request.setAttribute("action","WriteBlog");
		getServletContext().getRequestDispatcher("/WriteBlog.jsp").forward(request,
				response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		model.Userprofile user = (model.Userprofile)request.getSession().getAttribute("User");
		String description = request.getParameter("desc");
		postBlog(user,new Date(), description);
		getServletContext().getRequestDispatcher("/index.jsp").forward(request,
				response);
	}
	
	
	protected void postBlog(model.Userprofile user, Date date, String desc){
		
		model.Post blog = new model.Post();
		blog.setDescription(desc);
		blog.setPostDate(date);
		blog.setUserprofile(user);
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		trans.begin();
		try{
		em.persist(blog);
		trans.commit();
		}catch (Exception e){
			System.out.println(e);
			trans.rollback();
		} finally{
			em.close();
		}	
		
	
	}
	
}
