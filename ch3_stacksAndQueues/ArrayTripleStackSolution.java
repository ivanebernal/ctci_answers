import java.lang.Exception;
import java.lang.reflect.Array;

class ArrayTripleStackSolution {
    public static void main(String[] args) {
        ArrayTripleStack<Integer> tripleStack = new ArrayTripleStack(Integer.class);
        for(int i = 1; i < 12; i ++) {
            tripleStack.add(0, i);
        }
        for(int i = 1; i < 12; i ++) {
            tripleStack.add(1, i);
        }
        for(int i = 1; i < 13; i ++) {
            tripleStack.add(2, i);
        }
        System.out.println("----------------Initial Stacks----------------");
        System.out.println(tripleStack.toString());
        try {
            System.out.println("----------------Pops----------------");
            System.out.println(tripleStack.pop(0));
            System.out.println(tripleStack.pop(0));
            System.out.println(tripleStack.pop(1));
            System.out.println(tripleStack.pop(1));
            System.out.println(tripleStack.pop(2));
            System.out.println(tripleStack.pop(2));
            System.out.println("----------------Peeks----------------");
            System.out.println(tripleStack.peek(0));
            System.out.println(tripleStack.peek(1));
            System.out.println(tripleStack.peek(2));
        } catch (StackEmptyException e) {
            //TODO: handle exception
        }
        System.out.println("----------------Final Stacks----------------");
        System.out.println(tripleStack.toString());
        
        

    }
}

class ArrayTripleStack<T> {
    T[] stacks;
    int[] sizes;
    int[] starts;
    Class<T> klass;

    public ArrayTripleStack(Class<T> c) {
        klass = c;
        final T[] a = (T[]) Array.newInstance(c, 30);
        stacks = a;
        sizes = new int[3];
        starts = new int[3];
        sizes[0] = 10;
        sizes[1] = 10;
        sizes[2] = 10;
        starts[0] = 9;
        starts[1] = 19;
        starts[2] = 29;
    }

    void add(int stack, T data) {
        int lastSpace = getLastSpace(stack);
        if(starts[stack] == lastSpace) {
            duplicateSize(stack);
        }
        if(stacks[starts[stack]] != null) starts[stack]--;
        stacks[starts[stack]] = data;
    }

    int getLastSpace(int stack) {
        int result = 0;
        int currStack = 0;
        while(currStack < stack) {
            result += sizes[currStack];
            currStack++;
        }
        return result;
    }

    int getFirstSpace(int stack) {
        int result = 0;
        int currStack = 0;
        while(currStack <= stack) {
            result += sizes[currStack] - 1;
            currStack++;
        }
        // System.out.println("First space of " + stack + ": " + result);
        return result;
    }

    T pop(int stack) throws StackEmptyException {
        if(stacks[getFirstSpace(stack)] == null) throw new StackEmptyException();
        T result = stacks[starts[stack]];
        stacks[starts[stack]] = null;
        if(starts[stack] < getFirstSpace(stack)) starts[stack]++;
        return result;
    }

    T peek(int stack) throws StackEmptyException {
        if(stacks[starts[stack]] == null) throw new StackEmptyException();
        return stacks[starts[stack]];
    }

    boolean isEmpty(int stack) {
        return starts[stack] == sizes[stack] - 1 && stacks[starts[stack]] == null;
    }

    private void duplicateSize(int stack) {
        T[] data = stacks;
        int currSize = sizes[stack];
        sizes[stack] = currSize*2;
        int newStackSize = sizes[0] + sizes[1] + sizes[2]; //TODO: to scale this, sum in a loop
        final T[] a = (T[]) Array.newInstance(klass, newStackSize);
        stacks = a;
        copyFromOldStacks(stack, data);
    }

    private void copyFromOldStacks(int stack, T[] oldData){
        int offset = 0;
        int oldDataOffset = 0;
        for(int i = 0; i < sizes.length; i++) {
            for(int j = 0; j < sizes[i]; j++) {
                if(stack == i && j < sizes[i]/2) {
                    stacks[j + offset] = null;
                } else if(stack == i && j >= sizes[i]/2) {
                    stacks[j + offset] = oldData[j + offset - sizes[i]/2];
                    oldDataOffset = sizes[i]/2;
                } else {
                    stacks[j+offset] = oldData[j + offset - oldDataOffset];
                }
                
            }
            offset += sizes[i];
            starts[i] += oldDataOffset; 
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        int offset = 0;
        for(int i = 0; i < sizes.length; i++) {
            for(int j = 0; j<sizes[i]; j++) {
                builder.append(stacks[j + offset] + ", ");
            }
            builder.append("\n");
            offset += sizes[i];
        }
        return builder.toString();
    }
}

class StackEmptyException extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = 1234567890L;

}