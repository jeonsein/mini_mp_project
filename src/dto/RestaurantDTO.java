package dto;

import java.util.List;

public class RestaurantDTO {

	private String res_id;
	private String res_name;
	private String res_info;
	private String res_tel;
	private String location;
	private List<MenuDTO> menuList;
	private int wish_count;

	public RestaurantDTO() {
	}

	public RestaurantDTO(String res_name) {
		this.res_name = res_name;
	}
	
	public RestaurantDTO(String res_name, int wish_count) {
		this.res_name = res_name;
		this.wish_count = wish_count;
	}

	public RestaurantDTO(String res_id, String res_name, String res_info, String res_tel, String location,
			List<MenuDTO> menuList, int wish_count) {
		this.res_id = res_id;
		this.res_name = res_name;
		this.res_info = res_info;
		this.res_tel = res_tel;
		this.location = location;
		this.menuList = menuList;
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

	public void setRes_info(String res_info) {
		this.res_info = res_info;
	}

	public String getRes_tel() {
		return res_tel;
	}

	public void setRes_tel(String res_tel) {
		this.res_tel = res_tel;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public List<MenuDTO> getMenuList() {
		return menuList;
	}

	public void setMenuList(List<MenuDTO> menuList) {
		this.menuList = menuList;
	}

	public int getWish_count() {
		return wish_count;
	}

	public void setWish_count(int wish_count) {
		this.wish_count = wish_count;
	}

	@Override
	public String toString() {
		return res_name + " " + wish_count;
	}

}