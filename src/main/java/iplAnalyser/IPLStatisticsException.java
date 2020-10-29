package iplAnalyser;

@SuppressWarnings("serial")
public class IPLStatisticsException extends Exception  {
	
	public enum ExceptionType {
		NO_FILE, INCORRECT_FILE, UNABLE_TO_PARSE, NO_STATISTICS_DATA
	}

	public ExceptionType type;

	public IPLStatisticsException(String message, ExceptionType type) {
		super(message);
		this.type = type;
	}

}
