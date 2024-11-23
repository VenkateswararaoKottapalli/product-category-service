package com.myproject.domain.ports.inbound;

import com.myproject.interfaces.rest.request.ProductResponse;

public interface IDeleteProduct {
    ProductResponse deleteProduct(Integer productId);
}
