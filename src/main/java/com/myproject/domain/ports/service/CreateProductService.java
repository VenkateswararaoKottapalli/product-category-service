package com.myproject.domain.ports.service;

import com.myproject.domain.ports.inbound.ICreateProduct;
import com.myproject.interfaces.rest.request.AddProductRequest;
import com.myproject.interfaces.rest.response.AddProductResponse;
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
public class CreateProductService implements ICreateProduct {

    private final WebClient.Builder webClientBuilder;

    @Override
    public AddProductResponse addNewProduct(AddProductRequest addProductRequest) {
        log.info("Started creating product with request: {}", addProductRequest);
        Mono<AddProductResponse> addProductResponseMono = webClientBuilder.build()
                .post()
                .uri(FAKE_STORE_CLIENT_API_URL)
                .bodyValue(addProductRequest)
                .retrieve()
                .onStatus(httpStatusCode -> httpStatusCode.is4xxClientError() || httpStatusCode.is5xxServerError(),
                        clientResponse -> Mono.error(new RuntimeException("API call failed with status code: " + clientResponse.statusCode())))
                .bodyToMono(AddProductResponse.class);

        AddProductResponse addProductResponse = null;
        if (!ObjectUtils.isEmpty(addProductResponseMono.block())) {
            addProductResponse = addProductResponseMono.block();
            log.info("Successfully created product with response: {}", addProductResponse);
        }
        return addProductResponse;
    }
}
