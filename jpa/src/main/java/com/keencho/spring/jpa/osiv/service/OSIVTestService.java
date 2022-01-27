package com.keencho.spring.jpa.osiv.service;

import com.keencho.spring.jpa.osiv.model.OSIVChildrenTestModel;
import com.keencho.spring.jpa.osiv.model.OSIVParentTestModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.keencho.spring.jpa.osiv.repository.OSIVChildrenTestRepository;
import com.keencho.spring.jpa.osiv.repository.OSIVParentTestRepository;

import javax.transaction.Transactional;

@Service
@Transactional
public class OSIVTestService {

    @Autowired
    OSIVParentTestRepository osivParentTestRepository;

    @Autowired
    OSIVChildrenTestRepository osivChildrenTestRepository;

    public void insertParent() {
        osivParentTestRepository.save(new OSIVParentTestModel("parent"));
    }

    public void insertChildren() {
        osivChildrenTestRepository.save(new OSIVChildrenTestModel("children", osivParentTestRepository.findAll().stream().findAny().get()));
    }

    public OSIVChildrenTestModel getChildren() {
        var children = osivChildrenTestRepository.findAll().stream().findAny().get();
        return children;
    }
}
