**Do I need to watch week 5 / week 6 lectures for this assignment?**

Yes. The assignment covers all of the material up to and including week 6.

**Should I avoid recursion, explicit or implicit, in my solutions for this assignment?**

The answer is definitely no for implicit, and not necessarily for explicit. Many of the functions we have to implement can be made more elegant through the use of `for`-expressions and/or high-order functions on lists. But if you feel a straightforward explicitly recursive solution makes more sense, go ahead and give it a shot.

**"Implicit recursion?" What does that mean?**

This is an informal usage that seems to have cropped up during the previous runs of this class. Folds generalize structurally recursive functions on recursive data structures, so functions defined in terms of folds do not refer to themselves, but may still be considered "implicitly" recursive due to the use of folds.

**May I use mutable variables in this assignment? It seems natural in some parts of it.**

No. The same policy holds as with the rest of the course. Grader will dock points for the use of mutable variables. Anything that can be implemented with mutable variables, can also be implemented without them, and usually without much, if any, overhead in terms of source code readability.

**My `for`-expressions do not compile due to cryptic type errors.**

First of all, the sequence type of the first generator expression determines the sequence type for the entire `for`-expression - it's easy to run afoul of this.

Secondly, if you're still stuck, consider desugaring your `for`-expression following Prof. Odersky's explanations in the lectures, and rewriting it as a series of `val`s (perhaps even giving those explicit types to help you figure out where does the problem lie). This should help you figure out in which part of your expression the type mismatch actually is. This may take a while, but it's very useful to go through this process at least once. When you're working with nested lists, the most likely cause is that you've missed one level of nesting somewhere, or vice versa, introduced an unnecessary level of nesting. If you're using multiple containers you may need to use `toList` or such to make your types agree.

Lastly, one relatively common problem is figuring out how does the parser treat the expression after the `yield`. Something like `for { ... } yield x :: xs` means `(for { ... } yield x) :: xs`, while you probably wanted to say `for { ... } yield (x :: xs)`.

**Is the list of occurrences supposed to be in some specific order?**

Yes. The `Occurrences` type comes with an implied invariant of always being sorted by the character. Your solutions may rely on the fact that all inputs provided to them fulfill said invariant, and must in turn ensure that all outputs returned by them do so as well. This is explicitly mentioned both in the assignment handout and in the source code comments.

**Does `wordOccurrences` have to make use of `groupBy`?**

Yes. The use of `groupBy` here is required by the grader.

**Is there an easy way to make a `Word` out of a `Sentence`?**

Yes. Look up `mkString` in the documentation.

**My `dictionaryByOccurences` breaks on words not in the dictionary. How do I fix it?**

Using `withDefaultValue` - this was shown in the lectures discussing the `Map[T, V]` containers.

**I'm totally lost on `combinations`. How do I do it?**

One way (not necessarily the best one) to approach this problem would be to think about properties of `combinations` inductively. What are the combinations of an empty set? Given the list of combinations for some set A, how do you obtain a list of combinations for a set A with an extra element X included in it?

**I'm not clear on what the usage of "sets" means in context of `combinations`? Aren't sets supposed to include any element at most once?**

The usage of the word "set" is a bit informal here. We're actually interested in multisets, which may include the same element more than once.

**May my implementation of `subtract` assume that it's not going to be provided an `y` that is not a subset of `x`?**

Yes.

**Do I have to use the suggested functions for my implementation of `subtract`?**

No. Doing so will be quite instructive, but the grader won't check for implementation details here, as long as your `subtract` implements the behavioural requirements.

**What is the list of anagrams of an empty sentence?**

It's a list consisting of one element - the empty sentence itself. (The empty sentence is an empty list.)

**How do I filter out "incomplete" anagrams?**

One way to ensure that would be to implement previous parts of the assignment in such a way that the list of anagrams for a word not in the dictionary would be empty.

**Dictionary file seems to be missing some words I assumed would be there.**

Yeah. Do not assume a common word will be in it. When in doubt, check it.

**How about mutability in the challenge assignment?**

You should not submit this part of your code, especially if you do this with mutable containers. Note that while doing this with mutable containers is the most straightforward way to do this, it's not strictly necessary, as Scala already has the fundamental "caching" mechanism implemented in form of `lazy val`s. Given those, we can do the rest without mutable data structures. Also note that in pure world lazy evaluation strategy is equivalent to call-by-name to the factor of computational complexity - that is, it's an optimization that preserves expression semantics.

**I don't get it. How can you cache anything without mutable values?**

    object Main extends App {
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
      val f = new MemoStringFunction(s => { println("bork."); s.length }, "")
      println(f("a"))
      println(f("abcdef"))
      println(f("a"))
      println(f("abcdef"))
    }

Note that this is not really idiomatic in Scala.
