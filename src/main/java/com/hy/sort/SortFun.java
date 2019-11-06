package com.hy.sort;

import java.util.Stack;

/**
 * Created by huangyong on 2017/6/5.
 * 各种基本排序算法
 */
class SortFun {
    /**
     * 冒泡排序(外层循环为冒泡循环的次数，内层循环为每一趟排序过程)
     * 由于每一趟冒泡排序都会一次将该趟的最大元素放到序列最后面，
     * 因此下一趟排序的序列长度为上一趟排序的序列长度减去1，以此类推
     *
     * @param arr 待排序数组
     */
    static void bubbleSort(int[] arr) {
        System.out.println("冒泡排序：");
        int size = arr.length;
        boolean isSorted;//控制冒泡的趟数，防止无谓的循环
        for (int i = 0; i < size; i++) {
            isSorted = false;
            for (int j = 0; j < size - 1 - i; j++) {//冒泡排序是比较两个相邻的元素，因此j的值需要在size-i的基础上再减1，防止下标溢出
                if (arr[j] > arr[j + 1]) {
                    swapReference(arr, j, j + 1);
                    isSorted = true;
                }
            }
            //如果序列已经有序（即内存循环的冒泡排序过程未发生元素交换，说明待排序列是有序的），结束外层循环
            if (!isSorted) {
                break;
            }
        }
    }

    /**
     * 选择排序(在要排序的一组数中，选出最小的一个数与第一个位置的数交换；
     * 然后在剩下的数当中再找最小的与第二个位置的数交换，
     * 如此循环到倒数第二个数和最后一个数比较为止。)
     * 1、首先选择第一个元素，将其视为临时最小元素temp
     * 2、从下一个元素开始遍历，查找最小元素min
     * 3、若min比temp小，交换min和temp的位置
     * 4、从第二个元素开始，依次执行1-3的操作
     *
     * @param arr 待排序数组
     */
    static void selectSort(int[] arr) {
        System.out.println("选择排序：");
        int size = arr.length;
        int min;//设置标识位
        for (int i = 0; i < size; i++) {
            min = i;
            //查找小于arr[min]的数组元素，若找到，设置min新的下标
            for (int j = i + 1; j < size; j++) {
                if (arr[j] < arr[min]) {
                    min = j;
                }
            }
            swapReference(arr, i, min);
        }
    }

    /**
     * 选择排序的另一种写法（不如上面的效率高）
     * 该种排序方式是只要找到一个元素的值比临时最小元素小就互换其位置
     *
     * @param arr 待排序数组
     */
    static void selectSort1(int[] arr) {
        System.out.println("选择排序另一种方式：");
        int size = arr.length;
        for (int i = 0; i < size; i++) {
            for (int j = i + 1; j < size; j++) {
                if (arr[j] < arr[i]) {
                    swapReference(arr, i, j);
                }
            }
        }
    }

    /**
     * 插入排序
     * (每步将一个待排序的记录，依次插入到已经排好序的序列中的合适位置，直到全部插入玩为止)
     * 方法：1、从第一个元素开始，该元素视为已排序，
     * 2、取出数组中的下一个元素，在已排好序的序列中从后往前扫描
     * 3、若该元素（已排序）大于新元素，将该元素移到下一位置
     * 4、重复上一步骤，直到找到已排序的元素小于或等于新元素的位置
     * 5、将新元素插入到该位置中
     * 6、重复步骤2
     *
     * @param arr 待排序数组
     */
    static void insertionSort(int[] arr) {
        System.out.println("插入排序：");
        int size = arr.length;
        int temp;
        int j;//符合插入条件的数组下标
        for (int i = 0; i < size; i++) {
            temp = arr[i];
            //假如temp比前面的值小，则将前面的值后移
            for (j = i; j > 0 && arr[j - 1] > temp; j--) {
                arr[j] = arr[j - 1];//将前面的值后移
            }
            arr[j] = temp;
        }
    }

    /**
     * 插入排序的另一种形式
     *
     * @param arr 待排序数组
     */
    static void insertionSort1(int[] arr) {
        int size = arr.length;
        for (int i = 0; i < size - 1; i++) {
            for (int j = i + 1; j > 0; j--) {//将待插入元素插入有序序列中采用的是冒泡的方式
                if (arr[j - 1] <= arr[j])
                    break;
                swapReference(arr, j - 1, j);
            }
        }
    }

    /**
     * 折半插入排序
     * （与插入排序的不同之处是：折半插入排序的待插入元素的插入位置是使用折半查找来确定的，
     * 在数据量大的时候大幅减少了元素比较时间）
     *
     * @param arr 待排序数组
     */
    static void binaryInsertionSort(int[] arr) {
        int size = arr.length;
        for (int i = 1; i < size; i++) {//通常第一个元素默认为有序
            int temp = arr[i];//存储待插入元素数据
            int low = 0;//记录搜索范围的左边界
            int high = i - 1;//记录搜索范围的右边界
            while (low <= high) {
                int middle = (low + high) / 2;
                //比较中间位置和i处元素大小，缩小搜索范围
                if (arr[middle] < temp) {
                    low = middle + 1;
                } else {
                    high = middle - 1;
                }
            }

            //将low~i处的元素整体向后移动一位
            System.arraycopy(arr, low, arr, low + 1, i - low);

            arr[low] = temp;
        }
    }

    /**
     * 快速排序
     *
     * @param arr 待排序数组
     */
    static void quickSort(int[] arr) {
        quickSortRecursive(arr, 0, arr.length - 1);
    }

    /**
     * 交换排序数组中两个元素的顺序
     *
     * @param arr   待排序数组
     * @param left  数组左元素
     * @param right 数组右元素
     */
    private static void swapReference(int[] arr, int left, int right) {
        if (left == right) {
            return;
        }
        int temp = arr[left];
        arr[left] = arr[right];
        arr[right] = temp;
    }

    /**
     * 快速排序（递归形式）
     * 1、首先选组中值middle作为枢纽
     * 2、从左往右遍历直到第一个大于middle的元素x
     * 3、从右往左遍历直到第一个小于middle的元素y
     * 4、将x和y互换位置
     * 5、重复2和3步骤 直到将整个待排序列分为两个部分，左边部分所有元素值比middle小，右边部分所有元素值比middle大
     * 6、分别对左右两个部分的序列执行1-5操作
     *
     * @param arr   待排序数组
     * @param left  数组左下标
     * @param right 数组右下标
     */
    private static void quickSortRecursive(int[] arr, int left, int right) {
        //判断停止递归的条件（即数组的左下标大于数组的右下标），
        //一般在程序的开始处判断，避免额外的操作
        if (left >= right) {
            return;
        }

        //获取每次分值后的枢纽中值索引位置
        int mid = partition(arr, left, right);

        quickSortRecursive(arr, left, mid - 1);
        quickSortRecursive(arr, mid + 1, right);
    }

    /**
     * 快速排序（非递归形式）
     * 采用栈来实现
     *
     * @param arr   待排序数组
     * @param left  左边界
     * @param right 右边界
     */
    private static void quickSortWithoutRecursive(int[] arr, int left, int right) {
        if (arr == null || arr.length == 1)
            return;

        Stack<Integer> stack = new Stack<>();
        stack.push(left);
        stack.push(right);

        //开始循环，结束条件为栈空
        while (!stack.isEmpty()) {
            //栈顶两个元素出栈
            int end = stack.pop();
            int start = stack.pop();

            //右边界小于等于左边界，则不需要做任何操作，结束本次循环，继续出栈
            if (start >= end) {
                continue;
            }

            //获取每次分值后的枢纽索引位置
            int mid = partition(arr, start, end);

            //左半部分入栈
            stack.push(start);
            stack.push(mid - 1);
            //右半部分入栈
            stack.push(mid + 1);
            stack.push(end);
        }
    }

    /**
     * 确定每次分区后枢纽中值索引位置
     * 由于每次完整分值后，枢纽中值索引都可能会改变，因此该中值索引取最终每次完整分值后的左边界位置
     *
     * @param arr   待排序数组
     * @param left  左边界
     * @param right 右边界
     * @return 中值位置
     */
    private static int partition(int[] arr, int left, int right) {
        int mid = left + (right - left) / 2;
        int pivot = arr[mid];
        swapReference(arr, mid, right);

        int start = left;
        int end = right - 1;

        while (start < end) {
            while (start < end && arr[start] < pivot) {
                start++;
            }
            while (start < end && arr[end] >= pivot) {
                end--;
            }

            swapReference(arr, start, end);
        }

        if (arr[start] >= arr[right]) {
            swapReference(arr, start, right);
        }

        return start;
    }

    /**
     * shell排序（通过比较一定间隔的元素工作；个趟所比较的距离随着算法的进行而减小，直到最后只比较相邻元素为止）
     * 使用一个增量，将待排序数组分割成若干个子数组，然后对这些个子数组进行直接插入排序
     * 缩小该增量，继续对被该增量分割出来的若干个子数组执行直接插入排序，直到增量缩小为1，也就是只比较相邻元素
     *
     * @param arr 待排序数组
     */
    static void shellSort(int[] arr) {
        int gap = 1;
        int length = arr.length;
        //首先确定最初的增量
        while (gap < length / 3) {
            gap = gap * 3 + 1;
        }
        for (; gap > 0; gap /= 3) {
            for (int i = gap; i < arr.length; i++) {
                int temp = arr[i];//保存待插入元素的值
                int j;
                //对以增量分割出来的子数组执行直接插入排序
                for (j = i; j >= gap && arr[j - gap] > temp; j -= gap) {
                    arr[j] = arr[j - gap];
                }
                arr[j] = temp;
            }
        }
    }

    /**
     * 归并排序（迭代方法）
     * 1、申请空间，使其大小为两个已经排序序列之和，用来存放合并后的序列
     * 2、设定两个指针，位置分别为两个已经排序序列的初始位置
     * 3、比较两个指针所指向的元素，将较小的元素放入到合并空间，并移动原指向较小元素的指针到下一位置
     * 4、重复步骤3直到某一指针达到序列末尾
     * 5、将另一序列剩下所有元素直接复制到合并序列尾
     *
     * @param arr 待排序数组
     */
    static void mergeSortWithoutRecursion(int[] arr) {
        int length = arr.length;
        int[] result = new int[length];//临时数组，保存归并排序后的数组空间
        int start;//设置起始下标
        int block;//定义每次归并的区间

        //逐步增大每次归并的区间
        // （首先将整个待排序列分成若干个只含有2个元素的子数组，分别对其进行归并，
        // 然后将其分成若干个含有4个元素的子数组，对其进行归并，再然后分成含有8个元素的子数组，以此类推）
        //这是采用循环的方式，递归只是把外层的依次归并改为了递归方式
        for (block = 1; block < length * 2; block *= 2) {
            for (start = 0; start < length; start += block * 2) {
                int low = start;//获取result的起始下标
                int mid = (start + block) < length ? (start + block) : length;
                int high = (start + block * 2) < length ? (start + block * 2) : length;
                //设置两个块的起始下标和结束下标
                int start1 = low;
                int start2 = mid;
                //开始对两个block进行归并排序
                while (start1 < mid && start2 < high) {
                    result[low++] = arr[start1] < arr[start2] ? arr[start1++] : arr[start2++];
                }
                //将剩下的某个序列中的所有元素复制到合并序列中
                while (start1 < mid) {
                    result[low++] = arr[start1++];
                }
                while (start2 < high) {
                    result[low++] = arr[start2++];
                }
            }
            //将临时数组中的元素复制回原数组中
            int[] temp = arr;
            arr = result;
            result = temp;
        }
    }

    /**
     * 归并排序（递归方法）
     * 1、将序列相邻两个元素进行归并操作，形成n/2个有序序列
     * 2、将上述序列再次归并，形成n/4个有序序列
     * 3、重复步骤2，直到所有元素排序完毕
     *
     * @param arr 待排序数组
     */
    static void mergeSortRecursion(int[] arr) {
        mergeRecursion(arr, 0, arr.length - 1);
    }

    /**
     * 归并递归
     *
     * @param arr   待排序数组
     * @param left  左边界
     * @param right 右边界
     */
    private static void mergeRecursion(int[] arr, int left, int right) {
        if (left >= right)
            return;
        int mid = (left + right) / 2;
        int start1 = left;
        int start2 = mid + 1;
        mergeRecursion(arr, start1, mid);
        mergeRecursion(arr, start2, right);

        int[] result = new int[arr.length];//临时数组，保存归并排序后的数组元素
        int low = left;
        while (start1 <= mid && start2 <= right) {
            result[low++] = arr[start1] < arr[start2] ? arr[start1++] : arr[start2++];
        }
        while (start1 <= mid) {
            result[low++] = arr[start1++];
        }
        while (start2 <= right) {
            result[low++] = arr[start2++];
        }

        //将临时数组中的元素复制到原数组中
        for (low = left; low <= right; low++)
            arr[low] = result[low];
    }

    /**
     * 堆排序（递归形式，主要是在构建大根堆采用的递归）
     * 1、将待排序数组R构建为一个大根堆（即根节点值大于其子节点的值）
     * 2、将最大元素即根节点元素R[0]和堆的最后一个R[n]元素互换
     * 3、对剩下的0-（n-1）个元素继续构建堆（新的根节点元素可能不满足堆序性质）
     * 4、将最大元素根节点元素R[0]和最后一个元素R[n-1]互换
     * 5、重复操作3和4，直到整个数组排好序
     *
     * @param arr 待排序数组
     */
    static void heapSortWithRecursion(int[] arr) {
        //首先将数组堆化
        int length = arr.length - 1;
        //不必从最后一个叶子节点开始，从第一个非叶子节点开始即可（一般倾向于从中间开始）
        int beginIndex = (length - 1) >> 1;
        for (int i = beginIndex; i >= 0; i--) {
            buildMaxHeap(arr, i, length);
        }

        //对堆化数据排序（由于已经堆化的数据已经满足大根堆的堆序性质，因此直接互换第一个和最后一个元素的位置即可）
        for (int i = length; i > 0; i--) {
            swapReference(arr, 0, i);
            buildMaxHeap(arr, 0, i - 1);
        }
    }

    /**
     * 构建堆（该堆为大根堆，即根节点的元素值大于其子节点的元素值）递归形式
     *
     * @param arr    待排序数组
     * @param index  根节点索引
     * @param length 数组长度
     */
    private static void buildMaxHeap(int[] arr, int index, int length) {
        int left = (index << 1) + 1;//左子节点索引
        int right = left + 1;//右子节点索引
        int maxIndex = left;//先默认左子节点为最大节点

        if (left > length)//左子节点超出索引范围
            return;
        //若右子节点也存在
        if (right <= length && arr[left] < arr[right]) {
            maxIndex = right;
        }

        //比较父节点和最大子节点值的大小
        if (arr[maxIndex] > arr[index]) {//如果子节点比根节点大（即不满足大根堆的堆序性质）
            swapReference(arr, maxIndex, index);//如果父节点被子节点调换
            buildMaxHeap(arr, maxIndex, length);//则需要继续判断换下后的父节点是否符合以该换下的父节点为根节点的子堆的性质
        }
    }

    /**
     * 堆排序（非递归形式，将递归部分改为循环）
     *
     * @param arr 待排序数组
     */
    static void heapSortWithoutRecursion(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            createMaxHeap(arr, arr.length - 1 - i);
            swapReference(arr, 0, arr.length - 1 - i);
        }
    }

    /**
     * 构建大根堆（符合大根堆的堆序性质，非递归）
     *
     * @param arr    待排序数组
     * @param length 数组长度
     */
    private static void createMaxHeap(int[] arr, int length) {
        int beginIndex = (length - 1) >> 1;
        for (int i = beginIndex; i >= 0; i--) {
            //保存当前正在判断的节点
            int index = i;
            //若当前节点的左子节点存在
            while ((index << 1) + 1 <= length) {
                int leftIndex = (index << 1) + 1;//获取左子节点索引
                int maxIndex = leftIndex;//先设置左子节点为最大子节点
                int rightIndex = leftIndex + 1;//获取右子节点索引
                //若右子节点存在，比较两个子节点大小
                if (rightIndex <= length && arr[rightIndex] > arr[leftIndex]) {
                    maxIndex = rightIndex;
                }

                //若最大子节点比父节点大，交换两个节点的值
                if (arr[maxIndex] > arr[index]) {
                    swapReference(arr, index, maxIndex);
                    //将最大节点的索引赋值给index，也就是之前父节点所在的索引，
                    //主要是为了确保以交换下来的父节点为根节点的新的子堆是否满足堆序性质
                    index = maxIndex;
                } else {
                    //若父节点满足堆序性质，则结束本次循环
                    // 因为没有发生节点交换，所以index还是原来的值，若不退出则会一直循环下去
                    break;
                }
            }
        }
    }
}
