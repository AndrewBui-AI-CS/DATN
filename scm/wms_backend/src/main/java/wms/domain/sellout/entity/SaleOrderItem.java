package wms.domain.sellout.entity;

import lombok.*;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import wms.common.BaseEntity;
import wms.domain.category.entity.ProductEntity;
import wms.domain.sellout.entity.SaleOrder;

import javax.persistence.*;

@Entity
@Table(name = "scm_sale_order_item")
@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SaleOrderItem extends BaseEntity {
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "order_code", referencedColumnName = "code")
    @NotFound(action = NotFoundAction.IGNORE)
    private SaleOrder saleOrder;

    @Column(name = "item_seq_id")
    private String seqId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_code", referencedColumnName = "code")
    @NotFound(action = NotFoundAction.IGNORE)
    private ProductEntity product;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "price_unit")
    private Double priceUnit;
}
