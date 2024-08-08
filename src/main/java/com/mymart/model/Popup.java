package com.mymart.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "popup")
public class Popup 
{
	
	 @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private int id;
	    private String popupimage;
	    private String popupdata;
	    private String popupofferdata;
	    private String popupmarqueedata;
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getPopupimage() {
			return popupimage;
		}
		public void setPopupimage(String popupimage) {
			this.popupimage = popupimage;
		}
		public String getPopupdata() {
			return popupdata;
		}
		public void setPopupdata(String popupdata) {
			this.popupdata = popupdata;
		}
		public String getPopupofferdata() {
			return popupofferdata;
		}
		public void setPopupofferdata(String popupofferdata) {
			this.popupofferdata = popupofferdata;
		}
		public String getPopupmarqueedata() {
			return popupmarqueedata;
		}
		public void setPopupmarqueedata(String popupmarqueedata) {
			this.popupmarqueedata = popupmarqueedata;
		}
		public Popup(int id, String popupimage, String popupdata, String popupofferdata, String popupmarqueedata) {
			super();
			this.id = id;
			this.popupimage = popupimage;
			this.popupdata = popupdata;
			this.popupofferdata = popupofferdata;
			this.popupmarqueedata = popupmarqueedata;
		}
		public Popup() {
			super();
			// TODO Auto-generated constructor stub
		}
		@Override
		public String toString() {
			return "Popup [id=" + id + ", popupimage=" + popupimage + ", popupdata=" + popupdata + ", popupofferdata="
					+ popupofferdata + ", popupmarqueedata=" + popupmarqueedata + "]";
		}
	    

}
