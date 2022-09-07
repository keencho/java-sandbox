package sycho.spring.basic.excel.style;

import com.keencho.lib.spring.excel.style.KcExcelCellStyleDefaultConfigurer;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;

public class BodyStyle extends KcExcelCellStyleDefaultConfigurer {

    @Override
    public short fillForegroundColor() {
        return IndexedColors.YELLOW.getIndex();
    }

    @Override
    public FillPatternType fillPatternType() {
        return FillPatternType.SOLID_FOREGROUND;
    }

    @Override
    public Font font(Font font) {
        font.setBold(true);

        return font;
    }

    @Override
    public BorderStyle borderStyle() {
        return BorderStyle.THIN;
    }

    @Override
    public short borderColor() {
        return IndexedColors.RED.getIndex();
    }
}
