package com.keencho.querydsl;

import com.querydsl.core.types.Expression;
import com.querydsl.core.types.ExpressionException;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Map;

public class KcRecordExpression<T extends Record> extends KcExpression<T> {

    private final Class<? extends T> type;

    public KcRecordExpression(Class<? extends T> type, Map<String, Expression<?>> bindings) {
        super(type, bindings);
        if (!type.isRecord()) {
            throw new RuntimeException("This expression is an expression for record. Use KcExpression to bind a regular class.");
        }

        this.type = type;
    }

    @Override
    public T newInstance(Object... a) {
        if (this.type.getDeclaredFields().length != a.length) {
            throw new RuntimeException("Because a record type must create an object as a constructor that accepts all variables as arguments, the number of declared fields and the number of parameters to bind must be the same.");
        }

        try {
            var arr = getBindings().keySet().toArray();
            var fields = new Field[a.length];
            for (var i = 0; i < fields.length; i ++) {
                fields[i] = this.type.getDeclaredField((String) arr[i]);
            }

            var matchedConstructor = Arrays
                    .stream(this.type.getConstructors())
                    .filter(constructor -> {
                        var parameterTypes = constructor.getParameterTypes();
                        for (var i = 0; i < a.length; i ++) {
                            var constructorType = parameterTypes[i];
                            var field = fields[i];
                            if (!constructorType.getTypeName().equals(field.getType().getTypeName())) {
                                return false;
                            }
                        }

                        return true;
                    }).toList();

            // 파라미터의 갯수와 타입이 동일한 생성자는 한개여야함.
            // class(String a, String b) class(String b, String a) 와 같이 순서만 바꾼 경우도 있겠지만 이 경우 제대로된 데이터가 바인딩 된다는 보장이 없으므로 그냥 생성자는 한개로 제한
            // 위와같은 경우 그냥 클래스 쓰자.
            if (matchedConstructor.size() != 1) {
                throw new RuntimeException("No or more than one constructor exists with the same number and type of parameters. It seems record is not suitable for this case.");
            }

            var constructor = matchedConstructor.get(0);
            return (T) constructor.newInstance(a);
        } catch (Exception e) {
            throw new ExpressionException(e.getMessage(), e);
        }
    }
}
