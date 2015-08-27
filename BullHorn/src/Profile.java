
import java.io.IOException;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import customTools.DBUtil;

/**
 * Servlet implementation class Profile
 */
@WebServlet("/Profile")
public class Profile extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Profile() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Integer id = Integer.parseInt(request.getParameter("user"));
		
		
		String profile=printProfile(id);
		String posts=printPosts(id);
		// Set response content type
		response.setContentType("text/html");

		// Actual logic goes here.
		request.setAttribute("profile", profile);
		request.setAttribute("posts",posts);
		getServletContext().getRequestDispatcher("/Profile.jsp").forward(
				request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	protected String printPosts(int id){
		String profiles="";
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		String qString = "SELECT p FROM Post p where p.userprofile.userId = :id ORDER BY p.postDate desc";
		TypedQuery<model.Post> q = em.createQuery(qString, model.Post.class);
		q.setParameter("id", id);
		List<model.Post> posts = q.getResultList();
		try {
			for (model.Post p : posts) {

				profiles += " <div class = \"container\"><div class=\"panel panel-primary\"><div class=\"panel-heading\">"
						+ p.getPostId()
						+ "</div>"
						+ " <div class=\"panel-body\"><p > Date: "
						+  new SimpleDateFormat("MM/dd/yyyy")
						.format(p.getPostDate())
						+ "</p><p>"
						+ p.getDescription()
						+ "</p><div class=\"col-xs-12 col-md-6\"></div></div></div></div>";

			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return profiles;
	}
	
	protected String printProfile(int id){
		String profile="";
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		String qString = "SELECT u FROM Userprofile u where u.userId = :id";
		TypedQuery<model.Userprofile> q = em.createQuery(qString, model.Userprofile.class);
		q.setParameter("id", id);
		
		try{
			model.Userprofile user = q.getSingleResult();

			profile += "<div class=\"container\"><div class=\"item  col-xs-4 col-lg-4\">  <div class=\"panel panel-primary\"><div class=\"panel-heading\">"+user.getUserId() 
					+"</div><div class=\"panel-body\"> <p class=\"group inner list-group-item-text\">"+user.getUserName() 
						+"</p> <p class=\"group inner list-group-item-text\"> "+user.getUserEmail() 
						+"</p> <p class=\"group inner list-group-item-text\"> "+user.getUserZipcode()
						+"</p> <p class=\"group inner list-group-item-text\"> "+user.getJoinDate() 
						+"</p> <p class=\"group inner list-group-item-text\"> "+user.getMotto() 
					+"</p></div></div></div></div>";
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return profile;
	}
}
