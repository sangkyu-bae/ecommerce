package com.example.order.adapter.out.external.product;

import com.example.order.application.port.out.RequestProductInfoPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.cloud.client.discovery.DiscoveryClient;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductAdapter implements RequestProductInfoPort {

    private final DiscoveryClient discoveryClient;
    private final RestTemplate restTemplate;
    @Override
    public boolean getProductQuantity(ProductInfoRequest productInfoRequest) {
        return false;
    }

    private String callServiceWithInt(int intValue) {
        List<ServiceInstance> instances = discoveryClient.getInstances("your-service-name");
        if (instances != null && !instances.isEmpty()) {
            ServiceInstance selectedInstance = instances.get(0);
            String serviceUrl = selectedInstance.getUri().toString();

            // 서비스 호출을 위한 URL 구성
            String requestUrl = serviceUrl + "/endpoint"; // 실제 서비스의 엔드포인트 URL로 변경

            // HTTP 요청 헤더 설정
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            // int 값을 JSON 문자열로 변환하여 요청 본문에 포함
            String requestBody = "{\"value\":" + intValue + "}";

            // HTTP 요청 본문과 헤더를 포함한 요청 엔티티 생성
            HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

            // RestTemplate을 사용하여 POST 요청 보내기
            String response = restTemplate.postForObject(requestUrl, requestEntity, String.class);

            // 서비스 응답 처리
            return response;
        }
        return null;
    }
}
