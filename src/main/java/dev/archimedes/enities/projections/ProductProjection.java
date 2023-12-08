package dev.archimedes.enities.projections;

import org.springframework.beans.factory.annotation.Value;

public class ProductProjection {
    public interface ProductInfo{
        long getId();
        String getProduct_name();
        double getPrice();
        String getCategory();
        String getExpiry();
        String getOrigin();
        @Value("#{target.branches.size()}")
        int getBranchCount();
    }
}
