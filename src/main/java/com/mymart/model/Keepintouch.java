package com.mymart.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Keepintouch")
public class Keepintouch {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	private String googleimage;
	private String linkedinimage;
	private String facebookimage;
	private String ximage;
	private String instargramiamge;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getGoogleimage() {
		return googleimage;
	}
	public void setGoogleimage(String googleimage) {
		this.googleimage = googleimage;
	}
	public String getLinkedinimage() {
		return linkedinimage;
	}
	public void setLinkedinimage(String linkedinimage) {
		this.linkedinimage = linkedinimage;
	}
	public String getFacebookimage() {
		return facebookimage;
	}
	public void setFacebookimage(String facebookimage) {
		this.facebookimage = facebookimage;
	}
	public String getXimage() {
		return ximage;
	}
	public void setXimage(String ximage) {
		this.ximage = ximage;
	}
	public String getInstargramiamge() {
		return instargramiamge;
	}
	public void setInstargramiamge(String instargramiamge) {
		this.instargramiamge = instargramiamge;
	}
	public Keepintouch(Long id, String googleimage, String linkedinimage, String facebookimage, String ximage,
			String instargramiamge) {
		super();
		this.id = id;
		this.googleimage = googleimage;
		this.linkedinimage = linkedinimage;
		this.facebookimage = facebookimage;
		this.ximage = ximage;
		this.instargramiamge = instargramiamge;
	}
	public Keepintouch() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Keepintouch [id=" + id + ", googleimage=" + googleimage + ", linkedinimage=" + linkedinimage
				+ ", facebookimage=" + facebookimage + ", ximage=" + ximage + ", instargramiamge=" + instargramiamge
				+ "]";
	}
	
	
}
	