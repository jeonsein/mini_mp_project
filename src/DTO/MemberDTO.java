package DTO;
public class MemberDTO {
	//커밋
	private String id;
	private int pwd;
	private String name;
	private String tel;
	private int region_code;
	
	public MemberDTO() {
		// TODO Auto-generated constructor stub
	}
	
	public MemberDTO(String id) {
		this.id = id;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public int getPwd() {
		return pwd;
	}
	
	public void setPwd(int pwd) {
		this.pwd = pwd;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getTel() {
		return tel;
	}
	
	public void setTel(String tel) {
		this.tel = tel;
	}
			
	public int getRegion_code() {
		return region_code;
	}
	
	public void setRegion_code(int region_code) {
		this.region_code = region_code;
	}

	
} // end class
