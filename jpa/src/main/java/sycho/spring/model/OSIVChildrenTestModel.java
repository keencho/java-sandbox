package sycho.spring.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(name = "osiv_children_test_model")
public class OSIVChildrenTestModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    String keyword;

    @ManyToOne(fetch = FetchType.LAZY)
    private OSIVParentTestModel osivParentTestModel;

    public OSIVChildrenTestModel(String keyword, OSIVParentTestModel osivParentTestModel) {
        this.keyword = keyword;
        this.osivParentTestModel = osivParentTestModel;
    }
}
