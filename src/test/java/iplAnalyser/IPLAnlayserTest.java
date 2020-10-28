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
}
