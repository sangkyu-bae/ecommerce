interface ColorData {
    colorDto : Data;
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