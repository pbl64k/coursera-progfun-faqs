// (Rather pointless) Implementation of FizzBuzz using lazy streams

object Main {
  // Infinite stream of natural numbers
  def nats: Stream[Int] = 1 #:: (nats map (_ + 1))

  def makefn(fact: Int, str: String)(x: String, y: Int) =
      if (y % fact == 0)
        x + str
      else
        x

  // Generalizes to any number of mutually prime divisors
  def fs = List(makefn(3, "Fizz")_, makefn(5, "Buzz")_,
    (x: String, y: Int) =>
      if (x.length == 0)
        y.toString
      else
        x)

  // The anonymous function maps a natural to its fizzbuzzy representation
  def fizzbuzz = nats map (n => fs.foldLeft("")((x, f) => f(x, n)))

  def main(args: Array[String]) = {
    for (x <- fizzbuzz take 100) {
      println(x)
    }
  }
}

