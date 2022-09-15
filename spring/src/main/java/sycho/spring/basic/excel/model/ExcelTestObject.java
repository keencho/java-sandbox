package sycho.spring.basic.excel.model;

import com.keencho.lib.spring.excel.annotation.KcExcelColumn;
import com.keencho.lib.spring.excel.annotation.KcExcelDocument;
import lombok.Data;
import sycho.spring.basic.excel.resolver.ExcelPhoneNumberResolver;
import sycho.spring.basic.excel.style.BodyStyle;
import sycho.spring.basic.excel.style.HeaderStyle;

@Data
@KcExcelDocument
public class ExcelTestObject {

    @KcExcelColumn(headerName = "이름", headerStyleConfigurer = HeaderStyle.class)
    private String name;

    @KcExcelColumn(headerName = "나이", width = 50, headerStyleConfigurer = HeaderStyle.class, bodyStyleConfigurer = BodyStyle.class)
    private String age;

    @KcExcelColumn(headerName = "전화번호", resolver = ExcelPhoneNumberResolver.class, headerStyleConfigurer = HeaderStyle.class)
    private String phoneNumber;
}
