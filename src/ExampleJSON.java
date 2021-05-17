
import java.util.Scanner;

public class ExampleJSON {

	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);
		HandlingJSON ha = new HandlingJSON();
		
		int r;
		String nome_file;
		String url;
		do {
			System.out.println("\nMENU':");
			System.out.println("1)Download a JSONObject using an API call");
			System.out.println("2)Load a JSONObject from a txt file");
			System.out.println("3)Save a JSONObject in a txt file");
			System.out.println("4)Download a JSONArray using an API call");
			System.out.println("5)Load a JSONArray from a txt file");
			System.out.println("6)Save a JSONArray in a txt file");
			System.out.println("0)Exit");
			
			r = in.nextInt();
			in.nextLine();
			switch(r) {
			case 1:
				System.out.println("API call on OpenWeather:");
				System.out.println("Current weather from London...");
				url = "http://api.openweathermap.org/data/2.5/weather?q=London,uk&APPID=02858051ffc502f32c0e4788c6bfff15"; 
				ha.APIcall(url, true);
				break;
			case 2:
				System.out.println("File name:");
				nome_file = in.nextLine();
				ha.loadFile(nome_file, true);
				break;
			case 3:
				System.out.println("File name:");
				nome_file = in.nextLine();
				ha.saveFile(nome_file, true);
				break;
			case 4:
				System.out.println("API call on this website: api.publicapis.org");
				System.out.println("Show categories of various websites...");
				url = "https://api.publicapis.org/categories"; 
				ha.APIcall(url, false);
				break;
			case 5:
				System.out.println("File name:");
				nome_file = in.nextLine();
				ha.loadFile(nome_file, false);
				break;
			case 6:
				System.out.println("File name:");
				nome_file = in.nextLine();
				ha.saveFile(nome_file, false);
				break;
			case 0:
				
				break;
			}
			
		}while(r>0);

	}

}
