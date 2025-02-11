import React from 'react';
import { styled } from '@mui/material/styles';
import { Box, Typography, Pagination, CircularProgress } from '@mui/material';
import TableHeader from "@/components/common/TableHeader";
import OrderInfoBox from "@/components/order/OrderInfoBox";

const Container = styled(Box)({
  padding: '24px',
  backgroundColor: '#fff5e6',  // 이미지의 배경색과 동일하게
  minHeight: '100vh',
});

const Content = styled(Box)({
  maxWidth: '1200px',
  margin: '0 auto',
});

const OrderSection = styled(Box)({
  backgroundColor: '#fff5e6',
});

const Title = styled(Box)({
  display: 'flex',
  alignItems: 'center',
  gap: '12px',
  padding: '24px 0',
  borderBottom: '1px solid #e9ecef',
  marginBottom: '20px',
  
  '& .icon': {
    fontSize: '32px',  // 이모지 크기 증가
  },
  
  '& .text': {
    fontSize: '28px',  // 폰트 크기 증가
    fontWeight: 700,   // 더 진하게
    color: '#2c3e50',  // 더 진한 색상
    letterSpacing: '-0.5px',  // 자간 조정
  },
  
  '& .sub-text': {
    fontSize: '16px',
    color: '#868e96',
    marginLeft: '8px',
    fontWeight: 400,
  }
});

const OrderList = styled(Box)({
  backgroundColor: '#fff5e6',
});

const OrderItem = styled(Box)({
  display: 'grid',
  gridTemplateColumns: '2fr 1fr 1fr 1fr',
  padding: '16px',
  borderBottom: '1px solid #e9ecef',
  alignItems: 'center',
  
  '& .product-info': {
    display: 'flex',
    flexDirection: 'column',
    gap: '4px',
  },
  
  '& .product-name': {
    fontSize: '14px',
    fontWeight: 500,
    color: '#1a1a1a',
  },
  
  '& .product-option': {
    fontSize: '13px',
    color: '#868e96',
  },
  
  '& .quantity, & .price, & .status': {
    fontSize: '14px',
    color: '#495057',
    textAlign: 'center',
  },
  
  '& .price': {
    fontWeight: 500,
  }
});

const PaginationContainer = styled(Box)({
  display: 'flex',
  justifyContent: 'center',
  padding: '24px 0',
  '& .MuiPagination-ul': {
    '& .MuiPaginationItem-root': {
      color: '#495057',
      '&.Mui-selected': {
        backgroundColor: '#495057',
        color: '#fff',
      }
    }
  }
});

interface OrderData {
  id: number;
  productName: string;
  colorName: string;
  size: string;
  amount: number;
  payment: number;
  statusCode: {
    name: string;
  };
}

interface MyOrderContainerProps {
  data: {
    orderAggregationVos: OrderData[];
  };
  isLoading: boolean;
  totalPage: number;
  setPagingNm: (page: number) => void;
}

function MyOrderContainer({data, isLoading, totalPage, setPagingNm}: MyOrderContainerProps) {
    return (
        <Container>
            <Content>
                <OrderSection>
                    <Title>
                        <span className="icon">📦</span>
                        <span className="text"> 주문 현황</span>
                    </Title>
                    
                    <OrderList>
                        <OrderItem sx={{ fontWeight: 600, borderBottom: '2px solid #dee2e6' }}>
                            <Box>상품정보</Box>
                            <Box sx={{ textAlign: 'center' }}>수량</Box>
                            <Box sx={{ textAlign: 'center' }}>주문금액</Box>
                            <Box sx={{ textAlign: 'center' }}>주문현황</Box>
                        </OrderItem>

                        {isLoading ? (
                            <Box sx={{ py: 4, textAlign: 'center' }}>
                                <CircularProgress size={32} />
                            </Box>
                        ) : (
                            data?.orderAggregationVos.map((order) => (
                                <OrderItem key={order.id}>
                                    <Box className="product-info">
                                        <Typography className="product-name">
                                            {order.productName}
                                        </Typography>
                                        <Typography className="product-option">
                                            {order.colorName} / {order.size}
                                        </Typography>
                                    </Box>
                                    <Box className="quantity">
                                        {order.amount}
                                    </Box>
                                    <Box className="price">
                                        {order.payment.toLocaleString()}원
                                    </Box>
                                    <Box className="status">
                                        {order.statusCode.name}
                                    </Box>
                                </OrderItem>
                            ))
                        )}
                    </OrderList>

                    {totalPage > 1 && (
                        <PaginationContainer>
                            <Pagination 
                                count={totalPage}
                                size="medium"
                                showFirstButton 
                                showLastButton
                                onChange={(_, page) => setPagingNm(page)}
                            />
                        </PaginationContainer>
                    )}
                </OrderSection>
            </Content>
        </Container>
    );
}

export default MyOrderContainer;