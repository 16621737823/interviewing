// �����㷨��ǰ�ᣬ�����������
// ����Ĵ���ȫ��ʹ������ұ�����
public class �����㷨 {
    // 1.�ҵ���һ�����ڵ���target��Ԫ��
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

    // 2.�ҵ���һ������target��Ԫ�أ�ת��Ϊ>=target+1
    // 3.�ҵ���һ��С��target��Ԫ�أ�ת��Ϊ���1����ߵĵ�һ����
    // 4.�ҵ���һ��С�ڵ���target��Ԫ�أ�ת��Ϊ���2����ߵĵ�һ����

    public static void main(String[] args) {
        int[] nums = {1, 3, 5, 6};
        System.out.println(binarySearch_1(nums, 5));
    }
}
