import java.util.*
import kotlin.math.pow
import kotlin.math.sqrt

//Возьмём строку состоящую из нескольких случайных координат точек на плоскости 100 на 100.
//Строку точек должен ввести пользователь.
//Координаты разделены запятой, отдельные точки слешем.
//Например: 10,21/43,6/33,81/ …
// Нужно найти кратчайший путь соединяющий их все.

//class of coordinates
class Coord(val pointX: Int, val pointY: Int) {
    fun lcd() {
        print("X-$pointX,Y-$pointY | ")
    }
}

//array of Coord class //make points
fun arrPoint(massive: Array<Int>): Array<Coord> {
    var point = emptyArray<Coord>()
    for (i in massive.indices step 2) {
        point += Coord(massive[i], massive[i + 1])
    }
    return (point)
}

//find the closest point of origin//находим ближайшую точку к началу координат
fun startPoint(points: Array<Coord>): Array<Coord> {
    var points2 = points.clone()
    var a: Int
    var b = 0
    var start = distance1(Coord(0, 0), points[0])
    for (i in points.indices) {
        a = distance1(Coord(0, 0), points[i])
        when {
            a < start -> {
                start = a
                b = i
            }
        }
    }
    points2[0] = points[b]
    points2[b] = points[0]
    return (points2)
}

//find the shortest way//находим кротчайший путь
fun shortWay(points2: Array<Coord>): Array<Coord> {
    var minDist = distance1(points2[0], points2[1])
    var index = 1
    var dist:Int
    var x:Coord
    for (i in 0..points2.size-2){
        for (j in i+1..points2.size-1){
            dist=distance1(points2[i],points2[j])
            if (dist < minDist) {
                minDist = dist
                index = j
            }
        }
        x=points2[i+1]
        points2[i+1]=points2[index]
        points2[index]=x
    }
    return (points2)
}

fun main(arg: Array<String>) {
    println("Введите координаты (в диапазоне 0..100) разделены запятой, отдельные точки слешем.\nНапример: 10,21/43,6/33,81/ … ")
    val scaner = Scanner(System.`in`)
    var coord = scaner.next()

    //coord = "10,21/43,6/33,81/12,56/78,34/5,5/65,10/100,100/26,65"
    coord = "10,21/43,6/3,8/1,1"
    println(coord)


//convert string to array of numbers
    var massive = convStrToMas(coord)

    massive.forEach { i -> print("$i-") }
    println("")

//coordinate check
    check(massive.size, coord)
//array of Coord class //make points
    var points = arrPoint(massive)
    points.forEach { i -> i.lcd() }
    println()
    println("Distance is-${distance1(points[0], points[3])}")

    var points2 = startPoint(points)

    shortWay(points2)
    println()
    points2.forEach { i -> i.lcd() }
    println()



////////////////////////////////////////////////OLD
    var pointX = emptyArray<Int>()
    var pointY = emptyArray<Int>()

    for (i in massive.indices) {
        when {
            i % 2 == 0 -> pointX += massive[i]
            else -> pointY += massive[i]
        }
    }
//find the closest point of origin
//находим ближайшую точку к началу координат
    var a: Int
    var b = 0
    var start = distance(0.0, 0.0, pointX[0].toDouble(), pointY[0].toDouble())
    for (i in pointX.indices) {
        a = distance(0.0, 0.0, pointX[i].toDouble(), pointY[i].toDouble())
        when {
            a < start -> {
                start = a
                b = i
            }
        }
    }
//Move start-point to start massive
    var X: Int
    var Y: Int
    X = pointX[0]
    Y = pointY[0]
    pointX[0] = pointX[b]
    pointY[0] = pointY[b]
    pointX[b] = X
    pointY[b] = Y

//find the shortest way
    var temp: Int
    var tempMin = distance(pointX[0].toDouble(), pointY[0].toDouble(), pointX[1].toDouble(), pointY[1].toDouble())
    var index = 1
    for (i in 0..pointX.size - 2) {
        for (j in i + 1..pointX.size - 1) {
            temp = distance(pointX[i].toDouble(), pointY[i].toDouble(), pointX[j].toDouble(), pointY[j].toDouble())
            if (temp < tempMin) {
                tempMin = temp
                index = j
            }
        }
        X = pointX[i + 1]
        Y = pointY[i + 1]
        pointX[i + 1] = pointX[index]
        pointY[i + 1] = pointY[index]
        pointX[index] = X
        pointY[index] = Y
    }
//the shortest path to the following points
    println("Кратчайший путь по следующим точкам")
    print("X: ")
    pointX.forEach { i -> print("$i,") }
    println("")
    print("Y: ")
    pointY.forEach { i -> print("$i,") }
    println("")
}
////////////////////////////////////////////////OLD


//Distance between two points
//Manual check - https://usnd.to/uQUf - ручная проверка
fun distance(x1: Double, y1: Double, x2: Double, y2: Double): Int {
    var d: Double
    d = sqrt(((x2 - x1).pow(2)) + ((y2 - y1).pow(2)))
    return d.toInt()
}

//Distance between two points NEW
//Manual check - https://usnd.to/uQUf
fun distance1(point1: Coord, point2: Coord): Int {
    var d: Double
    d = sqrt(((point2.pointX - point1.pointX).toDouble().pow(2)) + ((point2.pointY - point1.pointY).toDouble().pow(2)))
    return d.toInt()
}

//coordinate check
fun check(x: Int, s: String) {
    if (x % 2 != 0) {
        println("Некоторые координаты введены не верно!\nПроверьте $s ")
    }
}

//convert string to array of numbers
//Для проверки, является ли символ цифрой или буквой, используем Char.isDigit() и Char.isLetter().
fun convStrToMas(cordStr: String): Array<Int> {
    val legh = cordStr.length - 1
    var numb: String = ""
    var formas: Int
    var massive = emptyArray<Int>()
    for (i in 0..legh) {
        if (cordStr[i].isDigit()) {
            numb += cordStr[i].toString()
        } else {
            formas = numb.toInt()
            massive += formas
            numb = ""
        }
    }
    formas = numb.toInt()
    massive += formas

    return (massive)
}

