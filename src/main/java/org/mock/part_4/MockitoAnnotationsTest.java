package org.mock.part_4;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class) // Enable the special annotations of Mockito e.g. @Spy, @Mock, etc..
public class MockitoAnnotationsTest {
    /* Enable the special annotations programmatically (instead of class annotation) e.g. @Spy, @Mock, etc..
       @Before
       public void init() {
           MockitoAnnotations.initMocks(this);
       }
    */

    // Auto-Generate a mock List instance by @Mock annotation
    // Auto initialized
    @Mock
    List<String> mockedList;
    @Mock
    List<Object> captorMockedList;

    // Auto-Generate a spy List instance by @Spy annotation
    // Must provide true instance -> initialize the object
    @Spy
    List<String> spiedList = new ArrayList<String>();

    // Auto-Generate an argument captor instance by @Captor
    // Auto initialized
    @Captor
    ArgumentCaptor argCaptor;

    // Mock object used to inject to injectMockedFancyThing
    @Mock
    List<Object> toInjectList;

    // Auto-Generated InjectMock object
    @InjectMocks
    FancyThing injectMockedFancyThing = new FancyThing();

    // Demo 1:
    // Mock is injected when using @Mock annotation
    @Test
    public void mockedListAnnotationTest() {
        this.mockedList.add("one");
        verify(this.mockedList).add("one");
        assert 0 ==  this.mockedList.size();

        when(this.mockedList.size()).thenReturn(100);
        assert 100 == this.mockedList.size();
    }

    // Demo 2:
    // Spy is injected when using @Spy annotation
    @Test
    public void spiedListAnnotationTest() {
        this.spiedList.add("one");
        this.spiedList.add("two");

        verify(this.spiedList).add("one");
        verify(this.spiedList).add("two");

        assert 2 == this.spiedList.size();

        // Return 100 on .size() invocation
        // The .size() method is overridden/configured
        doReturn(100)
                .when(this.spiedList).size();

        // Verify the behavior of the overridden/configured method
        assert 100 == this.spiedList.size();
    }

    // Demo 3:
    // ArgumentCaptor is injected when using @Spy annotation
    @Test
    public void captorMockedListAnnotationTest() {
        final Object objectVar = new Object();

        this.captorMockedList.add(objectVar);
        verify(this.captorMockedList).add(this.argCaptor.capture());

        assert objectVar.equals(this.argCaptor.getValue()); // Applies reference equality if not defined
        assert objectVar == this.argCaptor.getValue(); // Reference equality is true
    }

    // Demo 4:
    // Mocks injector
    private static final class FancyThing {
        // Fields to be injected must not be declared final nor static
        // Name of field must be identical to mocked object name to inject
        // Mockito will try to inject mocks only either by constructor injection,
        // setter injection, or field injection
        // Mockito does not report if any dependency injection strategy fails
        // For constructor injection the biggest constructor is chosen and null will be passed as argument
        // for dependencies that are neither mocks nor spies
        private List<Object> toInjectList = null;
    }

    // Actual test of Demo 4
    @Test
    public void injectMockedFancyThingTest() {
        // Must be instance and so that not null
        assert null != this.injectMockedFancyThing;

        // Verify whether the field of class instance toInjectList has been injected and so that not null
        assert null != this.injectMockedFancyThing.toInjectList;
    }
}
