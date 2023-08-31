package dto;
public class MenuDTO {

	private String menu_name;
	private String menu_price;
	private int res_id;
	
	public MenuDTO() {
		
	}
	
	public MenuDTO(String menu_name, String menu_price) {
		this.menu_name = menu_name;
		this.menu_price = menu_price;
	}
	
	public String getMenu_name() {
		return menu_name;
	}
	public void setMenu_name(String menu_name) {
		this.menu_name = menu_name;
	}
	public String getMenu_price() {
		return menu_price;
	}
	public void setMenu_price(String menu_price) {
		this.menu_price = menu_price;
	}


	public int getRes_id() {
		return res_id;
	}

	public void setRes_id(int res_id) {
		this.res_id = res_id;
	}

	@Override
	public String toString() {
		
		
		return menu_name + " " + menu_price;
	}
	
	
	
}
