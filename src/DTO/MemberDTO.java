package DTO;


public class MemberDTO {
	//커밋
	private String id;
	private int pwd;
	private String name;
	private String tel;
	private int region_code;
	
	public MemberDTO() {};

	public MemberDTO(String id) {
		this.id = id;
	}

	public MemberDTO (String id, int pwd) {
		this.id = id;
		this.pwd = pwd;
	}

	public MemberDTO(String id, int pwd, String name, String tel, int region_code) {
		super();
		this.id = id;
		this.pwd = pwd;
		this.name = name;
		this.tel = tel;
		this.region_code = region_code;
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
