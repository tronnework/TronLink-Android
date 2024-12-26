package com.tron.wallet.business.tabdapp.browser;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
public class ObserverList<E> implements Iterable<E> {
    static final boolean $assertionsDisabled = false;
    private int mCount;
    private int mIterationDepth;
    private boolean mNeedsCompact;
    public final List<E> mObservers = new ArrayList();

    public interface RewindableIterator<E> extends Iterator<E> {
        void rewind();
    }

    public void incrementIterationDepth() {
        this.mIterationDepth++;
    }

    public boolean isEmpty() {
        return this.mCount == 0;
    }

    public int size() {
        return this.mCount;
    }

    public boolean addObserver(E e) {
        if (e == null || this.mObservers.contains(e)) {
            return false;
        }
        this.mObservers.add(e);
        this.mCount++;
        return true;
    }

    public boolean removeObserver(E e) {
        int indexOf;
        if (e == null || (indexOf = this.mObservers.indexOf(e)) == -1) {
            return false;
        }
        if (this.mIterationDepth == 0) {
            this.mObservers.remove(indexOf);
        } else {
            this.mNeedsCompact = true;
            this.mObservers.set(indexOf, null);
        }
        this.mCount--;
        return true;
    }

    public boolean hasObserver(E e) {
        return this.mObservers.contains(e);
    }

    public void clear() {
        this.mCount = 0;
        if (this.mIterationDepth == 0) {
            this.mObservers.clear();
            return;
        }
        int size = this.mObservers.size();
        this.mNeedsCompact |= size != 0;
        for (int i = 0; i < size; i++) {
            this.mObservers.set(i, null);
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new ObserverListIterator();
    }

    public RewindableIterator<E> rewindableIterator() {
        return new ObserverListIterator();
    }

    private void compact() {
        for (int size = this.mObservers.size() - 1; size >= 0; size--) {
            if (this.mObservers.get(size) == null) {
                this.mObservers.remove(size);
            }
        }
    }

    public void decrementIterationDepthAndCompactIfNeeded() {
        int i = this.mIterationDepth - 1;
        this.mIterationDepth = i;
        if (i <= 0 && this.mNeedsCompact) {
            this.mNeedsCompact = false;
            compact();
        }
    }

    public int capacity() {
        return this.mObservers.size();
    }

    public E getObserverAt(int i) {
        return this.mObservers.get(i);
    }

    private class ObserverListIterator implements RewindableIterator<E> {
        private int mIndex;
        private boolean mIsExhausted;
        private int mListEndMarker;

        private ObserverListIterator() {
            incrementIterationDepth();
            this.mListEndMarker = capacity();
        }

        @Override
        public void rewind() {
            compactListIfNeeded();
            incrementIterationDepth();
            this.mListEndMarker = capacity();
            this.mIsExhausted = false;
            this.mIndex = 0;
        }

        @Override
        public boolean hasNext() {
            int i = this.mIndex;
            while (i < this.mListEndMarker && getObserverAt(i) == null) {
                i++;
            }
            if (i < this.mListEndMarker) {
                return true;
            }
            compactListIfNeeded();
            return false;
        }

        @Override
        public E next() {
            while (true) {
                int i = this.mIndex;
                if (i >= this.mListEndMarker || getObserverAt(i) != null) {
                    break;
                }
                this.mIndex++;
            }
            int i2 = this.mIndex;
            if (i2 < this.mListEndMarker) {
                ObserverList observerList = ObserverList.this;
                this.mIndex = i2 + 1;
                return (E) observerList.getObserverAt(i2);
            }
            compactListIfNeeded();
            throw new NoSuchElementException();
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

        private void compactListIfNeeded() {
            if (this.mIsExhausted) {
                return;
            }
            this.mIsExhausted = true;
            decrementIterationDepthAndCompactIfNeeded();
        }
    }
}
