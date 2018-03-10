# 选择排序
# 找到最小的元素,然后和第一个元素交换位置,再从剩下的元素中找到最小的,和第二个交换位置
import math
math.sqrt()
def less(foo, bar):
    if foo < bar:
        return True
    else:
        return False

def select(nums):
    """
    """
    min = 9999
    n = len(nums)

    for (num, index) in enumerate(nums):
        min = index

        j = index + 1
        while j < n:
            if less(nums[j], nums[min]):
                min = j
            j += 1
        print(index)
        nums[index], nums[min] = nums[min], nums[index]
        
    return nums
    



if __name__ == '__main__':
    nums = [145, 23, 113, 443244, 23435,23423423,4,23,423,423,32,43,43,234,12]
    a = select(nums)
    print(a)
    