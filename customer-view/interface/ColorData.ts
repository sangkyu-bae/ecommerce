interface ColorData {
    colorName : string;
    colorSize : SizeQuantityData[]
}
interface SizeQuantityData {
    // size : number,
    size : Size
    quantity : number;
}
interface Size {
    size : number,
    id : number
}