import java.util.*

fun input(): String {
    println("Введите координаты (в диапазоне 0..100) разделены запятой, отдельные точки слешем.\nНапример: 10,21/43,6/33,81/ … ")
    val scaner = Scanner(System.`in`)
    var coord = scaner.next()

    //coord = "10,21/43,6/33,81/12,56/78,34/5,5/65,10/100,100/26,65"
    //coord = "10,21/43,6/3,8/1,1"
    //println(coord)
    return coord
}