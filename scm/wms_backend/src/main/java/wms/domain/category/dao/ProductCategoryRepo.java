package wms.domain.category.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import wms.domain.category.entity.ProductCategory;

public interface ProductCategoryRepo extends JpaRepository<ProductCategory, Long> {
    @Query(value = "select * from scm_product_category where is_deleted = 0", nativeQuery = true)
    Page<ProductCategory> search(Pageable pageable);

    ProductCategory getProductCategoryById(long id);
    ProductCategory getProductCategoryByCode(String code);
}
