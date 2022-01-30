import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Main {

	public static void main(String[] args) {
		UndirectedGraph<String> graph=new UndirectedGraph<String>();
		UndirectedGraph<String> graph2=new UndirectedGraph<String>();
		try {
			File karatefile = new File("src\\karate_club_network.txt");
			File facebookfile = new File("src\\facebook_social_network.txt");
			FileReader fr = null;
			fr = new FileReader(karatefile);

			BufferedReader br = new BufferedReader(fr);
			String line;

			while ((line = br.readLine()) != null) {
				String[] splittext = line.split(" ");
				graph.addConnection(splittext[0], splittext[1]);

			}
			fr.close();
			fr = null;
			fr = new FileReader(facebookfile);

			br = new BufferedReader(fr);

			while ((line = br.readLine()) != null) {
				String[] splittext = line.split(" ");
				graph2.addConnection(splittext[0], splittext[1]);

			}
			fr.close();
			

		} catch (IOException e) {
			System.out.println(e);
		}
		graph.getCentralityMetrics();
		graph2.getCentralityMetrics();
		System.out.println("2019510026 Ibrahim_Serhan Desteli:");
		graph.findMaxMetrics(0);
		graph2.findMaxMetrics(1);
		

	}

}
