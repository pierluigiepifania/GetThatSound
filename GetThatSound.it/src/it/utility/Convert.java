package it.utility;

public class Convert {
	public static String underscoreToSpace(String und) {
		StringBuilder sb = new StringBuilder();
		char[] temp= {'_'};
		for(int i = 0; i<und.length(); i++) {
			if(temp[0] == und.charAt(i)) {
				sb.append(" ");
			}
			else {
				sb.append((und.charAt(i)));
			}
		}
		return sb.toString();
	}
	
	public static String spaceToUnderscore(String space) {
		StringBuilder sb = new StringBuilder();
		char[] temp= {' '};
		for(int i = 0; i<space.length(); i++) {
			if(temp[0] == space.charAt(i)) {
				sb.append("_");
			}
			else {
				sb.append((space.charAt(i)));
			}
		}
		return sb.toString();
	}

}
