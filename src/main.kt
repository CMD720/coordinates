
//Возьмём строку состоящую из нескольких случайных координат точек на плоскости 100 на 100.
//Строку точек должен ввести пользователь.
//Координаты разделены запятой, отдельные точки слешем.
//Например: 10,21/43,6/33,81/ …
// Нужно найти кратчайший путь соединяющий их все.


fun main(arg: Array<String>) {
    var coord: String
    coord=input()
    val massive = convStrToMas(coord)
    check(massive.size, coord)
    var points = arrPoint(massive)
    points = startPoint(points)
    shortWay(points)
    println("Кратчайший путь по следующим точкам")
    points.forEach { i -> i.lcd() }
}