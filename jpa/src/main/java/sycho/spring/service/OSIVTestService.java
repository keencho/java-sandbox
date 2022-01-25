package sycho.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.stereotype.Service;
import sycho.spring.model.OSIVChildrenTestModel;
import sycho.spring.model.OSIVParentTestModel;
import sycho.spring.repo.OSIVChildrenTestRepository;
import sycho.spring.repo.OSIVParentTestRepository;

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
