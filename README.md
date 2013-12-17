# What is this?

This is a teaching example that shows

* the different appropriate levels of unit and integration testing with Spring MVC
* proper use of test doubles (stubs and mocks) as well as ArgumentCaptor


# Getting started

First

    gradle idea

then **open** project (not _import)._

If you run `hello.Application`, you will have a tiny app running locally:
<http://localhost:8080/greet?name=Alex>


# Different levels of testing

* Pure unit tests: `GreetingControllerTest` & `GreetingControllerForDictatorTest`
* `GreetingControllerSpringTest` tests stuff that cannot be sensibly tested at unit level because Spring annotations are used.
* `ConfigurationTest` again is used to test an even larger unit, namely the whole spring app, in order to make sure that everything is wired together correctly (e.g. all the beans can be found).

Note how the tests for the larger units only address aspects that cannot be tested at a more unit-y level.
