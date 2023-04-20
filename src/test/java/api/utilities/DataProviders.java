package api.utilities;

import org.testng.annotations.DataProvider;

import java.io.IOException;
import java.util.Iterator;

public class DataProviders {

    @DataProvider(name="Data")
    public static String[][] getAllData() throws IOException {
        String path = System.getProperty("user.dir")+"/test-data/UserData.xlsx";
        ExcelUtility excel = new ExcelUtility(path);
        int rowNum = excel.getRowCount("Sheet1");
        int columnCount = excel.getCellCount("Sheet1",1);
        String apiData[][] = new String[rowNum][columnCount];
        for (int i = 1; i <= rowNum; i++) {
            for (int j = 0; j < columnCount; j++) {
                apiData[i-1][j] = excel.getCellData("Sheet1",i,j);
            }
        }
        return apiData;
    }

    @DataProvider(name="UserNames")
    public static String[] getUsernames() throws IOException {
        String path = System.getProperty("user.dir")+"/test-data/UserData.xlsx";
        ExcelUtility excel = new ExcelUtility(path);
        int rowNum = excel.getRowCount("Sheet1");
        String apiData[] = new String[rowNum];
        for (int i = 1; i<=rowNum; i++){
            apiData[i-1] = excel.getCellData("Sheet1",i,1);
        }
        return apiData;
    }
}
