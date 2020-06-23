package com.devamatre.designpatterns.structural.facade;

import com.devamatre.designpatterns.structural.facade.CustomerServiceFacade.DepartmentType;

import java.util.Arrays;
import java.util.List;

/**
 * Author: Rohtash Singh Lakra
 * Created: 6/22/20 7:17 PM
 * Version: 1.0.0
 */
public class CustomerServiceTest {
    /**
     * @param args
     */
    public static void main(String[] args) {
        final List<DepartmentType> calls = Arrays.asList(DepartmentType.BILLING, DepartmentType.ORDERS,
                DepartmentType.BILLING, DepartmentType.ORDERS, DepartmentType.SHIPPING, DepartmentType.SHIPPING,
                DepartmentType.BILLING, DepartmentType.ORDERS, DepartmentType.SHIPPING, DepartmentType.BILLING,
                DepartmentType.ORDERS, DepartmentType.SHIPPING);

        calls.forEach(departmentType -> CustomerServiceFacade.INSTANCE.callHandler(departmentType));
    }
}
