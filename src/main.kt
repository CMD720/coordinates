import java.util.*
import kotlin.math.pow
import kotlin.math.sqrt

//Возьмём строку состоящую из нескольких случайных координат точек на плоскости 100 на 100.
//Строку точек должен ввести пользователь.
//Координаты разделены запятой, отдельные точки слешем.
//Например: 10,21/43,6/33,81/ …
// Нужно найти кратчайший путь соединяющий их все.

fun main(arg: Array<String>) {
    println("Введите координаты (в диапазоне 0..100) разделены запятой, отдельные точки слешем.\nНапример: 10,21/43,6/33,81/ … ")
    val scaner = Scanner(System.`in`)
    var coord = scaner.next()
    //coord = "10,21/43,6/33,81/12,56/78,34/5,5/65,10/100,100/26,65"
    //coord = "10,21/43,6/3,8/1,1"
    //println(coord)

//convert string to array of numbers
    val legh = coord.length - 1
    var numb: String = ""
    var formas: Int
    var massive = emptyArray<Int>()
    for (i in 0..legh) {
        if (coord[i] != ',') {
            if (coord[i] != '/') {
                numb += coord[i].toString()
            } else {
                formas = numb.toInt()
                massive += formas
                numb = ""
            }
        } else {
            formas = numb.toInt()
            massive += formas
            numb = ""
        }
    }
    formas = numb.toInt()
    massive += formas

//coordinate check
//проверка ошибки ввода координат
    if (massive.size % 2 != 0) {
        println("Некоторые координаты введены не верно!\nПроверьте $coord ")
    }

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

//Distance between two points
//Manual check - https://usnd.to/uQUf - ручная проверка
public fun distance(x1: Double, y1: Double, x2: Double, y2: Double): Int {
    var d: Double
    d = sqrt(((x2 - x1).pow(2)) + ((y2 - y1).pow(2)))
    return d.toInt()
}
