import java.util.Vector;

import menu_dao.MenuDAO;
import restaurant_dao.RestaurantDAO;
import sevice.RestaurantSevice;

public class Mangoplate {

	public static void main(String[] args) {
		
//		Vector<Object> v = null;
//		
//		
//		RestaurantDAO rDao = new RestaurantDAO();
//		MenuDAO mDao = new MenuDAO();
//		
//		v = rDao.wishBestSelect();
//		
//		for(int i = 0, count=1; i< v.size(); i++,count++) {
//			System.out.println(count + " " + v.get(i));
//		}
////		
//		v = mDao.selectWishMenu(1);
//		
//		for(int i = 0, count=1; i< v.size(); i++,count++) {
//			System.out.println(count + " " + v.get(i));
//		}
		
		RestaurantSevice rs = new RestaurantSevice();
		
		rs.restaurantList();
		
	}

}