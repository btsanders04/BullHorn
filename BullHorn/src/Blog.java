

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		String qString= "SELECT p FROM Post p ORDER BY p.postDate desc";
		String blogs="";
		TypedQuery<model.Post> q = em.createQuery(qString, model.Post.class);
		List<model.Post> posts = q.getResultList();
		for(model.Post p: posts){
			
			/*reviews+=" <div class=\"panel panel-primary\"><div class=\"panel-heading\">"+result2.getString("user_name")+"</div>"+
				     " <div class=\"panel-body\"><p > Date: "+result.getDate("REVIEW_DATE")
							+"</p><p>  Rating: "+result.getString("RATING")+"</p><p>"
				     +result.getString("REVIEW_DES")+"</p></div></div>";
		*/  blogs+="<div class=\"container\">";
			blogs+="<div class = \"panel panel-primary\"><div class=\"panel-heading\">" + p.getUserprofile().getUserName()+"</div>";
			blogs+= " <div class=\"panel-body\"><p > Date: "+  new SimpleDateFormat("MM/dd/yyyy").format(p.getPostDate()) +" </p>";
			blogs+="<p>" + p.getDescription() +"</p></div></div></div>";
			
		}
		request.setAttribute("blogs",blogs);
		getServletContext().getRequestDispatcher("/Blogs.jsp").forward(request,
				response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
