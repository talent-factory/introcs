package edu.princeton.cs._43stack;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testklasse für die {@link Queue} Implementierung.
 * 
 * Diese Klasse testet alle Funktionalitäten der Queue-Klasse, einschließlich:
 * - Initialisierung einer leeren Queue
 * - Hinzufügen von Elementen (enqueue)
 * - Entfernen von Elementen (dequeue)
 * - Überprüfen des ersten Elements (peek)
 * - Überprüfen, ob die Queue leer ist (isEmpty)
 * - Abfragen der Größe der Queue (size/length)
 * - String-Repräsentation (toString)
 * - Iterator-Funktionalität
 * - Fehlerbehandlung
 */
@DisplayName("Queue-Tests")
class QueueTest {

    private Queue<String> queue;
    private Queue<Integer> intQueue;

    @BeforeEach
    void setUp() {
        queue = new Queue<>();
        intQueue = new Queue<>();
    }

    @Nested
    @DisplayName("Grundlegende Operationen")
    class BasicOperations {

        @Test
        @DisplayName("Neue Queue sollte leer sein")
        void newQueueShouldBeEmpty() {
            assertTrue(queue.isEmpty());
            assertEquals(0, queue.size());
            assertEquals(0, queue.length());
        }

        @Test
        @DisplayName("Enqueue sollte Elemente korrekt hinzufügen")
        void enqueueShouldAddElements() {
            queue.enqueue("Element 1");
            assertEquals(1, queue.size());
            assertFalse(queue.isEmpty());

            queue.enqueue("Element 2");
            assertEquals(2, queue.size());
        }

        @Test
        @DisplayName("Dequeue sollte Elemente in FIFO-Reihenfolge entfernen")
        void dequeueShouldRemoveElementsInFifoOrder() {
            queue.enqueue("Element 1");
            queue.enqueue("Element 2");
            queue.enqueue("Element 3");

            assertEquals("Element 1", queue.dequeue());
            assertEquals(2, queue.size());

            assertEquals("Element 2", queue.dequeue());
            assertEquals(1, queue.size());

            assertEquals("Element 3", queue.dequeue());
            assertEquals(0, queue.size());
            assertTrue(queue.isEmpty());
        }

        @Test
        @DisplayName("Peek sollte das erste Element zurückgeben ohne es zu entfernen")
        void peekShouldReturnFirstElementWithoutRemoving() {
            queue.enqueue("Element 1");
            queue.enqueue("Element 2");

            assertEquals("Element 1", queue.peek());
            assertEquals(2, queue.size()); // Größe bleibt unverändert

            assertEquals("Element 1", queue.peek()); // Wiederholtes Peek gibt dasselbe Element zurück
        }
    }

    @Nested
    @DisplayName("Fehlerbehandlung")
    class ErrorHandling {

        @Test
        @DisplayName("Dequeue auf leerer Queue sollte NoSuchElementException werfen")
        void dequeueShouldThrowExceptionOnEmptyQueue() {
            assertThrows(NoSuchElementException.class, () -> queue.dequeue());
        }

        @Test
        @DisplayName("Peek auf leerer Queue sollte NoSuchElementException werfen")
        void peekShouldThrowExceptionOnEmptyQueue() {
            assertThrows(NoSuchElementException.class, () -> queue.peek());
        }
    }

    @Nested
    @DisplayName("Iterator-Tests")
    class IteratorTests {

        @Test
        @DisplayName("Iterator sollte Elemente in FIFO-Reihenfolge durchlaufen")
        void iteratorShouldTraverseElementsInFifoOrder() {
            queue.enqueue("Element 1");
            queue.enqueue("Element 2");
            queue.enqueue("Element 3");

            Iterator<String> iterator = queue.iterator();
            assertTrue(iterator.hasNext());
            assertEquals("Element 1", iterator.next());
            
            assertTrue(iterator.hasNext());
            assertEquals("Element 2", iterator.next());
            
            assertTrue(iterator.hasNext());
            assertEquals("Element 3", iterator.next());
            
            assertFalse(iterator.hasNext());
        }

        @Test
        @DisplayName("Iterator sollte NoSuchElementException werfen, wenn keine Elemente mehr vorhanden sind")
        void iteratorShouldThrowExceptionWhenNoMoreElements() {
            queue.enqueue("Element 1");
            Iterator<String> iterator = queue.iterator();
            
            iterator.next(); // Erstes Element abrufen
            assertFalse(iterator.hasNext());
            assertThrows(NoSuchElementException.class, iterator::next);
        }

        @Test
        @DisplayName("Iterator.remove() sollte UnsupportedOperationException werfen")
        void iteratorRemoveShouldThrowUnsupportedOperationException() {
            queue.enqueue("Element 1");
            Iterator<String> iterator = queue.iterator();
            
            assertThrows(UnsupportedOperationException.class, iterator::remove);
        }
    }

    @Nested
    @DisplayName("String-Repräsentation")
    class StringRepresentation {

        @Test
        @DisplayName("toString sollte korrekte String-Repräsentation zurückgeben")
        void toStringShouldReturnCorrectRepresentation() {
            queue.enqueue("Element 1");
            queue.enqueue("Element 2");
            queue.enqueue("Element 3");
            
            assertEquals("Element 1 Element 2 Element 3 ", queue.toString());
        }

        @Test
        @DisplayName("toString sollte leeren String für leere Queue zurückgeben")
        void toStringShouldReturnEmptyStringForEmptyQueue() {
            assertEquals("", queue.toString());
        }
    }

    @Nested
    @DisplayName("Komplexe Szenarien")
    class ComplexScenarios {

        @Test
        @DisplayName("Queue sollte mit verschiedenen Datentypen funktionieren")
        void queueShouldWorkWithDifferentDataTypes() {
            // Integer-Queue testen
            intQueue.enqueue(1);
            intQueue.enqueue(2);
            intQueue.enqueue(3);
            
            assertEquals(3, intQueue.size());
            assertEquals(1, intQueue.peek());
            assertEquals(1, intQueue.dequeue());
            assertEquals(2, intQueue.dequeue());
            assertEquals(1, intQueue.size());
        }

        @Test
        @DisplayName("Queue sollte nach Entleerung wiederverwendbar sein")
        void queueShouldBeReusableAfterEmptying() {
            queue.enqueue("Element 1");
            queue.enqueue("Element 2");
            
            queue.dequeue();
            queue.dequeue();
            assertTrue(queue.isEmpty());
            
            // Queue wiederverwenden
            queue.enqueue("Neues Element 1");
            queue.enqueue("Neues Element 2");
            
            assertEquals(2, queue.size());
            assertEquals("Neues Element 1", queue.peek());
        }

        @Test
        @DisplayName("Enqueue und Dequeue sollten in gemischter Reihenfolge korrekt funktionieren")
        void enqueueDequeueShouldWorkInMixedOrder() {
            queue.enqueue("Element 1");
            queue.enqueue("Element 2");
            
            assertEquals("Element 1", queue.dequeue());
            
            queue.enqueue("Element 3");
            queue.enqueue("Element 4");
            
            assertEquals("Element 2", queue.dequeue());
            assertEquals("Element 3", queue.dequeue());
            
            queue.enqueue("Element 5");
            
            assertEquals("Element 4", queue.dequeue());
            assertEquals("Element 5", queue.dequeue());
            
            assertTrue(queue.isEmpty());
        }
    }

    @Nested
    @DisplayName("Leistungstests")
    class PerformanceTests {

        @Test
        @DisplayName("Queue sollte mit großen Datenmengen umgehen können")
        void queueShouldHandleLargeAmountOfData() {
            final int ELEMENTS = 10000;
            
            // Viele Elemente hinzufügen
            for (int i = 0; i < ELEMENTS; i++) {
                intQueue.enqueue(i);
            }
            
            assertEquals(ELEMENTS, intQueue.size());
            
            // Alle Elemente entfernen und Reihenfolge prüfen
            for (int i = 0; i < ELEMENTS; i++) {
                assertEquals(i, intQueue.dequeue());
            }
            
            assertTrue(intQueue.isEmpty());
        }
    }
}
