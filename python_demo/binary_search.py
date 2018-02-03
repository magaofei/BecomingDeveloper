# coding: utf-8
def binary_search(foo_array, start, end, key):
    """
    二分查找

    搜索过程从数组的中间元素开始，如果中间元素正好是要查找的元素，则搜索过程结束；如果某一特定元素大于或者小于中间元素，则在数组大于或小于中间元素的那一半中查找，而且跟开始一样从中间元素开始比较。如果在某一步骤数组为空，则代表找不到。这种搜索算法每一次比较都使搜索范围缩小一半
    return key path
    """
    if start > end:
        return -1
    half = start + (end - start) // 2   # 防止溢出  (start + end ) / 2
    half_value = foo_array[half]

    """
    !!!!!!!!!!
    注意 return
    没有 return 的话就没有把值带回来
    !!!!!!!!!!
    """
    if half_value > key:
        return binary_search(foo_array, start, half - 1, key)


    if half_value < key:
        return binary_search(foo_array, half + 1, end, key)

    return half

if __name__ == '__main__':
    # foo_array = [i for i in range(10)]
    foo_array = [1, 2, 3, 4, 5, 6]

    result = binary_search(foo_array, 0, len(foo_array) - 1, 2)
    print(result)