package util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

public class StringUtil {
	
	public static boolean isNullOrBlank(String value){
		if(value == null || value.trim().isEmpty()){
			return true;
		}
		return false;
	}
	
	public static boolean isNullOrBlank(Integer value){
		if(value == null || value == 0){
			return true;
		}
		return false;
	}
	
	public static boolean isNullOrBlank(Date value){
		if(value == null){
			return true;
		}
		return false;
	}
	
	public static String logToString(String name, String value){
		StringBuilder builder = new StringBuilder();
		if (!StringUtil.isNullOrBlank(value)) {
			builder.append(name);
			builder.append(" : ");
			builder.append(value);
			builder.append(", ");
		}
		return builder.toString();
	}
	
	public static String logToString(String name, Integer value){
		StringBuilder builder = new StringBuilder();
		if (!StringUtil.isNullOrBlank(value)) {
			builder.append(name);
			builder.append(" : ");
			builder.append(value);
			builder.append(", ");
		}
		return builder.toString();
	}
	public static String logToString(String name, Date startDate, Date endDate){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		StringBuilder builder = new StringBuilder();
		if(!StringUtil.isNullOrBlank(startDate) || !StringUtil.isNullOrBlank(endDate)){
			builder.append(name);
			builder.append(" : ");
			builder.append(sdf.format(startDate));
			builder.append("~");
			builder.append(sdf.format(endDate));
			builder.append(", ");
		}
		return builder.toString();
	}
	public static String logToString(String name, Date value){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		StringBuilder builder = new StringBuilder();
		if(!StringUtil.isNullOrBlank(value)){
			builder.append(name);
			builder.append(sdf.format(value));
			builder.append(", ");
		}
		return builder.toString();
	}
	public static String sliceString(String value, int length){
		if(value.length() > length){
			return value.substring(0, length) + "...";
		}else{
			return value;	
		}
	}
	
	public static Date convertDateLongToDate(String longDate){
		return new Date(Long.parseLong(longDate));
	}
	
	public static String XSSReplace(String text){
		text = text.replaceAll("<", "&lt;").replaceAll(">", "&gt;");
		text = text.replaceAll("\\(", "&#40;").replaceAll("\\)", "&#41;");
		text = text.replaceAll("'", "&#39;");
		text = text.replaceAll("\"", "&#34;");
		text = text.replaceAll("eval\\((.*)\\)", "");
		text = text.replaceAll("[\\\"\\\'][\\s]*javascript:(.*)[\\\"\\\']", "\"\"");
		return text;
	}
	
	public static String XSSReplaceEscape(String text){
		text = text.replaceAll("&lt;", "<").replaceAll("&gt;", ">").replaceAll("&#34;", "\"");
		return text;
	}
	
	public static String stripXSS(String value) {
		String cleanValue = null;
		if (value != null) {
			cleanValue = XSSReplace(value);

			// Avoid null characters
			cleanValue = cleanValue.replaceAll("\0", "");
			
			// Avoid anything between script tags
			Pattern scriptPattern = Pattern.compile("<script>(.*?)</script>", Pattern.CASE_INSENSITIVE);
			cleanValue = scriptPattern.matcher(cleanValue).replaceAll("");
	 
			// Avoid anything in a src='...' type of expression
			//scriptPattern = Pattern.compile("src[\r\n]*=[\r\n]*\\\'(.*?)\\\'", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
			//cleanValue = scriptPattern.matcher(cleanValue).replaceAll("");

			//scriptPattern = Pattern.compile("src[\r\n]*=[\r\n]*\\\"(.*?)\\\"", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
			//cleanValue = scriptPattern.matcher(cleanValue).replaceAll("");
			
			// Remove any lonesome </script> tag
			scriptPattern = Pattern.compile("</script>", Pattern.CASE_INSENSITIVE);
			cleanValue = scriptPattern.matcher(cleanValue).replaceAll("");

			// Remove any lonesome <script ...> tag
			scriptPattern = Pattern.compile("<script(.*?)>", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
			cleanValue = scriptPattern.matcher(cleanValue).replaceAll("");

			// Avoid eval(...) expressions
			scriptPattern = Pattern.compile("eval\\((.*?)\\)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
			cleanValue = scriptPattern.matcher(cleanValue).replaceAll("");
			
			// Avoid expression(...) expressions
			scriptPattern = Pattern.compile("expression\\((.*?)\\)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
			cleanValue = scriptPattern.matcher(cleanValue).replaceAll("");
			
			// Avoid javascript:... expressions
			scriptPattern = Pattern.compile("javascript:", Pattern.CASE_INSENSITIVE);
			cleanValue = scriptPattern.matcher(cleanValue).replaceAll("");
			
			// Avoid vbscript:... expressions
			scriptPattern = Pattern.compile("vbscript:", Pattern.CASE_INSENSITIVE);
			cleanValue = scriptPattern.matcher(cleanValue).replaceAll("");
			
			// Avoid onload= expressions
			scriptPattern = Pattern.compile("onload(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
			cleanValue = scriptPattern.matcher(cleanValue).replaceAll("");
		}
		return cleanValue;
	}

}
