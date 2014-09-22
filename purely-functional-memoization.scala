// (Rather pointless) Purely functional memoization using lazy values in Scala
// See week6-a5-forcomp-faq

object Main {
  class MemoStringFunction[T](f: String => T, x: String) {
    private lazy val v = f(x)
    private lazy val ts = (for {
      tx <- 'a' to 'z'
    } yield (tx, new MemoStringFunction(f, x + tx))).toMap
    private def get(x: String): T = x match {
      case "" => v
      case s => ts(s.head).get(s.tail)
    }
    def apply(x: String): T = get(x) 
  }

  def g(s: String): Int = {
    println("computing length for: " ++ s)
    if (s.isEmpty)
        0
    else
        (1 + f(s.tail))
  }

  val f = new MemoStringFunction(g, "")

  def main(args: Array[String]) = {
    println(f("a"))
    println(f("f"))
    println(f("abcdef"))
    println(f("a"))
    println(f("f"))
    println(f("abcdef"))
  }
}
