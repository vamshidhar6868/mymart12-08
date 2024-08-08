package com.mymart.model;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.NotEmpty;

public class PopupDto 
{
	 private MultipartFile popupimage;
	 @NotEmpty(message = "popup data field is Required")
	 private String popupdata;
	 @NotEmpty(message = "OfferDetails field is Required")
	 private String popupofferdata;
	 @NotEmpty(message = "ScrollData field is Required")
	 private String popupmarqueedata;
	public MultipartFile getPopupimage() {
		return popupimage;
	}
	public void setPopupimage(MultipartFile popupimage) {
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
	 
}
