package com.javaex.ex02;

public class AuthorVo {

	//필드
	private int authorId;
	private String authorName;
	private String authorDesc;
	//생성자
	public AuthorVo() {}
	
	public AuthorVo(int authorId, String authorName, String authorDesc) {
		super();
		this.authorId = authorId;
		this.authorName = authorName;
		this.authorDesc = authorDesc;
	}
	
	public AuthorVo(int authorId) {
		this.authorId = authorId;
	}

	//메소드 gs
	public int getAuthorId() {
		return authorId;
	}

	public void setAuthorID(int authorID) {
		this.authorId = authorID;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public String getAuthorDesc() {
		return authorDesc;
	}

	public void setAuthorDesc(String authorDesc) {
		this.authorDesc = authorDesc;
	}

	
	//메소드 일반
	@Override
	public String toString() {
		return "AuthorVo [authorID=" + authorId + ", authorName=" + authorName + ", authorDesc=" + authorDesc + "]";
	}
	

}
