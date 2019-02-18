package it.utility;

import java.io.File;

public class FileSystemManager {
	public static String makeDir(String artista, String album) {
		File file;
		artista = Convert.spaceToUnderscore(artista);
		album = Convert.spaceToUnderscore(album);
		try {

			file = new File("C:\\audio\\"+artista);
			System.out.println(file.toString());
			if (!file.exists()) {
				if (file.mkdir()) {
					file = new File("C:\\audio\\"+artista+"\\"+album);
					if(file.mkdir()) System.out.println("Directory is created!");
					else System.out.println("Failed to create directory!");
				}
				else System.out.println("Failed to create directory!");
			}
			else {
				file = new File("C:\\audio\\"+artista+"\\"+album);
				if (!file.exists()) {
					if (file.mkdir()) {
						System.out.println("Directory is created!");
					} else {
						System.out.println("Failed to create directory!");
					}
				}
				else System.out.println("The directory was already here!");
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			return "notok";
		}
		return file.getPath();

	}
}
