package util.serialize;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * CSV 工具类
 *
 * @author zhangqian
 */
public class CSVUtil {
    /**
     * 数据生成csv文件
     */
    public static void generateCsvFile(List<LinkedHashMap<String, Object>> list) throws IOException {
        String fileName = "abc.csv";

        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new FileOutputStream(fileName),
                StandardCharsets.UTF_8);
        //解决excel csv UTF-8中文乱码，csv文件前必须要加个BOM头，Excel才能正确打开文件
        byte[] bom = {(byte) 0xEF, (byte) 0xBB, (byte) 0xBF};
        outputStreamWriter.write(new String(bom));
        CSVWriter csvWriter = new CSVWriter(outputStreamWriter);
        //获取字段
        LinkedHashMap<String, Object> map = list.get(0);
        List<String> keyList = new ArrayList<>(map.size());
        map.forEach((k, v) -> keyList.add(k));

        csvWriter.writeNext(keyList.toArray(new String[0]));

        //输出对象
        for (LinkedHashMap<String, Object> stringObjectLinkedHashMap : list) {
            List<String> valueList = new ArrayList<>();
            stringObjectLinkedHashMap.forEach((k, v) -> valueList.add(String.valueOf(v == null ? "" : v)));

            csvWriter.writeNext(valueList.toArray(new String[0]));
        }
        csvWriter.close();
        outputStreamWriter.close();
    }

    /**
     * 数据生成csv文件
     */
    public static List<LinkedHashMap<String, Object>> formatDate() throws IOException, CsvValidationException {
        FileReader fileReader = new FileReader("abc.csv");

        List<LinkedHashMap<String, Object>> dataList = new ArrayList<>();
        CSVReader csvReader = new CSVReader(fileReader);
        try (CSVReader reader = csvReader) {
            // 读取 CSV 文件的第一行，将其作为字段名
            String[] headers = reader.readNext();
            if (headers == null || headers.length == 0) {
                throw new IOException("CSV 文件为空或格式错误");
            }

            String[] values;
            while ((values = reader.readNext()) != null) {
                LinkedHashMap<String, Object> dataMap = new LinkedHashMap<>();
                for (int i = 0; i < headers.length; i++) {
                    String value = (i < values.length) ? values[i] : "";
                    dataMap.put(headers[i], value);
                }
                dataList.add(dataMap);
            }
        }
        return dataList;
    }


    public static void main(String[] args) throws IOException, CsvValidationException {
        List<LinkedHashMap<String, Object>> list = new ArrayList<>();

        LinkedHashMap<String, Object> map1 = new LinkedHashMap<>();
        map1.put("name", "张三");
        map1.put("age", 18);

        LinkedHashMap<String, Object> map2 = new LinkedHashMap<>();
        map2.put("name", "李四");
        map2.put("age", 20);
        list.add(map1);
        list.add(map2);

        // 序列化
        generateCsvFile(list);

        // 反序列化
        List<LinkedHashMap<String, Object>> linkedHashMaps = formatDate();
        System.out.println(linkedHashMaps);
    }
}