import java.util.*;
import java.io.*;

public class TravelingSalesman {
	public static void main (String[] args) {

		int[][] dist = importFromFile(args[0]);
		ArrayList<ArrayList<Integer>> test = pathGeneration(dist[0].length);
		// System.out.println(test);
		//System.out.println(pathGeneration(4));
		System.out.println(leastDistancePerPath(test, dist));
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

	private static ArrayList<Integer> availableCities (ArrayList<Integer> citiesList, ArrayList<Integer> path) {
		for (int city : path) {
			citiesList.remove(citiesList.indexOf(city));
		}

		return citiesList;
	}

	public static ArrayList<ArrayList<Integer>> pathGeneration (int cities) {
		ArrayList<ArrayList<Integer>> paths = new ArrayList<ArrayList<Integer>>();

		//generates startblock and available cities
		ArrayList<ArrayList<Integer>> previousCalc = new ArrayList<ArrayList<Integer>>();

		// list of possible cities as nums
		ArrayList<Integer> citiesList = new ArrayList<Integer>();
		for (int city = 0; city < cities; city++) {
			//adding paths
			ArrayList<Integer> path = new ArrayList<Integer>();
			path.add(city);
			if (city < (cities / 2) + cities % 2) previousCalc.add(path);//If you flip the start and end digits, any permutation in between is the reversal of end and start digit. Since we calculated all permutations in one direction in the first half, calculating it backwards serves no purpose.
			//adding list of cities
			citiesList.add(city);
		}
		//doesn't run additional runs when not needed
		if (cities == 1) {
			return previousCalc;
		}
		//runs additional appending to paths
		for (int run = 1; run < cities; run++) {
			//System.out.println("Run: " + run);
			ArrayList<ArrayList<Integer>> currentCalc = new ArrayList<ArrayList<Integer>>();
			for (ArrayList<Integer> path : previousCalc) {
				ArrayList<Integer> citiesRemaining = availableCities(new ArrayList<Integer>(citiesList), path);
				//appends all allowed cities to the path
				for (int city : citiesRemaining) {
					ArrayList<Integer> newPath = new ArrayList<Integer>(path);
					newPath.add(city);
					currentCalc.add(newPath);
				}
			}
			previousCalc = currentCalc;
		}
		paths = previousCalc;

		return paths;
	}

	public static int leastDistancePerPath (ArrayList<ArrayList<Integer>> paths, int[][] distances) {
		int distance = (int)Double.POSITIVE_INFINITY;
		for (int i = 0; i < paths.size(); i++) {
			int permutationdistance = 0;
			//code to calculate distance of each permutation
			for(int city = 0; city < paths.get(i).size() - 1; city++){
				int currentCity = paths.get(i).get(city);
				int nextCity = paths.get(i).get(city + 1);
				permutationdistance += distances[currentCity][nextCity];
			}
			if (permutationdistance < distance){
				distance = permutationdistance;
			}
		}

		return distance;
	}
}
