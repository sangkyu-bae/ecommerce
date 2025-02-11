// 타입 정의 파일 분리
export interface OrderItem {
    id: number;
    productName: string;
    colorName: string;
    size: string;
    amount: number;
    payment: number;
    statusCode: {
        name: string;
        code: string;
    };
}

export interface OrderResponse {
    pageNumber: number;
    pageSize: number;
    totalElement: number;
    totalPage: number;
    orderAggregationVos: OrderItem[];
}

export interface OrderFetcherProps {
    children: React.ReactElement;
} 