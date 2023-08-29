package Service;
import MemberInformation.MemberInformationDAO;

public class Mangoplate {

	public static void main(String[] args) {
		//커밋
		// 미니 프로젝트 메인문
		MemberInformationDAO dao=new MemberInformationDAO();
		dao.ShowMember("mango");
		//dao.UpdatePwd("5555", "joon");
		
	}	

}