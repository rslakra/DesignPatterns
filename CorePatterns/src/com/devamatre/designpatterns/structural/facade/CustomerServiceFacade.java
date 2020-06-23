package com.devamatre.designpatterns.structural.facade;

/**
 * Author: Rohtash Singh Lakra
 * Created: 6/22/20 7:10 PM
 * Version: 1.0.0
 */
public enum CustomerServiceFacade {
    INSTANCE;

    enum DepartmentType {
        BILLING,
        SHIPPING,
        ORDERS;
    }

    public void callHandler(DepartmentType departmentType) {
        Department department = null;
        switch (departmentType) {
            case BILLING:
                department = new Billing();
                break;
            case SHIPPING:
                department = new Shipping();
                break;
            case ORDERS:
                department = new Orders();
                break;

            default:
                break;
        }

        department.callHandler();
    }
}
