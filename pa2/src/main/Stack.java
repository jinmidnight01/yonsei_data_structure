/*
 * Name: Jinhyo Park
 * Student ID: 2018121022
 */

/*
 * Do NOT import any additional packages/classes.
 * If you (un)intentionally use some additional packages/classes we did not
 * provide, you may receive a 0 for the homework.
 */

public final class Stack<T> implements IStack<T> {
    /*
     * you may declare variables here
     */
    private final LinkedList<T> stack;

    Stack() {
        /*
         * implement your constructor here
         */
        stack = new LinkedList<T>();
    }

    @Override
    public void push(T e){
        /*
         * Function input:
         *  + e: element to push
         *  
         * Job:
         * add e to Stack as the top element
         */
        stack.insert(0, e);
    }

    @Override
    public T pop(){
        /*
         * Function input:
         *  none
         *
         * Job:
         * remove and return the top element.
         * if stack is empty, return null.
         */
        if (stack.getSize() == 0) {
            return null;
        } else {
            T temp = stack.getData(0);
            stack.remove(0);
            return temp;
        }
    }

    @Override
    public T peek(){
        /*
         * Function input:
         *  none
         * 
         * Job:
         * return the top element.
         * do not remove the returned element.
         * if stack is empty, return null.
         */
        if (stack.getSize() != 0) {
            return stack.getData(0);
        }
        return null;
    }

    @Override
    public void clear(){
        /*
         * Function input:
         *  none
         *  
         * Job:
         * clear the stack.
         */
        stack.clear();
    }

    @Override
    public int getSize(){
        /*
         * Function input:
         *  none
         * 
         * Job:
         * return the number of elements in the stack.
         * this operation should run in O(1) time.
         */
        return stack.getSize();
    }

    @Override
    public boolean isEmpty(){
        /*
         * Function input:
         *  none
         * Job:
         * return true if there are no elements left in the Queue.
         * return false otherwise.
         */
        if (stack.getSize() != 0) {
            return false;
        };
        return true;
    }
}
