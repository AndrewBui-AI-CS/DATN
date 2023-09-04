package wms.domain.sellout.entity;

import lombok.*;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import wms.common.BaseEntity;
import wms.domain.category.entity.ContractType;
import wms.domain.category.entity.ProductEntity;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "scm_product_price_sellout")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductSalePrice extends BaseEntity implements Serializable {
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_code", referencedColumnName = "code")
    @NotFound(action = NotFoundAction.IGNORE)
    private ProductEntity productEntity;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "contract_type_code", referencedColumnName = "code")
    @NotFound(action = NotFoundAction.IGNORE)
    private ContractType contractType;

    @Column(name = "mass_quantity_discount")
    private double massDiscount;

    @Column(name = "contract_discount")
    private double contractDiscount;
}
