package org.mock.part_4;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

import org.junit.Test;
import org.mockito.ArgumentCaptor;

// static import for methods
import static org.mockito.Mockito.*;

public class MockitoTest {
    // Demo 1:
    // Basic mock generations
    @Test
    public void mockitoGenerationTest() {
        // Generate a mock instance var of the List interface
        List<?> mockedList = mock(List.class); // static method access from Mockito.mock()

        // Ensure mock list instance has been created successfully
        assert null != mockedList;

        // Mock specific subclass impl. of List interface e.g. ArrayList impl.
        ArrayList<?> mockedArrayList = mock(ArrayList.class);

        // Ensure mock list instance has been created successfully
        assert null != mockedArrayList;

        // Specific instances with type parameters example
        List<Integer> integerList = mock(LinkedList.class);

        // Add numbers from 1 to 5 using .addAll(Collection) method
        integerList.addAll(Arrays.asList(1, 2, 3, 4, 5));
    }

    // Demo 2:
    // Basic method return/behavior controls
    @Test(expected = NoSuchElementException.class)
    public void mockitoMethodReturnBehaviorControlTest() throws NoSuchElementException {
        // Generate a mocked iterator instance
        Iterator mockedIterator = mock(Iterator.class);

        // Iterator method .next() should return "Hello!" and after that returns "Mockito!"
        when(mockedIterator.next())       // On .next() method invocation -> return:
                .thenReturn("Hello!")     // 1. Invocation -> returns "Hello!"
                .thenReturn("Mockito!");  // 2. Invocation -> returns "Mockito!"

        // .next() been called two times, expected string in result := "Hello!Mockito!"
        String result = String.format("%s%s", mockedIterator.next(), mockedIterator.next());

        // Validate expected behavior and test result := "Hello!Mockito!"
        assert "Hello!Mockito!".equals(result);

        // Attempt to throw an exception on a third .next() method invocation as the iterator should be at the end
        // doThrow static method access from Mockito.doThrow(Throwable... toBeThrown)
        doThrow(new NoSuchElementException()) // Throw NoSuchElementException if ->
                .when(mockedIterator).next(); // mockedIterator.next() is invoked

        // Test expected behavior: Throws NoSuchElementException as in @Test(expected = NoSuchElementException.class)
        mockedIterator.next();
    }

    // Demo 3:
    // Basic method invocation checks
    @Test
    public void mockitoMethodInvocationChecksTest() {
        List<String> mockedList = mock(List.class);

        mockedList.add("one");
        mockedList.add("two");
        mockedList.add("three times");
        mockedList.add("three times");
        mockedList.add("three times");

        // Set return value of .size() to 3
        when(mockedList.size()) // On invocation of .size() ->
                .thenReturn(3); // Return: 3

        // Validate the return value of .size() == 3
        assert 3 == mockedList.size();

        // Verify .add("one") has been invoked one or more times
        verify(mockedList, atLeastOnce()) // Verifies invocation to a method at least one time
                .add("one");              // Specific parameter is being required for the verification

        // Verify .add("two") has been invoked exactly one time
        // Static method access from Mockito.times(exact number of invocations)
        verify(mockedList, times(1))
                .add("two");

        // Verify .add("three times") has been invoked exactly three times
        verify(mockedList, times(3))
                .add("three times");

        // Verify .isEmpty() has been never invoked
        // Static method access from Mockito.never()
        verify(mockedList, never())
                .isEmpty();
    }

    // Demo 4:
    // Mock-Wrapper behavior
    @Test
    public void mockitoWrapperBehaviorTest() {
        ArrayList<Object> mockedList = mock(ArrayList.class);

        // Create element for the mockedList to add
        final Object objectVar = new Object();

        // Add the created object to the mockedList two times
        mockedList.add(objectVar);
        mockedList.add(objectVar);

        // Verify .add(objectVar) has been invoked two times
        verify(mockedList, times(2))
                .add(objectVar);

        // Default return value for int
        assert 0 == mockedList.size();

        // Default values are being returned
        assert !mockedList.isEmpty();

        // Collection Framework is handled a bit different, e.g. subList returns non-null list object
        // instanceof checks for null and subtype
        assert mockedList
                .subList(
                        ThreadLocalRandom.current().nextInt(),
                        Integer.MIN_VALUE
                ) instanceof List;
    }

    // Demo 5:
    // Mock-Wrapper spy wraps an instance and returns an enclosed new object
    // The new object delegates method calls directly to the underlying true object if no configuration/override is set
    @Test
    public void mockitoSpyWrapperBehaviorTest() {
        List<String> spyList = spy(new LinkedList<>());

        // Override method .size() to return 100 on invocation
        when(spyList.size())
                .thenReturn(100);

        // Add element "one" with .add("one")
        spyList.add("one");

        // Add element "two" with .add("two")
        spyList.add("two");

        // Validate the first element "one" added with .get(0)
        assert "one".equals(spyList.get(0));

        // Validate the second element "two" added with .get(1)
        assert "two".equals(spyList.get(1));

        // Validate overridden .size() method to return 100
        assert 100 == spyList.size();
    }

    // Demo 6:
    // Argument capture example
    @Test
    public void mockitoArgumentsCaptureTest() {
        // Standard ArrayList instance via Arrays.asList(...)
        final List<String> list = Arrays.asList("1", "2");

        // Generate a mock list
        List<String> mockedList = mock(List.class);

        // Generate the argument capture for list instance
        ArgumentCaptor<List<String>> argumentCaptor = ArgumentCaptor.forClass(List.class);

        // Invoke .addAll() on mockedList to add all items of list
        mockedList.addAll(list);

        // Retrieves the items given over .addAll to the argument captor instance
        verify(mockedList).addAll(argumentCaptor.capture());

        // Verify that the argument captor instance has counted two items via .addAll()
        assert 2 == argumentCaptor.getValue().size();
        
        assert argumentCaptor.getValue() == list;

        // Verify each item from the original list created on the first line match the captured items
        // Done via list .equals() - Checking each item if equal
        assert list.equals(argumentCaptor.getValue());

        // Reference check is true
        assert list == argumentCaptor.getValue();
    }

    // Demo 7:
    // Argument capture example for primitives
    @Test
    public void mockitoArgumentsCaptureTestPrimitives() {
        final int sizeNumbers = 100;

        // Some values in an int array
        final int[] someNums = IntStream.rangeClosed(1, sizeNumbers).toArray();

        // Define a local class definition to mock
        // final or anonymous classes can not be mocked (Java stuff)
        // Class object can be private, instance inner class or temporally defined as follows
        class MyClazz {
            // Method modifier must be package-private or public and not final
            // -> private methods are Not accessible by the argumentCaptor! (Java stuff)
            // -> final methods can not be overridden
            void f(int x) {
            }
        }

        // The argumentCaptor for int values
        ArgumentCaptor<Integer> argumentCaptor = ArgumentCaptor.forClass(int.class);

        // Generate a mocked consumer to simulate the functionality
        MyClazz mockedMyClazz = mock(MyClazz.class);

        for(int num : someNums)
            mockedMyClazz.f(num);

        // Retrieves the ints from the instance object which were passed to f.
        verify(mockedMyClazz, times(someNums.length))
                .f(argumentCaptor.capture());

        // Validate the captor's stack of captured values
        // Empties the stack (Side effect)
        for(int num : someNums)
            assert num == argumentCaptor.getAllValues().remove(0);

        // Side effect -> manipulated captor's stack is empty now!
        assert argumentCaptor.getAllValues().isEmpty();
    }
}
