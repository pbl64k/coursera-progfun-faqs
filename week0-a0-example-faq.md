**Does this assignment count towards my final grade?**

No. It works just like a real assignment, with submission to server and full feedback from the grader, but the grade itself will be discarded for the purposes of final grade calculation. The purpose of this assignment is to let you familiarize yourself with the process for solving and submitting programming assignments without any risk of losing points. And I would strongly recommend doing just that - assignment instructions and provided code contain a lot of hints that will be very useful in the weeks to come.

**I downloaded the example assignment project, but it doesn't appear to work, producing cryptic error messages. What am I doing wrong?**

You should complete the definitions of functions `sum` and `max` in the source code. The places you should edit are marked with `???`

**I can't run the project even though I defined `sum` and `max`.**

If you want to be able to run your project instead of just running tests, using Scala Worksheet or using REPL, you should add a `Main` object to your project.

**Where should I run `sbt` to be able to do `import example.Lists._` in console?**

You should run `sbt` from the directory where `build.sbt` extracted from `example.zip` is. The path to this file inside the archive is `example/build.sbt`.

**What is the sum of an empty list?**

It's 0.

**How do I throw an exception?**

`throw new java.util.NoSuchElementException()`

**Should I make my solutions tail-recursive?**

No. Straightforward recursive solutions will suffice.
