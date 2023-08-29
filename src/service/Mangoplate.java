package service;

import dao.MemberInformationDAO;

public class Mangoplate {

	public static void main(String[] args) {

		// 미니 프로젝트 메인문
		MemberInformationDAO dao=new MemberInformationDAO();
		
		System.out.println(">>> 망고플레이트에 오신걸 환영합니다!");
		MainService mainService = new MainService();
		mainService.start();
		
	}	

}