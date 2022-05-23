package com.javaex.ex02;

import java.util.List;

public class AuthorApp {

	public static void main(String[] args) {
		
		AuthorDao authorDao = new AuthorDao();
		
		
		authorDao.authorInsert("김문열", "경북 영양");
		authorDao.authorInsert("박경리", "경상남도 통영");
		authorDao.authorInsert("유시민", "17대 국회의원");
		authorDao.authorInsert("기안84", "기안동에서 산 84년생");
		authorDao.authorInsert("강풀", "온라인 만화가 1세대");
		authorDao.authorInsert("김영하", "알쓸신잡");
		
		
		/*
		int dCount = authorDao.authorDelete(4);
		System.out.println("삭제건수:" + dCount );
		*/
		
//		int uCount = authorDao.authorUpdate(1, "이문열", "삼국지 작가");
//		System.out.println("수정건수:" + uCount );
		
//		List<AuthorVo> authorList= authorDao.authorSelect();
//		
//		System.out.println(authorList.get(0).getAuthorName());
//		for(int i=0; i<authorList.size(); i++) {
//			
//			AuthorVo authorVo = authorList.get(i);
//			System.out.println(authorVo.getAuthorId() + ", " + authorVo.getAuthorName() + ", " + authorVo.getAuthorDesc());
//			
//		}
	}

}