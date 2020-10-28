package iplAnalyser;

import com.opencsv.bean.CsvBindByName;

public class CSVRuns {
	
	@CsvBindByName(column = "Player")
	public String playerName;
	
	@CsvBindByName(column = "Mat")
	public int matches;
	
	@CsvBindByName(column = "Inns")
	public int innings;
	
	@CsvBindByName(column = "NO")
	public int notOut;	
	
	@CsvBindByName(column = "Runs")
	public int runs;
	
	@CsvBindByName(column = "Avg")
	public double avg;
	
	@CsvBindByName(column = "BF")
	public int bF;
	
	@CsvBindByName(column = "SR")
	public float strikeRate;
	
	@CsvBindByName(column = "100")
	public int noOfHundreds;
	
	@CsvBindByName(column = "50")
	public int noOfFifties;
	
	@CsvBindByName(column = "4s")
	public int noOfFours;
	
	@CsvBindByName(column = "6s")
	public int noOfSixes;

}
