package model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the USERPROFILE database table.
 * 
 */
@Entity

@NamedQuery(name="Userprofile.findAll", query="SELECT u FROM Userprofile u")
public class Userprofile implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="UserSeq", sequenceName="UserSeq", initialValue=5, allocationSize=1)
	@GeneratedValue(generator = "UserSeq", strategy=GenerationType.SEQUENCE)
	@Column(name="USER_ID")
	private long userId;

	@Column(name="USER_EMAIL")
	private String userEmail;

	@Column(name="USER_NAME")
	private String userName;

	@Column(name="USER_PASS")
	private String userPass;

	@Column(name="USER_ZIPCODE")
	private String userZipcode;
	
	@Column(name="MOTTO")
	private String motto;
	
	@Column(name="JOIN_DATE")
	private Date joinDate;

	//bi-directional many-to-one association to Post
	@OneToMany(mappedBy="userprofile")
	private List<Post> posts;

	public Userprofile() {
	}
	
	

	public String getMotto() {
		return motto;
	}



	public void setMotto(String motto) {
		this.motto = motto;
	}



	public Date getJoinDate() {
		return joinDate;
	}



	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}



	public long getUserId() {
		return this.userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getUserEmail() {
		return this.userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPass() {
		return this.userPass;
	}

	public void setUserPass(String userPass) {
		this.userPass = userPass;
	}

	public String getUserZipcode() {
		return this.userZipcode;
	}

	public void setUserZipcode(String userZipcode) {
		this.userZipcode = userZipcode;
	}

	public List<Post> getPosts() {
		return this.posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	public Post addPost(Post post) {
		getPosts().add(post);
		post.setUserprofile(this);

		return post;
	}

	public Post removePost(Post post) {
		getPosts().remove(post);
		post.setUserprofile(null);

		return post;
	}

}