package experiments;

import java.util.Date;

public class demo {

	public static void main(String[] args) {
		
		//date printing with method changing concept
		Date date = new Date();
		//System.out.println(d.toString().replace(" ", "-").replace(":", "-"));
		
		
		String dateText = date.toString();
		String dateTextWithoutSpaces = dateText.replace(" ", "-");
		String dateTextWithoutSpacesAndSemicolon = dateTextWithoutSpaces.replace(":", "-");
		System.out.println(dateTextWithoutSpacesAndSemicolon);
		
	}

}
