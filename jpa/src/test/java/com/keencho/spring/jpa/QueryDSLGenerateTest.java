package com.keencho.spring.jpa;

import com.keencho.lib.spring.jpa.querydsl.KcGenericExporter;
import com.querydsl.codegen.Keywords;
import org.junit.jupiter.api.Test;

import javax.persistence.*;
import java.io.File;

public class QueryDSLGenerateTest {

    @Test
    public void test() {

        var exporter = new KcGenericExporter();
        exporter.setKeywords(Keywords.JPA);
        exporter.setEntityAnnotation(Entity.class);
        exporter.setEmbeddableAnnotation(Embeddable.class);
        exporter.setEmbeddedAnnotation(Embedded.class);
        exporter.setSupertypeAnnotation(MappedSuperclass.class);
        exporter.setSkipAnnotation(Transient.class);
        exporter.setTargetFolder(new File("build/generated/querydsl"));
        exporter.export(KeenchoSpringJpaApplication.class.getPackage());
    }
}
