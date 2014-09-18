**Any ideas on testing my solutions?**

You could try using a few levels from the original flash game and see whether your algo manages to find a correct solution.

**Which one is `x`, and which one is `y` as far as `Pos` is concerned?**

When you're accessing a vector of vectors representing the terrain you should index by `x` first, and by `y` second.

**What do the two positions supplied to `Block` constructor represent?**

The positions of the two blocks comprising the playing piece. If the playing piece is standing upright, both are the same, otherwise the two positions are adjacent.

**May I assume coordinates supplied to `terrainFunction` will not be out-of-bounds?**

No. You should explicitly check for coordinates being within bounds. Both ways.

**What does `terrainFunction` really mean?**

Whether any part of the playing piece can be located at these coordinates without losing the game.

**Is `neighborsWithHistory` supposed to produce an infinite stream of positions?**

No, don't overthink it. You only need to generate the immediate neighbors. The rest will be handled by functions implemented later in the assignment.

**How is my `from` supposed to handle infinite terrain?**

Naturally. The tail of a stream will not be evaluated unless needed. Since `solution` only takes the first path to the goal, the entire infinite solution space will not be explored (as long as there's *some* solution in it).

There's an important caveat here: the behavior of `++` is unintuitive, as it will, in fact, force partial evaluation of its second operand. Just use `#:::` instead, which is the equivalent of `++`, but behaves sensibly for lazy streams, never touching its second operand.

Note that `++` generally works even with infinite streams, since it only evaluates the *head* of its second argument. However, evaluating the head alone may be enough to trigger infinite recursion.

**Running `Bloxxorz` causes stack overflow.**

See the answer to the previous question.

**Can't we just determine `explored` from `initial` in `from`?**

No, we can't, as `initial` may represent a partially explored space.

**I'm having a hard time debugging and/or dealing with type errors in `from`.**

Try decomposing your logic through the use of `val`s, perhaps giving your expessions explicit types.

**My solver seems to find solutions, but not the optimal ones.**

Meditate over `from`. How do you ensure that you explore the search space in layers, such that all states achievable in, for example, two moves are explored before the states that take three or more moves to achieve?

**My solution blows up when graded -- what should solution return when no solution is possible?**

An empty `List`, as the comments suggest.

**I am returning just that! But the grader still complains about "head of an empty stream" or somesuch.**

Make sure you actually test your solution on a level that has no solution. (Hint: `from` needs to handle this situation sanely, too.)

Lastly, just hang in there. You're very close to completing this class, which is no small feat, and if you worked through the lectures and previous assignments, you should be totally up to this last climb.
