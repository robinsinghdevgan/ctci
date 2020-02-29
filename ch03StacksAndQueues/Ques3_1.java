public class Ques3_1 {

    static class ThreeInOneStack{

        static class MyStack {
            Integer min, max, top;
            boolean empty;
            MyStack(final Integer min, final Integer max, final Integer top) {
                this.min = min;
                this.max = max;
                this.top = top;
                empty = true;
            }

            boolean isEmpty() {
                if (top == null)
                    return true;
                return false;
            }

            private boolean isFull() {
                if (top >= max) {
                    return true;
                }
                return false;
            }

            Integer getPushIndex (Integer data) throws StackOverflowError {
                if(isEmpty()) {
                    top = min;
                }
                else if(isFull()) {
                    throw new StackOverflowError();
                }
                else {
                    ++top;
                }
                return top;
            }

            public Integer getPopIndex() {
                if(isEmpty()) {
                    System.err.println("Stack is empty.");
                    return null;
                }
                Integer topToReturn = top;
                --top;
                if (topToReturn <= min) {
                    top = null;
                    return min;
                }
                return topToReturn;
            }
        }

        Integer[] memory;
        MyStack[] stacks = new MyStack[3];

        public ThreeInOneStack(final int sizeOfEachStack) {
            memory = new Integer[sizeOfEachStack*3];
            stacks[0] = new MyStack(0, sizeOfEachStack - 1, null);
            stacks[1] = new MyStack(sizeOfEachStack, sizeOfEachStack*2 - 1, null);
            stacks[2] = new MyStack(sizeOfEachStack*2, sizeOfEachStack*3 - 1, null);
        }

        public void push(final short stackNumberZeroOneOrTwo, final Integer data) throws StackOverflowError {
            if ((stackNumberZeroOneOrTwo < 0) || (stackNumberZeroOneOrTwo > 2)) {
                System.err.println("Wrong stackNumber provided");
                return;
            }
            MyStack s = stacks[stackNumberZeroOneOrTwo];
            memory[s.getPushIndex(data)] = data;
        }

        public Integer pop(final short stackNumberZeroOneOrTwo) {
            if ((stackNumberZeroOneOrTwo < 0) || (stackNumberZeroOneOrTwo > 2)) {
                System.err.println("Wrong stackNumber provided");
                return null;
            }
            MyStack s = stacks[stackNumberZeroOneOrTwo];
            if (!s.isEmpty()) {
                Integer popIndex = s.getPopIndex();
                Integer valueAtPopIndex = memory[popIndex];
                memory[popIndex] = null;
                return valueAtPopIndex;
            }
            System.err.println("Stack: " + stackNumberZeroOneOrTwo + " is empty.");
            return null;
        }

        public Integer peek(final short stackNumberZeroOneOrTwo) {
            return memory[stacks[stackNumberZeroOneOrTwo].top];
        }

        public boolean isEmpty(final short stackNumberZeroOneOrTwo) {
            return stacks[stackNumberZeroOneOrTwo].isEmpty();
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            for (int i = 0, size = memory.length; i < size; ++i) {
                sb.append(memory[i] + " ");
            }
            return super.toString() + " : " + sb.toString();
        }
    }

    public static void main(final String[] args) {
        ThreeInOneStack t = new ThreeInOneStack(5);

        
        for (short i = 0; i < 3; ++i)
            System.out.println(t.isEmpty(i));

        for (short i = 0; i < 3; ++i) {
            for (int j = 0; j < 5 ; ++j) {
                t.push(i, j);
            }
        }
        for (short i = 0; i < 3; ++i)
            System.out.println(t.isEmpty(i));

        System.out.println(t);
        for (short i = 0; i < 3; ++i)
            System.out.println(t.peek(i));

        for (short i = 0; i < 3; ++i) {
            for (int j = 0; j < 6 ; ++j) {
                System.out.println("stack: " + i + " popped: " + t.pop(i));
            }
        }
        System.out.println(t);

    }

    
}