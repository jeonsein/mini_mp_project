package img;

public class Loading implements Runnable {
	
	public static void printSpace() {
        for (int i = 0; i < 60; i++) {
            System.out.println("");
        }
    }

    @Override
    public void run() {
    	printSpace();
        System.out.println();
        String str = "Loading.....";
        for(int i = 0; i < str.length(); i++){
            System.out.println(str.charAt(i));
            try{
                Thread.sleep(100);
            }catch (Exception e){

            }
        }
        System.out.println();

        for (int i = 0; i<30; ++i){
            System.out.print("▒");
            System.out.print("▒");
            System.out.print("▒");

            try{
                Thread.sleep(100-10*i);
            }catch (Exception e){

            }
        }

    }
}
