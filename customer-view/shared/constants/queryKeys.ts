export const QUERY_KEYS = {
    ORDERS: {
        all: ['orders'] as const,
        list: (page: number) => [...QUERY_KEYS.ORDERS.all, 'list', page] as const,
    },
    COUPON :{
        key : ['event']
    }
} as const;