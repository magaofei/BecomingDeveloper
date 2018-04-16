class ClassDemo(object):

    def __init__(self, foo):
        self.foo = foo

    @classmethod
    def test(cls, foo):
        t = cls(foo)
        return t.foo


class StaticDemo(object):

    @staticmethod
    def test(bar):
        return bar

    @staticmethod
    def test_static(d):
        print(d)


if __name__ == '__main__':
    bar = ClassDemo.test("bar")
    print(bar)
    print(StaticDemo.test('foo'))

    
