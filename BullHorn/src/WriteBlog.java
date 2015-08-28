

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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
		request.setAttribute("recipients",getRecipients());
		getServletContext().getRequestDispatcher("/WriteBlog.jsp").forward(request,
				response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		model.Userprofile user = (model.Userprofile)request.getSession().getAttribute("User");
		String description = request.getParameter("desc");
		String recipient = request.getParameter("rec");
		postBlog(user,new Date(), description, recipient);
		getServletContext().getRequestDispatcher("/index.jsp").forward(request,
				response);
	}
	
	protected String getRecipients(){
		String returnRec="";
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		List<model.Userprofile> recipients = em.createQuery("select u from Userprofile u order by u.userId").getResultList();
		for(model.Userprofile u : recipients){
			returnRec+="<option>"+u.getUserName()+"</option>";
		}
		return returnRec;
	}
	
	protected void postBlog(model.Userprofile user, Date date, String desc, String rec){
		
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		model.Userprofile recipient = em.createQuery("select u from Userprofile u where u.userName = :rec",model.Userprofile.class).setParameter("rec", rec).getSingleResult();
		
		
		model.Post blog = new model.Post();
		blog.setDescription(desc);
		blog.setPostDate(date);
		blog.setUserprofile(user);
		blog.setRecipient(recipient);
		
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
