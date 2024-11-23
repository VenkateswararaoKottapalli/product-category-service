package com.myproject.domain.ports.service;

import com.myproject.domain.ports.inbound.IUpdateOrCreateProduct;
import com.myproject.interfaces.rest.request.AddProductRequest;
import com.myproject.interfaces.rest.request.ProductResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import static com.myproject.application.constant.CommonConstants.FAKE_STORE_CLIENT_API_URL;

@Service
@Slf4j
@AllArgsConstructor
public class UpdateOrCreateProductService implements IUpdateOrCreateProduct {

    private WebClient.Builder webClientBuilder;

    @Override
    public ProductResponse updateOrProduct(Integer productId, AddProductRequest addProductRequest) {
        log.info("Started updating or creating the product with id : {}", productId);
        Mono<ProductResponse> productResponseMono = webClientBuilder.build()
                .put()
                .uri(FAKE_STORE_CLIENT_API_URL + "/" + productId)
                .bodyValue(addProductRequest)
                .retrieve()
                .onStatus(httpStatusCode -> httpStatusCode.is4xxClientError() || httpStatusCode.is5xxServerError(),
                        clientResponse -> Mono.error(new RuntimeException("API call failed with status code: " + clientResponse.statusCode())))
                .bodyToMono(ProductResponse.class);

        ProductResponse productResponse = null;
        if (!ObjectUtils.isEmpty(productResponseMono.block())) {
            productResponse = productResponseMono.block();
            log.info("Updated or created product successfully with id : {}", productId);
        }
        return productResponse;
    }
}
