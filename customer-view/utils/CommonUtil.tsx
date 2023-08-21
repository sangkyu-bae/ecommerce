export const getTotalPageNumber = (totalCount: number, pageSize: number): number => {
    let totalPageNumber: number = Math.ceil(totalCount / pageSize);
    return totalPageNumber;
}

interface MenuData {
    menuName: string;
    url: string;
}

interface MenuList {
    admin: MenuData[];
}

const menuList: MenuList = {
    admin: [
        {
            menuName: '상품관리',
            url: "/admin/product"
        },
        {
            menuName: '상품등록',
            url: "/admin/myPage"
        }
    ]
}

class Util {
    constructor() {
    }

    getTotalPageNumber = (totalCount: number, pageSize: number): number => {
        let totalPageNumber: number = Math.ceil(totalCount / pageSize);
        return totalPageNumber;
    }
    addCommasToNumber = (price: number): string => {
        return price.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
    }

    getMenuList =(type : string ):MenuData[]=>{
        return menuList[type];
    }
}

export default Util;