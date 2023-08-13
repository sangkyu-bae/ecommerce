interface ColorData {
    colorName : string;
    colorSize : SizeQuantityData[]
}
interface SizeQuantityData {
    size : Size
    quantity : number;
}
interface Size {
    size : number,
    id : number
}