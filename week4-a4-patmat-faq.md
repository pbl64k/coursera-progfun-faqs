**May I use `List` methods from the standard library in this assignment?**

You could give it a shot, but you would be at grader's mercy. None of the `List` methods are needed for this assignment (including `sortBy` / `sortWith` - Prof. Odersky demonstrated a simple implementation in the lectures, though a very practical mergesort implementation wouldn't be hard to put together either), and solutions based on pattern matching will be pretty concise.

**Does that include `map`, `flatMap`, (insert the name of the method you wanted to use), `head`, `tail`?**

Yes. This assignment is all about pattern matching. List constructors and pattern matching on lists are entirely sufficient here, though there's probably no harm in occisionally using `head` or somesuch if it looks like it's a better approach.

**Should I make my solutions tail-recursive?**

`until` and `codeBits` are two functions where it would make sense (because it's a very natural way to express those two functions). Other than that, no.

**Er... how do I access left and right sub-trees of a tree node?**

Pattern match on the `Fork` constructor.

**I'm stuck on implementing `times` using just pattern matching.**

You may want to write a function that takes a character and a list of frequencies, and yields a list of frequencies with the frequency of given character increased by one. What does this function need to return when the initial list of frequencies of empty? What does this function need to return when the head of the list of frequencies is the same character as the one passed as an argument? What if it's a different character?

This is the usual approach to recursive functions: identify the base case and the recursive case.

**Is empty list a singleton?**

No.

**Should `combine` process the list recursively until only a singleton is left?**

No. `combine` performs one iteration of that process, decreasing the list size by one on the outside. The rest is the job for `createCodeTree`.

**Should `combine` try to repair invalid inputs, such as duplicate characters in the trees to be combined?**

No. You may assume valid input.

**May I throw an exception if `combine` is passed a `Nil`?**

No. Assignment instructions are unambiguous here.

**`until` looks like an imperative loop. How do I implement something like that in a functional fashion?**

Iteration is equivalent to tail-recursion. You might want to take a look at the FAQ for recfun.

**Do I really need to use `mergeCodeTables` in my implementation of `convert`?**

Not really. The grader won't explicitly check for dependencies here. Using a certain approach to implementing `mergeCodeTables` will lead to a particularly elegant and succint version of `convert`, but that's not to say there cannot be elegant and succint implementations of `convert` that take a different approach.

**When I'm traversing the code tree using a helper function, I have to pass an extra argument that is completely useless until I need it for a recursive call at the very end of the traversal. That kinda looks ugly.**

Note that you helper function closes on everything in the outer function's scope... including its arguments.

**Grader says my encoding is not minimal.**

`combine` relies on a certain list invariant (described in the handout) for picking a pair of trees with the lowest weight. It's rather easy to overlook that `combine` also needs to *maintain* said invariant. If you fail to do so, your encoding will be sub-optimal.

**Grader says my encoding is impossibly efficient.**

There are great many ways to get this wrong. You should start looking for bugs by checking that the composition of your `encode` and `decode` is an identity (that is, you get your plaintext back by encoding then decoding).

**Why would `someText` need 1919 bits to encode?!**

That's just the name of the variable containing the plaintext to be encoded on the grader side.

**What does it mean for an encoding to be optimal?**

The short answer is, you don't need to worry about this, if you precisely follow the assignment instructions your encoding *will* be optimal.

A longer answer is, proper Huffman encoding is optimal in the following sense: if we assume that our sample string is representative of the true underlying distribution of character frequencies, and that individual characters/symbols are independent and identically distributed, then Huffman code for sufficiently long strings drawn from said distribution will minimize the expected length of codetext among all possible lossless encodings for said distribution. If you're interested in this topic, Wikipedia would be a decent start for further reading:

http://en.wikipedia.org/wiki/Huffman_coding

**There are many possible optimal trees. Which one should I pick?**

The provided test for `combine` is an implicit specification of how exactly you should construct your tree. The same test will be run on the server, so make sure your implementation passes it.

**What should I do if the sample text only has one unique character / no characters at all in it?**

Feel free to blow up the grader's fridge. Huffman code is not particularly well-defined for these cases.

**`CodeTable` is a very inefficient representation with awful lookups. Should I redefine it?**

No. This is really just for demonstration purposes, so you should leave it be, despite its inefficiency.
