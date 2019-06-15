package com.speedoring.interface_update_data;

import com.speedoring.modal.user.product_category.ProductCategoryList;
import com.speedoring.modal.user.service_category.ServicesCategory;

import java.util.List;

public interface CategoryServiceInterface {

    void getCategoryList(List<ServicesCategory> servicesList, List<ProductCategoryList> categoryLists);
}
