package com.mymart.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "faqs")
public class Faq {
	
	 @Id
	 @GeneratedValue(strategy=GenerationType.IDENTITY)
	 
	 private Long id;
	 private String question;
	 private String answer;
	
	 public Faq(Long id, String question, String answer) {
		super();
		this.id = id;
		this.question = question;
		this.answer = answer;
	}
	public Faq() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Faq [id=" + id + ", question=" + question + ", answer=" + answer + "]";
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public Faq orElse(Object object) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
