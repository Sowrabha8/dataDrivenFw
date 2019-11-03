package utility;

import java.io.File;
import java.lang.reflect.Method;
import java.util.Hashtable;

import org.testng.annotations.DataProvider;

import baseTest.BaseTest;

public class TestDataUtility extends BaseTest{
	
	//@DataProvider(name="getData")
	public Object[][] getData(Method methodName) {
		excelReader = new ExcelReader(System.getProperty("user.dir")+File.separator+"src/test/resources/testDataFolder/TestData.xlsx");
		String sheetName = methodName.getName();
		int rows = excelReader.getRowCount(sheetName);
		int cols = excelReader.getColumnCount(sheetName);

		Object[][] data = new Object[rows-1][1];
		Hashtable<String, String> table = null;

		for (int rowNum = 2; rowNum <= rows; rowNum++) {
			table = new Hashtable<String, String>();
			for (int colNum = 0; colNum < cols; colNum++) {
				
				table.put(excelReader.getCellData(sheetName, colNum, 1), excelReader.getCellData(sheetName, colNum, rowNum));
				data[rowNum-2][0]=table;
			}
			
		}
		
		return data;
	}

	public static boolean execution(String methodName) {
		// Reads excel's suite_name sheet and if a test is 
		// marked as Y, returns true
		// else returns false
		excelReader = new ExcelReader(System.getProperty("user.dir")+File.separator+"src/test/resources/testDataFolder/TestData.xlsx");
		String sheetName = "suite_name";
		String execStatus = null;
		int rows = excelReader.getRowCount(sheetName);
		for (int rowNum = 2; rowNum <= rows; rowNum++) {
				String testCaseName = excelReader.getCellData(sheetName, 0, rowNum);
				if (testCaseName.equalsIgnoreCase(methodName)) {
					execStatus = excelReader.getCellData(sheetName, 1, rowNum);
				}
		}
		if (execStatus.equalsIgnoreCase("Y")) {
			return true;
		}
		else {
			return false;
		}
	}
}
