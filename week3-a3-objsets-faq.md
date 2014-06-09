**Why is `Empty` a `class` rather than an `object`?**

One reason for that could be that the assignment designers are more accustomed to designing generic containers (which are parametrized by the type of contained elements). And `object`s cannot take type parameters. Empty containers in the standard library *are* implemented as objects (at least some of them), but there's some trickery related to type variance to make that work. Both parametric types and type variance will be covered in later weeks of the class.

**Can I extend the interface of provided classes?**

Yes. As long as your implementation conforms to the behavior specified by the assignment, you may extend class definitions with additional methods. Note that you probably shouldn't tinker with classes outside of `TweetSet.scala`, as the grader may substitute withheld implementations when running on the server.

**So can I make `Empty` an `object`?**

No. That would break the interface, as `object`s and instances of `class`es are constructed differently.

**May I use case classes/pattern matching/something else not introduced in the lectures?**

Do so at your own risk. The tools provided so far are entirely sufficient to solve the assignment.

**I'd like to use `foreach` in my solution. May I?**

Not really. `foreach` doesn't evaluate to anything useful, so the only way to use it would be resorting to side effects, which is something we're trying to keep away from in this class.

**I don't get `filterAcc`. What's it for?**

It's a helper function for `filter`. It builds the result using `acc` as an intermediate result.

**Should `filterAcc` be tail-recursive? Accumulator variable seems to hint at it.**

No. There's no point in making `filterAcc` tail-recursive as it won't result in any substantial performance gain.

**How exactly am I supposed to collect the values for `filterAcc`? I can recurse on just one branch.**

Actually, you can recurse on both. Think about Pascal's triangle. There's an additional complication in this case, as one of your recursive calls should be using the result of the other.

**Okay, but I don't know how to add values to `acc`. It's immutable.**

You can't and you don't need to. Create a new set (remember, operations on sets create new sets) and use it as an accumulator afterwards.

**I seem to need `union` for `filter`. Am I right?**

Not really, it can be done without `union`, and the solution is pretty simple.

**How do I detect an empty set? There doesn't appear to be any way to do it.**

An OO approach would be to provide different implementations of some methods for `Empty` and `NonEmpty`. A particular case of this would be to create an `isEmpty` method that returns different values for `Empty` and `NonEmpty`, though this is not necessarily the best approach, depending on one's style preferences. You definitely shouldn't be using `isInstanceOf` or using `null`, however.

We will learn yet another way to tackle this problem in later weeks.

**Catching a thrown exception seems to be a sound way to deal with the problem of handling empty sets.**

You shouldn't do that. Exception handling seems to come with substantial overhead on JVM, and in previous runs of the class some students ran into grader timeouts due to taking this approach. Avoid exception handling when implementing recursive functions.

**Just how fast should union be? Can I use the implementation given in the lectures?**

Pretty fast. At the very least you should be able to `run` the program and see the results in a second or so (this may vary depending upon hardware and JVM settings, with some students reporting execution time for `run` of up to ten seconds when running perfectly fine solutions in resource-constrained environments). The implementation in the lectures does not fit this criterion.

Note that there are several simple implementations that achieve linearithmic asymptotic time complexity on average, which should be sufficient for your solution to be accepted. If you want an extra challenge, try finding and implementing a linear solution. (This is Exercise 3.9 in Okasaki's famous *Purely Functional Data Structures* -- special thanks go to Moshe Ivry for explaining the idea to me during the second run of this class.) It's a good deal more involved than simpler approaches, but it's a beautiful algorithm, and it balances the resulting tree as an added bonus. Hint: this is somewhat similar to mergesort. The tricky part is reconstructing the tree from a linear representation of the resulting set in linear time. This may require some degree of recursive ingenuity.

**So how do I implement `union` efficiently?**

Various approaches may succeed, including an implementation very similar to the one in the lectures that exhibits much better peformance. Some students used a variety of alternate approaches, including some making use of `filter`.

**Shouldn't `mostRetweeted` be `leastRetweeted` instead?**

No. `mostRetweeted` should return the tweet with the highest number of retweets in a set. It is perfectly possible to implement `descendingByRetweet` using `mostRetweeted` rather than `leastRetweeted`.

**How do I break the ties for `mostRetweeted`?**

Ties may be broken abritrarily in this case. When two or more tweets have the same number of retweets, it doesn't matter which one of them you return as `mostRetweeted`.

**Is there a deep reason for requiring `mostRetweeted` to throw an exception in case of an empty set of tweets?**

None that I'm aware of. Just treat is as an arbitrary (but reasonably sensible) requirement.

**So what's a sane way to implement `mostRetweeted` that will work quickly and correctly in all cases? I thought I shouldn't use exception handling in recursive functions.**

Think about `filter` and `filterAcc`.

**I can't write my head around `descendingByRetweet`. Is there a systematic way to attack problems like this?**

Yes. To define a function over a recursive data structure, you need to think of the base case and of the recursive case, generally speaking. (There are always nuances in the real world.)

`descendingByRetweet` for a given set of tweets is a list. It should be empty for an empty set. If the set is non-empty, the list should be non-empty as well. A non-empty list consists of two parts - its head (which should be a tweet) and its tail (which should be a list of tweets). Clearly, the head of this list should be a tweet with the most retweets in the given set. Can you define - in English - what the tail of this list should be? Translate that definition to Scala.

**My `descendingByRetweet` is in reverse order. How do I reverse it?**

You can simply "reverse" the way you're constructing it. It's not quite as straightforward as exchanging two expressions, but it's not much more challenging than that.

**Running the program / accessing `allTweets` seems to take forever.**

See the question about `union` performance. `union` affects quite a lot of other stuff, including provided code.
