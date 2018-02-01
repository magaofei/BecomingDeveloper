from dis import dis

# b = 6

# def f2(a):
#     # global b
#     print(a)
#     print(b)
#     b = 9

# f2(3)


def f3():
    b = 1

    total = 0
    def f4(new_value):
        nonlocal b, total
        b = b+1
        total += new_value
        return total / b

    return f4

f = f3()
f(10)

# print(dis(f2))


"""
重点

Python在编译字节码时，先判断b是局部变量，因为b被赋值了，这是Python的设计，和JavaScript的设计正好相反。
Python默认是局部变量，JavaScript变量如果不写var默认是全局变量

解决方法，
在函数内使用 global 关键字修饰  line 6

"""