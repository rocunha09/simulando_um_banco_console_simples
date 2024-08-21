package util;

public class Console {
	public static void clearTerminal(int i) {
		try {
			if(i > 0) {
				for(int j = 0; j < i; j++)
					System.out.println();
				return;
			}
			if(System.getProperty("os.name").toLowerCase().contains("windows"))
				new ProcessBuilder("cmd", "/c:", "cls").inheritIO().start().waitFor();
			else 
				new ProcessBuilder("clear").inheritIO().start().waitFor();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void sleep(int seconds) {
		try {
			if(seconds < 0)
				seconds = 1;
			Thread.sleep(seconds * 1000);
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
