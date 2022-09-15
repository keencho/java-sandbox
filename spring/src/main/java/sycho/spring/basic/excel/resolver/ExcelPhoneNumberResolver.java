package sycho.spring.basic.excel.resolver;

import com.keencho.lib.spring.excel.resolver.KcExcelMaskingDefaultResolver;

import java.util.Arrays;
import java.util.regex.Pattern;

public class ExcelPhoneNumberResolver extends KcExcelMaskingDefaultResolver {

    private final String regex = "(\\d{2,3})-?(\\d{3,4})-?(\\d{4})$";

    @Override
    public String apply(Object value) {
        if (value instanceof String phoneNumber) {
            var matcher = Pattern.compile(regex).matcher(phoneNumber);

            if (matcher.find()) {
                var target = matcher.group(2);
                var length = target.length();
                var c = new char[length];
                Arrays.fill(c, '*');

                return phoneNumber.replace(target, String.valueOf(c));
            }
            return phoneNumber;
        }

        return super.apply(value);
    }
}
