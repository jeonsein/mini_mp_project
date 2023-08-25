import java.util.Scanner;

import lombok.extern.log4j.Log4j2;
import member_dto.MemberDTO;


@Log4j2
public class TestMain {
	
	private MemberDTO loginedMember;

	public static void main(String[] args) {

		MemberDTO mdto = new MemberDTO("user1", "5555");
		
		mdto.getId();
		
		log.info("");
		
	}

}
