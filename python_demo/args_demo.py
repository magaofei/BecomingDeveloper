

def func1(*arg, **kwargs):
    # print(arg)
    # print(type(arg))

    print('print(arg)')
    for i in arg:
        print(i)
    print('kwargs')
    print(kwargs.get('foo'))

    # print(*arg)
    # print(type(*arg))

    # print(kwargs)
    foo = kwargs.get('foo')
    # print(foo)
    # print(**kwargs)

def func2(*arg):
    print(__name__)
    print(arg)


if __name__ == '__main__':

    func1([1, 2, 3, 4], foo=1, bar=2)
    # print(r1)
    d = [i for i in range(10)]
    d2 = (i for i in range(10))
    print(type(d2))
    r = func2(d)
    print(r)
    # print(*d)


    