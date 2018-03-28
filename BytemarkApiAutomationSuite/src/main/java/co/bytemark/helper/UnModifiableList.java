package co.bytemark.helper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UnModifiableList {

	public static void main(String[] args) {
		//List llist = new ArrayList();
		//Collections.unmodifiableList(llist);
		//llist.add("20");
		List mm1 = mm1();
		mm1.add("30");
	}
	
	public  static List mm1() {
		ArrayList list = new ArrayList();
		list.add("velan");
		
		return Collections.unmodifiableList(list);
		
	}
}
