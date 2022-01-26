package com.keencho.spring.jpa.service;

import com.keencho.spring.jpa.model.OSIVChildrenTestModel;
import com.keencho.spring.jpa.model.OSIVParentTestModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.keencho.spring.jpa.repo.OSIVChildrenTestRepository;
import com.keencho.spring.jpa.repo.OSIVParentTestRepository;

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
