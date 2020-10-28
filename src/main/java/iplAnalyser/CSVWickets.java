package iplAnalyser;

import com.opencsv.bean.CsvBindByName;

public class CSVWickets {
	
	@CsvBindByName(column = "Player")
	public String playerName;
	
	@CsvBindByName(column = "Mat")
	public int matches;
	
	@CsvBindByName(column = "Inns")
	public int innings;
	
	@CsvBindByName(column = "Ov")
	public double over;	
	
	@CsvBindByName(column = "Runs")
	public int runs;
	
	@CsvBindByName(column = "Wkts")
	public int wickets;
	
	@CsvBindByName(column = "Avg")
	public float avg;
	
	@CsvBindByName(column = "Econ")
	public float economy;
	
	@CsvBindByName(column = "SR")
	public float strikeRate;
	
	@CsvBindByName(column = "4w")
	public int fourWkts;
	
	@CsvBindByName(column = "5w")
	public int fiveWkts;

}
