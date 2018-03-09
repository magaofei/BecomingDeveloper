
# property
class Test(object):
    """
    """
    def __init__(self):
        """
        """
    
    
    @property
    def foo(self):
        return self._foo
    
    @foo.setter
    def foo(self, foo):
        self._foo = foo


if __name__ == '__main__':
    test = Test()
    test.foo = "foo1"
    print(test.foo)