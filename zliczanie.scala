object Main{
	def main(args: Array[String]){
		val source = scala.io.Source.fromFile("log.txt")
    	val data = source.getLines.toList.map(x => x.toInt)
    	printList(data)
    	println

    	srednia(data)

    	mediana(data)

    	println("maks = " + data.max)
	}

	def srednia(args: List[Int]): Unit = {
		val iloscElem = args.length
		val suma: Int = args.reduceLeft(_.toInt + _.toInt)

		val srednia = suma/iloscElem

		println("srednia wynosi = " + srednia.toString)
	}

	def mediana(args: List[Int]): Unit = {
		val srodkowy = args.length/2
		val posortowana = args.sorted
		println("mediana wynosi = " + posortowana(srodkowy))
	}


	def printList(args: List[_]): Unit = {
  		args.foreach(println)
	}
}