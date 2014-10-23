What is this?
=============

This is a bare-bones Spring 4.1 example demonstrating how @Autowired works when the dependency is explicitly wired.

How does @Autowired work when the dependency is explicitly wired, anyway?
=========

So glad you asked.

If you annotate a method as @Autowired

and you have the requisite application of aspects happening


then Spring will attempt to fulfill that dependency from available beans, by default matching by type, 
and failing if an ambiguity arises.

However, if you explicitly fulfill the dependency


then `@Autowired` has no effect.

In particular, in this case, Spring **doesn't** attempt to auto-wire the dependency by type and then also 
subsequently explicitly fulfill the dependency.

Why does this matter?
--------------

This nuance to how `@Autowired` works is key to being able to configure your way out of situations where `@Autowired`
 would have failed for ambiguity where that ambiguity is resolved by your explicit configuration.
 
How does this example code demonstrate this
===========================================

The example involves a `HairSplitter` object that depends upon a `Knife`.

```
public class HairSplitter {
    ...

    @Autowired
    public void setKnife( Knife aKnife ) {
        this.knife = aKnife;
    }
}
```

and an `applicationContext.xml` that defines two `Knife` instances 
(such that autowiring by type would fail for ambiguity).

```
<beans ... ">

    <context:annotation-config />

    <bean id="barberKnife" class="io.ghost.apetro.example.BarberKnife" />

    <bean id="butterKnife" class="io.ghost.apetro.example.ButterKnife" />

    <bean id="hairSplitter" class="io.ghost.apetro.example.HairSplitter" >
      <property name="knife" ref="barberKnife" />
    </bean>

</beans>
```

Since the `applicationContext.xml` explicitly declares how to fulfill `HairSplitter`'s dependency on `Knife`, 
`@Autowired` doesn't fail for ambiguity and instead that `hairSplitter.setKnife()` is called just once, 
with the `barberKnife`.

That `setKnife()` is called just once and not twice is observable from what the test method prints to the console

```
setKnife(io.ghost.apetro.example.BarberKnife@7a096dab)
```

and more convincingly by the assertion on a counter method in that test:

```
public class DemonstrateOverridingAutowireTest {

    /**
     * This is the demonstration.
     */
    @Test
    public void testOverridingAutowire() {

        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        HairSplitter hairSplitter = (HairSplitter) context.getBean("hairSplitter");

        // Even though setKnife() is annotated as Autowired, only the explicit injection from the applicationContext.xml
        // file will fire, so the setter method will have been called only once.
        assertEquals(1, hairSplitter.getNumberOfTimesSetKnifeCalled());
    }


```

How do I run this code myself?
==============================

Option 1: use your IDE

Use your IDE of choice to run the single JUnit `@Test` in `io.ghost.apetro.example.DemonstrateOverridingAutowireTest`.

Option 2: use Maven

```
     mvn test
```
