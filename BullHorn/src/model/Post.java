package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the POST database table.
 * 
 */
@Entity
@NamedQuery(name="Post.findAll", query="SELECT p FROM Post p")
public class Post implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="POST_ID")
	private long postId;

	private String description;

	@Temporal(TemporalType.DATE)
	@Column(name="POST_DATE")
	private Date postDate;

	@OneToOne
	@JoinColumn(name="RECIPIENT")
	private Userprofile recipient;

	//bi-directional many-to-one association to Userprofile
	@ManyToOne
	@JoinColumn(name="USER_ID")
	private Userprofile userprofile;

	public Post() {
	}

	public long getPostId() {
		return this.postId;
	}

	public void setPostId(long postId) {
		this.postId = postId;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getPostDate() {
		return this.postDate;
	}

	public void setPostDate(Date postDate) {
		this.postDate = postDate;
	}

	public Userprofile getRecipient() {
		return this.recipient;
	}

	public void setRecipient(Userprofile recipient) {
		this.recipient = recipient;
	}

	public Userprofile getUserprofile() {
		return this.userprofile;
	}

	public void setUserprofile(Userprofile userprofile) {
		this.userprofile = userprofile;
	}

}