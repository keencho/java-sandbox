package com.keencho.hibernate.reactive.config;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ReactiveAdapterRegistry;
import org.springframework.core.ReactiveTypeDescriptor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class MutinyAdapter {

    @Autowired
    ReactiveAdapterRegistry registry;

    @PostConstruct
    public void registerAdapter() {
        System.out.println("----- register adapter -----");
        registry.registerReactiveType(
                ReactiveTypeDescriptor.singleOptionalValue(Uni.class, () -> Uni.createFrom().nothing()),
                uni -> ((Uni<?>)uni).convert().toPublisher(),
                publisher -> Uni.createFrom().publisher(publisher)
        );

        registry.registerReactiveType(
                ReactiveTypeDescriptor.multiValue(Multi.class, ()-> Multi.createFrom().empty()),
                multi -> (Multi<?>) multi,
                publisher-> Multi.createFrom().publisher(publisher)
        );
    }
}
