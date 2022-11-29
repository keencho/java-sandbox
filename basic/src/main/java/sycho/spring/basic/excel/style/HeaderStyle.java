package sycho.spring.basic.excel.style;

import com.keencho.lib.spring.excel.style.KcExcelCellStyleDefaultConfigurer;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;

public class HeaderStyle extends KcExcelCellStyleDefaultConfigurer {

    @Override
    public short fillForegroundColor() {
        return IndexedColors.GREY_50_PERCENT.getIndex();
    }

    @Override
    public FillPatternType fillPatternType() {
        return FillPatternType.SOLID_FOREGROUND;
    }

    @Override
    public Font font(Font font) {
        font.setBold(true);
        font.setColor(IndexedColors.WHITE.getIndex());

        return font;
    }

    @Override
    public BorderStyle borderStyle() {
        return BorderStyle.MEDIUM;
    }

    @Override
    public short borderColor() {
        return IndexedColors.YELLOW.getIndex();
    }
}
