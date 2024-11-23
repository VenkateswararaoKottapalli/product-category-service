package com.myproject.domain.ports.inbound;

import com.myproject.interfaces.rest.request.ProductResponse;

import java.util.List;

public interface IFetchAllProducts {
    List<ProductResponse> fetchAllProducts();
}
