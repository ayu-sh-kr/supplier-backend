package dev.archimedes.enities.projections;

import org.springframework.beans.factory.annotation.Value;

public class OrderProjection {
    public interface OrderInfo{

//        @Value("#{target.id}")
        long getId();

//        @Value("#{target.status}")
        String getOrderStatus();

        @Value("#{target.buyer.id}")
        long getBuyerId();

        @Value("#{target.branch.branchId}")
        int getBranchId();

        @Value("#{target.buyer.name}")
        String getBuyerName();

        @Value("#{target.branch.name}")
        String getBranchName();

//        @Value("#{target.products.length}")
//        int getProductSize();
    }
}
