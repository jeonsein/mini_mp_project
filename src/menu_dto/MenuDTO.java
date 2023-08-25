package menu_dto;
public class MenuDTO {

	private String menu_name;
	private String menu_price;
	private int region_code;
	
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
	public int getRegion_code() {
		return region_code;
	}
	public void setRegion_code(int region_code) {
		this.region_code = region_code;
	}

	@Override
	public String toString() {
		
		
		return menu_name + " / " + menu_price;
	}
	
	
	
}
