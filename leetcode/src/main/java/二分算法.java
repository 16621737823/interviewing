// 二分算法的前提，数组是有序的
// 后面的代码全部使用左闭右闭区间
public class 二分算法 {
    // 1.找到第一个大于等于target的元素
    public static int binarySearch_1(int[] nums, int target) {
        int left=0;
        int right=nums.length-1;
        while(left<=right){
            int mid=(right-left)/2+left;
            if(nums[mid]<target){
                left=mid+1;
            }else{
                right=mid-1;
            }
        }
        return left;
    }

    // 2.找到第一个大于target的元素，转化为>=target+1
    // 3.找到第一个小于target的元素，转化为情况1的左边的第一个数
    // 4.找到第一个小于等于target的元素，转化为情况2的左边的第一个数

    public static void main(String[] args) {
        int[] nums = {1, 3, 5, 6};
        System.out.println(binarySearch_1(nums, 5));
    }
}
