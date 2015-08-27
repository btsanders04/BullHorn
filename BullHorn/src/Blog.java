
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Post;
import model.Userprofile;
import customTools.DBUtil;

/**
 * Servlet implementation class Blog
 */
@WebServlet("/Blogs")
public class Blog extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Blog() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		String qString = "SELECT p FROM Post p ORDER BY p.postDate desc";
		String blogs = "";
		TypedQuery<model.Post> q = em.createQuery(qString, model.Post.class);
		List<model.Post> posts = q.getResultList();
		for (model.Post p : posts) {

			blogs += "<div class=\"container\">";
			blogs += "<div class = \"panel panel-primary\" ><div class=\"panel-heading\" style=\"background-color:black\">"
					+ "<a href=\"Profile?user="+p.getUserprofile().getUserId()+"\"><font color=\"white\">" + p.getUserprofile().getUserName() + "</font></a></div>";
			blogs += " <div class=\"panel-body\"><p > Date: "
					+ new SimpleDateFormat("MM/dd/yyyy")
							.format(p.getPostDate()) + " </p>";
			blogs += "<p>" + p.getDescription() + "</p></div></div></div>";

		}
		request.setAttribute("blogs", blogs);
		getServletContext().getRequestDispatcher("/Blogs.jsp").forward(request,
				response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String word = request.getParameter("words");
		String blogs = "";
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		String qString = "SELECT p FROM Post p WHERE p.description LIKE :desc";
		TypedQuery<model.Post> q = em.createQuery(qString, model.Post.class);
		q.setParameter("desc", "%"+word+"%");
		try {
			List<model.Post> posts = q.getResultList();
			System.out.println(q.toString());
			for (model.Post p : posts) {

				
				blogs += "<div class=\"container\">";
				blogs += "<div class = \"panel panel-primary\"><div class=\"panel-heading style=\"background-color:black\">"
						+ "<a href=\"Profile?user="+p.getUserprofile().getUserId()+"\"><font color=\"white\">" +p.getUserprofile().getUserName() + "</font></a></div>";
				blogs += " <div class=\"panel-body\"><p> Date: "
						+ new SimpleDateFormat("MM/dd/yyyy").format(p
								.getPostDate()) + " </p>";
				blogs += "<p>" + p.getDescription() + "</p></div></div></div>";

			}
			System.out.println(blogs);
		} catch (NoResultException e) {
			System.out.println(e);
		} finally {
			em.close();
		}
		request.setAttribute("blogs", blogs);
		getServletContext().getRequestDispatcher("/Blogs.jsp").forward(request,
				response);
	}

}
