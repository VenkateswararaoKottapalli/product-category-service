package com.myproject.domain.ports.service;

import com.myproject.domain.ports.inbound.IFetchProduct;
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
public class FetchProductService implements IFetchProduct {

    private final WebClient.Builder webClientBuilder;

    @Override
    public ProductResponse fetchProduct(Integer productId) {
        log.info("Started fetching product with id : {}", productId);
        Mono<ProductResponse> productResponseMono = webClientBuilder.build()
                .get()
                .uri(FAKE_STORE_CLIENT_API_URL + "/" + productId)
                .retrieve()
                .onStatus(httpStatusCode -> httpStatusCode.is4xxClientError() || httpStatusCode.is5xxServerError(),
                        clientResponse -> Mono.error(new RuntimeException("API call failed with status code: " + clientResponse.statusCode())))
                .bodyToMono(ProductResponse.class);

        ProductResponse productResponse = null;
        if(!ObjectUtils.isEmpty(productResponseMono.block())){
            productResponse = productResponseMono.block();
            log.info("Fetched product successfully with id : {}", productId);
        }
        return productResponse;
    }
}
