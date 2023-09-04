package wms.domain.category.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import wms.common.BaseEntity;
import wms.domain.category.entity.ProductEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "scm_product_unit")
public class ProductUnit extends BaseEntity implements Serializable {
    @Column(name = "code")
    private String code;
    @Column(name = "name")
    private String name;
    @OneToMany(mappedBy = "productUnit",fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<ProductEntity> product;
}
