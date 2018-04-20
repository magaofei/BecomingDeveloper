__doc__ = """
iter for a number demo
"""

class IterNumber(object):
    """
    """
    def __init__(self, num):
        self.num = num
        self.index = 1
    
    def __iter__(self):
        return self
    
    def __next__(self):
        r = int(self.num / self.index % 10)
        if r <= 0:
            raise StopIteration
        self.index *= 10
        return r



if __name__ == '__main__':
    i = IterNumber(123)
    r = next(i)
    print(r)
    r = next(i)
    print(r)
    r = next(i)
    print(r)
    # r = next(i)
    # print(r)

