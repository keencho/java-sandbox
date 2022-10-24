package sycho.spring.basic.excel.controller;

import com.keencho.lib.spring.excel.KcExcelDownloader;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import sycho.spring.basic.excel.model.ExcelTestObject;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/excel")
public class ExcelTestController {

    @GetMapping
    public String excelHtml() {
        return "excel";
    }

    @GetMapping("/export")
    public void export(HttpServletResponse response) throws IOException {
        var map = new LinkedHashMap<String, List<?>>();
        var data = this.listDummyData();
        map.put("테스트", data);

        var downloader = new KcExcelDownloader(map, false);
        downloader.export(response, "테스트 문서");
    }

    private List<ExcelTestObject> listDummyData() {
        return IntStream.range(0, 1000).mapToObj(i -> {
            var obj = new ExcelTestObject();
            obj.setName("이름" + i);
            obj.setAge(String.valueOf(i));
            obj.setPhoneNumber("01011112222");

            return obj;
        }).collect(Collectors.toList());
    }
}
