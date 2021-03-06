package com.stefanmuenchow.collections.immutable;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;

import com.stefanmuenchow.collections.function.BinaryFunction;
import com.stefanmuenchow.collections.function.Predicate;
import com.stefanmuenchow.collections.function.UnaryFunction;

public interface ImmutableCollection<E> {
    public int size();
    public boolean isEmpty();
    public boolean contains(final Object o);
    public Iterator<E> iterator();
    public Object[] toArray();
    public <T> T[] toArray(final T[] a);
    public ImmutableCollection<E> remove(final Object o);
    public boolean containsAll(final Collection<?> c);
    public ImmutableCollection<E> addAll(final Collection<? extends E> c);
    public ImmutableCollection<E> removeAll(final Collection<?> c);
    public ImmutableCollection<E> retainAll(final Collection<?> c);
    public ImmutableCollection<E> clear();

    /**
     * Adds an element to the collection.
     * @param elem Element to add
     * @return Collection with element added
     */
    ImmutableCollection<E> add(E elem);

    /**
     * Seeks a single element based on a predicate. The first element for which
     * the predicate returns <code>true</code>, is returned. If the predicate is
     * <code>false</code> for all elements, an Exception is thrown.
     *
     * @param pred  Predicate
     * @throws NoSuchElementException If no element matches
     * @return Element
     */
    E find(Predicate<E> pred) throws NoSuchElementException;

    /**
     * Retains all elements in the collection for which the predicate is true.
     *
     * @param predicate
     *            Predicate
     * @return Collection containing only elements for which predicate returns
     *         true
     */
    ImmutableCollection<E> filter(Predicate<E> predicate);

    /**
     * Removes all elements in the collection for which the predicate is true.
     *
     * @param predicate
     *            Predicate
     * @return Collection containing only elements for which predicate returns
     *         false
     */
    ImmutableCollection<E> remove(Predicate<E> predicate);

    /**
     * Replaces each occurence of an element in the collection with a
     * replacement. The elements are compared by the equals method. If the
     * element is not found in the collection, nothing is done.
     *
     * @param seek
     *            Element to be replaced
     * @param replacement
     *            Replacement
     * @return List with replaced elements
     */
    ImmutableCollection<E> replace(E seek, E replacement);

    /**
     * Replaces each element in the collection, for which the predicate returns
     * true, with a replacement. The elements are compared by the equals method.
     * If the predicate evaluates to false for all elements, nothing is done.
     *
     * @param predicate
     *            Predicate to identify elements to replace
     * @param replacement
     *            Replacement
     * @return List with replaced elements
     */
    ImmutableCollection<E> replace(Predicate<E> predicate, E replacement);

    /**
     * Calls a given function for each element in the collection with the
     * element as the one and only parameter. Each element is replaced by the
     * return value of the function and the resulting collection is returned.
     * Creates a new Collection.
     *
     * @param function
     *            Unary function
     * @see UnaryFunction
     * @return Altered collection
     */
    <R> ImmutableCollection<R> map(UnaryFunction<R, E> function);

    /**
     * Combines the elements of this list from left to right using a binary
     * function and an initial value. If the collection is empty, the initial
     * value is returned.
     *
     * @param initial
     *            Initial value
     * @param funct
     *            Binary Function
     * @see BinaryFunction
     * @return A single value
     */
    <R> R reduce(R initial, BinaryFunction<R, E> funct);

    /**
     * Combines the elements of this list from left to right using a binary
     * function. If the collection is empty, null is returned.
     *
     * @param funct
     *            Binary Function
     * @see BinaryFunction
     * @return A single value
     */
    E reduce(BinaryFunction<E, E> funct);

    /**
     * Calls the toString() method of each element in the collection and
     * intersperses the resulting strings with delimiter. The complete result is
     * returned.
     *
     * @param delimiter
     *            String that is inserted between each two elements
     * @return Resulting string representation
     */
    String join(String delimiter);

    /**
     * Counts all entries for which the predicate evaluates to true.
     *
     * @param predicate
     *            Predicate
     * @return Number of elements in collection for which predicate is true
     */
    int count(Predicate<E> predicate);

    /**
     * Checks if the predicate evaluates to true for any element in the
     * collection. If not, the result is false.
     *
     * @param pred
     *            Predicate
     * @return true / false
     */
    boolean exists(Predicate<E> pred);

    /**
     * Checks if the predicate evaluates to true for all elements in the
     * collection. If not, the result is false.
     *
     * @param pred
     *            Predicate
     * @return true / false
     */
    boolean forall(Predicate<E> pred);

    /**
     * Replaces each occurence of a key in the specified map with the
     * corresponding value.
     * @param replacements Map including all replacements
     * @return Collection with replaced elements
     */
    ImmutableCollection<E> replace(Map<E, E> replacements);

    /**
     * If the collection contains other collections, then the values of all
     * collections are collected recursively and put into the resulting list. If
     * it is already a "flat" collection, nothing is done. A new collection is
     * created.
     *
     * @return Flat collection not containing any other collection
     */
    ImmutableCollection<Object> flatten();

    /**
     * Casts all elements of the collecion to a specified type.
     * @param <T> Element type of the resulting collection
     * @param clazz Class to which all elements should be casted
     *
     * @throws ClassCastException
     * @return Collection with changed element type
     */
    <T> ImmutableCollection<T> castAllElements(Class<T> clazz);

    /**
     * Converts this collection to an array of the same type.
     * @param clazz Type of the array elements
     * @return Array holding all elements of this collection
     */
    <T> T[] toArray(Class<T> clazz);
}
