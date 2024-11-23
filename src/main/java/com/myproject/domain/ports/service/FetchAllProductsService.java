package com.myproject.domain.ports.service;

import com.myproject.domain.ports.inbound.IFetchAllProducts;
import com.myproject.interfaces.rest.request.ProductResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

import static com.myproject.application.constant.CommonConstants.FAKE_STORE_CLIENT_API_URL;

@Service
@AllArgsConstructor
@Slf4j
public class FetchAllProductsService implements IFetchAllProducts {

    private final WebClient.Builder webClientBuilder;

    @Override
    public List<ProductResponse> fetchAllProducts() {
        log.info("Started fetching all products");
        Mono<List<ProductResponse>> productsList = webClientBuilder.build()
                .get()
                .uri(FAKE_STORE_CLIENT_API_URL)
                .retrieve()
                .onStatus(httpStatusCode -> httpStatusCode.is4xxClientError() || httpStatusCode.is4xxClientError(),
                        clientResponse -> Mono.error(new RuntimeException("API call failed with status code: " + clientResponse.statusCode())))
                .bodyToMono(new ParameterizedTypeReference<List<ProductResponse>>() {
                });

        List<ProductResponse> productResponseList = null;
        if (!ObjectUtils.isEmpty(productsList.block())) {
            productResponseList = productsList.block();
            log.info("Fetched all products wirth size: {}", productResponseList.size());
        }
        return productResponseList;
    }
}
