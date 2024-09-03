//package com.example.gateway.filter;
//
//
//import com.google.common.hash.HashFunction;
//import org.springframework.cloud.gateway.filter.GatewayFilter;
//import org.springframework.cloud.gateway.filter.GatewayFilterChain;
//import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
//
//import org.springframework.web.server.ServerWebExchange;
//import reactor.core.publisher.Mono;
//
//import java.net.URI;
//import java.util.Map;
//
//
//public class ConsistentHashingFilter implements GatewayFilter {
//
//    private final Map<String, String> partitionToInstanceMap = Map.of(
//            "0", "notification-service:8089",
//            "1", "notification-service-2:8089"
//    );// Partition과 인스턴스의 맵핑 정보
//    @Override
//    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
//        // Kafka 메시지에서 얻은 partition 정보 (예시)
//        String memberId = exchange.getRequest().getQueryParams().getFirst("memberId");
//        String partition = getPartitionForMember(memberId);
//        String instance = partitionToInstanceMap.get(partition);
//
//        URI uri = URI.create("http://" + instance);
//        exchange.getAttributes().put(ServerWebExchangeUtils.GATEWAY_REQUEST_URL_ATTR, uri);
//
//        return chain.filter(exchange);
//    }
//    private String getPartitionForMember(String memberId) {
//        // Consistent Hashing 알고리즘을 통해 partition 결정
//        return HashFunction.hash(memberId) % partitionToInstanceMap.size();
//    }
//
//}
