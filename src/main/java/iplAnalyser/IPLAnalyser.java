package iplAnalyser;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import census.CSVBuilderException;
import census.CSVBuilderFactory;
import census.ICSVBuilder;

public class IPLAnalyser {
	
	List<CSVRuns> csvRunsList = null;
	List<CSVWickets> csvWktsList = null;

	public int loadDataOfRuns(String CSVFile) throws IPLStatisticsException {
		try {
			Reader reader = Files.newBufferedReader(Paths.get(CSVFile));
			ICSVBuilder<CSVRuns> csvBuilder = CSVBuilderFactory.createCSVBuilder();
			csvRunsList = csvBuilder.getCSVFileList(reader, CSVRuns.class);
			return csvRunsList.size();
		} catch (CSVBuilderException exception) {
			throw new IPLStatisticsException(exception.getMessage(), IPLStatisticsException.ExceptionType.UNABLE_TO_PARSE);
		} catch (IOException exception) {
			throw new IPLStatisticsException(exception.getMessage(), IPLStatisticsException.ExceptionType.INCORRECT_FILE);
		}

	}

	public int loadDataOfWickets(String CSVFile) throws IPLStatisticsException {
		try {
			Reader reader = Files.newBufferedReader(Paths.get(CSVFile));
			ICSVBuilder<CSVWickets> csvBuilder = CSVBuilderFactory.createCSVBuilder();
			csvWktsList = csvBuilder.getCSVFileList(reader, CSVWickets.class);
			return csvWktsList.size();
		} catch (CSVBuilderException exception) {
			throw new IPLStatisticsException(exception.getMessage(), IPLStatisticsException.ExceptionType.UNABLE_TO_PARSE);
		} catch (IOException exception) {
			throw new IPLStatisticsException(exception.getMessage(), IPLStatisticsException.ExceptionType.INCORRECT_FILE);
		}
	}	

	/**
	 * Usecase1 : sorting data based on batting average
	 * 
	 * @return
	 */
	public String getAverageWiseSortedData() {
		Comparator<CSVRuns> iplCSVComparator = Comparator.comparing(entry -> entry.avg);
		this.sort(csvRunsList, iplCSVComparator);
		String sorted = new Gson().toJson(csvRunsList);
		return sorted;
	}
	
	/**
	 * Usecase2 : Determine top striking rate
	 * 
	 * @return
	 */
	public String getSRWiseSortedData() {
		Comparator<CSVRuns> iplCSVComparator = Comparator.comparing(entry -> entry.strikeRate);
		this.sort(csvRunsList, iplCSVComparator);
		String sorted = new Gson().toJson(csvRunsList);
		return sorted;
	}
	
	/**
	 * Usecase3 : maximum fours and sixes hitters
	 * 
	 * @return
	 */
	public String getSortedDataOnNoOfFoursAndSixes() {
		Comparator<CSVRuns> iplCSVComparator = Comparator
				.comparing(entry -> (entry.noOfFours * 4) + (entry.noOfSixes * 6));
		this.sort(csvRunsList, iplCSVComparator);
		String sorted = new Gson().toJson(csvRunsList);
		return sorted;
	}
	
	/**
	 * Usecase4 : Find Best strike rate with Fours and Sixes
	 * 
	 * @return
	 */
	public String getSortedDataOnStrikeRateOnSixAndFour() {
		double max = 0;
		double temp = 0;
		String playerName = "";
		double maxSR = 0;
		double tempSR = 0;
		for (int i = 0; i < csvRunsList.size(); i++) {
			temp = (csvRunsList.get(i).noOfFours + csvRunsList.get(i).noOfSixes);
			tempSR = temp / csvRunsList.get(i).bF;
			if (temp > max && tempSR > maxSR) {
				max = temp;
				maxSR = tempSR;
				playerName = csvRunsList.get(i).playerName;
			}
		}
		System.out.println(playerName);
		return playerName;
	}
	
	public String getSortedOnAverageAndStrikeRate() {
		Comparator<CSVRuns> iplCSVComparator = Comparator.comparing(entry -> entry.avg);
		this.sort(csvRunsList, iplCSVComparator.thenComparing(entry -> entry.strikeRate));
		String sorted = new Gson().toJson(csvRunsList);
		return sorted;
	}
	
	/**
	 * Usecase6 : Player with max runs and best average 
	 * 
	 * @return
	 */
	public String getSortedOnMaxRunsAndStrikeRate() {
		Comparator<CSVRuns> iplCSVComparator = Comparator.comparing(entry -> entry.runs);
		this.sort(csvRunsList, iplCSVComparator.thenComparing(entry -> entry.avg));
		String sorted = new Gson().toJson(csvRunsList);
		return sorted;
	}
	
	public String getSortedOnBowlingAvg() {
		Comparator<CSVWickets> iplCSVComparator = Comparator.comparing(entry -> entry.avg);
		this.sortForBowling(csvWktsList, iplCSVComparator);
		String sorted = new Gson().toJson(csvWktsList);
		return sorted;
	}
	
	/**
	 * Usecase8 : Finding Bowler with best striking rate in IPL2019
	 * 
	 * @return
	 */
	public String getSortedOnBowlingStrikeRate() {
		Comparator<CSVWickets> iplCSVComparator = Comparator.comparing(entry -> entry.strikeRate);
		this.sortForBowling(csvWktsList, iplCSVComparator);
		String sorted = new Gson().toJson(csvWktsList);
		return sorted;
		
	}
	
	/**
	 * Usecase9 :Sorting by Bowling Economy
	 * 
	 * @return
	 */
	public String getSortedOnBowlingEconomy() {
		Comparator<CSVWickets> iplCSVComparator = Comparator.comparing(entry -> entry.economy);
		this.sort(csvWktsList, iplCSVComparator.reversed());
		String sorted = new Gson().toJson(csvWktsList);
		return sorted;
	}
	
	/**
	 * Usecase9 :Sorting by Bowling StrikeRate by Hauls
	 * 
	 * @return
	 */
	
	public String[] getSortedOnStrikeRateAnd4wOr5w() {
		double tempSR = 0;
		TreeMap<Double, String> csvMap = new TreeMap<>();
		for(int i = 0; i < csvWktsList.size(); i++ ) {
			int temp = csvWktsList.get(i).fourWkts * 4 + csvWktsList.get(i).fiveWkts * 5;
			if(temp > 0) {
				tempSR = ((Math.ceil(csvWktsList.get(i).over)*6 + ((csvWktsList.get(i).over*10)%10)))/temp;
				csvMap.put(tempSR, csvWktsList.get(i).playerName);
			}
		}
		for(Map.Entry<Double, String> entry : csvMap.entrySet()) {
			System.out.println(entry.getKey()+" "+ entry.getValue());
		}
		return csvMap.values().toArray(new String[0]);
	}
	
	/**
	 * Usecase11 : Bowler with great averages and best striking rate
	 * 
	 * @return
	 */
	public String getSortedOnBowlingAvgAndStrikeRate() {
		Comparator<CSVWickets> iplCSVComparator = Comparator.comparing(entry -> entry.avg);
		this.sortForBowling(csvWktsList, iplCSVComparator.thenComparing(entry -> entry.strikeRate));
		String sorted = new Gson().toJson(csvWktsList);
		return sorted;
	}
	
	/**
	 * UC 12 : sorting by max wickets with best bowling average
	 * 
	 * @return
	 */
	public String getSortedJsonMaxWicketsWithBestBowlingAvg() {
		Comparator<CSVWickets> bowlingComparator = Comparator.comparing(entry -> -entry.wickets);
		this.sortForBowling(csvWktsList, bowlingComparator.thenComparing(entry -> entry.avg));
		String jsonSortedPlayers = new Gson().toJson(csvWktsList);
		return jsonSortedPlayers;
	}
	
	/**
	 * Usecase13 : Adding names of Players with Best Batting and bowling average to the list
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<String> getSortedOnBestBattingAndBowlingAverage() {
		List<CSVRuns> battingList = (ArrayList<CSVRuns>) new Gson().fromJson(this.getAverageWiseSortedData(),
				                                         new TypeToken<ArrayList<CSVRuns>>() {}.getType());
		List<CSVWickets> bowlingList = (ArrayList<CSVWickets>) new Gson().fromJson(this.getSortedOnBowlingAvg(),
				                                               new TypeToken<ArrayList<CSVWickets>>() {}.getType());
		return this.forAllRounder(battingList, bowlingList);
	}
	
	public List<String> getSortedformaxRunsAndWickets(){
		@SuppressWarnings("unchecked")
		List<CSVRuns> maxRunsList = (ArrayList<CSVRuns>) new Gson().fromJson(this.getAverageWiseSortedData(),
                                                         new TypeToken<ArrayList<CSVRuns>>() {}.getType());
        @SuppressWarnings("unchecked")
		List<CSVWickets> maxWktsList = (ArrayList<CSVWickets>) new Gson().fromJson(this.getSortedOnBowlingAvg(),
                                                               new TypeToken<ArrayList<CSVWickets>>() {}.getType());
        return this.forAllRounder(maxRunsList, maxWktsList); 
	}
	
	
	private List<String> forAllRounder(List<CSVRuns> runsList, List<CSVWickets> wicketsList) {
		List<String> playerList = new ArrayList<>();
		for (CSVRuns bat : runsList) {
			for (CSVWickets bowl : wicketsList) {
				if (bat.playerName.equals(bowl.playerName)) {
					playerList.add(bat.playerName);
				}
			}
		}
		return playerList;
	}
	
	/**
	 * Usecase15: find Player with maximum hundreds and best average
	 * 
	 * @return
	 */
	public List<CSVRuns> getSortedOnMaxHundredsAndBattingAverage() {
		csvRunsList.removeIf(entry -> entry.noOfHundreds == 0 );
		Comparator<CSVRuns> iplCSVComparator = Comparator.comparing(entry -> entry.noOfHundreds);
		this.sort(csvRunsList, iplCSVComparator);
		this.sort(csvRunsList, Comparator.comparing(entry -> entry.avg));
		return csvRunsList;
	}
	
	/**
	 * Usecase16 : Player with zero 100s but with best average
	 * 
	 * @return
	 */
	public List<CSVRuns> getSortedOnZeroCenturiesAndBestBattingAvg() {
		csvRunsList.removeIf(entry -> (entry.noOfHundreds + entry.noOfFifties) != 0);
		Comparator<CSVRuns> iplCSVComparator = Comparator.comparing(entry -> entry.avg);
		this.sort(csvRunsList, iplCSVComparator);
		return csvRunsList;
	}
	
	private void sortForBowling(List<CSVWickets> csvList, Comparator<CSVWickets> iplCSVComparator) {
		for (int i = 0; i < csvList.size(); i++) {
			for (int j = 0; j < csvList.size() - i - 1; j++) {
				CSVWickets player1 = csvList.get(j);
				CSVWickets player2 = csvList.get(j + 1);
				if (iplCSVComparator.compare(player1, player2) > 0 && (player1.wickets != 0 && player2.wickets != 0)) {
					csvList.set(j, player2);
					csvList.set(j + 1, player1);
				}
			}
		}
	}

	private <E> void sort(List<E> csvList, Comparator<E> iplCSVComparator) {
		for (int i = 0; i < csvList.size(); i++) {
			for (int j = 0; j < csvList.size() - i - 1; j++) {
				E player1 = csvList.get(j);
				E player2 = csvList.get(j + 1);
				if (iplCSVComparator.compare(player1, player2) < 0) {
					csvList.set(j, player2);
					csvList.set(j + 1, player1);
				}
			}
		}
	}

}
