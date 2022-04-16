import kotlin.math.pow
import kotlin.math.sqrt

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
    val points2 = points.clone()
    var a: Int
    var b = 0
    var start = distance(Coord(0, 0), points[0])
    for (i in points.indices) {
        a = distance(Coord(0, 0), points[i])
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

//find the shortest way//находим кратчайший путь
fun shortWay(points2: Array<Coord>): Array<Coord> {
    var minDist = distance(points2[0], points2[1])
    var index = 1
    var dist: Int
    var x: Coord
    for (i in 0..points2.size - 2) {
        for (j in i + 1..points2.size - 1) {
            dist = distance(points2[i], points2[j])
            if (dist < minDist) {
                minDist = dist
                index = j
            }
        }
        x = points2[i + 1]
        points2[i + 1] = points2[index]
        points2[index] = x
    }
    return (points2)
}

//Distance between two points NEW
//Manual check - https://usnd.to/uQUf
fun distance(point1: Coord, point2: Coord): Int {
    val d: Double
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
    var numb = ""
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