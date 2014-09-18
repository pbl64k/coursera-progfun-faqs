**I don't get what's going on with sets here. Isn't a set just a data structure containing all of its elements?**

There are different possible representations of sets. In this assignment, we represent sets by their indicator functions. An indicator function can only tell us one thing - whether something belongs to a given set or not. This is the reason why assignment defines `Set` as a type alias for functions from `Int` to `Boolean`.

This is an implicit representation, as opposed to explicit representation in form of a data structure containing all of the set's elements, and it comes with certain advantages and disadvantages. One disadvantage is that we cannot enumerate all the elements of a given set, one advantage is that we can efficiently perform certain operations even with infinite sets.

Definition of what an indicator function is can be found in Wikipedia:

http://en.wikipedia.org/wiki/Indicator_function

**Infinite sets? What would an infinite set be?**

    def evenNumbers: Set = x => x % 2 == 0

**I'm using `Set()` to construct a set and stuff starts blowing up in remote parts of my program. What am I doing wrong?**

`Set()` is from Scala's standard library, and it constructs a set in one of built-in explicit representations. If you want to create a set compatible with this assignment's definition, you should create a function. E.g.:

    def twoAndFive: Set = x => x == 2 || x == 5

This creates a set that contains the integers two and five. Unfortunately, built-in `Set()`s define `apply`, so they can also "look" like functions, but for the purposes of this assignment you should use the representation described in the problem statement.

**I'm done with `singletonSet()`, but how can there be a set with multiple elements given our definitions here?!**

It would be a function that returns `true` in more than one case. See the example in the previous answer.

**What would an empty set be?**

    def emptySet: Set = x => false

**How do I detect an empty set?**

You can't - as mentioned before, we cannot inspect the functions representing sets in this assignment. Good news is, we don't need that capability for any of the assignment problems. `exists` - to be implemented later in the assignment - kinda allows you to check whether the set is empty... within given bounds. Obviously, that's not a real test for an empty set.

**What are these *union*, *intersect* and *set difference* things?**

This is from basic set theory. Don't worry, this is no rocket science, you can learn all the set theory you need for this assignment in half an hour. See, for example:

http://en.wikipedia.org/wiki/Basic_set_operations#Basic_operations

http://en.wikipedia.org/wiki/Algebra_of_sets

Not that we're using *set-theoretic difference* as difference in this assignment, rather than *symmetric set difference*.

**Can I assume the same bounds as described in `forall` and `exists` tasks apply to `union` etc.?**

No. They do apply for `map` (in a way), but the rest of your solutions should be general. Norman Graham, one of the CTAs for the Spring '13 session of this class, put it this way:

"Bounds are not needed for the implementation of `union()`. If you're thinking in terms of iterating over a range of integers, then you're probably falling back into imperative thought patterns. Pay attention to the type signatures and think about how you can define `union()` directly as a Set in terms of `s` and `t`."

**The grader complains about my implementation of `diff` for some strange reason.**

Make sure your difference is not symmetric. See above for links explaining the difference between the two.

**Is there any difference between `intersect` and `filter`?**

Not really.

**What should `forall` evaluate to when given an empty set?**

True. This follows from the duality of existential and universal classifiers (extended de Morgan's laws). The reasoning is the same as with "sum of an empty sequence" or "product of an empty sequence" - the result should be the identity element of the operation, which is "true" for conjunction.

**Should `forall` and `exists` terminate as soon as the result is known for certain?**

Definitely.

**Both of them?!**

Yeah. Remember, `forall` is just an `exists` in disguise. And vice versa.

**Must I define `exists` in terms of `forall`?**

Why wouldn't you? Remember, `forall` is just an `exists` in disguise. And vice versa.

**I'm looking at `map` and I don't even know how to approach this problem.**

A couple of crucial hints: you can't find the inverse of an arbitrary function. For the mathematically inclined there's a proof here:

http://stackoverflow.com/questions/13404208/in-pure-functional-languages-is-there-an-algorithm-to-get-the-inverse-function/13405526#13405526

This result should be quite intuitive, especially since we're treating the function here as a black box - we can't know anything about it, we can only ask it abouts its outputs for different inputs.

Secondly, our implementation of `exists` is somewhat lacking, but if we implement `map` as if our `exists` worked perfectly in all cases that will be enough for the grader.

**What's a partial application? What's currying? What is the difference between them? How are they related?**

There's always a lot of confusion between currying and partial applications.

Partial application is easy. Fundamentally, we just take some function `(a, b, c ...) => z` and produce a new function with reduced domain by simply fixing some of the arguments. E.g., consider $$+: \mathbb{Z}^2 \to \mathbb{Z}$$ We can make a successor function $$succ: \mathbb{Z} \to \mathbb{Z}$$ out of it by fixing one of the summands to be one:

    def succ(x: Int) = x + 1

This is a partial application of the `+` operator (and Scala being a language with rich syntax, you can express this in a lot of different ways, including `(_ + 1)`).

Currying, on the other hand, is simply an operation that transforms "uncurried" function with type `(a, b) => c` into its "curried" equivalent `a => b => c`, and uncurrying is the dual operation.

    def curry2[A, B, C](f: (A, B) => C)(x: A)(y: B) = f(x, y)
    def uncurry2[A, B, C](f: A => B => C)(x: A, y: B) = f(x)(y)

There are two things currying has in common with partial application.

Firstly, currying is implemented in terms of partial application (if you desugar the `curry2` definition above, for example):

    def curry2[A, B, C](f: (A, B) => C) = (x: A) => (y: B) => f(x, y)

Secondly, curried functions make it easy to create their partially applied versions.
