#! /usr/bin/python
# coding:utf-8


def binary_search_recursion(key, arr, start, end):
    '''list 必须是排序好的
    '''
    if start > end:
        return -1
    mid = start + (end - start) // 2
    if arr[mid] > key:
        return binary_search_recursion(key, arr, start, mid - 1)
    if arr[mid] < key:
        return binary_search_recursion(key, arr, mid + 1, end)
    return mid


if __name__ == '__main__':
    arr = [3, 9, 28, 67, 12, 45]
    arr.sort()
    print(binary_search_recursion(12, arr, 0, len(arr)-1))
    print(binary_search_recursion(3, arr, 0, len(arr)-1))
    print(binary_search_recursion(9, arr, 0, len(arr)-1))
    print(binary_search_recursion(99, arr, 0, len(arr)-1))