package restaurant_dto;
public class RestaurantDTO {

	private String res_id;
	private String res_name;
	private String res_info;
	private String res_tel;
	private int region_code;
	private int wish_count;
	
	public RestaurantDTO() {
		
	}
	public RestaurantDTO(String res_name) {
		this.res_name = res_name;
	}
	public RestaurantDTO(String res_name,int wish_count ) {
		this.res_name = res_name;
		this.wish_count = wish_count;
	}
	
	public RestaurantDTO(String res_name, String res_tel, String res_info, int region_code, int wish_count) {
		this.res_name = res_name;
		this.res_tel = res_tel;
		this.res_info = res_info;
		this.region_code = region_code;
		this.wish_count = wish_count;
	}
	
	
	public String getRes_id() {
		return res_id;
	}
	public void setRes_id(String res_id) {
		this.res_id = res_id;
	}
	public String getRes_name() {
		return res_name;
	}
	public void setRes_name(String res_name) {
		this.res_name = res_name;
	}
	public String getRes_info() {
		return res_info;
	}
	public int getWish_count() {
		return wish_count;
	}
	public void setWish_count(int wish_count) {
		this.wish_count = wish_count;
	}
	public void setRes_info(String res_info) {
		this.res_info = res_info;
	}
	public String getRes_tel() {
		return res_tel;
	}
	public void setRes_tel(String res_tel) {
		this.res_tel = res_tel;
	}
	public int getRegion_code() {
		return region_code;
	}
	public void setRegion_code(int region_code) {
		this.region_code = region_code;
	}
	@Override
	public String toString() {
		
		return res_name + " " + wish_count;
	}
	

	
}