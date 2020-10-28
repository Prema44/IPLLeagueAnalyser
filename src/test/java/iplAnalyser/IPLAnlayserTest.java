package iplAnalyser;

import static org.junit.Assert.*;
import java.io.IOException;
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
	public void givenStatisticsoOfRuns_IfMatchNumberOfRecords_ShouldReturnTrue() {
		try {
			int count = iPLAnalyser.loadDataOfRuns(MOST_RUNS);
			assertEquals(101, count);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (CSVBuilderException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void givenStatisticsofWickets_IfMatchNumberOfRecords_ShouldReturnTrue() {
		try {
			int count = iPLAnalyser.loadDataOfWickets(MOST_WKTS);
			assertEquals(99, count);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (CSVBuilderException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void givenStatisticsOfRuns_WhenSortedOnAverage_ShouldReturnTrue() throws IOException, CSVBuilderException {
		iPLAnalyser.loadDataOfRuns(MOST_RUNS);
		String sortedCSVData = iPLAnalyser.getAverageWiseSortedData();
		CSVRuns[] iplCSV = new Gson().fromJson(sortedCSVData, CSVRuns[].class);
		assertEquals(83.2, iplCSV[0].avg, 0.0);
	}
	
	@Test
	public void givenStatisticsofRuns_WhenSortedOnStrikeRate_ShouldReturnTrue() throws IOException, CSVBuilderException {
		iPLAnalyser.loadDataOfRuns(MOST_RUNS);
		String sortedCSVData = iPLAnalyser.getSRWiseSortedData();
		CSVRuns[] iplCSV = new Gson().fromJson(sortedCSVData, CSVRuns[].class);
		assertEquals(333.33f, iplCSV[0].strikeRate, 0.0);
	}
	
	@Test
	public void givenStatisticsofRuns_WhenSortedOn4sand6s_ShouldReturnTophitter() throws IOException, CSVBuilderException {
		iPLAnalyser.loadDataOfRuns(MOST_RUNS);
		String sortedCSVData = iPLAnalyser.getSortedDataOnNoOfFoursAndSixes();
		CSVRuns[] iplCSV = new Gson().fromJson(sortedCSVData, CSVRuns[].class);
		assertEquals("Andre Russell", iplCSV[0].playerName);
	}
	
	@Test
	public void givenStatisticsOfRuns_WhenSortedOnStrikeRateWithfourAndSix_ShouldReturnTrue() throws IOException, CSVBuilderException {
		iPLAnalyser.loadDataOfRuns(MOST_RUNS);
		String name = iPLAnalyser.getSortedDataOnStrikeRateOnSixAndFour();
		assertEquals("Andre Russell", name);
	}
	
	@Test
	public void givenStatisticsOfRuns_WhenSortedOnAverageAndStrikeRate_ShouldReturnTrue() throws IOException, CSVBuilderException {
		iPLAnalyser.loadDataOfRuns(MOST_RUNS);
		String sortedCSVData = iPLAnalyser.getSortedOnAverageAndStrikeRate();
		CSVRuns[] iplCSV = new Gson().fromJson(sortedCSVData, CSVRuns[].class);
		assertEquals("MS Dhoni", iplCSV[0].playerName);
	}
	
	@Test
	public void givenStatisticsOfRuns_WhenSortedOnMaxRunsAndStrikeRate_ShouldReturnTrue() throws IOException, CSVBuilderException {
		iPLAnalyser.loadDataOfRuns(MOST_RUNS);
		String sortedCSVData = iPLAnalyser.getSortedOnMaxRunsAndStrikeRate();
		CSVRuns[] iplCSV = new Gson().fromJson(sortedCSVData, CSVRuns[].class);
		assertEquals("David Warner ", iplCSV[0].playerName);
	}
	
	@Test
	public void givenBowlingStatistics_WhenSortedOnBowlingAvg_ShouldReturnTrue() throws IOException, CSVBuilderException {
		iPLAnalyser.loadDataOfWickets(MOST_WKTS);
		String sortedCSVData = iPLAnalyser.getSortedOnBowlingAvg();
		CSVRuns[] iplCSV = new Gson().fromJson(sortedCSVData, CSVRuns[].class);
		assertEquals("Anukul Roy", iplCSV[0].playerName);
	}
	
	@Test
	public void givenBowlingStatistics_WhenSortedOnBowlingStrikeRate_ShouldReturnTrue() throws IOException, CSVBuilderException {
		iPLAnalyser.loadDataOfWickets(MOST_WKTS);
		String sortedCSVData = iPLAnalyser.getSortedOnBowlingStrikeRate();
		CSVWickets[] iplCSV = new Gson().fromJson(sortedCSVData, CSVWickets[].class);
		assertEquals("Alzarri Joseph", iplCSV[0].playerName);
	}
	
	@Test
	public void givenBowlingStatistics_WhenSortedOnBowlingEconomy_ShouldReturnTrue() throws IOException, CSVBuilderException {
		iPLAnalyser.loadDataOfWickets(MOST_WKTS);
		String sortedCSVData = iPLAnalyser.getSortedOnBowlingEconomy();
		CSVWickets[] iplCSV = new Gson().fromJson(sortedCSVData, CSVWickets[].class);
		assertEquals("Shivam Dube", iplCSV[0].playerName);
	}
}
