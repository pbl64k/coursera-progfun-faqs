**Is there a limit on a number of submissions?**

Yes. We're only allowed five submissions before the grades stop counting towards the effective score for the assignment. See the Grading Policy page in the sidebar.

**How do I compute the elements of Pascal's triangle?**

There's a nifty animation on Wikipedia's page for Pascal's triangle, right next to the table of contents:

http://en.wikipedia.org/wiki/Pascal%27s_triangle

Note that you *should not* compute the elements of the triangle using other formulae for binomial coefficients. Grader may rely on certain behavior specific to this particular way to implement this function. Other approaches may fail where recursive solution would succeed (e.g., because factorial of any sufficiently large number is congruent 0 modulo $$2^{64}$$, causing division by zero errors).

**What should I do in `pascal()` if `c > r`?**

Grader will not complain if you return zero in that case.

**What exactly does it mean for a string to be balanced?**

A string with no parentheses in it is balanced. Any given opening parenthesis is balanced if and only if there's a closing parenthesis to the right of it such that the expression between them is also balanced. Any given closing parenthesis is balanced if and only if there's an opening parenthesis to the left of it such that the expression between them is also balanced. An expression is balanced if and only if all of the parentheses in it are balanced.

Note that this does not give you an immediate solution for the problem - you should think about it a little.

**What does CHF stand for?**

It's the currency symbol for Swiss franc.

**How many ways are there to change 0 CHF?**

One way - using no coins at all. No, you cannot give no coins if asked for 14 CHF - sum of an empty sequence of numbers is 0, not 14.

**What is the meaning of all those `isEmpty`, `head` and `tail`?**

Give them a shot in `sbt` console or Scala worksheet. The behavior should be easy to understand.

**Should my solution be runnable?**

Yes. `run` in `sbt` should produce a simple text-based representation of Pascal's triangle.

**Should I use just what we learned in the class so far?**

Generally speaking, yes. Graders are known to check the code for using certain features in at least some cases. Besides, it's a good idea anyway, as the early lessons in the class work towards motivating and then introducing various more advanced concepts.

**Should I handle edge/corner cases?**

Generally speaking, the assignment instructions tend to tell us whenever we need to do something specific about "weird" inputs. In all other cases you may assume the inputs are valid.

**Should I make my solutions tail-recursive?**

No. The grader will be perfectly happy with solutions that do not use tail recursion.

**Can I make my solutions tail-recursive?**

Yes, you can make pretty much anything tail-recursive. Simple tail-recursion takes the form of:

    def f(current_state: StateType): StateType =
      if (exit_condition(current_state))
        current_state
      else
        f(make_new_state(current_state))
    f(initial_state)

and is equivalent to the following imperative approach (in pseudo-code):

    current_state := initial_state
    while not exit_condition(current_state) do
        mutate_state(current_state)
    end

Anything that can be computed one of these two ways can also be computed the other way (in fact, the transformation from one to the other is purely mechanical). You may convice yourself that anything computable can be computed iteratively by considering the Universal Turing Machine - which is certainly an iterative process.

But the question you should be asking yourself instead is whether making your solution tail-recursive gave you any benefit. If making a function tail-recursive requires passing a lot of state as a parameter, you're essentially trading off stack space for heap space, which may or may not be useful, depending on the situation. For example, writing balanced tree traversal in tail-recursive form is no rocket science, but it's usually an exercise in futility, unless your stack space is *extremely* constrained, while heap space is abundant.

Even when tail-recursive implementations have the same asymptotic complexity, they may have worse constant factors, which may be significant in some circurmstances. In some situations involving lazy data structures, naive tail-recursive rewrites may be outright wrong, as they will not terminate, while a straightforward recursive implementation would be both terminating and efficient, but that is a fairly advanced topic beyond the scope of this class.

**Wait... How do I write a tail-recursive tree traversal?**

The answer uses language features and concepts not introduced in the lectures yet, so you may not be able to understand it completely right now (but you will be after finishing the class):

    object Main extends App {
      class Tree
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
      println(sum(Node(Leaf(1), 2, Node(Leaf(3), 4, Leaf(5)))))
    }

In general, when you want to convert something non-trivial to a tail-recursive form, you reify the work to be done (in this case, in form of a list of sub-trees still to be processed -- `ts`).

**Why am I not supposed to use `var`? I need mutable state.**

You can have "mutable state" without mutable variables. See the examples above - they have "mutable state", but all the functions involved remain referentially transparent (i.e., they always produce the same result given the same inputs).

**My solution passes all of provided unit tests, but the grader complains about it.**

Unit tests provided in the starter code do not cover all of the cases. In fact, writing your own test cases is part of the assignment. And with the limit on a number of submissions it's a *crucial* part of it.

**I don't get `if .. else` - it doesn't quite do what I expected.**

`if .. else` in Scala is not equivalent to `if .. else` *statements* in imperative programming languages. It's an expression, which means it has a type and always evaluates to a value, so it's closer to ternary operator (`?:`) in C-like languages or `foo if bar else baz` in Python.

The type of any `if` expression is the least common ancestor of the types of its branches (we'll learn more about Scala's type system later in the class). While we're using proper `if .. else` expressions, where the types of expressions in both branches are the same, that reduces to "`if` expression has the same type as its branches". `if` without an explicit `else` clause still has it, implicitly, but its type is `AnyVal`, and the type of the entire `if` expression is the same in this case -- which is probably not what you expected or desired.

**What's wrong with using `return`?**

A ton of things, perhaps the scariest being the fact that it doesn't respect the boundaries of anonymous functions.

But perhaps more importantly, if you're thinking in terms of `return`, you're doing something wrong. Most if not all of your function definitions should consist of a single expression (perhaps with some auxiliary definitions). This expression may be quite involved, but it will always evaluate to something - and that something will be "returned" by your function.

**What's the best way to enforce preconditions in my functions?**

It depends, but `require` is great (both in the context of this class and elsewhere).
