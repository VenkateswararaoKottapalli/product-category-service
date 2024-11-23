package com.myproject.domain.ports.inbound;

import com.myproject.interfaces.rest.request.ProductResponse;

public interface IFetchProduct {
    ProductResponse fetchProduct(Integer productId);
}
