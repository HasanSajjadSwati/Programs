package com.kalsym.product.service.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.kalsym.product.service.model.product.Product;
import com.kalsym.product.service.model.product.Product;
import com.kalsym.product.service.model.product.ProductReview;
import com.kalsym.product.service.model.product.ProductReview;
import com.kalsym.product.service.model.product.ProductVariant;
import com.kalsym.product.service.model.product.ProductVariant;
import com.kalsym.product.service.model.product.ProductVariantAvailable;
import com.kalsym.product.service.model.product.ProductVariantAvailable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author hasan
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductRequest {
    private Product product;
    private List<ProductReview> productReview;
    private List<ProductVariant> productVariant;
}
