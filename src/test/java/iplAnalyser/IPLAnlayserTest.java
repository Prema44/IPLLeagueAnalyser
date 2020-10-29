package iplAnalyser;

import static org.junit.Assert.*;
import java.io.IOException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import com.google.gson.Gson;
import census.CSVBuilderException;

public class IPLAnlayserTest {

	IPLAnalyser iPLAnalyser;
	private static final String MOST_RUNS = "H:\\bridgelab2\\IPL\\MostRuns.csv";
	private static final String MOST_WKTS = "H:\\bridgelab2\\IPL\\MostWkts.csv";

	@Before
	public void setUp() {
		iPLAnalyser = new IPLAnalyser();
	}

	@Test
	public void givenStatisticsoOfRuns_IfMatchNumberOfRecords_ShouldReturnTrue()  {
			int count;
			try {
				count = iPLAnalyser.loadDataOfRuns(MOST_RUNS);
				assertEquals(101, count);
			} catch (IPLStatisticsException e) {
				e.printStackTrace();
			}				
	}

	@Test
	public void givenStatisticsofWickets_IfMatchNumberOfRecords_ShouldReturnTrue() {
			int count;
			try {
				count = iPLAnalyser.loadDataOfWickets(MOST_WKTS);
				assertEquals(99, count);
			} catch (IPLStatisticsException e) {
				e.printStackTrace();
			}	
	}

	@Test
	public void givenStatisticsOfRuns_WhenSortedOnAverage_ShouldReturnTrue() throws IOException, CSVBuilderException, IPLStatisticsException {
		iPLAnalyser.loadDataOfRuns(MOST_RUNS);
		String sortedCSVData = iPLAnalyser.getAverageWiseSortedData();
		CSVRuns[] iplCSV = new Gson().fromJson(sortedCSVData, CSVRuns[].class);
		assertEquals("MS Dhoni", iplCSV[0].playerName);
	}
	
	@Test
	public void givenStatisticsofRuns_WhenSortedOnStrikeRate_ShouldReturnTrue() throws IOException, CSVBuilderException, IPLStatisticsException {
		iPLAnalyser.loadDataOfRuns(MOST_RUNS);
		String sortedCSVData = iPLAnalyser.getSRWiseSortedData();
		CSVRuns[] iplCSV = new Gson().fromJson(sortedCSVData, CSVRuns[].class);
		assertEquals("Ishant Sharma", iplCSV[0].playerName);
	}
	
	@Test
	public void givenStatisticsofRuns_WhenSortedOn4sand6s_ShouldReturnTophitter() throws IOException, CSVBuilderException, IPLStatisticsException {
		iPLAnalyser.loadDataOfRuns(MOST_RUNS);
		String sortedCSVData = iPLAnalyser.getSortedDataOnNoOfFoursAndSixes();
		CSVRuns[] iplCSV = new Gson().fromJson(sortedCSVData, CSVRuns[].class);
		assertEquals("Andre Russell", iplCSV[0].playerName);
	}
	
	@Test
	public void givenStatisticsOfRuns_WhenSortedOnStrikeRateWithfourAndSix_ShouldReturnTrue() throws IOException, CSVBuilderException, IPLStatisticsException {
		iPLAnalyser.loadDataOfRuns(MOST_RUNS);
		String name = iPLAnalyser.getSortedDataOnStrikeRateOnSixAndFour();
		assertEquals("Andre Russell", name);
	}
	
	@Test
	public void givenStatisticsOfRuns_WhenSortedOnAverageAndStrikeRate_ShouldReturnTrue() throws IOException, CSVBuilderException, IPLStatisticsException {
		iPLAnalyser.loadDataOfRuns(MOST_RUNS);
		String sortedCSVData = iPLAnalyser.getSortedOnAverageAndStrikeRate();
		CSVRuns[] iplCSV = new Gson().fromJson(sortedCSVData, CSVRuns[].class);
		assertEquals("MS Dhoni", iplCSV[0].playerName);
	}
	
	@Test
	public void givenStatisticsOfRuns_WhenSortedOnMaxRunsAndStrikeRate_ShouldReturnTrue() throws IOException, CSVBuilderException, IPLStatisticsException {
		iPLAnalyser.loadDataOfRuns(MOST_RUNS);
		String sortedCSVData = iPLAnalyser.getSortedOnMaxRunsAndStrikeRate();
		CSVRuns[] iplCSV = new Gson().fromJson(sortedCSVData, CSVRuns[].class);
		assertEquals("David Warner ", iplCSV[0].playerName);
	}
	
	@Test
	public void givenBowlingStatistics_WhenSortedOnBowlingAvg_ShouldReturnBest() throws IOException, CSVBuilderException, IPLStatisticsException {
		iPLAnalyser.loadDataOfWickets(MOST_WKTS);
		String sortedCSVData = iPLAnalyser.getSortedOnBowlingAvg();
		CSVRuns[] iplCSV = new Gson().fromJson(sortedCSVData, CSVRuns[].class);
		assertEquals("Anukul Roy", iplCSV[0].playerName);
	}
	
	@Test
	public void givenBowlingStatistics_WhenSortedOnBowlingStrikeRate_ShouldReturnTrue() throws IOException, CSVBuilderException, IPLStatisticsException {
		iPLAnalyser.loadDataOfWickets(MOST_WKTS);
		String sortedCSVData = iPLAnalyser.getSortedOnBowlingStrikeRate();
		CSVWickets[] iplCSV = new Gson().fromJson(sortedCSVData, CSVWickets[].class);
		assertEquals("Alzarri Joseph", iplCSV[0].playerName);	
	}
	
	@Test
	public void givenBowlingStatistics_WhenSortedOnBowlingEconomy_ShouldReturnTrue() throws IOException, CSVBuilderException, IPLStatisticsException {
		iPLAnalyser.loadDataOfWickets(MOST_WKTS);
		String sortedCSVData = iPLAnalyser.getSortedOnBowlingEconomy();
		CSVWickets[] iplCSV = new Gson().fromJson(sortedCSVData, CSVWickets[].class);
		assertEquals("Shivam Dube", iplCSV[0].playerName);
	}
	
	@Test
	public void givenBowlingStatistics_WhenSortedOnStrikeRateAnd4wOR5w_ShouldReturnTrue() throws IOException, CSVBuilderException, IPLStatisticsException {
		iPLAnalyser.loadDataOfWickets(MOST_WKTS);
		String[] sortedCSVData = iPLAnalyser.getSortedOnStrikeRateAnd4wOr5w();
		assertEquals("Alzarri Joseph", sortedCSVData[0]);
	}
	
	@Test
	public void givenBowlingStatistics_WhenSortedOnBowlingAvgAndStrikeRate_ShouldReturnTrue() throws IOException, CSVBuilderException, IPLStatisticsException {
		iPLAnalyser.loadDataOfWickets(MOST_WKTS);
		String sortedCSVData = iPLAnalyser.getSortedOnBowlingAvgAndStrikeRate();
		CSVWickets[] iplCSV = new Gson().fromJson(sortedCSVData, CSVWickets[].class);
		assertEquals("Anukul Roy", iplCSV[0].playerName);
	}
	
	@Test
	public void BowlingStatistics_WhenSortedOnMaxWicketsAndBowlingAvg_ShouldReturnTrue() throws IOException, CSVBuilderException, IPLStatisticsException {
		iPLAnalyser.loadDataOfWickets(MOST_WKTS);
		String sortedCSVData = iPLAnalyser.getSortedJsonMaxWicketsWithBestBowlingAvg();
		CSVWickets[] iplCSV = new Gson().fromJson(sortedCSVData, CSVWickets[].class);
		assertEquals("Imran Tahir", iplCSV[0].playerName);
	}
	
	@Test
	public void BowlingStatistics_WhenSortedOnBattingAndBowlingAvg_ShouldReturnTrue() throws IOException, CSVBuilderException, IPLStatisticsException {
		iPLAnalyser.loadDataOfWickets(MOST_WKTS);
		iPLAnalyser.loadDataOfRuns(MOST_RUNS);
		List<String> sortedCSVData = iPLAnalyser.getSortedOnBestBattingAndBowlingAverage();
		assertEquals("Andre Russell", sortedCSVData.get(0));
		assertEquals("Marcus Stoinis", sortedCSVData.get(1));
	}	
}
