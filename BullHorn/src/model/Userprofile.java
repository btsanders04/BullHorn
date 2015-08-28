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
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="USER_ID")
	private long userId;

	@Column(name="IMAGE_LINK")
	private String imageLink;

	@Temporal(TemporalType.DATE)
	@Column(name="JOIN_DATE")
	private Date joinDate;

	private String motto;

	@Column(name="USER_EMAIL")
	private String userEmail;

	@Column(name="USER_NAME")
	private String userName;

	@Column(name="USER_PASS")
	private String userPass;

	@Column(name="USER_ZIPCODE")
	private String userZipcode;

	//bi-directional many-to-one association to Post
	@OneToMany(mappedBy="userprofile")
	private List<Post> posts;

	public Userprofile() {
	}

	public long getUserId() {
		return this.userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getImageLink() {
		return this.imageLink;
	}

	public void setImageLink(String imageLink) {
		this.imageLink = imageLink;
	}

	public Date getJoinDate() {
		return this.joinDate;
	}

	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}

	public String getMotto() {
		return this.motto;
	}

	public void setMotto(String motto) {
		this.motto = motto;
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