import java.util.*;
import java.io.*;

public class TravelingSalesman {
	public static void main (String[] args) {
		System.out.println(pathGeneration(1));
	}

	public static int[][] importFromFile(String file) {
		int cities = 0;
		int[][] distances = new int[0][0];
		ArrayList<String> citiesList = new ArrayList<String>();
		//parse each line to cities to cities to value
		try {
			File f = new File(file);
			Scanner in = new Scanner(f);
			while (in.hasNextLine()) {

				Scanner line = new Scanner(in.nextLine());

				for (int i = 0; line.hasNext(); i++) {

					String word = line.next();
					// creates a list of cities with corresponding indexes and counts number of cities
					if ((i==0||i==2) && !citiesList.contains(word)) {
						citiesList.add(word);
						cities++;
					}
				}
				line.close();
			}
			in.close();
			Scanner inn = new Scanner(f);

			// assigning distances
			distances = new int[cities][cities];
			int index1 = 0;
			int index2 = 0;
			while (inn.hasNextLine()) {
				Scanner line = new Scanner(inn.nextLine());
				for (int i = 0; line.hasNext(); i++) {
					String word = line.next();
					if (i==0) index1 = citiesList.indexOf(word);
					if (i==2) index2 = citiesList.indexOf(word);
					if (i==4) {
						distances[index1][index2] = Integer.parseInt(word);
						distances[index2][index1] = Integer.parseInt(word);
					}
				}
			}
			inn.close();
		} catch (FileNotFoundException e) {
			// e.printStackTrace();
			System.out.println(file + " not found");
		}
		// int[][] distances = new int[cities][cities];
		// System.out.println(citiesList);
		return distances;
	}

	private ArrayList<Integer> availableCities (ArrayList<Integer> citiesList, ArrayList<Integer> path) {
		for (Integer city : path) {
			citiesList.remove(citiesList.get(city));
		}

		return citiesList;
	}

	public static ArrayList<ArrayList<Integer>> pathGeneration (int cities) {
		//generates how many possible paths there are
		int permutations = 1;
		for (int counter = cities; counter >= 1; counter--) cities *= permutations;
		//generates arrays

		ArrayList<ArrayList<Integer>> paths = new ArrayList<ArrayList<Integer>>();
		//generates startblock and available cities
		ArrayList<ArrayList<Integer>> previousCalc = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> citiesList = new ArrayList<Integer>();
		for (int city = 0; city < cities; city++) {
			//adding paths
			ArrayList<Integer> path = new ArrayList<Integer>();
			path.add(city);
			previousCalc.add(path);
			//adding list of cities
			citiesList.add(city);
		}

		if (cities == 1) {
			return previousCalc;
		}

		for (int run = 1; run < cities; run++) {
			
		}

		return paths;
	}

	public static int leastDistancePerPath (int[][] paths) {
		int distance = (int)Double.POSITIVE_INFINITY;


		return distance;
	}
}
