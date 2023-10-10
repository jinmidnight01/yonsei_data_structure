/*
 * Name: Jinhyo Park
 * Student ID: 2018121022
 */

/*
 * Do NOT import any additional packages/classes.
 * If you (un)intentionally use some additional packages/classes we did not
 * provide, you may receive a 0 for the homework.
 */

public final class Queue<T> implements IQueue<T> {
    /* 
     * you may declare variables here
     */
    private final LinkedList<T> queue;
    int size;

    Queue(){
        /*
         * implement your constructor here
         */
        queue = new LinkedList<T>();
    }

    @Override
    public void enqueue(T e) {
        /*
         * Function input:
         *  + e: element to enqueue
         *
         * Job:
         * add e to Queue as the last element
         * this method should run in O(1) time.
         */
        queue.insert(queue.getSize(), e);
    }

    @Override
    public T dequeue(){
        /*
         * Function input:
         *  none
         * 
         * Job:
         * remove and return the first element.
         * if queue is empty, return null.
         * this method should run in O(1) time.
         */
        if (queue.getSize() == 0) {
            return null;
        } else {
            T temp = queue.getData(0);
            queue.remove(0);
            return temp;
        }
    }

    
    @Override
    public void clear(){
        /*
         * Function input:
         *  none
         *  
         * Job:
         * clear the queue.
         */
        queue.clear();
    }

    @Override
    public T peek(){
        /*
         * Function input:
         *  none
         *
         * Job:
         * return the first element.
         * do not remove the returned element.
         * if queue is empty, return null.
         * this method should run in O(1) time.
         */
        if (queue.getSize() != 0) {
            return queue.getData(0);
        }
        return null;
    }

    @Override
    public int getSize(){
        /*
         * Function input:
         *  none
         * 
         * Job:
         * return the number of elements in the queue.
         * this method should run in O(1) time.
         */
        return queue.getSize();
    }

    @Override
    public boolean isEmpty(){
        /*
         * Function input:
         *  none
         * 
         * Job:
         * return true if there are no elements left in the Queue.
         * return false otherwise.
         * this method should run in O(1) time.
         */
        if (queue.getSize() != 0) {
            return false;
        };
        return true;
    }
}
