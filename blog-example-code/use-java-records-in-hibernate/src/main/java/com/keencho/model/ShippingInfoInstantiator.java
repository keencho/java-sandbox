package com.keencho.model;

import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.metamodel.spi.EmbeddableInstantiator;
import org.hibernate.metamodel.spi.ValueAccess;

public class ShippingInfoInstantiator implements EmbeddableInstantiator {
    @Override
    public Object instantiate(ValueAccess valueAccess, SessionFactoryImplementor sessionFactory) {
        var name = valueAccess.getValue(0, String.class);
        var address = valueAccess.getValue(1, String.class);
        var street = valueAccess.getValue(2, String.class);

        return new ShippingInfo(name, address, street);
    }

    @Override
    public boolean isInstance(Object object, SessionFactoryImplementor sessionFactory) {
        return object instanceof ShippingInfo;
    }

    @Override
    public boolean isSameClass(Object object, SessionFactoryImplementor sessionFactory) {
        return object.getClass().equals(ShippingInfo.class);
    }
}
