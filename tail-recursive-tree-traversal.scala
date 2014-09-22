// (Rather pointless) Tail recursive binary tree traversal in Scala
// See week1-a1-recfun-faq

object Main {
  sealed abstract class Tree
  case class Leaf(n: Int) extends Tree
  case class Node(l: Tree, n: Int, r: Tree) extends Tree
  def sum(t: Tree) = {
    def sum0(acc: Int, ts: List[Tree]): Int = ts match {
      case Nil => acc
      case t :: ts => t match {
        case Leaf(x) => sum0(acc + x, ts)
        case Node(l, x, r) => sum0(acc + x, l :: r :: ts)
      }
    }
    sum0(0, List(t))
  }

  def main(args: Array[String]) = {
    println(sum(Node(Leaf(1), 2, Node(Leaf(3), 4, Leaf(5)))))
  }
}
