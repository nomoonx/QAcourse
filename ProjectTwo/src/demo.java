/**
 * 类demo.java的实现描述：TODO 类实现描述
 * 
 * @author noMoon Nov 9, 2014 3:10:37 AM
 */
public class demo {

    public static void main(String[] args) {
			System.out.println(greaterThanZero(0));
    }

    /*@ requires num>0;
     @ ensures \result == true;
    @*/
		public static boolean greaterThanZero(int num) {
	if (num > 0)
	    return false;
	else
	    return true;
    }

}
