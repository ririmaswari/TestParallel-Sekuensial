package com.juaracoding.ecom.providers;

import com.juaracoding.ecom.utils.ExcelUtils;
import org.testng.annotations.DataProvider;

import java.util.ArrayList;
import java.util.List;

public class DataTestProvider {
    @DataProvider(name = "loginDataProvider")
    public Object[][] loginDataProvider() {
        String excelPath = "src/test/resources/DataTestProvider.xlsx";
        ExcelUtils excel = new ExcelUtils(excelPath, "Sheet1");

        int rowCount = excel.getRowCount();
        int colCount = excel.getColCount();

        List<Object[]> dataList = new ArrayList<>();

        for (int i = 1; i < rowCount; i++) {
            boolean isRowEmpty = true;
            Object[] rowData = new Object[colCount];

            for (int j = 0; j < colCount; j++) {
                String cellValue = excel.getCellData(i, j);
                rowData[j] = cellValue;

                if (!cellValue.isEmpty()) {
                    isRowEmpty = false;
                }
            }

            if (!isRowEmpty) {
                dataList.add(rowData);
            } else {
                System.out.println("Skipping empty row: " + i);
            }
        }

        return dataList.toArray(new Object[0][]);
    }
}
