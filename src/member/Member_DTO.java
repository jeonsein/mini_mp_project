package member;

public class Member_DTO {

	private String id;
	private int pwd;
	private String tel;
	private String name;
	private int region_name;

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

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getRegion_name() {
		return region_name;
	}

	public void setRegion_name(int region_name) {
		this.region_name = region_name;
	}

}
