package dev.archimedes.enities.projections;

import org.springframework.beans.factory.annotation.Value;

import java.util.List;

public class EmployeeProjection {

    public interface EmployeeInfo{
        int getId();
        String getName();
        String getEmail();
        @org.springframework.beans.factory.annotation.Value("#{target.roles.![name]}")
        List<String> getRoles();
        @org.springframework.beans.factory.annotation.Value("#{target.branch.branchId}")
        int getBranch();

    }

    public interface Address{

        @Value("#{target.address.city}")
        String getCity();

        @Value("#{target.address.state}")
        String getState();

        @Value("#{target.address.country}")
        String getCountry();

        @Value("#{target.address.pincode}")
        int getPincode();

        @Value("#{target.addressType}")
        String getAddressType();
    }
}
