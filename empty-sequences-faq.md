A common problem people have with assignments in this class is that certain tasks require us to implement some kind of an operation on collections, which is easy to understand when we're talking about collections with some elements in them, but may appear unintuitive if we generalize to empty collections. Faced with this problem, people often resort to either throwing an exception for empty collections, or returning a result that seems intuitive to them (even though this often needlessly complicates their code). To help with this a little, here's a list of relevant gotchas. Unless explicitly stated otherwise in the assignment handout, it would be advisable to use the interpretations in this document.

**To successfully complete the assignments, you only need to remember the items listed in bold below.** If you're interested in justifications, you're welcome to read the rest of the document.

Here's the general line of reasoning for obtaining the following results. Given a sequence of elements, $$(x_1, x_2, ... x_n)$$ and a binary operation $$+$$ (I will use the plus sign for convenience, but this may be something other than addition) we obtain the result of our function $$f$$ by applying this operation to the elements of the sequence in the following way:

$$x_1 + x_2 + x_3 + ... + x_n$$

For simplicity, we will assume that our operation is associative, that is:

$$a + (b + c) = (a + b) + c$$

And that it has an identity element $$I$$, such that:

$$I + a = a$$

$$a + I = a$$

Given two sequences $$A$$ and $$B$$, and the result of their concatenation $$C = A + B$$, we can conclude by associativity of our operation that:

$$f(C) = f(A) + f(B)$$

Let's assume that $$B$$ is actually an empty list. In that case $$C = A$$ (by properties of list concatenation), and we can conclude that:

$$f(A) = f(A) + f(B)$$

For this to hold in all cases, $$f(B)$$ must be the identity element $$I$$ of our operation $$+$$.

Note that this is at best a sketch of a proof, and would require additional assumptions to be applicable to collections other than sequences.

**1. Sum of an empty list is zero.**

This one is often considered to be reasonably intuitive. Zero, of course, is the additive identity. Informally, you usually expect your order total to be $0.00 before you've put anything into your shopping cart.

**2. Product of an empty list is one.**

Multiplicative identity is 1. This is closely related to other counter-intuitive results from high school math, such as $$2^0 = 1$$ and $$0! = 1$$.

**3. The proposition "for all items belonging to a set $$X$$, proposition $$P$$ holds true" is in itself true for any choice of $$P$$ if $$X$$ is an empty set.**

Known as "vacuous truth", this often appears to be very counter-intuitive, despite the fact that its dual statement:

The proposition "there does not exist an item belonging to a set $$X$$, such that proposition $$P$$ does not hold true for it" is true for any choice of $$P$$ if $$X$$ is an empty set.

...is considered to be very intuitive (obviously, there doesn't exist _anything_ in an empty set that would satisfy _any_ requirement). This duality is known as "extended (or generalized) de Morgan's laws":

http://en.wikipedia.org/wiki/De_Morgan%27s_laws#Extensions

If you think about it a little, the fact that there aren't any bug-eyed Martians who are avid fans of Star Wars also means that all bug-eyed Martians are not avid fans of Star Wars (even though, to the best of our knowledge, there aren't any bug-eyed Martians at all). Of course, the converse is also true: all bug-eyed Martians are avid fans of Star Wars - after all, there aren't any bug-eyed Martians who aren't avid fans of Star Wars.

Surprisingly, this can be seen in real life. If you go looking for hotels on some hotel booking site the results page will usually display a list of checkboxes titled "Hotel Preferences" (or something similar). This can be interpreted as specifying a list of requirements, and the hotels displayed will be such that _for all requirements in the requirement list, the hotel satisfies the requirement_. If you select "Swimming Pool" and "Childcare", only the hotels with both swimming pool and childcare service will be displayed in the search results. However, if you don't click on any checkboxes, the search results won't be empty. That's because all hotels satisfy an empty list of requirements.

Formally, $$\forall x \in X, P(x)$$ means $$P(x_1) \wedge P(x_2) \wedge ... \wedge P(x_n)$$, and truth is the identity of conjunction.

**4. Let's say we want to find the Cartesian product of a list of sets. If the list is empty, the result is not an empty set, but a set containing one element (which would usually be a 0-tuple, or a unit value).**

This is directly related to (2) above and it's more common in daily life than one might think. Let's say you're adamant about buying a car, and you've already decided on the make and model you want. The next step would be choosing options for your car. For the sake of keeping it simple, we shall assume that you can only pick color and engine. The model you want comes in just two colors, red and black, and with two engine options, 1.6L internal combusion and 2.0L diesel. The total number of options you have is a product of two and two, and the complete list of options is: red internal combustion, red diesel, black internal combustion and black diesel.

So far so good.

Now let's try this with Ford Model T, which famously didn't have any options. Does that mean that we have zero options if we have decided to buy a Model T? That would mean there are no possible outcomes whatsoever once we have decided to buy a Model T, so the universe would presumably implode on itself or something like that once we have made such an impossible decision.

Let's try it this way: we know we can order Model T in any color, as long as it's black. Therefore, we have exactly one option: to buy Model T in black. Note, however, that adding the color options to the list doesn't add any real choice - we know our car is going to be in black anyway. We can safely discard that, and that will still leave us with exactly one option - to buy Model T in its default (and only) configuration.

**5. The maximum of an empty list is not well-defined on real numbers.**

The maximum operation does not have an identity on real numbers. For any real number we can find another lesser real number, by subtracting 1 from the original number, for example. Since $$\forall x \in \mathbb{R}, \exists y \in \mathbb{R}, y < x$$, there's no $$x \in \mathbb{R}$$ such that for all $$y \in \mathbb{R}, x < y$$. Negative infinity _could_ serve as an identity, but it's not a member of the set of real numbers, and introducing it to the mix causes problems elsewhere.
